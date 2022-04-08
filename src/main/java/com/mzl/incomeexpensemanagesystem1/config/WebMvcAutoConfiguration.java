package com.mzl.incomeexpensemanagesystem1.config;

import com.mzl.incomeexpensemanagesystem1.interceptor.FileTypeInterceptor;
import com.mzl.incomeexpensemanagesystem1.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName :   Web
 * @Description: web配置类
 * @Author: v_ktlema
 * @CreateDate: 2021/12/24 19:57
 * @Version: 1.0
 */
@Configuration
public class WebMvcAutoConfiguration implements WebMvcConfigurer {

    /**
     * 文件类型请求拦截器bean到容器
     * @return
     */
    @Bean
    public FileTypeInterceptor fileTypeInterceptor(){
        return new FileTypeInterceptor();
    }

    /**
     * 登录请求拦截器bean到容器
     * @return
     */
    @Bean
    public LoginInterceptor loginInterceptor(){
        return new LoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/doc.html",
                        "/swagger-ui.html",
                        "/csrf",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/v2/api-docs",
                        "/error",
                        "/webjars/**",
                        "/**/favicon.ico",
                        "/user/userLogin",
                        "/admin/adminLogin",
                        "/admin/**", //不拦截管理员的请求
                        "/system/**",  //不拦截管理员系统的请求
                        "/user/register",
                        "/user/findBackPassword",
                        "/code/**",
                        "/actuator/**",  //放行所有actuator系统监控请求
                        "/assets/**",
                        "/applications/**",
                        "/instances/**",
                        "/dome",
                        "/tone",
                        "/generate/**",
                        "/timbre_audition/**",
                        "/user/**",
                        "/index.jsp",
                        "/regist.jsp",
                        "/userManage/**", //<!-- 后台的全部忽略 -->
                        "/categoryManage/**",
                        "/newsManage/**",
                        "/checkCode.action",
                        "/emailCode.action",
                        "/sendMsg.action",
                        "/test/**",
                        "/static/**",
                        "/actuator/**",
                        "/targets",
                        "/targets/**",
                        "/static/**",
                        "/api/**",
                        "/",
                        "/incomeExpense/"
                );

        registry.addInterceptor(fileTypeInterceptor())
                .addPathPatterns("/**");
//                .excludePathPatterns(
//                        "/doc.html",
//                        "/swagger-ui.html",
//                        "/csrf",
//                        "/configuration/ui",
//                        "/swagger-resources/**",
//                        "/configuration/security",
//                        "/v2/api-docs",
//                        "/error",
//                        "/webjars/**",
//                        "/**/favicon.ico",
//                        "/user/**",  //不拦截普通用户的请求
//                        "/announcement/**",
//                        "/budget/**",
//                        "/iEStatistic/**",
//                        "/iECategory/**",
//                        "/iERecord/**",
//                        "/iEStatistic/**",
//                        "/memorandum/**",
//                        "/news/**",
//                        "/userNews/**",
//                        "/wishList/**",
//                        "/feedback/**",
//                        "/admin/adminLogin",
//                        "/user/register",
//                        "/user/findBackPassword",
//                        "/code/**",
//                        "/actuator/**",  //放行所有actuator系统监控请求
//                        "/assets/**",
//                        "/applications/**",
//                        "/instances/**",
//                        "/dome",
//                        "/tone",
//                        "/generate/**",
//                        "/timbre_audition/**"
//                );

    }


}
