package apps.buyAToyota.homePage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import ru.yandex.qatools.allure.annotations.Step;

import com.orasi.core.interfaces.Button;
import com.orasi.core.interfaces.Element;
import com.orasi.core.interfaces.Link;
import com.orasi.core.interfaces.Textbox;
import com.orasi.core.interfaces.impl.ElementImpl;
import com.orasi.core.interfaces.impl.LinkImpl;
import com.orasi.core.interfaces.impl.internal.ElementFactory;
import com.orasi.utils.Sleeper;
import com.orasi.utils.TestEnvironment;
import com.orasi.utils.TestReporter;

/**
 * @summary Contains the methods & objects for the BuyAToyota.com homepage
 * @version Created 05/29/2015
 * @author Waightstill W. Avery
 */
public class HomePage extends com.orasi.utils.TestEnvironment{

	// ***********************
	// *** HomePage Fields ***
	// ***********************
	int timeout = getDefaultTestTimeout();
	int loopCounter = 0;
	
	// *************************
	// *** HomePage Elements ***
	// *************************
	//Open Layout element
	@FindBy(id = "openLayout")
	private Element eleOpenLayout;
	
	/*
	 * Header Wrapper Row
	 */
		//Data Slider 1
		@FindBy(xpath = "//*[@id=\"1392349455702\"]/header/div[2]/div/div[3]/div[2]/div/div[1]/a")
		private Link lnkDataSlider1;
		
		//Data Slider 2
		@FindBy(xpath = "//*[@id=\"1392349455702\"]/header/div[2]/div/div[3]/div[2]/div/div[2]/a")
		private Link lnkDataSlider2;
		
		//Data Slider 3
		@FindBy(xpath = "//*[@id=\"1392349455702\"]/header/div[2]/div/div[3]/div[2]/div/div[3]/a")
		private Link lnkDataSlider3;
		
		//Navigation Bar element
		@FindBy(id = "navbar")
		private Element eleNavBar;
		
		//Find Offer link
		@FindBy(xpath = "//*[@id=\"nav-find-offers\"]/a")
		private Link lnkFindOffer;
			
		//Find A Dealer link
		@FindBy(xpath = "//*[@id=\"nav-find-a-dealer\"/a")
		private Link lnkFindADealer;
		
		//Find Vehicles link
		@FindBy(xpath = "//*[@id=\"nav-find-vehicles\"/a")
		private Link lnkFindVehicles;
		
		//Tools link
		@FindBy(xpath = "//*[@id=\"nav-tools\"/a")
		private Link lnkTools;
		
		//Inventory link
		@FindBy(xpath = "//*[@id=\"nav-inventory\"/a")
		private Link lnkInventory;
		
		//Zip Input textbox
		@FindBy(id = "headerZipInput")
		private Textbox txtZipInput;
		
			/*
			 * Zip Input Confirmation Modal Elements
			 */
			//
			@FindBy(id = "zip-modal-confirm")
			private Element eleZipInputConfirmationModal;
		
			//Zip Input Confirmation Modal Zip Code element
			@FindBy(xpath = "//*[@id=\"zip-modal-confirm\"]/div/div/div[1]/h5/strong")
			private Element eleZipInputConfirmationModalZipCode;
			
			//Zip Input COnfirmation Modal Continue
			@FindBy(xpath = "//*[@id=\"zip-modal-confirm\"]/div/div/div[2]/div[1]/button")
			private Button btnZipInputConfirmationModalCancel;
			
			//Zip Input COnfirmation Modal Continue
			@FindBy(xpath = "//*[@id=\"zip-modal-confirm\"]/div/div/div[2]/div[2]/button")
			private Button btnZipInputConfirmationModalContinue;
		
		//Edit And Submit button
		@FindBy(id = "edit-and-submit-zip-button")
		private Button btnEditAndSubmitZip;
		
		/*
		 * Zip Modal Prompt Elements
		 */
		//Zip Code Prompt element
		@FindBy(id = "zip-modal-prompt")
		private Element eleZipModalPrompt;
		
