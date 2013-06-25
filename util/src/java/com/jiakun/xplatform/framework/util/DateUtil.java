package com.jiakun.xplatform.framework.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.alibaba.common.lang.StringUtil;

public class DateUtil {

	private static final long MILLISECONDS_A_DAY = 24 * 3600 * 1000;

	private static final long MILLISECONDS_A_HOUR = 3600 * 1000;

	private static final long MILLISECONDS_A_SECOND = 1000;

	public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

	private static Logger logger = Logger.getLogger(DateUtil.class);

	private static final String DEFAULT_DATEFULLTIME_FORMAT = "yyyyMMddHHmmss";

	public static final String DEFAULT_DATEFULLDATE_FORMAT = "yyyyMMdd";

	public static final String DEFAULT_YEAR_FORMAT = "yyyy";
	public static final String DEFAULT_MONTH_FORMAT = "MM";

	/** ��֤�����ַ���Ч���ڷ�Χ1900-1-1��2099-12-31. */
	private static final Pattern pattern = Pattern
		.compile("(?:(?:19|20)\\d{2})-(?:0?[1-9]|1[0-2])-(?:0?[1-9]|[12][0-9]|3[01])");

	/**
	 * ��ǰʱ�����days��
	 */
	public static Date addDays(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}

	public static Date addMinutes(Date date, int minutes) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, minutes);
		return cal.getTime();
	}

	public static Date addSeconds(Date date, int seconds) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.SECOND, seconds);
		return cal.getTime();
	}

	/**
	 * ��ǰʱ�����days��
	 */
	public static Date addMonths(Date date, int months) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, months);
		return cal.getTime();
	}

	/**
	 * ��ȡ��ǰ�µ��������
	 * 
	 * @return
	 */
	public static Date getMaxDate() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
		return cal.getTime();
	}

	/**
	 * ��ȡ��ǰ���
	 * 
	 * @return
	 */
	public static int getYear() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.YEAR);
	}

	/**
	 * ��ȡ��ǰ��
	 * 
	 * @return
	 */
	public static int getMonth() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.MONTH) + 1;
	}

	/**
	 * ��ȡ��ǰ�µ���С����
	 * 
	 * @return
	 */
	public static Date getMinDate() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DATE));
		return cal.getTime();
	}

	/**
	 * ��ȡָ���µ���Сʱ��
	 * 
	 * @param date
	 * @return
	 */
	public static Date getMinDateByMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DATE));
		return cal.getTime();
	}

	/**
	 * ��ȡָ���µ����ʱ��
	 * 
	 * @param date
	 * @return
	 */
	public static Date getMaxDateByMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
		return cal.getTime();
	}

	/**
	 * ȡ��ĳ�µĵ����һ��
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getLastDayOfLastMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);// ��
		cal.set(Calendar.MONTH, month - 1);// �£���ΪCalendar������Ǵ�0��ʼ������Ҫ��1
		cal.set(Calendar.DATE, 1);// �գ���Ϊһ��
		// cal.add(Calendar.MONTH, 1);// �·ݼ�һ���õ��¸��µ�һ��
		cal.add(Calendar.DATE, -1);// ��һ���¼�һΪ�������һ��
		return cal.getTime();// �����ĩ�Ǽ���
	}

	/**
	 * ��ǰʱ�����years��
	 */
	public static Date addYears(Date date, int years) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, years);
		return cal.getTime();
	}

	/**
	 * ���ָ����ʽ������ʱ���ַ�
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String datetime(String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(new Date());
	}

	/**
	 * ���ָ����ʽ������ʱ���ַ�
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String datetime(Date date, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(date);
	}

	/**
	 * ���ָ����ʽ������ʱ���ַ�
	 * 
	 * @param �����ַ�
	 * @param format
	 * @return
	 */
	public static String datetime(String date, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(date);
	}

	/**
	 * ���ָ����ʽ�ĵ�ǰ�����ַ�
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String date(Date date, String format) {
		if (date == null) {
			return "";
		}

		return (new SimpleDateFormat(format)).format(date);
	}

	/**
	 * ���ָ����ʽ�ĵ�ǰ�����ַ�
	 * 
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public static String date(String dateStr, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(dateStr);
	}

	/**
	 * ���"yyyy-MM-dd"��ʽ�ĵ�ǰ�����ַ�
	 * 
	 * @param date
	 * @return
	 */
	public static String getNowDateStr() {
		return getNowDatetimeStr(DEFAULT_DATE_FORMAT);
	}

	/**
	 * ���"yyyy-MM-dd HH:mm:ss"��ʽ�ĵ�ǰ����ʱ���ַ�
	 * 
	 * @param date
	 * @return
	 */
	public static String getNowDatetimeStr() {
		return getNowDatetimeStr(DEFAULT_DATETIME_FORMAT);
	}

	/**
	 * ���"yyyyMMddHHmmss"��ʽ�ĵ�ǰ����ʱ���ַ�
	 * 
	 * @param date
	 * @return
	 */
	public static String getNowDateminStr() {
		return getNowDatetimeStr(DEFAULT_DATEFULLTIME_FORMAT);
	}

	/**
	 * ��õ�ǰ����ʱ���ַ�
	 * 
	 * @param format
	 *            ���ڸ�ʽ
	 * @return ����ʱ���ַ�
	 */
	public static String getNowDatetimeStr(String format) {
		Calendar cal = Calendar.getInstance();
		return datetime(cal.getTime(), format);
	}

	/**
	 * ֻȡ��ǰʱ������ڲ��֣�Сʱ���֡�����ֶι���
	 */
	public static Date dateOnly(Date date) {
		return new Date(date.getTime() / MILLISECONDS_A_DAY);
	}

	/**
	 * ֻȡ��ǰʱ������ڲ��֣�Сʱ���֡�����ֶι���
	 */
	public static Date dateOnlyExt(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}

	/**
	 * ֻȡ��ǰʱ������ڲ��֣�Сʱ���֡�����ֶι���
	 */
	public static Date dateMinTime(Date date) {
		return dateOnlyExt(date);
	}

	/**
	 * ������2007-2-2 7:1:8��ʱ�䴮��Ϊ��׼��2007-02-02 07:01:08
	 * 
	 * @param dateTimeStr
	 *            δУ������ֵ
	 * @return ���ڶ���
	 */
	public static String getStandDateTimeStr(String dateTimeStr) {
		if (dateTimeStr == null || "".equals(dateTimeStr)) {
			return "";
		}

		dateTimeStr = dateTimeStr.replaceAll("\\s+", "|");
		String[] a = dateTimeStr.split("\\|");
		List<String> list = Arrays.asList(a);
		String datetime = "";
		int count = 1;
		for (int i = 0; i < list.size(); i++) {
			String temp = (String) list.get(i);
			StringTokenizer st;
			if (i == 0)
				st = new StringTokenizer(temp, "-");
			else
				st = new StringTokenizer(temp, ":");
			int k = st.countTokens();
			for (int j = 0; j < k; j++) {
				String sttemp = (String) st.nextElement();
				if (count == 1) {
					datetime = sttemp;
				} else {
					if ((sttemp.equals("0")) || (sttemp.equals("00")))
						sttemp = "0";
					else if ((Integer.valueOf(sttemp).intValue()) < 10)
						sttemp = sttemp.replaceAll("0", "");
					if (count < 4) {
						if ((Integer.valueOf(sttemp).intValue()) < 10)
							datetime = datetime + "-0" + sttemp;
						else
							datetime = datetime + "-" + sttemp;
					}
					if (count == 4) {
						if ((Integer.valueOf(sttemp).intValue()) < 10)
							datetime = datetime + " 0" + sttemp;
						else
							datetime = datetime + " " + sttemp;
					}
					if (count > 4) {
						if ((Integer.valueOf(sttemp).intValue()) < 10)
							datetime = datetime + ":0" + sttemp;
						else
							datetime = datetime + ":" + sttemp;
					}
				}
				count++;
			}
		}

		try {
			getDateFromStr(datetime);
			return datetime;
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * �ѱ�׼��2007-02-02 07:01:08��ʽת�������ڶ���
	 * 
	 * @param datetime
	 *            ����,��׼��2007-02-02 07:01:08��ʽ
	 * @return ���ڶ���
	 */
	@SuppressWarnings("deprecation")
	public static Date getDateFromStr(String datetime) {
		if (datetime == null || "".equals(datetime)) {
			return new Date();
		}

		String nyr = datetime.trim();

		if (nyr.indexOf(" ") > 0) {
			nyr = nyr.substring(0, nyr.indexOf(" "));
		} else {
			nyr = nyr.substring(0, nyr.length());
		}

		StringTokenizer st = new StringTokenizer(nyr, "-");
		Date date = new Date();
		String temp = "";
		int count = st.countTokens();
		for (int i = 0; i < count; i++) {
			temp = (String) st.nextElement();
			// if(!(temp.equals("0")))
			// temp.replaceAll("0", "");
			if (i == 0)
				date.setYear(Integer.parseInt(temp) - 1900);
			if (i == 1)
				date.setMonth(Integer.parseInt(temp) - 1);
			if (i == 2)
				date.setDate(Integer.parseInt(temp));
		}

		if (datetime.length() > 10) {
			String sfm = datetime.substring(11, 19);
			StringTokenizer st2 = new StringTokenizer(sfm, ":");
			count = st2.countTokens();
			for (int i = 0; i < count; i++) {
				temp = (String) st2.nextElement();
				// if(!(temp.equals("0")))
				// temp.replaceAll("0", "");
				if (i == 0)
					date.setHours(Integer.parseInt(temp));
				if (i == 1)
					date.setMinutes(Integer.parseInt(temp));
				if (i == 2)
					date.setSeconds(Integer.parseInt(temp));
			}
		}
		return date;
	}

	/**
	 * �������������������
	 * 
	 * @param startDate
	 *            ��ʼ���ڶ���
	 * @param endDate
	 *            ��ֹ���ڶ���
	 * @return
	 */
	public static int getQuot(Date startDate, Date endDate) {
		long quot = 0;
		quot = endDate.getTime() - startDate.getTime();
		quot = quot / MILLISECONDS_A_DAY;
		return (int) quot;
	}

	/**
	 * �������������������
	 * 
	 * @param startDateStr
	 *            ��ʼ�����ַ�
	 * @param endDateStr
	 *            ��ֹ���ַ�
	 * @param format
	 *            ʱ���ʽ
	 * @return
	 */
	public static int getQuot(String startDateStr, String endDateStr, String format) {
		long quot = 0;
		format = (format != null && format.length() > 0) ? format : DEFAULT_DATE_FORMAT;
		SimpleDateFormat ft = new SimpleDateFormat(format);
		try {
			Date date1 = ft.parse(endDateStr);
			Date date2 = ft.parse(startDateStr);
			quot = date1.getTime() - date2.getTime();
			quot = quot / MILLISECONDS_A_DAY;
		} catch (ParseException e) {
			logger.error("��ȡ����������������쳣: ", e);
		}
		return (int) quot;
	}

	/**
	 * ���������ַ�"yyyy-MM-dd HH:mm" ��ʽ��
	 * 
	 * @author chun.fengch
	 * 
	 * @param date
	 * @return
	 */
	public static final String getDateTime(Date date) {
		if (date == null)
			return "";
		DateFormat ymdhmsFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return ymdhmsFormat.format(date);
	}

	/**
	 * �����ʽ����ʱ����ַ�
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static final String getDateTime(Date date, String pattern) {
		if (date == null)
			return "";
		DateFormat ymdhmsFormat = new SimpleDateFormat(pattern);
		return ymdhmsFormat.format(date);
	}

	/**
	 * ����������������Сʱ
	 * 
	 * @param startDateStr
	 * @param endDateStr
	 * @param format
	 * @return
	 */
	public static int getQuotHours(Date startDate, Date endDate) {
		long quot = 0;
		quot = endDate.getTime() - startDate.getTime();
		quot = quot / MILLISECONDS_A_HOUR;
		return (int) quot;
	}

	/**
	 * ������������������
	 * 
	 * @param startDateStr
	 * @param endDateStr
	 * @param format
	 * @return
	 */
	public static int getQuotSeconds(Date startDate, Date endDate) {
		long quot = 0;
		quot = endDate.getTime() - startDate.getTime();
		quot = quot / MILLISECONDS_A_SECOND;
		return (int) quot;
	}

	/**
	 * ���ַ�ת��Ϊ������ ��ʽΪ: yyyy-MM-dd
	 * 
	 * @param dateTime
	 * @return
	 */
	public static Date getDateTime(String dateTime) {
		return getDateTime(dateTime, "yyyy-MM-dd");
	}

	public static Date getDateTime(String dateTime, String formatPattern) {
		try {
			if (StringUtil.isNotBlank(dateTime) && StringUtil.isNotBlank(formatPattern)) {
				SimpleDateFormat format = new SimpleDateFormat(formatPattern);
				return format.parse(dateTime);
			}
		} catch (ParseException e) {
			logger.error(e);
		}

		return null;
	}

	/**
	 * ���ַ�ת��Ϊ������ ��ʽΪ: yyyy-MM-dd
	 * 
	 * @param dateTime
	 * @return
	 */
	public static Date getDateDetailTime(String dateTime) {
		try {
			if (StringUtil.isNotBlank(dateTime)) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				return format.parse(dateTime);
			}
		} catch (ParseException e) {
			logger.error(e);
		}

		return null;
	}

	/**
	 * ȡ��ǰ��ʱ�������ҳ���ϱ�֤URLΨһ����ֹ����
	 * 
	 * @return
	 */
	public static long getDtSeq() {
		return System.currentTimeMillis();
	}

	/**
	 * �ж��Ƿ��ڲ������ڵ����ֵ����Сֵ֮��
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isBetween(Date min, Date compare) {
		Boolean ret = false;
		Date minDate = DateUtil.dateOnlyExt(min);
		Date maxDate = DateUtil.dateOnlyExt(DateUtil.addDays(min, 1));
		if (compare.after(minDate) && compare.before(maxDate)) {
			ret = true;
		}
		return ret;
	}

	public static Date getDate(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day);
		return cal.getTime();
	}

	/**
	 * ��ȡ����/����/�����ȵ��³�����ĩ.
	 * 
	 * @param monthRange
	 *            ȡֵ��Χ{cm:���£�pm:���£�sm:������}
	 * @return Map{firstDay:yyyy-MM-dd, lastDay:yyyy-MM-dd}
	 */
	public static Map<String, String> getFLDayMap(String monthRange) {
		return getFLDayMap(monthRange, DEFAULT_DATE_FORMAT);
	}

	/**
	 * ��ȡ����/����/�����ȵ��³�����ĩ.
	 * 
	 * @param monthRange
	 *            ȡֵ��Χ{cm:���£�pm:���£�sm:������}
	 * @param pattern
	 * @return Map{firstDay:yyyy-MM-dd, lastDay:yyyy-MM-dd}
	 */
	public static Map<String, String> getFLDayMap(String monthRange, String pattern) {
		Map<String, String> rs = new LinkedHashMap<String, String>();

		SimpleDateFormat df = new SimpleDateFormat(pattern);
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());

		if (StringUtil.isBlank(monthRange)) {
			monthRange = "cm";
		}

		if (!"sm".equals(monthRange)) {
			if ("pm".equals(monthRange)) {
				calendar.add(Calendar.MONTH, -1);
			}

			calendar.set(Calendar.DAY_OF_MONTH, 1);
			rs.put("firstDay", df.format(calendar.getTime()));

			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			rs.put("lastDay", df.format(calendar.getTime()));

			return rs;
		}

		/*
		 * �����ȵ��³�����ĩ
		 */
		int[][] seasons = { { 2, 4 }, // ����
			{ 5, 7 }, // �ļ�
			{ 8, 10 }, // �＾
			{ 11, 1 } // ����
			};
		int cm = calendar.get(Calendar.MONTH) + 1;

		for (int[] im : seasons) {
			if (cm >= im[0] && cm <= im[1]) {
				calendar.set(Calendar.MONTH, im[0] - 1);
				calendar.set(Calendar.DAY_OF_MONTH, 1);
				rs.put("firstDay", df.format(calendar.getTime()));

				calendar.set(Calendar.MONTH, im[1] - 1);
				calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
				rs.put("lastDay", df.format(calendar.getTime()));

				break;
			}
		}

		return rs;
	}

	/**
	 * ��ȡĳ���ڵ�����ַ�
	 * 
	 * @param date
	 * @return �ַ����͵����
	 */
	public static String getYearString(Date date) {
		return DateUtil.date(date, DEFAULT_YEAR_FORMAT);
	}

	/**
	 * ��ȡĳ���ڵ��������
	 * 
	 * @param date
	 * @return �������͵����
	 */
	public static int getYearInteger(Date date) {
		return Integer.parseInt(DateUtil.date(date, DEFAULT_YEAR_FORMAT));
	}

	/**
	 * ��ȡĳ���ڵ��·��ַ�
	 * 
	 * @param date
	 * @return
	 */
	public static String getMonthString(Date date) {
		return DateUtil.date(date, DEFAULT_MONTH_FORMAT);
	}

	/**
	 * ��ȡĳ���ڵ��·�����
	 * 
	 * @param date
	 * @return �������͵��·�
	 */
	public static int getMonthInteger(Date date) {
		return Integer.parseInt(DateUtil.date(date, DEFAULT_MONTH_FORMAT));
	}

	/**
	 * ȡ�õ�ǰ�µĵ����һ��
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getLastDayOfCurMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);// ��
		cal.set(Calendar.MONTH, month);// �£���ΪCalendar������Ǵ�0��ʼ������Ҫ��1
		cal.set(Calendar.DATE, 1);// �գ���Ϊһ��
		// cal.add(Calendar.MONTH, 1);// �·ݼ�һ���õ��¸��µ�һ��
		cal.add(Calendar.DATE, -1);// ��һ���¼�һΪ�������һ��
		return cal.getTime();// �����ĩ�Ǽ���
	}

	/**
	 * ȡ�õ�ǰ�µĵĵ�һ��
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getFirstDayOfCurMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);// ��
		cal.set(Calendar.MONTH, month - 1);// �£���ΪCalendar������Ǵ�0��ʼ������Ҫ��1
		cal.set(Calendar.DATE, 1);// �գ���Ϊһ��
		// cal.add(Calendar.MONTH, 1);// �·ݼ�һ���õ��¸��µ�һ��
		cal.add(Calendar.DATE, 0);// ��һ���¼�һΪ�������һ��
		return cal.getTime();// �����ĩ�Ǽ���
	}

	/** */
	/**
	 * ȡ��ĳ�������ܵĵ�һ��
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
		return c.getTime();
	}

	/** */
	/**
	 * ȡ��ĳ�������ܵ����һ��
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6);
		return c.getTime();
	}

	/** ��֤�����Ƿ���Ч����Ч���ڷ�Χ1900-1-1��2099-12-31. */
	public static boolean isValidDate(String ds) {
		if (StringUtil.isBlank(ds))
			return false;
		return pattern.matcher(ds).matches();
	}

	/** ��֤�����Ƿ���Ч����Ч���ڷ�Χ1900-1-1��2099-12-31. */
	public static boolean isValidDate(Date d) {
		if (d == null)
			return false;
		return pattern.matcher(date(d, DEFAULT_DATE_FORMAT)).matches();
	}

}
