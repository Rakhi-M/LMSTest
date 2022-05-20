Feature: Verify PUT method for Users API

  
   
		Scenario Outline: Verify the PUT method by modifying user name that already exists
    Given User set the api endpoint with user id <user_id>      
    And User forms the json request body with <putname> <phone_number> <putlocation> <time_zone> <visa_status> <linkedin_url>
    When User sends the put request
    Then User validates the status Code <statusCode>    
		And User validates response body fields <putname> <phone_number> <putlocation> <time_zone> <visa_status> <linkedin_url> for user <user_id>
		And User validates the Json response schema from <getJsonSchemafile>
		Examples:
		| user_id 		         |putname          |phone_number|putlocation        | time_zone |visa_status|linkedin_url                  |statusCode|getJsonSchemafile|
		|"UsersPropertiesFile" |"Test,UserPUTNew"|9998124444|"Test Put Location"|"EST"      |"H4"       |"www.linkedin.com/in/testuser"|201       |"..\\LMSApiTest\\src\\test\\java\\resources\\jsonschema\\Schema_UsersPut.json"|
		
				
		Scenario Outline: Verify the PUT method by modifying user name that does not exist
    Given User set the api endpoint with user id <user_id>     
    And User forms the json request body with <name> <phone_number> <location> <time_zone> <visa_status> <linkedin_url>
    When User sends the put request
    Then User validates the status Code <statusCode>		 
		And User validates message <message> in the response body
		
		Examples:
		|user_id| name 		    | phone_number   | location | time_zone 	   |visa_status|linkedin_url|statusCode|putname|message|
		|"U9090"|"Test,UserPUT" |9998124444|"Test location"|"EST"|"H4"|"www.linkedin.com/in/testuser"|404|"Test,UserPUTNotExist"|"Not Found"|
		
		
		