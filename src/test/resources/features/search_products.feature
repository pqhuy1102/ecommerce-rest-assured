@product
  Feature: Search products

    @smoke
    Scenario: Search products using a valid keyword
      When the client searches for products by using keyword "jeans"
      Then the product search request should be successful
      And the returned products should match the search keyword

    @negative
    Scenario: Search products using null keyword
      When the client searches for products without a keyword
      Then the product search request should be rejected

    @negative
    Scenario: Search products using empty keyword
      When the client searches for products with empty keyword
      Then the product search request should be error