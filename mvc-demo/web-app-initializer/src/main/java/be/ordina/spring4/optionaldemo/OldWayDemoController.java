package be.ordina.spring4.optionaldemo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sest on 23/01/15.
 */
@RestController
public class OldWayDemoController {


    @RequestMapping(value = "/oldWay", produces = "text/plain")
    public String requestParamOldWay(@RequestParam(value = "hello", required = false) String hello){
        StringBuilder result = new StringBuilder("Old way said: ");
        if(StringUtils.isNotBlank(hello)){
            result.append(hello.toUpperCase());
        }
        return result.toString();
    }
}
