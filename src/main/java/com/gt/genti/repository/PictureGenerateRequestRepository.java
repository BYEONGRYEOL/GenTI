package com.gt.genti.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gt.genti.domain.PictureGenerateRequest;
import com.gt.genti.domain.User;
import com.gt.genti.dto.PictureGenerateRequestDetailResponseDto;

@Repository
public interface PictureGenerateRequestRepository
	extends JpaRepository<PictureGenerateRequest, Long> {

	// RequestStatus Active means "Before_Work" or "In_Progress"
	@Query("select pqr "
		+ "from PictureGenerateRequest pqr "
		+ "where pqr.requester.id = :userId and "
		+ "pqr.requestStatus in (com.gt.genti.domain.enums.PictureGenerateRequestStatus.BEFORE_WORK, com.gt.genti.domain.enums.PictureGenerateRequestStatus.IN_PROGRESS)"
		+ "order by pqr.createdAt desc")
	List<PictureGenerateRequest> findByRequestStatusIsActiveAndUserId_JPQL(Long userId);

	List<PictureGenerateRequest> findAllByRequester(User requester);

	@Query("select pgr from PictureGenerateRequest pgr "
		+ "where pgr.requestStatus = com.gt.genti.domain.enums.PictureGenerateRequestStatus.BEFORE_WORK "
		+ "and pgr.creator.id = :userId "
		+ "order by pgr.createdAt asc "
		+ "limit 1 ")
	Optional<PictureGenerateRequest> findByCreatorAndRequestStatusIsBeforeWorkOrderByCreatedAtAsc(
		@Param("userId") Long userId);

	@Query("select pgr from PictureGenerateRequest pgr "
		+ "where pgr.requestStatus = com.gt.genti.domain.enums.PictureGenerateRequestStatus.BEFORE_WORK "
		+ "and pgr.creator.id is null "
		+ "order by pgr.createdAt desc")
	List<PictureGenerateRequest> findPendingRequests();


	@Query("select pgr from PictureGenerateRequest pgr "
		+ "where pgr.id = :id "
		+ "and pgr.requester.id = :requesterId ")
	Optional<PictureGenerateRequest> findByIdAndRequesterId(Long id, Long requesterId);


	@Query("select new com.gt.genti.dto.PictureGenerateRequestDetailResponseDto("

		+ ")")
	List<PictureGenerateRequestDetailResponseDto> findAllByCreatorAndOrderByCreatedAtDesc(Long creatorId);
}
