package week4.day2.assessment;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Nykaa {
	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.nykaa.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		String windowHandle = driver.getWindowHandle();
		System.out.println(windowHandle);
		WebElement brands = driver.findElement(By.xpath("//a[text() ='brands']"));
		WebElement L = driver.findElement(By.xpath("//a[text() ='L']"));
		WebElement loreal = driver.findElement(By.xpath("(//a[contains(text() , 'Paris')])[7]"));
		Actions builder = new Actions(driver);
		builder.moveToElement(brands).perform();
		builder.moveToElement(L).perform();
		builder.moveToElement(loreal).click().perform();
		String title = driver.getTitle();
		if(title.contains("L'Oreal Paris")) {
			System.out.println("Title contains L'Oreal Paris");
		}else {
			System.out.println("Title does not contains L'Oreal Paris");
		}
Thread.sleep(3000);
driver.findElement(By.xpath("//button[@class=' css-p2rfnw']")).click();
driver.findElement(By.xpath("//span[text()= 'customer top rated']")).click();
WebElement hair = driver.findElement(By.xpath("(//a[text()= 'hair'])[1]"));
WebElement hairCare = driver.findElement(By.xpath("//div[@id ='my-menu']/ul/li[3]/ul/li/div/div[1]/div/div/a"));
WebElement shampoo = driver.findElement(By.xpath("//div[@id ='my-menu']/ul/li[3]/ul/li/div/div[1]/div/ul/li[2]/a"));
builder.moveToElement(hair).perform();
builder.moveToElement(hairCare).perform();
builder.moveToElement(shampoo).click().perform();
Set<String> handle2 = driver.getWindowHandles();
List<String> list1 = new ArrayList<String>(handle2);
String secondWindow = list1.get(1);
driver.switchTo().window(secondWindow);
Thread.sleep(3000);
driver.findElement(By.xpath("//div[@class='sidebar__inner']/div/div[7]/div/div")).click();
driver.findElement(By.xpath("//div[@class='sidebar__inner']/div/div[7]/ul/div/div[7]")).click();
String title2 = driver.getTitle();
if(title2.contains("Shampoo")) {
	System.out.println("Filter is done with shampoo");
}else {
	System.out.println("Filter is not done with shampoo");
}
//driver.findElement(By.xpath("//div[@id='filters-strip']/div[1]/div[1]/div[7]/div[1]")).click();
driver.findElement(By.xpath("//div[@id='product-list-wrap']/div[15]/div[1]/div[1]/a[1]/div[2]/div[1]")).click();
Set<String> handle3 = driver.getWindowHandles();
List<String> list2 = new ArrayList<String>(handle3);
String thirdWindow = list2.get(2);
driver.switchTo().window(thirdWindow);
//WebElement quantity = driver.findElement(By.xpath("//select[@title='SIZE']"));
//Select dropdown = new Select(quantity);
//dropdown.selectByValue("1");
WebElement price = driver.findElement(By.xpath("//div[@class='css-1d5wdox']/span[2]"));
String text = price.getText();
String price1 = text.replaceAll("[^0-9]", "");
System.out.println("MRP of the product is : Rs."+price1);
driver.findElement(By.xpath("(//span[text()='ADD TO BAG'])[1]")).click();
driver.findElement(By.xpath("//header/div/div[2]/div/div[2]/div[2]/button")).click();
driver.switchTo().frame(0);
WebElement total = driver.findElement(By.xpath("//span[text()='Grand Total']//following::div[1]"));
String text1 = total.getText();
String price2 = text1.replaceAll("[^0-9]", "");
System.out.println("The GrandTotal is : Rs."+price2);
driver.findElement(By.xpath("//span[text()='PROCEED']")).click();
//driver.switchTo().defaultContent();
driver.findElement(By.xpath("//button[text()='CONTINUE AS GUEST']")).click();
WebElement grand = driver.findElement(By.xpath("((//div[@class='payment-details-tbl grand-total-cell prl20']//following::div)/span)[1]"));
String Total = grand.getText();
String text2 = Total.replaceAll("[^0-9]", "");
//System.out.println(text2);
if(price2.equals(text2)) {
	System.out.println("The Grand total is similar");
}else {
	System.out.println("The Grand total is not similar");
}

	}

}
