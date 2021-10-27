package com.douzone.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author user
 * 클래스(타입) 단독 매핑
 *
 */


@Controller
@RequestMapping("/guestbook/*")  // *를 꼭 붙여야 밑에께 실행된다.
public class GuestbookController {
	
	@ResponseBody
	@RequestMapping
	public String list() {    // /guestbook/list로 들어오면 이쪽으로 연결해준다
		return "GuestbookController.list()";
	}
	@ResponseBody
	@RequestMapping
	public String delete() {    
		return "GuestbookController.delete()";
	}
}
