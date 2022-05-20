
Feature: Validate Delete method for Users Api
  

  	 
 @deleteUser 
  Scenario Outline: Test the Delete Request to delete the user with valid user id
    Given User set the api endpoint with user id <user_id>
    When User sends the delete request
    Then User validates the status Code <statusCode>
    And User validates in the delete response with <user_id> and <message_response>
    Examples:
      |user_id   |statusCode    |message_response                         |
      |"UsersPropertiesFile"|200 | "The record has been deleted !"|
     

  Scenario Outline: Test the Delete Request to delete the user with invalid user id
    Given User set the api endpoint with user id <user_id>
    When User sends the delete request
    Then User validates the status Code <statusCode>
    And User validates message <message> in the response body
    Examples:
      | user_id    | statusCode | message		  |
      | 	"U99999"	|     404 		| "Not Found"	|
