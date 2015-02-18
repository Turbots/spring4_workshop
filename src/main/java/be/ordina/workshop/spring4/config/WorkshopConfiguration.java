package be.ordina.workshop.spring4.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "be.ordina.workshop.spring4.components")
public class WorkshopConfiguration {
}
