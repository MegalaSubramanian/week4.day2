package week4.day2.assessment;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Amazon {
	public static void main(String[] args) throws IOException, InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.amazon.in/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("oneplus 9 pro", Keys.ENTER);
		WebElement price = driver.findElement(By.xpath("(//span[@class='a-price-whole'])[1]"));
		String value = price.getText();
		System.out.println("Price of first product is : " + value);
		String price1 = value.replaceAll("[^0-9]", "");
		System.out.println("Product value is : " + price1);
		int productPrice = Integer.parseInt(price1);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.titleContains("oneplus"));
		WebElement rate = driver.findElement(By.xpath("((//div[@class='a-row a-size-small'][1])[1])/span[1]"));
		String rating = rate.getAttribute("aria-label");
		System.out.println(rating);
		driver.findElement(By.xpath("(//div[@class='a-section a-spacing-small a-spacing-top-small'])[2]/div")).click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> list = new ArrayList<String>(windowHandles);
		String newWindow = list.get(1);
		driver.switchTo().window(newWindow);
		File source = driver.getScreenshotAs(OutputType.FILE);
		File target = new File("./src/main/resources/snaps/mobile.png");
		FileUtils.copyFile(source, target);
		driver.findElement(By.xpath("//input[@id = 'add-to-cart-button'][1]")).click();
		WebElement total = driver.findElement(By.id("attach-accessory-cart-subtotal"));
		total.click();
		String total1 = total.getText();
		String totalPrice = total1.replaceAll("[^1-9]", "");
		System.out.println(totalPrice);
		int subtotal = Integer.parseInt(totalPrice);
		if (productPrice == subtotal) {
			System.out.println("The product value is equal to the subtotal amount");
		} else {
			System.out.println("The product value is not equal to the subtotal amount");
		}
	}
}
