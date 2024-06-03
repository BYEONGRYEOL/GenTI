package com.gt.genti.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gt.genti.domain.ResponseExample;
import com.gt.genti.domain.User;
import com.gt.genti.dto.ExampleSaveRequestDto;
import com.gt.genti.dto.ExampleWithPictureFindResponseDto;
import com.gt.genti.dto.PromptOnlyExampleFindResponseDto;
import com.gt.genti.dto.PromptOnlyExampleSaveRequestDto;
import com.gt.genti.error.ErrorCode;
import com.gt.genti.error.ExpectedException;
import com.gt.genti.repository.ResponseExampleRepository;
import com.gt.genti.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResponseExampleService {
	private final UserRepository userRepository;
	private final ResponseExampleRepository responseExampleRepository;

	public List<ExampleWithPictureFindResponseDto> getAllResponseExamples() {
		return responseExampleRepository.findAllByPromptOnlyIsFalse()
			.stream()
			.map(ExampleWithPictureFindResponseDto::new)
			.toList();
	}

	public List<PromptOnlyExampleFindResponseDto> getAllPromptOnlyExamples() {
		return responseExampleRepository.findAllByPromptOnlyIsFalse()
			.stream()
			.map(PromptOnlyExampleFindResponseDto::new)
			.toList();
	}

	@Transactional
	public void addResponseExamples(List<ExampleSaveRequestDto> requestDtoList,
		Long userId) {
		User findAdmin = userRepository.findById(userId)
			.orElseThrow(() -> new ExpectedException(ErrorCode.UserNotFound));

		responseExampleRepository.saveAll(
			requestDtoList.stream().map(ResponseExample::new).toList());
	}

	@Transactional
	public void addPromptOnlyExamples(List<PromptOnlyExampleSaveRequestDto> requestDtoList, Long userId) {
		User findAdmin = userRepository.findById(userId)
			.orElseThrow(() -> new ExpectedException(ErrorCode.UserNotFound));
		responseExampleRepository.saveAll(
			requestDtoList.stream().map(ResponseExample::new).toList());
	}
}
