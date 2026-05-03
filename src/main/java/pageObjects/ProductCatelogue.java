package pageObjects;

import java.util.List;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import AbstractComponents.AbstractComponents;

public class ProductCatelogue extends AbstractComponents {
	WebDriver driver;
	
	public ProductCatelogue(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> items;
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartOption;
	
	
	By FindByEle=By.cssSelector(".mb-3");
	By prodby=By.cssSelector("b");
	By AddtoCart=By.cssSelector(".card-body button:last-of-type");
	By toastMessage=By.cssSelector("#toast-container");
	By spinner=By.cssSelector(".ng-animating");
	
	public List<WebElement> getProductslist()
	{
		waitforElementtoAppear(FindByEle);
		 return items;
	}
	
	
	public WebElement getProduct(String BuyingItem)
	{
		WebElement prod=	getProductslist().stream().filter(product-> 
		product.findElement(prodby).getText().equals(BuyingItem)).findFirst().orElse(null);
		return prod;
	}
	
	public void AddtoCart(String Item) throws InterruptedException
	{
		WebElement rquiredProduct=getProduct(Item);
		rquiredProduct.findElement(AddtoCart).click();
	     waitforElementtoAppear(toastMessage);	
	 //    waitforElementtoDisappear(spinner);
	     Thread.sleep(1000);
	}
	
	
	
	
	
	
	

}
