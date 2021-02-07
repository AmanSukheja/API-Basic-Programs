package dataDrivenTestCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DataProvider_AddAnActivity {
	
	@Test(dataProvider="addActivityData")
	public void addNewActivity(String wrkCode, String prjCode, String actName)
	{
		
		RestAssured.baseURI = "http://den02tsp.us.oracle.com:7001/api/restapi/activity";
		RequestSpecification httpRequest = RestAssured.given();
		
		httpRequest.auth().basic("admin", "p");
		
		JSONObject requestParams = new JSONObject();
		requestParams.put("workspaceCode", wrkCode);
		requestParams.put("projectCode", prjCode);
		requestParams.put("activityName", actName);
		
		httpRequest.header("Content-Type", "application/json");
		
		httpRequest.body(requestParams.toJSONString());
		
		//POST Response
		Response httpResponse = httpRequest.request(Method.POST);
		
		String responseBody = httpResponse.getBody().asString();
		
		System.out.println("Response Body is : "+responseBody);
		
		Assert.assertEquals(responseBody.contains(wrkCode), true);
		Assert.assertEquals(responseBody.contains(prjCode), true);
		Assert.assertEquals(responseBody.contains(actName), true);
		
		int statusCode = httpResponse.getStatusCode();
		Assert.assertEquals(statusCode, 201);
	}

	@DataProvider(name="addActivityData")
	String [][] getActivityData()
	{
		String actData[][]= {
				{"API", "API", "Auto1"}, {"API", "API", "Auto2"},{"API", "API", "Auto3"}
		};
		return(actData);
	}
}
