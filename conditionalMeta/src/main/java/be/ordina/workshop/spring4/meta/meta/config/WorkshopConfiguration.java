package be.ordina.workshop.spring4.meta.meta.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "be.ordina.workshop.spring4.meta.meta.components")
public class WorkshopConfiguration {
}
