package com.rstyles.util.sql;

import java.util.ArrayList;
import java.util.List;

import javax.script.ScriptEngine;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class On extends Clause implements IClause {

	private List<IConditions> conditions = new ArrayList<>();

	@XmlElements({
			@XmlElement(name = "conditions", type = Conditions.class),
			@XmlElement(name = "and", type = And.class),
			@XmlElement(name = "or", type = Or.class)
	})
	public List<IConditions> getConditions() {
		return conditions;
	}

	public void setConditions(List<IConditions> conditions) {
		this.conditions = conditions;
	}

	@Override
	public String convert(SqlGenerator generator, ScriptEngine engine) {
		return generator.generate(this, engine);
	}

}
