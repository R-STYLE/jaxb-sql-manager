package com.rstyles.util.sql;

import java.util.Map;

public interface IStatement {

	String convert(SqlGenerator generator, Map<String, Object> params);

}
