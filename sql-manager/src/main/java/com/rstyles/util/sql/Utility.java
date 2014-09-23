package com.rstyles.util.sql;

import java.util.Map;

public final class Utility {
	
	private Utility() {
	}
	
	public static boolean isEmpty(final String src) {
		return src == null || src.isEmpty();
	}
	
	public static boolean isNotEmpty(final String src) {
		return ! isEmpty(src);
	}
	
	public static boolean isEmpty(Map<?,?> map) {
		return map == null || map.isEmpty();
	}
	
}
