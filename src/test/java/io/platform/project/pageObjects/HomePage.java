package io.platform.project.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.platform.project.commons.Common;
import io.platform.project.commons.ReadPropertyFile;
import io.platform.project.commons.WebDriverManager;


public class HomePage {
	public WebDriver driver;
	
	public HomePage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath = "//*[@id=\"bodyContent\"]/h1123")
	WebElement homePageTitle;
	
	public String getTitle(){
		return Common.getElementText(driver, homePageTitle, 2);
	}
	
	public void openHomePageURL(WebDriver driver){
		WebDriverManager.openURL(driver, ReadPropertyFile.getConfigPropertyVal("homePageURL"));
	}
}
