package com.jiakun.xplatform.framework.velocity;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.ToolboxManager;
import org.apache.velocity.tools.view.context.ToolboxContext;

import com.opensymphony.webwork.views.velocity.VelocityManager;

/**
 * 
 * @author
 * 
 */
public final class VelocityUtil {

	private static final String DEFAULT_INPUT_ENCODING = "GBK";
	private static final String DEFAULT_OUTPUT_ENCODING = "GBK";

	private VelocityUtil() {

	}

	/**
	 * �ϲ�ģ���ļ���Context �����߸���OutputStream�İ�ȫ�ر�
	 */
	public static void mergeFile(String fileName, Context ctx, OutputStream os) throws Exception {
		mergeFile(fileName, ctx, os, DEFAULT_INPUT_ENCODING, DEFAULT_OUTPUT_ENCODING);
	}

	/**
	 * �ϲ�ģ���ļ���Context �����߸���OutputStream�İ�ȫ�ر�
	 */
	public static void mergeFile(String fileName, Context ctx, OutputStream os, String inputEncoding,
		String outputEncoding) throws Exception {

		OutputStreamWriter writer = new OutputStreamWriter(os, outputEncoding);
		mergeFile(fileName, ctx, writer, inputEncoding);
	}

	/**
	 * �ϲ�ģ���ļ���Context �����߸���Writer�İ�ȫ�ر�
	 */
	public static void mergeFile(String fileName, Context ctx, Writer writer) throws Exception {
		mergeFile(fileName, ctx, writer, DEFAULT_INPUT_ENCODING);
	}

	/**
	 * �ϲ�ģ���ļ���Context �����߸���Writer�İ�ȫ�ر�
	 */
	public static void mergeFile(String fileName, Context ctx, Writer writer, String inputEncoding) throws Exception {

		VelocityManagerEx mgr = (VelocityManagerEx) VelocityManager.getInstance();
		VelocityEngine engine = mgr.getVelocityEngine();

		ToolboxManager toolboxMgr = mgr.getToolboxManager();
		Context context = new MergedContext(ctx, toolboxMgr.getToolboxContext(ctx));

		engine.mergeTemplate(fileName, inputEncoding, context, writer);
	}

	/**
	 * �ϲ�ģ���ļ���Context
	 */
	public static String mergeFile(String fileName, Context ctx) throws Exception {
		return mergeFile(fileName, ctx, DEFAULT_INPUT_ENCODING);
	}

	/**
	 * �ϲ�ģ���ļ���Context
	 */
	public static String mergeFile(String fileName, Context ctx, String inputEncoding) throws Exception {

		StringWriter writer = new StringWriter();
		mergeFile(fileName, ctx, writer, inputEncoding);
		return writer.toString();
	}

	/**
	 * �ϲ�ģ���ַ���Context
	 * 
	 * @param str
	 *            ģ�����ݣ����԰�VTL
	 */
	public static String mergeString(String str, Context ctx) throws Exception {
		VelocityManagerEx mgr = (VelocityManagerEx) VelocityManager.getInstance();
		VelocityEngine engine = mgr.getVelocityEngine();

		ToolboxManager toolboxMgr = mgr.getToolboxManager();
		Context context = new MergedContext(ctx, toolboxMgr.getToolboxContext(ctx));

		StringWriter writer = new StringWriter();
		return engine.evaluate(context, writer, "mergeString", str) ? writer.toString() : "";
	}

	/**
	 * 
	 * @author
	 * 
	 */
	private static class MergedContext extends VelocityContext {

		private final Context ctx;
		private final ToolboxContext toolbox;

		public MergedContext(Context ctx, ToolboxContext toolbox) {
			this.ctx = ctx;
			this.toolbox = toolbox;
		}

		public Object internalGet(String key) {
			Object o = null;

			if (toolbox != null) {
				o = toolbox.get(key);
				if (o != null) {
					return o;
				}
			}

			return ctx.get(key);
		}

	}

}
