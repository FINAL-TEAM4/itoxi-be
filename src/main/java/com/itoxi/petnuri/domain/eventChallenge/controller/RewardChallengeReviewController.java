package com.itoxi.petnuri.domain.eventChallenge.controller;

import com.itoxi.petnuri.domain.eventChallenge.dto.request.WriteReviewReq;
import com.itoxi.petnuri.domain.eventChallenge.service.RewardChallengeReviewService;
import com.itoxi.petnuri.domain.member.entity.Member;
import com.itoxi.petnuri.global.common.customValid.valid.ValidId;
import com.itoxi.petnuri.global.security.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/challenge/reward")
@RequiredArgsConstructor
public class RewardChallengeReviewController {
    private final RewardChallengeReviewService reviewService;

    @PostMapping(
            path = "/{challengeId}/review",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE}
    )
    public ResponseEntity<Object> writeReview(
            @PathVariable @ValidId Long challengeId,
            @RequestPart MultipartFile file,
            @RequestPart WriteReviewReq request,
            @AuthenticationPrincipal PrincipalDetails principalDetails
    ) {
        Member member = principalDetails.getMember();
        reviewService.writeReview(member, challengeId, file, request);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
