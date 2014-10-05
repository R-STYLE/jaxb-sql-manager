package com.rstyles.util.sql;

import java.util.ArrayList;
import java.util.List;

import javax.script.ScriptEngine;
import javax.xml.bind.annotation.XmlElement;

public class Using extends Clause implements IClause {

	private List<SimpleClause> columns = new ArrayList<>();
	
	@XmlElement(name = "columns")
	public List<SimpleClause> getColumns() {
		return columns;
	}

	public void setColumns(List<SimpleClause> columns) {
		this.columns = columns;
	}

	@Override
	public String convert(SqlGenerator generator, ScriptEngine engine) {
		return generator.generate(this, engine);
	}

}
