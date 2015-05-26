package toyota;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import apps.toyota.secondaryNavigation.SecondaryNavigation;

import com.orasi.utils.TestEnvironment;
import com.orasi.utils.TestReporter;
import com.saucelabs.testng.SauceOnDemandTestListener;

@Listeners({ SauceOnDemandTestListener.class })
public class TestAllSecondaryNavigations extends TestEnvironment {
	private String application = "Toyota";
	/*
	 * Define a collection of webdrivers and test names inside a Map. This
	 * allows for more than one driver to be used within a test class. This also
	 * allows for a particular driver to be tied to a specific test based on
	 * test name.
	 */
	protected Map<String, WebDriver> drivers = new HashMap<String, WebDriver>();

	// *********************
	// Before-Test Behavior
	// *********************
	@BeforeTest(groups = { "regression" })
	@Parameters({ "runLocation", "browserUnderTest", "browserVersion",
			"operatingSystem", "environment" })
	public void setupClass(String runLocation, String browserUnderTest,
			String browserVersion, String operatingSystem, String environment) {
		setApplicationUnderTest(application);
		setBrowserUnderTest(browserUnderTest);
		setBrowserVersion(browserVersion);
		setOperatingSystem(operatingSystem);
		setRunLocation(runLocation);
		setTestEnvironment(environment);
	}

	// *****
	// TEST
	// *****
	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * @Summary: Tests secondary navigation buttons/links
	 * @Precondition:NA
	 * @Author: Waightstill W Avery
	 * @Version: 03/10/2015
	 * @Return: N/A
	 */
	@Features("General Usage")
	@Stories("As any user, I can use all navigation items on the Navigation Bar")
	@Test(groups = { "regression" }, singleThreaded = true)
	public void testAllSecondaryNavigations() {
		testName = new Object() {
		}.getClass().getEnclosingMethod().getName() + "_"
				+ getOperatingSystem() + "_" + getBrowserUnderTest() + "_"
				+ getBrowserVersion();

		// Start the test and generate a driver
		testStart(testName);

		// Ensure the home page is loaded
		TestReporter.assertTrue(pageLoaded(), "Load the Home Page");
		TestReporter.log("Test the Secondary Navigation Bar Functionality");
		SecondaryNavigation secNav = new SecondaryNavigation(this);
		secNav.navigateAllSecondaryNavigationTabs();
	}

	@AfterTest
	public void afterTest() {
	endTest(testName);
	}
}
