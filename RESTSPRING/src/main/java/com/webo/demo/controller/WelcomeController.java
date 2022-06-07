package com.webo.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webo.demo.configuration.BasicConfiguration;
import com.webo.demo.service.SurveyService;

@RestController
public class WelcomeController {
@Autowired
SurveyService serv;
@Autowired
private BasicConfiguration basic;
@Value("${welcome.message}")
private String welcomemessage;
@RequestMapping("/")
public String welcomepage()
{
	return welcomemessage;
}
@RequestMapping("/dynamic-configuration")
public Map dynamicconfig()
{
	Map map=new HashMap();
	map.put("message",basic.getMessage());
	map.put("number",basic.getNumber());
	map.put("value",basic.isValue());
	return map;
     }
@Profile("prod")
@Bean
public String dummy()
{
	return "something";
}
}
