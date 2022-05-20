package stepDefinitions;

import static io.restassured.RestAssured.given;
import java.io.File;
import java.io.IOException;

import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.junit.Assert;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import util.PropertiesReader;
import util.UserPropertiesReaderWriter;
import util.Utils;

public class UsersAPI extends Utils{
	
	RequestSpecification request_specification,request;
	String endPoint;
	ValidatableResponse response,responseR,responsePut;
	UserPropertiesReaderWriter userP=new UserPropertiesReaderWriter();
	String user_id;
	
	@Before
	public void authorize() throws IOException {
		PropertiesReader.readProperties();
		request = PropertiesReader.getAuthorizedRequest();
		
	}



	/*
	 * public RequestSpecification requestSpecification() throws IOException {
	 * 
	 * if (request_specification == null) {
	 * 
	 * request_specification = new
	 * RequestSpecBuilder().setContentType(ContentType.JSON).setBaseUri(
	 * getGlobalValue("baseUrl")).build();
	 * 
	 * return request_specification; } return request_specification; }
	 */


	
	
	
	
	@Given("User set the endpoint to get all users")
	public void user_set_the_endpoint_to_get_all_users() {
		endPoint = PropertiesReader.setEndPoint("users");
	}

	@When("User sends the get request")
	public void user_sends_the_get_request() {
		response = request.get(endPoint).then();
	}
	
	@When("User sends the post request")
	public void i_send_the_post_request() throws IOException {
		response=request.post(endPoint).then();	
		
		JsonPath jPath=response.extract().jsonPath();
		String userId=jPath.get("user_id");
		
		if(userId!=null) {
			userP.writeUserProperties("user_id", userId);
		}
		
		
		/*
		 * String r = response.extract().asPrettyString(); JsonPath j = new
		 * JsonPath(r.toString()); System.out.println("Post request Response=>"+r); if
		 * (r.toString().contains("user_id")==true) { String uid=j.get("user_id");
		 * System.out.println("User id= "+uid); userP.writeUserProperties("user_id",
		 * uid);
		 * System.out.println("User id stored in properties file after post request=>"
		 * +userP.readUserProperties("user_id").toString()); System.out.
		 * println("Post request sent and user id was captured in users.properties file"
		 * ); }else
		 * {System.out.println("Post request sent and no user id was captured");}
		 * 
		 */
	}
	
	@When("User sends the put request")
	public void i_send_the_put_request() {
		response=request.put(endPoint).then();
		
	}

	@Then("User validates the status Code {int}")
	public void user_validates_the_status_code(Integer statusCode) {
		response.assertThat().statusCode(statusCode);		
	}
	
	@Then("User validates the status Code {int} and status line {string}")
	public void user_validates_the_status_code_and_status_line(Integer statusCode, String statusLine) {
		response.assertThat().statusCode(statusCode);
		response.assertThat().statusLine(statusLine);
	}


	@Then("User validates the Json response schema from {string}")
	public void user_validates_the_json_response_schema_from_system_get_property(String jsonSchemaPath) {
		response.assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File(jsonSchemaPath)));
	}
	
	
	@Given("User set the endpoint to get the user with id {string}")
	public void user_set_the_endpoint_to_get_the_user_with_id(String user_id) { 
		endPoint = PropertiesReader.setEndPoint("users") + "/" + user_id;
	}

	
	@Then("User validates message {string} in the response body")
	public void user_validates_message_in_the_response_body(String message) {
		 response.body("message", Matchers.containsString(message));
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Given("User forms the json request body with {string} {long} {string} {string} {string} {string}")
	public void user_forms_the_json_request_body_with(String name, long phone_number, String location, String time_zone, String visa_status, String linkedin_url) {
		JSONObject requestParams = new JSONObject(); 
		requestParams.put("name", name); 
		requestParams.put("phone_number", phone_number);
		requestParams.put("location", location);
		requestParams.put("time_zone", time_zone);
		requestParams.put("visa_status", visa_status);
		requestParams.put("linkedin_url", linkedin_url);
		request.header("Content-Type", "application/json");  
		request.body(requestParams.toJSONString());
	}
	
	
	
	@Then("User validates response body fields {string} {long} {string} {string} {string} {string} using user Id")
	public void user_validates_response_body_fields_using_user_id(String name, long phone_number, String location, String time_zone, String visa_status, String linkedin_url) throws IOException {
		
		String r = response.extract().asPrettyString();
		JsonPath j = new JsonPath(r.toString());		
		System.out.println(r);		
		String uid=j.get("user_id");
		System.out.println("User id= "+uid);
		
		  
		  JsonPath jsR = new JsonPath(r.toString());
		  String actualname = jsR.get("name"); 	
		  System.out.println(actualname);
		  long actualphone_number =jsR.get("phone_number");
		  System.out.println("Expectedphone_number=>"+phone_number);
		  String actuallocation = jsR.get("location"); 
		  String actualtime_zone = jsR.get("time_zone");		  
		  String actuallinkedin_url = jsR.get("linkedin_url"); 
		  Assert.assertEquals(name, actualname);
		  Assert.assertEquals(phone_number, actualphone_number);		  
		  Assert.assertEquals(location, actuallocation);
		  Assert.assertEquals(time_zone, actualtime_zone);		 
		  Assert.assertEquals(linkedin_url, actuallinkedin_url);

		  
	}
	
	
	@Given("User set the api endpoint with user id {string}")
	public void user_set_the_endpoint_with_user_id(String user_id) throws IOException {
	    if(user_id.equalsIgnoreCase("UsersPropertiesFile"))
	    {
	    	user_id=userP.readUserProperties("user_id").toString();
	    }
	    else
	    {
	    	this.user_id=user_id;
	    }
	    System.out.println(user_id);
	    endPoint = PropertiesReader.setEndPoint("users") + "/" + user_id;
	}

	@Then("User validates response body fields {string} {long} {string} {string} {string} {string} for user {string}")
	public void user_validates_response_body_fields_for_user(String name, long phone_number, String location, String time_zone, String linkedin_url, String visa_status, String user_id) throws IOException {
		if(user_id.equalsIgnoreCase("UsersPropertiesFile"))
	    {
	    	user_id=userP.readUserProperties("user_id").toString();
	    }
		else
	    {
	    	this.user_id=user_id;
	    }
		  request_specification = given().spec(requestSpecification()).queryParam("user_id", user_id);		  
		  responseR=request_specification.when().get(PropertiesReader.setEndPoint("users")+"/"+user_id).then();
		 
		  String s= response.extract().asPrettyString();
		  System.out.println(s);
		  JsonPath jsR = new JsonPath(s.toString());
		  String actualname = jsR.get("name"); 	 
		  String actuallocation = jsR.get("location"); 		  
		  Assert.assertEquals(name, actualname);		
		  Assert.assertEquals(location, actuallocation);
		 		
	}	

	@When("User sends the delete request")
	public void user_sends_the_delete_request() {
		 response=request.delete(endPoint).then();
	}

	@Then("User validates in the delete response with {string} and {string}")
	public void user_validates_in_the_delete_response_with_and(String user_id, String message) throws IOException {
		 if(user_id.equalsIgnoreCase("UsersPropertiesFile"))
		    {
		    	user_id=userP.readUserProperties("user_id").toString();
		    }
		 else
		    {
		    	this.user_id=user_id;
		    }
		
		response.body("message_response", Matchers.containsString(message));
	}


	
	
}
