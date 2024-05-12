package com.gt.genti.dto;

import java.time.LocalDateTime;

import com.gt.genti.domain.Report;
import com.gt.genti.domain.enums.ReportStatus;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReportResponseDto {
	Long id;
	String reporterEmail;
	String creatorEmail;
	String content;
	ReportStatus reportStatus;
	Long pictureGenerateResponseId;
	LocalDateTime createdAt;

	public ReportResponseDto(Report report, String reporterEmail, String creatorEmail, Long pictureGenerateResponseId) {
		this.id = report.getId();
		this.reporterEmail = reporterEmail;
		this.creatorEmail = creatorEmail;
		this.content = report.getContent();
		this.reportStatus = report.getReportStatus();
		this.pictureGenerateResponseId = pictureGenerateResponseId;
		this.createdAt = report.getCreatedAt();
	}
}