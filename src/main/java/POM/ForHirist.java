package POM;

import org.openqa.selenium.By;

import DriverConfig.CommonMethods;

public class ForHirist extends CommonMethods{
	

	String folderName="Hirist";
	By jobSeeker=By.xpath("//p[contains(text(),'Jobseeker Login')]");
	By signIn=By.xpath("//span[contains(text(),'Sign In')]");
	By email=By.xpath("//input[@name='email']");
	By passCode=By.xpath("//input[@name='password']");
	By login=By.xpath("//span[text()='Login']");
	By icon=By.xpath("//div[@class='icon-search-big search-icon']");
	By searchTab=By.xpath("(//input[@placeholder='Search Jobs'])[1]");
	By bigSeacrhIcon=By.xpath("//button[@Type='submit']/div");
	By checkBox=By.xpath("//div[@class='job-apply-checkbox']");
	By apply=By.xpath("(//button[@type='button'])[1]");
	By exp=By.xpath("(//a[contains(text(),'Any Exp. Level')])[1]");
	By location=By.xpath("(//a[contains(text(),'Any Location')])[1]");
	By selectyoE=By.xpath("(//li[contains(text(),'1 - 3 yrs')])[1]");
	By selectLocation=By.xpath("//label[text()='Metros']");
	By postPeriod=By.xpath("(//li[contains(text(),'Last Month')])[1]");
	By postingCol=By.xpath("(//a[contains(text(),'Last 3 Months')])[1]");
	
	
	
	
	
	
	public void clickPostingCol() {
		clickMethod(driver, postingCol, folderName," posting exp");
		clickMethod(driver, postPeriod, folderName," posting exp");
	}
	
	
	public void clickOnExp() {
		clickMethod(driver, exp, folderName," Any Experience");
		clickMethod(driver, selectyoE, folderName," 1-3 Years of experience");
	}
	
	public void clickOnLocation() {
		clickMethod(driver, location, folderName,"  Location ");
		clickMethod(driver, selectLocation, folderName,"  Metro cities ");
	}
	public void clickOnJobSeeker() {
		clickMethod(driver, jobSeeker, folderName," Jobseeker");
	}
	public void clickOnSignIN() {
		clickMethod(driver, signIn, folderName," Sign In");
	}
	
	public  void sendInEmail(String value) {
		sendKeysMethod(driver, email, value, folderName," Email" , value);

	}
	
	public  void sendInPassword(String value) {
	sendKeysMethod(driver, passCode, value, folderName," Password" , value);

	}
	
	public void clickOnLogin() {
		clickMethod(driver, login, folderName," Login button");

	}
	
	public  void clickOnIcon() {
		clickMethod(driver, icon, folderName ,  "small search Icon");

	}
	
	public  void clickOnSearchTab(String value) {
		sendKeysMethod(driver, searchTab, value , folderName, " Search Tab", value);

	}
	
	public void sendInBigSearchIcon() {
		clickMethod(driver, bigSeacrhIcon, folderName," Big Search Icon");
		scrollDown(8000);
		
		scrollUp();

	}
	
	public void clickOnApplyAll() {
		clickMethod(driver, apply, folderName," Apply");

	}
	
	public void selectFromList() {
		selectFromListMethod(checkBox);
	}
	


}
