package com.example.CarRentalAplication.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private UserDetailsService userDetailsService;


//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails userDetails = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("user1")
//                .roles("USER")
//                .build();
//
//        UserDetails adminDetails = User.withDefaultPasswordEncoder()
//                .username("admin")
//                .password("admin1")
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(userDetails, adminDetails);
//
//
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/cars/getCars").permitAll()
                .antMatchers(HttpMethod.GET, "/localization/getLocalizations").permitAll()
//                .antMatchers(HttpMethod.POST,"/client/add").permitAll()
                .antMatchers(HttpMethod.POST, "/booking/bookCar").hasAnyRole("USER","ADMIN")
                .antMatchers(HttpMethod.POST, "/booking/return").hasAnyRole("USER","ADMIN")
                .antMatchers(HttpMethod.GET, "/booking/getbookings").hasAnyRole("USER","ADMIN")
                .antMatchers(HttpMethod.GET, "/client/get").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/booking/getbookings").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/cars/delete").hasRole("ADMIN")
                .and()
                .formLogin().permitAll()
                .and()
                .logout().permitAll()
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }


    @Bean
    public PasswordEncoder getpasswordEncoder() {

        return NoOpPasswordEncoder.getInstance();
    }
}
