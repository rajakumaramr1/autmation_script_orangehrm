//import LoginTest.ChromeDriver;
//import LoginTest.WebDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;
//import org.testng.Assert;

public class AutomationScriptOrangeHRM {

	public static void main(String[] args){
		// TODO Auto-generated method stub
		
//		System.setProperty("webdriver.chrome.driver","C:\\Users\\Admin\\chromedriver.exe");

//        System.out.println("Hello");
//	    WebDriverManager.chromedriver().setup();
	    WebDriver driver=new ChromeDriver();
	    
//         Maximize the browser using maximize() method
        driver.manage().window().maximize();

        // Launching web site
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");
        driver.getTitle();

	}

}
