package com.cxp.openadmin.configuration;

import com.cxp.openadmin.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.unit.DataSize;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.MultipartConfigElement;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private Environment env;

    // 정적 자원(Static Resource)
    /* 정적자원이란 html, css, image, javascript와 같이 컴파일이 필요없는 파일들을 말합니다.
    스프링 부트에서 Web MVC 설정을 담당하는 WebMvcAutoConfiguration
    클래스는 기본 설정으로 웹 리소스 폴더에서 정적 자원들을 찾습니다.
    이 때 템플릿 엔진(Thymeleaf, Groovy, FreeMarker)등을 추가하면
    src/main/resources/templates 경로를 기본 경로로 인식하게 됩니다. */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/front/")
                .addResourceLocations("classpath:/assets/")
                .addResourceLocations("classpath:/META-INF/resources/")
                .addResourceLocations("file:"+env.getProperty("resource.upload-location"));
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TokenInterceptor()).excludePathPatterns("/**");
        WebMvcConfigurer.super.addInterceptors(registry);
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //단일 파일 최대-30MB
        factory.setMaxFileSize(DataSize.ofMegabytes(30));
        //방법이 다운그레이드되었습니다.
        //factory.setMaxRequestSize("30MB");
        /// 총 업로드 데이터 크기 설정-120MB
        factory.setMaxRequestSize(DataSize.ofMegabytes(120));
        return factory.createMultipartConfig();
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

}
