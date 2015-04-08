package toyota;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import com.orasi.utils.TestEnvironment;
import com.orasi.utils.TestNgTestClassMethods;
import com.saucelabs.testng.SauceOnDemandTestListener;

@Listeners({SauceOnDemandTestListener.class})
public class TestClassTemplate {
	
	String testName = null;
	protected TestEnvironment te = null;
	protected TestNgTestClassMethods test;

	// **********************
	// After Method Behavior
	// **********************
	@AfterMethod(groups = { "regression" })
	public synchronized void tearDownClass_SauceLabs(ITestResult results) {
		test.after_sauceLabs(results, te.getDriver());
	}
	
	@AfterMethod(groups = { "regressions" })
	public synchronized void tearDownClass(ITestResult results) {
		test.after(results, te.getDriver());
	}
	
	@BeforeTest(groups = { "regression" })
	@Parameters({ "runLocation", "browserUnderTest", "browserVersion",
			"operatingSystem", "environment" })
	public void setupClass(String application, String runLocation, String browserUnderTest,
			String browserVersion, String operatingSystem, String environment) {
		this.te = new TestEnvironment(application, browserUnderTest, browserVersion, operatingSystem,
				runLocation, environment);
		this.test = new TestNgTestClassMethods(application, this.te);
	}
}
