package com.jiakun.xplatform.framework.webwork.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ����ָʾ�ֶ���Ҫת����JSON���� һ��include��exclude����ͬʱʹ��
 * 
 * example:
 * 
 * @JsonResult(include = { "id", "name" }) private List<User> users = new
 *                     List<User>(); ... getter
 * 
 * @author tingjia.chentj
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface JsonResult {

	/**
	 * ��¼���ֶε�����.
	 */
	String field();

	/**
	 * �ܼ�¼���ֶ�����.
	 * 
	 * @return
	 */
	String total() default "";

	/**
	 * �������.
	 */
	String[] include() default {

	};

	/**
	 * �ų������.
	 */
	String[] exclude() default {

	};

}
