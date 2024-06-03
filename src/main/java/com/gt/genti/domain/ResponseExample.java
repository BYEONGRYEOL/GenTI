package com.gt.genti.domain;

import com.gt.genti.domain.common.BaseTimeEntity;
import com.gt.genti.dto.CommonPictureUrlResponseDto;
import com.gt.genti.dto.ExampleSaveRequestDto;
import com.gt.genti.dto.PromptOnlyExampleSaveRequestDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "response_example")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseExample extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column(name = "example_prompt")
	String examplePrompt;

	@Column(name = "prompt_only")
	Boolean promptOnly;

	@Column(name = "`key`")
	String key;

	public ResponseExample(ExampleSaveRequestDto dto) {
		this.promptOnly = false;
		this.key = dto.getKey();
		this.examplePrompt = dto.getPrompt();
	}

	public ResponseExample(PromptOnlyExampleSaveRequestDto dto) {
		this.promptOnly = true;
		this.key = null;
		this.examplePrompt = dto.getPrompt();
	}

	public CommonPictureUrlResponseDto mapToCommonResponse() {
		return new CommonPictureUrlResponseDto(id, key);
	}

}
