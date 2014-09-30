package com.rstyles.util.sql;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.xml.bind.JAXB;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class SqlRegistry {

	private static final Logger LOGGER = LoggerFactory.getLogger(SqlRegistry.class);

	private static ConcurrentMap<String, SqlContainer> CACHE = new ConcurrentHashMap<>();

	private SqlRegistry() {
	}

	static SqlContainer load(final Class<?> clazz) throws IOException {
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

	private static SqlContainer load(final Class<?> clazz, final String resourcePath) throws IOException {

		final InputStream is = clazz.getClassLoader().getResourceAsStream(resourcePath);
		if (is == null) {
			// TODO: message.
			throw new FileNotFoundException();
		}
		final String xml = IOUtils.toString(is);
		
		final SqlContainer current = JAXB.unmarshal(xml, SqlContainer.class);
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
