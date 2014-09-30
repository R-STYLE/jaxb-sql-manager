package com.rstyles.util.sql;

import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class DefaultSqlGenerator implements SqlGenerator {

	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultSqlGenerator.class);

	private static final ScriptEngineManager MANAGER = new ScriptEngineManager();

	private static final String SCRIPT_KIND = "javascript";

	@Override
	public String generate(final Insert insert, final Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String generate(final Select select, final Map<String, Object> params) {

		final ScriptEngine engine = this.prepareScriptEngine(params);

		final Clause columns = select.getColumns();

		final StringBuilder builder = new StringBuilder("SELECT ");
		
		if (this.isEnabled(columns, engine)) {
			this.appendColumns(builder, columns, engine);
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

	private ScriptEngine prepareScriptEngine(final Map<String, Object> params) {
		final ScriptEngine engine = MANAGER.getEngineByName(SCRIPT_KIND);
		this.assignParams(engine, params);
		return engine;
	}

	private ScriptEngine assignParams(final ScriptEngine engine, final Map<String, Object> params) {
		if (params == null || params.isEmpty()) {
			return engine;
		}
		for (final Entry<String, Object> param : params.entrySet()) {
			engine.put(param.getKey(), param.getValue());
		}
		return engine;
	}
	
	private void appendColumns(final StringBuilder builder, final Clause columns, final ScriptEngine engine) {
		final LinkedList<Condition> conditions = new LinkedList<>(columns.getConditions());
		final LinkedList<String> contexts = new LinkedList<>(columns.getContexts());
		if (conditions == null || conditions.isEmpty()) {
			builder.append(contexts.getFirst().trim());
			return;
		}
		for (String context : contexts) {
			final String trimed = context.trim();
			if (StringUtils.isEmpty(trimed)) {
				builder.append(this.getConditional(conditions.pop(), engine));
				continue;
			}
			builder.append(trimed);
		}
	}
	
	private String getConditional(final Condition condition, final ScriptEngine engine) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("condition: " + condition);
		}
		if (condition == null) {
			return StringUtils.EMPTY;
		}
		final String context = condition.getContext();
		if (context == null || context.trim().isEmpty()) {
			return StringUtils.EMPTY;
		}
		if (this.isEnabled(condition.getExpression(), engine)) {
			return condition.getContext();
		}
		return StringUtils.EMPTY;
	}

	private boolean isEnabled(final Clause clause, final ScriptEngine engine) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("clause: " + clause);
		}
		if (clause == null) {
			return false;
		}
		return this.isEnabled(clause.getCondition(), engine);
	}

	private boolean isEnabled(final String condition, final ScriptEngine engine) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("if attr: " + condition);
		}
		if (condition == null || condition.trim().isEmpty()) {
			return true;
		}
		Object ret = null;
		try {
			ret = engine.eval(condition);
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("eval result: " + ret);
			}
		} catch (ScriptException e) {
			if (LOGGER.isErrorEnabled()) {
				LOGGER.error("Invalid Script [ " + condition + " ] script kind is [ " + SCRIPT_KIND + " ]", e);
			}
			throw new IllegalFormatException(e);
		}
		if  (ret instanceof Boolean) {
			return ((Boolean) ret).booleanValue();
		}
		// FIXME: true?
		return false;
	}

}
