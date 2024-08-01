package cholog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MemberController {

    @RequestMapping("/hello")
    public String world(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello";
    }

    public Person json() {
        // TODO: /json 요청 시 {"name": "brown", "age": 20} 데이터를 응답할 수 있도록 설정하세요.
        return null;
    }
}
