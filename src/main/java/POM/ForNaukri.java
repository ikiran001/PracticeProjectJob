package POM;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import DriverConfig.CommonMethods;

public class ForNaukri extends CommonMethods {
	String folderName="Naukri";
	private boolean hasSelectedJobs = false;

	By email=By.xpath("//input[contains(@placeholder,'Enter Email ID / Username')]");
	By pass=By.xpath("//input[contains(@placeholder,'Enter Password')]");
	By logInbutton=By.xpath("(//button[contains(text(),'Login')])[1]");
	By profile=By.xpath("//div[contains(@class,'nI-gNb-drawer') or contains(@class,'nI-gNb-info')]");
	By view=By.xpath("//a[contains(normalize-space(.),'View') and contains(normalize-space(.),'Profile')] | //a[contains(@href,'/mnjuser/profile')]");
	By edit=By.xpath("//div[@class='hdn']/em");
	By save=By.xpath("(//button[contains(text(),'Save')])[2]");
	By jobs=By.xpath("//a[normalize-space()='Jobs' or contains(@href,'jobs')] | //div[contains(@class,'nI-gNb') and (normalize-space()='Jobs' or contains(normalize-space(.),'Jobs'))]");
	By checkBox=By.xpath("//i[contains(@class,'checkbox') or contains(@class,'multi') or contains(@class,'tick')] | //input[@type='checkbox']");
	By applyButton=By.xpath("//button[contains(normalize-space(.),'Apply')] | //a[contains(normalize-space(.),'Apply')]");
	By cross=By.xpath("//div[contains(@class,'chatBot-ic-cross') or contains(@class,'crossIcon')]");
	By chatList=By.cssSelector("ul.list[id^='chatList__']");

	private List<WebElement> waitForElements(By by) {
		return new WebDriverWait(driver, Duration.ofSeconds(20))
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
	}


	
	public void clickOnApply() {
		dismissChatOverlay();
		if (!hasSelectedJobs) {
			log.warn("No jobs selected. Skipping Apply.");
			return;
		}
		if (driver.findElements(applyButton).isEmpty()) {
			log.warn("No Apply buttons found.");
			return;
		}
		try {
			new WebDriverWait(driver, Duration.ofSeconds(10))
					.until(ExpectedConditions.elementToBeClickable(applyButton));
		} catch (TimeoutException e) {
			log.warn("Apply button not found or not clickable.");
			return;
		}
		clickMethod(driver, applyButton, folderName, " on Apply button");
		dismissChatOverlay();
		
		

	}
	public void clickOncheckBox() {
		List<WebElement> list;
		try {
			list = waitForElements(checkBox);
		} catch (TimeoutException e) {
			log.warn("No job checkboxes found to select.");
			return;
		}
		int j = 0;
		for (WebElement i : list) {
			i.click();
			scrollDown(250);
			j++;
			if (j == 5) {
				break;
			}
		}
		hasSelectedJobs = j > 0;
		log.info(" Selected " + j + " job checkboxes");
	}
	public void clickOnJobs() {
		dismissChatOverlay();
		scrollUp();
		clickMethod(driver, jobs, folderName, " Job button");

	}
	public void clickOnSave() {
		clickMethod(driver, save, folderName, " Save button");

	}
	public  void sendEmailId(String value) {
		sendKeysMethod(driver, email, value, folderName, " Email ID", value);
	}
	public  void sendPass(String value) {
		sendKeysMethod(driver, pass, value, folderName, " Password", value);
	}

	public  void clickOnLogin() {
		clickMethod(driver, logInbutton, folderName, " Login button");

	}

	public void clickOnProfile() {
		dismissChatOverlay();
		clickMethod(driver, profile, folderName, " Profile ");

	}
	public void clickOnVewProfil() {
		dismissChatOverlay();
		clickMethod(driver, view, folderName, " View & Update Profile");
	}

	public void clickOnediticon() {
		dismissChatOverlay();
		clickMethod(driver, edit, folderName, " Edit icon");
		scrollDown(800);
	}

	private void dismissChatOverlay() {
		hideElementIfPresent(chatList);
		List<WebElement> closeButtons = driver.findElements(cross);
		if (!closeButtons.isEmpty() && closeButtons.get(0).isDisplayed()) {
			clickMethod(driver, cross, folderName, " Cancel button");
		}
	}








}
