package com.rstyles.util.sql;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum(String.class)
public enum Type {
	@XmlEnumValue(value = "structured")
	STRUCTURED,
	@XmlEnumValue(value = "raw")
	RAW
}
