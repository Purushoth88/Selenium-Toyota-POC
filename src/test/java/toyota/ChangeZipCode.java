package toyota;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Parameter;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Stories;
import apps.toyota.mainNavigation.MainNavigation;

import com.orasi.utils.Constants;
import com.orasi.utils.TestEnvironment;
import com.orasi.utils.TestNgTestClassMethods;
import com.orasi.utils.TestReporter;
import com.orasi.utils.dataProviders.ExcelDataProvider;
import com.saucelabs.testng.SauceOnDemandTestListener;

@Listeners({ SauceOnDemandTestListener.class })
public class ChangeZipCode extends TestClassTemplate_SauceLabs{
	private String application = "Toyota";
	
	/*
	 * Define a collection of webdrivers and test names inside a Map.
	 * This allows for more than one driver to be used within a test class.
	 * This also allows for a particular driver to be tied to a specific test 
	 * based on test name.
	 */
	private Map<String, WebDriver> drivers = new HashMap<String, WebDriver>();
	
	// **************
	// Data Provider
	// **************
	@DataProvider(name = "dataScenario")
	public Object[][] scenarios() {
		Object[][] excelData = new ExcelDataProvider(
				Constants.TOYOTA_DATAPROVIDER_PATH + "ChangeZipCode"+".xlsx", "ChangeZipCode").getTestData();
		return excelData;
	}

	// *********************
	// Before-Test Behavior
	// *********************
	@BeforeTest(groups = { "regression" })
	@Parameters({ "runLocation", "browserUnderTest", "browserVersion",
			"operatingSystem", "environment" })
	public void setupClass(String runLocation, String browserUnderTest,
			String browserVersion, String operatingSystem, String environment) {
		te = new TestEnvironment(application, browserUnderTest, browserVersion, operatingSystem,
				runLocation, environment);
		test = new TestNgTestClassMethods(application, te);
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

	@Features("Change Zipcode")
	@Stories("As any user, I can change the zip code on the main page")
	@Test(dataProvider = "dataScenario", groups = { "regression" }, singleThreaded=true )
	public void testChangeZipCode(@Parameter String testScenario, @Parameter String zipCode)
			throws InterruptedException, IOException {
		testName = new Object(){}.getClass().getEnclosingMethod().getName() 
				+ "_" + te.getOperatingSystem()
				+ "_" + te.getBrowserUnderTest()
				+ "_" + te.getBrowserVersion();

		te.setDriver(test.testStart(testName, te));
		
		// Ensure the home page is loaded
		TestReporter.logScenario(testScenario);
		TestReporter.assertTrue(te.pageLoaded(), "Verify Homepage is displayed");

		// Change the zipcode
		MainNavigation mainNav = new MainNavigation(te);
		TestReporter.assertTrue(te.pageLoaded(), "Verify Navigation Bar is loaded");
		mainNav.changeZipCodes(zipCode);
	}
}
