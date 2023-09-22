package com.itoxi.petnuri.domain.delivery.entity;

import com.itoxi.petnuri.domain.delivery.type.DeliveryProcess;
import com.itoxi.petnuri.domain.member.entity.Member;
import com.itoxi.petnuri.domain.product.entity.ChallengeProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "challenge_delivery")
public class ChallengeDelivery {
    @Id
    @Column(name = "challenge_delivery_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_product_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ChallengeProduct product;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "road_address", nullable = false)
    private String roadAddress;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "zipcode", nullable = false)
    private String zipcode;

    @Column(name = "message")
    private String message;

    @Column(name = "process", nullable = false)
    @Enumerated(EnumType.STRING)
    private DeliveryProcess process;
}
