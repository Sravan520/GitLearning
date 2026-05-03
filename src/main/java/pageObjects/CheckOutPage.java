package pageObjects;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



import AbstractComponents.AbstractComponents;

public class CheckOutPage extends AbstractComponents{
	
	WebDriver driver;
	public CheckOutPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cartSection h3")
	private List<WebElement> cartProduct;
	
	@FindBy(css=".totalRow button")
	private WebElement checkout;
	
	
	@FindBy(css="[routerlink*='cart']")
	private WebElement cartOption;
	
	public boolean Checkoutorder(String Item)
	{
	
		Boolean match=cartProduct.stream().anyMatch(product->product.getText().equals(Item));
		return match;
		
	}
	
	public PlaceOrderPage checOut()
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		for(int i=0;i<25;i++)
		{
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");	
		}
		checkout.click();
		PlaceOrderPage placeOrder=new PlaceOrderPage(driver);
		return placeOrder;
	}
	
	

}
