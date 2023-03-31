package mx.edu.utez.SIBLAB.utils;

import mx.edu.utez.SIBLAB.controller.access.CustomAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    @Autowired
    private CustomAuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private AuthenticationEntryPoint customAuthenticationEntryPoint;
    @Autowired
    private CustomLogoutSuccessHandler logoutSuccessHandler;

    @Override
    public void configure(AuthenticationManagerBuilder build) throws Exception {
        build.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors()
                .and()
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/api-siblab/login/")
                .permitAll();



        http
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/api-siblab/login/").permitAll()
                    .antMatchers("/api-siblab/session/").permitAll()
                    .antMatchers(HttpMethod.POST, "/api-siblab/user/").permitAll()
                    .antMatchers(HttpMethod.GET, "/api-siblab/classroom/").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .exceptionHandling()
                    .authenticationEntryPoint(this.customAuthenticationEntryPoint)
                    .and()
                .formLogin()
                    .loginProcessingUrl("/api-siblab/login/").permitAll()
                    .successHandler(this.customAuthenticationSuccessHandler)
                    .failureHandler(this.authenticationFailureHandler)
                    .permitAll()
                    .and()
                .logout()
                    .logoutUrl("/api-siblab/logout/")
                    .logoutSuccessHandler(this.logoutSuccessHandler)
                    .permitAll()
                    .deleteCookies("JSESSIONID")
                    .invalidateHttpSession(true)
                    .and()
                .sessionManagement()
                    //.maximumSessions(1)
        ;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
