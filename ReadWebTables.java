package webtables;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ReadWebTables {
	String url = "file:///C:/Users/Feruza/eclipse-workspace/selenium-maven-automation/src/test/java/webtables/webtable.html";

	WebDriver driver;
	BufferedReader br;
	List<String> cities = new ArrayList<>();
	List<String> countries = new ArrayList<>();

	@BeforeClass
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();

	}

	@Test

	public void readScores() {
		driver.get(url);
		
		
		//reads the table name
		WebElement table = driver.findElement(By.tagName("table"));
		System.out.println(table.getText());

		
		//find how many rows in a table
		List<WebElement> rows = driver.findElements(By.xpath("//table[@id='worldcup']/tbody/tr"));
		System.out.println("Number of data rows: " + rows.size());

		// print all table headers, one by one
		//get all headers into a list
		//use a loop to print it out

		String headerXpath = "//table[@id='worldcup']/thead/tr/th";
		List<WebElement> headers = driver.findElements(By.xpath(headerXpath));
		
		List<String> expHeaders = Arrays.asList("Team1", "Score", "Team2");
		List<String> actualHeaders = new ArrayList<>();
		
		
		for (WebElement each : headers) {
			System.out.println(each.getText());

		}
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(actualHeaders, expHeaders);
		
		//write xpath and findelement get text --- need to print egypt
		
		String egyptXpath = "//table[@id='worldcup']/tbody/tr[4]/td[1]";
		
		softAssert.assertEquals(driver.findElement(By.xpath(egyptXpath)).getText(), "Egypt");
		
		
		int rowsCount = driver.findElements(By.xpath("//table[@id='worldcup']/tbody/tr")).size();
		int colsCount = driver.findElements(By.xpath("//table[@id='worldcup']/thead/tr/th")).size();
		
		System.out.println("===============");
		
		for(int rowNum = 1; rowNum <= rowsCount; rowNum++) {
			for(int col = 1; col <= colsCount; col++) {
				String xpath = "//table[@id='worldcup']/tbody/tr["+rowNum+"]/td["+col+"]";
				String tdData = driver.findElement(By.xpath(xpath)).getText();
				System.out.print("Bu nima?   " + tdData +"  \t");
			}
			System.out.println();
		}
		
		
		//
		
		softAssert.assertAll();
		
		
	}
	@Ignore
	@Test
	public void applicantsData() {
		driver.get("https://forms.zohopublic.com/murodil/report/Applicants/reportperma/DibkrcDh27GWoPQ9krhiTdlSN4_34rKc8ngubKgIMy8");
		
		printTableData("reportTab");
	
	}
	
	public void printTableData(String id) {
		int rowsCount = driver.findElements(By.xpath("//table[@id='"+id+"']/tbody/tr")).size();
		int colsCount = driver.findElements(By.xpath("//table[@id='"+id+"']/thead/tr/th")).size();
		
		System.out.println("===============");
		
		for(int rowNum = 1; rowNum <= rowsCount; rowNum++) {
			for(int col = 1; col <= colsCount; col++) {
				String xpath = "//table[@id='"+id+"']/tbody/tr["+rowNum+"]/td["+col+"]";
				String tdData = driver.findElement(By.xpath(xpath)).getText();
				System.out.print(tdData +"  \t");
			}
			System.out.println();
		}
	}
	

}