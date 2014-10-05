package com.rstyles.util.sql;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlMixed;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Conditional {

	private String expression;

	private List<String> contexts = new ArrayList<>();

	private Conditional otherwise;

	@XmlAttribute(name = "expr")
	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	@XmlMixed
	public List<String> getContexts() {
		return contexts;
	}

	public void setContexts(List<String> contexts) {
		this.contexts = contexts;
	}

	@XmlElement(name = "else")
	public Conditional getOtherwise() {
		return otherwise;
	}

	public void setOtherwise(Conditional otherwise) {
		this.otherwise = otherwise;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
