@smoke @product
  Feature: Products List

    Scenario: Get all available products
      When the client requests all products
      Then the product request should be successful
      And the product list should not be empty