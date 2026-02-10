package csd230.lab1.JuanRoy.config;

import csd230.lab1.JuanRoy.services.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {


    private final CustomUserDetailsService userDetailsService;


    public WebSecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        http
                .authorizeHttpRequests((requests) -> requests
                        // Existing public endpoints
                        .requestMatchers("/h2-console/**",
                                "/login",
                                "/css/**",
                                "/js/**",
                                "/api/rest/**",           // allow unrestricted access to rest api for testing
                                "/v3/api-docs",          // The actual JSON data
                                "/v3/api-docs/**",       // Support for groups
                                "/swagger-ui/**",        // UI static resources
                                "/swagger-ui.html",      // UI entry point
                                "/v3/api-docs.yaml"     // YAML version
                        ).permitAll()
                        // ------------------------------------
                        // Admin only
                        .requestMatchers("/books/add", "/books/edit/**", "/books/delete/**").hasRole("ADMIN")
                        // All others
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )
                .logout((logout) -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );


        // Required for H2 Console to work with Spring Security (it uses frames)
        http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));

        // Disable CSRF specifically for H2 Console and for REST API endpoints used by Swagger / curl during testing
        http.csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**", "/api/rest/**"));


        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        // FIX: Pass userDetailsService to the constructor
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
}
