package com.webo.demo.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.webo.demo.model.Question;
import com.webo.demo.model.Survey;
import com.webo.demo.service.SurveyService;

@RestController
public class SurveyController {
	@Autowired
	private SurveyService serv;
	@RequestMapping("/surveys/{surveyid}/questions")
	public List<Question> allquestions(@PathVariable String surveyid)
	{
		return serv.retrieveQuestions(surveyid);
	}
	
	@RequestMapping("/surveys/{surveyid}/questions/{questionid}")
	public Question questiondetails(@PathVariable String surveyid,@PathVariable String questionid)
	{
		return serv.retrieveQuestion(surveyid,questionid);
	}
	
	@PostMapping("/surveys/{surveyid}/question")
	public ResponseEntity<Object> addquestion(@PathVariable String surveyid,@RequestBody Question newquestion)
	{
		Question question= serv.addQuestion(surveyid, newquestion);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(question.getId()).toUri();
		return ResponseEntity.created(location).build();
 	}
	
}
