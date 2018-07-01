package webtables;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebTablesHw {

	/*
	 * 
	 * 1) goto https://forms.zohopublic.com/murodil/report/Applicants/reportperma/
	 * DibkrcDh27GWoPQ9krhiTdlSN4_34rKc8ngubKgIMy8 2) Create a HashMap 3) change row
	 * number to 100, read all data on first page and put uniquID as a KEY and
	 * Applicant info as a Value to a map.
	 * 
	 * applicants.put(29,"Amer, Sal-all@dsfdsf.com-554-434-4324-130000")
	 * 
	 * 4) Click on next page , repeat step 3 5) Repeat step 4 for all pages 6) print
	 * count of items in a map. and assert it is matching with a number at the
	 * buttom ======================================
	 */
	// td[contains(.,'name1')]/followwing-sibling::td/a
	WebDriver driver;
	Map<List<String>, List<String>> applicantMap = new HashMap<>();
	// Map<Integer, String> applicantMap;
	List<Integer> key1;
	List<String> value1;
	String uniqueID;
	String xpath;
	int col = 1;
	int rowNum;
	String tdData;

	@BeforeClass
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
		driver.get("https://forms.zohopublic.com/murodil/report/Applicants/reportperma/DibkrcDh27GWoPQ9krhiTdlSN4_34rKc8ngubKgIMy8");

	}

	@Test

	public void test1() throws InterruptedException {
		Select select = new Select(driver.findElement(By.id("recPerPage")));
		select.selectByVisibleText("100"); // shu yerda 100 ni kirityapti
		Thread.sleep(1000);

		// shu yerda 100 ta applicantni ismini id sini email hammayogini print qildi
		// men endi follow direction qilishim kerak

		int rowsCount = driver.findElements(By.xpath("//table[@id='reportTab']/tbody/tr")).size();
		System.out.println("How many rows are there : " + rowsCount);

		int colsCount = driver.findElements(By.xpath("//table[@id='reportTab']/thead/tr/th")).size();
		System.out.println("How many columns are there: " + colsCount);

	
		
		//this loop prints only ID numbers 
		
		for (rowNum = 1; rowNum <= rowsCount; rowNum++) {
			xpath = "//table[@id='reportTab']/tbody/tr[" + rowNum + "]/td[" + col + "]";
			uniqueID = driver.findElement(By.xpath(xpath)).getText();
			List<String> key1 = new ArrayList<>();
			key1.add(uniqueID);
			System.out.println(key1);
		}
		
		
		//this loop prints all descriptions except ID
		
		for (int rowNum = 1; rowNum <= rowsCount; rowNum++) {
			for (col = 2; col <= colsCount; col++) {
				String xpath = "//table[@id='reportTab']/tbody/tr[" + rowNum + "]/td[" + col + "]";
				tdData = driver.findElement(By.xpath(xpath)).getText();
				System.out.println("-" + tdData);

		

			}

		}

	}
}
