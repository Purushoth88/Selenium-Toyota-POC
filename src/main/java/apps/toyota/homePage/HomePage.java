package apps.toyota.homePage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.orasi.core.interfaces.Element;
import com.orasi.core.interfaces.impl.internal.ElementFactory;
import com.orasi.utils.PageLoaded;


/**
 * @summary Contains the methods & objects for the Toyota.com homepage
 * @version Created 09/10/2014
 * @author Waightstill W. Avery
 */
public class HomePage {

	// *******************************************
	// *** Accommodation WrapUp Frame Elements ***
	// *******************************************
	@FindBy(id = "marquee")
	private Element eleMarquee;

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
	public HomePage(WebDriver driver){
		this.driver = driver;	
		ElementFactory.initElements(driver, this);  
	}

	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, eleMarquee);
	}

	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element);
	}

	public HomePage initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	// ***********************************************
	// *** HomePage Interactions ***
	// ***********************************************
}
