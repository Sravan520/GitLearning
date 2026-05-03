package sravanacademy;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.LoginPage;

public class StandAloneTest {

	public static void main(String[] args) {
		
		String BuyingItem="ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		
		LoginPage login=new LoginPage(driver);
		
		driver.findElement(By.id("userEmail")).sendKeys("sravank@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Kumar@520");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		List<WebElement> items=driver.findElements(By.cssSelector(".mb-3"));
		
		
	   WebElement prod=	items.stream().filter(product-> 
		product.findElement(By.cssSelector("b")).getText().equals(BuyingItem)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		
		List<WebElement> cartProduct=driver.findElements(By.cssSelector(".cartSection h3"));
		
		Boolean match=cartProduct.stream().anyMatch(product->product.getText().equals(BuyingItem));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".actions a")));
		driver.findElement(By.xpath("(//input[@class='input txt'])[1]")).sendKeys("513");
		driver.findElement(By.cssSelector(".form-group input")).sendKeys("india");
		
		Actions a=new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"india");
		
		driver.findElement(By.cssSelector(".ta-item:nth-child(3)")).click();
	//	a.scrollToElement(driver.findElement(By.cssSelector(".actions a")));
	    
		JavascriptExecutor js=(JavascriptExecutor)driver;
		
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	    
		a.scrollToElement(driver.findElement(By.xpath("//a[contains(@class,'action__submit')]")));	
		driver.findElement(By.xpath("//a[contains(@class,'action__submit')]")).click();
		String confirmMessage=driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
		driver.close();
		
		
		
		
		
		
		
		

	}

}
