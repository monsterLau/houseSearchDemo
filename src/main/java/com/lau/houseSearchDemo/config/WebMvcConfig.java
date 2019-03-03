package com.lau.houseSearchDemo.config;

import com.lau.houseSearchDemo.service.CustomUserService;
import com.lau.houseSearchDemo.utils.MD5Util;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Configuration
public class WebMvcConfig extends WebSecurityConfigurerAdapter {
    @Bean
    UserDetailsService customUserService() {
        return new CustomUserService();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/user/**","searchHouse","searchResult").hasRole("USER")//需要USER角色才能访问
                .antMatchers("/admin/**").hasRole("ADMIN")//需要ADMIN角色才能访问
                .and().formLogin().loginPage("/login").defaultSuccessUrl("/index").failureUrl("/login?error")
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/index");


        //解决非thymeleaf的form表单提交被拦截问题
        http.csrf().disable();

    }

    //MD5加密密码
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService()).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return MD5Util.encode((String) charSequence);
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodePassword) {
                return encodePassword.equals(MD5Util.encode((String) rawPassword));
            }
        });
    }

}
