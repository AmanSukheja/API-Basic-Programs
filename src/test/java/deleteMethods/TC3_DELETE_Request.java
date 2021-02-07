package deleteMethods;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC3_DELETE_Request {
	
	@Test
	public void deleteAnActivity() {
		
		RestAssured.baseURI="http://den02tsp.us.oracle.com:7001/api/restapi/activity";
		RequestSpecification request =RestAssured.given();
		
		request.auth().basic("admin", "p");
	//	Response response = request.delete("http://den02tsp.us.oracle.com:7001/api/restapi/activity/236204");
		
		Response response = request.request(Method.DELETE, "/236205");
		int code = response.getStatusCode();
		System.out.println("Status code is "+code);
		Assert.assertEquals(code, 204);	
	}
}
