package apps.toyota.secondaryNavigation;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.orasi.core.interfaces.Button;
import com.orasi.core.interfaces.Element;
import com.orasi.core.interfaces.Link;
import com.orasi.core.interfaces.Textbox;
import com.orasi.core.interfaces.impl.ElementImpl;
import com.orasi.core.interfaces.impl.internal.ElementFactory;
import com.orasi.utils.PageLoaded;
import com.orasi.utils.Sleeper;
import com.orasi.utils.WebDriverSetup;


/**
 * @summary Contains the methods & objects for the Toyota.com Secondary Navigation bar
 * @version Created 09/10/2014
 * @author Waightstill W. Avery
 */
public class SecondaryNavigation {
	// ***************************************
	// *** Secondary Navigation Bar Fields ***
	// ***************************************
	int timeout = WebDriverSetup.getDefaultTestTimeout();
	int loopCounter = 0;
	
	
	// *****************************************
	// *** Secondary Navigation Bar Elements ***
	// *****************************************
	
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
	public SecondaryNavigation(WebDriver driver){
		this.driver = driver;	
		ElementFactory.initElements(driver, this);  
	}

	public boolean pageLoaded() {
		return new PageLoaded().isDomComplete(driver);
	}

	public boolean pageLoaded(Element element) {
		return new PageLoaded().isElementLoaded(this.getClass(), driver, element);
	}

	public SecondaryNavigation initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	// ***********************************************
	// *** HomePage Interactions ***
	// ***********************************************
	
	public void navigateAllSecondaryNavigationTabs(){
		openSelectVehicleDropdown();
		closeSelectVehicleDropdown();
		openShoppingToolsDropdown();
		closeShoppingToolsDropdown();
		clickFindADealer();
		clickBuildAndPrice();
		clickLocalSpecials();
	}

	private void openSelectVehicleDropdown(){
		//btnSelectVehicle.highlight(driver);
		btnSelectVehicle.click();
		loopCounter = 0;
		do{
			Sleeper.sleep(1000);
			loopCounter++;
			Assert.assertEquals(loopCounter != timeout, true, "The Select Vehicle dropdown did not open after ["+String.valueOf(timeout)+"] seconds.");
		}while(!btnSelectVehicle.getAttribute("class").contains("open"));
	}
	
	private void closeSelectVehicleDropdown(){
		//btnSelectVehicle.highlight(driver);
		btnSelectVehicle.click();
		loopCounter = 0;
		do{
			Sleeper.sleep(1000);
			loopCounter++;
			Assert.assertEquals(loopCounter != timeout, true, "The Select Vehicle dropdown was not closed after ["+String.valueOf(timeout)+"] seconds.");
		}while(btnSelectVehicle.getAttribute("class").contains("open"));
	}
	
	private void openShoppingToolsDropdown(){
		//btnShoppingTools.highlight(driver);
		btnShoppingTools.click();
		loopCounter = 0;
		do{
			Sleeper.sleep(1000);
			loopCounter++;
			Assert.assertEquals(loopCounter != timeout, true, "The Shopping Tools dropdown did not open after ["+String.valueOf(timeout)+"] seconds.");
		}while(!btnShoppingTools.getAttribute("class").contains("open"));
	}
	
	private void closeShoppingToolsDropdown(){
		//btnShoppingTools.highlight(driver);
		btnShoppingTools.click();
		loopCounter = 0;
		do{
			Sleeper.sleep(1000);
			loopCounter++;
			Assert.assertEquals(loopCounter != timeout, true, "The Shopping Tools dropdown was not closed after ["+String.valueOf(timeout)+"] seconds.");
			initialize();
			pageLoaded(btnShoppingTools);
		}while(btnShoppingTools.getAttribute("class").contains("open"));
	}
	
	private void clickFindADealer(){
		//Grab the number of links on the current page
		List<WebElement> list = driver.findElements(By.tagName("a")); 
		lnkFindADealer.jsClick(driver);
		initialize();
		//Loop until the number of links changes, thereby indicating that a new page was loaded
		loopCounter = 0;
		List<WebElement> list2;
		do{
			Sleeper.sleep(1000);
			loopCounter++;
			Assert.assertNotEquals(loopCounter, timeout);
			list2 = driver.findElements(By.tagName("a"));		
		}while(list2.size() == list.size());
	}
	
	private void clickBuildAndPrice(){
		//Grab the number of links on the current page
		List<WebElement> list = driver.findElements(By.tagName("a")); 
		//Click the link
		lnkBuildAndPrice.jsClick(driver);
		initialize();
		//Loop until the number of links changes, thereby indicating that a new page was loaded
		loopCounter = 0;
		List<WebElement> list2;
		do{
			Sleeper.sleep(1000);
			loopCounter++;
			Assert.assertNotEquals(loopCounter, timeout);
			list2 = driver.findElements(By.tagName("a"));		
		}while(list2.size() == list.size());
	}
	
	private void clickLocalSpecials(){
		//lnkLocalSpecials.highlight(driver);
		lnkLocalSpecials.click();
		if(!pageLoaded(txtLocalSpecialsZipCode)){
			initialize();
			lnkLocalSpecials.jsClick(driver);
			Assert.assertEquals(pageLoaded(txtLocalSpecialsZipCode), true, "The 'Build Your Toyota' page was not loaded.");
		}
	}
}
