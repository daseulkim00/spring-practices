package com.douzone.emaillist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.emaillist.repository.EmaillistRepository;
import com.douzone.emaillist.vo.EmaillistVo;

@Controller
public class EmaillistController {
	@Autowired    //필요한 의존 객체의 타입에 해당하는 빈을 찾아 주입한다.
	private EmaillistRepository emaillistRepository;

	
	@RequestMapping("")
	public String index(Model model) {
		List<EmaillistVo> list = emaillistRepository.findAll();
		model.addAttribute("list",list); // index에서 이름 list 
	// Model addAttribute(String name, Object value) model에 데이터를 담는다
		return "/WEB-INF/views/index.jsp";
	}
	
	@RequestMapping(value="/form", method=RequestMethod.GET)
	public String form() {
		return "/WEB-INF/views/form.jsp";    // 연결해주는거
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String add(EmaillistVo vo) {
		emaillistRepository.insert(vo);   // 정보저장
		return "redirect:/";
	}
}