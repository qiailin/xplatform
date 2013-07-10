package com.jiakun.xplatform.framework.webwork.converter;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import ognl.OgnlOps;

import org.json.JSONArray;
import org.json.JSONObject;

import com.jiakun.xplatform.framework.log.Logger4jCollection;
import com.jiakun.xplatform.framework.log.Logger4jExtend;
import com.opensymphony.webwork.util.WebWorkTypeConverter;
import com.opensymphony.xwork.util.OgnlUtil;
import com.opensymphony.xwork.util.XWorkConverter;

/**
 * �ͻ��˽��ӱ������JSON����ķ�ʽ�ύ������� �������ʵ��JSON��List<E>����֮��Ļ���.
 * 
 * @author tingjia.chentj
 * 
 */
public class Json2ListConverter extends WebWorkTypeConverter {

	// modify by xujiakun yyyy-MM-dd'T'HH:mm:ss'Z' to yyyy-MM-dd'T'HH:mm:ss
	private static final String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";

	private Logger4jExtend log = Logger4jCollection.getLogger(Json2ListConverter.class);

	/**
	 * �����е�ʵ������.
	 */
	@SuppressWarnings("rawtypes")
	private Class genericType = null;

	@SuppressWarnings("rawtypes")
	@Override
	public Object convertValue(Map context, Object target, Member member, String propertyName, Object value,
		Class toType) {
		// ��ȡ�����е�ʵ������
		if (member instanceof Method) {
			ParameterizedType type = (ParameterizedType) ((Method) member).getGenericParameterTypes()[0];
			Type actualType = type.getActualTypeArguments()[0];
			genericType = (Class) actualType;
		}
		return super.convertValue(context, target, member, propertyName, value, toType);
	}

	/**
	 * ��JSON��ʽת����List<E>.
	 * 
	 * @param context
	 * @param values
	 *            �ַ����鳤��Ϊ1��values[0]ΪJSON��ʽ��[{...},{...},...{...}]
	 * @param toClass
	 * 
	 * @see com.opensymphony.webwork.util.WebWorkTypeConverter#convertFromString(java.util.Map,
	 *      java.lang.String[], java.lang.Class)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		if (genericType == null) {
			return null;
		}
		XWorkConverter.setInstance(new XWorkConverter() {
		});
		ArrayList<Object> list = new ArrayList<Object>();
		try {
			JSONArray array = new JSONArray(values[0]);
			for (int i = 0; i < array.length(); i++) {
				Object obj = genericType.newInstance();
				Map map = toMap(array.getJSONObject(i));
				PropertyDescriptor[] props = OgnlUtil.getPropertyDescriptors(obj);
				for (PropertyDescriptor desc : props) {
					if (map.containsKey(desc.getName())) {
						Object value = null;
						// ����������
						// "yyyy-MM-dd'T'HH:mm:ss'Z'"�˸�ʽ����json2.jsת�����Date��ʽ
						if (java.util.Date.class.isAssignableFrom(desc.getPropertyType())) {
							try {
								value = new SimpleDateFormat(DATE_PATTERN).parse((String) map.get(desc.getName()));
							} catch (ParseException e) {
								log.error(e);
							}
						} else if (java.sql.Date.class.isAssignableFrom(desc.getPropertyType())) {
							try {
								value =
									new java.sql.Date(new SimpleDateFormat(DATE_PATTERN).parse(
										(String) map.get(desc.getName())).getTime());
							} catch (ParseException e) {
								log.error(e);
							}
						} else {
							value = OgnlOps.convertValue(map.get(desc.getName()), desc.getPropertyType());

						}
						desc.getWriteMethod().invoke(obj, new Object[] { value });
					}
				}
				list.add(obj);
			}
		} catch (Exception e) {
			log.error("JSON ���ת������.", e);
		}
		return list;
	}

	/**
	 * ��List<E>ת����JSON��ʽ.
	 * 
	 * @param context
	 * @param o
	 * 
	 * @see com.opensymphony.webwork.util.WebWorkTypeConverter#convertToString(java.util.Map,
	 *      java.lang.Object)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public String convertToString(Map context, Object o) {
		String json = null;
		if (o instanceof List) {
			json = new JSONArray((List) o).toString();
		}
		return json;
	}

	// ��JSONObjectת����Map
	@SuppressWarnings("rawtypes")
	private Map toMap(JSONObject object) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Iterator iter = object.keys();
		while (iter.hasNext()) {
			String key = (String) iter.next();
			Object val = object.get(key);
			map.put(key, "".equals(val.toString()) ? null : val);
		}
		return map;
	}

}
