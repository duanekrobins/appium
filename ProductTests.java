/**
 * @author Rajat Verma
 * https://www.linkedin.com/in/rajat-v-3b0685128/
 * https://github.com/rajatt95
 * https://rajatt95.github.io/
 *
 * Course: Appium Mobile Automation - Android & iOS + Frameworks + CICD (https://www.udemy.com/course/the-complete-appium-course-for-ios-and-android/)
 * Tutor: Omprakash Chavan (https://www.udemy.com/user/omprakash-chavan/)
 */

/***************************************************/

package com.appium.tests;

import com.appium.annotations.FrameworkAnnotation;
import com.appium.base.BaseTest;
import com.appium.enums.AuthorType;
import com.appium.enums.CategoryType;
import com.appium.manager.DriverManager;
import com.appium.pages.LoginPage;
import com.appium.pages.ProductDetailsPage;
import com.appium.pages.ProductsPage;
import com.appium.pages.SettingsPage;
import com.appium.utils.JSONUtils;
import com.appium.utils.TestUtils;
import com.appium.utils.VerificationUtils;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;
import org.json.JSONObject;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.List;

import static com.appium.constants.FrameworkConstants.*;
import static java.lang.Float.parseFloat;

public class ProductTests extends BaseTest {

	LoginPage loginPage;
	ProductsPage productsPage;
	boolean b = true;
	SettingsPage settingsPage;
	ProductDetailsPage productDetailsPage;

	JSONObject loginUsers;

	@AfterClass
	public void afterClass() {
		closeApp();
//		launchApp();
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
//		launchApp();
		// productsPage = new ProductsPage();
	}

	@AfterMethod
	public void afterMethod(Method method) {

		TestUtils.log().debug("******************* Test ended: " + method.getName() + "*******************");
		TestUtils.log().debug("---------------------------------------------------");
//		closeApp();
//		launchApp();
	}

	@DataProvider
	public Object[][] getProductDataForAdd() {
		String s = "addarticles";
		Object productsData[][] = TestUtils.getTestData(s);
		return productsData;
	}
	@FrameworkAnnotation(author = { AuthorType.HAMMAN}, category = { CategoryType.SMOKE, CategoryType.REGRESSION })
	@Test(groups = { "SMOKE", "REGRESSION" },priority = 0,dataProvider = "getProductDataForAdd")
	public void validateProductOnProductsPage(String nom, String qte) {
		float Z = parseFloat(qte);
		int X = Math.round(Z);
if(b){
login();
}
		productsPage.researchInputClick().enterProductName(nom);
		productsPage.isSearchTitleDisplayed();
		String actualSearchResult ="null";
		String xpathElements = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[1]";
		List<MobileElement> Resultats = DriverManager.getDriver().findElementsByXPath(xpathElements);
		cyclingVerifications(Resultats, nom);
		if (Resultats.size()==0)
			VerificationUtils.validateResponse(false,"Echec de la vérification de la présence du mot clé, car aucun element trouvé");;


//		String expectedProductTitle = StringsManager.getStrings()
//				.get(ACCOUNT_PAGE_TITLE);
//		VerificationUtils.validate(actualSearchResult, expectedProductTitle, "Product Title");
//
//		String actualSLBTitle = productsPage.getSLBTitle();
//		String expectedSLBTitle = StringsManager.getStrings()
//				.get(EXPECTED_DATA_KEY_PRODUCTS_PAGE_SLB_TITLE);
//		VerificationUtils.validate(actualSLBTitle, expectedSLBTitle, "Title for Sauce Labs Backpack");
//
//		String actualSLBPrice = productsPage.getSLBPrice();
//		String expectedSLBPrice = StringsManager.getStrings()
//				.get(EXPECTED_DATA_KEY_PRODUCTS_PAGE_SLB_PRICE);
//		VerificationUtils.validate(actualSLBPrice, expectedSLBPrice, "Price for Sauce Labs Backpack");

//		settingsPage = productsPage.pressSettingsBtn();
//		loginPage = settingsPage.pressLogoutBtn();

//		Assert.fail("*******************************Failing intentionally");

	}

