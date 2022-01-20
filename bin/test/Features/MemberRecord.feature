@this
Feature: Member Record Tracker
  Scenario: Check the no. of member change for the anime
    Given Selenium setup is done and driver is started with given anime name
#      |name|Steins;Gate|
      |name|Shingeki no Kyojin: The Final Season|
    And Local Data is stored in variable
    When Top Anime Page is loaded and windows is maximized
    And Anime name is searched and clicked
    Then Search for the members tag and store the count and display the difference
    And Store the new count in the file
#    Then Wait for the page to show the accept cookies option and accept the cookies
#    And Refresh the page and check if cookie acceptance is shown again or not
