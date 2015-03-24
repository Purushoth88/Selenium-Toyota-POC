package apps.toyota.vehiclesDropdown;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.orasi.core.interfaces.Button;
import com.orasi.core.interfaces.Element;
import com.orasi.core.interfaces.impl.internal.ElementFactory;
import com.orasi.utils.PageLoaded;
import com.orasi.utils.WebDriverSetup;

/**
 * @summary Contains the methods & objects for the Toyota.com Vehicles Dropdown page
 * @version Created 03/23/2015
 * @author Waightstill W. Avery
 */
public class VehiclesDropdown {
	// *******************************
	// *** VehiclesDropdown Fields ***
	// *******************************
	int timeout = WebDriverSetup.getDefaultTestTimeout();
	int loopCounter = 0;

	// *********************************
	// *** VehiclesDropdown Elements ***
	// *********************************
	@FindBy(id = "tcom-vehicles-dropdown")
	private Element eleVehiclesDropdown;
	
	@FindBy(linkText = "Build your own")
	private List<WebElement> lnkBuildYourOwn;

	// *********************
	// ** Build page area **
	// *********************
	private WebDriver driver;

	/**
	 * 
	 * @summary Constructor to initialize the page
	 * @version Created 03/23/2015
	 * @author Waightstill W Avery
	 * @param driver
	 * @throws NA
	 * @return NA
	 * 
	 */
	public VehiclesDropdown(WebDriver driver){
		this.driver = driver;	
		ElementFactory.initElements(driver, this);  
	}

	public boolean pageLoaded() {
		return new PageLoaded().isDomComplete(driver);
	}

	public boolean pageLoaded(Element element) {
		return new PageLoaded().isElementLoaded(this.getClass(), driver, element);
	}

	public VehiclesDropdown initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	// *************************************
	// *** VehiclesDropdown Interactions ***
	// *************************************
	
	private void clickCarsAndMinivan(){
		List<WebElement> buttons = driver.findElements(By.tagName("button"));
		Iterator buttonIt = buttons.iterator();
		
		boolean buttonFound = false;
		do{
			
		}while(!buttonFound);
	}
}
