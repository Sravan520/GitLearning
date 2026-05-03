package stepdefinitions;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import TestComponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.CheckOutPage;
import pageObjects.ConfirmPage;
import pageObjects.LoginPage;
import pageObjects.PlaceOrderPage;
import pageObjects.ProductCatelogue;

public class SubmitOrderStepDef extends BaseTest {
	
	public LoginPage login;
	public ProductCatelogue prodCatelogue;
	public CheckOutPage checkout;
	public ConfirmPage confirmpgage;
	String cvv="513";
	String country="India";
	
	
	@Given("I landed on E commerce page")
	public void I_landed_on_E_commerce_page() throws Exception
	{
		login=launchApplication();
		
	}

	@Given("^the user logged in (.+) with (.+)$")
	public void user_login(String username,String password)
	{
		prodCatelogue=login.loginApplication(username,password);
	}
	
	@When("^Select the (.+)$")
	public void select_product(String product) throws InterruptedException
	{
		List<WebElement> productslist=prodCatelogue.getProductslist();
		prodCatelogue.AddtoCart(product);
	}
	@And("^Checkout (.+) and submit the order$")
	public void checkout_order(String product)
	{
       checkout=prodCatelogue.gotoCart();
		
		Boolean match=checkout.Checkoutorder(product);
		Assert.assertTrue(match);
		PlaceOrderPage placedorder=checkout.checOut();
		
		confirmpgage=placedorder.placeOrder(cvv, country);
	}
	
	@Then( "{string}  validate the confirmation")
	public void validate_confirmation(String message)
	{
		String confirmMessage=confirmpgage.getConfirmMeassage();
		System.out.println("Expected confim message  " + message);
		System.out.println("Sravan Update New");
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(message));
		driver.close();
	}
	
	@Then("{string} validate the error")
	public void validate_error(String error)
	{
		String actualError=login.getErrorMessage();
		System.out.println(actualError);
		Assert.assertEquals(error,actualError);
		driver.close();
	}
	
}
