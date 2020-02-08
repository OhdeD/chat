
package com.chat.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder())
                .usersByUsernameQuery(
                        "SELECT mail, password, logged FROM chat_user WHERE mail=?")
                .authoritiesByUsernameQuery(
                        "SELECT r.role, cu.mail FROM chat_user cu, roles r WHERE cu.mail = ? AND cu.user_id = r.chat_user_user_id");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/v1/chat/new", "/v1/currentUser").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/v1/chat/admin", "/v1/chat/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .and().rememberMe()
                .tokenValiditySeconds(1209600).and().csrf().disable()
                .headers().frameOptions().disable();
    }
}