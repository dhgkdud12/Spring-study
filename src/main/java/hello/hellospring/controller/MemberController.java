package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired //스프링이 연관된 객체를 스프링 컨테이너에서 찾아서 넣어준다(memberService)
    //객체 생성 시점에 스프링 컨테이너에서 해당 스프링 빈을 찾아서 주입한다. DI (Dependency Injection), 의존성 주입
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm"; //templates폴더의 createMemberForm로 이동
    }

    //GET - 데이터 조회, POST - 데이터 전달, 등록
    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName()); //getName으로 꺼냄

        memberService.join(member); //회원가입

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers(); //회원조회
        model.addAttribute("members",members); //model에 담아서 화면에 넘김
        return "members/memberList"; 
    }
}


