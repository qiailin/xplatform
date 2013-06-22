package com.jiakun.xplatform.framework.webwork.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Token {

	/**
	 * ��ʾά��token�����ͣ�create���½�token��check�����token
	 */
	String type();
}