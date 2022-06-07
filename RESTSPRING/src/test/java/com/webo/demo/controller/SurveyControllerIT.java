package com.webo.demo.controller;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.webo.demo.RestspringApplication;
import com.webo.demo.model.Question;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestspringApplication.class,
webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SurveyControllerIT {
	@LocalServerPort
	private int port;
	@Test
	public void test() throws JSONException { 
		String url = "http://localhost:" + port
				+ "/surveys/Survey1/questions/Question1";
		TestRestTemplate restTemplate = new TestRestTemplate();
		//String resul=restTemplate.getForObject(url,String.class);
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		System.out.println("IM in the tests you've reached here");
		ResponseEntity<String> response = restTemplate.exchange(url,
				HttpMethod.GET, entity, String.class);

		String expected = "{id:Question1,description:Largest Country in the World,correctAnswer:Russia}";

		JSONAssert.assertEquals(expected, response.getBody(), false);
	
	}
	@Test
	public void addQuestions() throws JSONException { 
		String url = "http://localhost:" + port + "/surveys/Survey1/questions";
		TestRestTemplate restTemplate = new TestRestTemplate();
		//String resul=restTemplate.getForObject(url,String.class);
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		Question sampleQuestion = new Question("Question1",
				"Largest Country in the World", "Russia", Arrays.asList(
						"India", "Russia", "United States", "China"));
		HttpEntity entity = new HttpEntity<Question>(sampleQuestion, headers);
		System.out.println("IM in the tests you've reached here");
		ResponseEntity<String> response = restTemplate.exchange(url,
				HttpMethod.POST, entity, String.class);
		String actual=response.getHeaders().get(HttpHeaders.LOCATION).get(0);

		assertTrue(actual.contains("/surveys/Survey1/questions"));
	
	}

}
