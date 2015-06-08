package com.orasi.utils;

/**
 * An enumeration of Browsers that Selenium 2 uses.
 *
 */
public enum Browser {
    CHROME("chrome"),
    FIREFOX("firefox"),
    INTERNET_EXPLORER("ie"),
    SAFARI("safari"), 
    HTMLUNIT("htmlunit");
    
    String moniker;
    
    Browser(String moniker) {
        this.moniker = moniker;
    }
}
