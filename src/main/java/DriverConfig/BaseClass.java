package DriverConfig;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import Utilities.ReadConfig;


public class BaseClass {

	public  static String URL;
	public static WebDriver driver;
	public static final Logger log = Logger.getLogger(BaseClass.class);;

	@Parameters("browser")
	@BeforeClass
	public void setup(@Optional("chrome") String br) throws Exception {
		if (br.equals("chrome")) {
			String chromePath = ReadConfig.getConfigValue("chromepath");
			if (chromePath != null && !chromePath.trim().isEmpty()) {
				File chromeDriver = new File(chromePath);
				if (chromeDriver.isFile()) {
					System.setProperty("webdriver.chrome.driver", chromeDriver.getAbsolutePath());
				} else {
					log.warn("ChromeDriver not found at " + chromePath + "; using Selenium Manager.");
				}
			}
			driver = new ChromeDriver();
			log.info(" Opening Chrome Browser ");
		} else if (br.equals("firefox")) {
			String firefoxPath = ReadConfig.getConfigValue("firefoxpath");
			if (firefoxPath != null && !firefoxPath.trim().isEmpty()) {
				File geckoDriver = new File(firefoxPath);
				if (geckoDriver.isFile()) {
					System.setProperty("webdriver.gecko.driver", geckoDriver.getAbsolutePath());
				} else {
					log.warn("GeckoDriver not found at " + firefoxPath + "; using Selenium Manager.");
				}
			}
			driver = new FirefoxDriver();
			log.info("Opaning firefox Browser");
		} else if (br.equals("ie")) {
			String iePath = ReadConfig.getConfigValue("iepath");
			if (iePath != null && !iePath.trim().isEmpty()) {
				File ieDriver = new File(iePath);
				if (ieDriver.isFile()) {
					System.setProperty("webdriver.ie.driver", ieDriver.getAbsolutePath());
				} else {
					log.warn("IE driver not found at " + iePath);
				}
			}
			driver = new InternetExplorerDriver();
			log.info("Opaning ie Browser");
		}

		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	@AfterClass
	public void teardown() { 
		driver.quit();
		}

	public static String getTodaysDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		return date1;
	}

	public static String getCurrentTime() {
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH;mm;ss");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		String time = date1.substring(12);
		return time;
	}
	
	public static String  captureScreen() {
		if (driver == null) {
			log.warn("Driver is null, skipping screenshot capture.");
			return null;
		}
		TakesScreenshot ts = (TakesScreenshot) driver;
		String base64Code=ts.getScreenshotAs(OutputType.BASE64);
		log.info("Screen Shot saved successfully ");
		return base64Code;
	}

	public static String  captureScreen(WebDriver driver,  String folder) {

		if (driver == null) {
			log.warn("Driver is null, skipping screenshot capture.");
			return null;
		}
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File targetDir = new File(System.getProperty("user.dir"), "Screenshots/" + folder);
		if (!targetDir.exists()) {
			targetDir.mkdirs();
		}
		File target = new File(targetDir, getTodaysDate()
				 + "_" + getCurrentTime() + ".png");
		try {
			FileUtils.copyFile(source, target);
		} catch (IOException e) {
			e.printStackTrace();
		}
		log.info("Screenshot taken for test case name " + folder);
		return target.getAbsolutePath();
	}
	


}
