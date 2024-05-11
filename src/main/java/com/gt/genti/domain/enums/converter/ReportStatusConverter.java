package com.gt.genti.domain.enums.converter;

import com.gt.genti.domain.enums.ReportStatus;

import jakarta.persistence.Converter;

@Converter
public class ReportStatusConverter extends DefaultStringAttributeConverter<ReportStatus> {

	public ReportStatusConverter() {
		super(ReportStatus.class);
	}

}