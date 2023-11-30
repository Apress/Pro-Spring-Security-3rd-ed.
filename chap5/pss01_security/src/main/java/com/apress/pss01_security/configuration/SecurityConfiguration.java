package com.apress.pss01_security.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.www.DigestAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.DigestAuthenticationFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;


@Configuration
@EnableWebSecurity

public class SecurityConfiguration {


    @Bean
    public SecurityFilterChain filterChain1(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/", "/welcome").permitAll()
                        .requestMatchers("/authenticated").hasRole("ADMIN")
                        .requestMatchers("/customError").permitAll()
                        .anyRequest().denyAll()
                )

                .csrf(Customizer.withDefaults())

                // .httpBasic(withDefaults())    using Basic Authentication
                //.formLogin(withDefaults())     using Form Authentication not customized

                .rememberMe((remember) -> remember
                        .rememberMeParameter("remember-me")
                        .key("uniqueAndSecretKey")
                        .tokenValiditySeconds(1000)
                        .rememberMeCookieName("rememberloginnardone")
                        .rememberMeParameter("remember-me")
                )


                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                        .maximumSessions(1))


                // using customized login html page
                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/authenticated")
                        .failureUrl("/login?error=true")
                        .failureHandler(authenticationFailureHandler())
                        .permitAll()
                )


                .logout((logout) -> logout
                        .logoutSuccessUrl("/welcome")
                        .deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true)
                        .permitAll()
                );


           return http.build();
    }


    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    @Bean
    public UserDetailsService userDetailsService(){

        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder().encode("userpassw"))
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("adminpassw"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    /*  to use Digest Authentication

        DigestAuthenticationEntryPoint entryPoint() {
        DigestAuthenticationEntryPoint result = new DigestAuthenticationEntryPoint();
        result.setRealmName("My Security App Realm");
        result.setKey("3028472b-da34-4501-bfd8-a355c42bdf92");
    }

    @Autowired
    UserDetailsService userDetailsService;

    DigestAuthenticationFilter digestAuthenticationFilter() {
        DigestAuthenticationFilter result = new DigestAuthenticationFilter();
        result.setUserDetailsService(userDetailsService);
        result.setAuthenticationEntryPoint(entryPoint());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // ...
                .exceptionHandling(e -> e.authenticationEntryPoint(authenticationEntryPoint()))
                .addFilterBefore(digestFilter());
        return http.build();
    } */

}