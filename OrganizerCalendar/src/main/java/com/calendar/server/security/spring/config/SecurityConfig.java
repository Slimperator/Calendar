package com.calendar.server.security.spring.config;

import com.calendar.server.security.spring.impl.CustomAuthenticationProvider;
import com.calendar.server.security.spring.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by Владимир on 29.03.2017.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private CustomAuthenticationProvider authenticationProvider;

    @Autowired
    public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .authenticationProvider(authenticationProvider)
                .userDetailsService(userDetailsService)
                .passwordEncoder(getMd5PasswordEncoder());
                //.inMemoryAuthentication()
                //.withUser("user").password("password").roles("USER");
    }

    @Bean
    public Md5PasswordEncoder getMd5PasswordEncoder(){
        return new Md5PasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // включаем защиту от CSRF атак
        http.authorizeRequests()
                .antMatchers("/Calendar.html").permitAll()
                .anyRequest().authenticated()
                .and();

        http.formLogin()
                // указываем страницу с формой логина
                .loginPage("/Calendar.html")
                // указываем action с формы логина
                //.loginProcessingUrl("/j_spring_security_check")
                // указываем URL при неудачном логине
                //.failureUrl("/login?error")
                //.successForwardUrl("/Calendar.html")
                // даем доступ к форме логина всем
                .permitAll()
                .and();
        http.logout()
                // разрешаем делать логаут всем
                .permitAll()
                // указываем URL логаута
                //.logoutUrl("/logout")
                // указываем URL при удачном логауте
                //.logoutSuccessUrl("/login?logout")
                // делаем не валидной текущую сессию
                .invalidateHttpSession(true);
    }
}
