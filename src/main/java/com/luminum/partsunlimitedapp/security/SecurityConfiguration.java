package com.luminum.partsunlimitedapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource datasource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(datasource)
                .usersByUsernameQuery("SELECT username,password,enabled "
                        + "FROM users "
                        + "WHERE username = ?")
                .authoritiesByUsernameQuery("SELECT username,authority "
                        + "FROM authorities "
                        + "WHERE username = ?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/v1/parts**").hasAuthority("ROLE_WAREHOUSE")
                .antMatchers("/api/v1/articles**").hasAuthority("ROLE_SALES")
                .antMatchers("/api/v1/actions**").hasAuthority("ROLE_SALES")
                .antMatchers("/parts**").hasAnyAuthority("ROLE_WAREHOUSE", "ROLE_CUSTOMER")
                .antMatchers("/articles**").hasAnyAuthority("ROLE_SALES", "ROLE_CUSTOMER")
                .antMatchers("/actions**").hasAnyAuthority("ROLE_SALES", "ROLE_CUSTOMER")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .and()
                .httpBasic();

        http.csrf().disable();
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**");
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
