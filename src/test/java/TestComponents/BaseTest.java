package TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.module.Configuration;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.LoginPage;

public class BaseTest {

	public WebDriver driver;
	public LoginPage login;
	
	public WebDriver IntializeDriver() throws Exception
	{
		// Selecting the Browser
		
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//Resources//GlobalData.properties");
		Properties prop=new Properties();
		prop.load(fis);
		
		String browsername=System.getProperty("browser")!= null? System.getProperty("browser"):prop.getProperty("browser");
	
		
		if(browsername.contains("chrome"))
		{
		
		WebDriverManager.chromedriver().setup();
		ChromeOptions options=new ChromeOptions();
		options.addArguments("headless");
		
		if(browsername.contains("headless"))
		{
			
			driver=new ChromeDriver(options);
		driver.manage().window().setSize(new Dimension(1440,900));
		}
		else
		{
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		} 
		}
		
		else if (browsername.equals("firefox"))
		{
			FirefoxOptions options = new FirefoxOptions();
			options.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
			System.setProperty("webdriver.gecko.driver", "C:\\Drivers\\geckodriver.exe");
			driver=new FirefoxDriver();
			driver.manage().window().maximize();
		}
		
		else if(browsername.equals("edge"))
		{
			System.setProperty("webdriver.edge.driver", "C:\\Drivers\\msedgedriver.exe");
			driver=new EdgeDriver();
			driver.manage().window().maximize();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
	
	@BeforeMethod(alwaysRun=true)
	public LoginPage launchApplication() throws Exception
	{
	  driver=IntializeDriver();
	  login=new LoginPage(driver);
	  login.goto_url();
	  return login;
		
	}
	
	public List<HashMap<String, String>> getJSONData(String filepath) throws IOException
	{
		// reading of JSON to string
		
	String jsondata=FileUtils.readFileToString(new File(filepath),
			StandardCharsets.UTF_8);
	
	// coverting string to Hash Map
	
	ObjectMapper mapper=new ObjectMapper();
	List<HashMap<String,String>> data=mapper.readValue(jsondata,new TypeReference<List<HashMap<String,String>>>(){	});
	
	return data;
	// {map.map1}
		
	}

	@AfterMethod(alwaysRun=true)
	public void tearDown()
	{
		driver.quit();
	}
	
	
	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File file=new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
		FileUtils.copyFile(source, file);
		return  System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
		
	}
	
	
	
}
