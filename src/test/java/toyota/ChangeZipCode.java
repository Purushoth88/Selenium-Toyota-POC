package toyota;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import apps.toyota.homePage.HomePage;
import apps.toyota.mainNavigation.MainNavigation;

import com.orasi.utils.Constants;
import com.orasi.utils.TestReporter;
import com.orasi.utils.Screenshot;
import com.orasi.utils.WebDriverSetup;
import com.orasi.utils.dataProviders.ExcelDataProvider;
import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.junit.SauceOnDemandTestWatcher;

public class ChangeZipCode implements SauceOnDemandSessionIdProvider{

	private String application = "";
	private String browserUnderTest = "";
	private String browserVersion = "";
	private String operatingSystem = "";
	private String runLocation = "";
	private String environment = "";
	private Map<String, WebDriver> drivers = new HashMap<String, WebDriver>();
	
	//****************************
	//  SauceLabs Implememntations
	//****************************
	 /**
     * Constructs a {@link com.saucelabs.common.SauceOnDemandAuthentication} instance using the supplied Sauce
     * user name and access key. To use the authentication supplied by environment variables or
     * from an external file, use the no-arg {@link com.saucelabs.common.SauceOnDemandAuthentication} constructor.
     */
    public SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication("username", "password");
    /**
     * JUnit Rule which marks Sauce Jobs as passed/failed when the test succeeds or fails.
     */
    public @Rule
    SauceOnDemandTestWatcher resultReportingTestWatcher = new SauceOnDemandTestWatcher(this, authentication);
    /**
     * JUnit Rule that records the test name of the current test. When this is referenced
     * during the creation of {@link DesiredCapabilities}, the test method name is assigned
     * to the Sauce Job name and recorded in Jenkins Console Output and in the Sauce Jobs
     * Report in the Jenkins project's home page.
     */
    public @Rule TestName testName = new TestName();
    @Override
    public String getSessionId() {
        return "";
    }
	@DataProvider(name = "dataScenario")
	public Object[][] scenarios() {
		return new ExcelDataProvider(Constants.TOYOTA_DATAPROVIDER_PATH
				+ "ChangeZipCode.xlsx", "ChangeZipCode").getTestData();
	}

	@BeforeTest(groups = { "regression" })
	@Parameters({ "runLocation", "browserUnderTest", "browserVersion",
			"operatingSystem", "environment" })
	public void setup(String runLocation, String browserUnderTest,
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
		
		if(driver != null && driver.getWindowHandles().size() > 0){
			driver.quit();
		}
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
	public void testChangeZipCode (
			String testScenario, String zipCode) throws InterruptedException, IOException {
		
		String testName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		//Uncomment the following line to have TestReporter outputs output to the console
		TestReporter.setPrintToConsole(true);
		
		WebDriverSetup.setSeleniumHubURL("http://" + authentication.getUsername() + ":" + authentication.getAccessKey() + "@ondemand.saucelabs.com:80/wd/hub");
		WebDriverSetup setup = new WebDriverSetup(application,  browserUnderTest, browserVersion, operatingSystem, runLocation,  environment);
		WebDriver driver = setup.initialize();

		
//		WebDriverSetup setup = new WebDriverSetup(application,
//				browserUnderTest, browserVersion, operatingSystem, runLocation,
//				environment);
//		WebDriver driver = setup.initialize();
		
		System.out.println(testName);
		drivers.put(testName, driver);

		//Ensure the home page is loaded
		TestReporter.logScenario(testScenario);
		HomePage homePage = new HomePage(driver);
		Assert.assertEquals(homePage.pageLoaded(), true);
	
		//Change the zipcode
		MainNavigation mainNav = new MainNavigation(driver);
		mainNav.changeZipCodes(zipCode);
	}
}