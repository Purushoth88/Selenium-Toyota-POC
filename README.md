## Toyota Selenium-Java Project

This is the homepage for the Orasi-developed Selenium-Java Toyota project. These libraries contain both original code created by Orasi developers and open source code from various other projects which are listed further down. With these libraries, consumers have access to extended functionality for creating testing suites for Web Applications and API Web Services.

## Web Application Testing
The core code uses the Java-based Selenium webdriver for GUI testing. It takes the standard Selenium-defined WebElement and divides them into more consumer-friendly Elements, whose names are commonly found in HTML DOMs. The following are Elements, and the prefixes used in the code to identify them, as well as examples using the naming conventions:

* Button - btn (ex: btnContinue)
* Checkbox - chk (ex: chkAgreeToTermsAndConditions))
* Label - lbl (ex: lblHeader)
* Link - lnk (ex: lnkCancel)
* Listbox  - lst (ex: lstStates)
* Radiogroup - rad (ex: radYesOrNo)
* Textbox - txt (ex: txtUsername)
* Webtable - tab (ex: tabMemberNames)

All elements utilize the Orasi-developed [TestReporter](https://github.com/waitsavery/Toyota/blob/master/src/main/java/com/orasi/utils/TestReporter.java) which extends the [TestNG](https://github.com/cbeust/testng) Reporter by concatenating a timestamp and HTML formatting for use in viewing the report in a webbrowser. This allows for a functional audit which can ensure requirements are being met by the automated test, as well as provide steps to reproduce a defect when one occurs.

## API Web Service Testing
###Soap Services  
These libraries contain a SOAP API testing solution which leverages Java and W3C functionalities to dynamically build requests at runtime, modify, send and recieve 

###REST Services  
*ToDo*

## Jenkins Build Tool

## Sauce Labs Remote VM Farm

## Third Party Resources
These resources are being used directly, or have been extended upon.
* [Selenium 2.44](https://github.com/SeleniumHQ/selenium): The base library that allows for automation of web browsers.
* [TestNG 6.8.7](https://github.com/cbeust/testng/): Test execution framework that extends JUnit tests and allows more flexibility for testing.
* [Smartbear SoapUI 4.5.0](https://github.com/SmartBear/soapui): Allows consumer to build requests files at runtime and sends request through HTTPClient
* **More to come...**
	
## Accessing this module
* Clone/Fork Git repository: This will be the preferred method if the consumer does not want all the functionality of the libraries,
							but still wants access to some. This also allows the consumer to control what Maven dependencies are used and what functionality he wants to keep or tweak.

* Link Git repository as Maven Module: This method will separate the core functionality from the consumers source code. The core module
										can then be pulled at any time for general updates, and allows consumers to control what updates
										are allowed. This is recommended for advanced Maven and GIT users.

* Add as Maven Dependency: By adding the Orasi Selenium Core Jar to the Maven POM file, the consumer will always have easy access to
							updated code. This also allows the option for versioning in the case the consumer needs to test the core
							updates before fully committing to them.
