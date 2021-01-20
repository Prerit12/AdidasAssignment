package com.adidas.base;

public class Constants {

	/** Log4j path */
	public static final String LOG4J_PATH = "./src/main/resources/log4j.properties";

	/** Directories path for Screenshots */
	public static final String PASS_TEST_CASE_FOLDER = "./Current test results/Reports/screenshots/PassedTestCaseFolder/";
	public static final String FAILED_TEST_CASE_FOLDER = "./Current test results/Reports/screenshots/FailedTestCaseFolder/";
	public static final String PASS_TEST_CASE_EXTENSION = "_Pass.png";
	public static final String FAILED_TEST_CASE_EXTENSION = "_Fail.png";

	/** Directories path for ExtentReports */
	public static final String EXTENT_REPORT_PATH = "./Current test results/Reports/ExtentReport.html";
	public static final String EXTENT_REPORT_CONFIG = "./src/main/resources/extent-config.xml";

	/** Home Page */
	public static final String BTN_PHONES = "//a[text()='Phones']";
	public static final String BTN_LAPTOPS = "//a[text()='Laptops']";
	public static final String BTN_MONITORS = "//a[text()='Monitors']";
	public static final String STRING_PHONES = "//a[text()='Samsung galaxy s6']";
	public static final String STRING_MONITOR = "//a[text()='Apple monitor 24']";
	public static final String STRING_LAPTOP = "//a[text()='Sony vaio i5']";
	
	/** Item Name */
	public static final String BTN_SONYVAIO = "//a[text()='Sony vaio i5']";
	public static final String BTN_DELL = "//a[text()='Dell i7 8gb']";
	public static final String BTN_ADDTOCART ="//a[text()='Add to cart']";
	
	/** Navigation Item */
	public static final String BTN_HOME = "//a[@href='index.html' and @class='nav-link']";
	public static final String BTN_CART = "//a[@href='cart.html' and @class='nav-link']";
	
	/** Cart Page */
	public static final String CART_ITEM = "//tr[@class='success']/td[2]";
	public static final String BTN_PLACEORDER = "//button[text()='Place Order']";
	public static final String STRING_TOTALPRICE = "//h3[@id='totalp']";
	public static final String TXT_NAME = "//input[@id='name']";
	public static final String TXT_COUNTRY = "//input[@id='country']";
	public static final String TXT_CITY = "//input[@id='city']";
	public static final String TXT_CARD = "//input[@id='card']";
	public static final String TXT_MONTH = "//input[@id='month']";
	public static final String TXT_YEAR = "//input[@id='year']";
	public static final String BTN_PURCHASE = "//button[text()='Purchase']";
	public static final String STRING_PURCHASE_LOGS = "//p[@class='lead text-muted ']";
	public static final String BTN_OK = "//button[text()='OK']";
	
	/** API Constants */
	public static final String BASE_URL = "https://petstore.swagger.io/v2/";

}
