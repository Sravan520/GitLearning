package sravanacademy;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.CheckOutPage;
import pageObjects.ConfirmPage;
import pageObjects.LoginPage;
import pageObjects.OrderPage;
import pageObjects.PlaceOrderPage;
import pageObjects.ProductCatelogue;

public class SubmitOrderTest extends BaseTest{

	
	String BuyingItem="ZARA COAT 3";
	@Test(dataProvider="getData",groups="Purchase Product")
	public void submitOrderTest(HashMap<String,String> input) throws Exception {
		
		String cvv="513";
		String country="India";
		ProductCatelogue prodCatelogue=login.loginApplication(input.get("username"),input.get("password"));
		
		List<WebElement> productslist=prodCatelogue.getProductslist();
		prodCatelogue.AddtoCart(input.get("product"));
		CheckOutPage checkout=prodCatelogue.gotoCart();
		
		Boolean match=checkout.Checkoutorder(input.get("product"));
		Assert.assertTrue(match);
		PlaceOrderPage placedorder=checkout.checOut();
		
		ConfirmPage confirmpgage=placedorder.placeOrder(cvv, country);
		
		String confirmMessage=confirmpgage.getConfirmMeassage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
	}

	@Test(dependsOnMethods= {"submitOrderTest"})
	public void OrderpageTes() throws Exception
	{
		
		ProductCatelogue prodCatelogue=login.loginApplication("sravank@gmail.com", "Kumar@520");
		OrderPage orderpage=prodCatelogue.gotToOrders();
		Assert.assertTrue(orderpage.ValidateOrderPage(BuyingItem));
	}
	
//  Getting Data from JSON File
	
	@DataProvider
	public Object[][] getData() throws Exception
	{
		
			List<HashMap<String,String>> data=getJSONData(System.getProperty("user.dir")+"//src//test//java//data//purchaseorder.json");
		return new Object[][]{{data.get(0)},{data.get(1)}};
		
			}

	
	//-- getting the date using 2 dimensional array
	
//	@DataProvider
//	public Object[][] getData()
//	{
//		return new Object[][] {{"sravank@gmail.com","Kumar@520","ZARA COAT 3"},{"kumarv@gamail.com","Kumar@520","ADIDAS ORIGINAL"}};
//	} 
//	
	//--getting data using Hash Map
	
//	@DataProvider
//	public Object[][] getData()
//	{
//		HashMap<String,String> map=new HashMap<String,String>();
//		
//		map.put("username","sravank@gmail.com");
//		map.put("password", "Kumar@520");
//		map.put("product","ZARA COAT 3");
//		
//		HashMap<String,String> map1=new HashMap<String,String>();
//		map1.put("username","kumarv@gamail.com");
//		map1.put("password", "Kumar@520");
//		map1.put("product","ADIDAS ORIGINAL");
//		
//		return new Object[][]{{map},{map1}};
//		
//			}
	

	
	
}
