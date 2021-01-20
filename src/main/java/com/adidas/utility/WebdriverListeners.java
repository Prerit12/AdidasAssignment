package com.adidas.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

import com.adidas.base.BaseSetup;

public class WebdriverListeners implements WebDriverEventListener {
	
	@Override
	public void beforeAlertAccept(WebDriver driver) {
		BaseSetup.logger.info("Clicking on Alert Accept Button");
	}

	@Override
	public void afterAlertAccept(WebDriver driver) {
		BaseSetup.logger.info("Accept Button on Alert clicked");
	}

	@Override
	public void afterAlertDismiss(WebDriver driver) {
		BaseSetup.logger.info("Clicking on Alert Dismiss Button");
	}

	@Override
	public void beforeAlertDismiss(WebDriver driver) {
		BaseSetup.logger.info("Dismiss Button on Alert clicked");
	}

	@Override
	public void beforeNavigateTo(String url, WebDriver driver) {
		BaseSetup.logger.info("Navigating to URL: " + url);
	}

	@Override
	public void afterNavigateTo(String url, WebDriver driver) {
		BaseSetup.logger.info("Navigated to URL: " + url);
	}

	@Override
	public void beforeNavigateBack(WebDriver driver) {
		BaseSetup.logger.info("Navigating Back to URL");
	}

	@Override
	public void afterNavigateBack(WebDriver driver) {
		BaseSetup.logger.info("Navigated Back to URL");
	}

	@Override
	public void beforeNavigateForward(WebDriver driver) {
		BaseSetup.logger.info("Navigating Forward to URL");
	}

	@Override
	public void afterNavigateForward(WebDriver driver) {
		BaseSetup.logger.info("Navigated Forward to URL");
	}

	@Override
	public void beforeNavigateRefresh(WebDriver driver) {
		BaseSetup.logger.info("Refreshing the Page");
	}

	@Override
	public void afterNavigateRefresh(WebDriver driver) {
		BaseSetup.logger.info("Page is refreshed");
	}

	@Override
	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		BaseSetup.logger.info("Finding Element By: " + by + " and Element: " + element);
	}

	@Override
	public void afterFindBy(By by, WebElement element, WebDriver driver) {
		BaseSetup.logger.info("Element Found By: " + by + " and Element: " + element);
	}

	@Override
	public void beforeClickOn(WebElement element, WebDriver driver) {
		BaseSetup.logger.info("Clicking on Element: " + element);
	}

	@Override
	public void afterClickOn(WebElement element, WebDriver driver) {
		BaseSetup.logger.info("Clicked on Element: " + element);
	}

	@Override
	public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		BaseSetup.logger.info("Changing value of Element: " + element);
	}

	@Override
	public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		BaseSetup.logger.info("Changed value of Element: " + element);
	}

	@Override
	public void beforeScript(String script, WebDriver driver) {
		BaseSetup.logger.info("Script is executing: " + script);
	}

	@Override
	public void afterScript(String script, WebDriver driver) {
		BaseSetup.logger.info("Script is executed: " + script);
	}

	@Override
	public void beforeSwitchToWindow(String windowName, WebDriver driver) {
		BaseSetup.logger.info("Switching to Window: " + windowName);
	}

	@Override
	public void afterSwitchToWindow(String windowName, WebDriver driver) {
		BaseSetup.logger.info("Switched to Window: " + windowName);
	}

	@Override
	public void onException(Throwable throwable, WebDriver driver) {
		BaseSetup.logger.error("Exception is: " + throwable);
	}

	@Override
	public <X> void beforeGetScreenshotAs(OutputType<X> target) {
		BaseSetup.logger.info("Taking screenshot: " + target);
	}

	@Override
	public <X> void afterGetScreenshotAs(OutputType<X> target, X screenshot) {
		BaseSetup.logger.info("Screenshot taken: " + screenshot);
	}

	@Override
	public void beforeGetText(WebElement element, WebDriver driver) {
		BaseSetup.logger.info("Getting text of Element: " + element);
	}

	@Override
	public void afterGetText(WebElement element, WebDriver driver, String text) {
		BaseSetup.logger.info("Text of Element: " + element + "is: " + text);
	}
}
