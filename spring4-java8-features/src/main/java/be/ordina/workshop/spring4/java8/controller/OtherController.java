package be.ordina.workshop.spring4.java8.controller;

import be.ordina.workshop.spring4.java8.model.Beer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by stevedezitter on 07/03/15.
 */
@RequestMapping("/other")
@Controller
public class OtherController {

    @RequestMapping("dates")
    public String getDate(Model model) {
        Beer beer = new Beer("aaa", "bbb", new BigDecimal(5.9), LocalDateTime.now());

        model.addAttribute("beer",beer);

        return "beers";
    }

}
