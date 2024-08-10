package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import DriverConfig.CommonMethods;

public class ForNaukri extends CommonMethods {
	String folderName="Naukri";

	By email=By.xpath("//input[contains(@placeholder,'Enter Email ID / Username')]");
	By pass=By.xpath("//input[contains(@placeholder,'Enter Password')]");
	By logInbutton=By.xpath("(//button[contains(text(),'Login')])[1]");
	By profile=By.xpath("//div[@class='nI-gNb-drawer']");
	By view=By.xpath("//a[text()='View & Update Profile']");
	By edit=By.xpath("//div[@class='hdn']/em");
	By save=By.xpath("(//button[contains(text(),'Save')])[2]");
	By jobs=By.xpath("(//div[contains(text(),'Jobs')])[1]");
	By checkBox=By.xpath("//i[contains(@class,'checkbox')]");
	By applyButton=By.xpath("//button[contains(text(),'Apply')]");
	By cross=By.xpath("//div[@class='crossIcon chatBot chatBot-ic-cross']");


	
	public void clickOnApply() {
		clickMethod(driver, applyButton, folderName, " on Apply button");
		WebElement ele=driver.findElement(cross);
		if(ele.isEnabled()){
			clickMethod(driver, cross, folderName, " Cancel button");
		}
		
		

	}
	public void clickOncheckBox() {
		selectForNaukri(checkBox);
	}
	public void clickOnJobs() {
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
		clickMethod(driver, profile, folderName, " Profile ");

	}
	public void clickOnVewProfil() {
		clickMethod(driver, view, folderName, " View & Update Profile");
	}

	public void clickOnediticon() {
		clickMethod(driver, edit, folderName, " Edit icon");
		scrollDown(800);
	}








}
