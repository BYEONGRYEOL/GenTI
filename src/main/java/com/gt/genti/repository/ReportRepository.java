package com.gt.genti.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gt.genti.domain.Report;
import com.gt.genti.dto.ReportResponseDto;

public interface ReportRepository extends JpaRepository<Report, Long> {

	@Query("select new com.gt.genti.dto.ReportResponseDto( "
		+ "r,"
		+ "u.email,"
		+ "c.user.email,"
		+ "pgres.id)"
		+ "from Report r "
		+ "join r.pictureGenerateResponse pgres "
		+ "join pgres.request.requester u "
		+ "join pgres.creator c "
		// + "where r.pictureGenerateResponse.id = pgres.id and "
		// + "pgres.request.requester.id = u.id "
		+ "order by r.reportStatus asc, r.createdAt desc ")
	List<ReportResponseDto> findAllByOrderByReportStatusAndCreatedAt();
}
