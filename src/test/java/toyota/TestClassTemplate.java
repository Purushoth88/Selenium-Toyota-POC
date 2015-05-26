package toyota;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;

import com.orasi.utils.TestEnvironment;
import com.orasi.utils.TestNgTestClassMethods;
import com.saucelabs.testng.SauceOnDemandTestListener;

@Listeners({ SauceOnDemandTestListener.class })
public class TestClassTemplate {
	String testName = null;
	protected TestEnvironment te = null;
	protected TestNgTestClassMethods test = null;

	// **********************
	// After Method Behavior
	// **********************
	@AfterMethod(groups = { "regression" })
	public synchronized void tearDownClass(ITestResult results) {
		test.after(results, te.getDriver());
	}
}
