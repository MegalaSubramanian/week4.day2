package week4.day2.assessment;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SnapDeal {
	public static void main(String[] args) throws IOException, InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.snapdeal.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		String windowHandle = driver.getWindowHandle();
		System.out.println(windowHandle);
		driver.findElement(By.xpath("//span[@class='catText']")).click();
		driver.findElement(By.xpath("//span[text()='Sports Shoes']")).click();
		WebElement total = driver.findElement(By.xpath(("(//div[@class='child-cat-count '])[3]")));
		String totalItem = total.getText();
		System.out.println("Total no of Sports shoes are : " + totalItem.replaceAll("[^0-9]", ""));
		driver.findElement(By.xpath("//div[contains(text(),'Training Shoes')]")).click();
		driver.findElement(By.xpath("//i[@class='sd-icon sd-icon-expand-arrow sort-arrow']")).click();
		WebElement drop = driver.findElement(By.xpath("//ul[@class='sort-value']/li[2]"));
		drop.click();
		WebElement items = driver.findElement(By.xpath("//span[@class='category-name category-count']"));
		String itemtext = items.getText();
		String replaceAll = itemtext.replaceAll("[^0-9]", "");
		int count = Integer.parseInt(replaceAll);
		WebElement pricetag1 = driver.findElement(By.xpath("(//span[@class='lfloat product-price'][1])[1]"));
		String attribute1 = pricetag1.getText();
		String a = attribute1.replaceAll("[^0-9]", "");
		int price1 = Integer.parseInt(a);
		WebElement pricetag2 = driver.findElement(By.xpath("(//span[@class='lfloat product-price'][1])[2]"));
		String attribute2 = pricetag2.getText();
		String b = attribute2.replaceAll("[^0-9]", "");
		int price2 = Integer.parseInt(b);
		if (price1 <= price2) {
			System.out.println("Items are sorted correctly");
		} else {
			System.out.println("Items are not sorted  correctly");
		}
		WebElement from = driver.findElement(By.name("fromVal"));
		from.clear();
		from.sendKeys("900");
		WebElement to = driver.findElement(By.name("toVal"));
		to.clear();
		to.sendKeys("1200");
		driver.findElement(By.xpath("//div[contains(@class,'price-go-arrow btn')]")).click();
		Thread.sleep(5000);
		//click view more button
	    driver.findElement(By.xpath("(//button[contains(@class,'view-more-button btn btn-line btn-theme-secondary viewMoreFilter')])[2]")).click();
	    //click the navy filter
	    Thread.sleep(5000);
	    driver.findElement(By.xpath("//input[@id='Color_s-Navy']/following-sibling::label")).click();
		//driver.findElement(By.xpath("//label[@for='Color_s-Navy']")).click();
		String filter = driver.findElement(By.xpath("(//div[@class='filters'])[1]")).getText();
		System.out.println("The filters applied are: "+filter);
		if(filter.contains("800"+"1200"+"Navy")) {
			System.out.println("Filters are applied");
		}else {
			System.out.println("Filters are applied");
		}
		Actions builder = new Actions(driver);
		WebElement product1 = driver.findElement(By.xpath("(//div[@class='product-tuple-image '])[1]"));
		builder.moveToElement(product1).perform();
		driver.findElement(By.xpath("(//div[@class='clearfix row-disc'])[1]")).click();	
		WebElement price = driver.findElement(By.xpath("//span[@class='payBlkBig']"));
		String p1 =price.getText();
		WebElement discount = driver.findElement(By.xpath("//span[@class='percent-desc ']"));
		String p2 =discount.getText();
		System.out.println("The cost is :"+p1+"and the discount percentage is:"+p2);
		File source = driver.getScreenshotAs(OutputType.FILE);
		File target = new File("./src/main/resources/snaps/shoe.png");
		FileUtils.copyFile(source,target);
		driver.quit();
	}
}
