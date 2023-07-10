package Urim.Urimspring.controller;

public class MemberForm {
    private String name;
    // input의 name을 보고 String이 값을 name에 넣어줌

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
