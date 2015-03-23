package apps.toyota.homePage;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.orasi.core.interfaces.Element;
import com.orasi.core.interfaces.impl.internal.ElementFactory;
import com.orasi.utils.PageLoaded;
import com.orasi.utils.WebDriverSetup;


/**
 * @summary Contains the methods & objects for the Toyota.com homepage
 * @version Created 03/01/2015
 * @author Waightstill W. Avery
 */
public class HomePage {
	// ***********************
	// *** HomePage Fields ***
	// ***********************
	int timeout = WebDriverSetup.getDefaultTestTimeout();
	int loopCounter = 0;
	
	// *************************
	// *** HomePage Elements ***
	// *************************
	@FindBy(id = "marquee")
	private Element eleMarquee;

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
	public HomePage(WebDriver driver){
		this.driver = driver;	
		ElementFactory.initElements(driver, this);  
	}

	public boolean pageLoaded() {
		return new PageLoaded().isDomComplete(driver);
	}

	public boolean pageLoaded(Element element) {
		return new PageLoaded().isElementLoaded(this.getClass(), driver, element);
	}

	public HomePage initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	// *****************************
	// *** HomePage Interactions ***
	// *****************************
}
