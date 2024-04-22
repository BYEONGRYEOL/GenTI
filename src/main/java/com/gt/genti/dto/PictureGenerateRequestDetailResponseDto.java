package com.gt.genti.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.gt.genti.domain.PictureGenerateRequest;
import com.gt.genti.domain.UserFacePicture;
import com.gt.genti.domain.enums.CameraAngle;
import com.gt.genti.domain.enums.RequestStatus;
import com.gt.genti.domain.enums.ShotCoverage;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PictureGenerateRequestDetailResponseDto {
	Long id;
	Long requesterId;
	String prompt;
	List<String> facePictureUrlList;
	Long posePictureId;
	String posePictureUrl;
	CameraAngle cameraAngle;
	ShotCoverage shotCoverage;
	RequestStatus requestStatus;
	LocalDateTime createdAt;

	@Builder
	public PictureGenerateRequestDetailResponseDto(PictureGenerateRequest pictureGenerateRequest) {
		this.id = pictureGenerateRequest.getId();
		this.requesterId = pictureGenerateRequest.getRequester().getId();
		this.prompt = pictureGenerateRequest.getPrompt();
		this.facePictureUrlList = pictureGenerateRequest.getRequester().getUserFacePictureList().stream().map(
			UserFacePicture::getUrl).toList();
		this.posePictureId = pictureGenerateRequest.getPosePicture().getId();
		this.posePictureUrl = pictureGenerateRequest.getPosePicture().getUrl();
		this.cameraAngle = pictureGenerateRequest.getCameraAngle();
		this.shotCoverage = pictureGenerateRequest.getShotCoverage();
		this.requestStatus = pictureGenerateRequest.getRequestStatus();
		this.createdAt = pictureGenerateRequest.getCreatedAt();
	}
}