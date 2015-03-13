package toyota;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.junit.runner.Description;

import com.saucelabs.saucerest.SauceREST;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.junit.rules.TestWatcher;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.IReporter;
import org.testng.ITestNGListener;
import org.testng.ITestNGListenerFactory;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

import apps.toyota.homePage.HomePage;
import apps.toyota.mainNavigation.MainNavigation;

import com.mysql.jdbc.Statement;
import com.orasi.api.restServices.SeleniumJobInfo;
import com.orasi.api.restServices.core.RestService;
import com.orasi.utils.Base64Coder;
import com.orasi.utils.Constants;
import com.orasi.utils.TestReporter;
import com.orasi.utils.Screenshot;
import com.orasi.utils.WebDriverSetup;
import com.orasi.utils.dataProviders.ExcelDataProvider;
import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.common.Utils;
import com.saucelabs.junit.SauceOnDemandTestWatcher;

public class ChangeZipCode{
	private String application = "";
	private String browserUnderTest = "";
	private String browserVersion = "";
	private String operatingSystem = "";
	private String runLocation = "";
	private String environment = "";
	private Map<String, WebDriver> drivers = new HashMap<String, WebDriver>();   
    
    //**************
    // Data Provider
    //**************
	@DataProvider(name = "dataScenario")
	public Object[][] scenarios() {
		return new ExcelDataProvider(Constants.TOYOTA_DATAPROVIDER_PATH
				+ "ChangeZipCode.xlsx", "ChangeZipCode").getTestData();
	}

	//*********************
	// Before-Test Behavior 
	//*********************
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

	//**********************
	// After Method Behavior 
	//**********************
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
		RestService restService = new RestService();
		try {
			restService.sendGetRequest(SeleniumJobInfo.lastBuildURL);
		} catch (IOException e) {
			/// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SeleniumJobInfo job;
		try {
			job = restService.mapJSONToObject(SeleniumJobInfo.class);
		
        JSONArray tags = new JSONArray();
        String[] groups = test.getMethod().getGroups();
        for (int x = 0 ; x < groups.length ; x++){tags.add(groups[x]);}
        updates.put("tags", tags);
        System.out.println( job.getUrl().replace("perrybox:8080", "jenkins.orasi.com"));
        updates.put("build", job.getUrl().replace("perrybox:8080", "jenkins.orasi.com"));
        client.updateJobInfo(((RemoteWebDriver) driver).getSessionId().toString(), updates);
        System.out.println(client.getJobInfo(((RemoteWebDriver) driver).getSessionId().toString()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(driver != null && driver.getWindowHandles().size() > 0){
			driver.quit();
		}
	}

	//*****
	// TEST
	//*****
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
		TestReporter.setPrintToConsole(true);
		
		//WebDriverSetup.setSeleniumHubURL("http://" + authentication.getUsername() + ":" + authentication.getAccessKey() + "@ondemand.saucelabs.com:80/wd/hub");
		WebDriverSetup setup = new WebDriverSetup(application,  browserUnderTest, browserVersion, operatingSystem, runLocation,  environment, testName);
		WebDriver driver = setup.initialize();
		System.out.println(WebDriverSetup.getSeleniumHubURL());
		
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