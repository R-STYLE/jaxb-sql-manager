package com.rstyles.util.sql;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class SqlManager {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SqlManager.class);

	private static final SqlManager INSTANCE = new SqlManager();

	private static final ConcurrentMap<String, SqlManager> POOL = new ConcurrentHashMap<>();

	private final SqlGenerator generator;

	private SqlManager() {
		this(new DefaultSqlGenerator());
	}

	private SqlManager(final SqlGenerator generator) {
		this.generator = generator;
	}

	public static SqlManager getManager() {
		return SqlManager.INSTANCE;
	}

	public static SqlManager getManager(final SqlGenerator generator) {
		if (generator == null) {
			return SqlManager.INSTANCE;
		}
		final String key = generator.getClass().getName();
		if (POOL.containsKey(key)) {
			return POOL.get(key);
		}
		final SqlManager created = new SqlManager(generator);
		final SqlManager anotherCached = POOL.putIfAbsent(key, created);
		if (anotherCached != null) {
			return anotherCached;
		}
		return created;

	}

	public String getSql(final Class<?> clazz, final String id, final Map<String, Object> params) throws IOException {
		final SqlContainer container = SqlRegistry.load(clazz);
		return this.getSql(container, id, params).convert(generator, params);
	}

	@SuppressWarnings("unchecked")
	protected <T extends Sql> T getSql(final SqlContainer container, final String id, final Map<String, Object> params) throws FileNotFoundException {
		return (T) CollectionUtils.find(container.getSqls(), new Predicate() {
			@Override
			public boolean evaluate(final Object element) {
				return id.equals(((Sql) element).getId());
			}
		});
	}

}
