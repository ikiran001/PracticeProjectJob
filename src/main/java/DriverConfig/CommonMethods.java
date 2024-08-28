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

	public static void scrollDown(int value) {
		
		je=(JavascriptExecutor)driver;
		je.executeScript("window.scrollBy(0,"+value+")", "");

	}
	
	public static void scrollUp() {
	
		try {
			
			je=(JavascriptExecutor)driver;
			
			//je.executeScript("window.scrollBy("+value+",0)", "");
			je.executeScript("window.scrollTo(0, 0)", "");
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	

	public void selectForNaukri(By by) {
		int j=0;
		List<WebElement> list= driver.findElements(by);
		for(WebElement i: list) {
			i.click();
			scrollDown(250);
			j++;
			if(j==5) {
				break;
			}
		}
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
				
				list.get(i).click();
				Thread.sleep(300);
				scrollDown(110);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
			
			
			
		}
		
		
		
	}



}
