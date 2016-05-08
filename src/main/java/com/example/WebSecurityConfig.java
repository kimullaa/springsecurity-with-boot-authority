package com.example;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeRequests()
						.antMatchers("/admin/**").hasAuthority("AUTH_SHOW_ADMIN_PAGE")
						.antMatchers("/operations/create-user").hasAuthority("AUTH_CREATE_USER")
						.antMatchers("/operations/show-all").hasAuthority("AUTH_SHOW_ALL")
						.anyRequest().authenticated()
					.and()
					.formLogin()
						.loginPage("/login")
						.defaultSuccessUrl("/home")
						.failureUrl("/login?error")
						.permitAll().and()
					.logout()
						.logoutUrl("/logout")
						.logoutSuccessUrl("/login?logout")
						.permitAll();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
				.withUser("user").password("password")
				.authorities(new String[]{"AUTH_SHOW_ALL", "AUTH_CREATE_USER","AUTH_SHOW_USER_PAGE"})
				.and()
				.withUser("admin").password("password")
				.authorities(new String[]{"AUTH_SHOW_ADMIN_PAGE"});
	}
}
