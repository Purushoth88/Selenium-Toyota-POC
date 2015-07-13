package apps.toyota.mainNavigation;

import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import ru.yandex.qatools.allure.annotations.Parameter;
import ru.yandex.qatools.allure.annotations.Step;

import com.orasi.core.interfaces.Button;
import com.orasi.core.interfaces.Element;
import com.orasi.core.interfaces.Textbox;
import com.orasi.core.interfaces.impl.internal.ElementFactory;
import com.orasi.utils.Sleeper;
import com.orasi.utils.TestEnvironment;
import com.orasi.utils.TestReporter;

/**
 * @summary Contains the methods & objects for the Toyota.com Main Navigation Bar
 * @version Created 03/01/2015
 * @author Waightstill W. Avery
 */
public class MainNavigation extends com.orasi.utils.TestEnvironment{
	// *****************************
	// *** MainNavigation Fields ***
	// *****************************
	String initialZipCode = "";
	String modifiedZipCode = "";
	int timeout = getDefaultTestTimeout();
	int loopCounter = 0;
	
	// *******************************
	// *** MainNavigation Elements ***
	// *******************************
	
	//Your Location button
	@FindBy(xpath = "//*[@id=\"tcom-main-nav\"]/ul/li[3]/button")
	private Button btnYourLocation;
	
	//ZIP Code Popup
	@FindBy(id = "tcom-nav-zip-flyout")
	private Element eleZipCodePopup;
	
	//ZIP Code Popup Body
	@FindBy(xpath = "//*[@id=\"tcom-nav-zip-flyout\"]/div")
	private Element eleZipCodePopupBody;
	
	//ZIP Code textbox
	//@FindBy(xpath = "//*[@id=\"tcom-nav-zip-flyout\"]/div/div/div[2]/div/input")
	@FindBy(xpath = "//*[@id=\"tcom-nav-zip-flyout\"]/div[1]/div/div/div[3]/div/input")
	private Textbox txtZipCode;
	
	//ZIP Code Submit button
	@FindBy(xpath = "//*[@id=\"tcom-nav-zip-flyout\"]/div[1]/div/div/a")
	private Button btnZipCodeSubmit;
	
	//Actual ZIP Code Value element
	@FindBy(xpath = "//*[@id=\"tcom-main-nav\"]/ul/li[3]/button/span/span")
	private Element eleZipCode;