		//Zip Code Prompt Header element
		@FindBy(xpath = "//*[@id=\"zip-modal-prompt\"]/div/div/div[1]/h5")
		private Element eleZipModalPromptHeader;
	
		//Zip Code Prompt textbox
		@FindBy(id = "search-value")
		private Textbox txtZipModalPromptZipCode;
		
		//Zip Code Prompt Cancel button
		@FindBy(xpath = "//*[@id=\"zip-modal-prompt\"]/div/div/div[3]/div[1]/button")
		private Button btnZipModalPromptCancel;
		
		//Zip Code Prompt Continue button
		@FindBy(xpath = "//*[@id=\"zip-modal-prompt\"]/div/div/div[3]/div[2]/button")
		private Button btnZipModalPromptContinue;
		
		/*
		 * Find Vehicles Dropdown Elements
		 */
		//Find Vehicles Dropdown element
		@FindBy(id = "find-vehicles-dropdown")
		private Element eleFindVehiclesDropdown;
		
		//Find Vehicles Dropdown Cars And Minivans link
		@FindBy(xpath = "//*[@id=\"find-vehicles-dropdown\"]/div/div/div[1]/ul/li[1]/a")
		private Link lnkFindVehiclesDropdownCarsAndMinivans;
		
		//Find Vehicles Dropdown Trucks link
		@FindBy(xpath = "//*[@id=\"find-vehicles-dropdown\"]/div/div/div[1]/ul/li[2]/a")
		private Link lnkFindVehiclesDropdownTrucks;
		
		//Find Vehicles Dropdown Crossovers And SUVs link
		@FindBy(xpath = "//*[@id=\"find-vehicles-dropdown\"]/div/div/div[1]/ul/li[1]/a")
		private Link lnkFindVehiclesDropdownCrossoversAndSuvs;
		
		//Find Vehicles Dropdown Hybrids And EVs link
		@FindBy(xpath = "//*[@id=\"find-vehicles-dropdown\"]/div/div/div[1]/ul/li[1]/a")
		private Link lnkFindVehiclesDropdownHybridsAndEvs;
		
		//Find Vehicles Dropdown See Offers link
		@FindBy(xpath = "//*[@id=\"find-vehicles-dropdown\"]/div/div/div[1]/div[1]/div/a")
		private Link lnkFindVehiclesDropdownSeeOffers;
		
		//Find Vehicles Dropdown Certified Used link
		@FindBy(xpath = "//*[@id=\"find-vehicles-dropdown\"]/div/div/div[1]/div[2]/div/a")
		private Link lnkFindVehiclesDropdownCertifiedUsed;
		
		/*
		 * Tools Dropdown Elements
		 */
		//Tools
		@FindBy(id = "tools-dropdown")
		private Element eleToolsDropdown;
	
	/*
	 * Main Wrapper Row
	 */
	//Main Wrapper Row element
	@FindBy(id = "main-wrapper-row")
	private Element eleMainWrapperRow;
	
	//Get Started link
	@FindBy(xpath = "//*[@id=\"1392349455774\"]/div/div/div[1]/div/a")
	private Link lnkGetStarted;
	
	//Footer Wrapper Row
	
	// *********************
	// ** Build page area **
	// *********************
	/**
	 * 
	 * @summary Constructor to initialize the page
	 * @version Created 05/29/2015
	 * @author Waightstill W Avery
	 * @param te - TestEnvironment instance containing the WebDriver to be used for the page class
	 * @throws NA
	 * @return NA
	 * 
	 */
	public HomePage(TestEnvironment te){
		super(te);
		ElementFactory.initElements(getDriver(), this);  
	}

	// *****************************
	// *** HomePage Interactions ***
	// *****************************
	
	public void testNavBarNavigation(){
		ensureZipCodePromptHiddenOnStartup();
		testFindOffersLink();
		testFindADealerLink();
		testFindVehiclesLink();
		testToolsLink();
		testInventoryLink();
		testChangeZipCode();
	}
	
	/**
	 * @param - N/A
	 * @return - N/A
	 * @author - Waightstill W Avery
	 * @summary - Tests that, when the Find Offers link is clicked, that the Zip Code Prompt is displayed.
	 */
	@Step("Open Zip Code Prompt Using Find Offers")
	public void testFindOffersLink(){
		clickFindOffer();
		closeZipCodePrompt();
	}
	
