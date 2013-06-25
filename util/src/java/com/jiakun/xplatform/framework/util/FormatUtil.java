package com.jiakun.xplatform.framework.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import com.alibaba.common.lang.StringUtil;

public class FormatUtil {

	public static boolean isEmpty(String str) {
		if ((str == null) || (str.length() <= 0)) {
			return true;
		}

		return false;
	}

	public static String formatDecimal(Double decimal, String pattern) {
		if (decimal == null) {
			return null;
		}
		DecimalFormat df = new DecimalFormat();
		if (isEmpty(pattern)) {
			df.applyPattern("#,###.##");
		} else {
			df.applyPattern(pattern);
		}
		return df.format(decimal);

	}

	public static String formatDecimal(Float decimal, String pattern) {
		if (decimal == null) {
			return null;
		}
		DecimalFormat df = new DecimalFormat();
		if (isEmpty(pattern)) {
			df.applyPattern("#,###.##");
		} else {
			df.applyPattern(pattern);
		}
		return df.format(decimal);

	}

	public static String formatDecimal(Double decimal) {
		return formatDecimal(decimal, "");
	}

	public static String formatDecimal(Float decimal) {
		return formatDecimal(decimal, "");
	}

	public static String formatDecimal(BigDecimal decimal, String pattern) {
		if (decimal == null) {
			return null;
		}
		DecimalFormat df = new DecimalFormat();
		if (isEmpty(pattern)) {
			df.applyPattern("#,###.##");
		} else {
			df.applyPattern(pattern);
		}
		return df.format(decimal);
	}

	/**
	 * �����ݵõ���ݽ���ַ��ʽ�����Ϊ����Ȼ���ظ�ֵ��
	 * 
	 * @param amount
	 * @return
	 */
	public static String getAmountFormat(BigDecimal amount) {
		if (amount == null || amount.signum() == 0)
			return "0.00";
		return new DecimalFormat("###,##0.00").format(amount);
	}

	/**
	 * ����ַ�õ���ݽ���ַ��ʽ�����Ϊ����Ȼ���ظ�ֵ��
	 * 
	 * @param amount
	 * @return
	 */
	public static String getAmountFormat(String amount) {
		return getAmountFormat(new BigDecimal(amount));
	}

	/**
	 * �������ݵõ�����ַ��ʽ�����Ϊ��,���أ�.������
	 * 
	 * @param amount
	 * @return��if(amount==null||amount<=0):0.00;else 220.00
	 */
	public static String getAmountFormatStr(BigDecimal amount) {
		if (amount == null || amount.signum() != 1)
			return "0.00";
		return new DecimalFormat("###,##0.00").format(amount);
	}

	/**
	 * �ַ��Сдת��
	 */
	public static String lowerToUpper(String s) {
		if (StringUtil.isEmpty(s)) {
			return null;
		}

		StringBuffer t = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			if (s.substring(i, i + 1).equals(
					s.substring(i, i + 1).toLowerCase())) {
				t.append(s.substring(i, i + 1).toUpperCase());
			} else {
				t.append(s.substring(i, i + 1).toUpperCase());
			}
		}
		return t.toString();
	}
}