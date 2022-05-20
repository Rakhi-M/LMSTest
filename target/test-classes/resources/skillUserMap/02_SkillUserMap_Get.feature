@tag
Feature: Validate Skill User Map Api Get Requests 

  @skillUserMap
  Scenario Outline: Test the Get Request to list all user-skills
    Given I set the endpoint to get all user-skills
    When I send the user-skill get request
    Then I validate the <status code> in the user-skill response
    And I validate the user-skill response Json schema from <getJsonSchema file>
    
    Examples:
    |status code|getJsonSchema file|
    |200        |"..\\LMSApiTest\\src\\test\\java\\resources\\jsonschema\\UserSkillMapGet.json"|
   

  @skillUserMapGetId
  Scenario Outline: Test the Get Request to get the user-skill with valid id
    Given I set the endpoint to get the user-skill with <userSkill Id>
    When I send the user-skill get request
    Then I validate the <status code> in the user-skill response
    And I validate in the response with <userSkill Id> <userId> <skillId>  <months of exp>
    And I validate the user-skill response Json schema from <getJsonSchema file>

    Examples:
      |userSkill Id |status code|skillId  |userId |months of exp |getJsonSchema file                    |
      | "US02"      |200        | 2		    |"U01"  |9             |"..\\LMSApiTest\\src\\test\\java\\resources\\jsonschema\\UserSkillMapGetId.json" |
     
      
@skillUserMap
  Scenario Outline: Test the Get Request to get the user-skill with invalid id
    Given I set the endpoint to get the user-skill with <user-skill Id>
    When I send the user-skill get request
    Then I validate the <status code> in the user-skill response
    And I validate in the user-skill response with <message>
    
    Examples:
      | user-skill Id    | status code | message		  |
      | "US2000"        	 |     404 		 | "Not Found"	|
     