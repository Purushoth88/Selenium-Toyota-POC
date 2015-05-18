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
 
These libraries contain a SOAP API testing solution which leverages Java and W3C functionalities to dynamically build requests at runtime, modify the requests, transmit and receive SOAP messages, and parse responses for field-level values with which validation can be performed. 

###REST Services  
  
These libraries contain a SOAP API testing solution which leverages the Apache HttpClient libraries. Full XML and JSON support is included to parse responses for field-level values with which validation can be performed. 

## Jenkins Build Tool
This project is designed to have a Jenkins CI instance hook into this repository and detect when changes are pushed to the Master branch. Ideally, this trigger will be changed to look for code changes pushed by developers, thereby triggering a new build to test the new application code.

## Sauce Labs Remote VM Farm

The capabilities of the Sauce Labs VM farm are harnessed to test the OS-Browser configurations required by the project. The Jenkins instance uses a local VM to spin up a webdriver for a test and the webdriver sends the commands to the remote VM to be executed.

## Reporting
* Test NG: Contains results for individual tests using the TestNG-extended Orasi reporter.

* Jenkins CI: Contains results for individual tests using the TestNG-extended Orasi reporter.  Also, keeps a build history to show the relative stability of past builds.

* Sauce Labs: Contains results for individual tests as well as captures videos and screenshots.

## Third Party Resources
These resources are being used directly, or have been extended upon.
* [Selenium 2.43.1](https://github.com/SeleniumHQ/selenium): The base library that allows for automation of web browsers.
* [TestNG 6.8.21](https://github.com/cbeust/testng/): Test execution framework that extends JUnit tests and allows more flexibility for testing.
* [Smartbear SoapUI 4.5.0](https://github.com/SmartBear/soapui): Allows consumer to build requests files at runtime and sends request through HTTPClient
* [Sauce Labs 2.1.18](https://github.com/saucelabs/sauce-java): Facilitates the  use of using the Sauce Lab VM farm as a remote Selenium grid on which to execute tests.
* [Apache HttpClient 4.3.1](https://hc.apache.org/httpcomponents-client-ga/httpclient/apidocs/index.html) Used to transmit and receive SOAP message requests and responses.
