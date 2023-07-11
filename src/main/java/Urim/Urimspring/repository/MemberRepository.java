package Urim.Urimspring.repository;

import java.sql.SQLException;
import java.util.Optional;
import Urim.Urimspring.domain.Member;
import java.util.List;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
