package com.voss.section2.drivers;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.JavascriptExecutor;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Driver {
    private String driverPath;
    private String browserName;
    private WebDriver webDriver;

    public Driver(String driverPath, String browserName) {
        this.driverPath = driverPath;
        this.browserName = browserName;
        this.webDriver = createDriver();
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    private WebDriver createDriver() {
        if (browserName.equalsIgnoreCase("chrome")) {
            return createChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            return createFirefoxDriver();
        } else {
            throw new UnsupportedOperationException("Unsupported browser: " + browserName);
        }
    }

    public void takeScreenshot(String folderPath, String fileName) {
        if (webDriver instanceof TakesScreenshot) {
            File screenshotFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
            try {
                File folder = new File(folderPath);
                if (!folder.exists()) {
                    folder.mkdirs();
                }

                File screenshot = new File(folder, fileName);
                FileHandler.copy(screenshotFile, screenshot);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private WebDriver createChromeDriver() {
        System.setProperty("webdriver.chrome.driver", driverPath);

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-extensions");
        options.addArguments("user-agent=YOUR_USER_AGENT_STRING");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");

        //comment to enable headless mode in chrome
        //options.addArguments("--headless");

        ChromeDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

    private WebDriver createFirefoxDriver() {
        System.setProperty("webdriver.gecko.driver", driverPath);

        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-extensions");
        options.addArguments("--no-sandbox");

        //comment to enable headless mode in firefox
        options.addArguments("--headless");


        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        options.merge(capabilities);

        FirefoxDriver driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

    public void close() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
public JavascriptExecutor getJavascriptExecutor() {
        if (webDriver instanceof JavascriptExecutor) {
            return (JavascriptExecutor) webDriver;
        } else {
            throw new UnsupportedOperationException("The WebDriver does not support JavaScript execution.");
        }
    }
}
