package edu.hcmuaf.tms.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorUtil {

	public static final Pattern VALID_USERNAME_REGEX = Pattern.compile("^[a-zA-Z]+[a-zA-Z\\d]+$");

	public static boolean validateUsername(String userName) {
		if (userName == null)
			return false;
		Matcher matcher = VALID_USERNAME_REGEX.matcher(userName);
		return matcher.find();
	}
}
