package com.douzone.fileupload.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.fileupload.service.FileUploadService;

@Controller
public class FileUploadController {
	@Autowired
	private FileUploadService fileUploadService;
	
	@RequestMapping({"", "/form"})      // "" 내가처음에 들어가는곳 
	public String form() {
		return "/WEB-INF/views/form.jsp";
	}
	
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public String upload(
		@RequestParam(value="email", required=true, defaultValue="") String email,
		@RequestParam(value="file") MultipartFile multipartFile, Model model) {
		System.out.println("email:" + email);
		
		String url = fileUploadService.restore(multipartFile);  //멀티파트파일을 넘겨주면 서비스에서 파일을 저장한 후  접근가능항 url을 던져준다.
		model.addAttribute("ur", url);
		
		return "/WEB-INF/views/result.jsp";
	}
}