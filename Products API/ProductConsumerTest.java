package Products;

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
public class ProductConsumerTest {
	// Set the headers
    Map<String, String> headers = new HashMap<>();
    int key;
    // Create the Fragment for POST request
    @Pact(consumer = "userconsumer", provider = "userprovider")
    public V4Pact createPostFragment(PactDslWithProvider builder) {
        // Set headers
        headers.put("Content-Type", "application/json");
        // Create the JSON body
        DslPart reqResBody = new PactDslJsonBody()
        		.integerType("id",3)
                .stringType("name", "phone")
                .integerType("price",18000);
        // Create the contract(Pact)
        return builder.given("Request 1 - POST").
        		uponReceiving("A request to create a products").
        		method("POST")
				.path("/api/products")
				.headers(headers).body(reqResBody)
				.willRespondWith().status(200)
				.body(reqResBody)
				.toPact(V4Pact.class);
    }
    // Consumer test with mock provider
    @Test
    @PactTestFor(providerName = "userprovider", pactMethod = "createPostFragment")
    public void postRequestTest(MockServer mockServer) {
        // Send request, get response, assert response
    	Map<String, Object> reqBody = new HashMap<>();
    	reqBody.put("id", 3);
    	reqBody.put("name", "phone");
    	reqBody.put("price", 18000);
        // Send request, get response, assert response
        given().
        	baseUri(mockServer.getUrl()+ "/api/products")
        	.headers(headers)
        	.body(reqBody)
        	.log().all().
        when()
        	.post().
        then()
        	.statusCode(200)
        	.body("name", equalTo("phone"))
        	.log().all();
    }
    // Create the Fragment for PUT request
    @Pact(consumer = "userconsumer", provider = "userprovider")
    public V4Pact createPutFragment(PactDslWithProvider builder) {
        // Set headers
        headers.put("Content-Type", "application/json");
        // Create the JSON body
        DslPart reqResBody = new PactDslJsonBody()
        		.integerType("id",1)
                .stringType("name", "Tab")
                .integerType("price",42000);
        // Create the contract(Pact)
        return builder.given("Request 2 - PUT")
				.uponReceiving("A request to modify a product")
				.method("PUT")
//                	.path("/api/cashiers")
				.headers(headers)
				.body(reqResBody)
				.pathFromProviderState("/api/products/${id}", "/api/products/1")
				.willRespondWith()
				.status(200)
				.body(reqResBody)
				.toPact(V4Pact.class);
    }
    // Consumer test with mock provider
    @Test
    @PactTestFor(providerName = "userprovider", pactMethod = "createPutFragment")
    public void putRequestTest(MockServer mockServer) {
        // Send request, get response, assert response
    	Map<String, Object> reqBody = new HashMap<>();
    	reqBody.put("id", 1);
        reqBody.put("name", "Tab");
        reqBody.put("price", 42000);
        // Send request, get response, assert response
        given().
        	baseUri(mockServer.getUrl()+ "/api/products")
        	.headers(headers)
        	.body(reqBody)
        	.pathParam("id", 1)
        	.log().all().
        when()
        	.put("/{id}").
        then()
        	.statusCode(200)
        	.body("name", equalTo("Tab"))
        	.log().all();
    }
 // Consumer test for GET fragment
 		@Pact(consumer = "userconsumer", provider = "userprovider")
 		public V4Pact createGetFragment(PactDslWithProvider builder) {
 			// setting headers
 			headers.put("Content-Type", "application/json");
 			// create json body
 			DslPart resBody = PactDslJsonArray.arrayMinLike(1)
 					.integerType("id", 1)
 					.stringType("name","phone")
 	                .integerType("price",18000)
 					.integerType("id", 2)
 					.stringType("name", "Tablet")
                    .integerType("price",42000);
 			// Create the contract(Pact)
 			return builder.given("Request 3 - GET ALL")
 					.uponReceiving("A request to get all products")
 					.method("GET")
 					.path("/api/products")
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
    	given()
    		.baseUri(mockServer.getUrl() + "/api/products")
    		.headers(headers)
    		.log().all().
        when()
        	.get().
        then()
        	.statusCode(200)
        	.log().all();
    }
    // Create the Fragment for GET BY ID request
    @Pact(consumer = "userconsumer", provider = "userprovider")
    public V4Pact createGetByIdFragment(PactDslWithProvider builder) {
        // Set headers
        headers.put("Content-Type", "application/json");
        // Create the JSON body
        DslPart reqResBody = new PactDslJsonBody()
        		.integerType("id",1)
                .stringType("name", "phone")
                .integerType("price",18000);
        // Create the contract(Pact)
        return builder.given("Request 4 - GET BY ID")
        		.uponReceiving("A request to get a product")
        		.method("GET")
//            	.path("/api/cashiers")
			    .headers(headers)
			    .pathFromProviderState("/api/products/${id}", "/api/products/1")
			    .willRespondWith()
			    .status(200).body(reqResBody)
			    .toPact(V4Pact.class);
    }
    // Consumer test with mock provider
    @Test
    @PactTestFor(providerName = "userprovider", pactMethod = "createGetByIdFragment")
    public void getByIdRequestTest(MockServer mockServer) {
        // Send request, get response, assert response
    	
        // Send request, get response, assert response
        given().
        	baseUri(mockServer.getUrl()+ "/api/products")
        	.headers(headers)
        	.log().all().
        when()
        	.get("/1").
        then()
        	.statusCode(200) 
        	.log().all();
    }
    
 // Create the Fragment for DELETE BY ID request
 	@Pact(consumer = "userconsumer", provider = "userprovider")
 	public V4Pact createDeleteByIdFragment(PactDslWithProvider builder) {
 		// Set headers
 		headers.put("Content-Type", "application/json");

 		// Create the contract(Pact)
 		return builder.given("Request 5 - DELETE BY ID").uponReceiving("A request to delete a product").method("DELETE")
//                 	.path("/api/cashiers")
 				.headers(headers).pathFromProviderState("/api/products/${id}", "/api/products/2").willRespondWith()
 				.status(200).toPact(V4Pact.class);
 	}

 	// Consumer test with mock provider
 	@Test
 	@PactTestFor(providerName = "userprovider", pactMethod = "createDeleteByIdFragment")
 	public void deleteByIdRequestTest(MockServer mockServer) {

 		// Send request, get response, assert response
 		given().baseUri(mockServer.getUrl() + "/api/products").headers(headers).log().all().when().delete("/2").then()
 				.statusCode(200).log().all();
 	}
}
