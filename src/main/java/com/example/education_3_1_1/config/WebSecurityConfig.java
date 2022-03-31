package com.example.education_3_1_1.config;



import com.example.education_3_1_1.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import javax.activation.DataSource;
import javax.persistence.Column;


@Configuration
@EnableWebSecurity
@ComponentScan
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    MyUserDetailsService myUserDetailsService;

//    @Override
//    public void configure(AuthenticationManagerBuilder builder)
//            throws Exception {
//        builder.authenticationProvider(authProvider());
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .authorizeRequests()
//                .antMatchers("/").hasAnyRole("ADMIN", "USER")
//                .antMatchers("/user/**").hasRole("ADMIN")
//                .and().formLogin().permitAll()
//                .and().csrf().disable();

                .formLogin()
                .loginPage("/login") //указываем логику обработки при логине
                .usernameParameter("username") // Указываем параметры логина и пароля с формы логина
                .passwordParameter("password") // Указываем параметры логина и пароля с формы логина
                .loginProcessingUrl("/perform_login")
                .successHandler(new LoginSuccessHandler())
                .permitAll(); // указываем страницу с формой логина

        http // разрешаем делать логаут всем
                .logout()
                .permitAll() // указываем URL логаута
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // указываем URL при удачном логауте
                .logoutSuccessUrl("/login?logout")
                .and().csrf().disable();

        http // делаем страницу регистрации недоступной для авторизированных пользователей

                .authorizeRequests()
                //страницы аутентификаци доступна всем
                .antMatchers("/login")
                .anonymous()
                .antMatchers("/user")
                .access("hasAnyRole('ADMIN', 'USER')")
                .antMatchers("/user-list/**")
                .access("hasAnyRole('ADMIN')") // защищенные URL
                .antMatchers("/hello")
                .access("hasAnyRole('ADMIN')")
                .anyRequest().authenticated();

    }

   @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(myUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }
}
