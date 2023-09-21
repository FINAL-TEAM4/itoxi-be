package com.itoxi.petnuri.global.common.exception.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    INTERNAL_SERVER_ERROR("내부 서버 오류가 발생하였습니다."),

    VALIDATION_ERROR("유효성 검사 중 예외가 발생했습니다."),
    S3UPLOADER_ERROR("s3 업로드 중 오류가 발생했습니다."),

    // REDIS
    INVALID_OR_EXPIRED_KEY("만료되었거나 유효하지 않은 키입니다."),
    NOT_MATCH_TOKEN("토큰이 일치하지 않습니다."),

    // JSON
    JSON_PARSE_FAILED("JSON 파싱에 실패했습니다."),

    LOGIN_FAILED("회원 정보가 존재하지 않습니다."),
    UN_AUTHORIZED("인증되지 않았습니다."),
    FORBIDDEN("접근이 거부되었습니다."),
    USER_NOT_FOUND("사용자를 찾을 수 없습니다."),
    DUPLICATED_EMAIL("이미 가입된 이메일입니다."),
    DUPLICATED_NICKNAME("이미 사용중인 닉네임입니다."),
    REFERRAL_CODE_NOT_FOUND("잘못된 추천인 코드입니다."),
    POINT_NOT_FOUND("회원 포인트 정보를 찾을 수 없습니다."),

    // 회원
    NOT_FOUND_MEMBER_ID("존재하지 않는 회원 ID 입니다."),

    // 챌린지
    NOT_FOUND_CHALLENGE_ID("존재하지 않는 챌린지 ID 입니다."),
    ;

    private final String message;

}