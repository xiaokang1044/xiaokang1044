package customer.zdep_jobs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class AppSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
        
        // .securityMatcher(AntPathRequestMatcher.antMatcher("/images/**"))
        //   .securityMatcher(AntPathRequestMatcher.antMatcher("/public/**"))
          .securityMatcher(AntPathRequestMatcher.antMatcher("/odata/**"))
          .securityMatcher(AntPathRequestMatcher.antMatcher("/rest/**"))
          .csrf(c -> c.disable()) // don't insist on csrf tokens in put, post etc.
          .authorizeHttpRequests(r -> r.anyRequest().permitAll())
          .build();
    }
}