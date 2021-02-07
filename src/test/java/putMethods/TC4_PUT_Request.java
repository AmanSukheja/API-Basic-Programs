package putMethods;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC4_PUT_Request {
	
	@Test
	public void updateAnActivity() {
		
		RequestSpecification request = RestAssured.given();
		
		request.auth().basic("admin", "p");
		
		JSONObject json=new JSONObject();
		
		json.put("workspaceCode", "API");
		json.put("projectCode", "API");
		json.put("activityName", "Act7");
		json.put("activityCode", "Act7");
		json.put("actualThisPeriodLaborUnits", 12);
		
		request.header("Content-Type", "application/json");
		request.body(json.toJSONString());
		
		Response response = request.put("http://den02tsp.us.oracle.com:7001/api/restapi/activity");
		
		int statusCode = response.getStatusCode();
		System.out.println("Status code is "+statusCode);
		
		Assert.assertEquals(statusCode, 204);
		
	}

}
