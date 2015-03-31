package com.orasi.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.NotConnectedException;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;

import com.saucelabs.common.SauceOnDemandAuthentication;

//public class WebDriverSetup implements SauceOnDemandSessionIdProvider{
public class WebDriverSetup{
    
	public WebDriver driver;
	//private String browserVersion = System.getProperty(Constants.BROWSER_VERSION);
	
	private ResourceBundle appURLRepository = ResourceBundle.getBundle(Constants.ENVIRONMENT_URL_PATH);
	private String seleniumHubURL = "http://"
			+ Base64Coder.decodeString(appURLRepository.getString("SAUCELABS_USERNAME"))
			+ ":"
			+ Base64Coder.decodeString(appURLRepository.getString("SAUCELABS_KEY"))
			+ "@ondemand.saucelabs.com:80/wd/hub";
	
	//***********
	// Sauce Labs
	//***********
    /**
     * Constructs a {@link com.saucelabs.common.SauceOnDemandAuthentication} instance using the supplied user name/access key.  To use the authentication
     * supplied by environment variables or from an external file, use the no-arg {@link com.saucelabs.common.SauceOnDemandAuthentication} constructor.
     */
	public SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication(
			Base64Coder.decodeString(appURLRepository.getString("SAUCELABS_USERNAME")),
			Base64Coder.decodeString(appURLRepository.getString("SAUCELABS_KEY")));

    /**
     * ThreadLocal variable which contains the  {@link WebDriver} instance which is used to perform browser interactions with.
     */
    private ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();

    /**
     * ThreadLocal variable which contains the Sauce Job Id.
     */
    private ThreadLocal<String> sessionId = new ThreadLocal<String>();
    
    
    
	
	//Define a variable to house the Linux OS username
	private String username = "";
		
	public WebDriverSetup(){}

	public WebDriverSetup(	String application, String browserUnderTest, 
							String browserVersion, String operatingSystem,
							String runLocation, String environment, String testName){
	    setTestApplication(application);
		setBrowserUnderTest(browserUnderTest);
		setBrowserVersion(browserVersion);
		setOperatingSystem(operatingSystem);
		setRunLocation(runLocation);
		setTestEnvironment(environment);
		setSeleniumHubURL(seleniumHubURL);
		setTestName(testName);
		//verifyExpectedAndActualOS();
	}
	
	//Getters & Setters
	public static void setTestEnvironment(String environment){ System.setProperty(Constants.TEST_ENVIRONMENT,environment);	}	
	public static String getTestEnvironment(){ return System.getProperty(Constants.TEST_ENVIRONMENT);}

	public static void setTestApplication(String application){System.setProperty(Constants.APPLICATION_UNDER_TEST,application);}
	public static String getTestApplication(){return System.getProperty(Constants.APPLICATION_UNDER_TEST);}

	public static String getOperatingSystem() {return System.getProperty(Constants.OPERATING_SYSTEM);}
	public static void setOperatingSystem(String operatingSystem) {	
		if(operatingSystem.equalsIgnoreCase("jenkinsParameter")){
			System.setProperty(Constants.OPERATING_SYSTEM , System.getProperty("jenkinsOperatingSystem"));
		}else{
			System.setProperty(Constants.OPERATING_SYSTEM , operatingSystem);	
		}
	} 

	public static void setBrowserUnderTest(String browser) {
		if(browser.equalsIgnoreCase("jenkinsParameter")){
			System.setProperty(Constants.BROWSER, System.getProperty("jenkinsBrowser"));
		}else{
			System.setProperty(Constants.BROWSER, browser);	
		}
	}	
	public static String getBrowserUnderTest(){return System.getProperty(Constants.BROWSER);}
	
	public static String getBrowserVersion() {return System.getProperty(Constants.BROWSER_VERSION);}
	public static void setBrowserVersion(String browserVersion) {
		if(browserVersion.equalsIgnoreCase("jenkinsParameter")){
			System.setProperty(Constants.BROWSER_VERSION, System.getProperty("jenkinsBrowserVersion"));
		}else{
			System.setProperty(Constants.BROWSER_VERSION, browserVersion);	
		}
	}

