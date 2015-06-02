package me.s4h.paiban;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by hikari on 2015/6/2.
 */

@Controller
@RequestMapping("/")
public class HomeController {
    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "redirect:/employee";
    }
}
