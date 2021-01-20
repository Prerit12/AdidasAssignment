package com.adidas.Pages;

import org.openqa.selenium.WebDriver;

import com.adidas.base.BaseSetup;
import com.adidas.base.Constants;
import com.adidas.utility.FunctionsClass;
import com.adidas.utility.FunctionsClass.locatorType;

public class HomePage {

	WebDriver driver;
	FunctionsClass fc;
	Boolean result;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		fc = new FunctionsClass(driver);
	}

	/** Function to Launch URL */
	public void launchURL(String url) {
		try {
			driver.get(url);
			driver.manage().window().maximize();
		} catch (Exception e) {
			BaseSetup.logger.error(e);
		}
	}

	/** Enum for Locators */
	public enum tabName {
		PHONES, LAPTOPS, MONITORS
	}

	/** Function to Navigate Tab */
	public boolean navigateTab(tabName TabName) {
		String tcName = new Throwable().getStackTrace()[0].getClassName();
		result = false;
		int x = 0;
		try {
			switch (TabName.toString().toLowerCase()) {
			case "phones":
				fc.waitUntilClickable(Constants.BTN_PHONES, locatorType.XPATH, 10);
				fc.clickElement(Constants.BTN_PHONES, locatorType.XPATH);
				fc.waitUntilClickable(Constants.STRING_PHONES, locatorType.XPATH, 10);
				if (fc.elementVisible(Constants.STRING_PHONES, locatorType.XPATH)) {
					fc.passTest("Phones tab is clicked", tcName);
				} else {
					x = x + 1;
					fc.failTest("Phone tab is not clicked", tcName);
				}
				break;
			case "laptops":
				fc.waitUntilClickable(Constants.BTN_LAPTOPS, locatorType.XPATH, 10);
				fc.clickElement(Constants.BTN_LAPTOPS, locatorType.XPATH);
				fc.waitUntilClickable(Constants.STRING_LAPTOP, locatorType.XPATH, 10);
				if (fc.elementVisible(Constants.STRING_LAPTOP, locatorType.XPATH)) {
					fc.passTest("Laptops tab is clicked", tcName);
				} else {
					x = x + 1;
					fc.failTest("Laptops tab is not clicked", tcName);
				}
				break;
			case "monitors":
				fc.waitUntilClickable(Constants.BTN_MONITORS, locatorType.XPATH, 10);
				fc.clickElement(Constants.BTN_MONITORS, locatorType.XPATH);
				fc.waitUntilClickable(Constants.STRING_MONITOR, locatorType.XPATH, 10);
				if (fc.elementVisible(Constants.STRING_MONITOR, locatorType.XPATH)) {
					fc.passTest("Monitor tab is clicked", tcName);
				} else {
					x = x + 1;
					fc.failTest("Monitor tab is not clicked", tcName);
				}
				break;
			}
			if (x == 0) {
				result = true;
			}
		} catch (Exception e) {
			BaseSetup.logger.error(e);
			fc.failTest("Searched text is not Visible", tcName);
		}
		return result;
	}

	/** Function to add item to Cart */
	public boolean addToCart(tabName TabName, String itemName, String item) {
		String tcName = new Throwable().getStackTrace()[0].getClassName();
		result = false;
		int x = 0;
		try {
			navigateTab(TabName);
			fc.waitUntilFound(itemName, locatorType.XPATH, 10);
			if (fc.elementVisible(itemName, locatorType.XPATH)) {
				fc.clickElement(itemName, locatorType.XPATH);
				fc.waitUntilClickable(Constants.BTN_ADDTOCART, locatorType.XPATH, 10);
				fc.clickElement(Constants.BTN_ADDTOCART, locatorType.XPATH);
				fc.sleep(1);
				fc.alertAccept();
				fc.clickElement(Constants.BTN_CART, locatorType.XPATH);
				fc.sleep(3);
				if (fc.getText("//tbody[@id='tbodyid']/tr/td[contains(text(),'" + item + "')]").contains(item)) {
					fc.passTest("Item Added to Cart", tcName);
					fc.clickElement(Constants.BTN_HOME, locatorType.XPATH);
				} else {
					x = x + 1;
					fc.failTest("Item is not added to Cart", tcName);
				}
			}

			if (x == 0) {
				result = true;
			}
		} catch (Exception e) {
			BaseSetup.logger.error(e);
			fc.failTest("Item Not Added to Cart", tcName);
		}
		return result;
	}

	/** Function to add item to Cart */
	public boolean deleteItem(String item) {
		String tcName = new Throwable().getStackTrace()[0].getClassName();
		result = false;
		int x = 0;
		try {
			fc.waitUntilClickable(Constants.BTN_CART, locatorType.XPATH, 10);
			fc.clickElement(Constants.BTN_CART, locatorType.XPATH);
			fc.sleep(3);
			if (fc.getText("//tbody[@id='tbodyid']/tr/td[contains(text(),'" + item + "')]").contains(item)) {
				fc.clickElement("//td[text()='" + item + "']/../td[4]/a[text()='Delete']");
				fc.sleep(3);
				if (fc.createElements(Constants.CART_ITEM, locatorType.XPATH).size() == 1) {
					fc.passTest("Item Deleted from Cart", tcName);
				}
			} else {
				x = x + 1;
				fc.failTest("Item is not deleted from Cart", tcName);
			}
			if (x == 0) {
				result = true;
			}
		} catch (Exception e) {
			BaseSetup.logger.error(e);
			fc.failTest("Item Not Deleted from Cart", tcName);
		}
		return result;
	}
}
