package com.gt.genti.adapter.in.web;

import static com.gt.genti.other.util.ApiUtils.*;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gt.genti.application.service.PictureGenerateWorkService;
import com.gt.genti.domain.enums.PictureGenerateRequestStatus;
import com.gt.genti.dto.PictureGenerateRequestBriefResponseDto;
import com.gt.genti.dto.PictureGenerateRequestDetailResponseDto;
import com.gt.genti.external.aws.dto.PreSignedUrlRequestDto;
import com.gt.genti.external.aws.dto.PreSignedUrlResponseDto;
import com.gt.genti.other.auth.UserDetailsImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/creators")
@RequiredArgsConstructor
public class PictureGenerateWorkController {
	private final PictureGenerateWorkService pictureGenerateWorkService;

	@GetMapping("/picture-generate-requests")
	public ResponseEntity<ApiResult<PictureGenerateRequestBriefResponseDto>> getAssignedPictureGenerateRequestBrief(
		@AuthenticationPrincipal
		UserDetailsImpl userDetails, @RequestParam(value = "pictureGenerateRequestStatus", defaultValue = "BEFORE_WORK") PictureGenerateRequestStatus pictureGenerateRequestStatus) {
		return success(pictureGenerateWorkService.getPictureGenerateRequestBrief(
			userDetails.getId(), pictureGenerateRequestStatus));
	}

	@GetMapping("/picture-generate-requests/all")
	public ResponseEntity<ApiResult<List<PictureGenerateRequestDetailResponseDto>>> getAssignedPictureGenerateRequestsAll(
		@AuthenticationPrincipal
		UserDetailsImpl userDetails) {
		return success(pictureGenerateWorkService.getPictureGenerateRequestDetailAll(
			userDetails.getId()));
	}

	@GetMapping("/picture-generate-response/{pictureGenerateResponseId}/upload-url")
	public ResponseEntity<ApiResult<List<PreSignedUrlResponseDto>>> getPictureUploadUrl(
		@PathVariable Long pictureGenerateResponseId,
		@RequestBody List<PreSignedUrlRequestDto> preSignedUrlRequestDtoList
	) {
		return success(pictureGenerateWorkService.getUploadUrl(pictureGenerateResponseId, preSignedUrlRequestDtoList));
	}

	@GetMapping("/picture-generate-response/{pictureGenerateResponseId}/submit")
	public ResponseEntity<ApiResult<Boolean>> submitPictureGenerateResponse(
		@PathVariable Long pictureGenerateResponseId) {
		return success(pictureGenerateWorkService.submit(pictureGenerateResponseId));
	}

}
