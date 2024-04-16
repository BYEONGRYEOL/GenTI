package com.gt.genti.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PictureGenerateRequestRequestDto {

	String prompt;
	String posePictureUrl;
	String cameraAngle;
	String shotCoverage;

	@Builder
	public PictureGenerateRequestRequestDto(String prompt, String posePictureUrl, String cameraAngle,
		String shotCoverage) {
		this.prompt = prompt;
		this.posePictureUrl = posePictureUrl;
		this.cameraAngle = cameraAngle;
		this.shotCoverage = shotCoverage;
	}
}
