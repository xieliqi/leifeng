package com.xxx.leifeng.controller;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xxx.leifeng.models.ValidateModel;

@RestController
@RequestMapping("validate")
public class ValidateController {
	
	@RequestMapping("v")
	@Valid
	public String v(@NotBlank(message="name is not blank") String name){
		System.out.println("v");
		return "success";
	}
	
	@RequestMapping("v1")
	public String v1(@Validated ValidateModel bean){
		return "success";
	}
	
}
