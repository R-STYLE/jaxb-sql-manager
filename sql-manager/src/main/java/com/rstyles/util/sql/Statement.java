package com.rstyles.util.sql;

import java.util.Map;

public interface Statement {

	public String convert(SqlGenerator generator, Map<String, Object> params);

}
