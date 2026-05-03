Feature: validate the user login 

@ErrorValidation
Scenario Outline: Error Validation
      Given I landed on E commerce page
       Given the user logged in <username> with <password>
       Then "Incorrect email or password." validate the error
      
      Examples:
       | username| password|
       |sravank@gmail.com | Kumar@50| 
       | kumarv@gamail.com | Kumar@50| 