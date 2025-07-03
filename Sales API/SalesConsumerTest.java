package Sales;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslJsonArray;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.V4Pact;
import au.com.dius.pact.core.model.annotations.Pact;

@ExtendWith(PactConsumerTestExt.class)
public class SalesConsumerTest {
	// Set the headers
	Map<String, String> headers = new HashMap<>();
	int key;

	// Create the Fragment for POST request
	@Pact(consumer = "userconsumer", provider = "userprovider")
	public V4Pact createPostFragment(PactDslWithProvider builder) {
		// Set headers
		headers.put("Content-Type", "application/json");
		// Create the JSON body
		DslPart reqBody = new PactDslJsonBody().integerType("id", 2).integerType("cashId", 2)
				.integerType("cashierId", 2).integerType("productId", 99).stringType("registeredAt", "2025-07-10");
		DslPart resBody = new PactDslJsonBody().integerType("id",2).nullValue("cash").nullValue("cashier")
				.nullValue("product").stringType("registeredAt");
		// Create the contract(Pact)
		return builder.given("Request 1 - POST").uponReceiving("A request to create a Sale").method("POST")
				.path("/api/sales").headers(headers).body(reqBody).willRespondWith().status(200).body(resBody)
				.toPact(V4Pact.class);
	}

	// Consumer test with mock provider
	@Test
	@PactTestFor(providerName = "userprovider", pactMethod = "createPostFragment")
	public void postRequestTest(MockServer mockServer) {
		// Send request, get response, assert response
		Map<String, Object> reqBody = new HashMap<>();
		reqBody.put("id", 2);
		reqBody.put("cashId", 2);
		reqBody.put("cashierId", 2);
		reqBody.put("productId", 2);
		reqBody.put("registeredAt", "2025-07-03");

		// Send request, get response, assert response
		given().baseUri(mockServer.getUrl() + "/api/sales").headers(headers).body(reqBody).log().all().when().post()
				.then().statusCode(200).body("id", equalTo(2)).log().all();
	}


	@Pact(consumer = "userconsumer", provider = "userprovider")
	public V4Pact createGetFragment(PactDslWithProvider builder) {
	    // setting headers
	    headers.put("Content-Type", "application/json");  // Fixed header
	    
	    // create json body that matches what provider actually returns
	    DslPart resBody = PactDslJsonArray.arrayMinLike(1)
	            .integerType("id")
	            .stringType("registeredAt");
	    
	    // Create the contract(Pact)
	    return builder.given("Request 2 - GET ALL")
	        .uponReceiving("A request to get cashiers")
	        .method("GET")
	        .path("/api/sales")
	        .headers(headers)
	        .willRespondWith()
	        .status(200)
	        .body(resBody)
	        .toPact(V4Pact.class);
	}
	
	// Consumer test with mock provider
	@Test
	@PactTestFor(providerName = "userprovider", pactMethod = "createGetFragment")
	public void getRequestTest(MockServer mockServer) {

		// Send request, get response, assert response
		given().baseUri(mockServer.getUrl() + "/api/sales").headers(headers).log().all().when().get().then()
				.statusCode(200).log().all();
	}
	
	// Create the Fragment for GET BY ID request
	@Pact(consumer = "userconsumer", provider = "userprovider")
	public V4Pact createGetByIdFragment(PactDslWithProvider builder) {
		// Set headers
		headers.put("Content-Type", "application/json");
		// Create the JSON body
		DslPart resBody = ((PactDslJsonBody) new PactDslJsonBody()
			    .integerType("id", 2)
			    .object("cash")
			        .integerType("id", 99)
			        .integerType("floor", 50)
			    .closeObject()
			    .object("cashier")
			        .integerType("id", 99)
			        .stringType("surname_name", "Bella")
			    .closeObject()
			    .object("product")
			        .integerType("id", 99)
			        .stringType("name", "Chocolate")
			        .integerType("price", 78)
			    .closeObject()
			).stringType("registeredAt", "2025-07-03");


		// Create the contract(Pact)
		return builder.given("Request 3 - GET BY ID").uponReceiving("A request to get a cashier").method("GET")
//				.path("/api/sales")
				.headers(headers)
				.pathFromProviderState("/api/sales/${id}", "/api/sales/2").willRespondWith().status(200)
				.body(resBody).toPact(V4Pact.class);
	}

	// Consumer test with mock provider
	@Test
	@PactTestFor(providerName = "userprovider", pactMethod = "createGetByIdFragment")
	public void getByIdRequestTest(MockServer mockServer) {
		// Send request, get response, assert response
		// Send request, get response, assert response
		given().baseUri(mockServer.getUrl() + "/api/sales").headers(headers).log().all().when().get("/2")
				.then().statusCode(200).body("id", equalTo(2)).log().all();
	}

	// Create the Fragment for DELETE BY ID request
	@Pact(consumer = "userconsumer", provider = "userprovider")
	public V4Pact createDeleteByIdFragment(PactDslWithProvider builder) {
		// Set headers
		headers.put("Content-Type", "application/json");

		// Create the contract(Pact)
		return builder.given("Request 4 - DELETE BY ID").uponReceiving("A request to delete a cashier").method("DELETE")
				.path("/api/sales").headers(headers).pathFromProviderState("/api/sales/${id}", "/api/sales/2")
				.willRespondWith().status(200).toPact(V4Pact.class);
	}

	// Consumer test with mock provider
	@Test
	@PactTestFor(providerName = "userprovider", pactMethod = "createDeleteByIdFragment")
	public void deleteByIdRequestTest(MockServer mockServer) {

		// Send request, get response, assert response
		given().baseUri(mockServer.getUrl() + "/api/sales").headers(headers).log().all().when().delete("/2").then()
				.statusCode(200).log().all();
	}

}
