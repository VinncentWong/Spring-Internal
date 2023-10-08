package com.example.springboot;

import lombok.SneakyThrows;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.annotation.*;
import org.springframework.core.io.Resource;

import java.nio.charset.Charset;
import java.time.LocalDate;

/*
You can use @PropertySources to extract external file like
properties file(key-value)

other files should be extracted with @Value("prefix:location") and with Resource type
 */
@PropertySources({
        @PropertySource("classpath:application.properties"),
})
/*
To make our bean aware with the IoC resource like AppContext, or Bean Name, etc
You can implements BeanNameAware. Aware is the super interface.

Aware means the bean know the current IoC resource(ofc through setMethod so dont forget to make field
to save the value)

All Aware interface always have set* method
 */
@Configuration
public class UserConfiguration implements BeanNameAware, ApplicationEventPublisherAware {

    @Value("${host.name}")
    private String hostName;

    /*
    To inject other files than properties, please define Resource type
     */
    @Value("classpath:pattern.txt")
    private Resource namePattern;

    private ApplicationEventPublisher publisher;

    private User user;

    @Profile("localhost")
    @Bean(name = "userCentWong")
    @SneakyThrows
    public User userCentWong(){
        var user = new User();
        user.name = "CentWong";
        user.hostName = this.hostName;
        user.namePattern = this.namePattern.getContentAsString(Charset.defaultCharset());
        user.environment = "localhost";
        this.user = user;
        return user;
    }

    @Profile("prod")
    @Bean(name = "userCentWong")
    @SneakyThrows
    public User userCentWong2(){
        var user = new User();
        user.name = "CentWong";
        user.hostName = this.hostName;
        user.namePattern = this.namePattern.getContentAsString(Charset.defaultCharset());
        user.environment = "prod";
        this.user = user;
        return user;
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("bean name = " + name);
    }

    /*
    You can implements ApplicationEventPublisherAware or just @Autowired the ApplicationEventPublisher
     */
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        System.out.println("setApplicationEventPublisher terpanggil");
        this.publisher = applicationEventPublisher;
    }

    public void publishEvent(){
        publisher.publishEvent(
                CheckoutEvent
                        .builder()
                        .namaItem("Nama Item: CentWong -> " + user.environment)
                        .tanggalCheckout(LocalDate.now())
                        .build()
        );
    }
}
