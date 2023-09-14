package com.itoxi.petnuri.domain.dailychallenge.entity;

import com.itoxi.petnuri.domain.dailychallenge.type.ChallengeStatus;
import com.itoxi.petnuri.global.common.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
/**
 * author         : matrix
 * date           : 2023-09-13
 * description    : 데일리 챌린지
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "daily_challenge")
public class DailyChallenge extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "daily_challenge_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;        // 챌린지명
    @Column(nullable = false)
    private String authMethod;  // 챌린지 인증 방법
    @Column(nullable = false)
    private Long payment;       // 챌린지 인증 완료시 지급 포인트

    @Column(nullable = false)
    private LocalDateTime startDate;    // 챌린지 시작 일자 : 2023-09-12 00:00:00

    //Todo: 데일리 챌린지에 종료 일자가 필요한지 생각해 보기
    @Column(nullable = false)
    private LocalDateTime endDate;      // 챌린지 종료 일자 : 9999-12-31 23:59:59

    @Enumerated(value = EnumType.STRING)
    private ChallengeStatus challengeStatus;

    @OneToMany(mappedBy = "dailyChallenge")
    private List<DailyParticipate> dailyParticipate = new ArrayList<>(); // 챌린지 참여 매핑

}