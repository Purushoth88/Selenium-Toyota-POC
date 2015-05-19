package toyota;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import apps.toyota.secondaryNavigation.SecondaryNavigation;

import com.orasi.utils.Sleeper;
import com.orasi.utils.TestEnvironment;
import com.orasi.utils.TestNgTestClassMethods;
import com.orasi.utils.TestReporter;
import com.saucelabs.testng.SauceOnDemandTestListener;

@Listeners({SauceOnDemandTestListener.class})
public class TestAllSecondaryNavigations extends TestClassTemplate_SauceLabs{
	private String application = "Toyota";
	/*
	 * Define a collection of webdrivers and test names inside a Map.
	 * This allows for more than one driver to be used within a test class.
	 * This also allows for a particular driver to be tied to a specific test 
	 * based on test name.
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
		this.te = new TestEnvironment(application, browserUnderTest, browserVersion, operatingSystem,
				runLocation, environment);
		this.test = new TestNgTestClassMethods(application, this.te);
	}
	
	//*****
	// TEST
	//*****
	/**
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @Summary: Tests secondary navigation buttons/links
	 * @Precondition:NA
	 * @Author: Waightstill W Avery
	 * @Version: 03/10/2015
	 * @Return: N/A
	 */
	@Test(groups = { "regression" }, singleThreaded=true)
	public void testAllSecondaryNavigations() throws InterruptedException, IOException {
		this.testName = new Object(){}.getClass().getEnclosingMethod().getName() 
				+ "_" + this.te.getOperatingSystem()
				+ "_" + this.te.getBrowserUnderTest()
				+ "_" + this.te.getBrowserVersion();

		this.te.setDriver(this.test.testStart(this.testName, this.te));
		
		//Ensure the home page is loaded
		TestReporter.assertTrue(this.te.pageLoaded(), "Load the Home Page");

		Sleeper.sleep(2000);
		
		//Test the secondary navigation bar functionality
		TestReporter.log("Test the Secondary Navigation Bar Functionality");
		SecondaryNavigation secNav = new SecondaryNavigation(this.te);
		secNav.navigateAllSecondaryNavigationTabs();
	}
}
