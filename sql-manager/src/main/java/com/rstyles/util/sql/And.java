package com.rstyles.util.sql;

import java.util.List;

import javax.script.ScriptEngine;

public class And extends Connector implements IConditions {

	private static final String TYPE = "AND";
	
	@Override
	public String getType() {
		return TYPE;
	}

	@Override
	public String convert(SqlGenerator generator, ScriptEngine engine, List<String> clauses) {
		return generator.generate(this, engine, clauses);
	}

}
