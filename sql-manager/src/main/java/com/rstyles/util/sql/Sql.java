package com.rstyles.util.sql;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlID;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Sql {

	private String id;

	private Statement statement;

	@XmlID
	@XmlAttribute(name = "id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@XmlAnyElement
	@XmlElementRefs({
			@XmlElementRef(name = "insert", type = Insert.class),
			@XmlElementRef(name = "select", type = Select.class),
			@XmlElementRef(name = "update", type = Update.class),
			@XmlElementRef(name = "delete", type = Delete.class)
	})
	public Statement getStatement() {
		return statement;
	}

	public void setStatement(Statement statement) {
		this.statement = statement;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
