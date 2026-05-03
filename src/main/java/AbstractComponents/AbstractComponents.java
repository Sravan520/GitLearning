package AbstractComponents;

import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.CheckOutPage;
import pageObjects.OrderPage;

public class AbstractComponents {
	WebDriver driver;
	
	public AbstractComponents(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartOption;
	
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement OrderHeader;
	
	
	public void waitforElementtoAppear(By FindBy)
	{
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfElementLocated(FindBy));
	}
	
	
	public void waitforWebElementtoAppear(WebElement FindBy)
	{
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOf(FindBy));
	}
	

	public void waitforElementtoDisappear(By ele)
	{
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(2));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(ele));
		
	}
	
	public void actionSendkeys(WebElement ele,String value,WebElement ele1)
	{
		Actions a =new Actions(driver);
		a.sendKeys(ele,value).build().perform();
		ele1.click();
		
	}
	
	public CheckOutPage gotoCart() {
		
		cartOption.click();
		CheckOutPage checkout=new CheckOutPage(driver);
		return checkout;
	}
	
	
	public OrderPage gotToOrders() 
	{
	OrderHeader.click();
	OrderPage orderpage=new OrderPage(driver);
	return orderpage;
	}
	
	
	
}
