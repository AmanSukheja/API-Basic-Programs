package dataDrivenTestCases;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ExcelData_AddAnActivity {
	
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
	String [][] getActivityData() throws IOException
	{
		String path = System.getProperty("user.dir")+"/src/test/java/dataDrivenTestCases/activityData.xlsx";
		
		int rownum = ExcelUtil.getRowCount(path, "sheet1");
		int colnum = ExcelUtil.getCellCount(path, "sheet1", rownum);
		String actData[][] = new String[rownum][colnum];
		
		for(int i=1;i<=rownum;i++) {
			for(int j=0;j<colnum;j++)
			{
				actData[i-1][j]=ExcelUtil.getCellData(path, "sheet1", i, j);
			}
		}
		return(actData);
	}
}
