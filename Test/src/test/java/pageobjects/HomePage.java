package pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomePage {
	public HomePage(WebDriver driver){
		super();
	}    
	
	@FindBy(how=How.XPATH, using="/html/body")
	public static WebElement Home_body;

}
