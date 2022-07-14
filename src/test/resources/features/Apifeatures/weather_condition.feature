
@api2
Feature:  send request and update condition endpoint and verify corresponding value.
Background:
Given I connected to Base URI
* Content-type is "application/json"
* Accept is "application/json"



Scenario Outline: update and verify check changed values.
When I send a Put request with the condition <condition> to the endpoint "<endpoint>"
And i send a get request
Then the status code is <status code>
And The condition is "<excondition>" if we send <condition>

Examples:
| condition | endpoint   | status code | excondition|
| 1         | condition |   200        | clear      |
| 2         | condition |   200        | windy      |
| 3         | condition |   200        | mist       |
| 4         | condition |   200        | drizzle    |
| 5         | condition |   200        | drizzle    |