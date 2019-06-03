package per.san.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * description:
 *
 * @author shencai.huang@hand-china.com
 * @date 5/15/2019 13:08
 * lastUpdateBy: shencai.huang@hand-china.com
 * lastUpdateDate: 5/15/2019
 */
@Controller
public class IndexController {

    @GetMapping("/index")
    public String index() {
        return "stock/index";
    }

    @GetMapping("/console")
    public String console() {
        return "stock/console";
    }

    @GetMapping("/optional_stock")
    public String optionalStock() {
        return "stock/optional_stock";
    }

    @GetMapping("/app")
    public String app() {
        return "stock/views/app";
    }
}
