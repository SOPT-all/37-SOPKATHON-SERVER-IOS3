package org.project.domain.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.project.domain.user.dto.response.UserResponse;
import org.project.domain.user.service.UserService;
import org.project.global.api.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@Tag(name = "회원 관련 api")
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/{userId}")  // 유저의 이름, 사용가능한 크레딧 개수 조회
    @Operation(summary = "마이페이지 기본 정보 제공 API")
    public ResponseEntity<ApiResponse<?>> getUserInfo(@PathVariable Long userId) {

        UserResponse response = userService.getUserInfo(userId);

        return ResponseEntity.ok(ApiResponse.ok(response));
    }
}
