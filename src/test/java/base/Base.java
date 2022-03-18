package base;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class Base {
	public static Logger logger;

	public static AndroidDriver<AndroidElement> driver;
	public static AndroidDriver<AndroidElement> capabilities() throws MalformedURLException 
	{
		
		//log track
		logger = Logger.getLogger("General Store");
		PropertyConfigurator.configure("Log4j.properties");
		//Letting which application to run
		File f =new File("src");
		File fs = new File(f,"General-Store.apk");
		
		//Openning Emulator
		DesiredCapabilities cap= new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "4TE0216714003365");
		cap.setCapability(MobileCapabilityType.APP, fs.getAbsolutePath());
		//cap.setCapability("skipUnlock", true);
		
		//Running Server in the mentioned port
		AndroidDriver<AndroidElement> driver= new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"),cap);
		
		//Adding wait
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
		
		
	}
}