@ui
Feature: searching field and title verifying

Scenario: verifying main pages  text title
When the main pages title should be "OpenWeather"




Scenario: Checking input value in searching field and verifying
Given the user select Sydney,AU from the list
When the selected city title should be like "Sydney, AU"
And the date and time need to be verified.



