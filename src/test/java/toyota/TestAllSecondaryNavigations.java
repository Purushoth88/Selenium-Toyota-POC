package toyota;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.json.simple.JSONArray;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import apps.toyota.homePage.HomePage;
import apps.toyota.mainNavigation.MainNavigation;
import apps.toyota.secondaryNavigation.SecondaryNavigation;

import com.orasi.utils.Base64Coder;
import com.orasi.utils.Constants;
import com.orasi.utils.TestReporter;
import com.orasi.utils.Screenshot;
import com.orasi.utils.WebDriverSetup;
import com.orasi.utils.dataProviders.ExcelDataProvider;
import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.saucerest.SauceREST;
import com.saucelabs.testng.SauceOnDemandTestListener;

@Listeners({SauceOnDemandTestListener.class})
public class TestAllSecondaryNavigations {
		private String application = "";
		private String browserUnderTest = "";
		private String browserVersion = "";
		private String operatingSystem = "";
		private String runLocation = "";
		private String environment = "";
		private Map<String, WebDriver> drivers = new HashMap<String, WebDriver>();
		private static ResourceBundle appURLRepository = ResourceBundle.getBundle(Constants.ENVIRONMENT_URL_PATH);
	    /**
	     * Constructs a {@link com.saucelabs.common.SauceOnDemandAuthentication} instance using the supplied user name/access key.  To use the authentication
	     * supplied by environment variables or from an external file, use the no-arg {@link com.saucelabs.common.SauceOnDemandAuthentication} constructor.
	     */
		public SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication(
				Base64Coder.decodeString(appURLRepository.getString("SAUCELABS_USERNAME")),
				Base64Coder.decodeString(appURLRepository.getString("SAUCELABS_KEY")));

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
					
			ResourceBundle appURLRepository = ResourceBundle.getBundle(Constants.ENVIRONMENT_URL_PATH);
			SauceREST client = new SauceREST(
					Base64Coder.decodeString(appURLRepository.getString("SAUCELABS_USERNAME")),
					Base64Coder.decodeString(appURLRepository.getString("SAUCELABS_KEY")));
	        Map<String, Object> updates = new HashMap<String, Object>();
	        updates.put("name",test.getMethod().getMethodName());
	        
			// if is a failure, then take a screenshot
			if (test.getStatus() == ITestResult.FAILURE) {
				new Screenshot().takeScreenShot(test, driver);
				updates.put("passed", false);
			}else{
				updates.put("passed", true);
			}
				
	        JSONArray tags = new JSONArray();
	        String[] groups = test.getMethod().getGroups();
	        for (int x = 0 ; x < groups.length ; x++){tags.add(groups[x]);}
	        updates.put("tags", tags);
	        client.updateJobInfo(((RemoteWebDriver) driver).getSessionId().toString(), updates);
	        System.out.println(client.getJobInfo(((RemoteWebDriver) driver).getSessionId().toString()));
			
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
		@Test(groups = { "regression" })
		public void testAllSecondaryNavigations() throws InterruptedException, IOException {
			
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			//Uncomment the following line to have TestReporter outputs output to the console
			TestReporter.setPrintToConsole(true);
			WebDriverSetup setup = new WebDriverSetup(application,
					browserUnderTest, browserVersion, operatingSystem, runLocation,
					environment, testName);
			WebDriver driver = setup.initialize();
			
			System.out.println(testName);
			drivers.put(testName, driver);

			//Ensure the home page is loaded
			TestReporter.log("Load the Home Page");
			HomePage homePage = new HomePage(driver);
			Assert.assertEquals(homePage.pageLoaded(), true);
		
			//Test the secondary navigation bar functionality
			TestReporter.log("Test the Secondary Navigation Bar Functionality");
			SecondaryNavigation secNav = new SecondaryNavigation(driver);
			secNav.navigateAllSecondaryNavigationTabs();
		}
}
