package com.itoxi.petnuri.domain.eventChallenge.service;

import com.itoxi.petnuri.domain.eventChallenge.dto.request.WriteReviewReq;
import com.itoxi.petnuri.domain.eventChallenge.entity.RewardChallengeReview;
import com.itoxi.petnuri.domain.eventChallenge.entity.RewardChallenger;
import com.itoxi.petnuri.domain.eventChallenge.repository.RewardChallengeReviewRepository;
import com.itoxi.petnuri.domain.eventChallenge.repository.RewardChallengerRepository;
import com.itoxi.petnuri.domain.member.entity.Member;
import com.itoxi.petnuri.global.common.exception.Exception400;
import com.itoxi.petnuri.global.common.exception.Exception404;
import com.itoxi.petnuri.global.s3.service.AmazonS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import static com.itoxi.petnuri.global.common.exception.type.ErrorCode.DUPE_POST_MEMBER;
import static com.itoxi.petnuri.global.common.exception.type.ErrorCode.NOT_FOUND_CHALLENGE_JOIN;

@Service
@RequiredArgsConstructor
public class RewardChallengeReviewService {
    private final RewardChallengerRepository challengerRepository;
    private final RewardChallengeReviewRepository reviewRepository;
    private final AmazonS3Service amazonS3Service;

    @Transactional
    public void writeReview(Member member, Long challengeId, MultipartFile file, WriteReviewReq request) {
        // 챌린지 신청 여부 검사
        RewardChallenger rewardChallenger = challengerRepository.findByChallengerAndRewardChallengeId(member, challengeId)
                .orElseThrow(() -> new Exception404(NOT_FOUND_CHALLENGE_JOIN));

        // 리뷰 등록 여부 검사
        if (reviewRepository.existsByChallengerAndRewardChallengeId(member, challengeId)) {
            throw new Exception400(DUPE_POST_MEMBER);
        }

        // 리뷰 저장
        String savedUrl = amazonS3Service.uploadRewardChallengeReviewImage(file);
        RewardChallengeReview review = RewardChallengeReview.create(member, rewardChallenger.getRewardChallenge(),
                request.getContent(), file.getOriginalFilename(), savedUrl);
        reviewRepository.save(review);

        // 참여 프로세스 업데이트
        rewardChallenger.reviewComplete();
        challengerRepository.save(rewardChallenger);
    }
}