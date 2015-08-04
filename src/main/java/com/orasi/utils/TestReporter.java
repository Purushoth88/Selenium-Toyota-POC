package com.orasi.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;

import com.orasi.utils.date.SimpleDate;

public class TestReporter {
	// Field, typically defined at the test level, used to determine if reports
	// should be output to the console
	private static boolean printToConsole = false;

	/**
	 * @summary Return the current timestamp; to be used to report when various actions occur
	 * @return String with the current timestamp
	 */
	private static String getTimestamp() {
		return SimpleDate.getTimestamp().toString() + " :: ";
	}

	/**
	 * @summary Method to remove common HTML characters from a string
	 * @param log - string with which to trim any HTML characters
	 * @return String with HTML characters removed
	 */
	private static String trimHtml(String log) {
		return log.replaceAll("<[^>]*>", "");
	}

	/**
	 * @summary Method to set the value used to determine if reports are to be output to the console
	 * @param printToConsole - boolean value to determine
	 */
	public static void setPrintToConsole(boolean printToConsole) {
		TestReporter.printToConsole = printToConsole;
	}

	/**
	 * @summary Method to get the value used to determine if reports are to be output to the console
	 * @return Boolean value to determine if reports should be output to the console
	 */
	public static boolean getPrintToConsole() {
		return printToConsole;
	}

	/**
	 * @summary Method used to report a step, typically used at the test level
	 * @param step - String description of the step to log
	 */
	public static void logStep(String step) {
		Reporter.log("<br/><b><font size = 4>Step: " + step + "</font></b><br/>");
		if (getPrintToConsole())
			System.out.println(step);
	}

	/**
	 * @summary Method used to report the overall test scenario, typically used at the test level
	 * @param scenario - string scenario to log
	 */
	public static void logScenario(String scenario) {
		Reporter.log("<br/><b><font size = 5>Data Scenario: " + scenario + "</font></b><br/>");
		if (getPrintToConsole())
			System.out.println(getTimestamp() + trimHtml(scenario));
	}

	/**
	 * @summary Method used to report individual interactions with the application, typically used at the page class level
	 * @param message - string message describing the interaction
	 */
	public static void interfaceLog(String message) {
		Reporter.log(getTimestamp() + message + "<br />");
		if (getPrintToConsole())
			System.out.println(getTimestamp() + trimHtml(message.trim()));
	}

	/**
	 * @summary Method used to report individual interactions with the application, typically used at the page class level
	 * @param message - string message describing the interaction
	 * @param failed - boolean value indicating whether the interaction failed
	 */
	public static void interfaceLog(String message, boolean failed) {
		if(failed){
			logFailure(message);
		}else{
			Reporter.log(getTimestamp() + "<font size = 2 color=\"red\">" + message + "</font><br />");
		}
		if (getPrintToConsole())
			System.out.println(getTimestamp() + trimHtml(message.trim()));
	}

	/**
	 * @summary Method to log a report
	 * @param message - message to log to the reporter
	 */
	public static void log(String message) {
		Reporter.log(getTimestamp() + " <i><b>" + message + "</b></i><br />");
		if (getPrintToConsole())
			System.out.println(getTimestamp() + trimHtml(message));
	}

	/**
	 * @summary Method to log a failure
	 * @param message - string description of the failure
	 */
	public static void logFailure(String message) {
		Reporter.log(
				getTimestamp() + " <font size = 2 color=\"red\"><b><u> FAILURE: " + message + "</font></u></b><br />");
		if (getPrintToConsole())
			System.out.println(getTimestamp() + trimHtml(message));
	}

	/**
	 * @summary Method to assert if a condition is true
	 * @param condition - string condition to test
	 * @param description - string description of the condition to be evaluated
	 */
	public static void assertTrue(boolean condition, String description) {
		try {
			Assert.assertTrue(condition, description);
			Reporter.log(getTimestamp() + " <font size = 2 color=\"green\"><b><u>Assert True - " + description
					+ "</font></u></b><br />");
			if (getPrintToConsole())
				System.out.println(getTimestamp() + "Assert True - " + trimHtml(description));
		} catch (AssertionError failure) {
			logFailure("<font size = 2 color=\"red\"><b><u>Assert True - " + description + "</b></u></font><br />");
			if (getPrintToConsole())
				System.out.println(getTimestamp() + "Assert True - " + trimHtml(description));
			Assert.fail(description);
		}
	}

	/**
	 * @summary Method to assert if a condition is false
	 * @param condition - string condition to test
	 * @param description - string description of the condition to be evaluated
	 */
	public static void assertFalse(boolean condition, String description) {
		try {
			Assert.assertFalse(condition, description);
			Reporter.log(getTimestamp() + " <font size = 2 color=\"green\"><b><u>Assert False - " + description
					+ "</font></u></b><br />");
			if (getPrintToConsole())
				System.out.println(getTimestamp() + "Assert False - " + trimHtml(description));
		} catch (AssertionError failure) {
			logFailure("<font size = 2 color=\"red\"><b><u>Assert False - " + description + "</b></u></font><br />");
			if (getPrintToConsole())
				System.out.println(getTimestamp() + "Assert False - " + trimHtml(description));
			Assert.fail(description);
		}
	}
	
