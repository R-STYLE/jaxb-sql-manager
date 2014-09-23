package com.rstyles.util.sql;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "plain-sql")
public class PlainSql extends Sql {

	private String statement;

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

}
