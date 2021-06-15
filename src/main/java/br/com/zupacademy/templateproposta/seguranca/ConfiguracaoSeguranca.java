package br.com.zupacademy.templateproposta.seguranca;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class ConfiguracaoSeguranca extends WebSecurityConfigurerAdapter{
	
	 @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.authorizeRequests(authorizeRequests ->
	                authorizeRequests
	                .antMatchers(HttpMethod.GET, "/actuator/prometheus/**").permitAll()
	                .antMatchers("/h2-console/**").permitAll()
	                .anyRequest().authenticated()
	        ).csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	                .and().oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
	        http.headers().frameOptions().sameOrigin();
	    }

}
