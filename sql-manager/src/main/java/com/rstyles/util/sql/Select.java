package com.rstyles.util.sql;

import java.util.Map;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Select extends Clause implements Statement {

	private Clause columns;
	private Clause from;
	private Clause where;
	private Clause orderby;
	private Clause groupby;
	private Limit limit;

	private Boolean forupdate;

	@XmlElement
	public Clause getColumns() {
		return columns;
	}

	public void setColumns(Clause columns) {
		this.columns = columns;
	}

	@XmlElement
	public Clause getFrom() {
		return from;
	}

	public void setFrom(Clause from) {
		this.from = from;
	}

	@XmlElement
	public Clause getWhere() {
		return where;
	}

	public void setWhere(Clause where) {
		this.where = where;
	}

	@XmlElement
	public Clause getOrderby() {
		return orderby;
	}

	public void setOrderby(Clause orderby) {
		this.orderby = orderby;
	}

	@XmlElement
	public Clause getGroupby() {
		return groupby;
	}

	public void setGroupby(Clause groupby) {
		this.groupby = groupby;
	}

	@XmlElement
	public Limit getLimit() {
		return limit;
	}

	public void setLimit(Limit limit) {
		this.limit = limit;
	}

	@XmlAttribute
	public Boolean getForupdate() {
		return forupdate;
	}

	public void setForupdate(Boolean forupdate) {
		this.forupdate = forupdate;
	}

	@Override
	public String convert(SqlGenerator generator, Map<String, Object> params) {
		return generator.generate(this, params);
	}

}
