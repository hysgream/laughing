package com.laughing2b.util.general;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;
/**
* @ClassName: DateUtil 
* @Description:时间工具类
* @author lifei.pan
* @email plfnet@163.com
* @date 2015年8月7日 下午4:58:50 
*
 */
public class DateUtil {
	/**
	 * 默认的日期格式
	 */
	public static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 取得当前日期
	 * 
	 * @return Date 当前日期
	 */
	public static Date getCurrentDate() {
		return new Date(System.currentTimeMillis());
	}
	/**
	 * 返回当前日期对应的默认格式的字符串
	 * 
	 * @return String 当前日期对应的字符串
	 */
	public static String getCurrentStringDate() {
		return convertDate2String(getCurrentDate(), DEFAULT_DATE_FORMAT);
	}
	/**
	 * 返回当前日期对应的指定格式的字符串
	 * 
	 * @param dateFormat
	 *            - 日期格式
	 * @return String 当前日期对应的字符串
	 */
	public static String getCurrentStringDate(String dateFormat) {
		return convertDate2String(getCurrentDate(), dateFormat);
	}
	/**
	 * 将日期转换成指定格式的字符串
	 * 
	 * @param date
	 *            - 要转换的日期
	 * @param dateFormat
	 *            - 日期格式
	 * @return String 日期对应的字符串
	 */
	public static String convertDate2String(Date date, String dateFormat) {
		SimpleDateFormat sdf = null;
		if (dateFormat != null && !dateFormat.equals("")) {
			try {
				sdf = new SimpleDateFormat(dateFormat);
			} catch (Exception e) {
				e.printStackTrace();
				sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
			}
		} else {
			sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		}
		return sdf.format(date);
	}
	/**
	 * 将日期转换成默认格式的字符串
	 * 
	 * @param date
	 *            - 要转换的日期
	 * @param dateFormat
	 *            - 日期格式
	 * @return String 日期对应的字符串
	 */
	public static String convertDate2String(Date date) {
		SimpleDateFormat sdf = null;
		sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		return sdf.format(date);
	}
	/**
	 * 将字符串转换成日期
	 * 
	 * @param stringDate
	 *            - 要转换的字符串格式的日期
	 * @return Date 字符串对应的日期
	 */
	public static Date convertString2Date(String stringDate) {
		return convertString2Date(stringDate, DEFAULT_DATE_FORMAT);
	}
	/**
	 * 将字符串转换成指定格式日期
	 * 
	 * @param stringDate
	 *            - 要转换的字符串格式的日期
	 * @param dateFormat
	 *            - 要转换的字符串对应的日期格式
	 * @return Date 字符串对应的日期
	 */
	public static Date convertString2Date(String stringDate, String dateFormat) {
		SimpleDateFormat sdf = null;
		if (dateFormat != null && !dateFormat.equals("")) {
			try {
				sdf = new SimpleDateFormat(dateFormat);
			} catch (Exception e) {
				e.printStackTrace();
				sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
			}
		} else {
			sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		}
		try {
			return sdf.parse(stringDate);
		} catch (ParseException pe) {
			pe.printStackTrace();
			return new Date(System.currentTimeMillis());
		}
	}
	/**
	 * 将一种格式的日期字符串转换成默认格式的日期字符串
	 * 
	 * @param oldDate
	 *            - 要格式化的日期字符串
	 * @param oldFormat
	 *            - 要格式化的日期的格式
	 * @return String 格式化后的日期字符串
	 */
	public static String formatStringDate(String oldStringDate, String oldFormat) {
		return convertDate2String(convertString2Date(oldStringDate, oldFormat),
				DEFAULT_DATE_FORMAT);
	}
	/**
	 * 将一种格式的日期字符串转换成另一种格式的日期字符串
	 * 
	 * @param oldDate
	 *            - 要格式化的日期字符串
	 * @param oldFormat
	 *            - 要格式化的日期的格式
	 * @param newFormat
	 *            - 格式化后的日期的格式
	 * @return String 格式化后的日期字符串
	 */
	public static String formatStringDate(String oldStringDate,
			String oldFormat, String newFormat) {
		return convertDate2String(convertString2Date(oldStringDate, oldFormat),
				newFormat);
	}
	/**
	 * 根据年份和月份判断该月有几天
	 * 
	 * @param year
	 *            - 年份
	 * @param month
	 *            - 月份
	 * @return int
	 */
	public static int days(int year, int month) {
		int total = 30;
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			total = 31;
			break;
		case 2:
			if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
				total = 29;
			else
				total = 28;
			break;
		default:
			break;
		}
		return total;
	}
	/**
	 * 给定一个日期,返回是一周中的第几天 星期日为每周的第一天,星期六为每周的最后一天
	 * */
	public static int dayOfWeek(Date date) {
		// 首先定义一个calendar，必须使用getInstance()进行实例化
		Calendar aCalendar = Calendar.getInstance();
		// 里面野可以直接插入date类型
		aCalendar.setTime(date);
		// 计算此日期是一周中的哪一天
		int x = aCalendar.get(Calendar.DAY_OF_WEEK);
		return x;
	}
	/**
	 * 得到当前日期的前后日期 +为后 -为前
	 * 
	 * @param day_i
	 * @return
	 */
	public static final String getBefDateString(String currentDate, int day_i,
			String format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			Date date = sdf.parse(currentDate);
			Calendar day = Calendar.getInstance();
			day.setTime(date);
			day.add(Calendar.DATE, day_i);
			return sdf.format(day.getTime());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 得到一个月的天数
	 */
	@SuppressWarnings("static-access")
	public static int getMonthDays(String dt) {
		try {
			if (dt == null) {
				throw new NullPointerException("日期参数为null");
			} else {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				Date date = sdf.parse(dt);
				Calendar cld = Calendar.getInstance();
				cld.setTime(date);
				return cld.getActualMaximum(cld.DAY_OF_MONTH);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	/**
	 * 得到一个月的天数
	 */
	@SuppressWarnings("static-access")
	public static int getMonthDays(String dt, String format) {
		try {
			if (dt == null) {
				throw new NullPointerException("日期参数为null");
			} else {
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				Date date = sdf.parse(dt);
				Calendar cld = Calendar.getInstance();
				cld.setTime(date);
				return cld.getActualMaximum(cld.DAY_OF_MONTH);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	private static final char[] zeroArray = "0000000000".toCharArray();
	/**
	 * 得到指定长度格式的字符串 ‘000000123456789’
	 * 
	 * @param string
	 * @param length
	 * @return
	 */
	public static final String zeroPadString(String string, int length) {
		if (string == null || string.length() > length) {
			return string;
		}
		StringBuilder buf = new StringBuilder(length);
		buf.append(zeroArray, 0, length - string.length()).append(string);
		return buf.toString();
	}
	/** 得到这个月的第一天 **/
	public static Date getMonthFirstDay(Date date) {
		String strdate = convertDate2String(date);
		strdate = strdate.substring(0, 8) + "01";
		return convertString2Date(strdate);
	}
	/** 秒数转化为小时格式 HH:MM:SS **/
	public static String convertSecToHour(int sec) {
		String time = "";
		int hour = 0;
		int minute = 0;
		int second = 0;
		hour = sec / 3600 > 0 ? sec / 3600 : 0;
		minute = (sec - hour * 3600) / 60 > 0 ? (sec - hour * 3600) / 60 : 0;
		second = sec - hour * 3600 - minute * 60 > 0 ? sec - hour * 3600
				- minute * 60 : 0;
		String shour = String.valueOf(hour).length() < 2 ? "0"
				+ String.valueOf(hour) : String.valueOf(hour);
		String sminute = String.valueOf(minute).length() < 2 ? "0"
				+ String.valueOf(minute) : String.valueOf(minute);
		String ssecond = String.valueOf(second).length() < 2 ? "0"
				+ String.valueOf(second) : String.valueOf(second);
		time = shour + ":" + sminute + ":" + ssecond;
		return time;
	}
	/**
	 * Formats a Date as a fifteen character long String made up of the Date's
	 * padded millisecond value.
	 */
	public static final String dateToMillis(Date date) {
		return zeroPadString(Long.toString(date.getTime()), 15);
	}
	public static final Date millisToDate(String stime) {
		long time = Long.parseLong(stime);
		return new Date(time);
	}
	SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sFullFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final String DATE_SEPARATOR = "-/";
	/** 作日期分析之用 */
	static StringTokenizer sToken;
	/** 将日期变为字符串格式 * */
	public String format(GregorianCalendar pCal) {
		return sDateFormat.format(pCal.getTime());
	}
	public String format(Date pDate) {
		return sDateFormat.format(pDate);
	}
	public String fullFormat(Date pDate) {
		return sFullFormat.format(pDate);
	}
	/** 将字符串格式的日期转换为Calender* */
	public static GregorianCalendar parse2Cal(String pDateStr) {
		sToken = new StringTokenizer(pDateStr, DATE_SEPARATOR);
		int vYear = Integer.parseInt(sToken.nextToken());
		// GregorianCalendar的月份是从0开始算起的，变态！！
		int vMonth = Integer.parseInt(sToken.nextToken()) - 1;
		int vDayOfMonth = Integer.parseInt(sToken.nextToken());
		return new GregorianCalendar(vYear, vMonth, vDayOfMonth);
	}
	/** 将字符串类型的日期(yyyy-MM-dd)转换成Date* */
	public Date parse2Date(String pDate) {
		try {
			return sDateFormat.parse(pDate);
		} catch (ParseException ex) {
			return null;
		}
	}
	/** 给定两个时间相差的月数,String版* */
	public static int monthsBetween(String pFormerStr, String pLatterStr) {
		GregorianCalendar vFormer = parse2Cal(pFormerStr);
		GregorianCalendar vLatter = parse2Cal(pLatterStr);
		return monthsBetween(vFormer, vLatter);
	}
	@SuppressWarnings("static-access")
	public static int monthsBetween(GregorianCalendar pFormer,
			GregorianCalendar pLatter) {
		GregorianCalendar vFormer = pFormer, vLatter = pLatter;
		boolean vPositive = true;
		if (pFormer.before(pLatter)) {
			vFormer = pFormer;
			vLatter = pLatter;
		} else {
			vFormer = pLatter;
			vLatter = pFormer;
			vPositive = false;
		}
		int vCounter = 0;
		while (vFormer.get(vFormer.YEAR) != vLatter.get(vLatter.YEAR)
				|| vFormer.get(vFormer.MONTH) != vLatter.get(vLatter.MONTH)) {
			vFormer.add(Calendar.MONTH, 1);
			vCounter++;
		}
		if (vPositive)
			return vCounter;
		else
			return -vCounter;
	}
	/**
	 * @return
	 */
	public static String getMMDDHH24MI() {
		return date2String(new Date(), "MMddHHmm");
	}
	/**
	 * 将date转字符串，默认格为yyyyMMddHHmmss
	 * 
	 * @param date
	 * @return
	 */
	public static String date2String(Date date) {
		return date2String(date, "yyyyMMddHHmmss");
	}
	/**
	 * 将date转字符串，格式按JAVA规则
	 * 
	 * @param date
	 * @param formatStr
	 *            ,如：yyyyMMddHHmmss
	 * @return
	 */
	public static String date2String(Date date, String formatStr) {
		SimpleDateFormat df = new SimpleDateFormat(formatStr);
		String dateStr = df.format(date);
		return dateStr;
	}
	/**
	 * 获取按月分表的表后缀
	 * @return
	 */
	public static String getTableSuffix() {
		return convertDate2String(new Date(), "yyyyMM");
	}
}
