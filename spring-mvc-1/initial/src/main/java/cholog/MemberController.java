package cholog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MemberController {
    @ModelAttribute
    @RequestMapping("/hello")
    public String world(@RequestParam String name, Model model) {
        model.addAttribute("name", name);
        return null;
    }

    @RequestMapping("/json")
    @ResponseBody
    public Person json() {
        return new Person("brown", 20);
    }
}
