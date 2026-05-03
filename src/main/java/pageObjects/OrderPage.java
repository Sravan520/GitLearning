package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponents;

public class OrderPage extends AbstractComponents{
	
	WebDriver driver;
	
	public OrderPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="td:nth-child(3)")
	List<WebElement> productOrders;
 
	
	public Boolean ValidateOrderPage(String product)
	{
		 Boolean value=productOrders.stream().anyMatch(products->products.getText().equals(product));
		 return value;
	}
	
}
