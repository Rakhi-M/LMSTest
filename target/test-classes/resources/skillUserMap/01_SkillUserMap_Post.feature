Feature: Post Request for User Skill Map Api
Scenario Outline: Test the Post Request to post a new  user-skill
    Given I set the endpoint to post the user-skill
    And I form the Json request body with <skill Id> <userId> <months of exp>
    When I send the user-skill post request
    Then I validate the <status code> in the user-skill response
    

    Examples:
      |skill Id|userId |months of exp|status code|
      |2       |"U16"  |10           |201        |
      
      
      