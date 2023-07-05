package Urim.Urimspring.service;


import Urim.Urimspring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;



class MemberServiceTest {

    MemberService memberService = new MemberService();
    @Test
    void 회원가입() {
        //given
        Member member =new Member();
        member.setName("hello");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }
    //test에서 join의 핵심은 예외가 처리되는지 확인하는것

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);

        // then
        /*assertThrows(IllegalStateException.class, () -> {
            memberService.join(member2);
        });*/
        try{
            memberService.join(member2);
            fail("이미 존재하는 회원입니다.");
        }catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.123");
        }

        //then
    }



    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}