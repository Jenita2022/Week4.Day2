package week4.day2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Actions_Sortable {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://www.leafground.com/pages/sortable.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		//Locate item 3 and item6
		WebElement eleItem3 = driver.findElement(By.xpath("//ul[@id='sortable']/li[3]"));
		WebElement eleItem6 = driver.findElement(By.xpath("//ul[@id='sortable']/li[6]"));
		//Move item 6 to item 3 from bottom to top
		Actions builder = new Actions(driver);
		//builder.dragAndDrop(eleItem6, eleItem3).perform(); This also works fine.
		builder.clickAndHold(eleItem6).moveToElement(eleItem3).release().perform();

	}

}
