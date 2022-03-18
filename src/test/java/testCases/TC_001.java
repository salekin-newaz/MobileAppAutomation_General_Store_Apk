package testCases;

import java.net.MalformedURLException;
import java.util.logging.Logger;


import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.Base;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import pageObjects.CheckoutPage;
import pageObjects.HomePage;
import pageObjects.CheckoutPage;
import pageObjects.Products;
import utilities.Utilities;


public class TC_001 extends Base{

	
	@Test
	public void myFirstTest() throws Exception {
		
	
		AndroidDriver<AndroidElement> driver =capabilities();
		Thread.sleep(2000);
		logger.info("Start appium");
		System.out.println("Start");
		HomePage home= new HomePage(driver);
		logger.info("Entered UserName");
		home.nameField.sendKeys("Sami");
		Thread.sleep(2000);
		logger.info("Hide Keyboard");
		driver.hideKeyboard();
		logger.info("Click On Radio Button");
		home.maleRadio.click();
		logger.info("Click On Drop Down");
		home.dropdown.click();
		
		Utilities util= new Utilities(driver);
		util.scrollToText("Bangladesh");
		logger.info("Click On Country");
		home.ChooseCountryBangladesh.click();
		Thread.sleep(2000);
		logger.info("Click On Lets Shop");
		home.letsShop.click();
		Thread.sleep(2000);
		
		Products prod= new Products(driver);
		
		util.scrollNAddToCart(prod.addToCartBy, "ADD TO CART");
		//prod.addToCart.get(1).click();
		//prod.addToCart.get(0).click();
		Thread.sleep(2000);
		prod.cart.click();
		Thread.sleep(2000);
		
		CheckoutPage cPage = new CheckoutPage(driver);
//		
//		 // sum of more than 4 products added to cart
//		 
//		 /* util.scrollDown(.5, .2); double sum = 0; int count =
//		 * cPage.productPrice.size();
//		 * for (int i = 0; i < count; i++) {
//		 * String amount1 = cPage.productPrice.get(i).getText(); 
//		 * double amountValue1 = util.getAmount(amount1); 
//		 * sum = sum + amountValue1; 
//		 * }
//		 * sum = Double.parseDouble(new DecimalFormat("##.##").format(sum));//take upto 2 digit after decimal
//		 */
//		
//		//Total price of all products added to cart
		double sum = util.scrollNCollectPrice(cPage.allProductPrice);
		System.out.println("Sum of Products Individually:" + sum);

		String total = cPage.totalAmount.getText(); 
		double totalValue = util.getAmount(total);
		totalValue = (double)Math.round(totalValue*100)/100;//round up decimal value
		System.out.println("TotalValue from the application: " + totalValue);
	Assert.assertEquals(sum, totalValue);//assertion or verify

		driver.pressKey(new KeyEvent(AndroidKey.HOME));

		driver.quit();
	}

}
