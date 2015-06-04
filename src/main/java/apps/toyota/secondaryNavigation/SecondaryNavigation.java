package apps.toyota.secondaryNavigation;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import ru.yandex.qatools.allure.annotations.Step;

import com.orasi.core.interfaces.Button;
import com.orasi.core.interfaces.Element;
import com.orasi.core.interfaces.Link;
import com.orasi.core.interfaces.Textbox;
import com.orasi.core.interfaces.impl.ElementImpl;
import com.orasi.core.interfaces.impl.internal.ElementFactory;
import com.orasi.utils.Sleeper;
import com.orasi.utils.TestEnvironment;
import com.orasi.utils.TestReporter;


/**
 * @summary Contains the methods & objects for the Toyota.com Secondary Navigation bar
 * @version Created 03/01/2015
 * @author Waightstill W. Avery
 */
public class SecondaryNavigation extends com.orasi.utils.TestEnvironment{
	// **************************************
	// *** SecondaryNavigation Bar Fields ***
	// **************************************
	int timeout = getDefaultTestTimeout();
	int loopCounter = 0;
	int windows7Wait = 2000;  //Time in milliseconds
	
	// ****************************************
	// *** SecondaryNavigation Bar Elements ***
	// ****************************************
	
	//Toyota Icon link
	@FindBy(id = "tcom-nav-logo-desktop")
	private Link lnkToyotaIcon;
	
	//Select Vehicle button
	@FindBy(xpath = "//*[@id=\"tcom-secondary-nav\"]/ul/li[2]/button")
	private Button btnSelectVehicle;
	
	//Select Vehicles Dropdown element
	@FindBy(id = "tcom-vehicles-dropdown")
	private Element eleSelectVehiclesDropdown;
	
	//Shopping Tools button
	@FindBy(xpath = "//*[@id=\"tcom-secondary-nav\"]/ul/li[3]/button")
	private Button btnShoppingTools;
	
	//Shopping Tools Dropdown element
	@FindBy(id = "tcom-shopping-dropdown")
	private Element eleShoppingToolsDropdown;
	
	//Find A Dealer link
	@FindBy(xpath = "//*[@id=\"tcom-secondary-nav\"]/ul/li[4]/a")
	private Link lnkFindADealer;
	
	//Find A Dealer Search Box textbox
	@FindBy(xpath = "//*[@id=\"dealerLocator\"]/div[1]/div[1]/div[1]/div/div[2]/input")
	private Textbox txtFindADealer;
	
	//Build & Price link
	@FindBy(xpath = "//*[@id=\"tcom-secondary-nav\"]/ul/li[5]/a")
	private Link lnkBuildAndPrice;
	
	//Build & Price Continue link
	@FindBy(xpath = "//*[@id=\"errorRedirect\"]/div/div/div[2]/a")
	private Link lnkBuildAndPriceContinue;
	
	//Build Your Toyota Header element
	@FindBy(xpath = "//*[@id=\"landing\"]/div[1]/h1")
	private Element eleBuildYourToyota;
	
	//Build Your Toyota Lander element
	@FindBy(id = "landing")
	private Element eleBuildYourToyotaLander;
	
	//Local Specials link
	@FindBy(xpath = "//*[@id=\"tcom-secondary-nav\"]/ul/li[6]/a")
	private Link lnkLocalSpecials;
	
	//Local Specials ZIPCode textbox
	@FindBy(xpath = "/html/body/div[7]/div[1]/section[2]/div/div/div/div[2]/div/div/div/input")
	private Link txtLocalSpecialsZipCode;

	// *********************
	// ** Build page area **
	// *********************	// *********************
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
	public SecondaryNavigation(TestEnvironment te){
		super(te);
		ElementFactory.initElements(getDriver(), this);  
	}

	// ****************************************
	// *** SecondaryNavigation Interactions ***
	// ****************************************
	
	/**
	 * @author Waightstill W Avery
	 * @param - N/A
	 * @return - N/A
	 * @summary - Clicks all links in the secondary navigation bar and validates
	 *          that the page loads..
	 */
	public void navigateAllSecondaryNavigationTabs(){
		openSelectVehicleDropdown();
		closeSelectVehicleDropdown();
		openShoppingToolsDropdown();
		closeShoppingToolsDropdown();
		clickFindADealer();
		clickBuildAndPrice();
		clickLocalSpecials();
	}
	
