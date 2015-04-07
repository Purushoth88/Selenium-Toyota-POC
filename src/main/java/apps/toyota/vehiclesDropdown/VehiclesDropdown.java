package apps.toyota.vehiclesDropdown;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.orasi.core.interfaces.Element;
import com.orasi.core.interfaces.impl.internal.ElementFactory;
import com.orasi.utils.TestEnvironment;

/**
 * @summary Contains the methods & objects for the Toyota.com Vehicles Dropdown page
 * @version Created 03/23/2015
 * @author Waightstill W. Avery
 */
public class VehiclesDropdown extends com.orasi.utils.TestEnvironment{
	// *******************************
	// *** VehiclesDropdown Fields ***
	// *******************************
	int timeout = getDefaultTestTimeout();
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
	public VehiclesDropdown(TestEnvironment te){
		super(te);
		ElementFactory.initElements(getDriver(), this);  
	}

	public boolean pageLoaded(Element element) {
		return this.pageLoaded(this.getClass(), element);
	}

	public void initializePage() {
		this.initializePage(this.getClass());	
	}

	// *************************************
	// *** VehiclesDropdown Interactions ***
	// *************************************
	
	private void clickCarsAndMinivan(){
		List<WebElement> buttons = getDriver().findElements(By.tagName("button"));
		Iterator buttonIt = buttons.iterator();
		
		boolean buttonFound = false;
		do{
			
		}while(!buttonFound);
	}
}
