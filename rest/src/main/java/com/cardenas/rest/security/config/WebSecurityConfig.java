package com.cardenas.rest.security.config;

import com.cardenas.rest.jwt.JwtAuthorizationFilter;
import com.cardenas.rest.jwt.JwtEntryPoint;
import com.cardenas.rest.service.impl.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@AllArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    private UserDetailsServiceImpl userDetailsService;
    private JwtEntryPoint jwtEntryPoint;

    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter() {
        return new JwtAuthorizationFilter();
    }


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/ignore1", "/ignore2");
    }


    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean()
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }*/

    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable().httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/api/auth/**")
                .permitAll()
                .antMatchers("/api/users/**")
                .authenticated()
                .anyRequest()
                .authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(jwtEntryPoint).
                and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.addFilterBefore(jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}


    /*



    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }


    protected void configure(HttpSecurity httpSecurity)throws Exception{
        httpSecurity.csrf().disable().httpBasic()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/api/auth/**")
                .permitAll()
                .antMatchers("/api/users/**")
                .permitAll()
                .anyRequest()
                .authenticated();
                httpSecurity.addFilter(jwtAuthorizationFilter());
    }


    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter() throws Exception {
        return new JwtAuthorizationFilter(this.authenticationManager());
    }

    @Bean
    public FilterRegistrationBean <MyFilter> myFilter(){
        FilterRegistrationBean<MyFilter>filterRegistrationBean= new FilterRegistrationBean<>();
            filterRegistrationBean.setFilter(new MyFilter());
            filterRegistrationBean.addUrlPatterns(ExceptionResource.EXCEPTIONS + ExceptionResource.MY_FILTER);
            return filterRegistrationBean;

    }

    @Bean
    public FilterRegistrationBean<TimeAccessFilter>timeAccessFilter(){
        FilterRegistrationBean<TimeAccessFilter>filterRegistrationBean=new FilterRegistrationBean<TimeAccessFilter>();
        filterRegistrationBean.setFilter(new TimeAccessFilter());
        filterRegistrationBean.addUrlPatterns(ExceptionResource.EXCEPTIONS + ExceptionResource.OUT_OF_TIME);
        filterRegistrationBean.setOrder(Ordered.LOWEST_PRECEDENCE-1);
        return filterRegistrationBean;

    }



}
*/