	public static void setDefaultTestTimeout(int timeout){System.setProperty(Constants.TEST_DRIVER_TIMEOUT, Integer.toString(timeout));}
	public static int getDefaultTestTimeout(){return Integer.parseInt(System.getProperty(Constants.TEST_DRIVER_TIMEOUT));}
	
	public static String getRunLocation() {	return System.getProperty(Constants.RUN_LOCATION);}
	public static void setRunLocation(String location) {System.setProperty(Constants.RUN_LOCATION, location);}
	
	public static String getSeleniumHubURL() { return System.getProperty(Constants.SELENIUM_HUB_URL);}
	public static void setSeleniumHubURL(String url) {System.setProperty(Constants.SELENIUM_HUB_URL, url);}	
	
	public static void setTestName(String testName){ System.setProperty("selenium.testName", testName);	}	
	public static String getTestName(){ return System.getProperty("selenium.testName");}
	
	public void setDriver(WebDriver driverSession){driver = driverSession;}	
	public WebDriver getDriver(){return driver;}	
	
	public ResourceBundle getEnvironmentURLRepository(){return appURLRepository;}

	/**
	 * Initializes the webdriver, sets up the run location, driver type,
	 * launches the application.
	 * 
	 * @param	None
	 * @version	12/16/2014
	 * @author 	Jessica Marshall
	 * @return 	the web driver
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public WebDriver initialize() throws InterruptedException, IOException{
		driverSetup();
		launchApplication();
		return this.driver;
	}
	
	
	/**
	 * Launches the application under test.  Gets the URL from an environment properties file
	 * based on the application.  
	 * 
	 * @param	None
	 * @version	12/16/2014
	 * @author 	Justin Phlegar
	 * @return 	Nothing
	 */
	public void launchApplication(){
		if(getTestEnvironment().isEmpty()){
			driver.get(appURLRepository.getString(getTestApplication().toUpperCase()));
		}else{
			driver.get(appURLRepository.getString(getTestApplication().toUpperCase() + "_" + getTestEnvironment().toUpperCase()));	
		}
	}
	
