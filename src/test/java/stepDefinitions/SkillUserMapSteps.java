package stepDefinitions;

import java.io.File;
import java.io.IOException;

import org.hamcrest.Matchers;
import org.json.simple.JSONObject;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import util.PropertiesReader;

public class SkillUserMapSteps {
	
	RequestSpecification request;
	ValidatableResponse response;
	String endPoint;
	
	@Before
	public void authorize() throws IOException {
		PropertiesReader.readProperties();
		request = PropertiesReader.getAuthorizedRequest();
		
	}
	
	@Given("I set the endpoint to get all user-skills")
	public void i_set_the_endpoint_to_get_all_user_skills() {
		endPoint = PropertiesReader.setEndPoint("userSkillMap");
	}
	@When("I send the user-skill get request")
	public void i_send_the_user_skill_get_request() {
		response = request.get(endPoint).then();
	}
	@Then("I validate the {int} in the user-skill response")
	public void i_validate_the_in_the_user_skill_response(Integer statusCode) {
		response.statusCode(statusCode);
	}
	@Then("I validate the user-skill response Json schema from {string}")
	public void i_validate_the_user_skill_response_json_schema_from(String jsonSchema) {
		response.assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File(jsonSchema)));
	}

	@Given("I set the endpoint to get the user-skill with {string}")
	public void i_set_the_endpoint_to_get_the_user_skill_with(String id) {
		endPoint = PropertiesReader.setEndPoint("userSkillMap") + "/" + id;
	}
	@Then("I validate in the response with {string} {string} {int}  {int}")
	public void i_validate_in_the_response_with(String userSkillId, String userId, Integer skillId, Integer monthsOfExp) {
	    response.body("user_skill_id",Matchers.equalTo(userSkillId));
	    response.body("user_id",Matchers.equalTo(userId));
	    response.body("skill_id",Matchers.equalTo(skillId));
	    response.body("months_of_exp",Matchers.equalTo(monthsOfExp));
	    
	}
	
	@Then("I validate in the user-skill response with {string}")
	public void i_validate_in_the_user_skill_response_with(String message) {
		response.body("message", Matchers.containsString(message));
	}
	
	@Given("I set the endpoint to delete the user skill with {string}")
	public void i_set_the_endpoint_to_delete_the_user_skill_with(String id) {
		endPoint = PropertiesReader.setEndPoint("userSkillMap") + "/" + id;
	}
	@When("I send the user-skill delete request")
	public void i_send_the_user_skill_delete_request() {
	    response=request.delete(endPoint).then();
	}
	@Then("I validate in the user-skill delete response with {string} and {string}")
	public void i_validate_in_the_user_skill_delete_response_with_and(String userSkillId, String message) {
		response.body("user_skill_id", Matchers.equalTo(String.valueOf(userSkillId)));
		response.body("message_response", Matchers.containsString(message));
	}
	
	@Given("I set the endpoint to post the user-skill")
	public void i_set_the_endpoint_to_post_the_user_skill() {
		endPoint = PropertiesReader.setEndPoint("userSkillMap");
	}
	@Given("I form the Json request body with {int} {string} {int}")
	public void i_form_the_json_request_body_with(Integer skillId, String userId, Integer monthsOfExp) {
		JSONObject requestParams = new JSONObject(); 
		requestParams.put("skill_id", skillId);
		requestParams.put("user_id",userId);
		requestParams.put("months_of_exp", monthsOfExp);
		request.header("Content-Type", "application/json"); // Add the Json to the body of the request 
		request.body(requestParams.toJSONString());
	}
	@When("I send the user-skill post request")
	public void i_send_the_user_skill_post_request() {
		response=request.post(endPoint).then();
	}
	
	@Given("I set the user-skill end point to put with {string}")
	public void i_set_the_user_skill_end_point_to_put_with(String id) {
		endPoint = PropertiesReader.setEndPoint("userSkillMap") + "/" + id;
	}
	@When("I send the user-skill put request")
	public void i_send_the_user_skill_put_request() {
	    response=request.put(endPoint).then();
	}







}
