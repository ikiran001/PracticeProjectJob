package DriverConfig;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonMethods extends BaseClass {
	public static JavascriptExecutor je;
	private static final Duration DEFAULT_WAIT = Duration.ofSeconds(20);

	private static WebElement waitForVisible(WebDriver driver, By by) {
		return new WebDriverWait(driver, DEFAULT_WAIT)
				.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	private static WebElement waitForClickable(WebDriver driver, By by) {
		return new WebDriverWait(driver, DEFAULT_WAIT)
				.until(ExpectedConditions.elementToBeClickable(by));
	}

	private static void scrollIntoView(WebElement element) {
		je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", element);
	}

	public static void hideElementIfPresent(By by) {
		List<WebElement> elements = driver.findElements(by);
		if (!elements.isEmpty()) {
			je = (JavascriptExecutor) driver;
			je.executeScript("arguments[0].style.display='none';", elements.get(0));
		}
	}



	public static  void clickMethod(WebDriver driver, By by , String folderName, String taskName) {
		try {
			WebElement element = waitForClickable(driver, by);
			scrollIntoView(element);
			element.click();
			log.info(" Clicked on "+ taskName);
		} catch (ElementClickInterceptedException e) {
			try {
				WebElement element = waitForVisible(driver, by);
				scrollIntoView(element);
				je.executeScript("arguments[0].click();", element);
				log.info(" Clicked on "+ taskName);
			} catch (Exception ex) {
				log.info("****************************"+taskName+ " is failed to click *************************");
				captureScreen(driver, folderName);
				ex.printStackTrace();
			}
		} catch (Exception e) {
			log.info("****************************"+taskName+ " is failed to click *************************");
			captureScreen(driver, folderName);
			e.printStackTrace();

		}

	}


	public static  void sendKeysMethod(WebDriver driver , By by, String value,String folderName,String taskName, String send) {
		try {
			WebElement element = waitForVisible(driver, by);
			scrollIntoView(element);
			element.click();
			element.clear();
			element.sendKeys(value);
			log.info(send+ " is sent in the field" );
		} catch (Exception e) {
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
