package stepDefinitions;

import java.io.File;
import java.io.IOException;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import util.PropertiesReader;
import io.restassured.module.jsv.JsonSchemaValidator;

public class SkillsSteps {

	RequestSpecification request;
	ValidatableResponse response;
	String endPoint;

	@Before
	public void authorize() throws IOException {
		PropertiesReader.readProperties();
		request = PropertiesReader.getAuthorizedRequest();
		
	}

	@Given("I set the endpoint to get all skills")
	public void i_set_the_endpoint_to_get_all_skills() {
		endPoint = PropertiesReader.setEndPoint("skills");

	}

	@When("I send the get request")
	public void i_send_the_get_request() {
		response = request.get(endPoint).then();
	}

	@Then("I validate the {int}")
	public void i_validate_the_status_code(Integer statusCode) {
		response.statusCode(statusCode);
	

	}

	

	@Then("I validate the Json response schema from {string}")
	public void i_validate_the_json_response_schema_from(String jsonSchema) {
		response.assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File(jsonSchema)));
	}


	@Given("I set the endpoint to get the skill with {int}")
	public void i_set_the_endpoint_to_get_the_skill_with(Integer id) {
		// Write code here that turns the phrase above into concrete actions
		endPoint = PropertiesReader.setEndPoint("skills") + "/" + String.valueOf(id);
	}

	
	
	@Then("I validate in the response with {int} and {string}")
	public void i_validate_in_the_response_with_and(Integer id, String skillName) {
		response.body("skill_id", Matchers.equalTo(id));
		response.body("skill_name", Matchers.equalTo(skillName));
	}
	
	@Then("I validate in the response with {string}")
	public void i_validate_in_the_response_with(String message) {
	    response.body("message", Matchers.containsString(message));
	}
	
	@Given("I set the endpoint to delete the skill with {int}")
	public void i_set_the_endpoint_to_delete_the_skill_with(Integer id) {
		endPoint = PropertiesReader.setEndPoint("skills") + "/" + String.valueOf(id);
	}
	@When("I send the delete request")
	public void i_send_the_delete_request() {
	    response=request.delete(endPoint).then();
	}
	
	@Then("I validate in the delete response with {int} and {string}")
	public void i_validate_in_the_delete_response_with_and(Integer id, String message) {
		response.body("Skill_Id", Matchers.equalTo(String.valueOf(id)));
		response.body("message_response", Matchers.containsString(message));
	    
	}
	
	@Given("I set the end point to post a new skill")
	public void i_set_the_end_point_to_post_a_new_skill() {
		endPoint = PropertiesReader.setEndPoint("skills");
	}
	
	
	@SuppressWarnings("unchecked")
	@Given("I form the json request body with {string}")
	public void i_form_the_json_request_body_with(String skillName) {
		JSONObject requestParams = new JSONObject(); 
		requestParams.put("skill_name", skillName); 
		request.header("Content-Type", "application/json"); // Add the Json to the body of the request 
		request.body(requestParams.toJSONString());
		
	}
	
	@When("I send the post request")
	public void i_send_the_post_request() {
		response=request.post(endPoint).then();
	    
	}
	
	@Then("I validate {string} in the response")
	public void i_validate_in_the_response(String skillName) {
	    response.assertThat().body("skill_name", Matchers.equalTo(skillName));
	}
	
	@Given("I set the end point to put with {int}")
	public void i_set_the_end_point_to_put_with(Integer id) {
		endPoint = PropertiesReader.setEndPoint("skills") + "/" + String.valueOf(id);
	}
	
	@When("I send the put request")
	public void i_send_the_put_request() {
	    response=request.put(endPoint).then();
	}
	
}
