package com.adidas.Pages;

import org.openqa.selenium.WebDriver;

import com.adidas.base.BaseSetup;
import com.adidas.base.Constants;
import com.adidas.utility.FunctionsClass;
import com.adidas.utility.FunctionsClass.locatorType;

public class CartPage {
	WebDriver driver;
	FunctionsClass fc;
	Boolean result;

	public CartPage(WebDriver driver) {
		this.driver = driver;
		fc = new FunctionsClass(driver);
	}

	/** Function used to purchase Item */
	public boolean purchaseItem() {
		String tcName = new Throwable().getStackTrace()[0].getClassName();
		result = false;
		int x = 0;
		try {
			String expectedPrice = fc.getText(Constants.STRING_TOTALPRICE, locatorType.XPATH);
			fc.clickElement(Constants.BTN_PLACEORDER, locatorType.XPATH);
			fc.waitUntilFound(Constants.TXT_NAME, locatorType.XPATH, 10);
			fc.sendText(Constants.TXT_NAME, "TestUser", locatorType.XPATH);
			fc.sendText(Constants.TXT_COUNTRY, "INDIA", locatorType.XPATH);
			fc.sendText(Constants.TXT_CITY, "GHAZIABAD", locatorType.XPATH);
			fc.sendText(Constants.TXT_CARD, "1234567890", locatorType.XPATH);
			fc.sendText(Constants.TXT_MONTH, "12", locatorType.XPATH);
			fc.sendText(Constants.TXT_YEAR, "24", locatorType.XPATH);
			fc.clickElement(Constants.BTN_PURCHASE, locatorType.XPATH);
			fc.waitUntilFound(Constants.STRING_PURCHASE_LOGS, locatorType.XPATH, 10);
			if (fc.getText(Constants.STRING_PURCHASE_LOGS, locatorType.XPATH).contains(expectedPrice)) {
				fc.passTest("Price is coming correct", tcName);
				String purchaseLogs = fc.getText(Constants.STRING_PURCHASE_LOGS, locatorType.XPATH);
				String logID = fc.splitString(purchaseLogs);
				BaseSetup.test.get().info("Purchase Log ID is: " + logID);
				fc.clickElement(Constants.BTN_OK, locatorType.XPATH);
			} else {
				x = x + 1;
				fc.failTest("Price is not coming correct", tcName);
			}
			if (x == 0) {
				result = true;
			}
		} catch (Exception e) {
			BaseSetup.logger.error(e);
		}
		return result;
	}
}
