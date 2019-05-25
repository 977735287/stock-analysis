//package per.san.common.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
///**
// * description:
// *
// * @author shencai.huang@hand-china.com
// * @date 5/23/2019 09:25
// * lastUpdateBy: shencai.huang@hand-china.com
// * lastUpdateDate: 5/23/2019
// */
//@Configuration
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//
////    @Bean
////    @Override
////    public AuthenticationManager authenticationManagerBean() throws Exception {
////        AuthenticationManager manager = super.authenticationManagerBean();
////        return manager;
////    }
////
////    @Bean
////    UserDetailsService customUserService() { //注册UserDetailsService 的bean
////        return new CustomUserDetailsService();
////    }
////
////    @Override
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.userDetailsService(customUserService());
////
////    }
////
////    @Bean
////    public PasswordEncoder passwordEncoder() {
////        return new BCryptPasswordEncoder();
////    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
////        http
////                .authorizeRequests()
////                .antMatchers("/public/**", "/login", "/register", "/find", "/swagger-ui.html",
////                        "/swagger-resources/**", "/images/**", "/webjars/**", "/v2/api-docs", "/configuration/ui",
////                        "/configuration/security").permitAll()
////                .anyRequest().authenticated()
////                .and()
////                .formLogin()
////                //指定登录页的路径
////                .loginPage("/login")
////                //指定自定义form表单请求的路径
////                .loginProcessingUrl("/user/login")
////                .failureUrl("/login?error")
////                .defaultSuccessUrl("/index")
////                //必须允许所有用户访问我们的登录页（例如未验证的用户，否则验证流程就会进入死循环）
////                //这个formLogin().permitAll()方法允许所有用户基于表单登录访问/login这个page。
////                .permitAll();
////        //默认都会产生一个hiden标签 里面有安全相关的验证 防止请求伪造 这边我们暂时不需要 可禁用掉
////        http .csrf().disable();
//        http
//                .authorizeRequests().anyRequest().permitAll().and().logout().permitAll();
//
//    }
//
//}
