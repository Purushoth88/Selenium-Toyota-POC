package toyota;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.json.simple.JSONArray;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
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
public class TestAllSecondaryNavigations_Parallel {
	private String application = "";
	private String browserUnderTest = "";
	private String browserVersion = "";
	private String operatingSystem = "";
	private String runLocation = "";
	private String environment = "";
	String testName = "";
	private Map<String, WebDriver> drivers = new HashMap<String, WebDriver>();
	
	private static ResourceBundle appURLRepository = ResourceBundle.getBundle(Constants.ENVIRONMENT_URL_PATH);
	
	WebDriver driver = null;
	WebDriverSetup setup = null;
	
	 private ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
	 private ThreadLocal<String> sessionId = new ThreadLocal<String>();
	public SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication(
			appURLRepository.getString("SAUCELABS_USERNAME"),
			appURLRepository.getString("SAUCELABS_KEY"));

	//**********************
	// After Method Behavior 
	//**********************
	@AfterMethod(groups = { "parallelDataGroups" })
	public synchronized void closeSession(ITestResult test) {
		System.out.println(test.getMethod().getMethodName());
		WebDriver driver = drivers.get(test.getMethod().getMethodName());	
	
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
        
        if(runLocation.equalsIgnoreCase("remote")){
    		ResourceBundle appURLRepository = ResourceBundle.getBundle(Constants.ENVIRONMENT_URL_PATH);
    		SauceREST client = new SauceREST(
    				Base64Coder.decodeString(appURLRepository.getString("SAUCELABS_USERNAME")),
    				Base64Coder.decodeString(appURLRepository.getString("SAUCELABS_KEY")));
        	client.updateJobInfo(((RemoteWebDriver) driver).getSessionId().toString(), updates);
        	System.out.println(client.getJobInfo(((RemoteWebDriver) driver).getSessionId().toString()));	
        }
		
		if(driver != null && driver.getWindowHandles().size() > 0){
			driver.quit();
		}
	}
	
	//*****
	// TEST
	//*****
    @DataProvider(name = "hardCodedBrowsers", parallel = true)
    public static Object[][] sauceBrowserDataProvider(Method testMethod) {
        return new Object[][]{
        		new Object[]{"safari", "8", "OSX 10.10"},
        		new Object[]{"chrome", "", "OSX 10.9"},
        		new Object[]{"firefox", "", "OSX 10.9"},
        		new Object[]{"safari", "7", "OSX 10.9"},
                new Object[]{"chrome", "", "Windows 8.1"},
                new Object[]{"firefox", "", "Windows 8.1"},
                new Object[]{"iexplore", "11", "Windows 8.1"},
                new Object[]{"chrome", "", "Windows 7"},
                new Object[]{"firefox", "", "Windows 7"},
                new Object[]{"iexplore", "11", "Windows 7"},
                new Object[]{"iexplore", "8", "Windows 7"}
        };
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
	@Test(groups = { "parallelDataGroups" }, dataProvider="hardCodedBrowsers")
	public void testAllSecondaryNavigations(
			String browser, String version, String os) throws InterruptedException, IOException {
		getIp();
		
		String testName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		//Uncomment the following line to have TestReporter outputs output to the console
		TestReporter.setPrintToConsole(true);
		
//		WebDriverSetup setup = new WebDriverSetup(application,
//				browserUnderTest, browserVersion, operatingSystem, runLocation,
//				environment, testName);
//		WebDriverSetup setup = new WebDriverSetup(application,
//				browser, version, os, "remote",
//				environment, testName);
//		WebDriver driver = setup.initialize();
		
		WebDriver driver = createDriver(browser, version, os);
		driver.get("http://www.toyota.com/");
		
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
	
	private void localHostIpAddress() throws UnknownHostException{
		InetAddress addr = InetAddress.getLocalHost();
		String ipAddress = addr.getHostAddress();
	      
        System.out.println("IP address of localhost from Java Program: " + ipAddress);
      
        //Hostname
        String hostname = addr.getHostName();
        System.out.println("Name of hostname : " + hostname);
	}
	
	public void getIp() throws UnknownHostException{
	    String ipAddress = null;
	    String hostName = null;
	    Enumeration<NetworkInterface> net = null;
	    try {
	        net = NetworkInterface.getNetworkInterfaces();
	    } catch (SocketException e) {
	        throw new RuntimeException(e);
	    }

	    while(net.hasMoreElements()){
	        NetworkInterface element = net.nextElement();
	        Enumeration<InetAddress> addresses = element.getInetAddresses();
	        while (addresses.hasMoreElements()){
	            InetAddress ip = addresses.nextElement();
	            if (ip instanceof Inet4Address){

	                if (ip.isSiteLocalAddress()){

	                    ipAddress = ip.getHostAddress();
	                    hostName = ip.getHostName();
	                }

	            }

	        }
	    }
	    
	    System.out.println("Local Host IPv4 Address: " + ipAddress);
	    System.out.println("Local Host Name: " + hostName);
	    localHostIpAddress();
	}
	
    private WebDriver createDriver(String browser, String version, String os) throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, browser);
        if (version != null) {
            capabilities.setCapability(CapabilityType.VERSION, version);
        }
        capabilities.setCapability(CapabilityType.PLATFORM, os);
        capabilities.setCapability("name", "Sauce Sample Test");
        webDriver.set(new RemoteWebDriver(
                new URL("http://" + authentication.getUsername() + ":" + authentication.getAccessKey() + "@ondemand.saucelabs.com:80/wd/hub"),
                capabilities));
        sessionId.set(((RemoteWebDriver) getWebDriver()).getSessionId().toString());
        return webDriver.get();
    }
    
    public WebDriver getWebDriver() {
        System.out.println("WebDriver" + webDriver.get());
        return webDriver.get();
    }
}
