package com.orasi.utils;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import ru.yandex.qatools.allure.annotations.Attachment;

public class NewScreenshot extends TestListenerAdapter {

    @Override
    public void onTestFailure(ITestResult result) {
	
	Object currentClass = result.getInstance();
	WebDriver driver = ((TestClassTemplate_SauceLabs) currentClass).te.getDriver();
	String runLocation = ((TestClassTemplate_SauceLabs) currentClass).te.getRunLocation().toLowerCase();
	if ( runLocation == "remote" ) driver = new Augmenter().augment(driver);
	Reporter.setCurrentTestResult(result);
	
	String destDir = "selenium-reports/html/screenshots";
	new File(destDir).mkdirs();	
	DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy__hh_mm_ssaa");
	
	String destFile = dateFormat.format(new Date()) + ".png";
	
	TestReporter.logScreenshot(driver, destDir + "/" + destFile);
	FailedScreenshot(driver);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
	// will be called after test will be skipped
    }

    @Override
    public void onTestSuccess(ITestResult result) {
	// will be called after test will pass
    }
    
    @Attachment(type = "image/png")
    public static byte[] FailedScreenshot(WebDriver driver) {
   	return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }

}