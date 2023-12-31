package com.itoxi.petnuri.domain.member.repository;

import com.itoxi.petnuri.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);

    boolean existsByReferralCode(String referralCode);

    Optional<Member> findByReferralCode(String referralCode);
}
