package com.jiakun.xplatform.framework.util;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.alibaba.common.lang.StringUtil;

/**
 * 
 * @author
 * 
 */
public final class MapUtil {

	private static final Object[] EMPTY_ARRAY = {};

	private MapUtil() {

	}

	/**
	 * ��һ��beanת����map
	 * 
	 * @param map
	 * @param bean
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Map populateMap(Map map, Object bean) {
		return populateMap(map, bean, null);
	}

	/**
	 * ����prefix=detail.��bean����һ������name����map�н���һ���
	 * key=detail.name��valueΪbean��name����ֵ��
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map populateMap(Map map, Object bean, String prefix) {
		boolean withoutPrefix = StringUtil.isBlank(prefix);

		try {
			Method[] methods = bean.getClass().getMethods();
			for (int i = 0; i < methods.length; i++) {
				String methodName = methods[i].getName();
				Class[] pts = methods[i].getParameterTypes();
				Class rt = methods[i].getReturnType();

				if (methodName.startsWith("get") && pts.length == 0 && !Void.class.equals(rt)) {
					String propName = Character.toLowerCase(methodName.charAt(3)) + methodName.substring(4);
					if ("class".equals(propName)) {
						continue;
					}

					String key = withoutPrefix ? propName : prefix + propName;

					Object value = methods[i].invoke(bean, EMPTY_ARRAY);
					if (value != null) {
						map.put(key, value);
					}
				}
			}

			return map;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * ��mapת����bean
	 * 
	 * @param bean
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Object populateBean(Object bean, Map map) {
		try {
			BeanUtils.populate(bean, map);
			return bean;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * ����prefix=detail.����map�д���һ��keyΪdetail.name��
	 * ��bean.setName�����ã�����ֵΪ��key��Ӧ��value��
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Object populateBean(Object bean, Map map, String prefix) {
		boolean withoutPrefix = StringUtil.isBlank(prefix);
		Map m = new HashMap();

		try {
			Method[] methods = bean.getClass().getMethods();
			for (int i = 0; i < methods.length; i++) {
				String methodName = methods[i].getName();

				if (methodName.startsWith("set")) {
					String propName = Character.toLowerCase(methodName.charAt(3)) + methodName.substring(4);
					String key = withoutPrefix ? propName : prefix + propName;

					Object value = map.get(key);
					if (value != null) {
						Class[] pts = methods[i].getParameterTypes();
						if (pts.length == 1) {
							// Class cls = pts[0];
							// if (cls.isArray()) {
							// cls = cls.getComponentType();
							// }

							m.put(propName, value);
						}
					}
				}
			}

			BeanUtils.populate(bean, m);
			return bean;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
