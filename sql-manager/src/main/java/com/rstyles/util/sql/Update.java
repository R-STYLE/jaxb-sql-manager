package com.rstyles.util.sql;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "update")
public class Update extends Clause implements Statement {

	private String table;

	private Clause set;

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
