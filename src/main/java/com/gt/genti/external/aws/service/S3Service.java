package com.gt.genti.external.aws.service;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.Headers;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.gt.genti.error.ErrorCode;
import com.gt.genti.error.ExpectedException;
import com.gt.genti.external.aws.dto.PreSignedUrlRequestDto;
import com.gt.genti.external.aws.dto.PreSignedUrlResponseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class S3Service {

	@Value("${aws.s3.bucket}")
	private String bucket;

	private final AmazonS3 amazonS3;

	public List<PreSignedUrlResponseDto> getPreSignedUrlMany(List<PreSignedUrlRequestDto> preSignedUrlRequestDtoList) {
		return preSignedUrlRequestDtoList.stream()
			.map(this::getPreSignedUrl)
			.toList();
	}

	public PreSignedUrlResponseDto getPreSignedUrl(PreSignedUrlRequestDto preSignedUrlRequestDto) {
		String fileName = preSignedUrlRequestDto.getFileName();
		String fileType = preSignedUrlRequestDto.getFileType().getStringValue();
		if (fileType == null) {
			throw new ExpectedException(ErrorCode.ActivePictureGenerateRequestNotExists);
		}
		String s3Key = createPath(fileType, fileName);

		GeneratePresignedUrlRequest generatePresignedUrlRequest = getGeneratePresignedUrlRequest(bucket, s3Key);
		URL url = amazonS3.generatePresignedUrl(generatePresignedUrlRequest);
		return new PreSignedUrlResponseDto(fileName, url, s3Key);
	}

	private GeneratePresignedUrlRequest getGeneratePresignedUrlRequest(String bucket, String fileName) {
		GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucket, fileName)
			.withMethod(HttpMethod.PUT)
			.withExpiration(getPreSignedUrlExpiration());

		generatePresignedUrlRequest.addRequestParameter(
			Headers.S3_CANNED_ACL,
			CannedAccessControlList.PublicReadWrite.toString()
		);

		return generatePresignedUrlRequest;
	}

	private Date getPreSignedUrlExpiration() {
		Date expiration = new Date();
		long expTimeMillis = expiration.getTime();
		expTimeMillis += 1000 * 60 * 2;
		expiration.setTime(expTimeMillis);

		return expiration;
	}

	private String createFileId() {
		return UUID.randomUUID().toString();
	}

	private String createPath(String prefix, String fileName) {
		String fileId = createFileId();
		return String.format("%s/%s", prefix, fileName + "-" + fileId);
	}
}
