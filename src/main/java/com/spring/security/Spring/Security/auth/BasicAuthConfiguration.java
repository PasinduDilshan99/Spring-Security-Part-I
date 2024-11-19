package com.spring.security.Spring.Security.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class BasicAuthConfiguration {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.authorizeHttpRequests().anyRequest().authenticated();
//        httpSecurity.formLogin();
//        httpSecurity.httpBasic();
//        return httpSecurity.build();

        httpSecurity.authorizeHttpRequests(auth -> {
            auth.anyRequest().authenticated();
        });
        httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        httpSecurity.httpBasic();
        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().sameOrigin();
        return httpSecurity.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails userDetails = User.withUsername("abc")
//                .password("{noop}def")
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(userDetails);
//    }

    @Bean
    public DataSource dataSource(){
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource){
        UserDetails user1 = User.withUsername("abc")
                .password("{noop}abc")
                .roles("ADMIN")
                .build();

        UserDetails user2 = User.withUsername("def")
                .password("{noop}def")
                .roles("USER")
                .build();
       JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
       jdbcUserDetailsManager.createUser(user1);
       jdbcUserDetailsManager.createUser(user2);
       return jdbcUserDetailsManager;
    }
}
