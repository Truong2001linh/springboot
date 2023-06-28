package com.pokemonreview.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.config.AuditingConfiguration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration // là một annotation trong Spring Framework được sử dụng để đánh dấu một lớp là một lớp cấu hình
@EnableWebSecurity /*@EnableWebSecurity là một annotation trong Spring Security được sử dụng để kích hoạt
 tính năng bảo mật trên ứng dụng web. Khi một lớp được đánh dấu bằng @EnableWebSecurity, nó sẽ được Spring Security
  hiểu là một lớp cấu hình bảo mật và sẽ thực hiện cấu hình và kích hoạt các tính năng bảo mật trên ứng dụng.*/
public class SecurityConfig {
    private JwtAuthEntryPoint jwtAuthEntryPoint;
    private CustomUserDetailsSecurity customUserDetailsSecurity;
    @Autowired
    public SecurityConfig(CustomUserDetailsSecurity customUserDetailsSecurity, JwtAuthEntryPoint jwtAuthEntryPoint) {
        this.customUserDetailsSecurity = customUserDetailsSecurity;
        this.jwtAuthEntryPoint = jwtAuthEntryPoint;
    }

    @Bean
    /*
        @Bean là một annotation trong Spring Framework được sử dụng để đánh dấu một phương thức trong một lớp cấu hình
         (configuration class) như là một bean được quản lý bởi Spring Container. Khi một phương thức được đánh dấu bằng @Bean,
          nó sẽ trả về một đối tượng (bean) được Spring quản lý và sử dụng trong ứng dụng.*/
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()// Vô hiệu hóa CSRF (Cross-Site Request Forgery) trong bộ lọc. CSRF là một kỹ thuật tấn công phổ biến mà người dùng đánh lừa để thực hiện các hành động không mong muốn trên ứng dụng web.
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()//là một phương thức trong cấu hình Spring Security được sử dụng để xác định các quy tắc bảo mật cho các yêu cầu.
                .requestMatchers("/api/auth/**").permitAll()
                .anyRequest().authenticated()//Xác thực tất cả các yêu cầu (requests) bằng cách yêu cầu xác thực (authenticated). Điều này có nghĩa là tất cả các yêu cầu đến ứng dụng đều phải được xác thực trước khi được chấp nhận.
                .and()//là một phương thức được sử dụng trong cấu hình Spring Security để kết nối các phần cấu hình khác nhau và xác định một chuỗi các quy tắc bảo mật liên tiếp.
                .httpBasic();//Cung cấp phương thức xác thực HTTP Basic. Khi yêu cầu xác thực, người dùng sẽ được yêu cầu cung cấp thông tin đăng nhập (username và password) qua header Authorization của yêu cầu HTTP.
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

//    @Bean
//    public UserDetailsService users(){
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password("password")
//                .roles("ADMIN")
//                .build();
//        UserDetails user = User.builder()
//                .username("user")
//                .password("password")
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(admin,user);
//    }
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration)  throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }
    @Bean
    public JWTAuthenticationFilter jwtAuthenticationFilter(){
        return new JWTAuthenticationFilter();
    }

}

/*Các đặc điểm và chức năng của @Configuration:

    - Định nghĩa các bean: Lớp được đánh dấu bằng @Configuration cho phép định nghĩa các bean trong Spring Container.
     Bạn có thể sử dụng các annotation khác như @Bean, @ComponentScan, @Import, @Profile để định nghĩa và cấu hình các bean.

    - Cấu hình Spring: @Configuration cho phép bạn cấu hình và tùy chỉnh các thành phần và tính năng của Spring Framework.
     Bạn có thể cung cấp các giá trị cấu hình, kết nối với các nguồn dữ liệu, cấu hình các thông số, v.v.

    - Tạo bean tự động: @Configuration cung cấp khả năng tự động tạo các bean dựa trên cấu hình và quy tắc đã định nghĩa.
     Spring sẽ tự động quét và phân tích các phương thức được đánh dấu bằng @Bean trong lớp @Configuration và tạo các bean
      tương ứng trong Spring Container.

    - Tương thích với các annotation khác: @Configuration tương thích với các annotation khác trong Spring Framework như
     @Component, @Autowired, @Value, v.v. Điều này cho phép bạn kết hợp các chức năng và tính năng của Spring khác để xây
      dựng và cấu hình ứng dụng.*/

/*Các đặc điểm và chức năng của @EnableWebSecurity:

    - Kích hoạt tính năng bảo mật: @EnableWebSecurity cho phép kích hoạt tính năng bảo mật của Spring Security trên ứng
     dụng web. Điều này bao gồm xác thực người dùng, phân quyền truy cập, quản lý phiên, bảo vệ khỏi các cuộc tấn công và
      nhiều tính năng bảo mật khác.

    - Cấu hình bảo mật: @EnableWebSecurity cho phép bạn cấu hình và tùy chỉnh các thành phần và tính năng bảo mật của Spring
     Security. Bạn có thể sử dụng các annotation khác như @Configuration, @EnableGlobalMethodSecurity, @EnableWebMvcSecurity,
      @EnableWebFluxSecurity, @EnableOAuth2Client, v.v. để định nghĩa và cấu hình các tính năng bảo mật.

    - Quản lý đối tượng bảo mật: @EnableWebSecurity cho phép bạn quản lý các đối tượng bảo mật như UserDetails,
     UserDetailsService, AuthenticationProvider, AccessDecisionManager, v.v. Bạn có thể tạo và cấu hình các đối tượng này
      để thực hiện xác thực và phân quyền trong ứng dụng.*/
/*Các đặc điểm và chức năng của @Bean:

    - Đăng ký bean: @Bean cho phép đăng ký một đối tượng trong Spring Container. Điều này cho phép Spring quản lý vòng đời
     và phụ thuộc của đối tượng đó, và bạn có thể sử dụng nó trong các thành phần khác của ứng dụng.

    - Tùy chỉnh cấu hình: @Bean cho phép bạn tùy chỉnh cấu hình các đối tượng trong ứng dụng. Bạn có thể cung cấp các giá
     trị, tham số, và tùy chọn khác để cấu hình bean theo nhu cầu của ứng dụng.

    - Dependency Injection: @Bean cho phép bạn sử dụng Dependency Injection để tiêm các bean khác vào phương thức trả về bean.
     Điều này giúp tạo ra các quan hệ phụ thuộc giữa các bean và giúp Spring tự động phân giải các phụ thuộc khi cần thiết.*/
