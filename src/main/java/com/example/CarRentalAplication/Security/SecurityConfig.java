package com.example.CarRentalAplication.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Autowired
    private MyUserDetailService myUserDetailService;

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(myUserDetailService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
        auth.userDetailsService(myUserDetailService);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/cars/getCars").permitAll()
                .antMatchers(HttpMethod.GET, "/localization/getLocalizations").permitAll()
                .antMatchers(HttpMethod.POST,"/client/add").permitAll()
                .antMatchers(HttpMethod.POST,"/client/register").permitAll()
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


//    @Bean
//    public PasswordEncoder getpasswordEncoder() {
//
//        return NoOpPasswordEncoder.getInstance();
//    }
}
