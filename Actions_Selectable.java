package week4.day2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Actions_Selectable {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://www.leafground.com/pages/selectable.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		//MUltiple Item selection
		WebElement eleItem1 = driver.findElement(By.xpath("//ol/li[1]"));
		WebElement eleItem7 = driver.findElement(By.xpath("//ol/li[7]"));
		WebElement eleItem3 = driver.findElement(By.xpath("//ol/li[3]"));
		Actions builder=new Actions(driver);
		builder.keyDown(Keys.CONTROL)
		.click(eleItem1)
		.click(eleItem7)
		.click(eleItem3)
		.keyUp(Keys.CONTROL)
		.perform();

	}

}
