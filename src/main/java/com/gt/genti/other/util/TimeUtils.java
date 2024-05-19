package com.gt.genti.other.util;

import java.time.Duration;

import com.gt.genti.error.ErrorCode;
import com.gt.genti.error.ExpectedException;

public class TimeUtils {
	public static Long ACCEPTABLE_TIME_MINUTE = 15L;
	public static Long PGRES_LIMIT_HOUR = 4L;

	//소요시간 기준 1시간 이내 2,500원, 2시간 이내 2,000원 4시간 이내 1,000원
	public static Long[][] TIME_REWARD_MAPPER = {
		{60 * 1L, 2500L},
		{60 * 2L, 2000L},
		{60 * 4L, 1000L}
	};

	public static String getTimeString(Duration duration) {
		return String.format("%02d:%02d:%02d", duration.toHours(), duration.toMinutesPart(),
			duration.toSecondsPart());
	}

	public static Long calculateReward(Long elapsedMinutes) {
		for (Long[] timeReward : TIME_REWARD_MAPPER) {
			if (elapsedMinutes <= timeReward[0]) {
				return timeReward[1];
			}
		}
		throw new ExpectedException(ErrorCode.Undefined);
	}
}