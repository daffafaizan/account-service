package account.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    private final int strength = 15;

    @Bean
    @Autowired
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic(Customizer.withDefaults()) // Default Basic auth config
                .exceptionHandling(ex -> ex.authenticationEntryPoint(restAuthenticationEntryPoint)) // Handle auth errors
                .csrf(csrf -> csrf.disable()) // For Postman
                .headers(headers -> headers.frameOptions().disable()) // For the H2 console
                .authorizeHttpRequests(auth -> auth  // manage access
                        .requestMatchers("/error/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth/signup").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth/changepass").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/empl/payment", "/api/empl/payment/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/acct/payments").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/acct/payments").permitAll()
                        .requestMatchers(HttpMethod.POST, "/actuator/shutdown").permitAll()
                        .anyRequest().denyAll()

                )
                .sessionManagement(sessions -> sessions
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // no session
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(strength);
    }
}
