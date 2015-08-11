package apps.buyAToyota.homePage;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;

import ru.yandex.qatools.allure.annotations.Step;

import com.orasi.core.interfaces.Button;
import com.orasi.core.interfaces.Element;
import com.orasi.core.interfaces.Link;
import com.orasi.core.interfaces.Textbox;
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
		@FindBy(xpath = "//*[@id=\"nav-find-a-dealer\"]/a")
		private Link lnkFindADealer;
		
		//Find Vehicles link
		@FindBy(xpath = "//*[@id=\"nav-find-vehicles\"]/a")
		private Link lnkFindVehicles;
		
		//Tools link
		@FindBy(xpath = "//*[@id=\"nav-tools\"]/a")
		private Link lnkTools;
		
		//Inventory link
		@FindBy(xpath = "//*[@id=\"nav-inventory\"]/a")
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
	
	/**
	 * @summary - High-level method, intended to be called at the test level,
	 *          which contains methods to test navigation bar functionality
	 * @author - Waightstill W Avery
	 */
	public void testNavBarNavigation(){
		if(!(getOperatingSystem().equalsIgnoreCase("windows 7") 
				&& getBrowserUnderTest().equalsIgnoreCase("iexplore")
				&& (getBrowserVersion().equalsIgnoreCase("8"))))
		{
			ensureZipCodePromptHiddenOnStartup();	
		}
		testFindOffersLink();
		testFindADealerLink();
		testFindVehiclesLink();
		testToolsLink();
		testInventoryLink();
		testChangeZipCode();
	}
	
	/**
	 * @summary - Tests that, when the Find Offers link is clicked, that the Zip Code Prompt is displayed.
	 * @author - Waightstill W Avery
	 */
	@Step("Open Zip Code Prompt Using Find Offers")
	public void testFindOffersLink(){
		TestReporter.logStep("Click Find Offers");
		clickFindOffer();
		closeZipCodePrompt();
	}
	
	/**
	 * @summary - Tests that, when the Find A Dealer link is clicked, that the Zip Code Prompt is displayed.
	 * @author - Waightstill W Avery
	 */
	@Step("Open Zip Code Prompt Using Find A Dealer")
	public void testFindADealerLink(){
		TestReporter.logStep("Click Find A Dealer");
		clickFindADealer();
		closeZipCodePrompt();
	}
	
	/**
	 * @summary - Tests that, when the Find Vehicles link is clicked, that the Find Vehicles Dropdown is displayed.
	 * @author - Waightstill W Avery
	 */
	@Step("Open Find Vehicles Dropdown")
	public void testFindVehiclesLink(){
		TestReporter.logStep("Click Find Vehicles");
		clickFindVehicles();
	}
	
	/**
	 * @summary - Tests that, when the Tools link is clicked, that the Tools Dropdown is displayed.
	 * @author - Waightstill W Avery
	 */
	@Step("Open Tools Dropdown")
	public void testToolsLink(){
		TestReporter.logStep("Click Tools");
		clickTools();
	}
	
	/**
	 * @summary - Tests that, when the Inventory link is clicked, that the Zip Code Prompt is displayed.
	 * @author - Waightstill W Avery
	 */
	@Step("Open Zip Code Prompt Using Inventory")
	public void testInventoryLink(){
		TestReporter.logStep("Click Inventory");
		clickInventory();
		closeZipCodePrompt();
	}
	
	/**
	 * @summary - Tests that, when the Inventory link is clicked, that the Zip Code Prompt is displayed.
	 * @author - Waightstill W Avery
	 */
	@Step("Change Zip Code And Validate The New Value")
	public void testChangeZipCode(){
		TestReporter.logStep("Change Zip Code");
		String previousZipCode = txtZipInput.getText();
		
		String newZipCode = changeZipCode(previousZipCode);
		TestReporter.assertTrue(validateChangedZipCode(newZipCode), "The zip code ["+previousZipCode+"] was changed to ["+newZipCode+"].");
	}
	
	/**
	 * @param - previousZipCode - string of the previous zip code
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
		txtZipInput.jsClick(getDriver());
		txtZipInput.set(sNewZipCode);
		btnEditAndSubmitZip.jsClick(getDriver());
		
		loopCounter = 0;
		do{
			Sleeper.sleep(1000);
			loopCounter++;
			initializePage(this.getClass());
			pageLoaded().isDomComplete();
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
		
		pageLoaded().isDomComplete();
		initializePage(this.getClass());
		if(txtZipInput.getText().equalsIgnoreCase(newZipCode)){
			isChanged = true;
		}
		
		return isChanged;
	}
	
	/**
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
	 * @return - boolean - true if the prompt is hidden, false otherwise
	 * @author - Waightstill W Avery
	 * @summary - Verify the zip code prompt is hidden
	 */
	@Step("Determine If Zip Code Prompt Is Hidden")
	private boolean isZipCodePromptHidden(){
		boolean isHidden = false;
		if(eleZipModalPrompt.getAttribute("aria-hidden").equalsIgnoreCase("true")){
			isHidden = true;
		}
		return isHidden;
	}
	
	/**
	 * @return - boolean - true is the prompt is displayed, false otherwise
	 * @author - Waightstill W Avery
	 * @summary - Verify the Find Vehicles dropdown is displayed
	 */
	@Step("Verify Dropdown Is Displayed")
	private boolean isDropdownVisible(Element dropdown){
		boolean isHidden = true;
		if(dropdown.getCoordinates().onPage().x != 0 && dropdown.getCoordinates().onPage().y != 0){
			isHidden = false;
		}
		return isHidden;
	}
	
	/**
	 * @summary - Close the zip code prompt
	 * @author - Waightstill W Avery
	 */
	@Step("Close Zip Code Prompt")
	private void closeZipCodePrompt(){
		TestReporter.log("Closing Zip Code Prompt");
		btnZipModalPromptCancel.jsClick(getDriver());
		
		loopCounter = 0;
		do{
			Sleeper.sleep(1000);
			loopCounter++;
			pageLoaded().isDomComplete();
			initializePage(this.getClass());
			TestReporter.assertNotEquals(loopCounter, getDefaultTestTimeout(), "The zipcode prompt was displayed after ["+String.valueOf(loopCounter)+"] seconds.");
		}while(isZipCodePromptDisplayed());
		
		Sleeper.sleep(2000);
	}
	
	/**
	 * @summary - The zip code prompt is displayed when the app is launched. Wait until the prompt is hidden.
	 * @author - Waightstill W Avery
	 */
	@Step("Validate That The Zip Code Prompt Is Hidden At Startup")
	private void ensureZipCodePromptHiddenOnStartup(){
		boolean isHidden = true;
		String attributeText = "";
		
		loopCounter = 0;
		do{
			Sleeper.sleep(1000);
			loopCounter++;
			pageLoaded().isDomComplete();
			initializePage(this.getClass());
			loopCounter++;
			TestReporter.assertNotEquals(loopCounter, getDefaultTestTimeout(), "The zip code prompt was not displayed after ["+String.valueOf(loopCounter)+"] seconds.");
			
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
	
	/**
	 * @summary - click the Find Offers link and ensure that the zip code dropdown is loaded
	 * @author - Waightstill W Avery
	 */
	private void clickFindOffer(){
		String linkName = "Find Offers";
		initializePage(this.getClass());
		pageLoaded().isElementLoaded(this.getClass(), lnkFindOffer);	
		ensureLinkLoadsZipCodePrompt(lnkFindOffer, linkName);
		TestReporter.assertTrue(true, "The " + linkName + " link was clicked successfully.");
	}
	
	/**
	 * @summary - click Find A Dealer link and ensure that the zip code dropdown is loaded
	 * @author - Waightstill W Avery
	 */
	private void clickFindADealer(){
		String linkName = "Find A Dealer";
		initializePage(this.getClass());
		pageLoaded().isDomComplete();	
		ensureLinkLoadsZipCodePrompt(lnkFindADealer, linkName);
		TestReporter.assertTrue(true, "The " + linkName + " link was clicked successfully.");
	}
	
	/**
	 * @summary - click the Find Vehicles link and ensure that the dropdown is loaded
	 * @author - Waightstill W Avery
	 */
	private void clickFindVehicles(){
		String linkName = "Find Vehicles";
		initializePage(this.getClass());
		pageLoaded().isDomComplete();
		ensureLinkLoadsDropdown(lnkFindVehicles, linkName, eleFindVehiclesDropdown);
		TestReporter.assertTrue(true, "The Find Vehicles link was clicked successfully.");
	}
	
	/**
	 * @summary - click the Tools link and ensure that the dropdown is loaded
	 * @author - Waightstill W Avery
	 */
	private void clickTools(){
		String linkName = "Tools";
		initializePage(this.getClass());
		pageLoaded().isDomComplete();
		ensureLinkLoadsDropdown(lnkTools, linkName, eleToolsDropdown);
		TestReporter.assertTrue(true, "The Tools link was clicked successfully.");
	}
	
	/**
	 * @summary - click the Inventory link and ensure that the dropdown is loaded
	 * @author - Waightstill W Avery
	 */
	private void clickInventory(){
		String linkName = "Inventory";
		initializePage(this.getClass());
		pageLoaded().isDomComplete();
		ensureLinkLoadsZipCodePrompt(lnkInventory, linkName);
		TestReporter.assertTrue(true, "The " + linkName + " link was clicked successfully.");
	}
	
	/**
	 * @summary - click a link and determine if the zip code prompt is displayed
	 * @author - Waightstill W Avery
	 * @param link - link to click
	 * @param linkName - name of the link to click, for reporting purposes
	 */
	private void ensureLinkLoadsZipCodePrompt(Element link, String linkName){
		loopCounter = 0;
		link.highlight(getDriver());
		Sleeper.sleep(1000);
		do{
			if(loopCounter%2 == 0){
				link.jsClick(getDriver());
			}else{
				link.click();
			}
			Sleeper.sleep(2000);
			loopCounter++;
			pageLoaded().isDomComplete();
			initializePage(this.getClass());
			TestReporter.assertNotEquals(loopCounter, getDefaultTestTimeout(), "The Zip Code Prompt was not displayed after ["+String.valueOf(loopCounter)+"] seconds after clicking the "+linkName+" link");
		}while(isZipCodePromptHidden());
	}
	
	/**
	 * @summary - click a link and determine if a dropdown is displayed
	 * @author - Waightstill W Avery
	 * @param element - link element to click
	 * @param linkName - link to click
	 * @param dropdown - dropdown
	 */
	private void ensureLinkLoadsDropdown(Element element, String linkName, Element dropdown){	
		element.highlight(getDriver());
		loopCounter = 0;
		
		//Click the link and ensure the dropdown is visible
		do{
			if(getOperatingSystem().equalsIgnoreCase("Windows 8.1") 
					&& getBrowserUnderTest().equalsIgnoreCase("iexplore")){
				element.click();
			}
			else{
				element.focus(getDriver());
			}
			
			Sleeper.sleep(1000);
			loopCounter++;
			pageLoaded().isDomComplete();
			initializePage(this.getClass());
			TestReporter.assertNotEquals(loopCounter, getDefaultTestTimeout(), "The " + linkName + " dropdown was not displayed after ["+String.valueOf(loopCounter)+"] seconds after clicking the " + linkName + " link.");
		}while(isDropdownVisible(dropdown));
	}
}
