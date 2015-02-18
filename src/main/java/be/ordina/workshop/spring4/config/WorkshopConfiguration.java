package be.ordina.workshop.spring4.config;

import be.ordina.workshop.spring4.components.FakeMessageService;
import be.ordina.workshop.spring4.components.LazyService;
import be.ordina.workshop.spring4.components.MessageService;
import be.ordina.workshop.spring4.components.RealMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class WorkshopConfiguration {

    @Autowired
    private Environment environment;

    @Bean
    public MessageService getMessageService() {
        String appEnv = environment.getProperty("environment", "");
        if (appEnv.equals("PRD")) {
            return new RealMessageService();
        } else {
            return new FakeMessageService();
        }
    }

    @Bean
    public LazyService getLazyServiceProxy() {
        return new LazyService();
    }
}
