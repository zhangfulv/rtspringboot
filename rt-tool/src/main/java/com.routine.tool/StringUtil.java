package com.routine.tool;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    
    /**
     * 生成32位uuid
     * @author zh 2017年3月20日 下午3:53:11
     * @return
     */
    public static String getUUID() {
        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
        return uuid;
    }
    
    /**
     * 得到出生日期 (yyyy-MM-dd),根据身份证号码
     * @author zf 2017年8月12日 下午3:28:10
     * @param cardNo 身份证号码
     * @return 出生日期
     */
    public static String getBirthdayByCardNo(String cardNo) {
        StringBuilder birthday = new StringBuilder();
        if (cardNo!=null && !"".equals(cardNo) && cardNo.trim().length() == 18) {
            // 二代身份证长度为18位
            birthday.append(cardNo.substring(6, 10)).append("-").append(cardNo.substring(10, 12)).append("-")
                    .append(cardNo.substring(12, 14));
            return birthday.toString();
        }else{
            return "";
        }
    }
    
    /**
     * 根据身份证号码获取年龄
     * @author yangyi 2017年10月23日 上午10:18:33
     * @param IdNO
     * @return
     */
    public static int getToAge(String IdNO){
        int leh = IdNO.length();
        String dates="";
        if (leh == 18) {
            dates = IdNO.substring(6, 10);
            SimpleDateFormat df = new SimpleDateFormat("yyyy");
            String year=df.format(new Date());
            int u=Integer.parseInt(year)-Integer.parseInt(dates);
            return u;
        }else if(leh ==15){
            dates = IdNO.substring(6, 8);
            return Integer.parseInt(dates);
        }else{
        	return 0;
        }
    }
    /**
     * 得到隐藏后的身份证号码
     * @author zf 2017年8月17日 下午4:17:32
     * @param cardNo
     * @return
     */
    public static String getHideCardNoByCardNo(String cardNo) {
        StringBuilder birthday = new StringBuilder();
        if (cardNo.trim().length() == 18) {
            // 二代身份证长度为18位
            birthday.append(cardNo.substring(0, cardNo.length() - 4)).append("****");
        }
        
        return birthday.toString();
    }
    
    /**
     * 将含有大写字母的换成下划线加小写字母 eg : A -> _a
     * @author zf 2017年8月29日 下午4:20:18
     * @param str 转换参数
     * @return 将大写字母全部变成 下划线 + 小写字母 并返回字符串
     */
    public static String toUnderLineLowerStr(String str) {
        char[] charArray = str.toCharArray();
        StringBuilder sb = new StringBuilder(charArray.length);
        for (char c : charArray) {
            if ((int)c < 97 &&  (int)c >= 65 && (int)c <= 90) {
                sb.append("_").append((char)((int)c + 32));
            } else {
                sb.append(c);
            }
        }
        String string = sb.toString();
        return string;
    }
    /**
     * 将含有大写字母的换成下划线加小写字母 eg : A -> _a
     * @author zf 2017年8月29日 下午4:20:18
     * @param str 转换参数
     * @param isContinueFirst 是否忽略第一个，直接转为小写
     * @return 将大写字母全部变成 下划线 + 小写字母 并返回字符串
     */
    public static String toUnderLineLowerStr(String str,Boolean isContinueFirst) {
        char[] charArray = str.toCharArray();
        StringBuilder sb = new StringBuilder(charArray.length);
        int i=0;
        for (char c : charArray) {
            if ((int)c < 97 &&  (int)c >= 65 && (int)c <= 90) {
            	 if(i==0 && isContinueFirst){
                     sb.append((char)((int)c + 32));
                     i++;
                 }else{
                     sb.append("_").append((char)((int)c + 32));
                 }
            } else {
                sb.append(c);
            }
        }
        String string = sb.toString();
        return string;
    }
    
    /**
     * 下划线转驼峰法
     * @param line 源字符串
     * @param smallCamel 大小驼峰,是否为小驼峰
     * @return 转换后的字符串
     */
    public static String underline2Camel(String line,boolean smallCamel){
        if(line==null||"".equals(line)){
            return "";
        }
        StringBuffer sb=new StringBuffer();
        Pattern pattern=Pattern.compile("([A-Za-z\\d]+)(_)?");
        Matcher matcher=pattern.matcher(line);
        while(matcher.find()){
            String word=matcher.group();
            sb.append(smallCamel&&matcher.start()==0?Character.toLowerCase(word.charAt(0)):Character.toUpperCase(word.charAt(0)));
            int index=word.lastIndexOf('_');
            if(index>0){
                sb.append(word.substring(1, index).toLowerCase());
            }else{
                sb.append(word.substring(1).toLowerCase());
            }
        }
        return sb.toString();
    }
    /**
     * 判断字符串是否为空 
     * null或者 只有空格的 默认为空.
     * @param str 待判定的字符串
     * @return
     * Zf 2018年4月26日 上午10:02:45
     */
    public static boolean isEmpty(String str){
        return  str == null || str.trim().equals("") || "null".equals(str);
    }
    /*public static void main(String[] args) {
		String sss="alarm_cardno";
		System.out.println(StringUtil.underline2Camel(sss, true));
	}*/

    /**
     * Determines if a string is not empty.
     */
    public static boolean isNotEmpty(CharSequence string) {
        return string != null && string.length() > 0;
    }
    
    /**
     * 18位身份证粗略验证
     * @author PuGang 2018年9月18日 上午10:54:19
     * @param idCard
     * @return
     */
    public static boolean is18IdCard(String idCard){  
        Pattern pattern = Pattern.compile("^(\\d{6})(19|20)(\\d{2})(1[0-2]|0[1-9])(0[1-9]|[1-2][0-9]|3[0-1])(\\d{3})(\\d|X|x)?$");
        Matcher matcher = pattern.matcher(idCard);  
        if(matcher.matches()){  
            return true;  
        }  
        return false;  
    }
    
    /**
     * 18位身份证校验,比较严格校验 
     * @author PuGang 2018年9月18日 上午10:55:27
     * @param idCard
     * @return
     */
    public static boolean is18IdCardVerification(String idCard){  
        Pattern pattern1 = Pattern.compile("^(\\d{6})(19|20)(\\d{2})(1[0-2]|0[1-9])(0[1-9]|[1-2][0-9]|3[0-1])(\\d{3})(\\d|X|x)?$");   
        Matcher matcher = pattern1.matcher(idCard);  
        int[] prefix = new int[]{7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2};  
        int[] suffix = new int[]{ 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 };  
        if(matcher.matches()){  
            Map<String, String> cityMap = initCityMap();  
            if(cityMap.get(idCard.substring(0,2)) == null ){  
                return false;  
            }  
            int idCardWiSum=0; //用来保存前17位各自乖以加权因子后的总和  
            for(int i=0;i<17;i++){  
                idCardWiSum+=Integer.valueOf(idCard.substring(i,i+1))*prefix[i];  
            }  
              
            int idCardMod=idCardWiSum%11;//计算出校验码所在数组的位置  
            String idCardLast=idCard.substring(17);//得到最后一位身份证号码  
              
            //如果等于2，则说明校验码是10，身份证号码最后一位应该是X  
            if(idCardMod==2){  
                if(idCardLast.equalsIgnoreCase("x")){  
                    return true;  
                }else{  
                    return false;  
                }  
            }else{  
                //用计算出的验证码与最后一位身份证号码匹配，如果一致通过，否则是无效的身份证号码  
                if(idCardLast.equals(suffix[idCardMod]+"")){  
                    return true;  
                }else{  
                    return false;  
                }  
           }  
        }  
        return false;  
    }
    
    /*身份证前缀*/
    private static Map<String, String> initCityMap(){  
        Map<String, String> cityMap = new HashMap<String, String>();  
            cityMap.put("11", "北京");  
            cityMap.put("12", "天津");  
            cityMap.put("13", "河北");  
            cityMap.put("14", "山西");  
            cityMap.put("15", "内蒙古");
            
            cityMap.put("21", "辽宁");  
            cityMap.put("22", "吉林");  
            cityMap.put("23", "黑龙江");
            
            cityMap.put("31", "上海");  
            cityMap.put("32", "江苏");  
            cityMap.put("33", "浙江");  
            cityMap.put("34", "安徽");  
            cityMap.put("35", "福建");  
            cityMap.put("36", "江西");  
            cityMap.put("37", "山东");
            
            cityMap.put("41", "河南");  
            cityMap.put("42", "湖北");  
            cityMap.put("43", "湖南");  
            cityMap.put("44", "广东");  
            cityMap.put("45", "广西");  
            cityMap.put("46", "海南");
            
            cityMap.put("50", "重庆");  
            cityMap.put("51", "四川");  
            cityMap.put("52", "贵州");  
            cityMap.put("53", "云南");  
            cityMap.put("54", "西藏");
            
            cityMap.put("61", "陕西");  
            cityMap.put("62", "甘肃");  
            cityMap.put("63", "青海");  
            cityMap.put("64", "宁夏");  
            cityMap.put("65", "新疆");
            
//          cityMap.put("71", "台湾");  
//          cityMap.put("81", "香港");  
//          cityMap.put("82", "澳门");  
//          cityMap.put("91", "国外");  
            return cityMap;  
        }
    
    /**
     * 逗号隔开的字符串转换成'aa','bb'
     * @author hxn 
     * @date 2018年10月22日  上午11:32:42
     * @param str
     * @return
     * String
     */
    public static String commaStrToQuotesStr(String str) {
    	  StringBuffer sb = new StringBuffer();
    	  String[] temp = str.split(",");
    	  for (int i = 0; i < temp.length; i++) {
    	   if (!"".equals(temp[i]) && temp[i] != null)
    	    sb.append("'" + temp[i] + "',");
    	  }
    	  String result = sb.toString();
    	  String tp = result.substring(result.length() - 1, result.length());
    	  if (",".equals(tp))
    	   return result.substring(0, result.length() - 1);
    	  else
    	   return result;
    	 }
    /**
     * 
     *  海康4.0接口性别 转换为 华尊格式 
     * @param gender
     * @return int
     * dsc 2018年10月26日 下午5:18:59
     */
    public static int hikGenderToHzSex(String  gender){
    	int alarmHumanSex = 2;
    	if("male".equals(gender)){
    		alarmHumanSex = 0;
    	}else if("female".equals(gender)){
    		alarmHumanSex = 1;
    	}
    	return alarmHumanSex;
    }
}
