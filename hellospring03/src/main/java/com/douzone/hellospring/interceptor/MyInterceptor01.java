package com.douzone.hellospring.interceptor;   // controller 가로채기

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MyInterceptor01 implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {               // 어떤핸들러인지는 모르겠는데 @Auth 달려있으면 인증해야한다/ 타입이 두개일수도있다 1. 그냥 핸들러 2. 
			System.out.println("MyInterceptor01.perHandle(...) called");
		return true;   //true handler 실행
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("MyInterceptor01.perHandle(...) called");

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("MyInterceptor01.afterCompletion(...) called");

	}

}
