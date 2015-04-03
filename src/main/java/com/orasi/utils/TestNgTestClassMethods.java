package com.orasi.utils;

import com.orasi.utils.Base64Coder;
import com.orasi.utils.Constants;
import com.orasi.utils.Screenshot;
import com.orasi.utils.TestReporter;
import com.orasi.utils.WebDriverSetup;
import com.saucelabs.saucerest.SauceREST;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.json.simple.JSONArray;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.Optional;

public class TestNgTestClassMethods {
	private String application = "";
	private String browserUnderTest = "";
	private String browserVersion = "";
	private String operatingSystem = "";
	private String runLocation = "";
	private String environment = "";

	private Map<String, WebDriver> drivers = new HashMap<String, WebDriver>();
	private static ResourceBundle appURLRepository = ResourceBundle
			.getBundle(Constants.ENVIRONMENT_URL_PATH);

	public TestNgTestClassMethods(String application) {
		this.application = application;
	}

	public void before(@Optional String runLocation, String browserUnderTest,
			String browserVersion, String operatingSystem, String environment) {
		this.runLocation = runLocation;
		this.browserUnderTest = browserUnderTest;
		this.browserVersion = browserVersion;
		this.operatingSystem = operatingSystem;
		this.environment = environment;
	}

	public void after(ITestResult test, WebDriver driver) {
		System.out.println(test.getMethod().getMethodName());
		//WebDriver driver = drivers.get(test.getMethod().getMethodName());

		Map<String, Object> updates = new HashMap<String, Object>();
		updates.put("name", test.getMethod().getMethodName());

		// if is a failure, then take a screenshot
		if (test.getStatus() == ITestResult.FAILURE) {
			new Screenshot().takeScreenShot(test, driver);
			updates.put("passed", false);
		} else {
			updates.put("passed", true);
		}

		JSONArray tags = new JSONArray();
		String[] groups = test.getMethod().getGroups();
		for (int x = 0; x < groups.length; x++) {
			tags.add(groups[x]);
		}
		updates.put("tags", tags);

		if (runLocation.equalsIgnoreCase("remote")) {
			ResourceBundle appURLRepository = ResourceBundle.getBundle(Constants.ENVIRONMENT_URL_PATH);
			SauceREST client = new SauceREST(
					Base64Coder.decodeString(appURLRepository.getString("SAUCELABS_USERNAME")),
					Base64Coder.decodeString(appURLRepository.getString("SAUCELABS_KEY")));
			client.updateJobInfo(((RemoteWebDriver) driver).getSessionId().toString(), updates);
			System.out.println(client.getJobInfo(((RemoteWebDriver) driver).getSessionId().toString()));
		}

		if (driver != null && driver.getWindowHandles().size() > 0) {
			driver.quit();
		}
	}

	public WebDriver testStart(String testName) throws InterruptedException,
			IOException {
		// Uncomment the following line to have TestReporter outputs output to
		// the console
		TestReporter.setPrintToConsole(true);

		WebDriverSetup setup = new WebDriverSetup(application,
				browserUnderTest, browserVersion, operatingSystem, runLocation,
				environment, testName);
		WebDriver driver = setup.initialize();

		System.out.println(testName);
		drivers.put(testName, driver);

		return drivers.get(testName);
	}
	
//	private String application = "";
//	private String browserUnderTest = "";
//	private String browserVersion = "";
//	private String operatingSystem = "";
//	private String runLocation = "";
//	private String environment = "";
	public String getApplicationUnderTest(){
		return application;
	}
	
	public String getBrowserUnderTest(){
		return browserUnderTest;
	}
	
	public String getBowserVersion(){
		return browserVersion;
	}
	
	public String getOperatingSystem(){
		return operatingSystem;
	}
	
	public String getRunLocation(){
		return runLocation;
	}
	
	public String getEnvironment(){
		return environment;
	}
}
