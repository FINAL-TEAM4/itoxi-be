package com.itoxi.petnuri.domain.dailychallenge.entity;

import com.itoxi.petnuri.domain.member.entity.Member;
import com.itoxi.petnuri.global.common.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * author         : matrix
 * date           : 2023-09-13
 * description    : 데일리 챌린지 인증글
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "daily_auth")
public class DailyAuth extends BaseTimeEntity {

    @Id
    @Column(name = "daily_auth_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "dailyAuth")
    private List<DailyParticipate> dailyParticipates = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    // 기획안에 인증샷은 최대 1개로 되어 있어 인증샷 테이블 -> 인증글로 통합
    @Column(nullable = false)
    private String imgName; // 인증샹 파일명

    @Column(nullable = false)
    private String imgUrl;  // 인증샷 S3 url
}