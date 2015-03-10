package toyota;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.orasi.utils.Constants;
import com.orasi.utils.TestReporter;
import com.orasi.utils.Screenshot;
import com.orasi.utils.WebDriverSetup;
import com.orasi.utils.dataProviders.ExcelDataProvider;

public class ChangeZipCode {

	private String application = "";
	private String browserUnderTest = "";
	private String browserVersion = "";
	private String operatingSystem = "";
	private String runLocation = "";
	private String environment = "";
	private Map<String, WebDriver> drivers = new HashMap<String, WebDriver>();

	@DataProvider(name = "dataScenario")
	public Object[][] scenarios() {
		return new ExcelDataProvider(Constants.TOYOTA_DATAPROVIDER_PATH
				+ "ChangeZipCode.xlsx", "ChangeZipCode").getTestData();
	}

	@BeforeTest(groups = { "regression" })
	@Parameters({ "runLocation", "browserUnderTest", "browserVersion",
			"operatingSystem", "environment" })
	public void setup(@Optional String runLocation, String browserUnderTest,
			String browserVersion, String operatingSystem, String environment) {
		this.application = "Toyota";
		this.runLocation = runLocation;
		this.browserUnderTest = browserUnderTest;
		this.browserVersion = browserVersion;
		this.operatingSystem = operatingSystem;
		this.environment = environment;
	}

	@AfterMethod(groups = { "regression" })
	public synchronized void closeSession(ITestResult test) {
		System.out.println(test.getMethod().getMethodName());
		WebDriver driver = drivers.get(test.getMethod().getMethodName());

		// if is a failure, then take a screenshot
		if (test.getStatus() == ITestResult.FAILURE) {
			new Screenshot().takeScreenShot(test, driver);
		}
		driver.quit();
	}

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
	public void testChangeZipCode(
			String testScenario, String zipCode) throws InterruptedException, IOException {
		
		String testName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		//Uncomment the following line to have TestReporter outputs output to the console
		//TestReporter.setPrintToConsole(true);
		WebDriverSetup setup = new WebDriverSetup(application,
				browserUnderTest, browserVersion, operatingSystem, runLocation,
				environment);
		WebDriver driver = setup.initialize();
		
		System.out.println(testName);
		drivers.put(testName, driver);

		TestReporter.logScenario(testScenario);

	}
}