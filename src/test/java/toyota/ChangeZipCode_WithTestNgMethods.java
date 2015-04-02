package toyota;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import apps.toyota.homePage.HomePage;
import apps.toyota.mainNavigation.MainNavigation;

import com.orasi.utils.Constants;
import com.orasi.utils.TestNgTestClassMethods;
import com.orasi.utils.TestReporter;
import com.orasi.utils.dataProviders.ExcelDataProvider;
import com.saucelabs.testng.SauceOnDemandTestListener;

@Listeners({ SauceOnDemandTestListener.class })
public class ChangeZipCode_WithTestNgMethods {
	String testName = "";
	
	private TestNgTestClassMethods test;
	private WebDriver driver;

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
	@Test(dataProvider = "dataScenario", groups = { "regression" })
	public void testChangeZipCode(String testScenario, String zipCode)
			throws InterruptedException, IOException {

		testName = new Object() {
		}.getClass().getEnclosingMethod().getName();

		driver = test.testStart(new Object(){}.getClass().getEnclosingMethod().getName());

		// Ensure the home page is loaded
		TestReporter.logScenario(testScenario);
		HomePage homePage = new HomePage(driver);
		Assert.assertEquals(homePage.pageLoaded(), true);

		// Change the zipcode
		MainNavigation mainNav = new MainNavigation(driver);
		mainNav.changeZipCodes(zipCode);
	}
}
