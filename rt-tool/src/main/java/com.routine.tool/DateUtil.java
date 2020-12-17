package com.routine.tool;

/**
 * Created by WanPengCheng on 2017/7/10.
 */


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 日期操作辅助类
 *
 * @author WanPengCheng
 * @create 2017-07-10-15:31
 **/
public class DateUtil {

	private DateUtil() {
	}

	/** 日期格式 **/
	public interface DATE_PATTERN {

		String HHMMSS = "HHmmss";
		String HH_MM_SS = "HH:mm:ss";
		String YYYYMMDD = "yyyyMMdd";
		String YYYY_MM_DD = "yyyy-MM-dd";
		String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
		String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
		String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
		String MMDD = "MM月dd日";
	}
	public interface DATE_FORMATTER{
		DateTimeFormatter YYYY_MM_DD_HH_MM_SS = DateTimeFormatter.ofPattern(DATE_PATTERN.YYYY_MM_DD_HH_MM_SS);
		DateTimeFormatter HHMMSS = DateTimeFormatter.ofPattern(DATE_PATTERN.HHMMSS);
		DateTimeFormatter HH_MM_SS = DateTimeFormatter.ofPattern(DATE_PATTERN.HH_MM_SS);
		DateTimeFormatter YYYYMMDD = DateTimeFormatter.ofPattern(DATE_PATTERN.YYYYMMDD);
		DateTimeFormatter YYYY_MM_DD = DateTimeFormatter.ofPattern(DATE_PATTERN.YYYY_MM_DD);
		DateTimeFormatter YYYYMMDDHHMMSS = DateTimeFormatter.ofPattern(DATE_PATTERN.YYYYMMDDHHMMSS);
		DateTimeFormatter YYYYMMDDHHMMSSSSS = DateTimeFormatter.ofPattern(DATE_PATTERN.YYYYMMDDHHMMSSSSS);
		DateTimeFormatter MMDD = DateTimeFormatter.ofPattern(DATE_PATTERN.MMDD);;
	}
	/**
	 * 格式化日期
	 *
	 * @param date
	 * @return
	 */
	public static final String format(Object date) {
		return format(date, DATE_PATTERN.YYYY_MM_DD);
	}

	/**
	 * 格式化日期
	 *
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static final String format(Object date, String pattern) {
		if (date == null) {
			return null;
		}
		if (pattern == null) {
			return format(date);
		}
		return new SimpleDateFormat(pattern).format(date);
	}

	/**
	 * 获取日期
	 *
	 * @return
	 */
	public static final String getDate() {
		return format(new Date());
	}

	/**
	 * 获取日期时间
	 *
	 * @return
	 */
	public static final String getDateTime() {
		return format(new Date(), DATE_PATTERN.YYYY_MM_DD_HH_MM_SS);
	}

	/**
	 * 获取日期
	 *
	 * @param pattern
	 * @return
	 */
	public static final String getDateTime(String pattern) {
		return format(new Date(), pattern);
	}

