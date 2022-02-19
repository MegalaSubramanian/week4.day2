package week4.day2.assessment;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class JqueryDraggable {

	public static void main(String[] args) {
			WebDriverManager.chromedriver().setup();
			ChromeDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("https://jqueryui.com/draggable/");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			driver.switchTo().frame(0);
			WebElement draggable = driver.findElement(By.id("draggable"));
			Actions builder = new Actions(driver);
			//builder.dragAndDropBy(draggable, 150, 150).perform();
			builder.keyDown(Keys.CONTROL).clickAndHold(draggable).moveByOffset(150, 150).keyUp(Keys.CONTROL).perform();
	}

}
