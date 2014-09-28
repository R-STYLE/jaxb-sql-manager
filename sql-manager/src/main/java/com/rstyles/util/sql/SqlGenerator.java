package com.rstyles.util.sql;

import java.util.Map;

class SqlGenerator {

	String generate(final SqlContainer container, final String id, final Map<String, Object> params) {
		
		return container.toString();
	}


}
