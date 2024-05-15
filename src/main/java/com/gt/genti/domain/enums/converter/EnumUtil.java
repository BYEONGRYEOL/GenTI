package com.gt.genti.domain.enums.converter;

import org.thymeleaf.util.StringUtils;

import com.gt.genti.domain.enums.ConvertableEnum;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EnumUtil {
	public static final String NULL = "NONE";

	private static <E extends Enum<E> & ConvertableEnum> E convertNullToEnum(Class<E> enumType) {
		for (E enumValue : enumType.getEnumConstants()) {
			if (enumValue.name().equals(NULL)) {
				return enumValue;
			}
		}
		throw new RuntimeException("""
			enum type : %s은 null 값을 허용하지 않습니다.
			""".formatted(enumType));
	}

	public static <E extends Enum<E> & ConvertableEnum> E stringToEnum(Class<E> enumType, String value) {
		for (E enumValue : enumType.getEnumConstants()) {
			if (StringUtils.equals(value, enumValue.getStringValue())) {
				return enumValue;
			}
		}

		try {
			return convertNullToEnum(enumType);
		} catch (Exception e) {
			throw new RuntimeException("""
				DB -> ENUM 값 불러오기 실패  enum : %s value :  %s \n%s
				""".formatted(enumType.getName(), value, e.getMessage()));
		}
	}

	public static <E extends Enum<E> & ConvertableEnum> E stringToEnumIgnoreCase(Class<E> enumType, String value) {
		for (E enumValue : enumType.getEnumConstants()) {
			// log.info("enumValue.name() : " + enumValue.name());
			// log.info("enumValue.getStringValue() : " + enumValue.getStringValue());
			// log.info("value : " + value);
			if (StringUtils.equalsIgnoreCase(value, enumValue.getStringValue())) {
				return enumValue;
			}
		}

		try {
			return convertNullToEnum(enumType);
		} catch (Exception e) {
			throw new RuntimeException("""
				DB -> ENUM 값 불러오기 실패  enum : %s value :  %s \n%s
				""".formatted(enumType.getName(), value, e.getMessage()));
		}
	}
}
