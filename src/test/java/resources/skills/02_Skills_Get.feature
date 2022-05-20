
@tag
Feature: Validate Skills Api Get Requests 

  @skills @skillsget
  Scenario Outline: Test the Get Request to list all skills
    Given I set the endpoint to get all skills
    When I send the get request
    Then I validate the <status code> 
    And I validate the Json response schema from <getJsonSchema file>
    
    Examples:
    |status code|getJsonSchema file|
    |200        |"C:\\Users\\Rakhi\\eclipse-workspace_new\\LMSApiTest\\src\\test\\java\\resources\\jsonschema\\SkillsGet.json"|
   

  @skills @skillsgetvalidId
  Scenario Outline: Test the Get Request to get the skill with valid id
    Given I set the endpoint to get the skill with <id>
    When I send the get request
    Then I validate the <status code>
    And I validate in the response with <id> and <skill>
    And I validate the Json response schema from <getJsonSchema file>

    Examples:
      |id |status code    |skill           |getJsonSchema file                                                         |
      | 4	|200         		| "Postman"		   |"..\\LMSApiTest\\src\\test\\java\\resources\\jsonschema\\SkillsGetId.json" |
      | 2 |200            |"Spring Boot"   |"..\\LMSApiTest\\src\\test\\java\\resources\\jsonschema\\SkillsGetId.json" |
      
@skills @skillsgetwithinvalidId
  Scenario Outline: Test the Get Request to get the skill with invalid id
    Given I set the endpoint to get the skill with <id>
    When I send the get request
    Then I validate the <status code>
    And I validate in the response with <message>
    
    Examples:
      | id    | status code | message		  |
      | 2000	|     404 		| "Not Found"	|
      | 1000	|     404			| "Not Found" |


