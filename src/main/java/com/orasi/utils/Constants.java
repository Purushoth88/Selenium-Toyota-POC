package com.orasi.utils;

import java.io.File;
import java.util.Calendar;


public class Constants {

	final static public int CURRENT_YEAR = Calendar.getInstance().get(Calendar.YEAR);
    final static public int CURRENT_MONTH = Calendar.getInstance().get(Calendar.MONTH);
    final static public int CURRENT_DAY = Calendar.getInstance().get(Calendar.DATE);
	
    /** Location of the environment URLs properties file */
    final static public String ENVIRONMENT_URL_PATH = "EnvironmentURLs";
   		
    final static public String TOYOTA_DATAPROVIDER_PATH = "/apps/toyota/dataProviders/";
    
    /** Location of the user credentials properties file */
    final static public String USER_CREDENTIALS_PATH = "UserCredentials";
    
    final static public String SANDBOX_PATH = "/sandbox/";
    
    /** Location of drivers in project */
    final static public String DRIVERS_PATH_LOCAL = "/drivers/";
    final static public String DRIVERS_PATH_REMOTE = "C:\\Selenium\\WebDrivers\\";
    
    /** Location of tnsnames in project */
    final static public String TNSNAMES_PATH = "/database/";
    		
    /** An alias for File.separator */
    final static public String DIR_SEPARATOR = File.separator;
    
    /** The current path of the project */ 
    final static public String CURRENT_DIR = determineCurrentPath();
		
    /** The global system property line.separator */
    final static public String LINE_SEPARATOR = System.getProperty("line.separator", "\n");
    
    /** An alias for the global system property line.separator */
    final static public String NEW_LINE = LINE_SEPARATOR;
    
    /** The default timeout in seconds, should be a generous default time */
    final static public int DEFAULT_GLOBAL_DRIVER_TIMEOUT = 20;
    
    /** The timeout (seconds) for finding web elements on a page, shouldn't be too long */
    final static public int ELEMENT_TIMEOUT = 10;
    
    /** The timeout (seconds) for page/DOM/transitions, should also be a generous */
    final static public int PAGE_TIMEOUT = 60;
    
    /**************************
    /**************************
    /   SAUCE LABS CONSTANTS
    /**************************
    /**************************
    /** The timeout (seconds) for the Selenium server to execute a command; default is 300 seconds*/
    final static public int COMMAND_TIMEOUT = 120;
    
    /** The timeout (seconds) for the SauceLabs waiting on a command from the Selenium script
     * https://docs.saucelabs.com/reference/troubleshooting-common-error-messages/#test-did-not-see-a-new-command-for-90-seconds-timing-out
     */
    final static public int IDLE_TIMEOUT = 20;
    
    /** System properties */

    public static final String APPLICATION_UNDER_TEST = "selenium.applicationUnderTest";
    public static final String BROWSER = "selenium.browser";
    public static final String BROWSER_VERSION = "selenium.browserVersion";
    public static final String OPERATING_SYSTEM = "selenium.OS";
    public static final String RUN_LOCATION = "selenium.runLocation";
    public static final String SELENIUM_HUB_URL = "selenium.hubUrl";
    public static final String TEST_DRIVER_TIMEOUT = "selenium.testDriverTimeout";
    public static final String TEST_ENVIRONMENT = "selenium.testEnvironment";
    
	/**
     * Defaults to "./" if there's an exception of any sort.
     * @warning Exceptions are swallowed.
     * @return Constants.DIR_SEPARATOR
     */
    final private static String determineCurrentPath() {
        try {
            return (new File(".").getCanonicalPath()) + Constants.DIR_SEPARATOR; 
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "." + Constants.DIR_SEPARATOR;
    }



}
