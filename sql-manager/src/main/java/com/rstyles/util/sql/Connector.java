package com.rstyles.util.sql;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;

public abstract class Connector extends Clause {

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

	abstract public String getType();

}
