package com.ordrit.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtils {

	//Is Email Valid
	public static boolean isEmailValid(String email) {
		boolean isValid = false;
		String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
		CharSequence inputStr = email;
		Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(inputStr);
		if (matcher.matches()) {
			isValid = true;
		}
		return isValid;

	}
	
	//Is Phone Number Valid
	public static boolean isPhoneNumberValid(String phoneNumber){
		String regex= "(\\d{3}-){1,2}\\d{4}";
		boolean isValid = false;
		if (phoneNumber.matches(regex)) {
			isValid = true;
	    }
		return isValid;
	}
	public static boolean isEmpty(String text){
		boolean isempty=false;
		if (text!=null && text.length()>0) {
			isempty=true;
		}
		return isempty;
	}
}