package JobPortals;


import org.openqa.selenium.By;
import org.testng.annotations.Test;

import DriverConfig.BaseClass;
import Utilities.ReadConfig;

public class Hirist extends BaseClass{

	
	@Test
	public void applyOnHirist() throws Exception  {
		
			driver.get(ReadConfig.getConfigValue("hirist"));
			log.info("open URL");
			driver.findElement(By.xpath("//p[contains(text(),'adadsd')]")).click();
			driver.findElement(By.xpath("//p[contains(text(),'Jobseeker Login')]")).click();
			
		
	}
}
