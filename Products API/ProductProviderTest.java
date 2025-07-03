package Products;

import java.util.HashMap;
import java.util.Map;

import org.apache.hc.core5.http.HttpRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.loader.PactFolder;

@Provider("userprovider")
@PactFolder("target/pacts")
public class ProductProviderTest {
	@BeforeEach
    public void setUp(PactVerificationContext context) {
        HttpTestTarget target = new HttpTestTarget("localhost", 8181);
        context.setTarget(target);
    }
    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    public void providerTest(PactVerificationContext context, HttpRequest request) {
    	context.verifyInteraction();
    }
    @State("Request 1 - POST")
    public Map<String, String> state1() {
		Map<String, String> data = new HashMap<String, String>();
		data.put("id", "1");
		data.put("name", "phone");
		data.put("price", "18000");
		return data;
    }
    @State("Request 2 - PUT")
	public Map<String, String> state2() {
		Map<String, String> data = new HashMap<String, String>();
		data.put("id", "2");
		return data;
	}
    @State("Request 3 - GET ALL")
	public void state3() {
	}
	@State("Request 4 - GET BY ID")
	public Map<String, String> state4() {
		Map<String, String> data = new HashMap<String, String>();
		data.put("id", "2");
		return data;
	}
	@State("Request 5 - DELETE BY ID")
	public Map<String, String> state5() {
		Map<String, String> data = new HashMap<String, String>();
		data.put("id", "3");
		return data;
	}
}

