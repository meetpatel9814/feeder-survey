package com.feeder.feedersurvey.config;

import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    private static final String UI_PATH_PREFIX = "/dist/feeder-survey/";


    /*  @Override public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.
      }*/
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/index.html");
        registry.addViewController("404.html").setViewName("forward:/index.html");
        registry.addViewController("/error").setViewName("forward:/index.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    @Bean
    public org.springframework.web.servlet.ViewResolver getViewResolver() {
        System.out.println("------------VIEW --------------");
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setSuffix(".html");
        internalResourceViewResolver.setPrefix(UI_PATH_PREFIX);
        return internalResourceViewResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        System.out.println("----------------- Resource -------------------");
        registry.addResourceHandler("/**.html").addResourceLocations(UI_PATH_PREFIX);
        registry.addResourceHandler("/**.js").addResourceLocations(UI_PATH_PREFIX);
        registry.addResourceHandler("/**.css").addResourceLocations(UI_PATH_PREFIX);
        registry.addResourceHandler("/static/**").addResourceLocations(UI_PATH_PREFIX);
        registry.addResourceHandler("/assets/json/**.json").addResourceLocations(UI_PATH_PREFIX+"assets/json/");

        //registry.addResourceHandler("/**").addResourceLocations(UI_PATH_PREFIX);
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer handlerConfigurer) {
        System.out.println("----------------- Config -------------------");
        handlerConfigurer.enable();
    }

    @Bean
    WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> enableDefaultServlet() {
        return (factory) -> factory.setRegisterDefaultServlet(true);
    }
}