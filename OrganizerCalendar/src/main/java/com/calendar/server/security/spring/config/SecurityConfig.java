package com.calendar.server.security.spring.config;

import com.calendar.server.security.spring.impl.CustomAuthenticationProvider;
import com.calendar.server.security.spring.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by Владимир on 29.03.2017.
 */
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    @Qualifier("userDetailsServiceImpl")
    private UserDetailsServiceImpl userDetailsServiceImpl;
    @Autowired
    @Qualifier("customAuthenticationProvider")
    private CustomAuthenticationProvider customAuthenticationProvider;

    @Autowired
    public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .authenticationProvider(customAuthenticationProvider)
                .userDetailsService(userDetailsServiceImpl)
                .passwordEncoder(getMd5PasswordEncoder());
                //.inMemoryAuthentication()
                //.withUser("user").password("password").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // включаем защиту от CSRF атак
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/com.Calendar.Calendar/service/auth/**").permitAll()
                .and();

        http.formLogin()
                // указываем страницу с формой логина
                .loginPage("/com.Calendar.Calendar/service/login")
                // указываем action с формы логина
                .loginProcessingUrl("/j_spring_security_check")
                // указываем URL при неудачном логине
                .failureUrl("/login")
                // Указываем параметры логина и пароля с формы логина
                .usernameParameter("j_username")
                .passwordParameter("j_password")
                // даем доступ к форме логина всем
                .permitAll()
                .and();
        http.logout()
                // разрешаем делать логаут всем
                .permitAll()
                // указываем URL логаута
                //.logoutUrl("/com.calendar.Calendar/service/login")
                // указываем URL при удачном логауте
                // делаем не валидной текущую сессию
                .invalidateHttpSession(true);
    }

    @Bean
    public UserDetailsService getUserDetailsService(){
        return new UserDetailsServiceImpl();
    }
    @Bean
    public AuthenticationProvider getAuthenticationProvider(){
        return new CustomAuthenticationProvider();
    }
    @Bean
    public Md5PasswordEncoder getMd5PasswordEncoder(){
        return new Md5PasswordEncoder();
    }

}
