package com.rstyles.util.sql;

import javax.script.ScriptEngine;

public interface IClause {

	String convert(SqlGenerator generator, ScriptEngine engine);

}
