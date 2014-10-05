package com.rstyles.util.sql;

import java.util.ArrayList;
import java.util.List;

import javax.script.ScriptEngine;
import javax.xml.bind.annotation.XmlElement;

public class SimpleClause extends Clause implements IClause {

	private List<Conditional> conditionals = new ArrayList<>();

	@XmlElement(name = "if")
	public List<Conditional> getConditionals() {
		return conditionals;
	}

	public void setConditionals(List<Conditional> conditionals) {
		this.conditionals = conditionals;
	}

	@Override
	public String convert(SqlGenerator generator, ScriptEngine engine) {
		return generator.generate(this, engine);
	}

}
