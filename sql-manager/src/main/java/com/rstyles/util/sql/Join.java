package com.rstyles.util.sql;

import java.util.ArrayList;
import java.util.List;

import javax.script.ScriptEngine;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Join extends Clause implements ITable {

	private String type;

	private IClause condition;

	private List<IConditions> conditions = new ArrayList<>();

	@XmlAttribute
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@XmlElements({
			@XmlElement(name = "on", type = On.class),
			@XmlElement(name = "using", type = Using.class)
	})
	public IClause getCondition() {
		return condition;
	}

	public void setCondition(IClause condition) {
		this.condition = condition;
	}

	@XmlElement(name = "conditions", type = Conditions.class)
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
