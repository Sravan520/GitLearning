package sravanacademy;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.Test;

import TestComponents.BaseTest;
import pageObjects.CheckOutPage;
import pageObjects.ProductCatelogue;
import TestComponents.Retry;
public class ErrorValidation extends BaseTest{
	
	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void loginerrorValidations()
	{
	
	ProductCatelogue prodCatelogue=login.loginApplication("sravank@gmail.com", "Kumar@5211");
	String actualError=login.getErrorMessage();
	System.out.println(actualError);
	Assert.assertEquals("Incorrect email or pssword.",actualError);
	System.out.println("Sravan Update new");

	}

	@Test
	public void productCatelogueError() throws Exception {
		
		String BuyingItem="ZARA COAT 3";
		String cvv="513";
		String country="India";
		ProductCatelogue prodCatelogue=login.loginApplication("sravank@gmail.com", "Kumar@520");
		
		List<WebElement> productslist=prodCatelogue.getProductslist();
		prodCatelogue.AddtoCart(BuyingItem);
		CheckOutPage checkout=prodCatelogue.gotoCart();
		
		Boolean match=checkout.Checkoutorder("ZARA COAT 73");
		Assert.assertFalse(match);
	}
	
	@Test
	public void productCatelogueError2() throws Exception {
		
		String BuyingItem="ZARA COAT 3";
		String cvv="513";
		String country="India";
		ProductCatelogue prodCatelogue=login.loginApplication("sravank@gmail.com", "Kumar@520");
		
		List<WebElement> productslist=prodCatelogue.getProductslist();
		prodCatelogue.AddtoCart(BuyingItem);
		CheckOutPage checkout=prodCatelogue.gotoCart();
		
		Boolean match=checkout.Checkoutorder("ZARA COAT 73");
		Assert.assertFalse(match);
	}
	
	@Test
	public void productCatelogueError3() throws Exception {
		
		String BuyingItem="ZARA COAT 3";
		String cvv="513";
		String country="India";
		ProductCatelogue prodCatelogue=login.loginApplication("sravank@gmail.com", "Kumar@520");
		
		List<WebElement> productslist=prodCatelogue.getProductslist();
		prodCatelogue.AddtoCart(BuyingItem);
		CheckOutPage checkout=prodCatelogue.gotoCart();
		
		Boolean match=checkout.Checkoutorder("ZARA COAT 73");
		Assert.assertFalse(match);
	}
	
	@Test
	public void productCatelogueError4() throws Exception {
		
		String BuyingItem="ZARA COAT 3";
		String cvv="513";
		String country="India";
		ProductCatelogue prodCatelogue=login.loginApplication("sravank@gmail.com", "Kumar@520");
		
		List<WebElement> productslist=prodCatelogue.getProductslist();
		prodCatelogue.AddtoCart(BuyingItem);
		CheckOutPage checkout=prodCatelogue.gotoCart();
		
		Boolean match=checkout.Checkoutorder("ZARA COAT 73");
		Assert.assertFalse(match);
	}
	
}
