package com.erin.movie.userManagement.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.erin.movie.userManagement.TaskImplementingLogoutHandler;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/adminOnly").hasAuthority("ROLE_ADMIN").antMatchers("/**").permitAll()
				.anyRequest().authenticated().and().formLogin().loginPage("/login").failureUrl("/login?error")
				.permitAll().defaultSuccessUrl("/").and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.addLogoutHandler(new TaskImplementingLogoutHandler()).permitAll().logoutSuccessUrl("/");
		// 로그아웃 기본 url은 (/logout)
		// 새로 설정하려면 .logoutUrl("url") 사용
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource).rolePrefix("ROLE_")
				.usersByUsernameQuery(
						"select username, replace(password, '$2y', '$2a'), true from users where username = ?") //username, pw, enabled
				.authoritiesByUsernameQuery("select username, role from users where username = ?"); //username,authority name 
	}

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder().encode("1234")).roles("ADMIN").and()
//				.withUser("guest").password(passwordEncoder().encode("guest")).roles("GUEST");
//	}

	// passwordEncoder() 추가
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}