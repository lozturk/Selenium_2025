import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.properties.Configuration;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Log4j2
public class LoginTest {
    WebDriver driver;
    String url;

    @BeforeClass
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\loztu\\IdeaProjects\\Selenium_2025\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();

    }


    @Test
    public void SignInTest(){
        url = Configuration.getInstance().getProperty("url");
        log.info("URL: {}", url);
        driver.get(url);
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

}
