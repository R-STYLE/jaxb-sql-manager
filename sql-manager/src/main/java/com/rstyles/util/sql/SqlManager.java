package com.rstyles.util.sql;

import java.io.FileNotFoundException;
import java.util.Map;

public final class SqlManager {
	
	private static final SqlGenerator GENERATOR = new SqlGenerator();

	private SqlManager() {
	}
	
	public static String getSql(final Class<?> clazz, final String id, final Map<String, Object> params) throws FileNotFoundException {
		final SqlContainer container = SqlRegistry.load(clazz);
		return GENERATOR.generate(container, id, params);
	}
	
}
