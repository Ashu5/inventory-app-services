package inventoryapp.inventoryapp.service;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	 @Override
	    public void configure(HttpSecurity http) throws Exception {
	       http.cors().and().csrf().disable()
	       .authorizeRequests()
	       .antMatchers("/").permitAll()
	       .antMatchers("/","/api").permitAll()
	       .antMatchers("/api/**").permitAll()
	       .antMatchers("/users").permitAll()
	       .antMatchers("/v2/api-docs/**").permitAll()
	       .antMatchers("/swagger.json").permitAll()
	       .antMatchers("/swagger-ui.html").permitAll()
	       .antMatchers("/swagger-resources/**").permitAll()
	       .antMatchers("/webjars/**").permitAll()
	       .anyRequest().authenticated();
	       
	    }
}
