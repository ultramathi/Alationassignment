package com.test;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class Alationassignment

{

	private static WebDriver driver;
	
	@BeforeSuite(alwaysRun = true)
	public void beforeSuite()
	
	{
		
		
		// Set system property and Initialize driver 
		 
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.amazon.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
	}
		
	@Test
	public void searchData()
	
	{
		
		// Select value from dropdown and search for the keyword  
		
		Select searchbox = new Select(driver.findElement(By.xpath("//select[@id='searchDropdownBox']")));
		searchbox.selectByVisibleText("Books");
		WebElement searchfor = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
		searchfor.sendKeys("data catalog");
		driver.findElement(By.xpath("//*[@id=\"nav-search\"]/form/div[2]/div/input")).click();
		
		/* we can also use below code to click search 
		searchfor.submit(); */
		
		
	}
	
	@Test(dependsOnMethods = "searchData")
	public void fetchAndDisplay()
	
	{
		
		
		/* Search and choose dynamic result displaying id and display the first result's data
		  These are the two dynamic id assigned by amazon.com for the search results
		//div[@id='search-main-wrapper']
		//div[@id="search"] */
		
		String dynid1 = new String();
		List<WebElement> search1 = driver.findElements(By.xpath("//div[contains(@id,'search')]"));
		for(int i=0;i<search1.size();i++)
		{
		   if(search1.get(i).getAttribute("id").equals("search-main-wrapper")||search1.get(i).getAttribute("id").equals("search"))
		     {
			     dynid1 = search1.get(i).getAttribute("id").toString();
			     break;
		     }
	    }
		
		String dynid2 = new String();
		String[] str = dynid2.split("\n");
		for(int j=0;j<str.length;j++)
		{
		   System.out.println(str[j]);
		}
		  
		
		/* To Print with Custom names
        
        System.out.println("Title :" +str[0]+"\r\n" + "Author :" + str[1]+"\r\n" +" No.of reviews :"+str[2] +"\r\n"+ "Book Format :" + str[3] +"\r\n"+ "DiscountedPrice in Dollars :" + str[4] +"\r\n"+ "DiscountedPrice in Cents:" +str[5] +"\r\n"+ "OriginalPrice :" +str[6]+"\r\n"+str[7]+"\r\n"+str[8]+"\r\n");
	  
	     */
	}
	

       
	@AfterSuite(alwaysRun = true)
	public void tearDown() 
	
	{
		driver.quit();
	}

}
