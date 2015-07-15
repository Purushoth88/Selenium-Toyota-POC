package apps.buyAToyota;

import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import apps.buyAToyota.homePage.HomePage;

import com.orasi.utils.TestEnvironment;
import com.orasi.utils.TestReporter;
import com.saucelabs.testng.SauceOnDemandTestListener;

@Listeners({ SauceOnDemandTestListener.class, com.orasi.utils.Screenshot.class })
public class TestNavBarNavigations extends TestEnvironment {
	private String application = "BuyAToyota";

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
	 * @Summary: Tests navigation buttons/links
	 * @Precondition:NA
	 * @Author: Waightstill W Avery
	 * @Version: 05/29/2015
	 * @Return: N/A
	 */
	@Features("General Usage")
	@Stories("As any user, I can use all navigation items on the Navigation Bar")
	@Test(groups = { "regression" })
	public void testAllNavBarNavigations() {
		testName = new Object() {
		}.getClass().getEnclosingMethod().getName() + "_"
				+ getOperatingSystem() + "_" + getBrowserUnderTest() + "_"
				+ getBrowserVersion();

		// Start the test and generate a driver
		testStart(testName);

		// Ensure the home page is loaded
		TestReporter.assertTrue(pageLoaded().isDomComplete(), "Load the Home Page");
		TestReporter.logScenario("Test the Navigation Bar Functionality");
		HomePage homePage = new HomePage(this);
		homePage.testNavBarNavigation();
	}

	@AfterMethod(groups = { "regression" })
	public void afterTest(ITestResult test) {
		endSauceTest(test);
	}
}
