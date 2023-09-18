#language: en
#project: Bancatech P2P
#client: Global Bank
#made by: Sofka
#email: info@sofka.com.co
#phone: (4) 366 83 25
#website: www.sofka.com.co
Feature: It is required to expose an API that receives the employee's information and store it
  It is required to expose an API that receives the employee's name, salary, and the age

  @EN001-HP
  Scenario Outline: The server do a petition post to create an employee
    When the user calls the microservice to register an employee
      | name   | salary   | age   |
      | <name> | <salary> | <age> |
    Then the response data is validated
      | name   | salary   | age   | codeResponse   | status   | message   |
      | <name> | <salary> | <age> | <codeResponse> | <status> | <message> |

    Examples:
      | name | salary | age | codeResponse | status  | message                              |
      | juan | 1000   | 25  | 200          | success | Successfully! Record has been added. |



