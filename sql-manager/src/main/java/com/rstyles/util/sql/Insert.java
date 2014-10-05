package com.rstyles.util.sql;

import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Insert extends Statement implements IStatement {

	private String table;

	private SimpleClause columns;

	private IValues values;

	@XmlElement(name = "into")
	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	@XmlElement
	public SimpleClause getColumns() {
		return columns;
	}

	public void setColumns(SimpleClause columns) {
		this.columns = columns;
	}

	@XmlElementRefs({
			@XmlElementRef(name = "values", type = Values.class),
			@XmlElementRef(name = "select", type = SelectClause.class)
	})
	public IValues getValues() {
		return values;
	}

	public void setValues(IValues values) {
		this.values = values;
	}

	@Override
	public String convert(SqlGenerator generator, Map<String, Object> params) {
		return generator.generate(this, params);
	}

}
