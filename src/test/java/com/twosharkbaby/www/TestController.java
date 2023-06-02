package com.twosharkbaby.www;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestController {

	@Autowired
    private TestRestTemplate restTemplate;
	
	@Test
	public void test() {
		ResponseEntity<String> responseEntity = restTemplate.getForEntity("/test", String.class);
		assertEquals("test", responseEntity.getBody());
	}
	
}
