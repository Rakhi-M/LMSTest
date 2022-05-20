package stepDefinitions;

import java.io.File;
import java.io.IOException;

import org.hamcrest.Matchers;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import util.PropertiesReader;

public class UserSkillMapGetSteps {
	
	RequestSpecification request;
	ValidatableResponse response;
	String endPoint;
	
	@Before
	public void authorize() throws IOException {
		PropertiesReader.readProperties();
		request = PropertiesReader.getAuthorizedRequest();		
	}
	
	
	@Given("I set the endpoint to get all user-skill-maps")
	public void i_set_the_endpoint_to_get_all_user_skill_maps() {
		endPoint = PropertiesReader.setEndPoint("userSkillMapGet");
	}
	@When("I send the user-skill-map get request")
	public void i_send_the_user_skill_map_get_request() {
		response = request.get(endPoint).then();
	}
	@Then("I validate the {int} in the user-skill-map response")
	public void i_validate_the_in_the_user_skill_map_response(Integer statusCode) {
	    response.assertThat().statusCode(statusCode);
	}
	@Then("I validate the user-skill-map response Json schema from {string}")
	public void i_validate_the_user_skill_map_response_json_schema_from(String jsonSchema) {
		response.assertThat().body(matchesJsonSchema(new File(jsonSchema)));
	}
	
	@Given("I set the endpoint to get the user-skill-map with {string}")
	public void i_set_the_endpoint_to_get_the_user_skill_map_with(String userId) {
		endPoint = PropertiesReader.setEndPoint("userSkillMapGet") + "/" + userId;
	}
	
	@Then("I validate in the response with {string} {string}  {string}")
	public void i_validate_in_the_response_with(String userId, String firstName, String lastName) {
	    response.assertThat().body("users.id", Matchers.equalTo(userId));
	    response.assertThat().body("users.firstName", Matchers.equalTo(firstName));
	    response.assertThat().body("users.lastName", Matchers.equalTo(lastName));
	    
	}
	
	@Then("I validate in the user-skill-map response with {string}")
	public void i_validate_in_the_user_skill_map_response_with(String message) {
	    response.assertThat().body("message", Matchers.containsString(message));
	}
	
	@Given("I set the endpoint to get the user-skill-map with skill id {int}")
	public void i_set_the_endpoint_to_get_the_user_skill_map_with_skill_id(Integer skillId) {
		endPoint = PropertiesReader.setEndPoint("userSkillMapGetSkillId") + "/" + skillId;
	}

	

}
