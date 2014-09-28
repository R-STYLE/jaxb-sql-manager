package com.rstyles.util.sql;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.xml.bind.JAXB;

class SqlRegistry {

	private static ConcurrentMap<String, SqlContainer> CACHE = new ConcurrentHashMap<>();

	private SqlRegistry() {
	}

	static SqlContainer load(final Class<?> clazz) throws FileNotFoundException {

		if (clazz == null) {
			// TODO: message.
			throw new IllegalArgumentException();
		}

		final String resourcePath = toResourcePath(clazz);

		if (CACHE.containsKey(resourcePath)) {
			return CACHE.get(resourcePath);
		}

		final SqlContainer loaded = load(clazz, resourcePath);
		final SqlContainer anotherCached = CACHE.putIfAbsent(resourcePath, loaded);
		if (anotherCached != null) {
			return anotherCached;
		}
		return loaded;
	}

	private static SqlContainer load(final Class<?> clazz, final String resourcePath) throws FileNotFoundException {

		final InputStream is = clazz.getClassLoader().getResourceAsStream(resourcePath);
		if (is == null) {
			// TODO: message.
			throw new FileNotFoundException();
		}

		final SqlContainer current = JAXB.unmarshal(is, SqlContainer.class);
		if (current == null) {
			// TODO: message.
			throw new IllegalStateException();
		}

		return current;
	}

	private static String toResourcePath(Class<?> clazz) {
		return clazz.getCanonicalName().replaceAll("\\.", "/") + ".xml";
	}

}
