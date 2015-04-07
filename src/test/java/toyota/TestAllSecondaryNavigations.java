package toyota;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import apps.toyota.homePage.HomePage;
import apps.toyota.secondaryNavigation.SecondaryNavigation;

import com.orasi.utils.TestEnvironment;
import com.orasi.utils.TestNgTestClassMethods;
import com.orasi.utils.TestReporter;
import com.saucelabs.testng.SauceOnDemandTestListener;

@Listeners({SauceOnDemandTestListener.class})
public class TestAllSecondaryNavigations {
	String testName = null;
	String application = "Toyota";
	
	private TestEnvironment te = null;
	private TestNgTestClassMethods test;
//	private WebDriver driver;

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
		//test.before(te);
	}

	// **********************
	// After Method Behavior
	// **********************
	@AfterMethod(groups = { "regression" })
	public synchronized void tearDownClass(ITestResult results) {
		test.after_sauceLabs(results, te.getDriver());
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
		
		testName = new Object(){}.getClass().getEnclosingMethod().getName() 
				+ "_" + te.getOperatingSystem()
				+ "_" + te.getBrowserUnderTest()
				+ "_" + te.getBrowserVersion();

		te.setDriver(test.testStart(testName, te));
		outputBrowserOsConfiguration();
		
		//Ensure the home page is loaded
		TestReporter.log("Load the Home Page");
		HomePage homePage = new HomePage(te);
		Assert.assertEquals(homePage.pageLoaded(), true);
	
		//Test the secondary navigation bar functionality
		TestReporter.log("Test the Secondary Navigation Bar Functionality");
		SecondaryNavigation secNav = new SecondaryNavigation(te);
		secNav.navigateAllSecondaryNavigationTabs();
	}
	
	private void outputBrowserOsConfiguration(){
		TestReporter.log("****************************", true);
		TestReporter.log("* Browser/OS Configuration *", true);
		TestReporter.log("****************************", true);
		TestReporter.log("Operating System: " + te.getOperatingSystem(), true);
		TestReporter.log("Browser: " + te.getBrowserUnderTest(), true);
		TestReporter.log("Browser Version: " + te.getBrowserVersion(), true);
		TestReporter.log("Default Test Timeout: " + te.getDefaultTestTimeout(), true);
		TestReporter.log("****************************", true);
		TestReporter.log("****************************", true);
		TestReporter.log("****************************", true);
	}
}
