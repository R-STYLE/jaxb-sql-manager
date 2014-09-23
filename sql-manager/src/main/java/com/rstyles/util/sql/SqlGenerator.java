package com.rstyles.util.sql;

import java.util.Map;

public class SqlGenerator {

	public static String generate(final Class<?> clazz, final String id, final Map<String, Object> params) {
		
		if (clazz == null || Utility.isEmpty(id)) {
			// TODO: message.
			throw new IllegalArgumentException();
		}
		
		return null;
	}

}
