package Base;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseClass {

    public final static Logger logger = Logger.getLogger(BaseClass.class);
    public static WebDriver driver;

    @Before
    public static void StartUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @After
    public  static void tearDown(){

        driver.quit();

    }
}