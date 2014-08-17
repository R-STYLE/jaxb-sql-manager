package com.rstyles.util.sql;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Insert extends Clause implements Statement {

	private Type type;

	private String table;

	private Clause columns;

	private Clause values;

	@XmlAttribute
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@XmlElement(name = "into")
	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	@XmlElement
	public Clause getColumns() {
		return columns;
	}

	public void setColumns(Clause columns) {
		this.columns = columns;
	}

	@XmlElementRefs({
			@XmlElementRef(name = "values", type = Clause.class),
			@XmlElementRef(name = "select", type = Select.class)
	})
	public Clause getValues() {
		return values;
	}

	public void setValues(Clause values) {
		this.values = values;
	}

}
