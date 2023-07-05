package Urim.Urimspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String hellowMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name",name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody //http에서 바디부의 데이터를 직접 넣어주겠다는 의미
    public String helloString(@RequestParam("name") String name){
        return "hello "+name; //hello string으로 바뀜 -> 이 문자 그대로 클라이언트한테 내려가고 View가 없음 즉 html이 없음
    }

    //데이터를 내놓을때 json 방식으로 key=value
    @GetMapping("hello-api")
    @ResponseBody //json으로 반환하는게 기본으로 설정되어있음
    //hello 객체를 넘기면 그 객체를 보고 HttpMessageConverter가 동작 기존에는 ViewController가 동작했음
    //단순 문자면 STringConveter가 동작하고 객체면 JsonConverter가 동작
    //객체면 Json 스타일로 바꿔서 이걸 응답하는것이 ResponseBody가 동작하는 원리
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello{ //정적 클래스로 만들면 클래스 안에서 클래스를 쓸 수 있음 ex) HelloController.hello 이런식으로
        private String name;

        public String getName(){
            return name;
        }
        public void setName(String name){
            this.name = name;
        }
    }
}
