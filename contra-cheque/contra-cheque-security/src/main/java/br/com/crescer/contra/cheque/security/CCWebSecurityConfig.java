/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.contra.cheque.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author matheus.schmitz
 */
@Configuration
public class CCWebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    CCDetailsService ccUserDetailsService;

    @Override
    protected void configure(final HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests().antMatchers().permitAll().anyRequest().authenticated()
                .and().formLogin().loginPage("/login").defaultSuccessUrl("/home", true).failureUrl("/login?error").permitAll()
                .and().logout().logoutUrl("/logout").deleteCookies("JSESSIONID").permitAll()
                .and().csrf().disable();
    }

    @Autowired
    public void setDetailsService(final AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(ccUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
