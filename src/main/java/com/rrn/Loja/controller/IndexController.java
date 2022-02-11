package com.rrn.Loja.controller;

import java.util.Properties;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
 
@Controller
@RequestMapping("/")
public class IndexController {
 
	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/ambiente")
	public String ambiente() {
		return "ambiente";
	}
	
	@RequestMapping("/painel")
	String painel() {
		return "/painel/index"; // nome da pasta/nome do arquivo html
	}
	
	@GetMapping("properties")
	@ResponseBody
	Properties properties() {
		return System.getProperties();
	}
    
}