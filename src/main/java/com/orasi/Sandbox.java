package com.orasi;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.orasi.core.interfaces.Element;
import com.orasi.core.interfaces.impl.ElementImpl;
import com.orasi.utils.Base64Coder;
import com.orasi.utils.Constants;
import com.orasi.utils.Sleeper;

public class Sandbox {
	Base64Coder base = new Base64Coder();
	
	@Test
	public void main(){
//		System.out.println(base.encodeString("sarahjane328"));
//		System.out.println(base.encodeString("912bf2a5-6123-4a9f-9265-c662d7c7c60c"));
		
		File file = new File(this.getClass().getResource(Constants.DRIVERS_PATH_LOCAL + "ChromeDriver.exe").getPath());
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().setScriptTimeout(Constants.DEFAULT_GLOBAL_DRIVER_TIMEOUT, TimeUnit.SECONDS).implicitlyWait(Constants.DEFAULT_GLOBAL_DRIVER_TIMEOUT, TimeUnit.SECONDS);	
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get("http://www.toyota.com");
		
		List<WebElement> inputs1 = driver.findElements(By.tagName("input"));
		System.out.println(inputs1.size());
		
		Element eleZipCodePopup = new ElementImpl(driver.findElement(By.id("tcom-nav-zip-flyout")));
		driver.findElement(By.xpath("//*[@id=\"tcom-main-nav\"]/ul/li[3]/button")).click();
		
		int loopCounter = 0;
		int timeout = 20;
		do{
			Sleeper.sleep(1000);
			loopCounter++;
			Assert.assertNotEquals(loopCounter, timeout, "The");
		}while(!eleZipCodePopup.getAttribute("class").toLowerCase().contains("open"));
		
		List<WebElement> inputs2 = driver.findElements(By.tagName("input"));
		System.out.println(inputs2.size());
	}
}
