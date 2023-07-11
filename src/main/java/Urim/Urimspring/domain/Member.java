package Urim.Urimspring.domain;


import jakarta.persistence.*;

@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)//jpa에서 entity해주면 jpa가 관리해주고 pk를 mapping 해줘야함
    //dp가 id를 자동으로 생성해주는 것을 identity전략이라고함

    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName(){
        return  name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