	/**
	 * Sets up the driver type, location, browser under test 
	 * 
	 * @param	None
	 * @version	12/16/2014
	 * @author 	Justin Phlegar
	 * @return 	Nothing 
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public void driverSetup() throws InterruptedException, IOException, NotConnectedException{

		driver = null;

		//If the location is local, grab the drivers for each browser type from within the project
		if (getRunLocation().equalsIgnoreCase("local")){
			DesiredCapabilities caps = null;
			File file = null;
			switch (getOperatingSystem().toLowerCase().trim().replace(" ", "")) {
			case "windows":
				if (getBrowserUnderTest().equalsIgnoreCase("Firefox") || getBrowserUnderTest().equalsIgnoreCase("FF")){
			    	driver = new FirefoxDriver();	    	
			    }
				//Internet explorer
			    else if(getBrowserUnderTest().equalsIgnoreCase("IE") || getBrowserUnderTest().replace(" ", "").equalsIgnoreCase("internetexplorer")){
			    	caps = DesiredCapabilities.internetExplorer();
			    	caps.setCapability("ignoreZoomSetting", true);
			    	caps.setCapability("enablePersistentHover", false);
			    	file = new File(this.getClass().getResource(Constants.DRIVERS_PATH_LOCAL + "IEDriverServer.exe").getPath());
					System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
					driver = new InternetExplorerDriver(caps);
			    }
				//Chrome
			    else if(getBrowserUnderTest().equalsIgnoreCase("Chrome")){
			    	file = new File(this.getClass().getResource(Constants.DRIVERS_PATH_LOCAL + "ChromeDriver.exe").getPath());
					System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
					driver = new ChromeDriver();		    	
			    }
				//Headless - HTML unit driver
			    else if(getBrowserUnderTest().equalsIgnoreCase("html")){	    	
					driver = new HtmlUnitDriver(true);		    	
			    }
				//Safari
			    else if(getBrowserUnderTest().equalsIgnoreCase("safari")){
			    	driver = new SafariDriver();
			    }
			    else {
			    	throw new RuntimeException("Parameter not set for browser type");
			    }
				break;
			case "mac":case "macos":
				if (getBrowserUnderTest().equalsIgnoreCase("Firefox") || getBrowserUnderTest().equalsIgnoreCase("FF")){
			    	driver = new FirefoxDriver();	    	
			    }
				//Internet explorer
			    else if(getBrowserUnderTest().equalsIgnoreCase("IE") || getBrowserUnderTest().replace(" ", "").equalsIgnoreCase("internetexplorer")){
			    	throw new RuntimeException("Currently there is no support for IE with Mac OS.");
			    }
				//Chrome
			    else if(getBrowserUnderTest().equalsIgnoreCase("Chrome")){
			    	file = new File(this.getClass().getResource(Constants.DRIVERS_PATH_LOCAL + "mac/chromedriver").getPath());
			    	System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
					try{
						//Ensure the permission on the driver include executable permissions
						Process proc = Runtime.getRuntime().exec(new String[]{"/bin/bash","-c","chmod 777 " + file.getAbsolutePath()});
						proc.waitFor();									
						driver = new ChromeDriver();
					}catch(IllegalStateException ise){
						ise.printStackTrace();
						throw new IllegalStateException("This has been seen to occur when the chromedriver file does not have executable permissions. In a terminal, navigate to the directory to which Maven pulls the drivers at runtime (e.g \"/target/classes/drivers/\") and execute the following command: chmod +rx chromedriver");
					}catch(IOException ioe){
						ioe.printStackTrace();
					}catch(InterruptedException ie){
						ie.printStackTrace();
					}
			    }
				//Headless - HTML unit driver
			    else if(getBrowserUnderTest().equalsIgnoreCase("html")){	    	
					driver = new HtmlUnitDriver(true);		    	
			    }
				//Safari
			    else if(getBrowserUnderTest().equalsIgnoreCase("safari")){
			    	driver = new SafariDriver();
			    }
			    else {
			    	throw new RuntimeException("Parameter not set for browser type");
			    }
				break;
			default:
				break;
			}			
		
		//Code for running on the selenium grid
		}else if(getRunLocation().equalsIgnoreCase("remote")){
	        DesiredCapabilities capabilities = new DesiredCapabilities();
	        capabilities.setCapability(CapabilityType.BROWSER_NAME, getBrowserUnderTest());
	        if (getBrowserVersion() != null) {
	            capabilities.setCapability(CapabilityType.VERSION, getBrowserVersion());
	        }
	        capabilities.setCapability(CapabilityType.PLATFORM, getOperatingSystem());
	        if(getBrowserUnderTest().toLowerCase().contains("ie") || 
	        		getBrowserUnderTest().toLowerCase().contains("iexplore")){
	        	capabilities.setCapability("ignoreZoomSetting", true);
		    }
	        capabilities.setCapability("name", getTestName());
	        webDriver.set(new RemoteWebDriver(
	                new URL("http://" + authentication.getUsername() + ":" + authentication.getAccessKey() + "@ondemand.saucelabs.com:80/wd/hub"),
	                capabilities));
	        sessionId.set(((RemoteWebDriver) getWebDriver()).getSessionId().toString());
			driver = webDriver.get();
		}else{
			throw new RuntimeException("Parameter for run [Location] was not set to 'Local' or 'Remote'");
		}
		
		driver.manage().timeouts().setScriptTimeout(Constants.DEFAULT_GLOBAL_DRIVER_TIMEOUT, TimeUnit.SECONDS).implicitlyWait(Constants.DEFAULT_GLOBAL_DRIVER_TIMEOUT, TimeUnit.SECONDS);	
		setDefaultTestTimeout(Constants.DEFAULT_GLOBAL_DRIVER_TIMEOUT);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
	}

    /**
     * @return the {@link WebDriver} for the current thread
     */
    public WebDriver getWebDriver() {
        System.out.println("WebDriver" + webDriver.get());
        return webDriver.get();
    }
}