Feature: Verify GET method for Users API

  

  Scenario Outline: Verify the GET Request to list all the users
    Given User set the endpoint to get all users
    When User sends the get request
    Then User validates the status Code <statusCode>
		And User validates the Json response schema from <getJsonSchemafile>
		
    Examples: 
      | statusCode | getJsonSchemafile|
      |200|"..\\LMSApiTest\\src\\test\\java\\resources\\jsonschema\\Schema_UsersGetAll.json"|


  Scenario Outline: Verify the GET Request to get the user with valid id
    Given User set the api endpoint with user id <user_id>
    When User sends the get request
    Then User validates the status Code <statusCode>
    And User validates response body fields <name> <phone_number> <location> <time_zone> <visa_status> <linkedin_url> for user <user_id>    
    And User validates the Json response schema from <getJsonSchemafile>
    Examples:       
		|user_id                | name 		       | phone_number   | location       | time_zone 	   |visa_status|linkedin_url                  |statusCode|getJsonSchemafile|
		|	"UsersPropertiesFile"	|"Test,PostUser" | 9198144564     |"Test locationn"|"EST"          |"H4"       |"www.linkedin.com/in/testuser"|200       |"..\\LMSApiTest\\src\\test\\java\\resources\\jsonschema\\Schema_UsersGetId.json"|


  Scenario Outline: Verify the GET Request to get the user with invalid id
    Given User set the api endpoint with user id <user_id>
    When User sends the get request
    Then User validates the status Code <statusCode> and status line <statusLine>
    And User validates message <message> in the response body     

    Examples: 
      | user_id  | statusCode |statusLine|message|
      |"U0000"|404|"HTTP/1.1 404 "|"Not Found"|