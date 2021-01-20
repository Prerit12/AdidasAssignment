package com.adidas.utility;

import org.openqa.selenium.WebDriver;
import org.testng.IClassListener;
import org.testng.ITestClass;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.adidas.base.BaseSetup;

public class TestListener extends BaseSetup implements ITestListener, IClassListener {
	FunctionsClass fc = new FunctionsClass();
	ExtentTest parent;

	@Override
	public void onStart(ITestContext iTestContext) {
		logger.info("Test Suite Started");
		fc.createDirectory();
		extent = ExtentReportManager.getInstance();
	}

	@Override
	public void onFinish(ITestContext iTestContext) {
		fc.copyReportToOld();
		driver = (WebDriver) iTestContext.getAttribute("WebDriver");
		if (driver != null) {
			driver.quit();
		}
		logger.info("Test Suite Finished");
		extent.flush();
	}

	@Override
	public void onTestStart(ITestResult iTestResult) {
		logger.info("Test case Started: " + iTestResult.getMethod().getMethodName());
		ExtentTest child = parentTest.get().createNode(iTestResult.getMethod().getMethodName());
		test.set(child);
	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		logger.info("Test case Success: " + iTestResult.getMethod().getMethodName());
		test.get().pass("Test passed");
		extent.flush();
	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		logger.error("Test case Failed: " + iTestResult.getMethod().getMethodName());
		test.get().fail(iTestResult.getThrowable());
		extent.flush();
	}

	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		logger.warn("Test case Skipped: " + iTestResult.getMethod().getMethodName());
		test.get().skip(iTestResult.getThrowable());
		extent.flush();
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		// Functionality not required
	}

	@Override
	public void onBeforeClass(ITestClass testClass) {
		logger.info("Before Class Started: " + testClass.getName());
		String className = testClass.getName();
		parent = extent.createTest(className);
		parentTest.set(parent);
	}

	@Override
	public void onAfterClass(ITestClass testClass) {
		// TODO Auto-generated method stub

	}
}
