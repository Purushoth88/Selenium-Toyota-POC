package toyota;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
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
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import apps.toyota.homePage.HomePage;
import apps.toyota.mainNavigation.MainNavigation;

import com.orasi.utils.Base64Coder;
import com.orasi.utils.Constants;
import com.orasi.utils.Screenshot;
import com.orasi.utils.TestReporter;
import com.orasi.utils.WebDriverSetup;
import com.orasi.utils.dataProviders.ExcelDataProvider;
import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.saucerest.SauceREST;
import com.saucelabs.testng.SauceOnDemandTestListener;

@Listeners({SauceOnDemandTestListener.class})
public class ChangeZipCode{
	private String application = "";
	private String browserUnderTest = "";
	private String browserVersion = "";
	private String operatingSystem = "";
	private String runLocation = "";
	private String environment = "";
	String testName = "";
	private Map<String, WebDriver> drivers = new HashMap<String, WebDriver>(); 
	private static ResourceBundle appURLRepository = ResourceBundle.getBundle(Constants.ENVIRONMENT_URL_PATH);
    /**
     * Constructs a {@link com.saucelabs.common.SauceOnDemandAuthentication} instance using the supplied user name/access key.  To use the authentication
     * supplied by environment variables or from an external file, use the no-arg {@link com.saucelabs.common.SauceOnDemandAuthentication} constructor.
     */
	public SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication(
			Base64Coder.decodeString(appURLRepository.getString("SAUCELABS_USERNAME")),
			Base64Coder.decodeString(appURLRepository.getString("SAUCELABS_KEY")));
	
    //**************
    // Data Provider
    //**************
	@DataProvider(name = "dataScenario")
	public Object[][] scenarios() {
		Object[][] excelData = new ExcelDataProvider(Constants.TOYOTA_DATAPROVIDER_PATH
				+ "ChangeZipCode.xlsx", "ChangeZipCode").getTestData();
		return excelData;
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
		
		testName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		//Uncomment the following line to have TestReporter outputs output to the console
		TestReporter.setPrintToConsole(true);
		
		WebDriverSetup setup = new WebDriverSetup(application,  browserUnderTest, browserVersion, operatingSystem, runLocation,  environment, testName);
		WebDriver driver = setup.initialize();
		
		System.out.println(testName);
		drivers.put(testName, driver);
		
		//localHostIpAddress();
		getIp();

		//Ensure the home page is loaded
		TestReporter.logScenario(testScenario);
		HomePage homePage = new HomePage(driver);
		Assert.assertEquals(homePage.pageLoaded(), true);
	
		//Change the zipcode
		MainNavigation mainNav = new MainNavigation(driver);
		mainNav.changeZipCodes(zipCode);
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
}
