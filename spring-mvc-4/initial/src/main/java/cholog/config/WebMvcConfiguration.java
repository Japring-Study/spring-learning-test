package cholog.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("hello");
	}

	// TODO: "/admin/**" 요청 시 LoginInterceptor 동작하게 하기
	@Override
	public void addInterceptors(InterceptorRegistry registry) {

	}

	// TODO: AuthenticationPrincipalArgumentResolver 등록하기
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
	}
}
