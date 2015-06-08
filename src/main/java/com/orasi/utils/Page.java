package com.orasi.utils;
import org.openqa.selenium.WebDriver;

import apps.toyota.homePage.HomePage;
import apps.toyota.mainNavigation.MainNavigation;
import apps.toyota.secondaryNavigation.SecondaryNavigation;
import apps.toyota.vehiclesDropdown.VehiclesDropdown;

import com.orasi.core.interfaces.Element;
import com.orasi.core.interfaces.impl.internal.ElementFactory;

public class Page extends ElementFactory{
    protected TestEnvironment te = null;
    
    public Page(){};
    
    public Page(TestEnvironment te){
	this.te =te;
    }
    
    public WebDriver getDriver(){
	return te.getDriver();
    }

    
    // ************************************
    // ************************************
    // ************************************
    // PAGE Instantiations
    // ************************************
    // ************************************
    // ************************************    
    public HomePage homepage(){
	return new HomePage(te);
    }
    
    public MainNavigation mainNavigation(){
	return   new MainNavigation(te);
    }
    
    public SecondaryNavigation secondaryNavigation(){
	return new SecondaryNavigation(te);
    }
    
    public VehiclesDropdown vehiclesDropdown(){
	return new VehiclesDropdown(te);
    }
    
    // ************************************
    // ************************************
    // ************************************
    // PAGE OBJECT METHODS
    // ************************************
    // ************************************
    // ************************************

    /**
     * @summary loops for a predetermined amount of time (defined by
     *          WebDriverSetup.getDefaultTestTimeout()) to determine if the DOM
     *          is in a ready-state
     * @return boolean: true is the DOM is completely loaded, false otherwise
     * @param N
     *            /A
     */
    protected boolean pageLoaded() {
	return new PageLoaded().isDomComplete(getDriver());
    }

    /**
     * @summary loops for a predetermined amount of time (defined by
     *          WebDriverSetup.getDefaultTestTimeout()) to determine if the
     *          Element is not null
     * @return boolean: true is the DOM is completely loaded, false otherwise
     * @param clazz
     *            - page class that is calling this method
     * @param element
     *            - element with which to determine if a page is loaded
     */
    protected boolean pageLoaded(Class<?> clazz, Element element) {

	return new PageLoaded().isElementLoaded(clazz, getDriver(), element);
    }

    /**
     * @summary Used to create all page objects WebElements as proxies (not
     *          actual objects, but rather placeholders) or to reinitialize all
     *          page object WebElements to allow for the state of a page to
     *          change and not fail a test
     * @return N/A
     * @param clazz
     *            - page class that is calling this method for which to
     *            initialize elements
     */
    protected void initializePage(Class<?> clazz) {
	try {
	    initElements(getDriver(), clazz.getConstructor(TestEnvironment.class));
	} catch (NoSuchMethodException | SecurityException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
}