	private void login() {
		JSONObject jsonObject_ValidUser =
				new JSONUtils()
						.getJSONObject(TEST_DATA_JSON_FILE)
						.getJSONObject(TEST_DATA_JSON_VALID_USER);

		String username = jsonObject_ValidUser.getString(TEST_DATA_JSON_USERNAME).toString();
		String password = jsonObject_ValidUser.getString(TEST_DATA_JSON_PASSWORD).toString();
		productsPage = 	loginPage.accountIconClick().connectBtnClick().
				enterUsername(username).
				enterPassword(password).
				pressLoginBtn();
		productsPage.accountBackBtnClick().researchInputTextClick();
		b = false;
	}

//	@FrameworkAnnotation(author = { AuthorType.HAMMAN}, category = { CategoryType.SMOKE, CategoryType.REGRESSION })
//	@Test(groups = { "SMOKE", "REGRESSION" })
//	public void validateProductOnProductsPage() {
//
//		JSONObject jsonObject_ValidUser =
//				new JSONUtils()
//					.getJSONObject(TEST_DATA_JSON_FILE)
//					.getJSONObject(TEST_DATA_JSON_VALID_USER);
//
//		String username = jsonObject_ValidUser.getString(TEST_DATA_JSON_USERNAME).toString();
//		String password = jsonObject_ValidUser.getString(TEST_DATA_JSON_PASSWORD).toString();
//		productsPage = loginPage.login(username, password);
//
//		String actualProductTitle = productsPage.getTitle();
//		String expectedProductTitle = StringsManager.getStrings()
//				.get(ACCOUNT_PAGE_TITLE);
//		VerificationUtils.validate(actualProductTitle, expectedProductTitle, "Product Title");
//
//		String actualSLBTitle = productsPage.getSLBTitle();
//		String expectedSLBTitle = StringsManager.getStrings()
//				.get(EXPECTED_DATA_KEY_PRODUCTS_PAGE_SLB_TITLE);
//		VerificationUtils.validate(actualSLBTitle, expectedSLBTitle, "Title for Sauce Labs Backpack");
//
//		String actualSLBPrice = productsPage.getSLBPrice();
//		String expectedSLBPrice = StringsManager.getStrings()
//				.get(EXPECTED_DATA_KEY_PRODUCTS_PAGE_SLB_PRICE);
//		VerificationUtils.validate(actualSLBPrice, expectedSLBPrice, "Price for Sauce Labs Backpack");
//
////		settingsPage = productsPage.pressSettingsBtn();
////		loginPage = settingsPage.pressLogoutBtn();
//
//		settingsPage = productsPage.
//				getMenuPage().
//				pressSettingsBtn();
//		loginPage = settingsPage.pressLogoutBtn();
//
////		Assert.fail("*******************************Failing intentionally");
//
//}

//	@FrameworkAnnotation(author = { AuthorType.HAMMAN, AuthorType.HAMMAN}, category = { CategoryType.BVT,
//			CategoryType.REGRESSION })
//	@Test(groups = { "BVT", "REGRESSION" })
//
//	public void validateProductOnProductDetailsPage() {
//
//		JSONObject jsonObject_ValidUser =
//				new JSONUtils()
//					.getJSONObject(TEST_DATA_JSON_FILE)
//					.getJSONObject(TEST_DATA_JSON_VALID_USER);
//
//		String username = jsonObject_ValidUser.getString(TEST_DATA_JSON_USERNAME).toString();
//		String password = jsonObject_ValidUser.getString(TEST_DATA_JSON_PASSWORD).toString();
//		productsPage = loginPage.login(username, password);
//
//		String actualProductTitle = productsPage.getTitle();
//		String expectedProductTitle = StringsManager.getStrings()
//				.get(ACCOUNT_PAGE_TITLE);
//
//		VerificationUtils.validate(actualProductTitle, expectedProductTitle, "Product Title");
//
//		productDetailsPage = productsPage.pressSLBTitle();
//
//		String actualSLBTitle = productDetailsPage.getSLBTitle();
//		String expectedSLBTitle = StringsManager.getStrings()
//				.get(EXPECTED_DATA_KEY_PRODUCTS_PAGE_SLB_TITLE);
//		VerificationUtils.validate(actualSLBTitle, expectedSLBTitle, "Title for Sauce Labs Backpack");
//
//		String actualSLBDescription = productDetailsPage.getSLBTxt();
//		String expectedSLBDescription = StringsManager.getStrings()
//				.get(EXPECTED_DATA_KEY_PRODUCTS_PAGE_SLB_DESCRIPTION);
//		VerificationUtils.validate(actualSLBDescription, expectedSLBDescription,
//				"Description for Sauce Labs Backpack");
//		productDetailsPage.scrollToSLBPrice();
//
//		String actualSLBPrice = productDetailsPage.getSLBPrice();
//		String expectedSLBPrice = StringsManager.getStrings()
//				.get(EXPECTED_DATA_KEY_PRODUCTS_PAGE_SLB_PRICE);
//		VerificationUtils.validate(actualSLBPrice, expectedSLBPrice, "Price for Sauce Labs Backpack");
//	}
@Step("Verification de la présence du mot clé")
public void cyclingVerifications(List<MobileElement> Resultats, String nom ){
		for ( int i = 0; i < Resultats.size(); ++i) {
			VerificationUtils.validateResponse(productsPage.getSearchResult(Resultats.get(i),nom),"Vérification de la présence du mot clé "+nom+" dans "+productsPage.getElementText(Resultats.get(i)));;
		}

	}
}
