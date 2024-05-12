package com.gt.genti.domain;

import com.gt.genti.domain.common.BaseTimeEntity;
import com.gt.genti.domain.common.PictureEntity;
import com.gt.genti.dto.AddPromptOnlyExampleRequestDto;
import com.gt.genti.dto.AddResponseExampleRequestDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "response_example")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseExample extends PictureEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column(name = "example_picture_url")
	String examplePictureUrl;

	@Column(name = "example_prompt")
	String examplePrompt;

	@Column(name = "prompt_only")
	Boolean promptOnly;

	@OneToOne
	@JoinColumn(name = "created_by")
	User createdBy;

	public ResponseExample(AddResponseExampleRequestDto dto, User createdBy) {
		this.promptOnly = false;
		this.examplePictureUrl = dto.getUrl();
		this.examplePrompt = dto.getPrompt();
		this.createdBy = createdBy;
	}

	public ResponseExample(AddPromptOnlyExampleRequestDto dto, User createdBy) {
		this.promptOnly = true;
		this.examplePictureUrl = null;
		this.examplePrompt = dto.getPrompt();
		this.createdBy = createdBy;
	}

	@Override
	public String getUrl(){
		return this.getExamplePictureUrl();
	}
}