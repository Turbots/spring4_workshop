package be.ordina.workshop.spring4.java8.service;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import javax.annotation.Priority;

/**
 * Created by stevedezitter on 08/03/15.
 */
@Service
//@Priority(2)
@Order(2)
public class MyOtherServiceImpl implements MyService {
    @Override
    public void someAction() {
        System.out.println("I'm doing action 2");
    }
}
