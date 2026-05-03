
package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponents;

public class LoginPage extends AbstractComponents{
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	// page factory elements
	
	@FindBy(id="userEmail")
	WebElement userID;
	
	@FindBy(id="userPassword")
	WebElement passwordEle;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement error;
	
	
	public ProductCatelogue loginApplication(String username,String password)
	{
		userID.sendKeys(username);
		passwordEle.sendKeys(password);
		submit.click();
		ProductCatelogue prodCatelogue=new ProductCatelogue(driver);
		return prodCatelogue;
	}
	
	public void goto_url()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getErrorMessage()
	{
		waitforWebElementtoAppear(error);
		return error.getText();
	}

}
