@smoke @e2e @account
  Feature: Account management

    Scenario: Manage account through its complete lifecycle
      Given a unique account test data is prepared

      When the client creates the account
      Then the account should be created successfully

      When the client login using created account credentials
      Then the client should login successfully

      When the client requests account details
      Then the account details should be correct

      When the client updates account information
      Then the account should be updated successfully

      When the client requests account details
      Then the account details should contain updated information

      When the client deletes account
      Then the account should be deleted successfully

      When the client login using deleted account credentials
      Then the login request should be rejected