#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@postSkill
Feature: Add skills using Skills API Post request

  @tag1
  Scenario Outline: Add a new skill
    Given I set the end point to post a new skill
    And I form the json request body with <skill name>
    When I send the post request
    Then I validate the <status code>
    And I validate <skill name> in the response
    
    Examples:
    |skill name   |status code|
    |"Cucumber"   |201        |

  @tag2
  Scenario Outline: Add a skill that already exists
   Given I set the end point to post a new skill
   And I form the json request body with <skill name>
   When I send the post request
   Then I validate the <status code>
   And I validate in the response with <message>

    Examples: 
      | skill name  | status code | message                                                          |
      | "Cucumber"  |     400     |  "Failed to create new Skill details as Skill already exists !!" |
      
