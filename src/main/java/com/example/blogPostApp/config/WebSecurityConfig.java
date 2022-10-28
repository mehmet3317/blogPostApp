package com.example.blogPostApp.config;

import com.example.blogPostApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig {

    @Autowired
    private UserService userService;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private static final String[] WHITELIST = {
            "/register/**",
            "/h2-console/**",
            "/"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {


         String[] PUBLIC_MATCHERS = {
                "/**",
                "/js/**",
                "/webjars/**",
                "/resources/static/**",
        };

        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(WHITELIST).permitAll()
                .antMatchers("/posts/*").permitAll()
                .antMatchers("/page/*").permitAll()
                .antMatchers("/users/*").permitAll()
                .antMatchers("/users/*/edit").permitAll()
                .antMatchers("/comment/*/delete").permitAll()
                .antMatchers("/search?keyword=").permitAll()
                .antMatchers(PUBLIC_MATCHERS).permitAll()
                .antMatchers("blogHomeUser/*").permitAll()

                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("email").passwordParameter("password")
                .defaultSuccessUrl("/", true).failureUrl("/login?error")
                .permitAll()
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/login?logout")
                .and()
                .httpBasic();

        // need to add for h2-console access
        http.csrf().disable();
        http.headers().frameOptions().disable();

        return http.build();
    }

}