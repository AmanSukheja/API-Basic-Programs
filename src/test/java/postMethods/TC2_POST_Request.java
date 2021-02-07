package postMethods;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC2_POST_Request {

	@Test
	public void postData() {
		
		RestAssured.baseURI = "http://den02tsp.us.oracle.com:7001/api/restapi/activity";
		
		RequestSpecification httpsRequest = RestAssured.given();
		httpsRequest.auth().basic("admin", "p");
		
		JSONObject requestParams = new JSONObject();
		requestParams.put("workspaceCode", "API");
		requestParams.put("projectCode", "API");
		requestParams.put("activityName", "Auto_act");
		requestParams.put("activityCode", "Auto_act");
	
		httpsRequest.header("Content-Type", "application/json");
		httpsRequest.body(requestParams.toJSONString());
		
		Response httpResponse = httpsRequest.request(Method.POST);
		
		//Validation
		String responseBody = httpResponse.getBody().asString();
		System.out.println("Response Body is : " +responseBody);
		
		int statusCode = httpResponse.getStatusCode();
		System.out.println("Status code is : "+statusCode);
		Assert.assertEquals(statusCode, 201);
	}
}
