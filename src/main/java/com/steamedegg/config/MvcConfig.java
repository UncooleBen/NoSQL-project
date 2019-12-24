package com.steamedegg.config;


import com.steamedegg.databaseservice.AppDao;
import com.steamedegg.databaseservice.BlogDao;
import com.steamedegg.databaseservice.impl.AppDaoImpl;
import com.steamedegg.databaseservice.impl.BlogDaoImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@EnableWebMvc
@Configuration
@ComponentScan({"com.steamedegg"})
@PropertySource("classpath:application.properties")
public class MvcConfig implements WebMvcConfigurer {
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver vr = new InternalResourceViewResolver();
        vr.setPrefix("/WEB-INF/jsp/");
        vr.setSuffix(".jsp");
        return vr;
    }

    @Bean
    public StandardServletMultipartResolver getStandardServletMultipartResolver() {
        return new StandardServletMultipartResolver();
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Bean
    public AppDao getAppDao() {
        return (new AppDaoImpl());
    }

    @Bean
    public BlogDao getBlogDao() {
        return new BlogDaoImpl();
    }

}
