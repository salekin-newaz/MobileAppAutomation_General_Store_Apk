package utilities;

import java.text.DecimalFormat;
import java.time.Duration;
import java.util.HashSet;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class Utilities {
	
	AndroidDriver<AndroidElement> driver;

	public Utilities(AndroidDriver<AndroidElement> driver) {

		this.driver = driver;
	}

	
	public void scrollToText(String value) {
		driver.findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + value + "\"));");
	}

	public void scrollDown(double d, double e) {
		Dimension dimension = driver.manage().window().getSize();
		int scrollStart = (int) (dimension.getHeight() * d);
		int scrollEnd = (int) (dimension.getHeight() * e);

		new TouchAction<>((PerformsTouchActions) driver).press(PointOption.point(0, scrollStart))
				.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(PointOption.point(0, scrollEnd))
				.release().perform();
	}

	// Scroll and Add to cart all product from product page by using smiler by
	// locator product page ADD TO CART button.
	public void scrollNAddToCart(By listItems, String text) throws InterruptedException {
		int i = 0;
		while (i < 11) {
			for (WebElement element : driver.findElements(listItems)) {
				if (element.getAttribute("text").equals(text)) {
					element.click();
				}i++;
			}
			//i++; // for maximum number of scroll and avoid infinite loop
			scrollDown(0.5, 0.1); // scroll screen from 50% to 10%
		}
	}

	// Scroll and collect price of all listed product by using smiler by locator on
	// current page.
	public double scrollNCollectPrice(By listItems) throws InterruptedException {
		HashSet<WebElement> set = new HashSet<>(); // Using Hashset to avoid duplicate
		double sum = 0;
		int i = 0;
		while (i < 7) {
			for (WebElement element : driver.findElements(listItems)) {
				if (set.add(element)) { // if add successful return true and also verify this element is unique
					String amountString = element.getText();
					double amountvalue = getAmount(amountString);
					sum += amountvalue;
				}
				
			}
			i++; // for maximum number of scroll and avoid infinite loop
			scrollDown(0.6, 0.2); // scroll screen from 50% to 20%
		}
		return Double.parseDouble(new DecimalFormat("##.##").format(sum)); // return total amount with two precision
																			// after decimal point
	}

	public static double getAmount(String value) {
		value = value.substring(1);
		double amountValue = Double.parseDouble(value);
		return amountValue;
	}

}