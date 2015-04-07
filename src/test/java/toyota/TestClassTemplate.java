package toyota;

import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.orasi.utils.Constants;
import com.orasi.utils.TestEnvironment;
import com.orasi.utils.TestNgTestClassMethods;
import com.orasi.utils.dataProviders.ExcelDataProvider;
import com.saucelabs.testng.SauceOnDemandTestListener;

@Listeners({SauceOnDemandTestListener.class})
public class TestClassTemplate {
	String testName = "";
	String application = "";
	private TestEnvironment te = null;
	private TestNgTestClassMethods test;

	// **************
	// Data Provider
	// **************
	@DataProvider(name = "dataScenario")
	public Object[][] scenarios() {
		Object[][] excelData = new ExcelDataProvider(
				Constants.TOYOTA_DATAPROVIDER_PATH + "<<<<>>>>.xlsx", "ChangeZipCode").getTestData();
		return excelData;
	}

	// *********************
	// Before-Test Behavior
	// *********************
	@BeforeTest(groups = { "template" })
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
	@AfterMethod(groups = { "template" })
	public synchronized void tearDownClass(ITestResult results) {
		test.after(results, te.getDriver());
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
	@Test(dataProvider = "dataScenario", groups = { "template" }, singleThreaded=true )
	public void testChangeZipCode(String testScenario, String zipCode)
			throws InterruptedException, IOException {

		testName = new Object(){}.getClass().getEnclosingMethod().getName() 
				+ "_" + te.getOperatingSystem()
				+ "_" + te.getBrowserUnderTest()
				+ "_" + te.getBrowserVersion();

		te.setDriver(test.testStart(testName, te));
		
		//**************************************************
		//**************************************************
		//**************************************************
		//	PAGE OBJECT CLASSES GO HERE
		//**************************************************
		//**************************************************
		//**************************************************
	}
}
