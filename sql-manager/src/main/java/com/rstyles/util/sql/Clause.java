package com.rstyles.util.sql;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlMixed;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Clause {

	private String condition;
	private List<String> contexts = new ArrayList<>();
	private List<Condition> conditions = new ArrayList<>();

	@XmlAttribute(name = "if")
	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	@XmlMixed
	public List<String> getContexts() {
		return contexts;
	}

	public void setContexts(List<String> contexts) {
		this.contexts = contexts;
	}

	@XmlElement(name = "if")
	public List<Condition> getConditions() {
		return conditions;
	}

	public void setConditions(List<Condition> conditions) {
		this.conditions = conditions;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
