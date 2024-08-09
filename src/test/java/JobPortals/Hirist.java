package JobPortals;


import org.testng.annotations.Test;

import DriverConfig.BaseClass;
import DriverConfig.FrameworkConstants;
import POM.ForHirist;
import Utilities.ReadConfig;

public class Hirist extends BaseClass{
	ForHirist  fh=new ForHirist();
	@Test
	public void applyOnHirist() throws Exception  {

		driver.get(ReadConfig.getConfigValue("hirist"));
		log.info(ReadConfig.getConfigValue("hirist")+" <-- is Open");
		fh.clickOnJobSeeker();
		fh.clickOnSignIN();
		fh.sendInEmail(FrameworkConstants.getEmail());
		fh.sendInPassword(FrameworkConstants.getPass());
		fh.clickOnLogin();
		fh.clickOnIcon();
		fh.clickOnSearchTab(FrameworkConstants.getAutomationtestengineer());
		fh.sendInBigSearchIcon();
		fh.selectFromList();
		fh.clickOnApplyAll();
		




	}
}
