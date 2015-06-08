package buyAToyota;

import apps.buyAToyota.homePage.BuyAToyota_main;

import com.orasi.utils.AutomationTest;
import com.orasi.utils.Browser;
import com.orasi.utils.Config;
import com.orasi.utils.Widgets;
import com.orasi.utils.TestEnvironment;
import com.orasi.utils.TestReporter;
import com.saucelabs.testng.SauceOnDemandTestListener;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

@Listeners({ SauceOnDemandTestListener.class, com.orasi.utils.Screenshot.class })
@Config(url = "http://www.buyatoyota.com", browser = Browser.FIREFOX)
public class TestNavBarNavigations_page extends AutomationTest{
	    
	private static WebDriver driver; 
	
	/*
	 * Define a collection of webdrivers and test names inside a Map. This
	 * allows for more than one driver to be used within a test class. This also
	 * allows for a particular driver to be tied to a specific test based on
	 * test name.
	 */
	protected Map<String, WebDriver> drivers = new HashMap<String, WebDriver>();

	// *********************
	// Before-Test Behavior
	// *********************
	@BeforeTest(groups = { "regression" })
	@Parameters({ "runLocation", "browserUnderTest", "browserVersion",
			"operatingSystem", "environment" })
	public static void init() {
		// TODO: Get Driver info from User Inputs 
		driver = new FirefoxDriver();
	}
	
//	public void setupClass(String runLocation, String browserUnderTest,
//			String browserVersion, String operatingSystem, String environment) {
//		setApplicationUnderTest(application);
//		setBrowserUnderTest(browserUnderTest);
//		setBrowserVersion(browserVersion);
//		setOperatingSystem(operatingSystem);
//		setRunLocation(runLocation);
//		setTestEnvironment(environment);
//	}
	
    /**
     * You are able to fire this test right up and see it in action.  Right click the test() method, and click "Run As... jUnit test".
     * 
     * The purpose of this is to show you how you can continue testing, just by taking the semi colon out, and continuing with your test.
     * @throws InterruptedException 
     */
    //@Test
	@Features("General Usage")
	@Stories("As any user, I can use all navigation items on the Navigation Bar")
	@Test(groups = { "regression" }, singleThreaded = true)
    public void NavBarTest() {
   	
		BuyAToyota_main mp = new BuyAToyota_main(driver);
		
		// Ensure the home page is loaded
		TestReporter.assertTrue(mp.pageLoaded().isDomComplete(), "Load the Home Page");
		TestReporter.logScenario("Test the Navigation Bar Functionality");

    	mp.testNavBarNavigation();
//    	click(By.id("fraMain"))  // do this to wait until the frame loads on the page
//    	.switchToFrame("fraMain")
//    	.setText(By.id("idField"), "eedpsc4")
//    	.validateText(By.id("idField"), "eedpsc4")
//    	.setText(By.id("passwordField"), "1")
//    	.click(By.xpath(Widgets.SIGN_IN))
//    	;
//	    	.closeWindow();  
    }
	
	
	// **
	// Zip Code Modal Popup
	// **
	//*[@id="search-value"]
	// *****
	// TEST
	// *****
	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * @Summary: Tests navigation buttons/links
	 * @Precondition:NA
	 * @Author: Waightstill W Avery
	 * @Version: 05/29/2015
	 * @Return: N/A
	 */
//	@Features("General Usage")
//	@Stories("As any user, I can use all navigation items on the Navigation Bar")
//	@Test(groups = { "regression" }, singleThreaded = true)
//	public void testAllNavBarNavigations() {
//		testName = new Object() {
//		}.getClass().getEnclosingMethod().getName() + "_"
//				+ getOperatingSystem() + "_" + getBrowserUnderTest() + "_"
//				+ getBrowserVersion();
//
//		// Start the test and generate a driver
//		testStart(testName);
//
//		// Ensure the home page is loaded
//		TestReporter.assertTrue(pageLoaded().isDomComplete(), "Load the Home Page");
//		TestReporter.logScenario("Test the Navigation Bar Functionality");
//		HomePage homePage = new HomePage(this);
//		homePage.testNavBarNavigation();
//	}

	@AfterMethod(groups = { "regression" })
	public void afterTest(ITestResult test) {
		endSauceTest(test);
	}
}
