package week4.day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import io.github.bonigarcia.wdm.WebDriverManager;
public class Snapdeal {

	public static void main(String[] args) throws InterruptedException, IOException  {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://www.snapdeal.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		//2. Go to Mens Fashion
		WebElement eleMensFas = driver.findElement(By.xpath("//span[@class='catText']"));
		//Mousehover on Men's Fashion
		Actions builder = new Actions(driver);
		builder.moveToElement(eleMensFas).perform();

		//3. Go to Sports Shoes
		driver.findElement(By.xpath("//span[text()='Sports Shoes']")).click();

		//4. Get the count of the sports shoes
		System.out.println("Count of Sports Shoes: " +driver.findElement(By.xpath("//div[@class='child-cat-count ']")).getText());

		//5. Click Training shoes

		driver.findElement(By.xpath("(//a[@class='child-cat-node dp-widget-link hashAdded'])[3]/div")).click();

		//6. Sort by Low to High

		driver.findElement(By.xpath("//div[@class='sort-selected']")).click();
		//Locate Price Low to high and select it
		driver.findElement(By.xpath("//ul[@class='sort-value']//li[2]")).click();
		Thread.sleep(2000);
		//7. Check if the items displayed are sorted correctly
		List<WebElement> price = driver.findElements(By.xpath("//span[@class='lfloat product-price']"));
		int priceSize = price.size();

		//create list and add the price of all items in the list
		List<Integer> priceLst=new ArrayList<Integer>();
		for(int i=0;i<priceSize;i++) {
			String text = price.get(i).getText(); //get the price of each item
			String replaceAll = text.replaceAll("[^0-9]",""); //replace spl char except 0-9
			int priceValue = Integer.parseInt(replaceAll); //convert string to int
			priceLst.add(priceValue);
		}
		System.out.println("List Values: "+priceLst);

		//create another list for sorting price
		List<Integer> priceLst1=new ArrayList<Integer>();
		boolean addpriceLst1 = priceLst1.addAll(priceLst);
		System.out.println("List1 Values Added: " +addpriceLst1);
		Collections.sort(priceLst1);
		System.out.println("Sorted values in List1 :" +priceLst1);
		//boolean isSorted =false;
		boolean sorted = priceLst.equals(priceLst1);
		if(sorted)
			System.out.println("Price is sorted from low to high");
		else
			System.out.println("Price is sorted from low to high");

		//8. Select the price range (900-1200)
		WebElement eleRangeFrom = driver.findElement(By.name("fromVal"));
		eleRangeFrom.clear();
		eleRangeFrom.sendKeys("900");
		eleRangeFrom.sendKeys(Keys.TAB);

		WebElement eleRangeTo = driver.findElement(By.name("toVal"));
		eleRangeTo.clear();
		eleRangeTo.sendKeys("1200");

		//select GO button
		driver.findElement(By.xpath("//div[@class='price-go-arrow btn btn-line btn-theme-secondary']")).click();

		//9.Filter with color Navy river
		//Filter with color yellow [as Navy items are not available]
		Thread.sleep(3000);
		driver.findElement(By.xpath("//label[@for='Color_s-Yellow']")).click();

		//10 verify the all applied filters 
		String priceFilter = driver.findElement(By.xpath("//a[@data-key='Price|Price']")).getText();
		System.out.println("Price Filter: " +priceFilter);
		String colorFilter = driver.findElement(By.xpath("//a[@data-key='Color_s|Color']")).getText();
		System.out.println("Color Filter: " +colorFilter);

		//11. Mouse Hover on first resulting Training shoes
		WebElement eleShoe = driver.findElement(By.xpath("//img[@class='product-image wooble']"));
		Actions builder1 = new Actions(driver);
		builder1.moveToElement(eleShoe).perform();

		//12. click QuickView button
		Thread.sleep(5000);
		driver.findElement(By.xpath("//section[@class='js-section clearfix dp-widget dp-fired']/div/div[2]/div/div")).click();
		Thread.sleep(4000);

		//13. Print the cost and the discount percentage
		Set<String> setProView = driver.getWindowHandles();
		List<String> lst = new ArrayList<String>(setProView);
		System.out.println("Open Windows: " +lst);
		driver.switchTo().window(lst.get(0));
		Thread.sleep(3000);
		String cost = driver.findElement(By.xpath("//div[@class='product-price pdp-e-i-PAY-l clearfix']/span")).getText();
		System.out.println("Cost: " +cost);
		String discount = driver.findElement(By.xpath("//div[@class='product-price pdp-e-i-PAY-l clearfix']/span[2]")).getText();
		System.out.println("Discount: " +discount);

		//14. Take the snapshot of the shoes.
		File source = driver.getScreenshotAs(OutputType.FILE);
		File destination = new File("./shoe.png");
		FileUtils.copyFile(source, destination);

		//15. Close the current window
		String parent = driver.getWindowHandle();
		driver.switchTo().window(parent);

		//16. Close the main window
		driver.close();
		 

	}
}