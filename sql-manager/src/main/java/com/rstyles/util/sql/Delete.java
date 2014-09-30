package com.rstyles.util.sql;

import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Delete extends Clause implements Statement {

	private String table;

	private Clause where;

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

	@Override
	public String convert(SqlGenerator generator, Map<String, Object> params) {
		return generator.generate(this, params);
	}

}
