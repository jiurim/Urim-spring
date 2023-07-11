package Urim.Urimspring.service;

import Urim.Urimspring.domain.Member;
import Urim.Urimspring.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Transactional // 항상 데이터를 저장하고 변경할 때 필요한 Transactional이 필요함
public class MemberService {
    private final MemberRepository memberRepository;


    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }
    /**
     *회원가입
     **/

    public Long join(Member member){
        //회원가입할 때 같은 이름이 있는 회원이 있으면 안된다는 설정이 있다면?
        //Optional<Member> result = memberRepository.findByName(member.getName());
        //그런데 이렇게 하면 안이쁨
        //따라서 이렇게 하면 좋음
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
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
