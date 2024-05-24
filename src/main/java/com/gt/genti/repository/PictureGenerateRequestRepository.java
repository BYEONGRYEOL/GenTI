package com.gt.genti.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gt.genti.domain.Creator;
import com.gt.genti.domain.PictureGenerateRequest;
import com.gt.genti.domain.User;
import com.gt.genti.domain.enums.PictureGenerateRequestStatus;
import com.gt.genti.domain.enums.PictureGenerateResponseStatus;

@Repository
public interface PictureGenerateRequestRepository
	extends JpaRepository<PictureGenerateRequest, Long> {

	// RequestStatus Active means "Before_Work" or "In_Progress"
	@Query("select pqr "
		+ "from PictureGenerateRequest pqr "
		+ "where pqr.requester.id = :userId and "
		+ "pqr.pictureGenerateRequestStatus  = :requestStatus "
		+ "order by pqr.createdAt desc")
	List<PictureGenerateRequest> findByRequestStatusAndUserId(PictureGenerateRequestStatus requestStatus, Long userId);

	List<PictureGenerateRequest> findAllByRequester(User requester);

	@Query("select pgr from PictureGenerateRequest pgr "
		+ "where pgr.pictureGenerateRequestStatus = com.gt.genti.domain.enums.PictureGenerateRequestStatus.IN_PROGRESS "
		+ "and pgr.creator= :creator "
		+ "order by pgr.createdAt desc "
		+ "limit 1 ")
	Optional<PictureGenerateRequest> findByCreatorAndRequestStatusIsBeforeWorkOrderByCreatedAtAsc(
		Creator creator);

	@Query("select pgr from PictureGenerateRequest pgr "
		+ "where pgr.pictureGenerateRequestStatus = com.gt.genti.domain.enums.PictureGenerateRequestStatus.CREATED "
		+ "and pgr.creator is null "
		+ "order by pgr.createdAt desc")
	List<PictureGenerateRequest> findPendingRequests();

	@Query("select pgr from PictureGenerateRequest pgr "
		+ "where pgr.id = :id "
		+ "and pgr.requester.id = :requesterId ")
	Optional<PictureGenerateRequest> findByIdAndRequesterId(Long id, Long requesterId);

	List<PictureGenerateRequest> findAllByCreatorIsOrderByCreatedAtDesc(Creator creator);

	@Query("select pgr from PictureGenerateRequest pgr "
		+ "where pgr.creator = :creator "
		+ "and pgr.pictureGenerateRequestStatus = :status "
		+ "order by pgr.createdAt desc "
		+ "limit 1 ")
	Optional<PictureGenerateRequest> findByStatusOrderByCreatedAtDesc(Creator creator,
		PictureGenerateRequestStatus status);

	@Query("select pgr from PictureGenerateRequest pgr "
		+ "where pgr.requester.id = :userId "
		+ "and pgr.pictureGenerateRequestStatus in :statusList "
		+ "order by pgr.createdAt desc "
		+ "limit 1 ")
	Optional<PictureGenerateRequest> findByUserIdAndRequestStatusIn(Long userId,
		List<PictureGenerateRequestStatus> statusList);

	//TODO 성능 이슈 때문에 dto를 select하도록 변경해야함
	// edited at 2024-05-23
	// author 서병렬

	@Query("select pgreq from PictureGenerateRequest pgreq "
		+ "join PictureGenerateResponse pgres "
		+ "where pgres.request = pgreq "
		+ "and pgres.status in :activeResponseStatusList "
		+ "and pgreq.creator = :foundCreator "
		+ "and pgreq.pictureGenerateRequestStatus in :activeRequestStatusList "
		+ "order by pgreq.createdAt desc "
		+ "limit 3 ")
	List<PictureGenerateRequest> findByCreatorAndActiveStatus(Creator foundCreator,
		List<PictureGenerateRequestStatus> activeRequestStatusList,
		List<PictureGenerateResponseStatus> activeResponseStatusList);
}
