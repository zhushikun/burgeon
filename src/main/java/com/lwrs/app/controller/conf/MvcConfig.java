package com.lwrs.app.controller.conf;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.lwrs.app.interceptor.BackStageInterceptor;
import com.lwrs.app.interceptor.LoginInterceptor;
import com.lwrs.app.interceptor.TempLoginInterceptor;
import com.lwrs.app.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
@EnableWebMvc
@ComponentScan
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private FileService fileService;

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.registerModule(new JavaTimeModule());
        jackson2HttpMessageConverter.setObjectMapper(mapper);
        jackson2HttpMessageConverter.setPrettyPrint(true);
        converters.add(jackson2HttpMessageConverter);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/MP_verify_NPewJUS3uKWvGcqV.txt").addResourceLocations("classpath:/static/MP_verify_NPewJUS3uKWvGcqV.txt");
        registry.addResourceHandler("/static/img/**").addResourceLocations("classpath:/static/img/");
        registry.addResourceHandler("/static/css/**").addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/static/js/**").addResourceLocations("classpath:/static/js/");
        String pictureDir = "file:" + fileService.getPictureDir() + "/";
        registry.addResourceHandler("/picture/**").addResourceLocations(pictureDir);
    }
//
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/login").setViewName("login");
//        registry.addViewController("/register").setViewName("register");
//    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
//        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/wx/oauth");
        registry.addInterceptor(new TempLoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/wx/oauth");
        registry.addInterceptor(new BackStageInterceptor()).addPathPatterns("/backstage/**").excludePathPatterns("/backstage/home", "/backstage/ajax/access-check");

    }
}
