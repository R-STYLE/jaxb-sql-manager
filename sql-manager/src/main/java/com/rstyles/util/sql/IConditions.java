package com.rstyles.util.sql;

import java.util.List;

import javax.script.ScriptEngine;


public interface IConditions {
	
	String convert(SqlGenerator generator, ScriptEngine engine, List<String> clauses);

}
