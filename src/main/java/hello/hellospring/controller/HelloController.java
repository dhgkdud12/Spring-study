package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello";  //templates 안 hello.html 찾아서 렌더링
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){ //파라미터
        model.addAttribute("name",name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody //HTTP의 BODY에 문자 내용을 직접 반환
    public String helloSpring(@RequestParam("name") String name){
        return "hello " + name; //"hello spring" , 문자
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello; //객체 반환
    }


    static class Hello{
        private String name;

        //프로퍼티 접근 방식
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
