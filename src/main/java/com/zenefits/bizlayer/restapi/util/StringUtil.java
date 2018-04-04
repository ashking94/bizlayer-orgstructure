package com.zenefits.bizlayer.restapi.util;

public class StringUtil {

	private StringUtil() {

	}

	public static boolean isNullorEmpty(String str) {
		return str == null || str.isEmpty();
	}
}
