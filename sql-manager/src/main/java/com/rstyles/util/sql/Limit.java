package com.rstyles.util.sql;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Limit {

	private Long offset;

	private Long rows;

	private String condition;

	private List<Conditional> conditions = new ArrayList<>();

	@XmlElement(name = "offset")
	public Long getOffset() {
		return offset;
	}

	public void setOffset(Long offset) {
		this.offset = offset;
	}

	@XmlElement(name = "rows")
	public Long getRows() {
		return rows;
	}

	public void setRows(Long rows) {
		this.rows = rows;
	}

	@XmlAttribute(name = "if")
	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	@XmlElement(name = "if")
	public List<Conditional> getConditions() {
		return conditions;
	}

	public void setConditions(List<Conditional> conditions) {
		this.conditions = conditions;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
