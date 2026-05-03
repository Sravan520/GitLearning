package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponents;

public class PlaceOrderPage extends AbstractComponents{
	
	WebDriver driver;
	
	public PlaceOrderPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="(//input[@class='input txt'])[1]")
	WebElement cvv;
	
	
	
//	@FindBy(css=".form-group input")
//	WebElement country;
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement countryEle;
	
	@FindBy(css=".ta-item:nth-child(3)")
	WebElement selctCountry;
	
	@FindBy(xpath="//a[contains(@class,'action__submit')]")
	WebElement placeorderbutton;
	
	
	public ConfirmPage placeOrder(String Cvv,String Country)
	{
		cvv.sendKeys(Cvv);
	//	countryEle.sendKeys(Country);
		actionSendkeys(countryEle,Country,selctCountry);
		JavascriptExecutor js=(JavascriptExecutor)driver;
		for(int i=0;i<25;i++)
		{
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");	
		}
		placeorderbutton.click();
		ConfirmPage confirmpgae=new ConfirmPage(driver);
		return confirmpgae;

	}
	
	
	
	

	
	
	
}
