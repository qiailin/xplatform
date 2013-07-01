package com.jiakun.xplatform.framework.util;

import java.util.UUID;

/**
 * Ψһ��ʶ������(20λ)
 * 
 * @author ��ս
 */
public final class OidUtil {
	/* ������� */
	private static char[] x36s = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

	/* ������ */
	private static short len = 20;

	private OidUtil() {

	}

	public static String newId() {
		char[] chs = new char[len];

		/**
		 * ���ǰ8λ����ַ�(��ϵͳʱ��Ϊ����, ��36λ����+Ӣ����ĸΪ�������)
		 */
		// 1999-9-9
		long v = (System.currentTimeMillis() - 936748800000L) >> 1;

		for (int i = 7; i > 0; i--) {
			chs[i] = x36s[(int) (v % 36)];
			v = v / 36;
		}
		chs[0] = x36s[(int) (v % 26) + 10];

		/**
		 * ��ɺ�12λ����ַ�(��UUIDΪ����, ��36λ����+Ӣ����ĸΪ�������)
		 */
		UUID u = UUID.randomUUID();
		v = u.getLeastSignificantBits() ^ u.getMostSignificantBits();
		if (v < 0) {
			v = -v;
		}

		for (int i = 8; i < len; i++) {
			chs[i] = x36s[(int) (v % 36)];
			v = v / 36;
		}

		return new String(chs);
	}

}
