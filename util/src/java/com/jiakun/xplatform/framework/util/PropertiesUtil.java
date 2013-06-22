package com.jiakun.xplatform.framework.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.DefaultPropertiesPersister;
import org.springframework.util.PropertiesPersister;

/**
 * Properties ������
 * 
 */
public class PropertiesUtil {

	private static final Logger logger = Logger.getLogger(PropertiesUtil.class);

	private static final String DEFAULT_ENCODING = "UTF-8";

	private static PropertiesPersister propertiesPersister = new DefaultPropertiesPersister();
	private static ResourceLoader resourceLoader = new DefaultResourceLoader();

	/**
	 * ������properties�ļ�, ��ͬ�����������������ļ��е�ֵ���Ḳ��֮ǰ������. �ļ�·��ʹ��Spring Resource��ʽ,
	 * �ļ�����ʹ��UTF-8.
	 * 
	 * @see org.springframework.beans.factory.config.PropertyPlaceholderConfigurer
	 */
	public static Properties loadProperties(String... resourcesPaths) {
		Properties props = new Properties();

		for (String location : resourcesPaths) {
			InputStream is = null;

			try {
				Resource resource = resourceLoader.getResource(location);
				is = resource.getInputStream();
				propertiesPersister.load(props, new InputStreamReader(is,
						DEFAULT_ENCODING));
			} catch (IOException e) {
				logger.error("Could not load properties from classpath:"
						+ location + ": " + e.getMessage(), e);
			} finally {
				if (is != null) {
					try {
						is.close();
					} catch (IOException e) {
						logger.error(e);
					}
				}
			}
		}

		return props;
	}
}
