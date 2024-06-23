package com.gt.genti.domain.enums.converter.db;

import static com.gt.genti.error.ResponseCode.*;

import java.util.List;

import org.apache.commons.codec.binary.StringUtils;

import com.gt.genti.domain.enums.ConvertableEnum;
import com.gt.genti.domain.enums.PictureGenerateRequestStatus;
import com.gt.genti.domain.enums.PictureGenerateResponseStatus;
import com.gt.genti.error.ExpectedException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EnumUtil {

	public static List<PictureGenerateResponseStatus> PICTURE_AVAILABLE_STATUS_LIST = List.of(
		PictureGenerateResponseStatus.COMPLETED, PictureGenerateResponseStatus.SUBMITTED_FINAL);

	public static List<PictureGenerateRequestStatus> PGREQ_STATUS_PENDING =
		List.of(
			PictureGenerateRequestStatus.CREATED,
			PictureGenerateRequestStatus.ASSIGNING,
			PictureGenerateRequestStatus.IN_PROGRESS,
			PictureGenerateRequestStatus.MATCH_TO_ADMIN
			);

	public static boolean canUserSeePicture(PictureGenerateResponseStatus status) {
		return PICTURE_AVAILABLE_STATUS_LIST.contains(status);
	}

	public static <E extends Enum<E> & ConvertableEnum> E stringToEnum(Class<E> enumType, String value) {
		return toEnum(enumType, value, false);
	}

	public static <E extends Enum<E> & ConvertableEnum> E stringToEnumIgnoreCase(Class<E> enumType, String value) {
		return toEnum(enumType, value, true);
	}

	private static <E extends Enum<E> & ConvertableEnum> E toEnum(Class<E> enumType, String value, boolean ignoreCase) {
		if (value == null) {
			E enumNullValue = enumType.getEnumConstants()[0].getNullValue();
			if (enumNullValue == null) {
				throw ExpectedException.withLogging(NotNullableEnum, enumType);
			} else {
				return enumNullValue;
			}
		}
		for (E enumValue : enumType.getEnumConstants()) {
			if (ignoreCase) {
				if (enumValue.getStringValue().equalsIgnoreCase(value)) {
					return enumValue;
				}
			} else {
				if (StringUtils.equals(value, enumValue.getStringValue())) {
					return enumValue;
				}
			}
		}

		throw ExpectedException.withLogging(DBToEnumFailed, enumType.getSimpleName(), value);
	}

}