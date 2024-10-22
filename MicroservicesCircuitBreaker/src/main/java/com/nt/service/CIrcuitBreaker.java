package com.nt.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
public class CIrcuitBreaker {
	@GetMapping("/data")
	@CircuitBreaker(fallbackMethod = "getDataFromDB" ,name = "Roshan")
	public String getDataFromReddis() {
//		int i=10/0;
		System.out.println("here data will come from reddis server: :Main logic");
		return  "reddis data sent to ur mail";
		
	}
	
	public String getDataFromDB(Throwable t) {
		System.out.println("here data will come from db: :fallback logic");
		return "DB data sent to ur mail";
		
		
	}
	

}
