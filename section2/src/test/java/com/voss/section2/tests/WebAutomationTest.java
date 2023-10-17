package com.voss.section2.tests;

import com.voss.section2.drivers.Driver;
import com.voss.section2.config.Config;
import com.voss.section2.tests.utilities.EmailSender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class WebAutomationTest {
    private static final Logger logger = Logger.getLogger(WebAutomationTest.class);

    public static void main(String[] args) {

        // Initializing log4j logging
        org.apache.log4j.PropertyConfigurator.configure("/Users/watsonmatunhire/IdeaProjects/AutomationTaskSuite/section2/src/main/java/resources/log4j.properties");


        Config config = new Config(args);
        String baseUrl = config.getBaseUrl();
        String driverPath = config.getDriverPath();
        String browserName = config.getBrowserName();

        System.out.println("Base URL: " + baseUrl);
        System.out.println("Driver Path: " + driverPath);
        System.out.println("Browser Name: " + browserName);

        // Creating new instance of Driver class
        Driver driver = new Driver(driverPath, browserName);
        // Obtaining WebDriver instance
        WebDriver webDriver = driver.getWebDriver();

        try {
            // Navigating to the URL
            webDriver.get(baseUrl);

            // Waiting for the page to load
            WebDriverWait wait = new WebDriverWait(webDriver, 10);
            wait.until(ExpectedConditions.titleContains("Automation Practice - Ultimate QA"));

            // Get and print the page title
            String pageTitle = webDriver.getTitle();
            System.out.println("Page Title: " + pageTitle);

            // Verify the page title
            String expectedTitle = "Automation Practice - Ultimate QA";
            Assert.assertEquals(pageTitle, expectedTitle, "Page title doesn't match the expected title");

            // Capturing screenshot
            driver.takeScreenshot("screenshots/pageTitleScreenshots", "pageTitleScreenshot.png");

            // Finding "Login automation" link by its text
            WebElement loginLink = webDriver.findElement(By.linkText("Login automation"));

            // Clicking link to access sign-in page
            loginLink.click();

            // Finding and fill in the email and password fields using JavaScript
            //wait = new WebDriverWait(webDriver, 10);
            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user[email]")));

            JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
            jsExecutor.executeScript("arguments[0].value = 'testerwatty0@gmail.com';", emailField);

            // Finding password field and filling in using JavaScript
            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user[password]")));
            jsExecutor.executeScript("arguments[0].value = 'Watty@123';", passwordField);

            // send user email notification for captcha manual intervention
            EmailSender.sendEmailNotification();

            // user alert dialogue - message on the web page
            String UserAlert = "alert('Captcha required for the sign-in process, requiring manual intervention from the user. An email has been sent to recipient@gmail.com.');";
            ((JavascriptExecutor) webDriver).executeScript(UserAlert);

            // Sleep for 1 minute / adjust as needed!
            Thread.sleep(100000);

            // Finding "Sign in" button
            //WebElement signInButton = webDriver.findElement(By.cssSelector("button.button-primary[data-callback='onSubmit']"));

            // Clicking button using JavaScript
            //JavascriptExecutor js = (JavascriptExecutor) webDriver;
            //js.executeScript("arguments[0].dispatchEvent(new Event('click'));", signInButton);

            // Checking if the "Verify" button is no longer visible (indicating successful login)
            /*WebElement verifyButton = webDriver.findElement(By.id("recaptcha-verify-button"));

            if (!verifyButton.isDisplayed()) {
                // Verifying button is not visible indicating successful login
                System.out.println("Login successful.");
                // Continue with your automation steps
            } else {
                // Verifying button is still visible, indicating captcha is not yet solved
                System.out.println("Captcha is not yet solved.");

            }*/

            // Finding dropdown menu by its CSS selector
            WebElement dropdownMenu = webDriver.findElement(By.cssSelector(".fa.fa-caret-down"));

            // Clicking  dropdown menu to open it
            dropdownMenu.click();

            // Waiting for "Sign Out" link to be clickable in dropdown menu
            WebElement signOutLink = new WebDriverWait(webDriver, 10)
                    .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".dropdown__menu-item a[href='/users/sign_out']")));

            // Clicking "Sign Out" link to sign out
            signOutLink.click();

            logger.info("Dropdown menu clicked.");
            logger.info("Sign Out link clicked.");

            // Navigating to base URL
            webDriver.get(baseUrl);
            logger.info("Navigating to the base URL: " + baseUrl);

            // Disable logging to reduce console output
            System.setProperty("webdriver.chrome.silentOutput", "true");
            Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);

            // Set implicit wait time
            webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            // Set up WebDriverWait
            wait = new WebDriverWait(webDriver, 10);

            // Wait for the page to load (you can use a wait here)
            // wait.until(ExpectedConditions.titleContains("Filling Out Forms"));

            // Loop to handle two forms
            for (int i = 0; i < 2; i++) {
                String formUrl = "https://ultimateqa.com/filling-out-forms/";

                // Navigate to the "Fill out forms" page directly
                webDriver.get(formUrl);

                // Wait for the page to load (you can use a wait here)
                wait.until(ExpectedConditions.titleContains("Filling Out Forms"));

                // Find the name input field
                WebElement nameField = webDriver.findElement(By.id("et_pb_contact_name_" + i));

                // Find the message textarea
                WebElement messageField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("et_pb_contact_message_" + i)));

                // Fill in the name
                nameField.sendKeys("Betty Tester");

                if (i == 1) {
                    // Wait for the first form's confirmation message to disappear
                    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".et-pb-contact-message")));
                }

                // Fill in the message
                String messageText = "Hello there, I'm Betty Tester for form " + (i + 1);
                messageField.sendKeys(messageText);

                // Log name and message entered
                System.out.println("Name entered: Betty Tester");
                System.out.println("Message entered: " + messageText);

                if (i == 1) {
                    // Finding the captcha question element
                    WebElement captchaQuestion = webDriver.findElement(By.cssSelector(".et_pb_contact_captcha_question"));

                    // Getting text of the captcha question
                    String captchaText = captchaQuestion.getText();

                    // Extracting numbers from the question
                    String[] numbers = captchaText.split("\\s+"); // Split by spaces

                    // Extracting individual numbers
                    int firstDigit = Integer.parseInt(numbers[0]);
                    int secondDigit = Integer.parseInt(numbers[2]);

                    // Calculating the answer
                    int answer = firstDigit + secondDigit;

                    // Logging calculated answer
                    System.out.println("Calculated answer for iteration " + (i + 1) + ": " + answer);

                    // Finding captcha answer input field
                    WebElement captchaAnswerField = webDriver.findElement(By.name("et_pb_contact_captcha_" + i));

                    // Clear any existing value in the field
                    captchaAnswerField.clear();

                    // Enter calculated answer
                    captchaAnswerField.sendKeys(Integer.toString(answer));
                }

                // Finding submit button and click it
                WebElement submitButton = webDriver.findElement(By.name("et_builder_submit_button"));
                submitButton.click();

                // Waiting for the confirmation message/overlay to appear
                WebElement confirmationMessage = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".et-pb-contact-message")));

                // Closing confirmation message/overlay
                confirmationMessage.click();
                Thread.sleep(3000);
        }
            // Go back to main page
            webDriver.get(baseUrl);

            // Navigating to "Fake Pricing Page"
            navigateToFakePricingPage(webDriver);

            // Purchasing Basic package
            purchaseBasicPackage(webDriver);

        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage());
        } finally {
            // Closing browser regardless of outcome
            driver.close();
        }
    }

    @Test
    public static void navigateToFakePricingPage(WebDriver webDriver) {
        // Finding "Fake Pricing Page" link by its link text
        WebElement fakePricingPageLink = webDriver.findElement(By.linkText("Fake Pricing Page"));

        logger.info("Clicking the 'Fake Pricing Page' link.");
        // Clicking link to access pricing page
        fakePricingPageLink.click();
    }

    @Test
    public static void purchaseBasicPackage(WebDriver webDriver) throws InterruptedException {
        // Waiting for the page to load
        WebDriverWait wait = new WebDriverWait(webDriver, 10);

        // Waiting for page to load with  correct title
        wait.until(ExpectedConditions.titleContains("Fake pricing page - Ultimate QA"));

        // Finding the "Purchase" button by its link text / css selector
        //WebElement purchaseButton = webDriver.findElement(By.linkText("Purchase"));
        WebElement purchaseButton = webDriver.findElement(By.cssSelector("a.et_pb_button.et_pb_pricing_table_button"));

        // Scrolling to the purchase button
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", purchaseButton);

        logger.info("Clicking the 'Purchase' button to purchase the Basic package.");
        // Clicking "Purchase" button
       purchaseButton.click();
        Thread.sleep(3000);
    }
}
