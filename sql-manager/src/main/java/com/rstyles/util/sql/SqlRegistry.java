package com.rstyles.util.sql;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class SqlRegistry {

	private static ConcurrentMap<Class<?>, Sql> CACHE = new ConcurrentHashMap<>();
	
	public static String getSql(Class<?> clazz, String id, Map<String, Object> params) {
		
		if (clazz == null || Utility.isEmpty(id)) {
			// TODO: message.
			throw new IllegalArgumentException();
		}
		
		return null;
	}
}
