package week4.day2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Table {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://www.leafground.com/pages/table.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		//Get the text of third row second column
		String text = driver.findElement(By.xpath("//table//tr[3]/td[2]")).getText();
		System.out.println("third row second column data : " +text);

		//Get the data from first column.
		for (int i=2;i<=6;i++) {
			WebElement eleRow = driver.findElement(By.xpath("//table//tr[" +i +"]/td[1]"));
			String colData= eleRow.getText();
			System.out.println(colData);
		}

		//Get the count of number of columns
		int colSize = driver.findElements(By.xpath("//table//tr//th")).size();
		System.out.println("No.Of Columns : " +colSize);

		//Get the count of number of rows
		int rowSize = driver.findElements(By.xpath("//table//tr")).size();
		System.out.println("No.Of Rows : " +rowSize);

		//Get the progress value of 'Learn to interact with Elements'
		for (int i=2;i<rowSize;i++) {
			String text2 = driver.findElement(By.xpath("//table//tr["+i+"]/td[1]")).getText();
			if(text2.equals("Learn to interact with Elements"))
				System.out.println(driver.findElement(By.xpath("//table//tr["+i+"]/td[2]")).getText());
		}

		//Check the vital task for the least completed progress.
		/*steps: get the values of second column using for loop, Add values to set to remove the duplicates, 
		      Convert set to list, sort the list*/
		Set<Integer> set1=new HashSet<Integer>();
		for (int j=2;j<=rowSize;j++) {
			String progress = driver.findElement(By.xpath("//table//tr["+j+"]/td[2]")).getText(); //getting all progress column data
			String pro = progress.replaceAll("%",""); //replacing "" in % place in progress value
			int value = Integer.parseInt(pro); //converting string to Integer using Integer.parseInt();
			set1.add(value);
		}
		System.out.println("Set Values:" +set1);
		List<Integer> lst = new ArrayList<Integer>(set1);
		Collections.sort(lst);
		System.out.println("Sorted List: "+lst);

		for (int k=2;k<rowSize;k++){
			String text3 = driver.findElement(By.xpath("//table//tr["+k+"]/td[2]")).getText();
			String replaceAll = text3.replaceAll("%", "");
			int num = Integer.parseInt(replaceAll);
			if(lst.get(0).equals(num))
				driver.findElement(By.xpath("//table//tr[6]/td[3]")).click();
			break;
		}

	}
}




