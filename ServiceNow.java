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

import io.github.bonigarcia.wdm.WebDriverManager;

public class ServiceNow {

	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://dev87145.service-now.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		WebElement mainframe = driver.findElement(By.id("gsft_main"));
		driver.switchTo().frame(mainframe);
		driver.findElement(By.id("user_name")).sendKeys("admin");
		driver.findElement(By.id("user_password")).sendKeys("Test@2022");
		driver.findElement(By.xpath("//button[text() ='Log in']")).click();
		driver.findElement(By.xpath("//input[@id ='filter']")).sendKeys("incident");
		driver.findElement(By.xpath("(//div[text() ='All'])[2]")).click();
		driver.switchTo().frame(0);
		driver.findElement(By.xpath("//button[text()='New']")).click();
		String home = driver.getWindowHandle();
		driver.findElement(By.id("lookup.incident.caller_id")).click();
		Set<String> window = driver.getWindowHandles();
		List<String> list = new ArrayList<String>(window);
		String secondWindow = list.get(1);
		driver.switchTo().window(secondWindow);
		driver.findElement(By.xpath("//a[@sys_id ='62826bf03710200044e0bfc8bcbe5df1']")).click();
		driver.switchTo().window(home);
		driver.switchTo().frame(0);
		driver.findElement(By.xpath("(//input[contains(@name,'incident.short_description')])[4]")).sendKeys("Testing");
		WebElement number = driver.findElement(By.id("incident.number"));
		String incidentID = number.getAttribute("value");
		System.out.println(incidentID);
		driver.findElement(By.xpath("//button[text() ='Submit']")).click();
		driver.switchTo().window(home);
		driver.switchTo().frame(0);
		WebElement search = driver.findElement(By.xpath("(//label[text()='Search'])[2]/following::input"));
		search.sendKeys(incidentID);
		search.click();
		Thread.sleep(3000);
		//WebElement body = driver.findElement(By.xpath("//body[@data-formname='sys_list_template2']"));
		File screenshotAs = driver.getScreenshotAs(OutputType.FILE);//method used to take Screenshot
	    File target = new File("./src/main/resources/snaps/img.png");	//set the storage path
	    FileUtils.copyFile(screenshotAs, target);

	}
}
