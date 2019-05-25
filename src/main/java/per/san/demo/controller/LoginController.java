package per.san.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * description:
 *
 * @author shencai.huang@hand-china.com
 * @date 5/15/2019 10:52
 * lastUpdateBy: shencai.huang@hand-china.com
 * lastUpdateDate: 5/15/2019
 */
@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/find")
    public String findPassword() {
        return "findPassword";
    }
}
