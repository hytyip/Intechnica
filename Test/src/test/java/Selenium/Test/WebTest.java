package Selenium.Test;

import org.testng.annotations.Test;

import pageobjects.HomePage;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class WebTest {
	WebDriver driver;
	int QueueCheck;
	
  @Test
  public void main() throws InterruptedException {
	  System.out.println("Start the Traffic Defender test with " + QueueCheck + " queue.");
	  
	  //Open the 1st Traffic Defender test web site
	  driver.get("http://52.48.141.228/");
	  
	  //Verify the home page
	  CheckHomePage(driver);
	  
	  //Open the 2nd Web site, It use different cookie. So, it will display the holding page
	  driver.get("http://52.48.141.228/");
	  //System.out.println(driver.manage().getCookies());
		  
	  //Verify the title
	  Assert.assertEquals(driver.getTitle().equals("TrafficDefender Holding Page"), true, "Wrong title");
	  System.out.println("Correct title");
		  
	  //Verify the header
	  WebElement TD_title = driver.findElement(By.xpath("/html/body/header/h2"));
	  Assert.assertEquals(TD_title.getText().equals("Holding Page"), true, "Wrong TD header");
	  System.out.println("It displays Holding Page.");
		  
	  
	  //Sometimes, it takes longer to display the queue number
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  
	  //Verify the queue number
	  WebElement queue = driver.findElement(By.xpath("//span[@class='odometer-value']")); 
	  Assert.assertEquals(queue.getText().equals(Integer.toString(QueueCheck)), true, "Queue is not the correct number");
	  System.out.println("Queue is " + queue.getText());
	  
	  //Verify the queue length text
	  WebElement queue_text = driver.findElement(By.id("queueLength"));
	  Assert.assertEquals(queue_text.getText().equals(Integer.toString(QueueCheck)), true, "Queue text is not the correct number");
	  System.out.println("Queue text is " + queue_text.getText());
	  
	  //Verify the queue text colour
	  Assert.assertEquals(queue_text.getCssValue("color").equals("rgba(251, 127, 4, 1)"), true, "Wrong colour");
	  System.out.println("It has correct colour " + queue_text.getCssValue("color"));
	  
      //Wait for 50 seconds for 1 queue
	  Thread.sleep(50000);
	
	  //Verify the home page again
	  CheckHomePage(driver);
	  System.out.println("Completed test");
  }
  
  @Parameters({ "QueueCheck" })
  @BeforeTest
  public void beforeTest(String value) {
	  QueueCheck = Integer.parseInt(value);
	  System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
	  this.driver = new ChromeDriver();
  }

  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

  public void CheckHomePage(WebDriver driver) {
	  //Wait for the Web ELement
	  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	  
	  //Verify the title
	  Assert.assertEquals(driver.getTitle(), "Perf test server 1", "Wrong title");
	  System.out.println("Correct Web title.");
	  
	  //Using Page factory to find web element
	  PageFactory.initElements(driver, HomePage.class);
	  
	  //Verify the content
	  Assert.assertEquals(HomePage.Home_body.getText().contains("Hello! I am the TD Perf Test Server 1"), true, "Wrong content");
	  System.out.println("It has correct content.");
	  System.out.println(HomePage.Home_body.getText());
  }
}
