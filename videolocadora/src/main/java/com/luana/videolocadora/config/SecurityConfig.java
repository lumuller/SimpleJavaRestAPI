package com.luana.videolocadora.config;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
 
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	@Autowired
    private CustomAuthenticationProvider authProvider;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {        
    	
    	
    	/*The authorization configuration below is not working as expected. I will keep it here, for future analysis.
    	 * The authority access is being implemented by the annotation @Secured, into VideoLocadoraResources.class*/
    	
    	/*http.sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().cors().and()
        .authorizeRequests()
        .antMatchers(HttpMethod.POST, "/users").hasAuthority("ROLE_ADMIN")
        .antMatchers(HttpMethod.PUT, "/users/**").hasAuthority("ROLE_ADMIN")
        .antMatchers(HttpMethod.GET, "/users").hasAuthority("ROLE_ADMIN")
        .antMatchers(HttpMethod.DELETE, "/**").hasAuthority("ROLE_ADMIN")
        .antMatchers(HttpMethod.POST, "/movies").hasAuthority("ROLE_ADMIN")
        .antMatchers(HttpMethod.PUT, "/movies/*").hasAuthority("ROLE_ADMIN")
        .antMatchers(HttpMethod.GET, "/movies").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
        .antMatchers(HttpMethod.POST, "/rents/*").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
        .antMatchers(HttpMethod.PUT, "/rents/*").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
        .antMatchers(HttpMethod.GET, "/rents/*").hasAuthority("ROLE_ADMIN")
        .and().logout()
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
           .permitAll()
            .and().csrf().disable();*/
 	
    	    	
        http.httpBasic()
        .and()
        .authorizeRequests()
        .anyRequest().authenticated().and()
        //.logout().logoutUrl("/logout").invalidateHttpSession(true).permitAll().and()
        .csrf().disable();
        

    }
  
}