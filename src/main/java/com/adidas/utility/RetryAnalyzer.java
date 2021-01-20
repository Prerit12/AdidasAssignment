package com.adidas.utility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.adidas.base.BaseSetup;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int retryCnt = 0;
    //You could mentioned maxRetryCnt (Maximum Retry Count) as per your requirement. Here I took 2, For any failed TestCases it runs two times
    private int maxRetryCnt = 2;
    
    //This method will be called every time a test fails. It will return TRUE if a test fails and need to be retried, else it returns FALSE
    public boolean retry(ITestResult result) {
        if (retryCnt < maxRetryCnt) {
			BaseSetup.logger.info("Retrying " + result.getName() + " again and the count is " + (retryCnt+1));
            retryCnt++;
            return true;
        }
        return false;
    }
   
}