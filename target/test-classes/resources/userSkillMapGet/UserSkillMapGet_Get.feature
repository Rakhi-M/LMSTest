@tag
Feature: Validate UserSkillMapGet Api Requests 

  @skillUserMapGet
  Scenario Outline: Test the Get Request to list all user-skill-maps
    Given I set the endpoint to get all user-skill-maps
    When I send the user-skill-map get request
    Then I validate the <status code> in the user-skill-map response
    And I validate the user-skill-map response Json schema from <getJsonSchema file>
    
    Examples:
    |status code|getJsonSchema file|
    |200        |"..\\LMSApiTest\\src\\test\\java\\resources\\jsonschema\\UserSkillMapGetApi.json"|
   

  @skillUserMapGetId
  Scenario Outline: Test the Get Request to get the user-skill-map with valid user-id
    Given I set the endpoint to get the user-skill-map with <user Id>
    When I send the user-skill-map get request
    Then I validate the <status code> in the user-skill-map response
    And I validate in the response with <user Id> <firstName>  <lastName>
    And I validate the user-skill-map response Json schema from <getJsonSchema file>

    Examples:
      |user Id |status code|firstName  |lastName |getJsonSchema file                                                                    |
      | "U02"  |200        | "jan"     |"sama"  |"..\\LMSApiTest\\src\\test\\java\\resources\\jsonschema\\UserSkillMapGet_userId.json" |
     
      
@skillUserMapGetId
  Scenario Outline: Test the Get Request to get the user-skill with invalid user id
    Given I set the endpoint to get the user-skill-map with <user Id>
    When I send the user-skill-map get request
    Then I validate the <status code> in the user-skill-map response
    And I validate in the user-skill-map response with <message>
    
    Examples:
      | user Id    | status code | message		  |
      | "U200"     |     404 		 | "Not Found"	|
      
 @skillUserMapGetId
  Scenario Outline: Test the Get Request to get the user-skill-map with valid skill-id
    Given I set the endpoint to get the user-skill-map with skill id <skill Id>
    When I send the user-skill-map get request
    Then I validate the <status code> in the user-skill-map response
    And I validate the user-skill-map response Json schema from <getJsonSchema file>

    Examples:
      |skill Id |status code|getJsonSchema file                                                                     |
      | 1       |200        |"..\\LMSApiTest\\src\\test\\java\\resources\\jsonschema\\UserSkillMapGet_skillId.json" |
     
      
@skillUserMapGetId
  Scenario Outline: Test the Get Request to get the user-skill with invalid skill id
    Given I set the endpoint to get the user-skill-map with skill id <skill Id>
    When I send the user-skill-map get request
    Then I validate the <status code> in the user-skill-map response
    And I validate in the user-skill-map response with <message>
    
    Examples:
      | skill Id    | status code | message		  |
      | 800         |     404 		 | "Not Found"	|

     