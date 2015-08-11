package apps.toyota;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Parameter;
import ru.yandex.qatools.allure.annotations.Stories;
import apps.toyota.mainNavigation.MainNavigation;

import com.orasi.utils.Constants;
import com.orasi.utils.TestEnvironment;
import com.orasi.utils.TestReporter;
import com.orasi.utils.dataProviders.ExcelDataProvider;
import com.saucelabs.testng.SauceOnDemandTestListener;

@Listeners({ SauceOnDemandTestListener.class, com.orasi.utils.Screenshot.class })
public class ChangeZipCode extends TestEnvironment {
	private String application = "Toyota";

	/*
	 * Define a collection of webdrivers and test names inside a Map. This
	 * allows for more than one driver to be used within a test class. This also
	 * allows for a particular driver to be tied to a specific test based on
	 * test name.
	 */
	private Map<String, WebDriver> drivers = new HashMap<String, WebDriver>();

	// **************
	// Data Provider
	// **************
	@DataProvider(name = "dataScenario")
	public Object[][] scenarios() {
		Object[][] excelData = new ExcelDataProvider(Constants.TOYOTA_DATAPROVIDER_PATH + "ChangeZipCode" + ".xlsx",
				"ChangeZipCode").getTestData();
		return excelData;
	}

	// *********************
	// Before-Test Behavior
	// *********************
	@BeforeTest(groups = { "regression" })
	@Parameters({ "runLocation", "browserUnderTest", "browserVersion", "operatingSystem", "environment" })
	public void setupClass(String runLocation, String browserUnderTest, String browserVersion, String operatingSystem,
			String environment) {
		setApplicationUnderTest(application);
		setBrowserUnderTest(browserUnderTest);
		setBrowserVersion(browserVersion);
		setOperatingSystem(operatingSystem);
		setRunLocation(runLocation);
		setTestEnvironment(environment);
	}

	// TEST
	// *****
	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * @Summary: Changes zip code on Toyota.com website
	 * @Precondition:NA
	 * @Author: Waightstill W Avery
	 * @Version: 03/10/2015
	 * @Return: N/A
	 */

	@Features("General Usage")
	@Stories("As any user, I can change the zip code on the main page")
	@Test(dataProvider = "dataScenario", groups = { "regression" })
	public void testChangeZipCode(@Parameter String testScenario, @Parameter String zipCode) {
		testName = new Object() {
		}.getClass().getEnclosingMethod().getName() + "_" + getOperatingSystem().replace(" ", "").replace(".", "") + "_" + getBrowserUnderTest() + "_"
				+ getBrowserVersion();

		testStart(testName);

		// Ensure the home page is loaded
		TestReporter.assertTrue(pageLoaded().isDomComplete(), "Verify Homepage is displayed");
		// Report the test scenario
		TestReporter.logScenario(testScenario);

		// Test the functionality to change the zip code
		TestReporter.logStep("Test the Functionality to Change the Zip Code");
		MainNavigation mainNav = new MainNavigation(this);
		TestReporter.assertTrue(pageLoaded().isDomComplete(), "Verify Navigation Bar is loaded");
		mainNav.changeZipCodes(zipCode);
	}

	@AfterMethod(groups = { "regression" })
	public void afterTest(ITestResult test) throws ClientProtocolException, IOException {
		endSauceTest(test);
	}
}