	/**
	 * @param - N/A
	 * @return - N/A
	 * @author - Waightstill W Avery
	 * @summary - Tests that, when the Find A Dealer link is clicked, that the Zip Code Prompt is displayed.
	 */
	@Step("Open Zip Code Prompt Using Find A Dealer")
	public void testFindADealerLink(){
		clickFindADealer();
		closeZipCodePrompt();
	}
	
	/**
	 * @param - N/A
	 * @return - N/A
	 * @author - Waightstill W Avery
	 * @summary - Tests that, when the Find Vehicles link is clicked, that the Find Vehicles Dropdown is displayed.
	 */
	@Step("Open Find Vehicles Dropdown")
	public void testFindVehiclesLink(){
		clickFindVehicles();
	}
	
	/**
	 * @param - N/A
	 * @return - N/A
	 * @author - Waightstill W Avery
	 * @summary - Tests that, when the Tools link is clicked, that the Tools Dropdown is displayed.
	 */
	@Step("Open Tools Dropdown")
	public void testToolsLink(){
		clickTools();
	}
	
	/**
	 * @param - N/A
	 * @return - N/A
	 * @author - Waightstill W Avery
	 * @summary - Tests that, when the Inventory link is clicked, that the Zip Code Prompt is displayed.
	 */
	@Step("Open Zip Code Prompt Using Inventory")
	public void testInventoryLink(){
		clickInventory();
		closeZipCodePrompt();
	}
	
	/**
	 * @param - N/A
	 * @return - N/A
	 * @author - Waightstill W Avery
	 * @summary - Tests that, when the Inventory link is clicked, that the Zip Code Prompt is displayed.
	 */
	@Step("Change Zip Code And Validate The New Value")
	public void testChangeZipCode(){
		String previousZipCode = txtZipInput.getText();
		
		String newZipCode = changeZipCode(previousZipCode);
		TestReporter.assertTrue(validateChangedZipCode(newZipCode), "The zip code ["+previousZipCode+"] was changed to ["+newZipCode+"].");
	}
	
	/**
	 * @param - N/A
	 * @return - String - the new zip code as seen in the app
	 * @author - Waightstill W Avery
	 * @summary - Click the textbox and enter the zip code.
	 */
	@Step("Change The Zip Code")
	private String changeZipCode(String previousZipCode){
		int iNewZipCode;
		String sNewZipCode = "";
		
		try{
			iNewZipCode = Integer.parseInt(previousZipCode) + 1;
			sNewZipCode = String.valueOf(iNewZipCode);
		}catch(NumberFormatException nfe){
			iNewZipCode = 92055;
			sNewZipCode = String.valueOf(iNewZipCode);
		}
		//txtZipInput.safeSet(sNewZipCode);
		txtZipInput.jsClick(getDriver());
		txtZipInput.set(sNewZipCode);
//		txtZipInput.sendKeys(Keys.ENTER);
		btnEditAndSubmitZip.jsClick(getDriver());
		
		loopCounter = 0;
		do{
			Sleeper.sleep(1000);
			loopCounter++;
			initializePage(this.getClass());
			pageLoaded();
		}while(eleZipInputConfirmationModal.getAttribute("aria-hidden").equalsIgnoreCase("true"));
		
		btnZipInputConfirmationModalContinue.jsClick(getDriver());
		
		return sNewZipCode;
	}
	
	/**
	 * @param - newZipCode - the new zip code with which to enter into the app
	 * @return - boolean - true if the zip code is changed in the app, false otherwise
	 * @author - Waightstill W Avery
	 * @summary - Validate the new zip code
	 */
	@Step("Validate The New Zip Code")
	private boolean validateChangedZipCode(String newZipCode){
		boolean isChanged = false;
		
		pageLoaded();
		initializePage(this.getClass());
		if(txtZipInput.getText().equalsIgnoreCase(newZipCode)){
			isChanged = true;
		}
		
		return isChanged;
	}
	
