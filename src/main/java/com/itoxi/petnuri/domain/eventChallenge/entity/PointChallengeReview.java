package com.itoxi.petnuri.domain.eventChallenge.entity;

import static com.itoxi.petnuri.domain.eventChallenge.type.PointChallengeReviewStatus.ACTIVE;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.itoxi.petnuri.domain.eventChallenge.type.PointChallengeReviewStatus;
import com.itoxi.petnuri.domain.member.entity.Member;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "point_challenge_review")
public class PointChallengeReview {

    @Id
    @Column(name = "point_challenge_review_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "point_challenge_id")
    private PointChallenge pointChallenge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member reviewer;

    @Column(name = "photo_name", nullable = false)
    private String photoName;

    @Column(name = "photo_url", nullable = false)
    private String photoUrl;

    @Column(name = "content", nullable = false)
    private String content;

    @Builder.Default
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private PointChallengeReviewStatus status = ACTIVE;

    @Builder.Default
    @Column(name = "created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt = LocalDateTime.now();

    public void uploadPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
