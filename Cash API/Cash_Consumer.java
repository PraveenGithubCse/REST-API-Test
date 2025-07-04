package Cash;

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
public class CashConsumerTest {
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
        		.integerType("id",1)
                .integerType("floor",57);
        // Create the contract(Pact)
        return builder.given("Request 1 - POST")
                .uponReceiving("A request to create a cash")
                	.method("POST")
                	.path("/api/cash-registers")
                	.headers(headers)
                	.body(reqResBody)
                .willRespondWith()
                	.status(200)
                	.body(reqResBody)
                .toPact(V4Pact.class);
    }
    //
    @Test
    @PactTestFor(providerName = "userprovider", pactMethod = "createPostFragment")
    public void postRequestTest(MockServer mockServer) {
        // Send request, get response, assert response
    	Map<String, Object> reqBody = new HashMap<>();
    	reqBody.put("id", 1);
    	reqBody.put("floor", 57);
        // Send request, get response, assert response
        given().
        	baseUri(mockServer.getUrl()+ "/api/cash-registers")
        	.headers(headers)
        	.body(reqBody)
        	.log().all().
        when()
        	.post().
        then()
        	.statusCode(200)
        	.body("floor", equalTo(57))
        	.log().all();
    }
    
 // Create the Fragment for PUT request
 	@Pact(consumer = "userconsumer", provider = "userprovider")
 	public V4Pact createPutFragment(PactDslWithProvider builder) {
 		// Set headers
 		headers.put("Content-Type", "application/json");
 		// Create the JSON body
 		DslPart reqResBody = new PactDslJsonBody().integerType("id", 1).integerType("floor", 87);
 		// Create the contract(Pact)
 		return builder.given("Request 2 - PUT").uponReceiving("A request to modify a cash").method("PUT")
//                 	.path("/api/cashiers")
 				.headers(headers).body(reqResBody).pathFromProviderState("/api/cash-registers/${id}", "/api/cash-registers/1")
 				.willRespondWith().status(200).body(reqResBody).toPact(V4Pact.class);
 	}

 	// Consumer test with mock provider
 	@Test
 	@PactTestFor(providerName = "userprovider", pactMethod = "createPutFragment")
 	public void putRequestTest(MockServer mockServer) {
 		// Send request, get response, assert response
 		Map<String, Object> reqBody = new HashMap<>();
 		reqBody.put("id", 1);
 		reqBody.put("floor", 87);

 		// Send request, get response, assert response
 		given().baseUri(mockServer.getUrl() + "/api/cash-registers").headers(headers).pathParam("id", 1).body(reqBody).log()
 				.all().when().put("/{id}").then().statusCode(200).body("floor", equalTo(87)).log().all();
 	}
    
    // Create a Fragment for the GET request
    @Pact(consumer = "userconsumer", provider = "userprovider")
    public V4Pact createGetFragment(PactDslWithProvider builder)
    {
    	//setting headers
    	headers.put("Content-Type","application.json");
    	//create json body
    	DslPart reqResBody = PactDslJsonArray.arrayMinLike(1)
        		.integerType("id",1)
                .integerType("floor", 25)
    	        .integerType("id",2)
                .integerType("floor", 57);
    	// Create the contract(Pact)
        return builder.given("Request 3 - GET ALL")
                .uponReceiving("A request to get cash")
                .method("GET")
                .path("/api/cash-registers")
                .headers(headers)
                .willRespondWith()
                .status(200)
                .body(reqResBody)
                .toPact(V4Pact.class);
    }
    // Consumer test with mock provider
    @Test
    @PactTestFor(providerName = "userprovider", pactMethod = "createGetFragment")
    public void getRequestTest(MockServer mockServer) {
        // Send request, get response, assert response
    	given()
    		.baseUri(mockServer.getUrl() + "/api/cash-registers")
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
                .integerType("floor", 57);
        // Create the contract(Pact)
        return builder.given("Request 4 - GET BY ID")
                .uponReceiving("A request to get a cash")
                	.method("GET")
//                	.path("/api/cash-registers")
                	.headers(headers)
                	.body(reqResBody)
                	.pathFromProviderState("/api/cash-registers/${id}", "/api/cash-registers/1")
                .willRespondWith()
                	.status(200)
                	.body(reqResBody)
                .toPact(V4Pact.class);
    }
    // Consumer test with mock provider
    @Test
    @PactTestFor(providerName = "userprovider", pactMethod = "createGetByIdFragment")
    public void getByIdRequestTest(MockServer mockServer) {
        // Send request, get response, assert response
    	Map<String, Object> reqBody = new HashMap<>();
    	reqBody.put("id", 1);
        reqBody.put("floor", 57);
        // Send request, get response, assert response
        given().
        	baseUri(mockServer.getUrl()+ "/api/cash-registers")
        	.headers(headers)
        	.body(reqBody)
        	.log().all().
        when()
        	.get("/1").
        then()
//        	.statusCode(200)
//        	.body("floor", equalTo(57))
        	.log().all();
    }
    // Create the Fragment for DELETE BY ID request
    @Pact(consumer = "userconsumer", provider = "userprovider")
    public V4Pact createDeleteByIdFragment(PactDslWithProvider builder) {
        // Set headers
        headers.put("Content-Type", "application/json");
        // Create the contract(Pact)
        return builder.given("Request 5 - DELETE BY ID")
                .uponReceiving("A request to delete a cash")
                	.method("DELETE")
                	.path("/api/cash-registers")
                	.headers(headers)
                	.pathFromProviderState("/api/cash-registers/${id}", "/api/cash-registers/2")
                .willRespondWith()
                	.status(200)
                .toPact(V4Pact.class);
    }
    // Consumer test with mock provider
    @Test
    @PactTestFor(providerName = "userprovider", pactMethod = "createDeleteByIdFragment")
    public void deleteByIdRequestTest(MockServer mockServer) {
        // Send request, get response, assert response
        given().
        	baseUri(mockServer.getUrl()+ "/api/cash-registers")
        	.headers(headers)
        	.log().all().
        when()
        	.delete("/2").
        then()
        	.statusCode(200)
        	.log().all();
    }
}