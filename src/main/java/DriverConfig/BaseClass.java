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
import org.testng.annotations.Parameters;

import Utilities.ReadConfig;


public class BaseClass {

	public  static String URL;
	public static WebDriver driver;
	public static final Logger log = Logger.getLogger(BaseClass.class);;

	@Parameters("browser")
	@BeforeClass
	public void setup(String br) throws Exception {
		if (br.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", ReadConfig.getConfigValue("chromepath"));
			driver = new ChromeDriver();
			log.info(" Opening Chrome Browser ");
		} else if (br.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", ReadConfig.getConfigValue("firefoxpath"));
			driver = new FirefoxDriver();
			log.info("Opaning firefox Browser");
		} else if (br.equals("ie")) {
			System.setProperty("webdriver.ie.driver", ReadConfig.getConfigValue("iepath"));
			driver = new InternetExplorerDriver();
			log.info("Opaning ie Browser");
		}

		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	@AfterTest
	public void teardown() { 
		
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

		TakesScreenshot ts = (TakesScreenshot) driver;
		String base64Code=ts.getScreenshotAs(OutputType.BASE64);
		log.info("Screen Shot saved successfully ");
		return base64Code;
	}

	public static String  captureScreen(WebDriver driver,  String folder) {

		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File("/Screenshots/" + folder +"/" + getTodaysDate()  //System.getProperty("user.dir") + 
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
