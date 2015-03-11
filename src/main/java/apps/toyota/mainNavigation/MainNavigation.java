package apps.toyota.mainNavigation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.orasi.core.interfaces.Button;
import com.orasi.core.interfaces.Element;
import com.orasi.core.interfaces.Link;
import com.orasi.core.interfaces.Textbox;
import com.orasi.core.interfaces.impl.internal.ElementFactory;
import com.orasi.utils.PageLoaded;
import com.orasi.utils.TestReporter;


/**
 * @summary Contains the methods & objects for the Toyota.com Main Navigation Bar
 * @version Created 09/10/2014
 * @author Waightstill W. Avery
 */
public class MainNavigation {
	// ******************************
	// *** Main Navigation Fields ***
	// ******************************
	String initialZipCode = "";
	String modifiedZipCode = "";
	
	// ********************************
	// *** Main Navigation Elements ***
	// ********************************
	
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
	 * @version Created 09/10/2014
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
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnYourLocation);
	}

	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element);
	}

	public MainNavigation initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	// ***********************************************
	// *** HomePage Interactions ***
	// ***********************************************

	private void clickYourLocation(){
		btnYourLocation.click();
		if(!pageLoaded(eleZipCodePopup)){
			initialize();
			btnYourLocation.jsClick(driver);
			Assert.assertEquals(pageLoaded(eleZipCodePopup), true, "The zip code popup was not displayed.");
		}
	}
	
	public void changeZipCodes(String zipCode){
		pageLoaded(txtZipCode);
		this.initialZipCode = captureCurrentZipCode();
		TestReporter.log("Initial zip code: ["+initialZipCode+"].");
		clickYourLocation();
		txtZipCode.safeSet(zipCode);
		initialize();
		pageLoaded();
		this.modifiedZipCode = captureCurrentZipCode();
		TestReporter.log("Modified zip code: ["+modifiedZipCode+"].");
		verifyZipCodeValue(zipCode);
	}
	
	private void verifyZipCodeValue(String expectedZipCode){
		Assert.assertEquals(captureCurrentZipCode(), expectedZipCode,  "The actual zipcode ["+captureCurrentZipCode()+"] did not match the expected zip code ["+expectedZipCode+"].");
	}
	
	private String captureCurrentZipCode(){
		return eleZipCode.getText().trim();
	}
}

