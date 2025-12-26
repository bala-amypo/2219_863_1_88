package com.example.demo.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.servlet.SimpleHelloServlet;

@Configuration
public class ServletConfig {

    @Bean
    public ServletRegistrationBean<SimpleHelloServlet> helloServlet() {
        return new ServletRegistrationBean<>(new SimpleHelloServlet(), "/hello-servlet");
    }
}
