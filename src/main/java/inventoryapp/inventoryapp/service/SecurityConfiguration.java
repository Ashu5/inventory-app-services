package inventoryapp.inventoryapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import inventoryapp.inventoryapp.filter.JwtRequestFilter;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService myUserDetailsService;
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserDetailsService);
	}

	 @Override
	    public void configure(HttpSecurity http) throws Exception {
	       http.cors().and().csrf().disable()
	       .authorizeRequests()
	       .antMatchers("/").permitAll()
	       .antMatchers("/api/authenticate").permitAll()
	       .antMatchers("/","/api/getProducts").permitAll()
	       .antMatchers("/v2/api-docs/**").permitAll()
	       .antMatchers("/swagger.json").permitAll()
	       .antMatchers("/swagger-ui.html").permitAll()
	       .antMatchers("/swagger-resources/**").permitAll()
	       .antMatchers("/webjars/**").permitAll()
	       .anyRequest().authenticated().and()
	       .exceptionHandling().and().sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	       http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	       
	    }
	 @Autowired
	 public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	
	 auth.userDetailsService(myUserDetailsService).passwordEncoder(getPasswordEncoder());
	 }

		@Bean
		public PasswordEncoder getPasswordEncoder() {
			return NoOpPasswordEncoder.getInstance();
		}
	 
	 @Override
		@Bean
		public AuthenticationManager authenticationManagerBean() throws Exception {
			
			return super.authenticationManagerBean();
		}
}
