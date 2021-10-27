package com.douzone.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
	
	@ResponseBody  // 어노테이션은 메소드,클래스 필드,파라메트에도 붙을수있다
	@RequestMapping({"", "/main","/main/a/b/c/d"})
	public String main() {
		return "MainController.main()";
	}
}
