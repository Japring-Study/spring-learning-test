package cholog.config;

import cholog.ui.AuthenticationPrincipalArgumentResolver;
import cholog.ui.CheckLoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
// 오류 났던 코드 아직 잘 이해안됨
//    private final AuthenticationPrincipalArgumentResolver authenticationPrincipalArgumentResolver;
//
//
//    public WebMvcConfiguration(AuthenticationPrincipalArgumentResolver authenticationPrincipalArgumentResolver){
//        this.authenticationPrincipalArgumentResolver = authenticationPrincipalArgumentResolver;
//    }


    // TODO: "/" 요청 시 hello.html 페이지 응답하기
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("hello");
    }

    // TODO: "/admin/**" 요청 시 LoginInterceptor 동작하게 하기
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CheckLoginInterceptor())
                .addPathPatterns("/admin/**"); // 인터셉터를 적용할 URL 패턴을 지정함
    }

    // TODO: AuthenticationPrincipalArgumentResolver 등록하기
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new AuthenticationPrincipalArgumentResolver());
        // 이 부분 때문에 많이 헤멧음.
    }
}
