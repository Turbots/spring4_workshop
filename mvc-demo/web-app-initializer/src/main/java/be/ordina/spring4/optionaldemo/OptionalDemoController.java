package be.ordina.spring4.optionaldemo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * Created by sest on 23/01/15.
 */

@RestController
public class OptionalDemoController {

    @RequestMapping(value = "/optional", produces = "text/plain")
    public String requestParamAsOptional(
            @RequestParam(value = "hello") Optional<String> hello) {

        StringBuilder result = new StringBuilder("Optional said: ");
        hello.ifPresent(value -> result.append(value.toUpperCase()));

        return result.toString();
    }

}
