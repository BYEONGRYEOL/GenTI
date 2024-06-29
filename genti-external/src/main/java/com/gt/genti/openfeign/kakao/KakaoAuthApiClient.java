package com.gt.genti.openfeign.kakao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gt.genti.openfeign.dto.response.kakao.KakaoTokenResponse;

@FeignClient(name = "kakaoAuthApiClient", url = "https://kauth.kakao.com")
public interface KakaoAuthApiClient {

    @PostMapping(value = "/oauth/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    KakaoTokenResponse getOAuth2AccessToken(
            @RequestParam("grant_type") String grantType,
            @RequestParam("client_id") String clientId,
            @RequestParam("redirect_uri") String redirectUri,
            @RequestParam("code") String code
    );

}