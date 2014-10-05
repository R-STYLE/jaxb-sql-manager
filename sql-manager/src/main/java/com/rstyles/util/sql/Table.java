package com.rstyles.util.sql;

import javax.script.ScriptEngine;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Table extends Clause implements ITable {

	@Override
	public String convert(SqlGenerator generator, ScriptEngine engine) {
		return generator.generate(this, engine);
	}

}
