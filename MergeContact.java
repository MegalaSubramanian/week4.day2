package week4.day2.assessment;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MergeContact {
	
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://leaftaps.com/opentaps/control/login");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		driver.findElement(By.id("username")).sendKeys("Democsr");
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		driver.findElement(By.className("decorativeSubmit")).click();
		driver.findElement(By.linkText("CRM/SFA")).click();
		driver.findElement(By.xpath("//a[text() = 'Contacts']")).click();
		driver.findElement(By.xpath("//a[text() = 'Merge Contacts']")).click();
		String home = driver.getWindowHandle();
		driver.findElement(By.xpath("(//img[@src = '/images/fieldlookup.gif'])[1]")).click();
		Set<String> from = driver.getWindowHandles();
		List<String> listfrom = new ArrayList<String>(from);
		String secondWindow = listfrom.get(1);
		driver.switchTo().window(secondWindow);
		driver.findElement(By.xpath("(//a[@class='linktext'])[1]")).click();
		driver.switchTo().window(home);
		driver.findElement(By.xpath("(//img[@src = '/images/fieldlookup.gif'])[2]")).click();
		Set<String> to = driver.getWindowHandles();
		List<String> listto = new ArrayList<String>(to);
		String thirdwindow = listto.get(1);
		driver.switchTo().window(thirdwindow);
		driver.findElement(By.xpath("(//a[@class='linktext'])[6]")).click();
		driver.switchTo().window(home);
		driver.findElement(By.xpath("//a[text()= 'Merge']")).click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		System.out.println("Page title is: "+driver.getTitle());
 
	}

}
