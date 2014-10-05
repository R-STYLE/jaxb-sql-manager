package com.rstyles.util.sql;

import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.StringUtils;

@XmlRootElement(name = "plain-sql")
public class PlainSql extends Sql {

	private String statement;

	@XmlElement(name = "statement")
	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	@Override
	public String convert(SqlGenerator generator, Map<String, Object> params) {
		if (StringUtils.isEmpty(this.statement)) {
			throw new IllegalFormatException();
		}
		return StringUtils.trim(this.statement);
	}

}
