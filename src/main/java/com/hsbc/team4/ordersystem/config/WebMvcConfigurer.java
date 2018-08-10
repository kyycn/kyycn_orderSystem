package com.hsbc.team4.ordersystem.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.hsbc.team4.ordersystem.common.adapt.BeanAdapter;
import com.hsbc.team4.ordersystem.common.factory.UUIDFactory;
import com.hsbc.team4.ordersystem.common.utils.BeanValidator;
import com.hsbc.team4.ordersystem.common.utils.LoggerUtil;
import com.hsbc.team4.ordersystem.common.utils.ResponseResults;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import javax.servlet.MultipartConfigElement;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.config
 * @Description :
 * @Date : 2018/8/3
 */
@Configuration
public class WebMvcConfigurer implements org.springframework.web.servlet.config.annotation.WebMvcConfigurer{

    /**
     * accept static resource
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        org.springframework.web.servlet.config.annotation.WebMvcConfigurer.super.addResourceHandlers(registry);
    }

    @Bean
    public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
        ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet);
        registration.setMultipartConfig(multipartConfigElement());
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
        return registration;
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("10MB");
        factory.setMaxRequestSize("50MB");
        return factory.createMultipartConfig();
    }


    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        return new StringHttpMessageConverter(
                Charset.forName("UTF-8"));
    }

    /**
     * fastjson config
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        org.springframework.web.servlet.config.annotation.WebMvcConfigurer.super.configureMessageConverters(converters);
        List<MediaType> supportedMediaTypes = new ArrayList<>();
        supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        fastJsonHttpMessageConverter.setSupportedMediaTypes(supportedMediaTypes);
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat,SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteNullNumberAsZero,SerializerFeature.WriteNullBooleanAsFalse,SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullStringAsEmpty,SerializerFeature.WriteNullListAsEmpty,SerializerFeature.WriteDateUseDateFormat,
                SerializerFeature.BrowserCompatible,SerializerFeature.WriteNonStringKeyAsString);
        converters.add(fastJsonHttpMessageConverter);
        converters.add(responseBodyConverter());
    }

    @Bean
    public ResponseResults responseResults(){
        return new ResponseResults();
    }
    @Bean
    public UUIDFactory uuidFactory(){
        return new UUIDFactory();
    }
    @Bean
    public BeanValidator beanValidator(){
        return new BeanValidator();
    }

    @Bean
    public BeanAdapter beanAdapter(){
        return new BeanAdapter();
    }
    @Bean
    public LoggerUtil loggerUtil(){
        return new LoggerUtil();
    }


}