	/**
	 * @param - N/A
	 * @return - boolean - true if the prompt is displayed, false otherwise
	 * @author - Waightstill W Avery
	 * @summary - Verify the zip code prompt is displayed
	 */
	@Step("Verify Zip Code Prompt Is Displayed")
	private boolean isZipCodePromptDisplayed(){
		boolean isDisplayed = false;
		if(eleZipModalPrompt.getAttribute("aria-hidden").equalsIgnoreCase("false")){
			isDisplayed = true;
		}
		return isDisplayed;
	}
	
	/**
	 * @param - N/A
	 * @return - boolean - true if the prompt is hidden, false otherwise
	 * @author - Waightstill W Avery
	 * @summary - Verify the zip code prompt is hidden
	 */
	@Step("Verify Zip Code Prompt Is Hidden")
	private boolean isZipCodePromptHidden(){
		boolean isHidden = false;
		if(eleZipModalPrompt.getAttribute("aria-hidden").equalsIgnoreCase("true")){
			isHidden = true;
		}
		return isHidden;
	}
	
	/**
	 * @param - N/A
	 * @return - boolean - true is the prompt is displayed, false otherwise
	 * @author - Waightstill W Avery
	 * @summary - Verify the Find Vehicles dropdown is displayed
	 */
	@Step("Verify Find Vehicles Dropdown Is Displayed")
	private boolean isFindVehiclesDropdownVisible(){
		boolean isHidden = true;
		if(eleFindVehiclesDropdown.syncVisible(getDriver(), getDefaultTestTimeout(), false)){
			isHidden = false;
		}
		return isHidden;
	}
	
	/**
	 * @param - N/A
	 * @return - true is the dropdown is displayed, false otherwise
	 * @author - Waightstill W Avery
	 * @summary - Verify the Find Vehicles dropdown is hidden
	 */
	@Step("Verify Tools Dropdown Is Displayed")
	private boolean isToolsDropdownVisible(){
		boolean isHidden = true;
		if(eleToolsDropdown.syncVisible(getDriver(), getDefaultTestTimeout(), false)){
			isHidden = false;
		}
		return isHidden;
	}
	
	/**
	 * @param - N/A
	 * @return - N/A
	 * @author - Waightstill W Avery
	 * @summary - Close the zip code prompt
	 */
	@Step("Close Zip Code Prompt")
	private void closeZipCodePrompt(){
		btnZipModalPromptCancel.jsClick(getDriver());
		
		loopCounter = 0;
		do{
			Sleeper.sleep(1000);
			loopCounter++;
			pageLoaded();
			initializePage(this.getClass());
			Assert.assertNotEquals(loopCounter, timeout, "The zipcode prompt was not displayed after ["+String.valueOf(getDefaultTestTimeout())+"] seconds after clicking the Find Offers link.");
		}while(isZipCodePromptDisplayed());
		
		Sleeper.sleep(2000);
	}
	
	/**
	 * @param - N/A
	 * @return - N/A
	 * @author - Waightstill W Avery
	 * @summary - The zip code prompt is displayed when the app is launched. Wait until the prompt is hidden.
	 */
	@Step("Validate That The Zip Code Prompt Is Hidden At Startup")
	private void ensureZipCodePromptHiddenOnStartup(){
		boolean isHidden = true;
		String attributeText = "";
		
		loopCounter = 0;
		do{
			Sleeper.sleep(1000);
			loopCounter++;
			pageLoaded();
			initializePage(this.getClass());
			loopCounter++;
			Assert.assertNotEquals(loopCounter, timeout, "The zip code prompt was not displayed after ["+String.valueOf(getDefaultTestTimeout())+"] seconds.");
			
			try{
				attributeText = eleZipModalPrompt.getAttribute("aria-hidden");
				if(attributeText.equalsIgnoreCase("false")){
					isHidden = false;
				}
			}catch(NullPointerException npe){
				TestReporter.log("Modal Not Found.");
			}
		}while(isHidden);
		
		closeZipCodePrompt();
	}
	
