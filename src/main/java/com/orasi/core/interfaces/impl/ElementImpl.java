package com.orasi.core.interfaces.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.StopWatch;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByClassName;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.orasi.core.interfaces.Element;
import com.orasi.utils.Constants;
import com.orasi.utils.TestReporter;

/**
 * An implementation of the Element interface. Delegates its work to an
 * underlying WebElement instance for custom functionality.
 */
public class ElementImpl implements Element {

	protected WebElement element;
	private java.util.Date date = new Date();
	private java.util.Date dateAfter = new Date();
	private String message = "";
	private StopWatch stopwatch = new StopWatch();

	public ElementImpl(final WebElement element) {
		this.element = element;
	}

	    
	/**
	 * @see org.openqa.selenium.WebElement#click()
	 */
	@Override
	public void click() {
	    	try{
	    	    element.click();
	    	}catch(RuntimeException rte){
	    	    TestReporter.interfaceLog("Clicked [ <font size = 2 color=\"red\"><b>@FindBy: " + getElementLocatorInfo()
        		+ " </font></b>]");
	    	}
		TestReporter.interfaceLog("Clicked [ <b>@FindBy: " + getElementLocatorInfo()
				+ " </b>]");
	}

	@Override
	public void jsClick(WebDriver driver) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript(
				"arguments[0].scrollIntoView(true);arguments[0].click();",
				element);
		TestReporter.interfaceLog("Clicked [ <b>@FindBy: " + getElementLocatorInfo()
				+ " </b>]");
	}

	@Override
	public void focus(WebDriver driver) {
		new Actions(driver).moveToElement(element).perform();
	}

	@Override
	public void focusClick(WebDriver driver) {
		new Actions(driver).moveToElement(element).click().perform();
		TestReporter.interfaceLog("Focus Clicked [ <b>@FindBy: " + getElementLocatorInfo()
				+ " </b>]");
	}

	/**
	 * @see org.openqa.selenium.WebElement#getLocation()
	 */
	@Override
	public Point getLocation() {
		return element.getLocation();
	}

	/**
	 * @see org.openqa.selenium.WebElement#submit()
	 */
	@Override
	public void submit() {
		element.submit();
	}

	/**
	 * @see org.openqa.selenium.WebElement#getAttribute()
	 */
	@Override
	public String getAttribute(String name) {
		return element.getAttribute(name);
	}

	/**
	 * @see org.openqa.selenium.WebElement#getCssValue()
	 */
	@Override
	public String getCssValue(String propertyName) {
		return element.getCssValue(propertyName);
	}

	/**
	 * @see org.openqa.selenium.WebElement#getSize()
	 */
	@Override
	public Dimension getSize() {
		return element.getSize();
	}

	/**
	 * @see org.openqa.selenium.WebElement#findElements()
	 */
	@Override
	public List<WebElement> findElements(By by) {
		return element.findElements(by);
	}

	/**
	 * @see org.openqa.selenium.WebElement#getText()
	 */
	@Override
	public String getText() {
		return element.getText();
	}

	/**
	 * @see org.openqa.selenium.WebElement#getTagName()
	 */
	@Override
	public String getTagName() {
		return element.getTagName();
	}

	/**
	 * @see org.openqa.selenium.WebElement#findElement()
	 */
	@Override
	public WebElement findElement(By by) {
		return element.findElement(by);
	}

	/**
	 * @see org.openqa.selenium.WebElement#isEnabled()
	 */
	@Override
	public boolean isEnabled() {
		return element.isEnabled();
	}

	/**
	 * @see org.openqa.selenium.WebElement#isDisplayed()
	 */
	@Override
	public boolean isDisplayed() {
		return element.isDisplayed();
	}

	/**
	 * @see org.openqa.selenium.WebElement.isSelected()
	 */
	@Override
	public boolean isSelected() {
		return element.isSelected();
	}

	/**
	 * @see org.openqa.selenium.WebElement#clear()
	 */
	@Override
	public void clear() {
		element.clear();
	}

	/**
	 * @see org.openqa.selenium.WebElement#sendKeys()
	 */
	@Override
	public void sendKeys(CharSequence... keysToSend) {
		if (keysToSend.toString() != "") {
			element.sendKeys(keysToSend);
			TestReporter.interfaceLog(" :: Send Keys [ <b>"
					+ keysToSend.toString()
					+ "</b> ] to Textbox [ <b>@FindBy: "
					+ getElementLocatorInfo() + " </b> ]");
		}
	}

	/**
	 * @see org.openqa.selenium.WebElement#getWrappedElement()
	 */
	@Override
	public WebElement getWrappedElement() {
		return element;
	}

	/**
	 * @see org.openqa.selenium.internal.Locatable#getCoordinates();
	 */
	@Override
	public Coordinates getCoordinates() {
		return ((Locatable) element).getCoordinates();
	}

	@Override
	public boolean elementWired() {
		return (element != null);
	}

	/**
	 * Used in conjunction with WebObjectPresent to determine if the desired
	 * element is present in the DOM Will loop for the time out listed in
	 * com.orasi.utils.Constants If object is not present within the time, throw
	 * an error
	 * 
	 * @author Justin
	 */
	@Override
	public boolean syncPresent(WebDriver driver) {
		return syncPresent(driver, Constants.DEFAULT_GLOBAL_DRIVER_TIMEOUT);
	}

	/**
	 * Used in conjunction with WebObjectPresent to determine if the desired
	 * element is present in the DOM Will loop for the time out passed in
	 * parameter timeout If object is not present within the time, throw an
	 * error
	 * 
	 * @author Justin
	 */
	public boolean syncPresent(WebDriver driver, int timeout) {
		return syncPresent(driver, timeout, true);
	}

	/**
	 * Used in conjunction with WebObjectPresent to determine if the desired
	 * element is present in the DOM Will loop for the time out passed in
	 * parameter timeout If object is not present within the time, handle error
	 * based on returnError
	 * 
	 * @author Justin
	 */
	@Override
	public boolean syncPresent(WebDriver driver, int timeout,
			boolean returnError) {
		boolean found = false;
		double loopTimeout = 0;
		By locator = getElementLocator();
		loopTimeout = timeout * 10;
		TestReporter.interfaceLog("<i> Syncing to element [ <b>@FindBy: "
				+ getElementLocatorInfo()
				+ "</b> ] to be <b>PRESENT</b> in DOM within [ " + timeout
				+ " ] seconds.</i>");
		
		stopwatch = new StopWatch();
		stopwatch.start();
		for (double seconds = 0; seconds < loopTimeout; seconds += 1) {

			if (webElementPresent(driver, locator)) {
				found = true;
				break;
			}
			try {
				Thread.sleep(100);
			} catch (Exception e) {}
		}
		stopwatch.stop();
		
		message = "<i>Element [<b>@FindBy: "
				+ getElementLocatorInfo()
				+ " </b>] is <<**>><b>PRESENT</b> on the page after [ "
				+ String.valueOf((stopwatch.getTime()) / 1000.0)
				+ " ] seconds.</i>";
		if (!found && returnError) {
			message = message.replace("<<**>>", "not ");
			TestReporter.logFailure(message);
			throw new RuntimeException(message);
		}else{
			message = message.replace("<<**>>", "");
			TestReporter.interfaceLog(message);
		}
		return found;
	}

	/**
	 *
	 * Used in conjunction with WebObjectVisible to determine if the desired
	 * element is visible on the screen Will loop for the time out listed in
	 * org.orasi.chameleon.CONSTANT.TIMEOUT If object is not visible within the
	 * time, throw an error
	 * 
	 * @author Justin
	 */
	@Override
	public boolean syncVisible(WebDriver driver) {
	    return syncVisible(driver, Constants.DEFAULT_GLOBAL_DRIVER_TIMEOUT);
	}

	/**
	 * Used in conjunction with WebObjectVisible to determine if the desired
	 * element is visible on the screen Will loop for the time out passed in the
	 * variable timeout If object is not visible within the time, throw an error
	 * 
	 * @author Justin
	 * 
	 */
	public boolean syncVisible(WebDriver driver, int timeout) {
	    return syncVisible(driver, timeout, true);
	}

	/**
	 * Used in conjunction with WebObjectVisible to determine if the desired
	 * element is visible on the screen Will loop for the time out passed in the
	 * variable timeout If object is not visible within the time, handle the
	 * error based on the boolean
	 *
	 * @author Justin
	 *
	 */
	@Override
	public boolean syncVisible(WebDriver driver, int timeout, boolean returnError) {
		boolean found = false;
		double loopTimeout = 0;

		loopTimeout = Integer.valueOf(timeout) * 10;
		TestReporter.interfaceLog("<i>Syncing to element [<b>@FindBy: "
				+ getElementLocatorInfo()
				+ "</b> ] to be <b>VISIBLE<b> within [ " + timeout
				+ " ] seconds.</i>");

		stopwatch = new StopWatch();
		stopwatch.start();
		for (double seconds = 0; seconds <= loopTimeout; seconds += 1) {

			if (webElementVisible(driver, element)) {
				found = true;
				break;
			}
			try {
				Thread.sleep(100);
			} catch (Exception e) {}
		}
		stopwatch.stop();
		
		message = "Element [ @FindBy: "
				+ getElementLocatorInfo()
				+ " ] is <<**>>VISIBLE on the page after [ "
				+ String.valueOf((stopwatch.getTime()/1000.0))
				+ " ] seconds.";
		if (!found && returnError) {
			message = message.replace("<<**>>", "not ");
			TestReporter.logFailure(message);
			throw new RuntimeException(message);
		}else{
			message = message.replace("<<**>>", " ");
			TestReporter.interfaceLog(message);
		}
		return found;
	}

	/**
	 * Used in conjunction with WebObjectVisible to determine if the desired
	 * element is hidden from the screen Will loop for the time out listed in
	 * org.orasi.chameleon.CONSTANT.TIMEOUT If object is not visible within the
	 * time, throw an error
	 * 
	 * @author Justin
	 * */
	@Override
	public boolean syncHidden(WebDriver driver) {
	    return syncHidden(driver, Constants.DEFAULT_GLOBAL_DRIVER_TIMEOUT);
	}

	/**
	 * Used in conjunction with WebObjectVisible to determine if the desired
	 * element is hidden from the screen Will loop for the time out listed in
	 * org.orasi.chameleon.CONSTANT.TIMEOUT If object is not visible within the
	 * time, throw an error
	 * 
	 * @author Justin
	 */
	public boolean syncHidden(WebDriver driver, int timeout) {
	    return syncHidden(driver, timeout, true);
	}

	/**
	 * Used in conjunction with WebObjectVisible to determine if the desired
	 * element is visible on the screen Will loop for the time out passed in the
	 * variable timeout If object is not visible within the time, handle the
	 * error based on the boolean
	 * 
	 * @author Justin
	 */
	@Override
	public boolean syncHidden(WebDriver driver, int timeout, boolean returnError) {
		boolean found = false;
		long loopTimeout = 0;

		loopTimeout = Long.valueOf(timeout) * 10;
		TestReporter.interfaceLog("<i>Syncing to element [<b>@FindBy: "
				+ getElementLocatorInfo()
				+ "</b> ] to be <b>HIDDEN</b> within [ <b>" + timeout
				+ "</b> ] seconds.</i>");

		stopwatch = new StopWatch();
		stopwatch.start();
		for (double seconds = 0; seconds < loopTimeout; seconds += 1) {

			if (!webElementVisible(driver, element)) {
				found = true;
				break;
			}
			try {
				Thread.sleep(100);
			} catch (Exception e) {}
		}
		stopwatch.stop();

		message = "<i>Element [<b>@FindBy: "
				+ getElementLocatorInfo()
				+ " </b>] is <<**>><b>HIDDEN</b> on the page after [ "
				+ (stopwatch.getTime()) / 1000.0
				+ " ] seconds.</i>";
		if (!found && returnError) {
			message = message.replace("<<**>>", "not ");
			TestReporter.logFailure(message);
			throw new RuntimeException(message);
		}else{
			message = message.replace("<<**>>", " ");
			TestReporter.logFailure(message);
		}
		return found;
	}

	/**
	 *
	 * Used in conjunction with WebObjectEnabled to determine if the desired
	 * element is enabled on the screen Will loop for the time out listed in
	 * org.orasi.chameleon.CONSTANT.TIMEOUT If object is not enabled within the
	 * time, throw an error
	 * 
	 * @author Justin
	 */
	@Override
	public boolean syncEnabled(WebDriver driver) {
	    return syncEnabled(driver, Constants.DEFAULT_GLOBAL_DRIVER_TIMEOUT);
	}

	/**
	 * 
	 * Used in conjunction with WebObjectEnabled to determine if the desired
	 * element is enabled on the screen Will loop for the time out passed in the
	 * variable timeout If object is not enabled within the time, throw an error
	 * 
	 * @author Justin
	 * 
	 */
	public boolean syncEnabled(WebDriver driver, int timeout) {
	    return syncEnabled(driver, timeout, true);
	}

	/**
	 * Used in conjunction with WebObjectEnabled to determine if the desired
	 * element is enabled on the screen Will loop for the time out passed in the
	 * variable timeout If object is not enabled within the time, handle the
	 * error based on the boolean
	 *
	 * @author Justin
	 *
	 */
	@Override
	public boolean syncEnabled(WebDriver driver, int timeout,
			boolean returnError) {
		boolean found = false;
		double loopTimeout = 0;

		loopTimeout = Integer.valueOf(timeout) * 10;
		TestReporter.interfaceLog("<i>Syncing to element [<b>@FindBy: "
				+ getElementLocatorInfo()
				+ "</b> ] to be <b>ENABLED</b> within [ <b>" + timeout
				+ "</b> ] seconds.</i>");

		stopwatch = new StopWatch();
		stopwatch.start();
		for (double seconds = 0; seconds < loopTimeout; seconds += 1) {

			if (webElementEnabled(driver, element)) {
				found = true;
				break;
			}
			try {
				Thread.sleep(100);
			} catch (Exception e) {}
		}
		stopwatch.stop();
		
		message = "<i>Element [<b>@FindBy: "
				+ getElementLocatorInfo()
				+ " </b>] is <<**>><b>ENABLED</b> on the page after [ "
				+ String.valueOf((stopwatch.getTime()) / 1000.0)
				+ " ] seconds.</i>";
		if (!found && returnError) {
			message = message.replace("<<**>>", "not ");
			TestReporter.logFailure(message);
			throw new RuntimeException(message);
		}else{
			message = message.replace("<<**>>", " ");
			TestReporter.logFailure(message);
		}
		return found;
	}

	/**
	 *
	 * Used in conjunction with WebObjectEnabled to determine if the desired
	 * element is disabled on the screen Will loop for the time out listed in
	 * org.orasi.chameleon.CONSTANT.TIMEOUT If object is not disabled within the
	 * time, throw an error
	 * 
	 * @author Justin
	 */
	@Override
	public boolean syncDisabled(WebDriver driver) {
	    return syncDisabled(driver, Constants.DEFAULT_GLOBAL_DRIVER_TIMEOUT);
	}

	/**
	 * 
	 * Used in conjunction with WebObjectDisabled to determine if the desired
	 * element is disabled on the screen Will loop for the time out passed in
	 * the variable timeout If object is not disabled within the time, throw an
	 * error
	 * 
	 * @author Justin
	 * 
	 */
	public boolean syncDisabled(WebDriver driver, int timeout) {
	    return syncDisabled(driver, timeout, true);
	}

	/**
	 * Used in conjunction with WebObjectDisabled to determine if the desired
	 * element is disabled on the screen Will loop for the time out passed in
	 * the variable timeout If object is not disabled within the time, handle
	 * the error based on the boolean
	 *
	 * @author Justin
	 *
	 */
	@Override
	public boolean syncDisabled(WebDriver driver, int timeout,
			boolean returnError) {
		boolean found = false;
		double loopTimeout = 0;

		loopTimeout = Integer.valueOf(timeout) * 10;
		TestReporter.interfaceLog("<i>Syncing to element [<b>@FindBy: "
				+ getElementLocatorInfo()
				+ "</b> ] to be <b>DISABLED</b> within [ <b>" + timeout
				+ "</b> ] seconds.</i>");

		stopwatch = new StopWatch();
		stopwatch.start();
		for (double seconds = 0; seconds < loopTimeout; seconds += 1) {

			if (!webElementEnabled(driver, element)) {
				found = true;
				break;
			}
			try {
				Thread.sleep(100);
			} catch (Exception e) {}
		}
		stopwatch.stop();
		
		message = "<i>Element [<b>@FindBy: "
				+ getElementLocatorInfo()
				+ " </b>] is <<**>><b>DISABLED</b> on the page after [ "
				+ String.valueOf((stopwatch.getTime()) / 1000.0)
				+ " ] seconds.</i>";
		if (!found && returnError) {
			message = message.replace("<<**>>", "not ");
			TestReporter.logFailure(message);
			throw new RuntimeException(message);
		}else{
			message = message.replace("<<**>>", " ");
			TestReporter.logFailure(message);			
		}
		return found;
	}

	/**
	 *
	 * Used in conjunction with WebObjectText Present to determine if the
	 * desired text is present in the desired element Will loop for the time out
	 * listed in org.orasi.chameleon.CONSTANT.TIMEOUT If text is not present
	 * within the time, throw an error
	 * 
	 * @author Justin
	 */
	@Override
	public boolean syncTextInElement(WebDriver driver, String text) {
	    return syncTextInElement(driver, text, Constants.DEFAULT_GLOBAL_DRIVER_TIMEOUT);
	}

	/**
	 * 
	 * Used in conjunction with WebObjectText Present to determine if the
	 * desired text is present in the desired element Will loop for the time out
	 * passed in the variable timeout If text is not present within the time,
	 * throw an error
	 * 
	 * @author Justin
	 * 
	 */
	public boolean syncTextInElement(WebDriver driver, String text, int timeout) {		
	    return syncTextInElement(driver, text, Constants.DEFAULT_GLOBAL_DRIVER_TIMEOUT, true);
	}

	/**
	 * Used in conjunction with WebObjectText Present to determine if the
	 * desired text is present in the desired element Will loop for the time out
	 * passed in the variable timeout If text is not present within the time,
	 * handle the error based on the boolean
	 *
	 * @author Justin
	 *
	 */
	@Override
	public boolean syncTextInElement(WebDriver driver, String text,
			int timeout, boolean returnError) {
		boolean found = false;
		double loopTimeout = 0;

		loopTimeout = Integer.valueOf(timeout) * 10;
		TestReporter.interfaceLog("<i>Syncing to text [<b>" + text  + "</b> ] in element [<b>@FindBy: "
				+ getElementLocatorInfo()
				+ "</b> ] to be displayed within [ <b>" + timeout
				+ "</b> ] seconds.</i>");

		stopwatch = new StopWatch();
		stopwatch.start();
		for (double seconds = 0; seconds < loopTimeout; seconds += 1) {

			if (webElementTextPresent(driver, element, text)) {
				found = true;
				break;
			}
			try {
				Thread.sleep(100);
			} catch (Exception e) {}
		}
		stopwatch.stop();

		message = "<i>Element [<b>@FindBy: "
				+ getElementLocatorInfo()
				+ " </b>] did <<**>>contain the text [ " + text
				+ " ] after [ " + String.valueOf((stopwatch.getTime())
				/ 1000.0) + " ] seconds.</i>";
		if (!found && returnError) {
			message = message.replace("<<**>>", "not ");
			TestReporter.logFailure(message);
			throw new RuntimeException(message);
		}else{
			message = message.replace("<<**>>", " ");
			TestReporter.logFailure(message);
		}
		return found;
	}

	/**
	 * Use WebDriver Wait to determine if object is present in the DOM or not
	 * 
	 * @author Justin
	 * @param driver
	 *            Main WebDriver
	 * @param locator
	 *            {@link By} object to search for
	 * @return TRUE if element is currently present in the DOM, FALSE if the
	 *         element is not present in the DOM
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private boolean webElementPresent(WebDriver driver, By locator) {
		Wait wait = new WebDriverWait(driver, 0);

		try {
			return wait.until(ExpectedConditions
					.presenceOfElementLocated(locator)) != null;
		} catch (NoSuchElementException | ClassCastException
				| StaleElementReferenceException | TimeoutException e) {
			return false;
		}

	}

	/**
	 * Use WebDriver Wait to determine if object is visible on the screen or not
	 * 
	 * @author Justin
	 * @param driver
	 *            Main WebDriver
	 * @param element
	 *            Element to search for
	 * @return TRUE if element is currently visible on the screen, FALSE if the
	 *         element is not visible on the screen
	 */
	private boolean webElementVisible(WebDriver driver, WebElement element) {
		try {
			Point location = element.getLocation();

			Dimension size = element.getSize();
			if ((location.getX() > 0 & location.getY() > 0)
					| (size.getHeight() > 0 & size.getWidth() > 0)) {
				return true;
			} else {
				return false;
			}

		} catch (WebDriverException | ClassCastException e) {
			return false;
		}
	}

	/**
	 * Use WebDriver Wait to determine if object is enabled on the screen or not
	 * 
	 * @author Justin
	 * @param driver
	 *            Main WebDriver
	 * @param element
	 *            Element to search for
	 * @return TRUE if element is currently enabled on the screen, FALSE if the
	 *         element is not enabled on the screen
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private boolean webElementEnabled(WebDriver driver, WebElement element) {
		Wait wait = new WebDriverWait(driver, 0);

		try {
			return wait.until(ExpectedConditions.elementToBeClickable(element)) != null;
		} catch (NoSuchElementException | ClassCastException
				| StaleElementReferenceException | TimeoutException e) {
			return false;
		}

	}

	/**
	 * Use WebDriver Wait to determine if object contains the expected text
	 * 
	 * @author Justin
	 * @param driver
	 *            Main WebDriver
	 * @param element
	 *            Element to search for
	 * @param text
	 *            The text to search for
	 * @return TRUE if element is currently visible on the screen, FALSE if the
	 *         element is not visible on the screen
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private boolean webElementTextPresent(WebDriver driver, WebElement element,
			String text) {
		Wait wait = new WebDriverWait(driver, 0);
		try {

			if (wait.until(ExpectedConditions.textToBePresentInElement(element,
					text)) != null) {
				return true;
			} else if (wait.until(ExpectedConditions
					.textToBePresentInElementValue(element, text)) != null) {
				return true;
			} else {
				return false;
			}

		} catch (NoSuchElementException | ClassCastException
				| StaleElementReferenceException | TimeoutException e) {
			try {
				if (wait.until(ExpectedConditions
						.textToBePresentInElementValue(element, text)) != null) {
					return true;
				} else {
					return false;
				}
			} catch (NoSuchElementException | ClassCastException
					| StaleElementReferenceException | TimeoutException e2) {
				return false;
			}
		}
	}

	/**
	 * Get the By Locator object used to create this element
	 * 
	 * @author Justin
	 * @return {@link By} Return the By object to reuse
	 */
	@Override
	public By getElementLocator() {
		By by = null;
		String locator = "";
		int startPosition = 0;
		try {
			startPosition = element.toString().lastIndexOf("->") + 3;
			locator = element.toString().substring(startPosition,
					element.toString().lastIndexOf(":"));
			locator = locator.trim();
			switch (locator) {
			case "className":
				by = new ByClassName(getElementIdentifier());
				break;
			case "cssSelector":
				by = By.cssSelector(getElementIdentifier());
				break;
			case "id":
				by = By.id(getElementIdentifier());
				break;
			case "linkText":
				by = By.linkText(getElementIdentifier());
				break;
			case "name":
				by = By.name(getElementIdentifier());
				break;
			case "tagName":
				by = By.tagName(getElementIdentifier());
				break;
			case "xpath":
				by = By.xpath(getElementIdentifier());
				break;
			}
			return by;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getElementIdentifier() {
		String locator = "";
		int startPosition = 0;
		startPosition = element.toString().lastIndexOf(": ") + 2;
		locator = element.toString().substring(startPosition,
				element.toString().lastIndexOf("]"));

		return locator.trim();
	}

	/**
	 * Get the By Locator object used to create this element
	 * 
	 * @author Justin
	 * @return {@link By} Return the By object to reuse
	 */
	private String getElementLocatorAsString() {
		int startPosition = 0;
		String locator = "";
		startPosition = element.toString().lastIndexOf("->") + 3;
		locator = element.toString().substring(startPosition,
				element.toString().lastIndexOf(":"));

		locator = locator.trim();
		return locator;

	}

	@Override
	public String getElementLocatorInfo() {
		return getElementLocatorAsString() + " = " + getElementIdentifier();
	}

	@Override
	public void highlight(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].style.border='3px solid red'", this);
	}

	@Override
	public void scrollIntoView(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].scrollIntoView(true);", element);
	}
	
	@Override
	public boolean onMouseOver(WebDriver driver)
	{
		//Grab the element
		WebElement element = getWrappedElement();
		boolean result = false;
		try
		{
			//Create the string that will be used as the JS script
			String mouseOverScript = 
					"if(document.createEvent){"
					+ 	"var evObj = document.createEvent('MouseEvents');"
					+ 	"evObj.initEvent('mouseover', true, false);"
					+ 	"arguments[0].dispatchEvent(evObj);}"
					+ "else if(document.createEventObject) { "
					+ 	"arguments[0].fireEvent('onmouseover');}";
			//Create a JS executor
			JavascriptExecutor js = (JavascriptExecutor) driver;
			//Execute the script
			js.executeScript(mouseOverScript, element);
			result = true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			result = false;
		}
		return result;
	}
	
	@Override
	public boolean onMouseOver(WebElement element, WebDriver driver)
	{
		boolean result = false;
		try
		{
			String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript(mouseOverScript, element);
			result = true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			result = false;
		}
		return result;
	}
	
	@Override
	public void moveToElement(WebDriver driver) {
		//Grab the element locator
		By locator = getElementLocator();
		//Create a JS executor
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//Grab the element locator type
		String locatorType = locator.toString().substring(3);
		//Define the beginning string that will be used to define the element
		String elem = "var elem = document;";
		
		/*
		 * Define a JavaScript document method to find elements by certain attributes
		 */
		if (locatorType.startsWith("id")) {
			elem = "var elem = document.getElementById(\""
					+ locatorType.substring(4) + "\");";
		} else if (locatorType.startsWith("xpath")) {
			String snippet = "document.getElementByXPath = function(sValue) { var a = this.evaluate(sValue, this, null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null); if (a.snapshotLength > 0) { return a.snapshotItem(0); } }; ";
			js.executeScript(snippet);
			elem = "var elem = document.getElementByXPath('"
					+ locatorType.substring(7) + "');";
		} else if (locatorType.startsWith("className")) {
			elem = "var elem = document.getElementsByClassName(\""
					+ locatorType.substring(14) + "\")[0];";
		}
		//Concatenate strings to make the mouse over script to be passed to the JS executor
		String mouseOverScript = elem
				+ "if(document.createEvent){"
				+ 	"var evObj = document.createEvent('MouseEvents');"
				+ 	"evObj.initEvent('mouseover', true, false);"
				+ 	"elem.dispatchEvent(evObj);}"
				+ "else if(document.createEventObject) {"
				+ 	"elem.fireEvent('onmouseover');}";
		//Execute the script
		js.executeScript(mouseOverScript);	
	}
	
	@Override
	public void moveToElement(WebDriver driver, WebElement element, By locator) {
		// Create a JS executor
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//Grab the element locator type
		String locatorType = locator.toString().substring(3);
		String elem = "var elem = document;";

		/*
		 * Define a JavaScript document method to find elements by certain
		 * attributes
		 */
		if (locatorType.startsWith("id")) {
			elem = "var elem = document.getElementById(\""
					+ locatorType.substring(4) + "\");";
		} else if (locatorType.startsWith("xpath")) {
			String snippet = "document.getElementByXPath = function(sValue) { var a = this.evaluate(sValue, this, null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null); if (a.snapshotLength > 0) { return a.snapshotItem(0); } }; ";
			js.executeScript(snippet);
			elem = "var elem = document.getElementByXPath(\""
					+ locatorType.substring(7) + "\");";
		} else if (locatorType.startsWith("className")) {
			elem = "var elem = document.getElementsByClassName(\""
					+ locatorType.substring(14) + "\")[0];";
		}
		//Concatenate strings to make the mouse over script to be passed to the JS executor
		String mouseOverScript = elem
				+ "if(document.createEvent){"
				+ 	"var evObj = document.createEvent('MouseEvents');"
				+ 	"evObj.initEvent('mouseover', true, false);"
				+ 	"elem.dispatchEvent(evObj);} "
				+ "else if(document.createEventObject) { "
				+ 	"elem.fireEvent('onmouseover');}";
		//Execute the script
		js.executeScript(mouseOverScript);
	}
	
	public void mouseHoverByJavaScript(WebDriver driver)
	{
		WebElement elem = getWrappedElement();
		String javaScript = "var evObj = document.createEvent('MouseEvents');"
				+ "evObj.initMouseEvent('mouseover',true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
				+ "arguments[0].dispatchEvent(evObj);";
		// Create a JS executor
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(javaScript, elem);
	}
	
	public void coordinateClick(WebDriver driver){
		Element element = new ElementImpl(getWrappedElement());
		int xPos = element.getCoordinates().onPage().x;
		int yPos = element.getCoordinates().onPage().y;
		int width = element.getSize().width;
		int height = element.getSize().height;
		float xMidpoint = (float)xPos + width/2;
		float yMidpoint = (float)yPos + height/2;
		
		String javaScript = "var element = document.elementFromPoint("+xMidpoint+","+yMidpoint+");element.click()";
		
		// Create a JS executor
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(javaScript);
	}
}