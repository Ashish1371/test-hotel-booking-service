package helpers;

import java.util.Map;

import com.google.gson.Gson;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;

public class rest_client {
	
	RequestSpecBuilder reqSpeBuilder; 
	static RequestSpecification reqSpec;
	public static final int GET_REQUEST = 0;
	public static final int POST_REQUEST = 1;
	public static final int DELETE_REQUEST = 2;
	public static final int PUT_REQUEST = 3;
	public static final int PATCH_REQUEST = 4;
	String url;
	
	 protected rest_client() {
	}
	
	 protected rest_client(String url)
	 
	{
		 System.out.println("baseurl"+url);
		this.url=url;
		
	}	
	
		
	 protected void sendRequest(int methodId,Map<String,String> headers,Map <String,String> pathParams,Map <String,String> queryParams,Map <String,String> cookies,Map <String,Object> body,String basepath)
	{
		reqSpeBuilder = new RequestSpecBuilder();
		reqSpeBuilder.setBaseUri(this.url);
		Gson gson = new Gson();
				
		
		if(headers!=null) {
		reqSpeBuilder.addHeaders(headers);
		
		}
		
		if(pathParams!=null) {
		reqSpeBuilder.addPathParams(pathParams);
		System.out.println("iii"+pathParams);
		}
		
		if(queryParams!=null) {
		reqSpeBuilder.addQueryParams(queryParams);
		System.out.println("oooo"+queryParams);
		}
		if(cookies!=null) {
		reqSpeBuilder.addCookies(cookies);
		}
		reqSpeBuilder.setUrlEncodingEnabled(false);
		reqSpec = reqSpeBuilder.build();
		
		reqSpeBuilder.setUrlEncodingEnabled(false);
		switch(methodId) {
		
		case rest_client.GET_REQUEST:
			
			contextData.setResponse(given().spec(reqSpec).when().get(basepath));	
			System.out.println(contextData.getResponse().asString());
			
			break;
			
		case rest_client.POST_REQUEST:	
		
			
			
			System.out.println(body);			
			contextData.setResponse(given().spec(reqSpec).when().body(gson.toJson(body)).post(basepath));				
			System.out.println(contextData.getResponse().asString());			
			break;
			
		case rest_client.DELETE_REQUEST:
			contextData.setResponse(given().spec(reqSpec).when().delete(basepath));	
			System.out.println("DeleteData response code"+contextData.getResponse().statusCode());
			
			break;
		
		case rest_client.PUT_REQUEST:
            
			
			System.out.println(body);			
			contextData.setResponse(given().spec(reqSpec).when().body(gson.toJson(body)).put(basepath));				
			System.out.println("updated data"+contextData.getResponse().asString());
			break;
			
			
			
          case rest_client.PATCH_REQUEST:
            
			System.out.println(body);			
			contextData.setResponse(given().spec(reqSpec).when().body(gson.toJson(body)).patch(basepath));				
			System.out.println("updated data"+contextData.getResponse().asString());
			break;	
		
		default:
			break;
		
		
		}
		
		
	}
	

}
