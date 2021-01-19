package sk.jarina.reservationsvaiibackend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import sk.jarina.reservationsvaiibackend.service.UserService;

import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);//.passwordEncoder(passwordEncoder());
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.httpBasic().disable().csrf().disable().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
                .antMatchers(HttpMethod.POST,"/api/film").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT,"/api/film").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST,"/api/screening").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST,"/api/tickets").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST,"/api/tickets").hasAuthority("CUSTOMER")
                .antMatchers(HttpMethod.GET,"/api/tickets").hasAuthority("CUSTOMER")
                .antMatchers(HttpMethod.GET,"/api/tickets").hasAuthority("ADMIN")
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated().and().csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorized());
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }


    @Bean
    public AuthenticationEntryPoint unauthorized() {
        return (req, resp, ex) -> resp.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                "REQUEST NOT AUTHORIZED");
    }


}
