package com.jiakun.xplatform.framework.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.log4j.Logger;

/***
 * HDFS ������
 * 
 */
public final class HDFSUtil {

	private static Logger logger = Logger.getLogger(HDFSUtil.class);

	private static final int BUFF_SIZE = 4 * 1024;

	private HDFSUtil() {

	}

	public static FileSystem getFileSystem(String ip, int port) throws Exception {
		FileSystem fs = null;

		try {
			String url = "hdfs://" + ip + ":" + String.valueOf(port);
			Configuration config = new Configuration();
			config.set("fs.default.name", url);

			fs = FileSystem.get(config);
		} catch (Exception e) {
			logger.error("getFileSystem failed :" + ExceptionUtils.getFullStackTrace(e));

			throw new Exception("getFileSystem exception");
		}

		return fs;
	}

	/**
	 * ����Ŀ¼�͸�Ŀ¼
	 * 
	 * @param fs
	 * @param dirName
	 * @throws Exception
	 */
	public static void mkdirs(FileSystem fs, String dirName) throws Exception {
		String dir = null;

		try {
			Path workDir = fs.getWorkingDirectory();
			dir = workDir + "/" + dirName;
			Path src = new Path(dir);
			boolean succ = fs.mkdirs(src);

			if (!succ) {
				throw new Exception("mkdirs error");
			}
		} catch (Exception e) {
			logger.error("create directory " + dir + " failed :" + ExceptionUtils.getFullStackTrace(e));

			throw new Exception("mkdirs exception");
		}
	}

	/**
	 * ɾ��Ŀ¼����Ŀ¼
	 * 
	 * @param fs
	 * @param dirName
	 * @throws Exception
	 */
	public static void rmdirs(FileSystem fs, String dirName) throws Exception {
		String dir = null;

		try {
			Path workDir = fs.getWorkingDirectory();
			dir = workDir + "/" + dirName;
			Path src = new Path(dir);
			boolean succ = fs.delete(src, true);

			if (!succ) {
				throw new Exception("rmdirs error");
			}
		} catch (Exception e) {
			logger.error("remove directory " + dir + " failed :" + ExceptionUtils.getFullStackTrace(e));

			throw new Exception("rmdirs exception");
		}
	}

	/**
	 * �ϴ�Ŀ¼���ļ�
	 * 
	 * @param fs
	 * @param local
	 * @param remote
	 * @throws Exception
	 */
	public static void upload(FileSystem fs, String local, String remote) throws Exception {
		try {
			Path workDir = fs.getWorkingDirectory();
			Path dst = new Path(workDir + "/" + remote);
			Path src = new Path(local);

			fs.copyFromLocalFile(false, true, src, dst);
		} catch (Exception e) {
			logger.error("upload " + local + " to  " + remote + " failed :" + ExceptionUtils.getFullStackTrace(e));

			throw new Exception("upload exception");
		}
	}

	/**
	 * ����Ŀ¼���ļ�
	 * 
	 * @param fs
	 * @param local
	 * @param remote
	 * @throws Exception
	 */
	public static void download(FileSystem fs, String local, String remote) throws Exception {
		try {
			Path dst = new Path(remote);
			Path src = new Path(local);

			fs.copyToLocalFile(false, dst, src);
		} catch (Exception e) {
			logger.error("download from " + remote + " to  " + local + " failed :"
				+ ExceptionUtils.getFullStackTrace(e));

			throw new Exception("download exception");
		}
	}

	/**
	 * д�ļ�
	 * 
	 * @param fs
	 * @param path
	 * @param data
	 * @throws Exception
	 */
	public static void write(FileSystem fs, String path, byte[] bytes) throws Exception {
		FSDataOutputStream out = null;

		try {
			Path workDir = fs.getWorkingDirectory();
			Path dst = new Path(workDir + "/" + path);

			out = fs.create(dst);
			out.write(bytes);
		} catch (Exception e) {
			logger.error("write content to " + path + " failed :" + ExceptionUtils.getFullStackTrace(e));

			throw new Exception("write exception");
		} finally {
			IOUtils.closeStream(out);
		}
	}

	/**
	 * д�ļ�
	 * 
	 * @param fs
	 * @param path
	 * @param data
	 * @throws Exception
	 * 
	 */
	public static void write(FileSystem fs, String path, File data) throws Exception {
		InputStream in = null;
		FSDataOutputStream out = null;

		try {
			Path workDir = fs.getWorkingDirectory();
			Path dst = new Path(workDir + "/" + path);

			in = new BufferedInputStream(new FileInputStream(data));
			out = fs.create(dst);

			IOUtils.copyBytes(in, out, BUFF_SIZE, true);
		} catch (Exception e) {
			logger.error("write content to " + path + " failed :" + ExceptionUtils.getFullStackTrace(e));

			throw new Exception("write exception");
		} finally {
			IOUtils.closeStream(out);
			IOUtils.closeStream(in);
		}
	}

	/**
	 * ���ļ�
	 * 
	 * @param fs
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static String read(FileSystem fs, String path) throws Exception {
		FSDataInputStream in = null;

		try {
			Path dst = new Path(path);

			// reading
			in = fs.open(dst);
			return in.readUTF();
		} catch (Exception e) {
			logger.error("read content from " + path + " failed :" + ExceptionUtils.getFullStackTrace(e));

			throw new Exception("read exception");
		} finally {
			IOUtils.closeStream(in);
		}
	}

	/**
	 * ���ļ�
	 * 
	 * @param fs
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static void read(FileSystem fs, String path, OutputStream output) throws Exception {
		FSDataInputStream in = null;

		try {
			Path dst = new Path(path);

			// reading
			in = fs.open(dst);

			IOUtils.copyBytes(in, output, BUFF_SIZE, true);
		} catch (Exception e) {
			logger.error("read content from " + path + " failed :" + ExceptionUtils.getFullStackTrace(e));

			throw new Exception("read exception");
		} finally {
			IOUtils.closeStream(in);
		}
	}

}
