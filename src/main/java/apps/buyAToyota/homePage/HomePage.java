package apps.buyAToyota.homePage;

import org.openqa.selenium.support.FindBy;

import ru.yandex.qatools.allure.annotations.Step;

import com.orasi.core.interfaces.Button;
import com.orasi.core.interfaces.Element;
import com.orasi.core.interfaces.Link;
import com.orasi.core.interfaces.Textbox;
import com.orasi.core.interfaces.impl.internal.ElementFactory;
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
			//Zip Input Confirmation Modal Zip Code element
			@FindBy(xpath = "//*[@id=\"zip-modal-confirm\"]/div/div/div[1]/h5/strong")
			private Element eleZipInputConfirmationModalZipCode;
		
		//Edit And Submit button
		@FindBy(id = "edit-and-submit-zip-button")
		private Button btnEditAndSubmitZip;
		
		/*
		 * Zip Modal Prompt Elements
		 */
		//Zip Code Prompt element
		@FindBy(id = "zip-modal-prompt")
		private Element eleZipModalPrompt;
		
		//Zip Code Header element
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
		lnkFindOffer.click();
		pageLoaded();
		initializePage(this.getClass());
		TestReporter.assertTrue(isZipCodePromptDisplayed(), "The zipcode prompt was not displayed after ["+String.valueOf(getDefaultTestTimeout())+"] seconds after clicking the Find Offers link.");
		
		btnZipModalPromptCancel.click();
		TestReporter.assertTrue(isZipCodePromptHidden(), "The zipcode prompt was not hidden after ["+String.valueOf(getDefaultTestTimeout())+"] seconds after clicking the Zip Code Prompt Cancel button.");
		pageLoaded();
		initializePage(this.getClass());
	}
	
	/**
	 * @param - N/A
	 * @return - N/A
	 * @author - Waightstill W Avery
	 * @summary - Tests that, when the Find A Dealer link is clicked, that the Zip Code Prompt is displayed.
	 */
	@Step("Open Zip Code Prompt Using Find A Dealer")
	public void testFindADealerLink(){
		lnkFindADealer.click();
		pageLoaded();
		initializePage(this.getClass());
		TestReporter.assertTrue(isZipCodePromptDisplayed(), "The Zip Code Prompt was not displayed after ["+String.valueOf(getDefaultTestTimeout())+"] seconds after clicking the Find A Dealer link.");
		
		btnZipModalPromptCancel.click();
		TestReporter.assertTrue(isZipCodePromptHidden(), "The Zip Code Prompt was not hidden after ["+String.valueOf(getDefaultTestTimeout())+"] seconds after clicking the Zip Code Prompt Cancel button.");
		pageLoaded();
		initializePage(this.getClass());
	}
	
	/**
	 * @param - N/A
	 * @return - N/A
	 * @author - Waightstill W Avery
	 * @summary - Tests that, when the Find Vehicles link is clicked, that the Find Vehicles Dropdown is displayed.
	 */
	@Step("Open Find Vehicles Dropdown")
	public void testFindVehiclesLink(){
		lnkFindVehicles.click();
		pageLoaded();
		initializePage(this.getClass());
		TestReporter.assertTrue(isFindVehiclesDropdownVisible(), "The Find Vehicles Dropdown was not displayed after ["+String.valueOf(getDefaultTestTimeout())+"] seconds after clicking the Find Vehicles link.");
	}
	
	/**
	 * @param - N/A
	 * @return - N/A
	 * @author - Waightstill W Avery
	 * @summary - Tests that, when the Tools link is clicked, that the Tools Dropdown is displayed.
	 */
	@Step("Open Tools Dropdown")
	public void testToolsLink(){
		lnkTools.click();
		pageLoaded();
		initializePage(this.getClass());
		TestReporter.assertTrue(isToolsDropdownVisible(), "The Tools Dropdown was not displayed after ["+String.valueOf(getDefaultTestTimeout())+"] seconds after clicking the Tools link.");
	}
	
	/**
	 * @param - N/A
	 * @return - N/A
	 * @author - Waightstill W Avery
	 * @summary - Tests that, when the Inventory link is clicked, that the Zip Code Prompt is displayed.
	 */
	@Step("Open Zip Code Prompt Using Inventory")
	public void testInventoryLink(){
		lnkInventory.click();
		pageLoaded();
		initializePage(this.getClass());
		TestReporter.assertTrue(isZipCodePromptDisplayed(), "The Zip Code Prompt was not displayed after ["+String.valueOf(getDefaultTestTimeout())+"] seconds after clicking the Inventory link.");
		
		btnZipModalPromptCancel.click();
		TestReporter.assertTrue(isZipCodePromptHidden(), "The Zip Code Prompt was not hidden after ["+String.valueOf(getDefaultTestTimeout())+"] seconds after clicking the Zip Code Prompt Cancel button.");
		pageLoaded();
		initializePage(this.getClass());
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
		TestReporter.assertTrue(validateChangedZipCode(newZipCode), "The zip code ["+previousZipCode+"] was not changed to ["+newZipCode+"].");
	}
	
	/**
	 * @param - N/A
	 * @return - N/A
	 * @author - Waightstill W Avery
	 * @summary - Click the textbox and enter the zip code.
	 */
	@Step("Change The Zip Code")
	private String changeZipCode(String previousZipCode){
		String newZipCode = String.valueOf(Integer.parseInt(previousZipCode) + 1);
		txtZipInput.safeSet(newZipCode);
		
		return newZipCode;
	}
	
	/**
	 * @param - N/A
	 * @return - N/A
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
	
	@Step("Verify Zip Code Prompt Is Displayed")
	private boolean isZipCodePromptDisplayed(){
		boolean isDisplayed = false;
		if(eleZipModalPrompt.syncVisible(getDriver(), getDefaultTestTimeout(), false)){
			isDisplayed = true;
		}
		return isDisplayed;
	}
	
	@Step("Verify Zip Code Prompt Is Hidden")
	private boolean isZipCodePromptHidden(){
		boolean isHidden = false;
		if(eleZipModalPrompt.syncHidden(getDriver(), getDefaultTestTimeout(), false)){
			isHidden = true;
		}
		return isHidden;
	}
	
	@Step("Verify Find Vehicles Dropdown Is Displayed")
	private boolean isFindVehiclesDropdownVisible(){
		boolean isHidden = false;
		if(eleFindVehiclesDropdown.syncVisible(getDriver(), getDefaultTestTimeout(), false)){
			isHidden = true;
		}
		return isHidden;
	}
	
	@Step("Verify Tools Dropdown Is Displayed")
	private boolean isToolsDropdownVisible(){
		boolean isHidden = false;
		if(eleToolsDropdown.syncVisible(getDriver(), getDefaultTestTimeout(), false)){
			isHidden = true;
		}
		return isHidden;
	}
	
}
