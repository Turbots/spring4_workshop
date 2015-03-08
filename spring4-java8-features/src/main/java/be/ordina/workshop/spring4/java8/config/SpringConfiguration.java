package be.ordina.workshop.spring4.java8.config;

import be.ordina.workshop.spring4.java8.model.Customer;
import be.ordina.workshop.spring4.java8.service.MyOtherServiceImpl;
import be.ordina.workshop.spring4.java8.service.MyService;
import be.ordina.workshop.spring4.java8.service.MyServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.annotation.Priority;
import java.util.List;

/**
 * Created by stevedezitter on 19/02/15.
 */
@Configuration
@ComponentScan("be.ordina.workshop.spring4.java8")
@EnableAsync
@EnableScheduling
@EnableWebMvc
@PropertySource("classpath:/properties/someProperties.properties")
@PropertySource("classpath:/properties/someProperties2.properties")
public class SpringConfiguration extends WebMvcConfigurationSupport{

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver =
                new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setExposeContextBeansAsAttributes(true);
        return resolver;
    }
    @Override
    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

//    @Bean
//    @Order(1)
//    public MyService service1() {
//        return new MyServiceImpl();
//    }
//
//    @Bean
//    @Order(3)
//    public MyService service2() {
//        return new MyOtherServiceImpl();
//    }

    @Bean
    @Qualifier("myExecutor")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(3);
        executor.setMaxPoolSize(5);
        executor.setQueueCapacity(7);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAllowCoreThreadTimeOut(false);

        return executor;
    }

    @Bean
    public PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public Customer customer() {
        return new Customer();
    }
}