package Tests;

import Base.BaseClass;
import org.junit.Assert;
import org.junit.ComparisonFailure;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Log;

public class searchPageT extends BaseClass {

    WebDriver driver;
    String searchWordf;
    public searchPageT(){
        driver=super.driver;
    }
    @Test
    public  void search(String searchWord){
        try {
            searchWordf=searchWord;
            WebElement searchTextbox = driver.findElement(new By.ByCssSelector("input.default-input"));
            searchTextbox.click();
            searchTextbox.sendKeys(searchWord);

            WebElement searchButton = driver.findElement(new By.ByCssSelector("button.o-header__search--btn"));
            searchButton.click();
            Log.info("Arama yapıldı. "+searchWord);
        }catch (ComparisonFailure e){
            Log.error("Arama yapılamadı.");
        }

    }

    @Test
    public  void searchSecondPage(){
        try {
//            JavascriptExecutor executor = (JavascriptExecutor) driver;
//            executor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[contains(@class,'pager')]/ul/li[2]/a")));
//            String URL = driver.getCurrentUrl();
//            Assert.assertEquals(URL, "https://www.gittigidiyor.com/arama/?k="+searchWordf+"&sf=2" );
//            Log.info("2.sayfa açıldı. ");

            //Sayfanın en altına gider#moreContentButton
            Thread.sleep(1000);

            driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);

                Thread.sleep(1000);

           driver.findElement(new By.ByCssSelector("#moreContentButton")).click();


            Thread.sleep(1000);


        }catch (AssertionError e){
            Log.error("2.sayfa açılamadı ");
        }
        catch(InterruptedException ie){
        }
    }
}
