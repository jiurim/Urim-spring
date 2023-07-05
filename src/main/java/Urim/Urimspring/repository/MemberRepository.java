package Urim.Urimspring.repository;

import java.util.Optional;
import Urim.Urimspring.domain.Member;
import java.util.List;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById (Long id); //없으면 null인데 이걸 감싸서 반환하는 방법이 Optional
    Optional<Member> findByName (String name);
    List<Member> findAll();

}
