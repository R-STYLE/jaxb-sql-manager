package com.rstyles.util.sql;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Update extends Clause implements Statement {

	private Type type;

	private String table;

	private Clause set;

	@XmlAttribute
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@XmlElement
	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	@XmlElement
	public Clause getSet() {
		return set;
	}

	public void setSet(Clause set) {
		this.set = set;
	}

}
