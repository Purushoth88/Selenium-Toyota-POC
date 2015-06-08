package com.orasi.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestEnvironment
{
	private static String application = null; 
	private static String browserUnderTest = null;
	private static String browserVersion = null; 
	private static String operatingSystem = null;
	private static String setRunLocation = null; 
	private static String environment = null;

	private static TestEnvironment instance=null;
	private WebDriver driver;
	private WebDriver Mozilla=null;
	
	protected TestEnvironment(String app, String browser, String ver, 
			String os, String uri, String env){		
		application = app; 
		browserUnderTest = browser;
		browserVersion = ver; 
		operatingSystem = os;
		setRunLocation = uri; 
		environment = env;
	}
	
	public static TestEnvironment getInstance(){
		if(instance==null){
			instance = new TestEnvironment(application, browserUnderTest, browserVersion, 
					operatingSystem, setRunLocation, environment);
		}
		return instance;
	}
	
}

