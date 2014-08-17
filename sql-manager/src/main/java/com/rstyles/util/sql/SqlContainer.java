package com.rstyles.util.sql;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@XmlRootElement(name = "sql-container")
public class SqlContainer {

	private List<Sql> sqls = new ArrayList<>();

	@XmlElement(name = "sql")
	public List<Sql> getSqls() {
		return sqls;
	}

	public void setSqls(List<Sql> sqls) {
		this.sqls = sqls;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
