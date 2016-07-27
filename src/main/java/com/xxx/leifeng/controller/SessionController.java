package com.xxx.leifeng.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@RestController
@RequestMapping("/session")
@SessionAttributes(value = {"name"})
public class SessionController {
	
	@RequestMapping("init")
	public String init(@RequestParam("name") String name){
		return name;
	}
	
	@RequestMapping("s1")
	public String session1(@ModelAttribute("name")String name){
		return name;
	}
	
	@RequestMapping("clean")
	public String session2(SessionStatus status,HttpSession session) {  
	    if(!status.isComplete()) {
	        status.setComplete();  
	    }  
	    return (String) session.getAttribute("name");  
	} 
}
