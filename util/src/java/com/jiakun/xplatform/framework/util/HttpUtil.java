package com.jiakun.xplatform.framework.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.net.ssl.SSLHandshakeException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.alibaba.common.lang.StringUtil;

public class HttpUtil {

	private static Logger logger = Logger.getLogger(HttpUtil.class);

	// private static final String CHARSET_UTF8 = "UTF-8";
	private static final String CHARSET_GBK = "GBK";
	private static final String SSL_DEFAULT_SCHEME = "https";
	private static final int SSL_DEFAULT_PORT = 443;

	/**
	 * �쳣�Զ��ָ�����, ʹ��HttpRequestRetryHandler�ӿ�ʵ��������쳣�ָ�
	 */
	private static HttpRequestRetryHandler requestRetryHandler = new HttpRequestRetryHandler() {
		// �Զ���Ļָ�����
		public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
			// ���ûָ����ԣ��ڷ����쳣ʱ���Զ�����3��
			if (executionCount >= 3) {
				// Do not retry if over max retry count
				return false;
			}
			if (exception instanceof NoHttpResponseException) {
				// Retry if the server dropped connection on us
				return true;
			}
			if (exception instanceof SSLHandshakeException) {
				// Do not retry on SSL handshake exception
				return false;
			}
			HttpRequest request = (HttpRequest) context.getAttribute(ExecutionContext.HTTP_REQUEST);
			boolean idempotent = (request instanceof HttpEntityEnclosingRequest);
			if (!idempotent) {
				// Retry if the request is considered idempotent
				return true;
			}
			return false;
		}
	};

	/**
	 * ʹ��ResponseHandler�ӿڴ�����Ӧ��HttpClientʹ��ResponseHandler���Զ��������ӵ��ͷ
	 * ţ�����˶����ӵ��ͷŹ���
	 */
	private static ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
		// �Զ�����Ӧ����
		public String handleResponse(HttpResponse response) throws IOException {
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				@SuppressWarnings("deprecation")
				String charset =
					EntityUtils.getContentCharSet(entity) == null ? CHARSET_GBK : EntityUtils.getContentCharSet(entity);
				return new String(EntityUtils.toByteArray(entity), charset);
			} else {
				return null;
			}
		}
	};

	/**
	 * Get��ʽ�ύ,URL�а��ѯ����, ��ʽ��http://www.g.cn?search=p&name=s.....
	 * 
	 * @param url
	 *            �ύ��ַ
	 * @return ��Ӧ��Ϣ
	 * @throws Exception
	 */
	public static String get(String url) throws Exception {
		return get(url, null, null);
	}

	/**
	 * Get��ʽ�ύ,URL�в����ѯ����, ��ʽ��http://www.g.cn
	 * 
	 * @param url
	 *            �ύ��ַ
	 * @param params
	 *            ��ѯ����, ��/ֵ��
	 * @return ��Ӧ��Ϣ
	 * @throws Exception
	 */
	public static String get(String url, Map<String, String> params) throws Exception {
		return get(url, params, null);
	}

	/**
	 * Get��ʽ�ύ,URL�в����ѯ����, ��ʽ��http://www.g.cn
	 * 
	 * @param url
	 *            �ύ��ַ
	 * @param params
	 *            ��ѯ����, ��/ֵ��
	 * @param charset
	 *            �����ύ���뼯
	 * @return ��Ӧ��Ϣ
	 * @throws Exception
	 */
	public static String get(String url, Map<String, String> params, String charset) throws Exception {
		if (url == null || StringUtil.isEmpty(url)) {
			return null;
		}
		List<NameValuePair> qparams = getParamsList(params);
		if (qparams != null && qparams.size() > 0) {
			charset = (charset == null ? CHARSET_GBK : charset);
			String formatParams = URLEncodedUtils.format(qparams, charset);
			url =
				(url.indexOf("?")) < 0 ? (url + "?" + formatParams)
					: (url.substring(0, url.indexOf("?") + 1) + formatParams);
		}
		DefaultHttpClient httpclient = getDefaultHttpClient(charset);
		HttpGet hg = new HttpGet(url);
		// �������󣬵õ���Ӧ
		String responseStr = null;
		try {
			responseStr = httpclient.execute(hg, responseHandler);
		} catch (ClientProtocolException e) {
			throw new Exception("�ͻ�������Э�����", e);
		} catch (IOException e) {
			throw new Exception("IO�����쳣", e);
		} finally {
			abortConnection(hg, httpclient);
		}

		return responseStr;
	}

	/**
	 * Post��ʽ�ύ,URL�в����ύ����, ��ʽ��http://www.g.cn
	 * 
	 * @param url
	 *            �ύ��ַ
	 * @param params
	 *            �ύ����, ��/ֵ��
	 * @return ��Ӧ��Ϣ
	 * @throws Exception
	 */
	public static String post(String url, Map<String, String> params) throws Exception {
		return post(url, params, null);
	}

	/**
	 * Post��ʽ�ύ,URL�в����ύ����, ��ʽ��http://www.g.cn
	 * 
	 * @param url
	 *            �ύ��ַ
	 * @param params
	 *            �ύ����, ��/ֵ��
	 * @param charset
	 *            �����ύ���뼯
	 * @return ��Ӧ��Ϣ
	 * @throws Exception
	 */
	public static String post(String url, Map<String, String> params, String charset) throws Exception {
		if (url == null || StringUtil.isEmpty(url)) {
			return null;
		}
		// ����HttpClientʵ��
		DefaultHttpClient httpclient = getDefaultHttpClient(charset);
		UrlEncodedFormEntity formEntity = null;
		try {
			if (charset == null || StringUtil.isEmpty(charset)) {
				formEntity = new UrlEncodedFormEntity(getParamsList(params));
			} else {
				formEntity = new UrlEncodedFormEntity(getParamsList(params), charset);
			}
		} catch (UnsupportedEncodingException e) {
			throw new Exception("��֧�ֵı��뼯", e);
		}
		HttpPost hp = new HttpPost(url);
		hp.setEntity(formEntity);
		// �������󣬵õ���Ӧ
		String responseStr = null;
		try {
			responseStr = httpclient.execute(hp, responseHandler);
		} catch (ClientProtocolException e) {
			throw new Exception("�ͻ�������Э�����", e);
		} catch (IOException e) {
			throw new Exception("IO�����쳣", e);
		} finally {
			abortConnection(hp, httpclient);
		}

		return responseStr;
	}

	/**
	 * Post��ʽ�ύ,����URL�а�Ĳ���,���SSL˫������֤����֤
	 * 
	 * @param url
	 *            �ύ��ַ
	 * @param params
	 *            �ύ����, ��/ֵ��
	 * @param charset
	 *            ������뼯
	 * @param keystoreUrl
	 *            ��Կ�洢��·��
	 * @param keystorePassword
	 *            ��Կ�洢���������
	 * @param truststoreUrl
	 *            ���δ洢���·��
	 * @param truststorePassword
	 *            ���δ洢���������, ��Ϊnull
	 * @return ��Ӧ��Ϣ
	 * @throws Exception
	 */
	public static String post(String url, Map<String, String> params, String charset, final URL keystoreUrl,
		final String keystorePassword, final URL truststoreUrl, final String truststorePassword) throws Exception {
		if (url == null || StringUtil.isEmpty(url)) {
			return null;
		}
		DefaultHttpClient httpclient = getDefaultHttpClient(charset);
		UrlEncodedFormEntity formEntity = null;
		try {
			if (charset == null || StringUtil.isEmpty(charset)) {
				formEntity = new UrlEncodedFormEntity(getParamsList(params));
			} else {
				formEntity = new UrlEncodedFormEntity(getParamsList(params), charset);
			}
		} catch (UnsupportedEncodingException e) {
			throw new Exception("��֧�ֵı��뼯", e);
		}
		HttpPost hp = null;
		String responseStr = null;
		try {
			KeyStore keyStore = createKeyStore(keystoreUrl, keystorePassword);
			KeyStore trustStore = createKeyStore(truststoreUrl, keystorePassword);
			SSLSocketFactory socketFactory = new SSLSocketFactory(keyStore, keystorePassword, trustStore);
			@SuppressWarnings("deprecation")
			Scheme scheme = new Scheme(SSL_DEFAULT_SCHEME, socketFactory, SSL_DEFAULT_PORT);
			httpclient.getConnectionManager().getSchemeRegistry().register(scheme);
			hp = new HttpPost(url);
			hp.setEntity(formEntity);
			responseStr = httpclient.execute(hp, responseHandler);
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("ָ���ļ����㷨������", e);
		} catch (KeyStoreException e) {
			throw new Exception("keytore�����쳣", e);
		} catch (CertificateException e) {
			throw new Exception("����֤����ڻ�����쳣", e);
		} catch (FileNotFoundException e) {
			throw new Exception("keystore�ļ�������", e);
		} catch (IOException e) {
			throw new Exception("I/O����ʧ�ܻ��ж� ", e);
		} catch (UnrecoverableKeyException e) {
			throw new Exception("keystore�е���Կ�޷��ָ��쳣", e);
		} catch (KeyManagementException e) {
			throw new Exception("������Կ����Ĳ����쳣", e);
		} finally {
			abortConnection(hp, httpclient);
		}

		return responseStr;
	}

	/**
	 * ��ȡDefaultHttpClientʵ��
	 * 
	 * @param charset
	 *            ������뼯, �ɿ�
	 * @return DefaultHttpClient ����
	 */
	private static DefaultHttpClient getDefaultHttpClient(final String charset) {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
		// ģ������������һЩ����������ֻ������������ʵ�����
		httpclient.getParams().setParameter(CoreProtocolPNames.USER_AGENT,
			"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)");
		httpclient.getParams().setParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE, Boolean.FALSE);
		httpclient.getParams().setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET,
			charset == null ? CHARSET_GBK : charset);
		httpclient.setHttpRequestRetryHandler(requestRetryHandler);

		return httpclient;
	}

	/**
	 * �ͷ�HttpClient����
	 * 
	 * @param hrb
	 *            �������
	 * @param httpclient
	 *            client����
	 */
	private static void abortConnection(final HttpRequestBase hrb, final HttpClient httpclient) {
		if (hrb != null) {
			hrb.abort();
		}
		if (httpclient != null) {
			httpclient.getConnectionManager().shutdown();
		}
	}

	/**
	 * �Ӹ��·���м��ش� KeyStore
	 * 
	 * @param url
	 *            keystore URL·��
	 * @param password
	 *            keystore������Կ
	 * @return keystore ����
	 */
	private static KeyStore createKeyStore(final URL url, final String password) throws KeyStoreException,
		NoSuchAlgorithmException, CertificateException, IOException {
		if (url == null) {
			throw new IllegalArgumentException("Keystore url may not be null");
		}
		KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
		InputStream is = null;
		try {
			is = url.openStream();
			keystore.load(is, password != null ? password.toCharArray() : null);
		} finally {
			if (is != null) {
				is.close();
				is = null;
			}
		}

		return keystore;
	}

	/**
	 * ������ļ�/ֵ�Բ���ת��ΪNameValuePair����
	 * 
	 * @param paramsMap
	 *            ����, ��/ֵ��
	 * @return NameValuePair����
	 */
	private static List<NameValuePair> getParamsList(Map<String, String> paramsMap) {
		if (paramsMap == null || paramsMap.size() == 0) {
			return null;
		}
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		for (Map.Entry<String, String> map : paramsMap.entrySet()) {
			params.add(new BasicNameValuePair(map.getKey(), map.getValue()));
		}

		return params;
	}

	public static void main(String[] arg) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "xplatform.user.login");
		params.put("passport", "123");
		params.put("password", "123");
		for (int i = 0; i < 10; i++) {
			try {
				HttpUtil.post("http://ims.jiakun.com.cn:8191/xplatform/router/rest", params);
			} catch (Exception e) {
				logger.error(e);
			}
		}
	}
}
