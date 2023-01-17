package com.walsin.coil.config.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
public class MyMvcConfig implements WebMvcConfigurer {

    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/META-INF/resources/", "classpath:/resources/",
            "classpath:/static/", "classpath:/public/", "classpath:/webapps/" };

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        WebMvcConfigurer.super.addInterceptors(registry);
//        registry.addInterceptor(new Interceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns("/emp/toLogin","/emp/login","/js/**","/css/**","/images/**");
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);

        // 解决升级swagger3.0.3后，swagger无法访问的问题
        ///registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");
        //registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        WebMvcConfigurer.super.addCorsMappings(registry);
        registry.addMapping("/**")
                //设置允许跨域请求的域名,二选一allowedOrigins或allowedOrigins
                //.allowedOrigins("*") //注意access-control-allow-origin时,allowedOrigins不能为*
                //.allowedOrigins("域名") //如果是localhost则很难配置，因为在跨域请求的时候，外部域的解析可能是localhost、127.0.0.1、主机名
                .allowedOriginPatterns("*") // 所有的外部域都可跨域访问
                //放行哪些原始域(请求方式)
                .allowedMethods("GET","HEAD","POST","PUT","DELETE","OPTIONS")
                //设置允许的请求头
                .allowedHeaders("*")
                //暴露哪些头部信息（因为跨域访问默认不能获取全部头部信息）
                .exposedHeaders("access-control-allow-headers",
                        "access-control-allow-methods",
                        "access-control-allow-origin",
                        "access-control-max-age",
                        "X-Frame-Options")
                //是否允许证书(是否支持跨域用户凭证),不再默认开启
                .allowCredentials(true)
                //跨域允许时间
                .maxAge(3600);
    }

//    @Bean
//    // 配置攔截器
//    public DemoInterceptor demoInterceptor() {
//        return new DemoInterceptor();
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) { // 覆寫addInterceptors方法，註冊攔截器
//        registry.addInterceptor(demoInterceptor());
//    }

}

