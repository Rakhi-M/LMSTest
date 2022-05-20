Feature: Modify an existing user-skill /Put Request  

  @tag1
  Scenario Outline: Modify a skill name that already exists
    Given I set the user-skill end point to put with <user-skill id>
    And I form the Json request body with <skill Id> <userId> <months of exp>
    When I send the user-skill put request
    Then I validate the <status code> in the user-skill response
    
    
    Examples:
      |user-skill id|skill Id|userId |months of exp|status code|
      |"US04"      |3       |"U02"  |24            |201        |

  @tag2
  Scenario Outline: Modify a skill name that does not exists
   Given I set the user-skill end point to put with <user-skill id>
    And I form the Json request body with <skill Id> <userId> <months of exp>
    When I send the user-skill put request 
    Then I validate the <status code> in the user-skill response

    Examples: 
     |user-skill id |skill Id|userId |months of exp|status code|
     |"US220"       |2       |"U16" |12            |404        |
