package com.jiakun.xplatform.framework.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.util.JavaScriptUtils;

import com.alibaba.common.lang.StringEscapeUtil;
import com.alibaba.common.lang.StringUtil;

/**
 * 
 * @author jacky.chenb
 * 
 */
public final class EncodeUtil {

	public static final String APP_ENCODING = "GBK";

	public static final String DB_ENCODING = "GBK";

	/** Ԥ����ͼƬ��ʽ. */
	static final String[] SUFFIXS = { ".gif", ".jpg", ".jpeg", ".png", ".ico" };

	private EncodeUtil() {

	}

	/**
	 * �����ݿ���ַ��������ַ�Ĵ洢�ֽ���
	 */
	public static int getDBLength(String str) {
		if (str == null) {
			return 0;
		}

		try {
			return str.getBytes(DB_ENCODING).length;
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException(e);
		}
	}

	/**
	 * ���Ӧ�õ��ַ��ַ�ת�����ֽ�����
	 */
	public static byte[] toBytes(String str) {
		if (str != null) {
			try {
				return str.getBytes(APP_ENCODING);
			} catch (UnsupportedEncodingException e) {
				throw new IllegalStateException(e);
			}
		} else {
			return new byte[0];
		}
	}

	/**
	 * ���Ӧ�õ��ַ��ֽ�����ת�����ַ�
	 */
	public static String toString(byte[] bytes) {
		if (bytes != null) {
			try {
				return new String(bytes, APP_ENCODING);
			} catch (UnsupportedEncodingException e) {
				throw new IllegalStateException(e);
			}
		} else {
			return null;
		}
	}

	/**
	 * ���Ӧ�õ��ַ���ַ����URL����
	 */
	public static String url(Object obj) {
		if (obj == null) {
			return "";
		}

		try {
			return URLEncoder.encode(obj.toString(), APP_ENCODING);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException(e);
		}
	}

	/**
	 * ���Ӧ�õ��ַ���ַ�URL����ת������
	 * 
	 * @param obj
	 * @return
	 */
	public static String decode(Object obj) {
		if (obj == null) {
			return "";
		}

		try {
			return URLDecoder.decode(obj.toString(), APP_ENCODING);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException(e);
		}
	}

	/**
	 * �Ե���˫����е������ַ����Ԥ���?��ֹjavascript�������?
	 * ������ű�����ʱ������Ԥ���?���磺 <script> var s =
	 * "<%= EncodeUtil.script(value) %>"; </script>
	 */
	public static String javascript(Object obj) {
		if (obj == null) {
			return "";
		}

		return JavaScriptUtils.javaScriptEscape(obj.toString());
	}

	/**
	 * �������ַ�<>"&�Ƚ���Ԥ���?��ֹHTML�������?
	 * �����input��area��Ԫ�ص�value����ʱ������Ԥ���?���磺 <input name="foo"
	 * value="<%= EncodeUtil.html(value) %>">
	 */
	public static String html(Object obj) {
		if (obj == null) {
			return "";
		}

		return StringEscapeUtil.escapeHtml(obj.toString());
	}

	/**
	 * �������ַ�<>"&�Ƚ���Ԥ���?��ֹXML�������? �����XML������ֵʱ������Ԥ���?���磺
	 * <node attribute="<%= EncodeUtil.xml(value) %>" />
	 */
	public static String xml(Object obj) {
		if (obj == null) {
			return "";
		}

		return StringEscapeUtil.escapeXml(obj.toString());
	}

	/**
	 * ���û��ı���������ʾϰ����ʾ��html��:brԪ�ء��س��Ϳո������⴦��,����ת����������磺
	 * "11\r\n\r\n22<br>
	 * <br>
	 * ss<br />
	 * d t"--->"11<br/>
	 * <br/>
	 * 22<br/>
	 * <br/>
	 * ss<br/>
	 * d&nbsp;&nbsp;&nbsp;t"
	 * 
	 * @added by hao.lvh
	 */
	public static String htmlText(Object obj) {
		if (obj == null) {
			return "";
		}

		String objStr = obj.toString();
		objStr = StringEscapeUtil.escapeHtml(objStr);
		objStr = objStr.replaceAll("\\n|\\r\\n", "<br/>");
		objStr = objStr.replaceAll(" ", "&nbsp;");

		return objStr;
	}

	/**
	 * ��ȡ���ַ�
	 * 
	 * @param str
	 *            ָ�����ַ�
	 * @param iLen
	 *            ��ȡ����
	 * @return substr
	 */
	public static String substr(String str, int iLen) {
		return substr(str, iLen, false);
	}

	/**
	 * 1. ��ȡ���ַ� 2. ���ַ� + ... + title��ʾ
	 * 
	 * @param str
	 *            ָ�����ַ�
	 * @param iLen
	 *            ��ȡ����
	 * @param showTitle
	 *            �Ƿ����title��ʾ��true����ʾ��false������ʾ��Ĭ��false
	 * @return substr
	 */
	public static String substr(String str, int iLen, boolean showTitle) {
		if (str == null || "".equals(str)) {
			return "";
		}

		try {
			str = html(str);

			StringBuilder rtStr = new StringBuilder();
			String tmp = null;
			int cLen = 0;

			for (int i = 0; i < iLen; i++) {
				tmp = str.substring(i, i + 1);
				rtStr.append(tmp);

				cLen += (tmp.getBytes(APP_ENCODING).length == 2 ? 2 : 1);

				if (cLen >= iLen) {
					break;
				}
			}

			if (iLen < getByteLen(str) && showTitle) {
				return "<span title=\"" + str + "\">" + rtStr.toString() + "...</span>";
			}

			return rtStr.toString();

		} catch (Exception e) {
			return str;
		}
	}

	/**
	 * ��ȡ�ַ���ֽڳ��ȣ�����Ϊ2���ֽڣ���ĸ����Ϊ1���ֽ�.
	 * 
	 * @param s
	 * @return
	 */
	private static int getByteLen(String s) {
		if (StringUtil.isBlank(s)) {
			return 0;
		}

		int len = 0;

		try {
			for (int i = 0; i < s.length(); i++) {
				String tmp = s.substring(i, i + 1);
				len += (tmp.getBytes(APP_ENCODING).length == 2 ? 2 : 1);
			}
		} catch (Exception e) {
			return len;
		}

		return len;
	}

	public static Map<String, String> getUserIdAppInstanceId(String sipUsername) {
		if (StringUtil.isBlank(sipUsername)) {
			return null;
		}
		String[] attrs = sipUsername.split("@");
		if (attrs.length != 2) {
			return null;
		}
		Map<String, String> result = new HashMap<String, String>();
		result.put("userId", attrs[0]);
		result.put("appInstanceId", attrs[1]);
		return result;
	}

	/** У���ַ��Ƿ��ǺϷ���ͼƬ��ַ. */
	public static boolean isIconUrl(String iconUrl) {
		if (StringUtil.isBlank(iconUrl)) {
			return false;
		}

		for (String suf : SUFFIXS) {
			if (iconUrl.toLowerCase().endsWith(suf)) {
				return true;
			}
		}
		return false;
	}

}
