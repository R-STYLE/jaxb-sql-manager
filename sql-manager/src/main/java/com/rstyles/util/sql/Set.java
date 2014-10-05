package com.rstyles.util.sql;

import java.util.ArrayList;
import java.util.List;

import javax.script.ScriptEngine;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Set extends Clause implements IClause {

	private List<SimpleClause> assigns = new ArrayList<>();

	@XmlElement
	public List<SimpleClause> getConditions() {
		return assigns;
	}

	public void setConditions(List<SimpleClause> assigns) {
		this.assigns = assigns;
	}

	@Override
	public String convert(SqlGenerator generator, ScriptEngine engine) {
		return generator.generate(this, engine);
	}

}