	/**
	 * 日期计算
	 *
	 * @param date
	 * @param field
	 * @param amount
	 * @return
	 */
	public static final Date addDate(Date date, int field, int amount) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(field, amount);
		return calendar.getTime();
	}

	/**
	 * 字符串转换为日期:不支持yyM[M]d[d]格式
	 *
	 * @param date
	 * @return
	 */
	public static final Date stringToDate(String date) {
		if (date == null) {
			return null;
		}
		String separator = String.valueOf(date.charAt(4));
		String pattern = "yyyyMMdd";
		if (!separator.matches("\\d*")) {
			pattern = "yyyy" + separator + "MM" + separator + "dd";
			if (date.length() < 10) {
				pattern = "yyyy" + separator + "M" + separator + "d";
			}
		} else if (date.length() < 8) {
			pattern = "yyyyMd";
		}
		pattern += " HH:mm:ss.SSS";
		pattern = pattern.substring(0, Math.min(pattern.length(), date.length()));
		try {
			return new SimpleDateFormat(pattern).parse(date);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 间隔天数
	 *
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static final Integer getDayBetween(Date startDate, Date endDate) {
		Calendar start = Calendar.getInstance();
		start.setTime(startDate);
		start.set(Calendar.HOUR_OF_DAY, 0);
		start.set(Calendar.MINUTE, 0);
		start.set(Calendar.SECOND, 0);
		start.set(Calendar.MILLISECOND, 0);
		Calendar end = Calendar.getInstance();
		end.setTime(endDate);
		end.set(Calendar.HOUR_OF_DAY, 0);
		end.set(Calendar.MINUTE, 0);
		end.set(Calendar.SECOND, 0);
		end.set(Calendar.MILLISECOND, 0);

		long n = end.getTimeInMillis() - start.getTimeInMillis();
		return (int) (n / (60 * 60 * 24 * 1000L));
	}

	/**
	 * 间隔月
	 *
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static final Integer getMonthBetween(Date startDate, Date endDate) {
		if (startDate == null || endDate == null || !startDate.before(endDate)) {
			return null;
		}
		Calendar start = Calendar.getInstance();
		start.setTime(startDate);
		Calendar end = Calendar.getInstance();
		end.setTime(endDate);
		int year1 = start.get(Calendar.YEAR);
		int year2 = end.get(Calendar.YEAR);
		int month1 = start.get(Calendar.MONTH);
		int month2 = end.get(Calendar.MONTH);
		int n = (year2 - year1) * 12;
		n = n + month2 - month1;
		return n;
	}

	/**
	 * 间隔月，多一天就多算一个月
	 *
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static final Integer getMonthBetweenWithDay(Date startDate, Date endDate) {
		if (startDate == null || endDate == null || !startDate.before(endDate)) {
			return null;
		}
		Calendar start = Calendar.getInstance();
		start.setTime(startDate);
		Calendar end = Calendar.getInstance();
		end.setTime(endDate);
		int year1 = start.get(Calendar.YEAR);
		int year2 = end.get(Calendar.YEAR);
		int month1 = start.get(Calendar.MONTH);
		int month2 = end.get(Calendar.MONTH);
		int n = (year2 - year1) * 12;
		n = n + month2 - month1;
		int day1 = start.get(Calendar.DAY_OF_MONTH);
		int day2 = end.get(Calendar.DAY_OF_MONTH);
		if (day1 <= day2) {
			n++;
		}
		return n;
	}

	/**
	 * date转成字符串
	 * 
	 * @author zh 2017年3月16日 下午3:31:55
	 * @param date
	 * @param format
	 * @return
	 */
	public static String dateToString(Date date, String format) {
		String mDate = "";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			mDate = sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mDate;
	}

	/**
	 * 日期转换成日期
	 * 
	 * @author zh 2017年3月16日 下午3:34:22
	 * @param date
	 * @param format
	 * @return
	 */
	public static Date dateToDate(Date date, String format) {
		Date tDate = null;
		String mDate = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			mDate = sdf.format(date);
			tDate = sdf.parse(mDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tDate;
	}

	/**
	 * 取得与原日期相差一定天数的日期，返回Date型日期
	 *
	 * @param date
	 *            原日期
	 * @param intBetween
	 *            相差的天数
	 * @return date加上intBetween天后的日期
	 */
	public static Date getDateBetween(Date date, int intBetween) {
		Calendar calo = Calendar.getInstance();
		calo.setTime(date);
		calo.add(Calendar.DATE, intBetween);
		return calo.getTime();
	}

	/**
	 * 取得当前月份总的天数
	 * 
	 * @return Zf 2018年4月2日 上午10:18:47
	 */
	public static int getCurrentMonthLastDay() {
		Calendar a = Calendar.getInstance();
		a.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		a.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}

	/**
	 * 得到上个月第一天时间 时间戳
	 * 
	 * @return Zf 2018年4月2日 上午11:42:47
	 */
	public static Long getLastMonthFirstDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTimeInMillis();
	}

	/**
	 * 得到上个月最后一天时间戳时间戳
	 * 
	 * @return Zf 2018年4月2日 上午11:43:45
	 */
	public static Long getLastMonthEndDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, -1);
		return calendar.getTimeInMillis();
	}

	/**
	 * 获取这个月第一天时间 时间戳
	 * 
	 * @return Zf 2018年4月2日 下午2:02:32
	 */
	public static Long getThisMonthFirstDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTimeInMillis();
	}

	/**
	 * 获取五天之前的时间 时间戳
	 * 
	 * @return Zf 2018年4月2日 下午2:02:32
	 */
	public static Long getFiveDayAgo() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - 5);
		return calendar.getTimeInMillis();
	}


	/**
	 * 时间处理 要获取前后几分钟的时间
	 * 
	 * @author hxn
	 * @date:2018年4月18日上午11:33:22
	 * @param date
	 * @return
	 */
	public static Date getWhatMin(Date date, int min) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, min);
		date = calendar.getTime();
		return date;
	}

	/**
	 * yyyy-MM-dd HH:mm:ss格式的字符串转时间
	 * 
	 * @author hxn
	 * @date:2018年4月12日下午4:20:08
	 * @return
	 */
	public static final Date getStringtoDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 时间转成yyyy-MM-dd HH:mm:ss格式的字符串
	 * 
	 * @author hxn
	 * @date:2018年4月12日下午5:30:23
	 * @param date
	 * @return
	 */
	public static final String getDatetoString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}

	/**
	 * 日期比较
	 * 
	 * @param dt1
	 * @param dt2
	 * @return 1：dt1 在dt2前 -1：dt1在dt2后 0：dt1等于dt2
	 */
	public static int compare_date(Date dt1, Date dt2) {
		try {
			if (dt1.getTime() > dt2.getTime()) {
				return 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				return -1;
			} else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}

	/**
	 * 判断时间是否在时间段内
	 * 
	 * @param date
	 *            需要判断的时间
	 * @param begin
	 *            开始时间
	 * @param end
	 *            结束时间
	 * @return
	 */
	public static boolean isInDate(Date date, Date begin, Date end) {
		return (date.getTime() >= begin.getTime()) && (date.getTime() <= end.getTime());
	}

	/**
	 * 获取指定时间的前一天时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date getNextDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		date = calendar.getTime();
		return date;
	}

	/**
	 * 以当前时间为标准，得到指定的时间戳
	 * 
	 * @param orderDay
	 *            指定的天数,可以为负数代表前几天
	 * @return Zf 2018年4月20日 下午7:59:45
	 */
	public static Long getOrderDateTime(int orderDay) {
		return getOrderDateTime(orderDay, new Date());
	}

	/**
	 * 以当前时间为标准，得到指定的时间戳
	 * 
	 * @param orderDay
	 *            指定的天数,可以为负数代表前几天
	 * @param orderDate
	 *            指定初始时间
	 * @return Zf 2018年4月20日 下午7:59:45
	 */
	public static Long getOrderDateTime(int orderDay, Date orderDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(orderDate);
		calendar.add(Calendar.DAY_OF_MONTH, orderDay);
		return calendar.getTimeInMillis();
	}

	/**
	 * 
	 * 获取当天0点时间或者24点时间
	 * 
	 * @param month
	 *            0,24
	 * @return int dsc 2018年6月20日 下午2:32:25
	 */
	public static String getTimeSmorningOrSnight(int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, month);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return format(cal.getTime(), DATE_PATTERN.YYYY_MM_DD_HH_MM_SS);

	}
	
	/**
	 * 获取当前时间倒推整数个小时的时间(返回时间为整点 如:2019-06-06 16:00:00)
	 * @author PuGang 2019年6月6日 下午5:41:56
	 * @param hours 非0整数(1,2...n)
	 * @return
	 */
	public static String getBacksteppingTime(int hours) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY) - hours);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return format(cal.getTime(), DATE_PATTERN.YYYY_MM_DD_HH_MM_SS);
	}
	
	/**
	 * 获取该月开始时间
	 * @author PuGang 2019年6月10日 下午12:38:28
	 * @param month 月份
	 * @return
	 */
	public static String getFirstDayOfMonth(int month) {
	     Calendar cal = Calendar.getInstance();
	     // 设置月份
	     cal.set(Calendar.MONTH, month - 1);
	     // 获取某月最小天数
	     int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
	     // 设置日历中月份的最小天数
	     cal.set(Calendar.DAY_OF_MONTH, firstDay);
	     // 格式化日期
	     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	     String firstDayOfMonth = sdf.format(cal.getTime())+" 00:00:00";
	     return firstDayOfMonth;
	 }
	
	/**
	 * 获得该月结束时间
	 * @author PuGang 2019年6月10日 下午12:40:35
	 * @param month 月份
	 * @return
	 */
	 public static String getLastDayOfMonth(int month) {
	    Calendar cal = Calendar.getInstance();
	    // 设置月份
	    cal.set(Calendar.MONTH, month - 1);
	    // 获取某月最大天数
	    int lastDay=0;
	   //2月的平年瑞年天数
	   if(month==2) {
		   lastDay = cal.getLeastMaximum(Calendar.DAY_OF_MONTH);
	   }else {
		   lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	   }
	    // 设置日历中月份的最大天数
	     cal.set(Calendar.DAY_OF_MONTH, lastDay);
	    // 格式化日期
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    String lastDayOfMonth = sdf.format(cal.getTime())+" 23:59:59";
	    return lastDayOfMonth;
	 }
	
	/**
	 * 
	 * 获取指定日期前几个小时的时间
	 * 
	 * @param hour
	 * @return String dsc 2018年6月25日 上午9:14:23
	 */
	public static String beforeOneHourToNowDate(Date tDate, int hour) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(tDate);
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - hour);
		return format(calendar.getTime(), DATE_PATTERN.YYYY_MM_DD_HH_MM_SS);
	}
	/**
	 * 判断某天是星期几
	 * @param date 必须yyyy-MM-dd
	 * @return: java.lang.String
	 * @auther: dsc
	 * @date: 2019/3/26 9:38
	 */
	public static String getWeekday(String date) {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdw = new SimpleDateFormat("E");
		Date d = null;
		try {
			d = sd.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sdw.format(d);
	}

	/**
	 * 根据传入的日期获取所在月份所有日期
	 * @param d Date
	 * @return: list
	 * @auther: dsc
	 * @date: 2019/3/26 9:57
	 */
	public static List<String>getAllDaysMonthByDate(Date d)//
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<String> lst = new ArrayList();
		Date date = getMonthStart(d);
		Date monthEnd = getMonthEnd(d);
		while (!date.after(monthEnd)) {
			lst.add(sdf.format(date));
			date = getNext(date);
		}
		return lst;
	}
	private static Date getMonthEnd(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		int index = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.add(Calendar.DATE, (-index));
		return calendar.getTime();
	}

	private static Date getNext(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 1);
		return calendar.getTime();
	}
	public static List<String>getAllDaysMonth()
	{
		List<String> lst=new ArrayList();
		Date d = new Date();
		// 月初
		// System.out.println("月初" + sdf.format(getMonthStart(d)));
		// 月末
		//System.out.println("月末" + sdf.format(getMonthEnd(d)));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = getMonthStart(d);
		Date monthEnd = getMonthEnd(d);
		while (!date.after(monthEnd)) {
			//System.out.println(sdf.format(date));
			lst.add(sdf.format(date));
			date = getNext(date);
		}
		return lst;
	}
	private static Date getMonthStart(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int index = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.add(Calendar.DATE, (1 - index));
		return calendar.getTime();

	}


		/**
         * 根据录入日期为标准，返回为时间增加（减少）相应秒数后的时间
         *
         * @param date
         *            录入日期
         * @param nSeconds
         *            增加(减少)秒数
         * @return 增加（减少相应）秒数后的日期 zf 2018年7月31日 上午9:59:43
         */
	public static String getDateBySeconds(Date date, int nSeconds) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND) + nSeconds);
		return format(calendar.getTime(), DATE_PATTERN.YYYY_MM_DD_HH_MM_SS);
	}

	/**
	 * 
	 * @Title: string2Date
	 * @Description: 字符串转日期
	 * @author huangpei
	 * @param date
	 * @param format
	 * @return Date
	 */
	public static Date string2Date(String date, String format) {
		if (StringUtil.isEmpty(format)) {
			format = DATE_PATTERN.YYYY_MM_DD_HH_MM_SS;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String dateString2String(String dateString, String sourceFormat, String destFormat) {
		if (StringUtil.isEmpty(sourceFormat)) {
			sourceFormat = DATE_PATTERN.YYYY_MM_DD_HH_MM_SS;
		}
		if (StringUtil.isEmpty(destFormat)) {
			destFormat = DATE_PATTERN.YYYY_MM_DD;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(sourceFormat);
		try {
			Date date = sdf.parse(dateString);
			return DateUtil.dateToString(date, destFormat);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 范围指定时间 前几天 某个小时的时间
	 * @param date 指定时间
	 * @param day  往前推几天
	 * @param month 小时 0-24
	 * @author wy 
	 * date:Feb 18, 2019 3:44:45 PM
	 * @return
	 */
    public static String returnPre(Date date, int day,int month) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, month);  
        calendar.set(Calendar.SECOND, 0);   
        calendar.set(Calendar.MINUTE, 0);   
        calendar.set(Calendar.MILLISECOND, 0); 
        calendar.add(Calendar.DATE, day);
        return format(calendar.getTime(),"yyyy-MM-dd HH:mm:ss");
    }
	/**
	 * 获得两个日期之间的所有月份
	 * @param minDate
	 * @param maxDate
	 * @return: java.util.List<java.lang.String>
	 * @auther: dsc
	 * @date: 2019/3/26 10:42
	 */
	public static List<String> getMonthBetweens(String minDate, String maxDate) throws ParseException {
		ArrayList<String> result = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//格式化为年月
		Calendar min = Calendar.getInstance();
		Calendar max = Calendar.getInstance();
		min.setTime(sdf.parse(minDate));
		min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);
		max.setTime(sdf.parse(maxDate));
		max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);
		 Calendar curr = min;
		  while (curr.before(max)) {
			      result.add(sdf.format(curr.getTime()));
			      curr.add(Calendar.MONTH, 1);
			    }

		    return result;
	}

	/**@Description   计算与当前时间差值
	 * @param ltime 传入时间戳
	 * @return long 返回与当前时间戳差值
	 * @Author zf
	 * @Date 2020-12-02 13:35
	 * @Update
	 **/
	public static long computerDiff(long ltime){
		return System.currentTimeMillis() - ltime;
	}
}
