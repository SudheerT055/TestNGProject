package com.login.functionality;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginFunctionality {
	WebDriver driver = null;
	String expected_Error_String = "Epic sadface: Username and password do not match any user in this service";

	public void openChromeBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
	}

	public void visitSwagLabs() {
		driver.get("https://www.saucedemo.com/");
	}

	public void enterUserName(String username) {
		driver.findElement(By.xpath("//input[@id='user-name']")).clear();
		driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys(username);
	}

	public void enterPassword(String password) {
		driver.findElement(By.xpath("//input[@id='password']")).clear();
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
	}

	public void clickOnLoginButton() {
		driver.findElement(By.xpath("//input[@id='login-button']")).click();
	}

	public boolean checkMenuReactButtonDisplayed() {
		return driver.findElement(By.id("react-burger-menu-btn")).isDisplayed();
	}

	public void clickOnMenuReactButton() {
		driver.findElement(By.id("react-burger-menu-btn")).click();
	}

	public void clickOnLogoutButton() {
		driver.findElement(By.id("logout_sidebar_link")).click();
	}

	public String checkErrorMessageIsDisplayed() {
		WebElement error = driver.findElement(By.xpath("//h3[@data-test='error']"));
		return error.getText();
	}

	@BeforeMethod(groups = { "smoketest", "regression", "bvt" })
	public void lauchChromeAndWebsite() {
		openChromeBrowser();
		visitSwagLabs();
	}

	@AfterMethod(groups = { "smoketest", "regression", "bvt" })
	public void tearDown() {
		driver.close();
	}

	@Test(priority = 1, groups = "smoketest", dataProvider = "myDataProvider")
	public void enterValidUsernameAndPassword(String username, String password) {
		enterUserName(username);
		enterPassword(password);
		clickOnLoginButton();
		if (checkMenuReactButtonDisplayed()) {
			clickOnMenuReactButton();
			clickOnLogoutButton();
		}

	}

	@DataProvider(name = "myDataProvider")
	public Object[][] myDataset() {
		return new Object[][] { { "standard_user", "secret_sauce" }, 
								{ "problem_user", "secret_sauce" },
								{ "performance_glitch_user" , "secret_sauce"}};
	}

	@Test(priority = 2, groups = { "smoketest", "regression" })
	public void enterValidUsernameInvalidPassword() {
		enterUserName("standard_user");
		enterPassword("invalidPassword");
		clickOnLoginButton();
		String actual_Error = checkErrorMessageIsDisplayed();
		Assert.assertEquals(actual_Error, expected_Error_String);
	}

	@Test(priority = 3, groups = "regression", enabled = false)
	public void enterInvalidUsernameValidPassword() {
		enterUserName("invalidUser");
		enterPassword("secret_sauce");
		clickOnLoginButton();
		String actual_Error = checkErrorMessageIsDisplayed();
		Assert.assertEquals(actual_Error, expected_Error_String);
	}

	@Test(priority = 4, groups = { "regression", "bvt" })
	public void enterInvalidUsernameInvalidPassword() {
		enterUserName("invalidUser");
		enterPassword("invalidPassword");
		clickOnLoginButton();
		String actual_Error = checkErrorMessageIsDisplayed();
		Assert.assertEquals(actual_Error, expected_Error_String);
	}

}
