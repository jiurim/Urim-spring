package Urim.Urimspring.controller;
import Urim.Urimspring.domain.Member;
import Urim.Urimspring.service.MemberService;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;


@Controller
public class MemberController {
    /**private final MemberService memberService = new MemberService();**/
    //이렇게 new를 사용하면 멤버 컨트롤러말고 다른 여러 컨트롤러들이 멤버 서비스를 가져다 쓸 수 있음
    //따라서 스프링컨테이너에다가 등록하면 하나만 쓸 수 있음 생성자자로!

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }
    //이렇게 하면 memberservice를 찾을 수 없다는 오류가 뜸 AutoWired는 Spring Container에서 memberservice를 가져옴
    // memberservice에 가보면 순수한 자바클래스로 spring이 알 수 없음
    // 따라서 @Service를 넣어주면 됨
    //그리고 Repository 클래스에 @Repository를 넣어주면됨 이것이 정형화된 패턴
    // 따라서 이렇게 해놓으면 spring이 뜰 때 Controller service repository를 가지고 올라옴
    // controller와 service를 연결시켜줘야하는데 이때 연결시킬 때 Autowired를 쓰면 membercontroller가 생성될 때
    // spring bean에 등록되어 있는 service 객체를 가져다가 넣어줌 이것이 바로 dependency injection(의존관계)

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }
    //url에 직접 치는걸 get방식으로 mapping됨

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());
        
        memberService.join(member);

        return "redirect:/";
    }
    //보통 조회할 떄 get 그리고 데이터를 등록할 땐 post를 씀
    
    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }






}
//이렇게 해놓기만 해도 spring 통이 생기고 거기에 멤버객체가 생성돼 spring에 넣어둠