package com.example.holamundo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //autenticacion
    //@Override
    //protected void configure(AuthenticationManagerBuilder auth) throws Exception{
      //  auth.inMemoryAuthentication().
        //        withUser("admin")
          //      .password("{noop}1234")
            //    .roles("ADMIN","USER")
              //  .and()
                //.withUser("user")
                //.password("{noop}1234")
                //.roles("USER");}

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean //va a estar disponible en el contenedor de spring con la anotacion
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configuererGlobal(AuthenticationManagerBuilder builder) throws Exception{
        builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    //autorizacion
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/editar/**" ,"/agregar/**","/eliminar")
                .hasRole("ADMIN")
                .antMatchers("/")
                .hasAnyRole("USER","ADMIN")
                .and()
                .formLogin()
                .loginPage("/login")
        .and().exceptionHandling().accessDeniedPage("/errores/403");
    }

}
