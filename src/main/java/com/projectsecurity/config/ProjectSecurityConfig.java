package com.projectsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableSwagger2
public class ProjectSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * /myAccount - Secured /myBalance - Secured /myLoans - Secured /myCards -
     * Secured /notices - Not Secured /contact - Not Secured
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        /**
         * Default configurations which will secure all the requests
         */

/*
         http .authorizeRequests() .anyRequest().authenticated() .and()
         .formLogin().and() .httpBasic();
*/

        /**
         * Custom configurations as per our requirement
         */


        http.cors().configurationSource(new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest httpServletRequest) {
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                config.setAllowedMethods(Collections.singletonList("*"));
                config.setAllowCredentials(true);
                config.setAllowedHeaders(Collections.singletonList("*"));
                config.setMaxAge(3600L);
                return config;
            }
        }).and().csrf().ignoringAntMatchers("/contact").csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and().
                authorizeRequests() .antMatchers("/myAccount").authenticated()
          .antMatchers("/myBalance").authenticated()        //.hasAuthority("UPDATE")
          .antMatchers("/myLoans").hasAnyRole("USER", "ADMIN")
                .antMatchers("/myCards").hasRole("ROOT")
                .antMatchers("/notices").permitAll()
                .antMatchers("/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/webjars/**").permitAll()
          .antMatchers("/contact").permitAll() .and() .formLogin().and() .httpBasic();


        /**
         * Configuration to deny all the requests
         */

        /*
         * http .authorizeRequests() .anyRequest().denyAll() .and() .formLogin().and()
         * .httpBasic();
         */

        /**
         * Configuration to permit all the requests
         */

        http .authorizeRequests() .anyRequest().permitAll().and() .formLogin().and()
                .httpBasic();


    }
/*
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password("12345").authorities
                 ("admin").and(). withUser("user").password("12345").authorities("read").and()
                 .passwordEncoder(NoOpPasswordEncoder.getInstance()); }
*/

/*
      @Override protected void configure(AuthenticationManagerBuilder auth) throws
      Exception { InMemoryUserDetailsManager userDetailsService = new
      InMemoryUserDetailsManager();
          UserDetails user = User.withUsername("admin").password("12345").authorities("admin").build();
          UserDetails user1 = User.withUsername("user").password("12345").authorities("read").build();

      userDetailsService.createUser(user);
      userDetailsService.createUser(user1);
      auth.userDetailsService(userDetailsService);
      }*/

/*

      @Bean public UserDetailsService userDetailsService(DataSource dataSource) {
      return new JdbcUserDetailsManager(dataSource); }

*/

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
