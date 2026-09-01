# E-commerce API & UI Automation Framework

[![Automation Tests](https://github.com/pqhuy1102/ecommerce-rest-assured/actions/workflows/automation-tests.yml/badge.svg)](https://github.com/pqhuy1102/ecommerce-rest-assured/actions/workflows/automation-tests.yml)

A Java-based test automation framework for the [Practice Hoctest E-commerce](https://practice-ecommerce.hoctest.com) application, covering REST API and Selenium UI testing with BDD, reporting, and CI integration.

## Key Features

* API automation with REST Assured
* UI automation with Selenium WebDriver and Page Object Model
* BDD scenarios using Cucumber and TestNG
* Request/response DTOs with Jackson serialization
* Scenario-scoped dependency injection using PicoContainer
* Dynamic test data and automatic account cleanup
* Allure reports with API attachments and UI failure screenshots
* Headless execution and report artifacts in GitHub Actions

## Test Coverage

| Area            | Scenarios                                                   |
| --------------- | ----------------------------------------------------------- |
| Product API     | Retrieve and search products                                |
| Negative API    | Missing and empty search parameters                         |
| Account API E2E | Create, login, retrieve, update, delete and verify deletion |
| UI              | Homepage validation and account registration/login workflow |

## Tech Stack

`Java 21` · `REST Assured` · `Selenium` · `Cucumber` · `TestNG` · `Jackson` · `PicoContainer` · `Allure` · `Maven` · `GitHub Actions`

## Project Structure

```text
src/test/
├── java/
│   ├── api/        # Clients, DTOs, contexts, hooks and step definitions
│   └── ui/         # Drivers, page objects, hooks and step definitions
└── resources/
    ├── features/   # API and UI Cucumber scenarios
    └── config/     # Environment configuration
```

## Running Tests

Prerequisites: Java 21, Maven and Google Chrome.

```bash
git clone https://github.com/pqhuy1102/ecommerce-rest-assured.git
cd ecommerce-rest-assured
mvn clean test
```

Run the smoke suite:

```bash
mvn clean test "-Dcucumber.filter.tags=@smoke"
```

Run UI tests in headless mode:

```bash
mvn clean test "-Dcucumber.filter.tags=@ui" "-Dui.headless=true"
```

## Allure Report

```bash
mvn allure:serve
```

Raw results are generated in `target/allure-results`.

## Continuous Integration

GitHub Actions automatically runs the API and headless UI test suite on pushes and pull requests to the main branches. Allure, Cucumber and Surefire reports are retained as downloadable workflow artifacts.
