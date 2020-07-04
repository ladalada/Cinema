package org.example.config

import org.example.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
@EnableWebSecurity
open class WebSecurityConfig : WebSecurityConfigurerAdapter() {
    @Autowired
    private var userService: UserService? = null
    @Bean
    open fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Throws(Exception::class)
    override fun configure(httpSecurity: HttpSecurity) {
        httpSecurity
                .csrf()
                .disable()
                .authorizeRequests()
                //Доступ только для не зарегистрированных пользователей
                .antMatchers("/registration").not().fullyAuthenticated()
                //Доступ только для пользователей с ролью Администратор
                .antMatchers("/users").hasRole("ADMIN")
                .antMatchers("/orders").hasAnyRole("MANAGER")
                .antMatchers("/statistics").hasAnyRole("MANAGER")

                .antMatchers("/myorders").hasRole("USER")
                .antMatchers("/mystatistics").hasRole("USER")
                .antMatchers("/recommend").hasRole("USER")
                .antMatchers("/movie").hasRole("USER")

                .antMatchers("/newnote").hasRole("ADMIN")
                .antMatchers("/add_news").hasRole("ADMIN")
                .antMatchers("/newservice").hasRole("MANAGER")
                .antMatchers("/services").permitAll()
                .antMatchers("/basket").hasAnyRole("USER")
                .antMatchers("/news").fullyAuthenticated()
                .antMatchers("/registration").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/order_r").hasRole("MANAGER")
                .antMatchers("/remove_users_role").hasRole("ADMIN")
                .antMatchers("/make_manager").hasRole("ADMIN")
                .antMatchers("/remove_user").hasRole("ADMIN")
                //Доступ разрешен всем пользователей
                .antMatchers("/", "/webapp/jsp/**").permitAll()
                //Все остальные страницы требуют аутентификации
                .anyRequest().authenticated()
                .and()
                //Настройка для входа в систему
                .formLogin()
                .loginPage("/login")
                //Перенарпавление на главную страницу после успешного входа
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .logoutSuccessUrl("/")
    }

    @Autowired
    @Throws(Exception::class)
    protected fun configureGlobal(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder())
    }
}