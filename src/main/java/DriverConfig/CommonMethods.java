package DriverConfig;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CommonMethods extends BaseClass {
	public static JavascriptExecutor je;



	public static  void clickMethod(WebDriver driver, By by , String folderName, String taskName) {
		try {
			Thread.sleep(500);
			driver.findElement(by).click();
			log.info(" Clicked on "+ taskName);
		} catch (InterruptedException e) {
			log.info("****************************"+taskName+ " is failed to click *************************");
			captureScreen(driver, folderName);
			e.printStackTrace();

		}

	}


	public static  void sendKeysMethod(WebDriver driver , By by, String value,String folderName,String taskName, String send) {
		try {
			Thread.sleep(500);
			clickMethod(driver, by, folderName,taskName);
			driver.findElement(by).sendKeys(value);
			log.info(send+ " is sent in the field" );
		} catch (InterruptedException e) {
			log.info("****************************"+value+ " is failed to send *************************");
			captureScreen(driver, folderName);
			e.printStackTrace();

		}


	}

	public static void scrollDown() {
		je=(JavascriptExecutor)driver;
		je.executeScript("window.scrollBy(0,350)", "");

	}



	public  void selectFromListMethod(By by) {
		List<WebElement> list= driver.findElements(by);
		int size=list.size();
		if(size>=50) {
			clickOncheckBox(list, 50);
		}
		else {
			clickOncheckBox(list, size);
		}


	}
	
	public void clickOncheckBox(List<WebElement> list, int  size) {
		for(int i=0; i<size ; i++) {
			try {
				Thread.sleep(300);
				list.get(i).click();
				if(i%3==0 && i!=0) {
					scrollDown();
					}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
			
			
			
		}
		
		
		
	}



}
