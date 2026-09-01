@ui @account @authentication
  Feature: Register and login

    @smoke
    Scenario: Client can register and login with new account
      Given a unique account test data is prepared

      When the client registers with prepared account
      Then account-created confirmation should be displayed

      When the client login with created-account
      Then the client should login successfully on store UI
