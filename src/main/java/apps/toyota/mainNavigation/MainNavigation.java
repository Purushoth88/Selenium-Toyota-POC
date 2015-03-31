package apps.toyota.mainNavigation;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.orasi.core.interfaces.Button;
import com.orasi.core.interfaces.Element;
import com.orasi.core.interfaces.Textbox;
import com.orasi.core.interfaces.impl.internal.ElementFactory;
import com.orasi.utils.PageLoaded;
import com.orasi.utils.Sleeper;
import com.orasi.utils.TestReporter;
import com.orasi.utils.WebDriverSetup;


/**
 * @summary Contains the methods & objects for the Toyota.com Main Navigation Bar
 * @version Created 03/01/2015
 * @author Waightstill W. Avery
 */
public class MainNavigation {
	// *****************************
	// *** MainNavigation Fields ***
	// *****************************
	String initialZipCode = "";
	String modifiedZipCode = "";
	int timeout = WebDriverSetup.getDefaultTestTimeout();
	int loopCounter = 0;
	
	// *******************************
	// *** MainNavigation Elements ***
	// *******************************
	
	//Your Location button
	@FindBy(xpath = "//*[@id=\"tcom-main-nav\"]/ul/li[3]/button")
	private Button btnYourLocation;
	
	//ZIP Code Popup
	@FindBy(xpath = "//*[@id=\"tcom-nav-zip-flyout\"]/div")
	private Element eleZipCodePopup;
	
	//ZIP Code textbox
	@FindBy(xpath = "//*[@id=\"tcom-nav-zip-flyout\"]/div/div/div[2]/div/input")
	private Textbox txtZipCode;
	
	//Actual ZIP Code Value element
	@FindBy(xpath = "//*[@id=\"tcom-main-nav\"]/ul/li[3]/button/span/span")
	private Element eleZipCode;

	// *********************
	// ** Build page area **
	// *********************
	private WebDriver driver;

	/**
	 * 
	 * @summary Constructor to initialize the page
	 * @version Created 03/01/2015
	 * @author Waightstill W Avery
	 * @param driver
	 * @throws NA
	 * @return NA
	 * 
	 */
	public MainNavigation(WebDriver driver){
		this.driver = driver;	
		ElementFactory.initElements(driver, this);  
	}

	public boolean pageLoaded() {
		return new PageLoaded().isDomComplete(driver);
	}

	public boolean pageLoaded(Element element) {
		return new PageLoaded().isElementLoaded(this.getClass(), driver, element);
	}

	public MainNavigation initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	// ***********************************
	// *** MainNavigation Interactions ***
	// ***********************************

	/**
	 * @summary: clicks on the locations link to allow a new zipcode to be entered
	 * @author: Waightstill W Avery
	 * @param: NA
	 * @return: NA
	 */
	private void clickYourLocation(){
		//Attempt to use the Selenium 'click'
		btnYourLocation.click();
		if(!pageLoaded(eleZipCodePopup)){
			//If the zipcode popup does not load, then try a JavaScript 'click'
			initialize();
			btnYourLocation.jsClick(driver);
			Assert.assertEquals(pageLoaded(eleZipCodePopup), true, "The zip code popup was not displayed.");
		}
	}
	
	/**
	 * @summary: if the zipcode is different than that found in the UI, this method will click the location icon, enter the new zipcode and verify the change is reflected in the UI
	 * @author: Waightstill W Avery
	 * @param: zipCode - String, value to be entered as the new zipcode to use
	 * @return: NA
	 */
	public void changeZipCodes(String zipCode){
		pageLoaded(btnYourLocation);
		//Capture the zipcode that currently exists in the UI
		this.initialZipCode = captureCurrentZipCode();
		TestReporter.log("Initial zip code: ["+initialZipCode+"].");
		//Determine if the existing zipcode in the UI is the same as the zipcode to be used in the test
		if(!initialZipCode.equalsIgnoreCase(zipCode)){
			//If the zipcode is different, enter the zipcode to be used for the test
			clickYourLocation();
			pageLoaded();
			//Safari does not seem to behave the same with safeSet, so it is being handled differently
			//String os = WebDriverSetup.getOperatingSystem().toLowerCase();
			
			txtZipCode.set(zipCode);
			txtZipCode.sendKeys(Keys.ENTER);
			
			initialize();
			pageLoaded();
			loopCounter = 0;
			do{
				Sleeper.sleep(1000);
				loopCounter++;
				Assert.assertNotEquals(loopCounter, timeout, "The zipcode was found to not have changed within ["+String.valueOf(timeout)+"] seconds.");
			}while(eleZipCode.getText().equalsIgnoreCase(initialZipCode));
			//Capture the newly modified zipcode from the UI
			this.modifiedZipCode = captureCurrentZipCode();
			TestReporter.log("Modified zip code: ["+modifiedZipCode+"].");
			//Ensure the expected and actual zipcdes match
			verifyZipCodeValue(zipCode);	
		}
	}
	
	/**
	 * @summary: verifies that the expected and actual zipcodes are equal
	 * @author: Waightstill W Avery
	 * @param: expectedZipCode - String, value of the zipcode that is expected to be reflected in the UI
	 * @return: NA
	 */
	private void verifyZipCodeValue(String expectedZipCode){
		Assert.assertEquals(captureCurrentZipCode(), expectedZipCode,  "The actual zipcode ["+captureCurrentZipCode()+"] did not match the expected zip code ["+expectedZipCode+"].");
	}
	
	/**
	 * @summary: captures the zipcode that is currently displayed in the UI
	 * @author: Waightstill W Avery
	 * @param: NA
	 * @return: NA
	 */
	private String captureCurrentZipCode(){
		pageLoaded(eleZipCode);
		return eleZipCode.getText().trim();
	}
}

