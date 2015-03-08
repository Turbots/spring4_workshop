package be.ordina.spring4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

import java.util.Locale;
import java.util.TimeZone;

@Configuration
@ComponentScan(basePackages = "be.ordina.spring4.restapi.service")
public class AppConfig {

}