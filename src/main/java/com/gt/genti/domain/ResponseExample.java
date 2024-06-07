package com.gt.genti.domain;

import com.gt.genti.domain.common.Picture;
import com.gt.genti.domain.common.PictureEntity;
import com.gt.genti.dto.admin.request.ExampleSaveRequestDto;

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
public class ResponseExample extends PictureEntity implements Picture {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column(name = "example_prompt")
	String examplePrompt;

	@Column(name = "prompt_only")
	Boolean promptOnly;

	public ResponseExample(ExampleSaveRequestDto dto, User uploadedBy) {
		this.promptOnly = false;
		this.key = dto.getKey();
		this.examplePrompt = dto.getPrompt();
		this.setUploadedBy(uploadedBy);
	}

}
