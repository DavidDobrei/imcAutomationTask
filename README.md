# imcAutomationTask
# Selenium JUnit Automation Framework

This project demonstrates a test automation framework using **Java**, **JUnit**, **Selenium WebDriver**, and **Maven**, structured to follow the Page Object Model (POM) pattern. It supports running tests in multiple browsers by reading configuration from a `config.properties` file.

---

## 🚀 Technologies Used

- Java 17+
- Maven
- Selenium WebDriver
- JUnit 4
- ChromeDriver / GeckoDriver
- Page Object Model Design Pattern

## ⚙️ Setup Instructions

### ✅ Prerequisites

- Java 17+ installed and `JAVA_HOME` configured
- Maven installed and available in your system `PATH`
- Chrome and/or Firefox browsers installed
- ChromeDriver and/or GeckoDriver managed by dependancy in opm file
  <dependency>
            <groupId>io.github.bonigarcia</groupId>
            <artifactId>webdrivermanager</artifactId>
            <version>5.9.3</version>
        </dependency>
  - set via `System.setProperty()` in `DriverFactory`


---

## 🧪 How to Run Tests

### ▶️ Run from IntelliJ IDEA (Preferred)

1. Open the project in IntelliJ IDEA.
2. Wait for Maven to index dependencies.
3. Navigate to any test class (e.g., `ConcludeCourseByProgressTest.java`).
4. Right-click on the test method or class → **Run**.

> Tests use the browser specified in `config.properties` (default is Chrome).

---

### ⚙️ Change Browser

Edit this file:

```properties
# src/test/resources/config.properties
browser=firefox


