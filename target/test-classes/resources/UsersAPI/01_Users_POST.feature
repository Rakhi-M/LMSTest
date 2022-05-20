Feature: Validate POST method for Users API

 
	
@PostNew
	Scenario Outline: Verify the POST request to add a new user
   Given User set the endpoint to get all users      
    And User forms the json request body with <name> <phone_number> <location> <time_zone> <visa_status> <linkedin_url>
    When User sends the post request 
    Then User validates the status Code <statusCode> and status line <statusLine>
    And User validates the Json response schema from <getJsonSchemafile>
		And User validates response body fields <name> <phone_number> <location> <time_zone> <visa_status> <linkedin_url> using user Id  
		
		Examples:
		| name 		       | phone_number   | location       | time_zone 	   |visa_status|linkedin_url                  |statusCode|statusLine     |getJsonSchemafile                                                              |
		|"Test,PostUser" |9193246564      |"Test locationn"|"EST"          |"H4"       |"www.linkedin.com/in/testuser"|201       |"HTTP/1.1 201 "|"..\\LMSApiTest\\src\\test\\java\\resources\\jsonschema\\Schema_UsersPost.json"|
		
		
	
		Scenario Outline: Verify the POST request to add a new user that already exists
    Given User set the endpoint to get all users      
    And User forms the json request body with <name> <phone_number> <location> <time_zone> <visa_status> <linkedin_url>
    When User sends the post request
    Then User validates the status Code <statusCode> and status line <statusLine>
		And User validates message <message> in the response body
		
		Examples:
		| name 		    | phone_number   | location | time_zone 	   |visa_status|linkedin_url|statusCode|statusLine|message|
		|"Test,PostUser" |9198144564|"Test locationn"|"EST"|"H4"|"www.linkedin.com/in/testuser"|400|"HTTP/1.1 400 "|"Failed to create new User details as phone number already exists"|
		
		