package org.personal.drawingsite.security;

import org.personal.drawingsite.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);

    @Autowired
    private UserService userService;

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(encoder());
        return auth;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }


    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/**/*.{js,html,css}");
        web.debug(false);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/", "/register", "/login", "/references", "/active-requests").permitAll()
                .antMatchers("/request").hasAnyRole("USER", "ADMIN")
                .antMatchers("/accept-requests", "/focus/**", "/accept/**", "/done").hasRole("ADMIN")
                .and()
                .formLogin()
                .loginProcessingUrl("/login")
                .permitAll()
                .and()

                .logout().deleteCookies("remember-me")
                .logoutUrl("/logout").logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID")
                .permitAll()
                .and()

                .rememberMe()
                .and()

                .csrf().disable();
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
