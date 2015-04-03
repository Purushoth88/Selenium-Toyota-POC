package toyota;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import apps.toyota.homePage.HomePage;
import apps.toyota.secondaryNavigation.SecondaryNavigation;

import com.orasi.utils.TestNgTestClassMethods;
import com.orasi.utils.TestReporter;
import com.orasi.utils.WebDriverSetup;
import com.saucelabs.testng.SauceOnDemandTestListener;

@Listeners({SauceOnDemandTestListener.class})
public class TestAllSecondaryNavigations_WithTestNgMethods {
	String testName = null;
	
	private TestNgTestClassMethods test;
	private WebDriver driver;
	
	// *********************
	// Before-Test Behavior
	// *********************
	@BeforeTest(groups = { "regression" })
	@Parameters({ "runLocation", "browserUnderTest", "browserVersion",
			"operatingSystem", "environment" })
	public void setupClass(String runLocation, String browserUnderTest,
			String browserVersion, String operatingSystem, String environment) {
		test = new TestNgTestClassMethods("Toyota");
		test.before(runLocation, browserUnderTest, browserVersion, operatingSystem, environment);
	}

	// **********************
	// After Method Behavior
	// **********************
	@AfterMethod(groups = { "regression" })
	public synchronized void tearDownClass(ITestResult results) {
		test.after(results, driver);
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
				+ "_" + test.getOperatingSystem()
				+ "_" + test.getBrowserUnderTest()
				+ "_" + test.getBowserVersion();

		driver = test.testStart(testName);
		
		outputBrowserOsConfiguration();
		
		//Ensure the home page is loaded
		TestReporter.log("Load the Home Page");
		HomePage homePage = new HomePage(driver);
		Assert.assertEquals(homePage.pageLoaded(), true);
	
		//Test the secondary navigation bar functionality
		TestReporter.log("Test the Secondary Navigation Bar Functionality");
		SecondaryNavigation secNav = new SecondaryNavigation(driver);
		secNav.navigateAllSecondaryNavigationTabs();
	}
	
	private void outputBrowserOsConfiguration(){
		TestReporter.log("****************************", true);
		TestReporter.log("* Browser/OS Configuration *", true);
		TestReporter.log("****************************", true);
		TestReporter.log("Operating System: " + WebDriverSetup.getOperatingSystem(), true);
		TestReporter.log("Browser: " + WebDriverSetup.getBrowserUnderTest(), true);
		TestReporter.log("Browser Version: " + WebDriverSetup.getBrowserVersion(), true);
		TestReporter.log("Default Test Timeout: " + WebDriverSetup.getDefaultTestTimeout(), true);
		TestReporter.log("****************************", true);
		TestReporter.log("****************************", true);
		TestReporter.log("****************************", true);
	}
}
