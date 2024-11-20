import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeOptions;
import org.junit.Assert;
import java.time.Duration;
import java.util.List;

public class SamplEditHRM {

	public static void main(String[] args) {

		// Create ChromeOptions to manage browser behavior
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");

		// Initialize WebDriver
		WebDriver driver = new ChromeDriver(options);

		try {
			// Navigate to the login page
			driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");

			//wait element declaration
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement usernameField = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[name='username']")));
			
			// Login with valid credentials
			usernameField.sendKeys("Admin");

			driver.findElement(By.name("password")).sendKeys("admin123");

			WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));
			loginButton.click();

			String title = driver.getTitle();
			if (title.contains("OrangeHRM")) {
				System.out.println("Login successful!");
			} else {
				System.out.println("Login failed!");
			}

        	//Fetching the first Emp name from result table
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='oxd-table-card']/div[1]/div[4]")));

			
			List<WebElement> tableElements = driver.findElements(By.xpath("//div[@class='oxd-table-card']/div[1]/div[4]"));

			
			WebElement element = tableElements.getFirst();
			System.out.println("Fetching the first Emp name from the Employee table is " + element.getText());
	
			WebElement employeeNameTextBox = driver.findElement(By.xpath("//input[@placeholder='Type for hints...']"));
		
			String searchWordFullName = element.getText();
			String searchWord = searchWordFullName.split(" ")[0]; 
			
			//Entering the partial Employee name in Employee name field
			employeeNameTextBox.sendKeys(searchWord + " ");
			Thread.sleep(5000);		
			
			//This is the employee name suggestion list
			List<WebElement> empElements = driver
					.findElements(By.xpath("//div[@class='oxd-autocomplete-wrapper']/div[2]"));
			WebElement firstElement = empElements.getFirst();
			String firstValue = firstElement.getText();    
			
			System.out.println("Selecting first element from the displayed Employee name list is  " +  firstValue);
			
			firstElement.click();
	
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[type='submit']")));

			WebElement searchButton = driver.findElement(By.cssSelector("button[type='submit']"));

			searchButton.click();

			Thread.sleep(5000);

			String firstWord = firstValue.split(" ")[0];        
			
			List<WebElement> tableElements1 = driver.findElements(By.xpath("//div[@class='oxd-table-card']/div[1]/div[4]"));
		
			int sizetableElements1 = tableElements1.size();
			
			System.out.println("Size of the rows from the result table after searching for the particular Emp name is " + sizetableElements1);
			
			System.out.println("Testing whether table displays Employee data or not for the particular Emp name");
			
			for (int i = 0; i < sizetableElements1; i++) {	
				String tableItem = tableElements1.get(i).getText();
				if ((tableItem.contains(firstWord))) {							
				} else {
					System.out.println("The obtained table not shows valid result");					
				}
				
				if (i == sizetableElements1 - 1) {				    
					System.out.println("The obtained table shows particular emp data after searching the Emp name");
				}
			}
			
			//Trying to click on Disabled option of drop down
			WebElement dropdown = driver.findElement(
					By.xpath("//div[@class='oxd-grid-4 orangehrm-full-width-grid']/div[4]/div/div[2]/div/div/div"));

			dropdown.click();

			List<WebElement> optionElements = driver.findElements(
					By.xpath("//div[@class='oxd-grid-4 orangehrm-full-width-grid']/div[4]/div/div[2]/div/div/div"));

			//Selecting the Disabled option for the status of the Employee name 
			WebElement disableElement = optionElements.getLast();

			disableElement.click();

			searchButton.click();
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='orangehrm-paper-container']/div[2]/div/span")));

			System.out.println("===================================================================================================");
			
			System.out.println("Testing whether table shows results or not after modifying the status of same Emp name to 'Disabled'");
			
			WebElement noRecordsFound = driver
					.findElement(By.xpath("//div[@class='orangehrm-paper-container']/div[2]/div/span"));
			if (noRecordsFound.getText().equals("No Records Found")) {
				System.out.println("The re obtained table is empty after selecting the 'Disabled' option for the particula Emp name");
			} else {
				System.out.println("The re obtained table contains values after selecting the 'Disabled' option for the particular Emp name");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Test Failed: " + e.getMessage());
		} finally {
			// Close the browser
            driver.quit();
		}
	}

	private static Object firstValue(String text) {
		// TODO Auto-generated method stub
		return null;
	}
}