	// *********************
	// ** Build page area **
	// *********************
	/**
	 * 
	 * @summary Constructor to initialize the page
	 * @version Created 03/01/2015
	 * @author Waightstill W Avery
	 * @param te - TestEnvironment instance containing the WebDriver to be used for the page class
	 * @throws NA
	 * @return NA
	 * 
	 */
	public MainNavigation(TestEnvironment te){
		super(te);
		ElementFactory.initElements(getDriver(), this);  
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
	@Step("Click 'Your Location'")
	private void clickYourLocation(){
		//Attempt to use the Selenium 'click'
		btnYourLocation.jsClick(driver);
		
		//Loop until the popup is loaded
		loopCounter = 0;
		do{
			Sleeper.sleep(1000);
			//Ensure the DOM is loaded
			pageLoaded().isDomComplete();
			//Initialize all elements
			initializePage(this.getClass());
			//Iterate the counter
			loopCounter++;
			//Ensure the timeout is not reached
			Assert.assertNotEquals(loopCounter, timeout, "The zipcode popup was not opened after [" +String.valueOf(timeout)+ "] seconds.");
		}while(!eleZipCodePopup.getAttribute("class").toLowerCase().contains("open"));
	}
	
	/**
	 * @summary: if the zipcode is different than that found in the UI, this method will click the location icon, enter the new zipcode and verify the change is reflected in the UI
	 * @author: Waightstill W Avery
	 * @param: zipCode - String, value to be entered as the new zipcode to use
	 * @return: NA
	 */
	@Step("Change Zip Code to \"{0}\"")
	public void changeZipCodes(@Parameter String zipCode){
		//Ensure the "Your Location" button is loaded
		pageLoaded().isElementLoaded(this.getClass(), btnYourLocation);
		//Capture the zipcode that currently exists in the UI
		this.initialZipCode = captureCurrentZipCode();
		TestReporter.log("Initial zip code: ["+initialZipCode+"].");
		//Determine if the existing zipcode in the UI is the same as the zipcode to be used in the test
		//If so, increment the zipcode by 1
		if(initialZipCode.equalsIgnoreCase(zipCode)){
			int intZipCode = Integer.parseInt(zipCode);
			intZipCode = intZipCode++;
			zipCode = String.valueOf(intZipCode);
		}
		
		//If the zipcode is different, enter the zipcode to be used for the test
		clickYourLocation();
		//Ensure the zip code textbox is visible
		Assert.assertEquals(txtZipCode.syncVisible(driver), true, "The zipcode textbox is not visible.");
		
		//Enter the zip code and ensure the value remains in the textbox
		loopCounter = 0;
		do{
			//Click the textbox to put focus the cursor
			txtZipCode.click();
			//Set the zip code
			txtZipCode.set(zipCode);
			Sleeper.sleep(1000);
			//Ensure the DOM is loaded
			pageLoaded();
			//Initialize all elements
			initializePage(this.getClass());
			//Iterate the counter
			loopCounter++;
			//Ensure the timeout is not reached
			Assert.assertNotEquals(loopCounter, timeout, "The zipcode was not entered after [" +String.valueOf(timeout)+ "] seconds.");
		}while(!txtZipCode.getText().equalsIgnoreCase(zipCode));
		
		//Send the enter key to effectively change the zip code
		txtZipCode.sendKeys(Keys.ENTER);
		
		//Loop while the zip code popup is open
		loopCounter = 0;
		do{
			Sleeper.sleep(1000);
			//Ensure the DOM is loaded
			pageLoaded();
			//Initialize all elements
			initializePage(this.getClass());
			//Iterate the counter
			loopCounter++;
			//Ensure the timeout is not reached
			Assert.assertNotEquals(loopCounter, timeout, "The zipcode popup was not closed after [" +String.valueOf(timeout)+ "] seconds.");
		}while(eleZipCodePopup.getAttribute("class").toLowerCase().contains("open"));
		
		//Loop until the initial zip code has been changed on the page
		loopCounter = 0;
		do{
			Sleeper.sleep(1000);
			//Ensure the DOM is loaded
			pageLoaded();
			//Initialize all elements
			initializePage(this.getClass());
			//Iterate the counter
			loopCounter++;
			//Ensure the timeout is not reached
			Assert.assertNotEquals(loopCounter, timeout, "The zipcode was found to not have changed within ["+String.valueOf(timeout)+"] seconds.");
		}while(eleZipCode.getText().equalsIgnoreCase(initialZipCode));
		//Capture the newly modified zipcode from the UI
		this.modifiedZipCode = captureCurrentZipCode();
		TestReporter.log("Modified zip code: ["+modifiedZipCode+"].");
		//Ensure the expected and actual zipcdes match
		verifyZipCodeValue(zipCode);
	}
	
	/**
	 * @summary: verifies that the expected and actual zipcodes are equal
	 * @author: Waightstill W Avery
	 * @param: expectedZipCode - String, value of the zipcode that is expected to be reflected in the UI
	 * @return: NA
	 */
	@Step("Verify the Zip Code is \"{0}\"")
	private void verifyZipCodeValue(@Parameter String expectedZipCode){
		Assert.assertEquals(captureCurrentZipCode(), expectedZipCode,  "The actual zipcode ["+captureCurrentZipCode()+"] did not match the expected zip code ["+expectedZipCode+"].");
	}
	
	/**
	 * @summary: captures the zipcode that is currently displayed in the UI
	 * @author: Waightstill W Avery
	 * @param: NA
	 * @return: NA
	 */
	@Step("Capture the Current Zip Code")
	private String captureCurrentZipCode(){
		//Ensure the element is loaded
		pageLoaded().isElementLoaded(this.getClass(), eleZipCode);
		//Grab and return the element text for validation
		return eleZipCode.getText().trim();
		
	}
}

