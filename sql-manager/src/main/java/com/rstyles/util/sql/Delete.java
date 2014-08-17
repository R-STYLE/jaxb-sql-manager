package com.rstyles.util.sql;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Delete extends Clause implements Statement {
	
	private Type type;

	private String table;
	
	private Clause where;
	
	@XmlAttribute
	public Type getType() {
		return type;
	}
	
	public void setType(Type type) {
		this.type = type;
	}

	@XmlElement(name = "from")
	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	@XmlElement
	public Clause getWhere() {
		return where;
	}

	public void setWhere(Clause where) {
		this.where = where;
	}

}
