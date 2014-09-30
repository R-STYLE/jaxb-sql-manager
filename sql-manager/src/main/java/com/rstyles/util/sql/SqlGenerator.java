package com.rstyles.util.sql;

import java.util.Map;

interface SqlGenerator {

	String generate(final Insert insert, final Map<String, Object> params);

	String generate(final Select select, final Map<String, Object> params);
	
	String generate(final Update update, final Map<String, Object> params);
	
	String generate(final Delete delete, final Map<String, Object> params);
	
}
