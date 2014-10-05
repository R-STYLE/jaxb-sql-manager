package com.rstyles.util.sql;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class GeneratorUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(GeneratorUtil.class);

	static final ScriptEngineManager SCRIPT_MANAGER = new ScriptEngineManager();

	static final String SCRIPT_KIND = "javascript";

	static final String SEPARATOR_SPACE = " ";

	private GeneratorUtil() {
	}

	public static String generateConditional(final Conditional condition, final ScriptEngine engine) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("condition: " + condition);
		}
		if (condition == null) {
			return StringUtils.EMPTY;
		}
		final List<String> contexts = condition.getContexts();
		if (contexts == null || contexts.isEmpty()) {
			return StringUtils.EMPTY;
		}

		final StringBuilder builder = new StringBuilder();

		if (isEnabled(condition.getExpression(), engine)) {
			for (String context : contexts) {
				builder.append(SEPARATOR_SPACE);
				builder.append(StringUtils.trim(context));
			}
		} else {
			builder.append(SEPARATOR_SPACE);
			builder.append(condition.getOtherwise());
		}
		return builder.toString();
	}

	static boolean isDisabled(final Clause clause, final ScriptEngine engine) {
		return !isEnabled(clause, engine);
	}

	static boolean isEnabled(final Clause clause, final ScriptEngine engine) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("clause: " + clause);
		}
		if (clause == null) {
			return false;
		}
		return isEnabled(clause.getIfCondition(), engine);
	}

	static boolean isDisabled(final String condition, final ScriptEngine engine) {
		return !isEnabled(condition, engine);
	}

	static boolean isEnabled(final String condition, final ScriptEngine engine) {
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
				LOGGER.error("Invalid Script [ " + condition + " ] script kind is [ " + engine.getFactory().getEngineName() + " ]", e);
			}
			throw new IllegalFormatException(e);
		}
		if (ret instanceof Boolean) {
			return ((Boolean) ret).booleanValue();
		}
		// FIXME: true?
		return false;
	}

	static String trimedJoin(Iterable<String> src) {
		if (src == null) {
			return StringUtils.EMPTY;
		}
		final StringBuilder builder = new StringBuilder();
		for (String s : src) {
			builder.append(StringUtils.trim(s));
		}
		return builder.toString();
	}

	static ScriptEngine prepareDefaultScriptEngine(final Map<String, Object> params) {
		return prepareScriptEngine(SCRIPT_KIND, params);
	}

	static ScriptEngine prepareScriptEngine(final String kind, final Map<String, Object> params) {
		final ScriptEngine engine = SCRIPT_MANAGER.getEngineByName(kind);
		assignParams(engine, params);
		return engine;
	}

	static ScriptEngine assignParams(final ScriptEngine engine, final Map<String, Object> params) {
		if (params == null || params.isEmpty()) {
			return engine;
		}
		for (final Entry<String, Object> param : params.entrySet()) {
			engine.put(param.getKey(), param.getValue());
		}
		return engine;
	}

}
