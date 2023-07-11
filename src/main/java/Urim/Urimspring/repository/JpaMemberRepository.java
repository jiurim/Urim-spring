package Urim.Urimspring.repository;

import Urim.Urimspring.domain.Member;
import jakarta.persistence.EntityManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em;
    //jpa는 entitymanager로 모든걸 동작함
    //build.gradle에서 jpa를 라이브러리로 받았으므로 스프링부트가 자동으로 entitymanager를 자동으로 생성해줌

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }
    //저장하려면
    @Override
    public Member save(Member member) {
        //return null;
        em.persist(member);
        return member; //이렇게 하면 jpa가 insertquery 다 만들어서 db에 저장하고 id까지 set 해줌
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class,id);
        return Optional.ofNullable(member);
        //return Optional.empty();
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result =  em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name",name)
                .getResultList();

        return result.stream().findAny();
        //return Optional.empty();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m",Member.class)
                .getResultList(); //member을 쿼리로 날리고 객체 자체를 셀렉함
        //return null;
    }
}
