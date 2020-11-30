package com.example.zuulserver.security;

import com.example.commonservice.security.JwtConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity    // Enable security config. This annotation denotes config for spring security.
public class SecurityTokenConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtConfig jwtConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                // make sure we use stateless session; session won't be used to store user's state.
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // handle an authorized attempts
                .exceptionHandling().authenticationEntryPoint((req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                // Add a filter to validate the tokens with every request
                .addFilterAfter(new JwtTokenAuthenticationFilter(jwtConfig), UsernamePasswordAuthenticationFilter.class)
                // authorization requests config
                .authorizeRequests()
                // allow all who are accessing "auth" service
                .antMatchers(HttpMethod.POST, jwtConfig.getUri()).permitAll()
                /* Request to User controller*/
//
                /* Request to User controller*/

                .antMatchers(HttpMethod.GET,"/api/user/**").authenticated()
                .antMatchers(HttpMethod.POST,"/api/user/signUp/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/user/username/**").hasAnyRole("ADMIN", "REPAIRER")
                .antMatchers(HttpMethod.DELETE,"/api/user/**").hasAnyRole("ADMIN")

                /* Request to Finished_Device controller*/

                .antMatchers(HttpMethod.GET,"/api/finishedDevices/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/finishedDevices/**").hasRole("REPAIRER")
                .antMatchers(HttpMethod.POST, "/api/finishedDevices/{id}").hasRole("USER")

                /* Request to Device controller*/

                .antMatchers(HttpMethod.GET,"/api/device/**").hasAnyRole("REPAIRER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/api/device/**").permitAll()

                /* Request to Center controller*/

                .antMatchers("/api/center/**").authenticated()

                /* Request to Center_rep. controller */

                .antMatchers(HttpMethod.POST, "/api/centerRep/{centerId}/{repId}/**").hasAnyRole("REPAIRER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/api/centerRep/remove/**").hasAnyRole("REPAIRER", "ADMIN")
                // any other requests must be authenticated
                .anyRequest().authenticated();
    }

    @Bean
    public JwtConfig jwtConfig() {
        return new JwtConfig();
    }
}
