@api
Feature: send request and update temp endpoint and verify corresponding value.

  Background:
    Given I connected to Base URI
    * Content-type is "application/json"
    * Accept is "application/json"

  Scenario Outline: update and verify check changed values.
    When I send a Put request with the temperature <temperature> to the endpoint "<endpoint>"
    And i send a get request
    Then the status code is <status code>
    And The weather is "<weather>"

    Examples:
      | temperature | endpoint | status code | weather |
      | 100         | temp     | 200         | hot     |
      | 30           | temp    | 200         |freezing |
      | 43          | temp     | 200         | cold    |
      | 65          | temp     | 200         | mild    |





#  {
#  "city"        : "Vienna",
#  "condition"   : "clear",
#  "icon"        : "clear.PNG",
#  "conditionId" : 1,
#  "description" : "The weather is mild",
#  "weather": {
#  "tempInFahrenheit" : 60,
#  "tempInCelsius"    : 15
#  }
#  }

