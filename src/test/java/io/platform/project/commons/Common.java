package io.platform.project.commons;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.google.common.base.Function;

import static java.lang.Thread.currentThread;

public class Common {
	
	private static Logger logger = Logger.getLogger(Common.class);
	
	public static String getElementText(WebDriver driver, WebElement el, int seconds){
		if (waitForElement(driver, el, seconds)){
			String str = el.getText();
			logger.info("Element Display = " + str);
			return str;
		} else {
			return null;
		}
		
	}
	
	private static void screenShot(WebDriver driver, String javaClass, String methodName){
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(src, new File("./screenshot/"+
					new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss_").format(new Date())+
					javaClass + "_" + methodName + ".png"));
			logger.info("Screenshot taken");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info("Unable to take Screenshot");
		}
	}
	
	public static void check( WebDriver driver, boolean condition, String failMessage){
		if (condition) {
			logger.info("Check Condition True");
			Assert.assertTrue(true);
		} else {
			screenShot(driver,currentThread().getStackTrace()[2].getClassName(),
					currentThread().getStackTrace()[2].getMethodName());
			logger.info(failMessage);
			Assert.fail();
		}
	}
	
	public static String createEnvVariable(String envVariableName,String defaultValue){
		String variableValue = System.getProperty(envVariableName);
		logger.info("Environment Value for " + envVariableName + " is equal to " + variableValue);
		return (variableValue!=null) ? variableValue : defaultValue;
	}
	
	public static boolean waitForElement(WebDriver driver, WebElement el, int seconds){
		WebDriverWait wait = new WebDriverWait(driver, seconds);
		try {
			wait.until(ExpectedConditions.visibilityOf(el));
			logger.info(el.getText() + " is visible");
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.info("Element is NOT visible");
			return false;
		}
	}
	
	public static boolean isElementVisible (WebDriver driver, WebElement el, int seconds){
		boolean elementFound = false;
		
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(seconds, TimeUnit.SECONDS)
				.pollingEvery(10, TimeUnit.MILLISECONDS);
		
		Function<WebDriver, Boolean> function = arg0 -> {
			try {
				driver.manage().timeouts().implicitlyWait(1, TimeUnit.MILLISECONDS);
				el.getSize();
				return true;
			} catch (NoSuchElementException nse){
				return false;
			}
		};
		
		try {
			wait.until(function);
			logger.info("Element is visible");
			elementFound = true;
		} catch (Exception ex) {
			logger.info("Element is NOT visible");
			elementFound = false;
		}
		
		return elementFound;
	}
	
	public static boolean isElementVisible (WebDriver driver, WebElement el){
		return isElementVisible(driver, el, 2);
	}
}
