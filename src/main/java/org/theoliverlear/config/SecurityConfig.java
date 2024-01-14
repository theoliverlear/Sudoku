package org.theoliverlear.config;

// Import annotations and classes from the Spring Security framework
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
// The @Configuration annotation tells Spring that this class can be used for
// configuration, additionally it also tells Spring to manage this class as a
// bean in the application context.
@Configuration
// The @EnableWebSecurity annotation turns on a variety of beans needed to use
// Spring Security. For example, it turns on things like the security
// namespace configuration.
@EnableWebSecurity
// The @EnableGlobalMethodSecurity provides a simple approach for securing
// methods by providing Pre and Post annotations, such as @PreAuthorize,
// @PostAuthorize, @PreFilter, @PostFilter,.. etc. it's mainly used for method
// level security.
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    // @Bean is a method-level annotation within a @Configuration class.
    // The annotation support is provided by the two main annotations:
    // @Configuration and @Bean.
    // The @Bean annotation tells Spring that this method will return an
    // object that should be registered as a bean in the Spring application
    // context
    //------------------------Security-Filter-Chain---------------------------
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // HttpSecurity is the builder object used to create the security filter chain
        http
            .authorizeRequests() // begin the request level authorization rules
                .anyRequest().permitAll() // permit all incoming requests.
                .and()
                .csrf() // configuring Cross Site Request Forgery (CSRF) protections
                .disable(); // disabling CSRF protections
        // Call build method to create the SecurityFilterChain which holds
        // the security configuration for each incoming Http request and
        // returns it as a bean to the Spring Container.
        return http.build();
    }
    //----------------------------Password-Encoder----------------------------
    // The BCryptPasswordEncoder is a Spring Security implementation of
    // the PasswordEncoder interface. It utilizes the BCrypt strong hashing
    // function to encode the password. The BCryptPasswordEncoder class is
    // annotated with @Component, so it is automatically included in the
    // Spring application context.
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}