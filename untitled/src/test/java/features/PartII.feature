Feature: Part II

  Scenario: Register new user
    Given user register new account with valid data email "use7r78p219@example.com", password "password1234", firstName "John", lastName "Doe", username "us7e87rnampe896" and dateOfBirth "01/01/1990"
    When user registers new account
    Then user should receive a success response "Thanks for signing up."


  Scenario: Sign in with newly created user
    Given registered user is logged in with username "us7ername896" and password "password1234"
    When user logins
    Then user should receive success response with session token

  Scenario: Add new post
    Given registered user is logged in with username "us7ername896" and password "password1234"
    When user adds new post with text "This is new post"
    Then user should receive a success response

  Scenario: Add new comment on the post
    Given registered user is logged in with username "us7ername896" and password "password1234"
    And user adds new post with text "This is new post"
    When user adds new comment "This is my first comment"
    Then user should receive a success response with comment and user data


  Scenario: Get comments from the post
    Given registered user is logged in with username "us7ername896" and password "password1234"
    And user adds new post with text "This is new post"
    And user adds new comment "This is my first comment"
    When user gets comments from the post
    Then user should receive a success response with previously added comment




