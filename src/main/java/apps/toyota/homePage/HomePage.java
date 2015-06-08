package apps.toyota.homePage;

import org.openqa.selenium.support.FindBy;

import com.orasi.core.interfaces.Element;
import com.orasi.core.interfaces.impl.internal.ElementFactory;
import com.orasi.utils.Page;
import com.orasi.utils.TestEnvironment;
import com.orasi.utils.TestReporter;

/**
 * @summary Contains the methods & objects for the Toyota.com homepage
 * @version Created 03/01/2015
 * @author Waightstill W. Avery
 */
public class HomePage{
	// ***********************
	// *** HomePage Fields ***
	// ***********************
    	TestEnvironment te = null;	
	int timeout = te.getDefaultTestTimeout();
	int loopCounter = 0;
	
	// *************************
	// *** HomePage Elements ***
	// *************************
	@FindBy(id = "marquee")
	private Element eleMarquee;


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
	public HomePage(TestEnvironment te){
	    this.te = te;
	    TestReporter.assertTrue(te.pageLoaded(), "Verify Homepage is loaded");
	    ElementFactory.initElements(te.getDriver(), this);  
	}

	// *****************************
	// *** HomePage Interactions ***
	// *****************************
}
