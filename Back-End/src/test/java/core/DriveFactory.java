package core;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
public class DriveFactory {
	
	private static WebDriver driverSelenium;
	

	
	public static WebDriver getSeleniumDriver() {
		if(driverSelenium == null) {
			createDriverSelenium();
		}
		return driverSelenium;
	}
	
	public static void createDriverSelenium() {
		System.setProperty("webdriver.chrome.driver", "C:"+File.separator+"chromedriver"+File.separator+ "chromedriver.exe"); 
		
		  driverSelenium = new ChromeDriver();
		 
		  driverSelenium.manage().window().maximize();
		
	}
	
	public static void killSeleniumDriver() {
		if(driverSelenium != null) {
			driverSelenium.quit();
			driverSelenium = null;
		}
		
	}
}


	


