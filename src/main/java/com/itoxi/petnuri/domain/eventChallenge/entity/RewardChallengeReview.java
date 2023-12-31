package com.itoxi.petnuri.domain.eventChallenge.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.itoxi.petnuri.domain.eventChallenge.type.RewardChallengeReviewStatus;
import com.itoxi.petnuri.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

import static com.itoxi.petnuri.domain.eventChallenge.type.RewardChallengeReviewStatus.ACTIVE;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reward_challenge_review")
@EntityListeners(AuditingEntityListener.class)
public class RewardChallengeReview {

    @Id
    @Column(name = "reward_challenge_review_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member challenger;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reward_challenge_id")
    private RewardChallenge rewardChallenge;

    @Column(name = "photo_name", nullable = false)
    private String photoName;

    @Column(name = "photo_url", nullable = false)
    private String photoUrl;

    @Column(name = "content", nullable = false)
    private String content;

    @Builder.Default
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private RewardChallengeReviewStatus status = ACTIVE;

    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;

    public static RewardChallengeReview create(
            Member challenger, RewardChallenge challenge, String content, String photoName, String photoUrl
    ) {
        return RewardChallengeReview.builder()
                .challenger(challenger)
                .rewardChallenge(challenge)
                .content(content)
                .photoName(photoName)
                .photoUrl(photoUrl)
                .build();
    }
}
