package com.nighttest.test.nightconsumer;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.jayway.jsonpath.JsonPath;

import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit.PactProviderRule;
import au.com.dius.pact.consumer.junit.PactVerification;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;

public class Nightresponse {
	 @Rule
	    public PactProviderRule mockProvider = new PactProviderRule("inventory_provider","localhost", 8080, this);
	    private RestTemplate restTemplate=new RestTemplate();


	    @Pact(provider = "inventory_provider", consumer = "inventory_consumer")
	    public RequestResponsePact createPact(PactDslWithProvider builder) {
	        Map<String, String> headers = new HashMap<>();
	        headers.put("Content-Type", MediaType.APPLICATION_JSON_VALUE);


	        PactDslJsonBody bodyResponse = new PactDslJsonBody()
	                .stringValue("projectname", "WAWA")
	                 .stringType("softwarename", "SmartBear")               
	                .stringType("username", "Shyamchandar");

	        return builder
	        		.given("night inventory").uponReceiving("a request to save night response")
	                .path("/api/inventory")
	                .body(bodyResponse)
	                .headers(headers)
	                .method(RequestMethod.POST.name())
	                .willRespondWith()
	                .headers(headers)
	                .status(200).body(bodyResponse).toPact();
	    }

	   

		
		
		@Test
		@PactVerification
		public void testCreateInventoryConsumer() throws IOException {
			
			Projectdetails projectdetails=new Projectdetails("WAWA", "SmartBear", "Shyamchandar");
	    	HttpHeaders headers=new HttpHeaders();
	    	headers.setContentType(MediaType.APPLICATION_JSON);
	    	HttpEntity<Object> request=new HttpEntity<Object>(projectdetails, headers);
	    	System.out.println("MOCK provider URL"+mockProvider.getUrl());
	    	ResponseEntity<String> responseEntity=restTemplate.postForEntity(mockProvider.getUrl()+"/api/inventory", request, String.class);
	    	assertEquals("WAWA", JsonPath.read(responseEntity.getBody(),"$.projectname"));
	    	assertEquals("SmartBear", JsonPath.read(responseEntity.getBody(),"$.softwarename"));
	    	assertEquals("Shyamchandar", JsonPath.read(responseEntity.getBody(),"$.username"));
		}

}
