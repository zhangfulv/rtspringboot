package com.routine.tool;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.routine.tool.jdbcConvert.*;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;


public class JdbcUtil {
	public static Map<Class,IClsConvert> convertMap = new HashMap<Class,IClsConvert>(){
		{
			put(Boolean.class,new BooleanIClsConvert());
			put(Double.class,new DoubleIClsConvert());
			put(java.sql.Date.class,new DateIClsConvert());
			put(Float.class,new FloatIClsConvert());
			put(Integer.class,new IntegerIClsConvert());
			put(Timestamp.class,new TimestampClsConvert());
			put(String.class,new StringClsConvert());
		}
	};

	public static String toString(ResultSet resultSet) throws SQLException {
		StringBuilder buf = new StringBuilder();
		final List<Ord> columns = columnLabels(resultSet);
		while (resultSet.next()) {
			for (Ord column : columns) {
				buf.append(column.i == 1 ? "" : "; ").append(column.e).append("=")
						.append(resultSet.getObject(column.i));
			}
			buf.append("\n");
		}
		return buf.toString();
	}
	/**
	 * 根据结果返回json数组
	 * @param rs 结果集
	 * @return json数组
	 * @throws SQLException
	 * Zf 2018年1月6日 下午5:46:33
	 */
	public static JSONArray toJSONObject(ResultSet rs) throws SQLException {
		JSONArray jsonArr = new JSONArray();
		// 获取列数
		ResultSetMetaData metaData = rs.getMetaData();
		int columnCount = metaData.getColumnCount();
		// 遍历ResultSet中的每条数据
		while (rs.next()) {
			JSONObject jsonObj = new JSONObject();
			// 遍历每一列
			for (int i = 1; i <= columnCount; i++) {
				String columnName =metaData.getColumnLabel(i);
				String value = rs.getString(columnName);
				jsonObj.put(columnName, value);
			}
			jsonArr.add(jsonObj);
		}
		return jsonArr;
	}
	/**
	 * 根据结果集返回对象集合
	 * @param resultSet 结果集 
	 * @param record 对象类
	 * @return
	 * Zf 2018年6月22日 上午9:36:35
	 */
	@SuppressWarnings("unused")
	public  static <T> List<T> toListPoJoObject(ResultSet resultSet, Class<T> record){
		List<T> resultList = new ArrayList<T>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try{
			while(resultSet.next()){
				T obj = record.newInstance();
				Field[] fields = obj.getClass().getDeclaredFields();
				for(Field f : fields) {  
					if(!f.getName().startsWith("serialVersion")){
						try{
					     PropertyDescriptor pd = new PropertyDescriptor(f.getName(), record);
						 //获得写方法
					     Method wM = pd.getWriteMethod();
					     //获取列名
					     String columnName = StringUtil.toUnderLineLowerStr(f.getName());
					     Object columnValue = resultSet.getObject(columnName);
					     if(columnValue != null&&!"".equals(columnValue)){
							 IClsConvert iClsConvert = convertMap.get(f.getType());
							 if(iClsConvert != null) {
								 iClsConvert.invokeSetConvert(wM, obj, columnValue);
							 }else{
								 wM.invoke(obj, columnValue);
							 }
					     }
						}catch(Exception e){
							e.printStackTrace();
							System.out.println("字段:"+f.getName()+"###############");
							System.out.println("没有改写入方法:"+e.getMessage());
						}
					}
				}
				resultList.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return resultList;
	}
	
	private static List<Ord> columnLabels(ResultSet resultSet) throws SQLException {
	    int n = resultSet.getMetaData().getColumnCount();
	    List<Ord> columns = new ArrayList<>();
	    for (int i = 1; i <= n; i++) {
	      columns.add(Ord.of(i, resultSet.getMetaData().getColumnLabel(i)));
	    }
	    return columns;
	  }
	

	static class Ord{
		public int i;
		public String e;
		
		private Ord(int i,String e){
			this.i = i;
			this.e = e;
		}
		
		public static Ord of(int i,String e){
			
			return new Ord(i, e);
		}
	}

	/**@Description   通过bean类型，组装对应的插入语句
	 * @return java.lang.String insert sql语句
	 * @Author zf
	 * @Date 2019-01-18 10:07
	 * @Update
	 **/
	public static<T> String toInsertSQLFromBean(T record) {
		if(record == null) {
			throw new NullPointerException("传入record参数不能为空.");
		}
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Class<?> beanClass = record.getClass();
		String tableName = StringUtil.toUnderLineLowerStr(record.getClass().getSimpleName(), true);
		jdbcAnnotation annotation = record.getClass().getAnnotation(jdbcAnnotation.class);
		if(annotation != null){
			tableName = annotation.tableName();
		}
		Method[] methods = beanClass.getMethods();
		Object getFuncObject = null;
		StringBuilder inserSqlBuilder = new StringBuilder();
		inserSqlBuilder.append("insert into ").append(tableName).append("(");
		// Bean中get方法名称集合
		List<String> getFuncNames = new ArrayList<String>();
		try {
			Object beanInfo = record;
			for (Method method : methods) {
				if (method.getName().startsWith("get") && !method.getName().equalsIgnoreCase("getClass")) {
					getFuncObject = beanClass.getMethod(method.getName()).invoke(beanInfo);
					// 如果值为空，则不对该【列】进行数据填充
					if(null == getFuncObject || "".equals(getFuncObject.toString())) {
						continue;
					}
					getFuncNames.add(method.getName());
					jdbcAnnotation annotation1 = method.getAnnotation(jdbcAnnotation.class);
					if(annotation1 != null){
						inserSqlBuilder.append(annotation1.field()).append(",");
					}else {
						inserSqlBuilder.append(
								StringUtil.toUnderLineLowerStr(method.getName().replaceAll("get", ""), true)
						).append(",");
					}
				}
			}
			inserSqlBuilder = inserSqlBuilder.deleteCharAt(inserSqlBuilder.length() - 1);

			inserSqlBuilder.append(") values(");
			for (String getFuncName : getFuncNames) {
				// 如果是时间类型
				getFuncObject = beanClass.getMethod(getFuncName).invoke(beanInfo);
				if (getFuncObject instanceof Date) {
					inserSqlBuilder.append("'").append(sdf.format(getFuncObject)).append("'").append(",");
				} else {
					inserSqlBuilder.append("'").append(getFuncObject).append("'").append(",");
				}
			}
			inserSqlBuilder = inserSqlBuilder.deleteCharAt(inserSqlBuilder.length() - 1);
			inserSqlBuilder.append(")");
		}catch (Exception e){
			e.printStackTrace();
		}
			return inserSqlBuilder.toString();
	}
	public static<T> String toInsertSQLFromBean(Collection<T> recordList){
		return toInsertSQLFromBean(recordList,200);
	}

	/**
	 * 根据对象列表  ，转换为plsql。
	 * @param recordList 对象列表
	 * @param ticket 默认200
	 * @param <T>
	 * @return
	 */
	public static<T> String toInsertSQLFromBean(Collection<T> recordList,int ticket) {
		if(recordList == null){
			return null;
		}
		int ticketNumber = 0;
		StringBuilder stringBuilder = new StringBuilder();
		int start = 0;
		for(T record : recordList){
			String plSql = toInsertSQLFromBean(record);
			if(start == 0){
				stringBuilder.append(plSql);
			}else {
				stringBuilder.append(",").append(plSql.substring(plSql.indexOf("values(")+6));
			}
			start = 1;
			ticketNumber++ ;
			if(ticketNumber >= ticket){
				break;
			}
		}
		return stringBuilder.append(";").toString();
	}
}
