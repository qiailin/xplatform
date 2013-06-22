package com.jiakun.xplatform.framework.mail;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.AuthenticationFailedException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import com.jiakun.xplatform.framework.log.Logger4jCollection;
import com.jiakun.xplatform.framework.log.Logger4jExtend;
import com.jiakun.xplatform.framework.util.LogUtil;

public class MailService {

	private Logger4jExtend logger = Logger4jCollection
			.getLogger(MailService.class);

	// ���巢���ˡ��ռ��ˡ�SMTP���������û������롢���⡢���ݵ�
	private String displayName;
	private String to;
	private String from;
	private String smtpServer;
	private String username;
	private String password;
	private String subject;
	private String content;
	private boolean ifAuth; // �������Ƿ�Ҫ�����֤
	private String filename = "";
	private List<Mail> files = new ArrayList<Mail>(); // ���ڱ��淢�͸������ļ���ļ���

	/**
	 * ����SMTP��������ַ
	 */
	public void setSmtpServer(String smtpServer) {
		this.smtpServer = smtpServer;
	}

	/**
	 * ���÷����˵ĵ�ַ
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * ������ʾ�����
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * ���÷������Ƿ���Ҫ�����֤
	 */
	public void setIfAuth(boolean ifAuth) {
		this.ifAuth = ifAuth;
	}

	/**
	 * ����E-mail�û���
	 */
	public void setUserName(String username) {
		this.username = username;
	}

	/**
	 * ����E-mail����
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * ���ý�����
	 */
	public void setTo(String to) {
		this.to = to;
	}

	/**
	 * ��������
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * ������������
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * �÷��������ռ�������
	 */
	public void addAttachfile(Mail mailFile) {
		files.add(mailFile);
	}

	/**
	 * ��ʼ��SMTP��������ַ��������E-mail��ַ���û������롢�����ߡ����⡢����
	 */
	public MailService(String smtpServer, String from, String displayName,
			String username, String password, String to, String subject,
			String content) {
		this.smtpServer = smtpServer;
		this.from = from;
		this.displayName = displayName;
		this.ifAuth = true;
		this.username = username;
		this.password = password;
		this.to = to;
		this.subject = subject;
		this.content = content;
	}

	/**
	 * ��ʼ��SMTP��������ַ��������E-mail��ַ�������ߡ����⡢����
	 */
	public MailService(String smtpServer, String from, String displayName,
			String to, String subject, String content) {
		this.smtpServer = smtpServer;
		this.from = from;
		this.displayName = displayName;
		this.ifAuth = false;
		this.to = to;
		this.subject = subject;
		this.content = content;
	}

	/**
	 * �����ʼ�
	 */
	@SuppressWarnings("unused")
	public HashMap<String, String> send() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("state", "success");
		String message = "�ʼ����ͳɹ���";
		Session session = null;
		Properties props = System.getProperties();
		props.put("mail.smtp.host", smtpServer);

		// ��������Ҫ�����֤
		if (ifAuth) {
			props.put("mail.smtp.auth", "true");
			SmtpAuth smtpAuth = new SmtpAuth(username, password);
			session = Session.getDefaultInstance(props, smtpAuth);
		} else {
			props.put("mail.smtp.auth", "false");
			session = Session.getDefaultInstance(props, null);
		}
		session.setDebug(false);
		Transport trans = null;
		Message msg = null;
		try {
			msg = new MimeMessage(session);
			Address from_address = null;
			try {
				displayName = MimeUtility
						.encodeText(displayName, "gb2312", "B");
				from_address = new InternetAddress(from, displayName);
				msg.setFrom(from_address);
			} catch (java.io.UnsupportedEncodingException e) {
				logger.error(LogUtil.parserBean(from_address), e);
			}
			// ����ռ���
			String[] toArray = to.split(";");
			InternetAddress[] toAdd = new InternetAddress[toArray.length];
			for (int i = 0; i < toArray.length; i++) {
				toAdd[i] = new InternetAddress(toArray[i]);
			}
			InternetAddress[] address = toAdd;
			msg.setRecipients(Message.RecipientType.TO, address);
			msg.setHeader("Disposition-Notification-To", from);
			try {
				subject = MimeUtility.encodeText(subject, "gb2312", "B");
			} catch (Exception e) {
				logger.error(LogUtil.parserBean(from_address), e);
			}

			msg.setSubject(subject);
			Multipart mp = new MimeMultipart();
			MimeBodyPart mbp = new MimeBodyPart();
			mbp.setContent(content.toString(), "text/html;charset=GBK");
			mp.addBodyPart(mbp);
			// �и���
			if (!files.isEmpty()) {
				for (Mail file : files) {
					mbp = new MimeBodyPart();
					// �õ����Դ
					FileDataSource fds = new FileDataSource(file.getFilepath());
					// �õ��������?����BodyPart
					mbp.setDataHandler(new DataHandler(fds));
					try {
						filename = MimeUtility.encodeText(file.getFilename(),
								"gb2312", "B");
					} catch (UnsupportedEncodingException e) {
						logger.error(LogUtil.parserBean(file), e);
					}
					// �õ��ļ���ͬ������BodyPart
					mbp.setFileName(filename);
					mp.addBodyPart(mbp);
				}
			}
			// Multipart���뵽�ż�
			msg.setContent(mp);
			// �����ż�ͷ�ķ�������
			msg.setSentDate(new Date());
			// �����ż�
			msg.saveChanges();
			trans = session.getTransport("smtp");
			trans.connect(smtpServer, username, password);
			trans.sendMessage(msg, msg.getAllRecipients());
			trans.close();

		} catch (AuthenticationFailedException e) {
			map.put("state", "failed");
			message = "�ʼ�����ʧ�ܣ�����ԭ��\n" + "�����֤����!";
			logger.error(LogUtil.parserBean(msg), e);
		} catch (MessagingException e) {
			message = "�ʼ�����ʧ�ܣ�����ԭ��\n" + e.getMessage();
			map.put("state", "failed");
			Exception ex = null;
			if ((ex = e.getNextException()) != null) {
				logger.error(LogUtil.parserBean(msg), e);
			}
		}
		map.put("message", message);
		return map;
	}
}
