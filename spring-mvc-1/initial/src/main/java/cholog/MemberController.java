package cholog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {

    @GetMapping("/hello")
    public String world(@RequestParam String name, Model model) {
        model.addAttribute("name", name); // "name": hello.html의 {name}
        return "hello";
    }

    @GetMapping("/json")
    @ResponseBody
    public Person json() {
        Person person = new Person("brown", 20);
        return person; // new Person("brown", 20);
    }
}
