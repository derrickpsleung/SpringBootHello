package com.hello.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.hello.handler.DefaultAuthenticaionSuccessHandler;
import com.hello.service.HelloUserDetailsService;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;
    
    @Autowired
    private DefaultAuthenticaionSuccessHandler defaultAuthenticaionSuccessHandler;
    
	@Autowired
	private HelloUserDetailsService helloUsrDtlSvc;

    // roles admin allow to access /admin/**
    // roles user allow to access /user/**
    // custom 403 access denied handler
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
					.antMatchers("/login", "/db", "/css/**", "/webjars/**").permitAll()
//					.antMatchers("/admin/**").hasAnyRole(Role.ADMIN.getValue())
//					.antMatchers("/user/**").hasAnyRole(Role.NORMAL_USER.getValue())
					.anyRequest().authenticated()
                .and()
                .formLogin()
					.loginPage("/login")
					.usernameParameter("username").passwordParameter("password")
					.successHandler(defaultAuthenticaionSuccessHandler)
					.and()
                .logout()
					.permitAll()
					.and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
    	auth.userDetailsService(helloUsrDtlSvc);
    }
}