	/**
	 * @summary Method to assert if two objects are equal
	 * @param value1 - First value to compare
	 * @param value2 - Second value to compare
	 * @param description - string description of the condition to be evaluated
	 */
	public static void assertEquals(Object value1, Object value2, String description) {
		try {
			Assert.assertEquals(value1, value2, description);
			Reporter.log(getTimestamp() + " <font size = 2 color=\"green\"><b><u>Assert Equals - " + description
					+ "</font></u></b><br />");
			if (getPrintToConsole())
				System.out.println(getTimestamp() + "Assert Equals - " + trimHtml(description));
		} catch (AssertionError failure) {
			logFailure("<font size = 2 color=\"red\"><b><u>Assert Equals - " + description + "</b></u></font><br />");
			if (getPrintToConsole())
				System.out.println(getTimestamp() + "Assert Equals - " + trimHtml(description));
			Assert.fail(description);
		}
	}
	
	/**
	 * @summary Method to assert if two objects are not equal
	 * @param value1 - First value to compare
	 * @param value2 - Second value to compare
	 * @param description - string description of the condition to be evaluated
	 */
	public static void assertNotEquals(Object value1, Object value2, String description) {
		try {
			Assert.assertNotEquals(value1, value2, description);
			Reporter.log(getTimestamp() + " <font size = 2 color=\"green\"><b><u>Assert Not Equals - " + description
					+ "</font></u></b><br />");
			if (getPrintToConsole())
				System.out.println(getTimestamp() + "Assert Not Equals - " + trimHtml(description));
		} catch (AssertionError failure) {
			logFailure(
					"<font size = 2 color=\"red\"><b><u>Assert Not Equals - " + description + "</b></u></font><br />");
			if (getPrintToConsole())
				System.out.println(getTimestamp() + "Assert Not Equals - " + trimHtml(description));
			Assert.fail(description);
		}
	}

	/**
	 * @summary Method to determine if a value is greater than zero
	 * @param value - integer value to compare to zero
	 */
	public static void assertGreaterThanZero(int value) {
		try {
			Assert.assertTrue(value > 0);
			Reporter.log(getTimestamp() + " <font size = 2 color=\"green\"><b><u>Assert Greater Than Zero - Assert "
					+ value + " is greater than zero</font></u></b><br />");
			if (getPrintToConsole())
				System.out.println(
						getTimestamp() + "Assert Greater Than Zero - Assert " + value + " is greater than zero");
		} catch (AssertionError failure) {
			logFailure(
					"<font size = 2 color=\"red\"><b><u>Assert Greater Than Zero - " + value + "</b></u></font><br />");
			if (getPrintToConsole())
				System.out.println(
						getTimestamp() + "Assert Greater Than Zero - Assert " + value + " is greater than zero");
			Assert.fail("Assert " + value + " is greater than zero");
		}
	}

	/**
	 * @summary - Method to determine if a value is greater than zero
	 * @param value - float value to compare to zero
	 */
	public static void assertGreaterThanZero(float value) {
		assertGreaterThanZero((int) value);
	}

	/**
	 * @summary - Method to determine if a value is greater than zero
	 * @param value - double value to compare to zero
	 */
	public static void assertGreaterThanZero(double value) {
		assertGreaterThanZero((int) value);
	}

	/**
	 * @summary Method to determine if an object is null
	 * @param object - object to determine if null
	 * @param description - string describing the evaluation
	 */
	public static void assertNull(Object object, String description) {
		try {
			Assert.assertNull(object, description);
			Reporter.log(getTimestamp() + " <font size = 2 color=\"green\"><b><u>Assert Null - " + description
					+ "</font></u></b><br />");
			if (getPrintToConsole())
				System.out.println(getTimestamp() + "Assert Null - " + trimHtml(description));
		} catch (AssertionError failure) {
			logFailure("<font size = 2 color=\"red\"><b><u>Assert Null - " + description + "</b></u></font><br />");
			if (getPrintToConsole())
				System.out.println(getTimestamp() + "Assert Null - " + trimHtml(description));
			Assert.fail(description);
		}
	}

	/**
	 * @summary - Method to determine if an object is not null
	 * @param object - object to determine if not null
	 * @param description - string describing the evaluation
	 */
	public static void assertNotNull(Object object, String description) {
		try {
			Assert.assertNotNull(object, description);
			Reporter.log(getTimestamp() + "<font size = 2 color=\"green\"><b><u>Assert Not Null - " + description
					+ "</font></u></b><br />");
			if (getPrintToConsole())
				System.out.println(getTimestamp() + "Assert Not Null - " + trimHtml(description));
		} catch (AssertionError failure) {
			logFailure("<font size = 2 color=\"red\"><b><u>Assert Not Null - " + description + "</b></u></font><br />");
			if (getPrintToConsole())
				System.out.println(getTimestamp() + "Assert Not Null - " + trimHtml(description));
			Assert.fail(description);
		}
	}

	/**
	 * @summary Method to take a screenshot and save it in a user-defined location
	 * @param driver - Current web driver
	 * @param fileLocation - directory location to which to save the screenshot
	 * @param runLocation
	 */
	public static void logScreenshot(WebDriver driver, String fileLocation, String runLocation) {
		File file = new File("");

		try {
			file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(file, new File(fileLocation));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (runLocation.equalsIgnoreCase("remote")) {
			fileLocation = fileLocation.replace("/var/lib/jenkins/jobs/OpenSandbox/jobs/Toyota-SauceLabs/workspace/",
					"job/OpenSandbox/job/Toyota-SauceLabs/ws/");
			Reporter.log("<a href='https://jenkins.orasi.com/" + fileLocation + "'>FAILED SCREENSHOT</a>");
		} else {
			TestReporter.log(fileLocation);
			Reporter.log("<a href='" + fileLocation + "'> <img src='file:///" + fileLocation
					+ "' height='200' width='300'/> </a>");
		}
	}
}
