package com.rstyles.util.sql;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Select extends Statement implements IStatement {

	private List<SimpleClause> columns = new ArrayList<>();
	private From from;
	private Where where;
	private List<SimpleClause> groupby = new ArrayList<>();
	private List<SimpleClause> having = new ArrayList<>();
	private List<SimpleClause> orderby = new ArrayList<>();
	private Limit limit;

	private Boolean distinct;
	private Boolean forupdate;

	@XmlElement
	public List<SimpleClause> getColumns() {
		return columns;
	}

	public void setColumns(List<SimpleClause> columns) {
		this.columns = columns;
	}

	@XmlElement
	public From getFrom() {
		return from;
	}

	public void setFrom(From from) {
		this.from = from;
	}

	@XmlElement
	public Where getWhere() {
		return where;
	}

	public void setWhere(Where where) {
		this.where = where;
	}

	@XmlElement
	public List<SimpleClause> getGroupby() {
		return groupby;
	}

	public void setGroupby(List<SimpleClause> groupby) {
		this.groupby = groupby;
	}
	
	@XmlElement
	public List<SimpleClause> getHaving() {
		return having;
	}

	public void setHaving(List<SimpleClause> having) {
		this.having = having;
	}
	
	@XmlElement
	public List<SimpleClause> getOrderby() {
		return orderby;
	}

	public void setOrderby(List<SimpleClause> orderby) {
		this.orderby = orderby;
	}

	@XmlElement
	public Limit getLimit() {
		return limit;
	}

	public void setLimit(Limit limit) {
		this.limit = limit;
	}
	
	@XmlAttribute
	public Boolean getDistinct() {
		return distinct;
	}

	public void setDistinct(Boolean distinct) {
		this.distinct = distinct;
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
