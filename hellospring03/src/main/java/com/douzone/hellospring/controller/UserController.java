package com.douzone.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 
 * @author user
 * 
 * @RequestMapping 클래그(타입) + 핸들러(메소드)
 *
 */

@Controller
@RequestMapping("/user")
public class UserController {	
	
	@ResponseBody
	@RequestMapping(value="/join",method=RequestMethod.GET)  //get 방식으로 받기 위해서 적어줌
	public String join() {
		return "/WEB-INF/views/join.jsp";
	}
	

	@RequestMapping(value="/join",method=RequestMethod.POST)  //POST 방식으로 받기 위해서 적어줌
	public String join(UserVo vo) {
		System.out.println(vo);
		//userDao.inser(vo);
		return "redirect:/";              //같은 url로받아서 쓴다
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public String update(@RequestParam("n")String name) {     //좋은 방법은 아님
		/**
		 * 만일 n이라는 이름의 파라미터가 없는 경우 
		 * 400 bad request 에러가 발생한다.
		 */
		System.out.println(name);
		return "UserController.update()";
	}
	
	@ResponseBody
	@RequestMapping("/update2")
	public String update2(
		@RequestParam(value="n",required=true, defaultValue="")String name,
		@RequestParam(value="a",required=true, defaultValue="0") Integer age) {   
		System.out.println("----" + name + "----");
		System.out.println("----" + age + "----");
		return "UserController.update()";
	}
	
}
