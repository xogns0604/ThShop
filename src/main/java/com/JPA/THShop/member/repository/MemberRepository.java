package com.JPA.THShop.member.repository;

import com.JPA.THShop.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
