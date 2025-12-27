package com.example.demo.config;

import com.example.demo.servlet.SimpleHelloServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletConfig {
    
    @Bean
    public ServletRegistrationBean<HelloServlet> helloServletRegistration() {
        ServletRegistrationBean<HelloServlet> registration = 
            new ServletRegistrationBean<>(new HelloServlet(), "/hello-servlet");
        registration.setName("SimpleHelloServlet");
        return registration;
    }
}
