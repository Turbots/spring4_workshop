package be.ordina.spring4.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * @author Ken Coenen
 */
@Configuration
@ComponentScan(basePackages = "be.ordina.spring4", excludeFilters = {
  @ComponentScan.Filter(value = Controller.class, type = FilterType.ANNOTATION),
  @ComponentScan.Filter(value = Configuration.class, type = FilterType.ANNOTATION)
})
public class AppConfig {
}
