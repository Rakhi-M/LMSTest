
@tag
Feature: Validate skills api delete request
  

  @delete
  Scenario Outline: Test the Delete Request to delete the skill with valid id
    Given I set the endpoint to delete the skill with <id>
    When I send the delete request
    Then I validate the <status code>
    And I validate in the delete response with <id> and <message>
    

    Examples:
      |id   |status code    |message                         |
      | 259	|200         		| "The record has been deleted !"|
     

  Scenario Outline: Test the Delete Request to delete the skill with invalid id
    Given I set the endpoint to delete the skill with <id>
    When I send the delete request
    Then I validate the <status code>
    And I validate in the response with <message>
    
    Examples:
      | id    | status code | message		  |
      | 200		|     404 		| "Not Found"	|
      | 137	  |     404			| "Not Found" |