package com.gt.genti.dto;

import com.gt.genti.domain.enums.UserStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserStatusUpdateRequestDto {
	UserStatus userStatus;
}