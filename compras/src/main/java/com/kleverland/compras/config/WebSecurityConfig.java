package com.kleverland.compras.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.kleverland.compras.service.UserDetailsServiceImpl;
 
@Configuration
@EnableWebSecurity(debug = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
 
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }
     
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
     
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
      .cors().and().csrf().disable()
    	.httpBasic()
        .and()
        .authorizeRequests()
//        .antMatchers(HttpMethod.GET, "/usuarios").hasAnyAuthority("ADMIN")
//        .antMatchers(HttpMethod.POST, "/usuario").hasAnyAuthority("ADMIN")
        .antMatchers("/produtos/**").hasAnyAuthority("USER", "ADMIN")
        .antMatchers("/categorias/**").hasAnyAuthority("USER", "ADMIN")
        .antMatchers("/categoria/**").hasAnyAuthority("ADMIN")
        .antMatchers("/usuarios").permitAll()
        .antMatchers("/usuario/**").hasAnyAuthority("ADMIN")
        .antMatchers("/usuarios").hasAnyAuthority("ADMIN")
        .anyRequest().authenticated()
        .and()
        .exceptionHandling().accessDeniedPage("/403")
        .and()
        .headers().frameOptions().disable();
        ;
    }
}