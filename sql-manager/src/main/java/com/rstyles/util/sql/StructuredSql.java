package com.rstyles.util.sql;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "sql")
public class StructuredSql extends Sql implements ISql {

	private Statement statement;

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

}
