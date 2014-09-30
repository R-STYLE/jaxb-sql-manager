package com.rstyles.util.sql;

import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Insert extends Clause implements Statement {

	private String table;

	private Clause columns;

	private Clause values;

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

	@Override
	public String convert(SqlGenerator generator, Map<String, Object> params) {
		return generator.generate(this, params);
	}

}
