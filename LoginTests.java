package com.appium.tests;

import static com.appium.constants.FrameworkConstants.TEST_DATA_JSON_FILE;
import static com.appium.constants.FrameworkConstants.TEST_DATA_JSON_INVALID_PASSWORD;
import static com.appium.constants.FrameworkConstants.TEST_DATA_JSON_VALID_USER;
import static com.appium.constants.FrameworkConstants.ACCOUNT_PAGE_TITLE;
import static com.appium.constants.FrameworkConstants.TEST_DATA_JSON_INVALID_USER;
import static com.appium.constants.FrameworkConstants.TEST_DATA_JSON_PASSWORD;
import static com.appium.constants.FrameworkConstants.TEST_DATA_JSON_USERNAME;
import static com.appium.constants.FrameworkConstants.EXPECTED_DATA_KEY_ERR_INAVLID_CREDENTIALS;

import java.lang.reflect.Method;

import com.appium.manager.DriverManager;
import io.appium.java_client.InteractsWithApps;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.json.JSONObject;
import org.testng.annotations.*;

import com.appium.annotations.FrameworkAnnotation;
import com.appium.base.BaseTest;
import com.appium.enums.AuthorType;
import com.appium.enums.CategoryType;
import com.appium.manager.StringsManager;
import com.appium.pages.LoginPage;
import com.appium.pages.ProductsPage;
import com.appium.utils.JSONUtils;
import com.appium.utils.TestUtils;
import com.appium.utils.VerificationUtils;

public class LoginTests extends BaseTest {

	LoginPage loginPage;
	ProductsPage productsPage;
	boolean b = true;
	JSONObject loginUsers;

	@AfterClass
	public void afterClass() {
		closeApp();
	}
	@BeforeClass
	public void beforeClass() {
//		closeApp();
		launchApp();
	}
	@BeforeMethod
	public void beforeMethod(Method method) {

		TestUtils.log().debug("---------------------------------------------------");
		TestUtils.log().debug("******************* Test started: " + method.getName() + "*******************");

		loginPage = new LoginPage();
		if(loginPage.isModifierDisplayed()){
			loginPage.pressModifierBtn();
		}
		// productsPage = new ProductsPage();
	}


	@AfterMethod
	public void afterMethod(Method method) {
		TestUtils.log().debug("*****************  ** Test ended: " + method.getName() + "*******************");
		TestUtils.log().debug("---------------------------------------------------");
		// closeApp();
		// launchApp();
		b = false;

	}

	@FrameworkAnnotation(author = { AuthorType.HAMMAN }, category = { CategoryType.SMOKE, CategoryType.REGRESSION })
	@Test(groups = {"REGRESSION" },priority=2, description = "Invalid Login Scenario with wrong username.")
	@Description("Invalid Login Scenario with wrong username.")
	@Story("TEST DE CONNEXION CARREFOUR")
	public void invalidUserName() {

		JSONObject jsonObject_InvalidUser = 
				new JSONUtils()
					.getJSONObject(TEST_DATA_JSON_FILE)
					.getJSONObject(TEST_DATA_JSON_INVALID_USER);
		
		String username = jsonObject_InvalidUser.getString(TEST_DATA_JSON_USERNAME).toString();
		String password = jsonObject_InvalidUser.getString(TEST_DATA_JSON_PASSWORD).toString();
		if(b)
			first_click();

		pola(username);

		loginPage.enterPassword(password).
			pressLoginBtn();

		String actualErrTxt = loginPage.getErrorTxt();
		String expectedErrTxt = StringsManager.getStrings()
				.get(EXPECTED_DATA_KEY_ERR_INAVLID_CREDENTIALS);
		VerificationUtils.validate(actualErrTxt, expectedErrTxt, "Verfication du message d'erreur pour un mail incorrect non conforme");

	}

//
//	@FrameworkAnnotation(author = { AuthorType.RAMEX, AuthorType.POLA }, category = { CategoryType.REGRESSION })
//	@Test(groups = { "SANITY" },priority=1)
//	public void invalidPassword() {
//
//		JSONObject jsonObject_InvalidUser =
//				new JSONUtils()
//					.getJSONObject(TEST_DATA_JSON_FILE)
//					.getJSONObject(TEST_DATA_JSON_INVALID_PASSWORD);
//
//		String username = jsonObject_InvalidUser.getString(TEST_DATA_JSON_USERNAME).toString();
//		String password = jsonObject_InvalidUser.getString(TEST_DATA_JSON_PASSWORD).toString();
//		if(b)
//			first_click();
//		loginPage.
//			enterUsername(username).
//			enterPassword(password).
//			pressLoginBtn();
//
//		String actualErrTxt = loginPage.getErrorTxt();
//		String expectedErrTxt = StringsManager.getStrings()
//				.get(EXPECTED_DATA_KEY_ERR_INAVLID_CREDENTIALS);
//
//		VerificationUtils.validate(actualErrTxt, expectedErrTxt, "Verification du message d'erreur pour un password incorrect ");
//
//	}
//
//	@FrameworkAnnotation(author = { AuthorType.RAMEX }, category = { CategoryType.SANITY,
//			CategoryType.BVT, CategoryType.REGRESSION })
//	@Test(groups = { "successfulLogin" },priority=3)
//	public void successfulLogin() {
//
//		JSONObject jsonObject_ValidUser =
//				new JSONUtils()
//					.getJSONObject(TEST_DATA_JSON_FILE)
//					.getJSONObject(TEST_DATA_JSON_VALID_USER);
//
//		String username = jsonObject_ValidUser.getString(TEST_DATA_JSON_USERNAME).toString();
//		String password = jsonObject_ValidUser.getString(TEST_DATA_JSON_PASSWORD).toString();
//		if(b)
//			first_click();
//		productsPage = loginPage.
//							enterUsername(username).
//							enterPassword(password).
//							pressLoginBtn();
//
//		String actualProductTitle = productsPage.getTitle();
//		String expectedProductTitle = StringsManager.getStrings()
//				.get(ACCOUNT_PAGE_TITLE);
//
//		VerificationUtils.validate(actualProductTitle, expectedProductTitle, "Verification du titre de la page utilisateur");
//	}


	public void first_click(){
		loginPage.accountIconClick().connectBtnClick();
		b=false;
	}
	public  void pola(String s ){
		loginPage.enterUsername(s);
	}
}
