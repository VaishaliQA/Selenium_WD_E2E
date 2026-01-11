package io.platform.project.homePageTests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.platform.project.commons.Common;
import io.platform.project.commons.WebDriverManager;
import io.platform.project.pageObjects.HomePage;

public class HomePageTest {
	HomePage homePage;
	WebDriver driver;
	WebDriverManager webDrivermanager;
	
	@BeforeTest
	public void beforeTest(){
		//Open Chrome
		webDrivermanager = new WebDriverManager();
		driver = webDrivermanager.getDriver();
		//Open URL
		homePage = new HomePage(driver);
		homePage.openHomePageURL(driver);
	}

	//@Parameters({browser})
	@Test
	public void test(){
		//http://107.170.213.234/catalog/index.php
		//Page is Loaded
		//Check the title
		String str = homePage.getTitle();
		String title = "Welcome to iBusinesss";
		Common.check(driver, title.equals(str), "Home Page Title not equal to "+ title);
	}
	
	
	@Test
	public void test2(){
		//http://107.170.213.234/catalog/index.php
		//Page is Loaded
		//Check the title
		
		//wait until condition (element is present)
		//driver.findBy(By.)
	}


	@AfterTest
	public void afterTest(){
		driver.quit();
	}
	

}
