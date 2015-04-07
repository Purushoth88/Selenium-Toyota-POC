package toyota;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import apps.toyota.homePage.HomePage;
import apps.toyota.mainNavigation.MainNavigation;

import com.orasi.utils.Constants;
import com.orasi.utils.TestEnvironment;
import com.orasi.utils.TestNgTestClassMethods;
import com.orasi.utils.TestReporter;
import com.orasi.utils.dataProviders.ExcelDataProvider;
import com.saucelabs.testng.SauceOnDemandTestListener;

@Listeners({ SauceOnDemandTestListener.class })
public class ChangeZipCode {
	String testName = "";
	String application = "Toyota";
	private TestEnvironment te = null;
	private TestNgTestClassMethods test;

	// **************
	// Data Provider
	// **************
	@DataProvider(name = "dataScenario")
	public Object[][] scenarios() {
		Object[][] excelData = new ExcelDataProvider(
				Constants.TOYOTA_DATAPROVIDER_PATH + "ChangeZipCode.xlsx", "ChangeZipCode").getTestData();
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

	// **********************
	// After Method Behavior
	// **********************
	@AfterMethod(groups = { "regression" })
	public synchronized void tearDownClass(ITestResult results) {
		test.after_sauceLabs(results, te.getDriver());
	}

	// *****
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
	@Test(dataProvider = "dataScenario", groups = { "regression" }, singleThreaded=true )
	public void testChangeZipCode(String testScenario, String zipCode)
			throws InterruptedException, IOException {

		testName = new Object(){}.getClass().getEnclosingMethod().getName() 
				+ "_" + te.getOperatingSystem()
				+ "_" + te.getBrowserUnderTest()
				+ "_" + te.getBrowserVersion();

		//driver = test.testStart(testName, te);
		te.setDriver(test.testStart(testName, te));
		outputBrowserOsConfiguration();
		
		// Ensure the home page is loaded
		TestReporter.logScenario(testScenario);
		HomePage homePage = new HomePage(te);
		Assert.assertEquals(te.pageLoaded(), true);

		// Change the zipcode
		MainNavigation mainNav = new MainNavigation(te);
		mainNav.changeZipCodes(zipCode);
	}
	
	private void outputBrowserOsConfiguration(){
		Reporter.log("****************************", true);
		Reporter.log("* Browser/OS Configuration *", true);
		Reporter.log("****************************", true);
		Reporter.log("Operating System: " + te.getOperatingSystem(), true);
		Reporter.log("Browser: " + te.getBrowserUnderTest(), true);
		Reporter.log("Browser Version: " + te.getBrowserVersion(), true);
		Reporter.log("Default Test Timeout: " + te.getDefaultTestTimeout(), true);
		Reporter.log("****************************", true);
		Reporter.log("****************************", true);
		Reporter.log("****************************", true);
	}
}
