package seleniumPractices;

import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Ignore;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.time.Duration;

@Ignore
public class SeleniumQuick {

    public static void main (String[]args){

        // Set up ChromeOptions
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Run Chrome in headless mode
        options.addArguments("--disable-gpu"); // Disable GPU rendering
        options.addArguments("--window-size=1920,1080"); // Set window size for headless mode

        // Initialize WebDriver with headless dropDownOptions
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/dropdown");
        System.out.println("Page title is : " + driver.getTitle());
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);


        WebElement dropdown = driver.findElement(By.id("dropdown"));
        Select selectObject = new Select(dropdown);

        List <WebElement> dropDownOptions = selectObject.getOptions();
        for (WebElement option : dropDownOptions ){
            System.out.println(option.getText());
        }
        Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);

        System.out.println(selectObject.getFirstSelectedOption().getText());
        Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);

        selectObject.selectByIndex(1);
        System.out.println(selectObject.getFirstSelectedOption().getText());
        Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);

        selectObject.selectByVisibleText("Option 2");
        System.out.println(selectObject.getFirstSelectedOption().getText());
        Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);

        selectObject.selectByValue("1");
        System.out.println(selectObject.getFirstSelectedOption().getText());
        Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);


        driver.quit();

    }


}
