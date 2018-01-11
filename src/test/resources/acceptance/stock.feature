Feature: User can successfully update stock and get stock

  Scenario: User updates a stock
    When the user updates a stock
     And the stock is successfully created
    Then the user receives status code of 201

  Scenario: User gets stock summary
   Given a stock exists
    When the user gets the stock
    Then the user receives status code of 200
     And the retrieved stock is correct
     