	/**
	 * @author Waightstill W Avery
	 * @param - N/A
	 * @return - N/A
	 * @summary - Clicks the "Select Vehicle" link to open the dropdown.
	 */
	@Step("Open Select Vehicle Dropdown")
	private void openSelectVehicleDropdown(){
		TestReporter.log("Click open 'Select Vehicle'");
		//Click the link
		btnSelectVehicle.jsClick(getDriver());
		
		//Loop until the dropdown is open
		loopCounter = 0;
		do{
			Sleeper.sleep(1000);
			//Ensure the DOM is loaded
			pageLoaded().isDomComplete();
			//Initialize all elements
			initializePage(this.getClass());
			//Iterate the counter
			loopCounter++;
			//Ensure the timeout has not been reached
			Assert.assertEquals(loopCounter != timeout, true, "The Select Vehicle dropdown did not open after ["+String.valueOf(timeout)+"] seconds.");
		}while(!btnSelectVehicle.getAttribute("class").contains("open"));
	}
	
	/**
	 * @author Waightstill W Avery
	 * @param - N/A
	 * @return - N/A
	 * @summary - Clicks the "Select Vehicle" link to close the dropdown.
	 */
	@Step("Close Select Vehicle Dropdown")
	private void closeSelectVehicleDropdown(){
		TestReporter.log("Click closed 'Select Vehicle'");
		//Click the link
		btnSelectVehicle.jsClick(getDriver());
		
		//Loop until the dropdown is closed
		loopCounter = 0;
		do{
			Sleeper.sleep(1000);
			//Ensure the DOM is loaded
			pageLoaded().isDomComplete();
			//Initialize all elements
			initializePage(this.getClass());
			//Iterate the counter
			loopCounter++;
			//Ensure the timeout has not been reached
			Assert.assertEquals(loopCounter != timeout, true, "The Select Vehicle dropdown was not closed after ["+String.valueOf(timeout)+"] seconds.");
		}while(btnSelectVehicle.getAttribute("class").contains("open"));
	}
	
	/**
	 * @author Waightstill W Avery
	 * @param - N/A
	 * @return - N/A
	 * @summary - Clicks the "Shopping Tools" link to open the dropdown.
	 */
	@Step("Open Shopping Tools Dropdown")
	private void openShoppingToolsDropdown(){
		TestReporter.log("Click open 'Shopping Tools'");
		//Click the link
		btnShoppingTools.jsClick(getDriver());
		
		//Loop until the dropdown is open
		loopCounter = 0;
		do{
			Sleeper.sleep(1000);
			//Ensure the DOM is loaded
			pageLoaded().isDomComplete();
			//Initialize all elements
			initializePage(this.getClass());
			//Iterate the counter
			loopCounter++;
			//Ensure the timeout has not been reached
			Assert.assertEquals(loopCounter != timeout, true, "The Shopping Tools dropdown did not open after ["+String.valueOf(timeout)+"] seconds.");
		}while(!btnShoppingTools.getAttribute("class").contains("open"));
	}
	
	/**
	 * @author Waightstill W Avery
	 * @param - N/A
	 * @return - N/A
	 * @summary - Clicks the "Shopping Tools" link to close the dropdown.
	 */
	@Step("Close Shopping Tools Dropdown")
	private void closeShoppingToolsDropdown(){
		TestReporter.log("Click closed 'Shopping Tools'");
		//CLick the link
		btnShoppingTools.jsClick(getDriver());
		
		//Loop until the dropdown is closed
		loopCounter = 0;
		do{
			Sleeper.sleep(1000);
			//Ensure the DOM is loaded
			pageLoaded().isDomComplete();
			//Initialize all elements
			initializePage(this.getClass());
			//Iterate the counter
			loopCounter++;
			//Ensure the timeout has not been reached
			Assert.assertEquals(loopCounter != timeout, true, "The Shopping Tools dropdown was not closed after ["+String.valueOf(timeout)+"] seconds.");
		}while(btnShoppingTools.getAttribute("class").contains("open"));
	}
	
	/**
	 * @author Waightstill W Avery
	 * @param - N/A
	 * @return - N/A
	 * @summary - Clicks the "Find A Dealer" link and loops until either the
	 *          page is loaded or the test timeout is reached.
	 */
	@Step("Click Find a Dealer")
	private void clickFindADealer(){
		List<WebElement> list = driver.findElements(By.xpath("//*[@id=\"tcom-nav-zip-flyout\"]/div/div/div[2]/div/input"));
		
		TestReporter.log("Click 'Find A Dealer'");
/*		//Grab the number of links on the current page
		List<WebElement> myList = getDriver().findElements(By.tagName("a"));
		int listSize = myList.size();
		TestReporter.log("Number of Inputs = " + String.valueOf(listSize));
		for(int i = 0; i < listSize; i++){
				TestReporter.log("Input " + String.valueOf(i));
				Element ele = new ElementImpl(myList.get(i));
				ele.jsClick(getDriver());
		}*/
		
		//Click the link
		lnkFindADealer.jsClick(getDriver());
		
		// Determine if OS is Windows 7. If so, apply a wait to let the page load
		if(isWindows7()){
			windows7Wait();
		}
		
		//Loop until the number of links changes, thereby indicating that a new page was loaded
		loopCounter = 0;
		List<WebElement> list2;
		do{
			Sleeper.sleep(1000);
			//Ensure the DOM is loaded
			pageLoaded().isDomComplete();
			//Initialize all elements
			initializePage(this.getClass());
			//Iterate the counter
			loopCounter++;
			//Ensure the timeout has not been reached
			Assert.assertNotEquals(loopCounter, timeout, "The 'Find Your Toyota Dealer' page was not loaded after ["+String.valueOf(timeout)+"] seconds.");
			list2 = getDriver().findElements(By.tagName("a"));		
		}while(list2.size() == list.size());
	}
	
