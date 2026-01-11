
# Selenium WD E2E Framework

Lightweight Selenium + TestNG automation framework (Java, Maven) used for testing web application, support modular test design, data-driven testing and cross-browser testing.

## Overview

- Language: Java
- Build: Maven
- Test framework: TestNG
- Structure: Page Object Model (POM) with shared commons utilities

## Project layout

- `src/main/java` — framework utilities and helpers
- `src/main/resources` — configuration files (`config.properties`, `log4j.properties`, `drivers/`)
- `src/test/java` — test code
	- `io.platform.project.pageObjects` — page object classes
	- `io.platform.project.homePageTests` — test classes
	- `io.platform.project.commons` — shared utilities (`Common.java`, `ReadPropertyFile.java`, `WebDriverManager.java`)
- `logs/` — runtime logs (created at test execution)
- `screenshot/` — screenshots saved on failures
- `target/` — Maven build output and test reports

## Prerequisites

- Java JDK 8 or newer installed and `JAVA_HOME` set
- Maven 3.6+ installed and on `PATH`
- A supported browser (e.g., Chrome) and matching WebDriver binary

## Configuration

1. Update test configuration in `src/main/resources/config.properties` (browser, base URL, driver paths).
2. Driver binaries can be placed under `src/main/resources/drivers/` or configured via `config.properties`.
3. Logging settings are in `src/main/resources/log4j.properties`.

Classes that read or manage configuration:

- `io.platform.project.commons.ReadPropertyFile` — reads `config.properties`
- `io.platform.project.commons.WebDriverManager` — creates and manages WebDriver instances

## Running tests

- Run the full test suite:

```bash
mvn clean test
```

- Run a specific TestNG test class (example):

```bash
mvn -Dtest=io.platform.project.homePageTests.HomePageTest test
```

- When run, TestNG and Surefire generate reports in `test-output/` and `target/surefire-reports/`.

## Reports and outputs

- HTML TestNG report: `test-output/index.html`
- Surefire reports: `target/surefire-reports/`
- Screenshots: `screenshot/`
- Logs: `logs/`

## Notes & Tips

- To change browser or driver path, edit `src/main/resources/config.properties` or update `WebDriverManager` as needed.
- Use the page objects in `src/test/java/io/platform/project/pageObjects` when adding new tests.
- Keep tests independent and avoid ordering dependencies.

## Contributing

1. Create a feature branch.
2. Add tests and ensure they pass locally with `mvn test`.



