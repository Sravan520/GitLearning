package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import AbstractComponents.AbstractComponents;

public class ConfirmPage extends AbstractComponents{
	
	WebDriver driver;
	
	public ConfirmPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		
	}
	
	@FindBy(css=".hero-primary")
     WebElement message;
	
	By message1=By.cssSelector(".hero-primary");
	
	public String getConfirmMeassage()
	{
		waitforElementtoAppear(message1);
		String OrderMessage=message.getText();
		return OrderMessage;
	}

	
	
}
