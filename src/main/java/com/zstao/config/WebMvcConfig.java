package com.zstao.config;



import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;


public class WebMvcConfig extends WebMvcConfigurationSupport {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*")
                .allowedMethods("POST","GET","PUT","OPTIONS","DELETE").maxAge(360000).allowCredentials(true);
        super.addCorsMappings(registry);
    }
    /**
     * 解决跨域问题覆盖默认的SpringMVC配置，会导致Swagger2框架的页面赵不到故而需要加入该配置解决路径访问的问题
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }

//    @Bean
//    public HandlerInterceptor apiRequestIntercepter(){
//        return new ApiRequestIntercepter();
//    }

    /**
     * 添加自定义拦截器
     * @param registry
     */
//    @Override
//    protected void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(apiRequestIntercepter())
//                .addPathPatterns("/**")
//                .excludePathPatterns("/tologin")
//                .excludePathPatterns("/login");
//        super.addInterceptors(registry);
//    }
}
