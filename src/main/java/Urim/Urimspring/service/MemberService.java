package Urim.Urimspring.service;

import Urim.Urimspring.domain.Member;
import Urim.Urimspring.repository.MemberRepository;
import Urim.Urimspring.repository.MemoryMemberRepository;
import java.util.List;

import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    /**
     *회원가입
     **/

    public Long join(Member member){
        //회원가입할 때 같은 이름이 있는 회원이 있으면 안된다는 설정이 있다면?
        //Optional<Member> result = memberRepository.findByName(member.getName());
        //그런데 이렇게 하면 안이쁨
        //따라서 이렇게 하면 좋음
        /*memberRepository.findByName(member.getName())
                .ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원");
          });
         */
        validateDuplicateMember(member);
        memberRepository.save(member);
        return  member.getId();
    }

    private void validateDuplicateMember(Member member){
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    //throw new IllegalAccessException("이미 존재하는 회원입니다");
                });
    }

    /**
     * 전체회원조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
