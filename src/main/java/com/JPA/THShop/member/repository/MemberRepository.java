package com.JPA.THShop.member.repository;


import com.JPA.THShop.member.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @EntityGraph(attributePaths = {"orders"})
    Member findFetchById(Long memberId);

    @EntityGraph(attributePaths = {"orders"})
    Page<Member> findPagingById(Long memberId, Pageable pageable);
}
