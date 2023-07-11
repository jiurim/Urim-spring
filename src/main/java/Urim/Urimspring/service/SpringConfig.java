package Urim.Urimspring.service;

import Urim.Urimspring.repository.*;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }



    /**private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em){
        this.em =em;
    } **/

    /**
    private DataSource dataSource;

    //@Autowired
    public SpringConfig(DataSource dataSource){
        this.dataSource = dataSource;
    }
    **/

    //자바 코드로 직접 Spring Bean등록하기
    /**@Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }
    //이렇게 하면 spring이 뜰 때 Configuration을 읽고 spring bean에 등록하는거라고 인식하고, memberservice의 로직을
    // 호출해서 spring bean에 등록해줌 그러면 생성자에 무엇을 넣어줘야함(=memberrepository)
     **/

   /** @Bean
    public MemberRepository memberRepository(){

        //return new MemoryMemberRepository();
        //return new JdbcMemberRepository(dataSource);
        //spring이 제공

        //return new JdbcTemplateMemberRepository(dataSource);

        return new JpaMemberRepository(em);
    } **/
    // controller는 spring이 관리하는 것이므로 Autowired로 해줘도 상관없음

    /**
     * 이렇게 bean을 사용하면 상황에 따라 구현 클래스를 변경해야만 할 때 {(ex) DB로 바꿀 때} 다른 코드는 건들지 않고
     * 바로 바꿀 수 있음
     */

}
