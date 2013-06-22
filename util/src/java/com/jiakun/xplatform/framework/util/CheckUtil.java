package com.jiakun.xplatform.framework.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckUtil {

	public static boolean regMail(String mail) {
		Pattern pattern = Pattern.compile(
				"^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$",
				Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(mail);
		return matcher.matches();
	}

}
