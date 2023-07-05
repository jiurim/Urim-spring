package Urim.Urimspring.repository;

import Urim.Urimspring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Optional;


import static org.hamcrest.MatcherAssert.assertThat;

class MemoryMemberRepositoryTest{
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member); //저장할 때 id가 저장

        Member result = repository.findById(member.getId()).get();
        //new에서 저장한거랑 DB에서 꺼낸 result가 같으면 True
        System.out.println("result = "+(result==member));

        //그런데 그 값을 볼 수 없고 true만 확인 가능함 이때 쓸 수 있는게 Assertions
        //Assertions.assertEquals(member,result);
        // 값이 뜨진 않지만 초록불이 뜨는것으로 보아 잘 돌아감
        //assertions보다 더 편리한 Assetions에서 org.assertj를 선택하면됨

        Assertions.assertThat(member).isEqualTo(result);
        //-> 이건 static옵션을 넣을 수 잇음 그럼 assertThat을 바로 쓸 수 잇음
    }
    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring2");
        repository.save(member2);

        Member result = repository.findByName("Spring1").get();

        Assertions.assertThat(result).isEqualTo(member1);

    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }

    //다시 전체를 돌려보면 findByName이 오류가 뜸 그 이유는 테스트 순서가 보장이 안되기 때문임
    // 모든 테스트는 순서 상관없이 메소드들이 따로 동작되도록 설정해야함
    // 이게 Spring1 과 Spring2 가 먼저 findAll() 에서 저장되었기 때문에
    // 따라서 테스트가 끝나면 데이터를 초기화줘야함 -> public void afterEach()
    // 이건 앞에 MemoryMemberRepository 클래스에서 clearstore을 정의해줘야함
    //이렇게 하면 순서 상관없이 테스트 한번 끝날 때 마다 repository가 지워짐

}

