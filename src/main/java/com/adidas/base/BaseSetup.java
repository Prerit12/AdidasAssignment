package com.adidas.base;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.adidas.utility.WebdriverListeners;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.adidas.utility.FunctionsClass;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseSetup {
	private static final String SOME_ERROR_OCCURED = "Some error occured";
	private static final String BROWSER = "Browser";

	public WebDriver driver;
	public static ExtentReports extent;
	public static ThreadLocal<ExtentTest> parentTest = new ThreadLocal<ExtentTest>();
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

	FunctionsClass fc = new FunctionsClass();

	String log4jConfPath = Constants.LOG4J_PATH;
	static String className = new Throwable().getStackTrace()[0].getClassName();
	public static final Logger logger = Logger.getLogger(className);

	/** Function to launch browser based on Excel Configuration */
	public WebDriver launchApplicationBrowser() {
		PropertyConfigurator.configure(log4jConfPath);
		logger.info("Launching the Application");
		try {
			if (fc.ExcelReader("AppType", "Web").get(0).contains("Yes")) {
				if (fc.ExcelReader(BROWSER, "Chrome").get(0).contains("Yes")) {
					launchChrome();
				} else if (fc.ExcelReader(BROWSER, "Firefox").get(0).contains("Yes")) {
					launchFireFox();
				} else if (fc.ExcelReader(BROWSER, "IE").get(0).contains("Yes")) {
					launchIE();
				} else {
					launchChrome();
				}
			} else {
				logger.warn("Please select App Type from Excel");
			}
		} catch (Exception e) {
			logger.error(SOME_ERROR_OCCURED + e);
		}

		EventFiringWebDriver eventDriver = new EventFiringWebDriver(driver);
		WebdriverListeners eCapture = new WebdriverListeners();
		eventDriver.register(eCapture);
		return eventDriver;
	}

	/** Function to launch Chrome Browser */
	public WebDriver launchChrome() {
		try {
			logger.info("Chrome Browser is starting");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
		} catch (Exception e) {
			logger.error(SOME_ERROR_OCCURED + e);
		}
		return driver;
	}

	/** Function to launch Firefox Browser */
	public WebDriver launchFireFox() {
		try {
			logger.info("Firefox Browser is starting");
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		} catch (Exception e) {
			logger.error(SOME_ERROR_OCCURED + e);
		}
		return driver;
	}

	/** Function to launch IE browser */
	public WebDriver launchIE() {
		try {
			logger.info("IE Browser is starting");
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		} catch (Exception e) {
			logger.error(SOME_ERROR_OCCURED + e);
		}
		return driver;
	}
}
