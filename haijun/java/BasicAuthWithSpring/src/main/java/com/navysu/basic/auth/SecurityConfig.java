package com.navysu.basic.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		// @formatter:off
		auth.inMemoryAuthentication()
				.withUser("navy").password(encoder.encode("password")).roles("USER")
				.and()
				.withUser("admin").password("{noop}password").roles("ADMIN");
        // @formatter:on
	}

	/**
	 * Since the csrf() is enabled by default, the /logout url must use the post
	 * method with csrf value.
	 */
	@Override
	public void configure(HttpSecurity http) throws Exception {
		// @formatter:off
        http
            .authorizeRequests()
		    .antMatchers("/home").hasAnyRole("USER")
		    .antMatchers("/admin").hasAnyRole("ADMIN")
        	.and()
        	.formLogin().loginPage("/login").failureUrl("/login?error=1").loginProcessingUrl("/login").permitAll()
        	.and()
            .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
        // @formatter:on
	}
}