package com.JPA.THShop.member.repository;

import com.JPA.THShop.member.domain.Member;
import com.JPA.THShop.member.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@Transactional @Rollback(false)
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    //멤버추가 테스팅
    @Test
    public void 유저추가Test() throws Exception{
        //given
        Member member = new Member("member1Id", "member1Pass", "member1이름", "email.net@gmail.com");
        Member saveMember = memberRepository.save(member);

        //when
        Member findMember = memberRepository.findById(member.getId()).get();

        //then
        assertThat(member.getId()).isEqualTo(findMember.getId());
    }

    @Test
    public void 유저정보수정Test() throws Exception{
        //given
        Member member = new Member("member2Id", "member2Pass", "member2이름", "email.net@gmail.com");
        memberRepository.save(member);
        String newPwd = "editMember2Pass";
        member.editInfo(newPwd);

        //when
        Member findMember = memberRepository.findById(member.getId()).get();

        //then
        assertThat(findMember.getPwd()).isEqualTo(newPwd);
    }
}