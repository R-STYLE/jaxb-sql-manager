package com.rstyles.util.sql;

import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "update")
public class Update extends Statement implements IStatement {

	private String table;

	private Set set;

	@XmlElement
	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	@XmlElement
	public Set getSet() {
		return set;
	}

	public void setSet(Set set) {
		this.set = set;
	}

	@Override
	public String convert(SqlGenerator generator, Map<String, Object> params) {
		return generator.generate(this, params);
	}

}
