# Test Plan for Section 2 - Automated End-to-End Testing

**Project**: AutomationTaskSuite - Section 2

**Test Environment**:
- **Testing Framework**: TestNG
- **Programming Language**: Java
- **Browsers**: Firefox, Chrome
- **Operating Systems**: Windows, Linux, MacOS

## Test Cases

### Test Case 1: Verify Page Title

**Description**: Verify the page title when browsing to [https://www.ultimateqa.com/automation/](https://www.ultimateqa.com/automation/)

**Expected Outcome**: The page title should match the expected title.

### Test Case 2: Take Screenshot of the Page

**Description**: Take a screenshot of the page at [https://www.ultimateqa.com/automation/](https://www.ultimateqa.com/automation/)

**Expected Outcome**: A screenshot of the page is captured.

### Test Case 3: Maximize Browser Window

**Description**: Maximize the browser window when browsing to [https://www.ultimateqa.com/automation/](https://www.ultimateqa.com/automation/)

**Expected Outcome**: The browser window is maximized.

### Test Case 4: Login to the Page with Captcha Handling

**Description**: Log in to the page via the "Login automation" link. If a captcha is presented, use the Java Mailer class to send an email notification for manual intervention. 

**Expected Outcome**: Successful login, and in case of a captcha, an email notification is sent to request manual intervention. 

### Test Case 5: Logout from the Page

**Description**: Log out from the page.

**Expected Outcome**: A successful logout should be performed.

### Test Case 6: Fill out Forms and Submit

**Description**: Browse to the "Fill out forms" page, complete all forms, and submit.

**Expected Outcome**: Forms should be filled out and submitted successfully.

### Test Case 7: Purchase Basic Package from Fake Pricing Page

**Description**: Browse to the "Fake Pricing Page" and purchase the Basic package.

**Expected Outcome**: A successful purchase of the Basic package should be performed.

**Reporting**: Used log4j for detailed reporting. It captures and logs all the important information during the application's operation.

## Test Execution Instructions

1. Ensure you have set up the required environment, including installing the testing framework and dependencies.

2. Open a terminal/command prompt and navigate to the project directory (section2).

3. To run the test suite, you can use either Maven or IntelliJ IDEA.
   **Using IntelliJ IDEA**

To run the test suite in IntelliJ IDEA, follow these steps:

1. Open IntelliJ IDEA and open the project.

2. In the Project Explorer, navigate to the `WebAutomationTest` class located in the following directory: /Users/watsonmatunhire/IdeaProjects/AutomationTaskSuite/section2/src/test/java/com/voss/section2/tests/WebAutomationTest.java


3. Right-click on the `WebAutomationTest` class.

4. Select "Run" to execute the test suite.

This will execute the automated tests in IntelliJ IDEA, and you'll see the test results in the IDE.

PS:Functionality Partially Implemented: Some features may not work as intended. It is advisable to utilize your integrated development environment (IDE) and right-click on the `WebAutomationTest` class. 
```shell
mvn test
```