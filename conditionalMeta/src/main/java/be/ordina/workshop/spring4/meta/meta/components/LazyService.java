package be.ordina.workshop.spring4.meta.meta.components;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Lazy
public class LazyService {

    @PostConstruct
    private void init() {
        System.out.println("LazyService getting initialized...");
    }

    public void doSomething() {
        System.out.println("Something done.");
    }
}
