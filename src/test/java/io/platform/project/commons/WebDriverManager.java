package io.platform.project.commons;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;



public class WebDriverManager {
	private WebDriver driver;
	private String osName = System.getProperty("os.name");
	private Logger logger = Logger.getLogger(WebDriverManager.class);
	
	private  WebDriver createDriver(String browser){
		if (osName.toLowerCase().contains("windows")){
			if (browser.equalsIgnoreCase("Chrome")){
				System.setProperty("webdriver.chrome.driver", "src//main//resources//drivers//chromedriver.exe");
				driver = new ChromeDriver();
				logger.info("Chrome Browser Chosen");
			} else if (browser.equalsIgnoreCase("Firefox")){
				System.setProperty("webdriver.gecko.driver", "src//main//resources//drivers//gecko.exe");
				driver = new FirefoxDriver();
				logger.info("Firefox Browser Chosen");
			} else {
				logger.info("Default Browser Chosen (chrome)");
				System.setProperty("webdriver.chrome.driver", "src//main//resources//drivers//chromedriver.exe");
				driver = new ChromeDriver();
			}
		} else if (osName.toLowerCase().contains("mac")){
			if (browser.equalsIgnoreCase("Chrome")){
				driver = new ChromeDriver();
				logger.info("Chrome Browser Chosen");
			} else if (browser.equalsIgnoreCase("Firefox")){
				driver = new FirefoxDriver();
				logger.info("Firefox Browser Chosen");
			} else {
				logger.info("Default Browser Chosen (chrome)");
				driver = new ChromeDriver();
			}
		} else if (osName.toLowerCase().contains("linux")){
			if (browser.equalsIgnoreCase("Chrome")){
				System.setProperty("webdriver.chrome.driver", "src//main//resources//drivers//chromedriver.exe");
				driver = new ChromeDriver();
				logger.info("Chrome Browser Chosen");
			} else if (browser.equalsIgnoreCase("Firefox")){
				System.setProperty("webdriver.gecko.driver", "src//main//resources//drivers//gecko.exe");
				driver = new FirefoxDriver();
				logger.info("Firefox Browser Chosen");
			} else {
				logger.info("Default Browser Chosen (chrome)");
				System.setProperty("webdriver.chrome.driver", "src//main//resources//drivers//chromedriver.exe");
				driver = new ChromeDriver();
			}
		}

		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.manage().window().setSize(new Dimension(1440, 900));
		
		Capabilities caps =((RemoteWebDriver) driver).getCapabilities();
		return driver;
	}
	
	
	public WebDriver getDriver(){
		if (driver == null){
			try {
				driver = createDriver(Common.createEnvVariable("browser", "chrome"));
				//driver = createDriver(ReadPropertyFile.getConfigPropertyVal("browser"));
				logger.info("Driver Initialization successful");
			} catch (Exception ex) {
				logger.info("Couldn't Initialize Driver");
				ex.printStackTrace();
			}
			
		} else {
			logger.info("Driver already Initialized");
		}
		
		return driver;

	}
	
	public static void openURL(WebDriver driver, String url){
		driver.get(url);
	}

}
