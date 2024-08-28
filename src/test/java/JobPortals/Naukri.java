package JobPortals;

import org.testng.annotations.Test;

import DriverConfig.BaseClass;
import DriverConfig.FrameworkConstants;
import POM.ForNaukri;
import Utilities.ReadConfig;

public class Naukri extends BaseClass{

	ForNaukri fn= new ForNaukri();
	@Test
	public void applyOnNaukri() throws Exception {
		driver.get(ReadConfig.getConfigValue("naukri"));
		log.info(ReadConfig.getConfigValue("naukri")+" <-- is Open");
		fn.sendEmailId(FrameworkConstants.getEmail());
		fn.sendPass(FrameworkConstants.getPassnkr());
		fn.clickOnLogin();
		fn.clickOnProfile();
		fn.clickOnVewProfil();
		fn.clickOnediticon();
		fn.clickOnSave();
		fn.clickOnJobs();
		fn.clickOncheckBox();
		fn.clickOnApply();

		

	}
	
	
}
