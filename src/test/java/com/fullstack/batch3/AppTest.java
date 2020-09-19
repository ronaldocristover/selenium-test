package com.fullstack.batch3;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.fullstack.batch3.common.DataUtils;
import com.fullstack.batch3.common.TestUtils;

/**
 * Unit test for simple App.
 */
public class AppTest extends BaseWebTest {

	@Test(groups = {
			"RegressionTest" }, description = "Verify that login is working correctly with correct username and password")

	public void loginWithCorrectCredentials() {

		loginPage.openUrl(DataUtils.getDataFromExcel("WebData", "BaseUrl"));
		loginPage.login(DataUtils.getDataFromExcel("WebData", "Username"), DataUtils.getDataFromExcel("WebData", "Password"));
		String actualUsername = profilePage.getUserProfileName();

		assertEquals(actualUsername, DataUtils.getDataFromExcel("WebData", "Username"));

	}

	@Test(groups = { "SanityTest" }, description = "Verify that the title of website is correct")

	public void verifyTitle() {

		loginPage.openUrl(DataUtils.getDataFromExcel("WebData", "BaseUrl"));
		String title = profilePage.getWebPageTitle();
		assertEquals(title, "Home -");

	}
	
	@Test
	public void testNavigation() {
		commonPage.openUrl(DataUtils.getDataFromExcel("WebData", "BaseUrl"));
		TestUtils.hardWait(5);
		commonPage.openUrl("https://facebook.com");
		TestUtils.hardWait(5);
		commonPage.goBack();
		TestUtils.hardWait(5);
		commonPage.goForward();
		TestUtils.hardWait(5);
		commonPage.refresh();
		TestUtils.hardWait(5);
	}
	
	@Test
	public void testSwitches() {
		commonPage.openUrl(DataUtils.getDataFromExcel("WebData", "BaseUrl"));
		commonPage.createTab();
		commonPage.createWindow();
		TestUtils.hardWait(2);
		commonPage.switchDriver(2);
		TestUtils.hardWait(2);
		commonPage.switchDriver(1);
		TestUtils.hardWait(2);
		commonPage.switchDriver(0);
	}
	
	@Test
	public void testJavaScript() {
		String script = "alert('This is level 2')";
		commonPage.executeJS(script);
		TestUtils.hardWait(2);
		commonPage.acceptAlert();
	}

}
