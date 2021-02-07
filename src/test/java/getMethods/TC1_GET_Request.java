package getMethods;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC1_GET_Request {

	@Test
	public void getActivityDetails() {
		
		RestAssured.baseURI = "http://den02tsp.us.oracle.com:7001/api/restapi/activity";
		
		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.auth().basic("admin", "p");
		Response httpResponse = httpRequest.request(Method.GET, "/232701");
		
		String responseBody = httpResponse.getBody().asString();
		System.out.println("Response Body is : " +responseBody);
		
		Assert.assertEquals(responseBody.contains("PRE_MITIGATED"), true);
		
		System.out.println("******************************************************************************************************");
		
		JsonPath jsonpath=httpResponse.jsonPath();
		System.out.println("Duration Type is "+jsonpath.get("durationType"));
		System.out.println("WBS Id is " +jsonpath.get("wbsId"));
		
		System.out.println("******************************************************************************************************");
		
		int statusCode = httpResponse.getStatusCode();
		System.out.println("Status code is : " +statusCode);
		Assert.assertEquals(statusCode, 200);
		
		String statusLine = httpResponse.getStatusLine();
		System.out.println("Status line is : " +statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		
		System.out.println("******************************************************************************************************");
		
		String contentType =httpResponse.header("Content-Type");
		System.out.println("Content Type is " +contentType);
		Assert.assertEquals(contentType, "application/json");
		
		
		System.out.println("******************************************************************************************************");
		Headers allHeaders = httpResponse.headers();
		//Printing all headers
		for(Header header: allHeaders)
		{
			System.out.println(header.getName() +"   "+header.getValue() );
		}
		
		System.out.println("******************************************************************************************************");
	}
}
