package be.ordina.workshop.spring4.java8.service;

import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import javax.annotation.Priority;

/**
 * Created by stevedezitter on 08/03/15.
 */
@Service
//@Priority(1)
@Order(1) @Primary
public class MyServiceImpl implements MyService {
    @Override
    public void someAction() {
        System.out.println("I'm doing action 1");
    }
}
