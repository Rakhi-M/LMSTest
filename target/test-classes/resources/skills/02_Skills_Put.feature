
@putSkill
Feature: Modify an existing skill
  

  @tag1
  Scenario Outline: Modify a skill name that already exists
    Given I set the end point to put with <skill id>
    And I form the json request body with <skill name>
    When I send the put request
    Then I validate the <status code>
    And I validate <skill name> in the response
    
    Examples:
    |skill id|skill name       |status code|
    |     259|"MongoDB"        |201        |

  @tag2
  Scenario Outline: Modify a skill name that does not exists
   Given I set the end point to put with <skill id>
   And I form the json request body with <skill name>
   When I send the put request
   Then I validate the <status code>
   And I validate in the response with <message>

    Examples: 
      |skill id| skill name           | status code | message                                                          |
      |    200 | "Cucumber BDD"       |     404     |  "Not Found" |