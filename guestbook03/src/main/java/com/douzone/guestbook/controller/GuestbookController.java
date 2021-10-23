package com.douzone.guestbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.guestbook.repository.GuestbookRepository;
import com.douzone.guestbook.vo.GuestbookVO;

@Controller
public class GuestbookController {
	
	@Autowired
	private GuestbookRepository guestbookRepository;
	
	@RequestMapping("")
	public String index(Model model) {
		List<GuestbookVO> list = guestbookRepository.findAll();
		model.addAttribute("list",list);
		return "/WEB-INF/views/index.jsp";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String add(GuestbookVO vo) {
		guestbookRepository.insert(vo);
		return "redirect:/";
	}
	
	// deleteform 
	@RequestMapping(value = "/delete" , method = RequestMethod.GET)  // 그냥 연결은 GET으로 하라고요~미취겟네~
	public String deleteform() {
		return "/WEB-INF/views/deleteform.jsp"; 
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST )
	public String delete(GuestbookVO vo) {
		guestbookRepository.delete(vo);
		return "redirect:/";
	}
	
	
	
	
	
	
	
	
	
	
}
