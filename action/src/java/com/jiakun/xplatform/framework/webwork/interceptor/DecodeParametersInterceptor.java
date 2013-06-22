package com.jiakun.xplatform.framework.webwork.interceptor;

import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.util.Map;

import ognl.ObjectPropertyAccessor;
import ognl.OgnlException;
import ognl.OgnlRuntime;
import ognl.PropertyAccessor;

import com.jiakun.xplatform.framework.annotation.Decode;
import com.jiakun.xplatform.framework.log.Logger4jCollection;
import com.jiakun.xplatform.framework.log.Logger4jExtend;
import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.xwork.ActionInvocation;
import com.opensymphony.xwork.interceptor.AroundInterceptor;

/**
 * ������webwork�����ļ�������ʱ�����params֮ǰ ���ڴ���AJAX����ʱ����������
 * 
 * @author tingjia.chentj
 */
public class DecodeParametersInterceptor extends AroundInterceptor {

	private static final long serialVersionUID = 6484320257843908148L;

	private static Logger4jExtend log = Logger4jCollection
			.getLogger(DecodeParametersInterceptor.class);

	private final static ThreadLocal<Boolean> encoded = new ThreadLocal<Boolean>();

	public static boolean isEncoded() {
		return encoded.get() == null ? false : encoded.get();
	}

	public static void setEncoded(boolean value) {
		encoded.set(value);
	}

	static {
		OgnlRuntime.setPropertyAccessor(Object.class, getObjectAccessor());
	}

	protected void after(ActionInvocation invocation, String result)
			throws Exception {
		encoded.set(null);
		encoded.remove();
	}

	protected void before(ActionInvocation invocation) throws Exception {
		setEncoded("XMLHttpRequest".equalsIgnoreCase(ServletActionContext
				.getRequest().getHeader("x-requested-with")));
	}

	private static final PropertyAccessor getObjectAccessor() {
		// ������ǰ�����Ҫ�Ƚ���URLDecode
		return new ObjectPropertyAccessor() {
			@SuppressWarnings("rawtypes")
			@Override
			public void setProperty(Map context, Object target, Object oname,
					Object value) throws OgnlException {
				if (DecodeParametersInterceptor.isEncoded()) {
					try {
						boolean decode = target.getClass().isAnnotationPresent(
								Decode.class);
						decode = decode
								|| getDeclaredField(target.getClass(),
										(String) oname).isAnnotationPresent(
										Decode.class);
						if (decode) {
							String[] tmp = (value == null ? new String[0]
									: (String[]) value);
							for (int i = 0, len = tmp.length; i < len; i++) {
								if (tmp[i] != null) {
									tmp[i] = URLDecoder.decode(tmp[i], "UTF-8");
								}
							}
						}
					} catch (Exception e) {
						// getDeclaredField���ܻ᷵�ؿ�ֵ���Ҳ�����Ӧ��fieldʱ��
						log.error(e); // do nothing
					}
				}
				super.setProperty(context, target, oname, value);
			}

			@SuppressWarnings("rawtypes")
			private Field getDeclaredField(Class target, String name) {
				if (target == null)
					return null;
				try {
					return target.getDeclaredField(name);
				} catch (Exception e) {
					return getDeclaredField(target.getSuperclass(), name);
				}
			}
		};
	}

}
