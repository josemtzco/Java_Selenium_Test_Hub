# Selenium Testing Hub

A lightweight, opinionated framework template for Java-based Selenium automation.

Across all the corporate jobs I have work, I see that Java Selenium is the default choice for two reasons
- Long term support.
- Most tech worker in corporate and code factories are familiar with Java and Selenium.
- Free

The downside of using this tech stack and real world scenarios I have seen are 
- Normally one person set the framework long time back and then it gets pass around even if it not the most optimal solution.
- If you make this a jar library and share across teams a dependency, every time you need an specific method for your project it will be shared across all teams.

Personally I have to choose I would use another framework that can manage the web drivers, page objects commons assertions in a standarized way.

---

## 🎯 Motivation

As a Software Engineer in Test, I'm recollecting proof of the work I have done in my work to built self confidence and show my experience as well.

---

## 🗂 What It Covers

1. **Thread-Safe WebDriver Factory**  
   - Uses `ThreadLocal` to give each parallel test its own `WebDriver` instance  
   - Configurable for Chrome, Firefox, or any Selenium-compatible browser  

2. **Base Page & Page Object Pattern**  
   - A `BasePage` class with common utilities (navigation, waits, screenshots)  
   - Sample page objects that demonstrate best practices for locator organization  

3. **Assertion Utilities**  
   - Wrapper methods around your chosen assertion library (TestNG, JUnit, AssertJ, etc.)  
   - Consistent error messaging and optional screenshot capture on failure  

4. **Parallel Execution Support**  
   - Pre-configured for TestNG (or JUnit) parallel test runs  
   - Example `testng.xml` demonstrating class- and method-level threading
   - Also with maven surfire plugin in case.
 
6. **Docker compose file**  
   - Creates a Selenium Hub
   - Creates multiples nodes of Chrome and Firefox

---


Created by Jose Martinez  
SDET | Automation Advocate  
Feel free to open an issue or ping me for feedback!  
