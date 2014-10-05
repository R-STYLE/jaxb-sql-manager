package com.rstyles.util.sql;

import java.util.List;
import java.util.Map;

import javax.script.ScriptEngine;

interface SqlGenerator {

	String generate(Insert insert, Map<String, Object> params);
	String generate(Select select, Map<String, Object> params);
	String generate(Update update, Map<String, Object> params);
	String generate(Delete delete, Map<String, Object> params);
	
	String generate(SimpleClause clause, ScriptEngine engine);
	String generate(Values values, ScriptEngine engine);
	String generate(SelectClause select, ScriptEngine engine);
	String generate(Conditions conditions, ScriptEngine engine, List<String> clauses);
	String generate(Connector connector, ScriptEngine engine, List<String> clauses);
	String generate(Where where, ScriptEngine engine);
	String generate(Table table, ScriptEngine engine);
	String generate(From from, ScriptEngine engine);
	String generate(Join join, ScriptEngine engine);
	String generate(On on, ScriptEngine engine);
	String generate(Using using, ScriptEngine engine);
	String generate(Set set, ScriptEngine engine);
	
}
