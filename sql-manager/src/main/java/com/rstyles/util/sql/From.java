package com.rstyles.util.sql;

import java.util.ArrayList;
import java.util.List;

import javax.script.ScriptEngine;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class From extends Clause implements IClause {

	private List<ITable> tables = new ArrayList<>();

	@XmlElementRefs({
			@XmlElementRef(name = "table", type = Table.class),
			@XmlElementRef(name = "join", type = Join.class)
	})
	public List<ITable> getTables() {
		return tables;
	}

	public void setTables(List<ITable> tables) {
		this.tables = tables;
	}

	@Override
	public String convert(SqlGenerator generator, ScriptEngine engine) {
		return generator.generate(this, engine);
	}

}
