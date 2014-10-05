package com.rstyles.util.sql;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.script.ScriptEngine;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class DefaultSqlGenerator implements SqlGenerator {

	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultSqlGenerator.class);

	public DefaultSqlGenerator() {
	}

	@Override
	public String generate(final Insert insert, final Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String generate(final Select select, final Map<String, Object> params) {

		final List<SimpleClause> columns = select.getColumns();
		if (columns == null || columns.isEmpty()) {
			return GeneratorUtil.trimedJoin(select.getContexts());
		}

		final ScriptEngine engine = GeneratorUtil.prepareDefaultScriptEngine(params);

		final StringBuilder builder = new StringBuilder("SELECT");

		final Boolean distinct = select.getDistinct();
		if (distinct != null && distinct.booleanValue()) {
			builder.append(GeneratorUtil.SEPARATOR_SPACE);
			builder.append("DISTINCT");
		}
		
		builder.append(GeneratorUtil.SEPARATOR_SPACE);
		for (SimpleClause column : columns) {
			builder.append(column.convert(this, engine));
		}

		builder.append(GeneratorUtil.SEPARATOR_SPACE);
		builder.append(select.getFrom().convert(this, engine));

		builder.append(GeneratorUtil.SEPARATOR_SPACE);
		builder.append(select.getWhere().convert(this, engine));

		final String groupby = this.generateSeparated(select.getGroupby(), engine, GeneratorUtil.SEPARATOR_COMMA);
		if (StringUtils.isNotEmpty(groupby)) {
			builder.append(GeneratorUtil.SEPARATOR_SPACE);
			builder.append("GROUP BY");
			builder.append(GeneratorUtil.SEPARATOR_SPACE);
			builder.append(groupby);
		}

		final String having = this.generateSeparated(select.getHaving(), engine, GeneratorUtil.SEPARATOR_COMMA);
		if (StringUtils.isNotEmpty(having)) {
			builder.append(GeneratorUtil.SEPARATOR_SPACE);
			builder.append("HAVING");
			builder.append(GeneratorUtil.SEPARATOR_SPACE);
			builder.append(having);
		}

		final String orderby = this.generateSeparated(select.getOrderby(), engine, GeneratorUtil.SEPARATOR_COMMA);
		if (StringUtils.isNotEmpty(orderby)) {
			builder.append(GeneratorUtil.SEPARATOR_SPACE);
			builder.append("ORDER BY");
			builder.append(GeneratorUtil.SEPARATOR_SPACE);
			builder.append(orderby);
		}

		return builder.toString();
	}

	@Override
	public String generate(final Update update, final Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String generate(final Delete delete, final Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String generate(SimpleClause clause, ScriptEngine engine) {

		if (GeneratorUtil.isDisabled(clause, engine)) {
			return StringUtils.EMPTY;
		}

		final LinkedList<Conditional> conditions = new LinkedList<>(clause.getConditionals());
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("conditions: " + conditions.size());
		}

		final List<String> contexts = clause.getContexts();
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("contexts  : " + contexts.size());
			for (String context : contexts) {
				LOGGER.debug("context   : " + StringUtils.trim(context));
			}
		}
		if (conditions == null || conditions.isEmpty()) {
			return GeneratorUtil.trimedJoin(contexts);
		}

		final StringBuilder builder = new StringBuilder();

		for (String context : contexts) {
			final String trimed = StringUtils.trim(context);
			if (StringUtils.isEmpty(trimed)) {
				builder.append(GeneratorUtil.generateConditional(conditions.pop(), engine));
				continue;
			}
			builder.append(trimed);
		}
		return builder.toString();
	}

	@Override
	public String generate(Values values, ScriptEngine engine) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String generate(SelectClause select, ScriptEngine engine) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String generate(Conditions conditions, ScriptEngine engine, List<String> clauses) {
		
		if (GeneratorUtil.isDisabled(conditions, engine)) {
			return StringUtils.EMPTY;
		}

		final LinkedList<Conditional> conditionals = new LinkedList<>(conditions.getConditionals());
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("conditions: " + conditionals.size());
		}
		final List<String> contexts = conditions.getContexts();
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("contexts  : " + contexts.size());
			for (String context : contexts) {
				LOGGER.debug("context   : " + StringUtils.trim(context));
			}
		}
		if (conditionals == null || conditionals.isEmpty()) {
			return GeneratorUtil.trimedJoin(contexts);
		}
		final StringBuilder builder = new StringBuilder();
		for (String context : contexts) {
			final String trimed = StringUtils.trim(context);
			if (StringUtils.isEmpty(trimed)) {
				builder.append(GeneratorUtil.generateConditional(conditionals.pop(), engine));
				continue;
			}
			builder.append(trimed);
		}
		return builder.toString();
	}

	@Override
	public String generate(Connector connector, ScriptEngine engine, List<String> clauses) {

		if (GeneratorUtil.isDisabled(connector, engine)) {
			return StringUtils.EMPTY;
		}

		final StringBuilder builder = new StringBuilder();
		if (clauses != null && !clauses.isEmpty()) {
			builder.append(connector.getType());
		}

		final List<IConditions> conditions = connector.getConditions();
		if (conditions == null || conditions.isEmpty()) {
			builder.append(GeneratorUtil.SEPARATOR_SPACE);
			builder.append(GeneratorUtil.trimedJoin(connector.getContexts()));
			return builder.toString();
		}

		final List<String> connectClauses = new ArrayList<>();
		for (IConditions condition : conditions) {
			final String clause = condition.convert(this, engine, connectClauses);
			if (StringUtils.isEmpty(clause)) {
				continue;
			}
			connectClauses.add(clause);
		}
		builder.append(GeneratorUtil.SEPARATOR_SPACE);
		builder.append("(");
		builder.append(StringUtils.join(connectClauses, GeneratorUtil.SEPARATOR_SPACE));
		builder.append(")");
		return builder.toString();
	}

	@Override
	public String generate(Where where, ScriptEngine engine) {

		if (GeneratorUtil.isDisabled(where, engine)) {
			return StringUtils.EMPTY;
		}

		final StringBuilder builder = new StringBuilder("WHERE");

		final List<IConditions> conditions = where.getConditions();
		if (conditions == null || conditions.isEmpty()) {
			builder.append(GeneratorUtil.SEPARATOR_SPACE);
			builder.append(GeneratorUtil.trimedJoin(where.getContexts()));
			return builder.toString();
		}

		final List<String> generatedClauses = new ArrayList<>();
		for (IConditions condition : conditions) {
			final String generated = condition.convert(this, engine, generatedClauses);
			if (StringUtils.isEmpty(generated)) {
				continue;
			}
			generatedClauses.add(generated);
		}
		builder.append(GeneratorUtil.SEPARATOR_SPACE);
		builder.append(StringUtils.join(generatedClauses, GeneratorUtil.SEPARATOR_SPACE));
		return builder.toString();
	}

	@Override
	public String generate(Table table, ScriptEngine engine) {
		if (GeneratorUtil.isDisabled(table, engine)) {
			return StringUtils.EMPTY;
		}
		return GeneratorUtil.trimedJoin(table.getContexts());
	}

	@Override
	public String generate(From from, ScriptEngine engine) {

		if (from == null) {
			return StringUtils.EMPTY;
		}

		final StringBuilder builder = new StringBuilder("FROM");

		final List<ITable> tables = from.getTables();
		if (tables == null || tables.isEmpty()) {
			builder.append(GeneratorUtil.SEPARATOR_SPACE);
			builder.append(GeneratorUtil.trimedJoin(from.getContexts()));
			return builder.toString();
		}
		for (ITable table : tables) {
			builder.append(GeneratorUtil.SEPARATOR_SPACE);
			builder.append(table.convert(this, engine));
		}
		return builder.toString();
	}

	@Override
	public String generate(Join join, ScriptEngine engine) {

		if (GeneratorUtil.isDisabled(join, engine)) {
			return StringUtils.EMPTY;
		}

		final StringBuilder builder = new StringBuilder();
		final String type = join.getType();
		if (StringUtils.isNotEmpty(type)) {
			builder.append(StringUtils.upperCase(type));
			builder.append(GeneratorUtil.SEPARATOR_SPACE);
		}
		builder.append("JOIN");
		builder.append(GeneratorUtil.SEPARATOR_SPACE);
		builder.append(GeneratorUtil.trimedJoin(join.getContexts()));

		final IClause conditionClause = join.getCondition();
		final String condition = conditionClause == null ? "" : conditionClause.convert(this, engine);
		if (StringUtils.isNotEmpty(condition)) {
			builder.append(GeneratorUtil.SEPARATOR_SPACE);
			builder.append(condition);
			return builder.toString();
		}

		final List<IConditions> conditions = join.getConditions();
		if (conditions == null || conditions.isEmpty()) {
			return builder.toString();
		}

		final List<String> clauses = new ArrayList<>();

		for (IConditions cond : conditions) {
			final String clause = cond.convert(this, engine, clauses);
			if (StringUtils.isEmpty(clause)) {
				continue;
			}
			clauses.add(clause);
		}
		builder.append(GeneratorUtil.SEPARATOR_SPACE);
		builder.append(StringUtils.join(clauses, GeneratorUtil.SEPARATOR_SPACE));
		return builder.toString();
	}

	@Override
	public String generate(On on, ScriptEngine engine) {

		if (GeneratorUtil.isDisabled(on, engine)) {
			return StringUtils.EMPTY;
		}

		final StringBuilder builder = new StringBuilder("ON");

		final List<IConditions> conditions = on.getConditions();
		if (conditions == null || conditions.isEmpty()) {
			builder.append(GeneratorUtil.SEPARATOR_SPACE);
			builder.append(GeneratorUtil.trimedJoin(on.getContexts()));
			return builder.toString();
		}

		final List<String> onClauses = new ArrayList<>();
		for (IConditions condition : conditions) {
			final String clause = condition.convert(this, engine, onClauses);
			if (StringUtils.isEmpty(clause)) {
				continue;
			}
			onClauses.add(clause);
		}
		builder.append(GeneratorUtil.SEPARATOR_SPACE);
		builder.append(StringUtils.join(onClauses, GeneratorUtil.SEPARATOR_SPACE));
		return builder.toString();
	}

	@Override
	public String generate(Using using, ScriptEngine engine) {

		if (using == null) {
			return StringUtils.EMPTY;
		}

		final StringBuilder builder = new StringBuilder("USING");

		final List<SimpleClause> columns = using.getColumns();
		if (columns == null || columns.isEmpty()) {
			builder.append(GeneratorUtil.SEPARATOR_SPACE);
			builder.append(GeneratorUtil.trimedJoin(using.getContexts()));
			return builder.toString();
		}
		for (SimpleClause column : columns) {
			builder.append(column.convert(this, engine));
		}
		return builder.toString();
	}

	@Override
	public String generate(Set set, ScriptEngine engine) {
		// TODO Auto-generated method stub
		return null;
	}

	private String generateSeparated(List<SimpleClause> clauses, ScriptEngine engine, String separator) {
		if (clauses == null || clauses.isEmpty()) {
			return StringUtils.EMPTY;
		}
		final List<String> generatedClauses = new ArrayList<>();
		for (SimpleClause clause : clauses) {
			final String generated = clause.convert(this, engine);
			if (StringUtils.isEmpty(generated)) {
				continue;
			}
			generatedClauses.add(generated);
		}
		return StringUtils.join(generatedClauses, separator);
	}

}
