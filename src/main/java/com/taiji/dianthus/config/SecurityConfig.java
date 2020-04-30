package com.taiji.dianthus.config;

import com.taiji.dianthus.security.DianthusUserDetailServiceImpl;
import com.taiji.dianthus.security.annotation.EnablePmSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsUtils;

/**
 * @ClassName SecurityConfig
 * @Description
 * @Author H.M
 * @Date 2019/10/21
 */
@Configuration
@EnableWebSecurity
@EnablePmSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private DianthusUserDetailServiceImpl userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http.headers().frameOptions().disable();
//        http.csrf().disable().formLogin().permitAll().
//                and().authorizeRequests().antMatchers
//                ("/login").permitAll().and().
//                authorizeRequests().anyRequest().authenticated();
        http.authorizeRequests().filterSecurityInterceptorOncePerRequest(true)
                .antMatchers("/403").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/image").permitAll()
                .antMatchers("/ssoLogin").permitAll()
                .antMatchers("/ssoLogin/*").permitAll()
                .anyRequest().fullyAuthenticated()
                //支持跨域
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                // // all others need login
                .and().formLogin().loginPage("/login").failureUrl("/login?error")
        // config
         .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        // .and().exceptionHandling().accessDeniedHandler(myAccessDeniedHandler)
        ;

//        http.addFilterAfter(taijiFilterSecurityInterceptor, FilterSecurityInterceptor.class)
//                .rememberMe().key("logintoken")
//                .and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 解决静态资源被拦截的问题
        web.ignoring().antMatchers("/laydate/**", "/js/**", "/css/**", "/images/**");
        // swagger start TODO
        web.ignoring().antMatchers("/doc.html");
        web.ignoring().antMatchers("/swagger-ui.html");
        web.ignoring().antMatchers("/swagger-resources/**");
        web.ignoring().antMatchers("/images/**");
        web.ignoring().antMatchers("/webjars/**");
        web.ignoring().antMatchers("/configuration/ui");
        web.ignoring().antMatchers("/configuration/security");

        // swagger end TODO
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    /**
     * 解决spring security 5.x无法注入AuthenticationManager的问题
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
