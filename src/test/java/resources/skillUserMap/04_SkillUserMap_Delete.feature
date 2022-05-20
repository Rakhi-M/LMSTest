@tag
Feature: Validate user-skills api delete request
  

  
  Scenario Outline: Test the Delete Request to delete a user-skill with valid id
    Given I set the endpoint to delete the user skill with <user-skill id>
    When I send the user-skill delete request 
    Then I validate the <status code> in the user-skill response
    And I validate in the user-skill delete response with <user-skill id> and <message>
    

    Examples:
      |user-skill id   |status code    |message                         |
      | "US371"	       |200         	 | "The record has been deleted !!"|
     

  Scenario Outline: Test the Delete Request to delete the user-skill with invalid id
    Given I set the endpoint to delete the user skill with <user-skill id>
    When I send the user-skill delete request
    Then I validate the <status code> in the user-skill response
    And I validate in the user-skill response with <message>
    
    Examples:
      | user-skill id | status code | message		  |
      | "US124"	    	|     404 		| "Not Found"	|
      | "US500"	      |     404			| "Not Found" |