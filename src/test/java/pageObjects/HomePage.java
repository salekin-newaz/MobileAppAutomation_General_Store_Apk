 package pageObjects;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import base.Base;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;


public class HomePage {

	public HomePage(AppiumDriver driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
	public WebElement nameField;
	
	@AndroidFindBy(id= "com.androidsample.generalstore:id/radioMale")
	public WebElement maleRadio;
	
	@AndroidFindBy(xpath="//*[@text='Female']")
	public WebElement femaleRadio;
	
	@AndroidFindBy(id="android:id/text1")
	public WebElement dropdown;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
	public WebElement letsShop;
	
	@AndroidFindBy(xpath="//*[@text='Bangladesh']")
	public WebElement ChooseCountryBangladesh; 
	
	
	

}