package com.zenefits.bizlayer.restapi.util;

import java.util.Collection;

public class CollectionUtil {

	private CollectionUtil() {

	}

	public static <T> boolean isNullorEmpty(Collection<T> list) {
		return list == null || list.isEmpty();
	}

}
