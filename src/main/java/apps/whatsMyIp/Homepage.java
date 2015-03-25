package apps.whatsMyIp;

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
import com.orasi.core.interfaces.impl.internal.ElementFactory;
import com.orasi.utils.PageLoaded;
import com.orasi.utils.Sleeper;
import com.orasi.utils.WebDriverSetup;

public class Homepage {

	// ***************************
	// *** Homepage Bar Fields ***
	// ***************************
	int timeout = WebDriverSetup.getDefaultTestTimeout();
	int loopCounter = 0;
	
	// *****************************
	// *** Homepage Bar Elements ***
	// *****************************
	@FindBy(id = "ip")
	private Element eleIpAddress;
	
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
	public Homepage(WebDriver driver){
		this.driver = driver;	
		ElementFactory.initElements(driver, this);  
	}

	public boolean pageLoaded() {
		return new PageLoaded().isDomComplete(driver);
	}

	public boolean pageLoaded(Element element) {
		return new PageLoaded().isElementLoaded(this.getClass(), driver, element);
	}

	public Homepage initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	// *****************************
	// *** Homepage Interactions ***
	// *****************************
	
	public String getIpAddress(){
		pageLoaded(eleIpAddress);
		return eleIpAddress.getText();
	}
}
