package com.douzone.hellospring.controller;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


// 어노테이션 (@Controller)을  달아줘야 찾을 수 있다  
@Controller  
public class HelloController {

	public void a() {
	}
	public void b() {
	}
	public void c() {
	}
	// 위에 3개는 건너뛴다 등록되어있지않아서
	
	
	
	@RequestMapping("/hello") // 어노테이션 : 'hello' 라는 요청이 들어왔을 때 실행
	public String hello() {
		return "/WEB-INF/views/hello.jsp";

	}

	@RequestMapping("/hello2") // 다양하게 url을 만들 수 있다.
	public String hello2(String name) {
		System.out.println(name);
		return "/WEB-INF/views/hello2.jsp";
	}

	@RequestMapping("/hello3")
	public ModelAndView hello3(String name) { // new를해야해서 model을 잘 안쓴다.
		ModelAndView mav = new ModelAndView();
		mav.addObject("name", name);
		mav.setViewName("/WEB-INF/views/hello3.jsp"); //ModelAndView에 viewname만 본다
		return mav;

	}

	@RequestMapping("/hello4")
	public String hello4(String name, Model model) { // 위에처럼 말고 이렇게 사용해라
		model.addAttribute("name", name);
		return "/WEB-INF/views/hello3.jsp";
	}
	
	@ResponseBody
	@RequestMapping("/hello5")
	public String hello5() { 
		return "<h1>Sun Yuon</h1>";   // 한글이 다 깨진다.
	}
	
	@RequestMapping("/hello6")
	public String hello6() { 
		System.out.println("hello6() called");
		return "redirect:/hello";   
	}
	
	// 아래처럼 쓰지마라 
	//@RequestMapping("/hello7")
	//public void hello7(
		//	HttpServletRequest req, 
		//	ClientHttpResponse resp, 
			//HttpSession session,
			//Writer out) throws IOExcption { 
		//String no = req.getParameter("n");
		//out.write("<h1>Hello World</h1>")  
	//}
	
	
}
