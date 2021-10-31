package web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import web.config.handler.LoginSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("ADMIN").password("ADMIN").roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

            http.authorizeRequests()
                    .antMatchers("/", "/login", "/logout").permitAll() // доступность всем
                    .antMatchers("/user/**").access("hasAnyRole('ADMIN')") // разрешаем входить на /user пользователям с ролью User, Admin
                    .and()
                    .formLogin()  // Spring сам подставит свою логин форму
                    .successHandler(new LoginSuccessHandler()) // подключаем наш SuccessHandler для перенеправления по ролям
                    .and().logout()
                    .logoutUrl("/logout") //URL-адрес, запускающий выход из системы (по умолчанию "/ logout").
                    .logoutSuccessUrl("/login") //URL-адрес для перенаправления после выхода из системы.
                    .and().csrf().disable();

        http
                // делаем страницу регистрации недоступной для авторизированных пользователей
                .authorizeRequests()
                //страницы аутентификаци доступна всем
                .antMatchers("/login","/index").anonymous()
                // защищенные URL
                .antMatchers("/edit","/new","/show").access("hasAnyRole('ADMIN')").anyRequest().authenticated();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
