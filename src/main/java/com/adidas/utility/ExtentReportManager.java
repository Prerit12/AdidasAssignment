package com.adidas.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.adidas.base.BaseSetup;
import com.adidas.base.Constants;


public class ExtentReportManager extends BaseSetup{
	protected static ExtentReports extent;
	
	public static ExtentReports getInstance() {
		if (extent == null) {
			createInstance();
		}
		return extent;
	}

	public static ExtentReports createInstance() {
		if (extent == null) {
			String path = Constants.EXTENT_REPORT_PATH;
			String extentConfig = Constants.EXTENT_REPORT_CONFIG;
			ExtentHtmlReporter reporter = new ExtentHtmlReporter(path);
			extent = new ExtentReports();
			extent.setSystemInfo("OS", "Windows");
			extent.setSystemInfo("Host Name", "Prerit");
			extent.setSystemInfo("Environment", "Live");
			extent.setSystemInfo("UserName", "Prerit");
			reporter.loadConfig(extentConfig);
			extent.attachReporter(reporter);
		}
		return extent;
	}
}
