package com.varun;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;

/**
 * Created by varun on १७-०४-२०१७.
 */
@Configuration
public class SecurityConfig extends GlobalAuthenticationConfigurerAdapter{

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("varun").password("sharma").roles("USER")
                .and().withUser("sumit").password("setia").roles("USER","OPERATOR");
    }
}
