package com.adidas.Test;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.adidas.Pages.CartPage;
import com.adidas.Pages.HomePage;
import com.adidas.Pages.HomePage.tabName;
import com.adidas.base.BaseSetup;
import com.adidas.base.Constants;
import com.adidas.utility.FunctionsClass;

public class StoreTest extends BaseSetup {
	WebDriver driver;
	FunctionsClass fc;
	HomePage hp;
	CartPage cp;

	@BeforeClass
	public void setup(ITestContext context) {
		try {
			driver = launchApplicationBrowser();
			context.setAttribute("WebDriver", driver);
			fc = new FunctionsClass(driver);
			hp = new HomePage(driver);
			cp = new CartPage(driver);
			hp.launchURL(fc.ExcelReader("Data", "URL").get(0).toString());
		} catch (Exception e) {
			BaseSetup.logger.error(e);
		}
	}

	@Test(priority = 1)
	public void navigatePhone() {
		try {
			Assert.assertTrue(hp.navigateTab(tabName.PHONES));
		} catch (Exception e) {
			BaseSetup.logger.error(e);
		}
	}

	@Test(priority = 2)
	public void navigateMonitor() {
		try {
			Assert.assertTrue(hp.navigateTab(tabName.MONITORS));
		} catch (Exception e) {
			BaseSetup.logger.error(e);
		}
	}

	@Test(priority = 3)
	public void navigateLaptop() {
		try {
			Assert.assertTrue(hp.navigateTab(tabName.LAPTOPS));
		} catch (Exception e) {
			BaseSetup.logger.error(e);
		}
	}

	@Test(priority = 4)
	public void addSony() {
		try {
			Assert.assertTrue(hp.addToCart(tabName.LAPTOPS, Constants.BTN_SONYVAIO, "Sony vaio i5"));
		} catch (Exception e) {
			BaseSetup.logger.error(e);
		}
	}

	@Test(priority = 5)
	public void addDell() {
		try {
			Assert.assertTrue(hp.addToCart(tabName.LAPTOPS, Constants.BTN_DELL, "Dell i7 8gb"));
		} catch (Exception e) {
			BaseSetup.logger.error(e);
		}
	}

	@Test(priority = 6)
	public void deleteDell() {
		try {
			Assert.assertTrue(hp.deleteItem("Dell i7 8gb"));
		} catch (Exception e) {
			BaseSetup.logger.error(e);
		}
	}
	
	@Test(priority = 7)
	public void purchaseItem() {
		try {
			Assert.assertTrue(cp.purchaseItem());
		} catch (Exception e) {
			BaseSetup.logger.error(e);
		}
	}
}
