package com.bs.boot.boottest.common.config;

import com.bs.boot.boottest.common.filter.MyFilter;
import com.bs.boot.boottest.common.interceptor.MyInterceptor;
import org.hibernate.metamodel.mapping.FilterRestrictable;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan(basePackages = "com.bs.boot.boottest",
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.REGEX,
                        pattern = {"com.bs.boot.boottest.target.*"})
        }
//        , excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX,
//        pattern = {"com.bs.boot.boottest.target.*3"
//        })
)
@EnableAspectJAutoProxy
@EnableCaching
public class WebMvcConfig implements WebMvcConfigurer {
    /*
     * Resources 경로를 설정
     * Filter 등록
     * 인터셉터등록
     * */

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    // 인터셉터 등록하기


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor()).addPathPatterns("/**");
    }

    @Bean
    public MyInterceptor myInterceptor() {
        return new MyInterceptor();
    }

    // 필터 등록하기

    @Bean
    FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new MyFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }

    // cors 차단  허용하기


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // cors예외를 설정해줌
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE");

        // javaScript가 막는것을 서버 설정을 해주어 요청이 가도록 한다.

    }
}