	private void clickFindOffer(){
		initializePage(this.getClass());
		pageLoaded(this.getClass(), lnkFindOffer);	
//		lnkFindOffer.focus(getDriver());
		lnkFindOffer.highlight(getDriver());
		lnkFindOffer.jsClick(getDriver());
		
		loopCounter = 0;
		do{
			Sleeper.sleep(1000);
			loopCounter++;
			pageLoaded();
			initializePage(this.getClass());
			Assert.assertNotEquals(loopCounter, timeout, "The zipcode prompt was not displayed after ["+String.valueOf(getDefaultTestTimeout())+"] seconds after clicking the Find Offers link.");
		}while(isZipCodePromptHidden());
		TestReporter.assertTrue(true, "The Find Offer link was clicked successfully.");
	}
	
	private void clickFindADealer(){
		initializePage(this.getClass());
		pageLoaded();
		lnkFindADealer = new LinkImpl(driver.findElement(By.id("nav-find-a-dealer")).findElement(By.tagName("a")));	
//		lnkFindADealer.focus(getDriver());
		lnkFindADealer.highlight(getDriver());
		lnkFindADealer.jsClick(getDriver());
		
		loopCounter = 0;
		do{
			Sleeper.sleep(1000);
			loopCounter++;
			pageLoaded();
			initializePage(this.getClass());
			Assert.assertNotEquals(loopCounter, timeout, "The zipcode prompt was not displayed after ["+String.valueOf(getDefaultTestTimeout())+"] seconds after clicking the Find Offers link.");
		}while(isZipCodePromptHidden());
		TestReporter.assertTrue(true, "The Find A Dealer link was clicked successfully.");
	}
	
	private void clickFindVehicles(){
		initializePage(this.getClass());
		pageLoaded();
		lnkFindVehicles = new LinkImpl(driver.findElement(By.id("nav-find-vehicles")).findElement(By.tagName("a")));	
//		lnkFindVehicles.focus(getDriver());
		lnkFindVehicles.highlight(getDriver());
		lnkFindVehicles.jsClick(getDriver());

		loopCounter = 0;
		do{
			Sleeper.sleep(1000);
			loopCounter++;
			pageLoaded();
			initializePage(this.getClass());
			Assert.assertNotEquals(loopCounter, timeout, "The Find Vehicles dropdown was not displayed after ["+String.valueOf(getDefaultTestTimeout())+"] seconds after clicking the Find Vehicles link.");
		}while(isFindVehiclesDropdownVisible());
		TestReporter.assertTrue(true, "The Find Vehicles link was clicked successfully.");
	}
	
	private void clickTools(){
		initializePage(this.getClass());
		pageLoaded();
		lnkTools = new LinkImpl(driver.findElement(By.id("nav-tools")).findElement(By.tagName("a")));
//		lnkTools.focus(getDriver());
		lnkTools.highlight(getDriver());
		lnkTools.jsClick(getDriver());
		
		loopCounter = 0;
		do{
			Sleeper.sleep(1000);
			loopCounter++;
			pageLoaded();
			initializePage(this.getClass());
			Assert.assertNotEquals(loopCounter, timeout, "The Tools Dropdown was not displayed after ["+String.valueOf(getDefaultTestTimeout())+"] seconds after clicking the Tools link.");
		}while(isToolsDropdownVisible());
		TestReporter.assertTrue(true, "The Tools link was clicked successfully.");
	}
	
	private void clickInventory(){
		initializePage(this.getClass());
		pageLoaded();
		lnkInventory = new LinkImpl(driver.findElement(By.id("nav-inventory")).findElement(By.tagName("a")));
//		lnkInventory.focus(getDriver());
		lnkInventory.highlight(getDriver());
		lnkInventory.jsClick(getDriver());
		
		loopCounter = 0;
		do{
			Sleeper.sleep(1000);
			loopCounter++;
			pageLoaded();
			initializePage(this.getClass());
			Assert.assertNotEquals(loopCounter, timeout, "The zipcode prompt was not displayed after ["+String.valueOf(getDefaultTestTimeout())+"] seconds after clicking the Inventory link");
		}while(isZipCodePromptHidden());
		TestReporter.assertTrue(true, "The Inventory link was clicked successfully.");
	}
}
