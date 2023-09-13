package com.itoxi.petnuri.domain.dailychallenge.entity;

/**
 * author         : matrix
 * date           : 2023-09-12
 * description    :
 */

import com.itoxi.petnuri.global.common.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "daily_auth_img")
public class DailyAuthImg extends BaseTimeEntity {

    @Id
    @Column(name = "daily_participate_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "daily_auth_id")
    private DailyAuth dailyAuth;

    private String name;    // image name
    private String url;     // image url(AWS S3)

}
