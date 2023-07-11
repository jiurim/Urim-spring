import Urim.Urimspring.domain.Member;
import Urim.Urimspring.repository.MemberRepository;
import Urim.Urimspring.repository.MemoryMemberRepository;
import Urim.Urimspring.service.MemberService;

public class test {
    MemberService memberService ;
    MemberRepository memberRepository = new MemoryMemberRepository();

    public static void main(String[] args) {
        Member member1 = new Member();
        member1.setName("sunwoo");

    }
}
