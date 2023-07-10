package Urim.Urimspring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(){
        return "home";
        //실행해보면 회원목록은 members로 회원가입은 member/new로 이동하게 해놨음
        // 그런데 저번에 아무것도 없으면 welcome 페이지로 이동하게 해놨는데 그렇지 않음
        // 그 이유는 우선순위가 있기 때문임
        // 정적컨텐츠에서 먼저 요청이 오면 스프링 관련 컨트롤러가 있는지 찾아보고 없으면 static 파일을 찾게 만들어놨음
        // welcome페이지도 같음 첫번째 요청이 오면 컨트롤러에서 매핑된게 있으므로 그 컨트롤러가 요청되고 끝나게됨


    }
}