	/**
	 * @author Waightstill W Avery
	 * @param - N/A
	 * @return - N/A
	 * @summary - Clicks the "Build & Price" link and loops until either the
	 *          page is loaded or the test timeout is reached.
	 */
	@Step("Click Build and Price")
	private void clickBuildAndPrice(){
		TestReporter.log("Click 'Build & Price'");
		//Grab the number of links on the current page
		List<WebElement> list = getDriver().findElements(By.tagName("a")); 
		
		//Click the link
		lnkBuildAndPrice.jsClick(getDriver());
		
		// Determine if OS is Windows 7. If so, apply a wait to let the page load
		if(isWindows7()){
			windows7Wait();
		}
		
		//Loop until the number of links changes, thereby indicating that a new page was loaded
		loopCounter = 0;
		List<WebElement> list2;
		do{
			Sleeper.sleep(1000);
			//Ensure the DOM is loaded
			pageLoaded().isDomComplete();
			//Initialize all elements
			initializePage(this.getClass());
			//Iterate the counter
			loopCounter++;
			//Ensure the timeout has not been reached
			Assert.assertNotEquals(loopCounter, timeout, "The 'Build Your Toyota' page was not loaded after ["+String.valueOf(timeout)+"] seconds.");
			//Grab the number of links on the current page for comparison
			list2 = getDriver().findElements(By.tagName("a"));		
		}while(list2.size() == list.size());
	}
	
	/**
	 * @author Waightstill W Avery
	 * @param - N/A
	 * @return - N/A
	 * @summary - Clicks the "Local Specials" link and loops until either the
	 *          page is loaded or the test timeout is reached.
	 */
	@Step("Click Local Specials")
	private void clickLocalSpecials(){
		TestReporter.log("Click 'Local Specials'");
//		initializePage(this.getClass());
//		pageLoaded(this.getClass(), lnkLocalSpecials);
		//Grab the number of links on the current page
		List<WebElement> list = getDriver().findElements(By.tagName("a")); 		
		
		//Click the link
		lnkLocalSpecials.jsClick(getDriver());
		
		// Determine if OS is Windows 7. If so, apply a wait to let the page load
		if(isWindows7()){
			windows7Wait();
		}
		
		//Loop until the number of links changes, thereby indicating that a new page was loaded
		loopCounter = 0;
		List<WebElement> list2;
		do{
			Sleeper.sleep(1000);
			//Ensure the DOM is loaded
			pageLoaded().isDomComplete();
			//Initialize all elements
			initializePage(this.getClass());
			//Iterate the counter
			loopCounter++;
			//Ensure the timeout has not been rea
			Assert.assertNotEquals(loopCounter, timeout, "The 'Local Specials' page was not loaded after ["+String.valueOf(timeout)+"] seconds.");
			//Grab the number of links on the current page for comparison
			list2 = getDriver().findElements(By.tagName("a"));		
		}while(list2.size() == list.size());
	}
	
	/**
	 * @author Waightstill W Avery
	 * @param - N/A
	 * @return - N/A
	 * @summary - Performance issues have been seen with the Windows 7
	 *          configurations. This method will allow for an arbitrary wait
	 *          period to allow for the page to load.
	 */
	private void windows7Wait(){
		Sleeper.sleep(windows7Wait);
	}
	
	/**
	 * @author Waightstill W Avery
	 * @param - N/A
	 * @return - true if the operating system under test is Windows 7, false
	 *         otherwise
	 * @summary - Tests to determine if the operating system under test is
	 *          Windows 7
	 */
	private boolean isWindows7(){
		boolean isWindows7 = false;
		if(getOperatingSystem().equalsIgnoreCase("Windows 7")){
			isWindows7 = true;
		}
		
		return isWindows7;
	}
}
