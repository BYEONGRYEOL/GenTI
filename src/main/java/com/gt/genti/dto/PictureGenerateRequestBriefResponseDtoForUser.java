package com.gt.genti.dto;

import java.time.LocalDateTime;

import com.gt.genti.domain.PictureGenerateRequest;
import com.gt.genti.domain.enums.CameraAngle;
import com.gt.genti.domain.enums.PictureGenerateRequestStatus;
import com.gt.genti.domain.enums.ShotCoverage;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PictureGenerateRequestBriefResponseDtoForUser {
	Long requestId;
	String prompt;
	CameraAngle cameraAngle;
	ShotCoverage shotCoverage;
	PictureGenerateRequestStatus status;
	// String remainTimeForFirstSubmit;
	// String remainTimeForFinalSubmit;
	LocalDateTime createdAt;

	public PictureGenerateRequestBriefResponseDtoForUser(PictureGenerateRequest pgreq) {
		this.requestId = pgreq.getId();
		this.prompt = pgreq.getPrompt();
		this.cameraAngle = pgreq.getCameraAngle();
		this.shotCoverage = pgreq.getShotCoverage();
		this.status = pgreq.getPictureGenerateRequestStatus();
		this.createdAt = pgreq.getCreatedAt();
		// Duration duration = Duration.between(LocalDateTime.now(),
		// 	createdAt.plusMinutes(TimeUtils.ACCEPTABLE_TIME_MINUTE));
		// this.remainTime = TimeUtils.getTimeString(duration);
	}
}
