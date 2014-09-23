package com.rstyles.util.sql;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@XmlRootElement(name = "sql-container")
public class SqlContainer {

	private List<ISql> sqls = new ArrayList<>();

//	@XmlAnyElement
//	@XmlElementRefs({
//			@XmlElementRef(name = "sql", type = StructuredSql.class)
//			,@XmlElementRef(name = "plain-sql", type = PlainSql.class)
//	})
	@XmlElements({
		@XmlElement(name = "sql", type = StructuredSql.class)
		,@XmlElement(name = "plain-sql", type = PlainSql.class)
	})
	public List<ISql> getSqls() {
		return sqls;
	}

	public void setSqls(List<ISql> sqls) {
		this.sqls = sqls;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
