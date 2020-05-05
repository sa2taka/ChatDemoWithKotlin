package com.sa2taka.ChatDemoWithKotlin.Config

import com.sa2taka.ChatDemoWithKotlin.Domain.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.util.matcher.AntPathRequestMatcher


@Configuration
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {
  @Throws(Exception::class)
  override fun configure(web: WebSecurity) {
    web
              .ignoring()
              .antMatchers("/img/**", "/css/**", "/js/**", "/webjars/**")
            .and()
              .ignoring()
              .antMatchers("/h2-console/**");
  }

  @Throws(Exception::class)
  override fun configure(http: HttpSecurity) {
      http
            .authorizeRequests()
            .antMatchers("/")
            .permitAll()
            .and()
            .formLogin()
            .usernameParameter("username")
            .passwordParameter("password")
            .permitAll()
            .and()
            .logout()
            .logoutRequestMatcher(AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/")
            .deleteCookies("JSESSIONID")
            .invalidateHttpSession(true)
            .permitAll()
            .and()
            .authorizeRequests()
            .anyRequest()
            .authenticated()
  }

  @Bean
  fun passwordEncoder(): PasswordEncoder{
    return BCryptPasswordEncoder()
  }
}