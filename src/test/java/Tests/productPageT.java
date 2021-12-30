package Tests;

import Base.BaseClass;
import org.junit.ComparisonFailure;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.Log;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class productPageT extends BaseClass {
    WebDriver driver;

    public productPageT(){
        driver=super.driver;
    }

    public void selectProduct(){


        try {
            Random rand = new Random();

            List<WebElement> proCount = driver.findElements(By.xpath("//div[contains(@class,'o-productList__itemWrapper')]"));

            JavascriptExecutor executor = (JavascriptExecutor) driver;
           WebElement test=driver.findElement(By.xpath("//div[contains(@class,'o-productList__itemWrapper')][rand.nextInt(proCount.size())]"));

            executor.executeScript("arguments[0].click();",test);
            Thread.sleep(1000);

            Log.info("Ürünün sayfadaki fiyatı = "+getproductPagePrice());
        }catch (ComparisonFailure e){
            Log.error("Ürün seçilemedi. ");
        }catch(InterruptedException ie){
        }

    }


    public Double getproductPagePrice(){
        try {
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            WebElement pricetext = driver.findElement(By.id("sp-price-lowPrice"));
            if (pricetext.getText()=="")
            {
                WebElement pricetext2 = driver.findElement(By.id("sp-price-highPrice"));
                return priceDouble(pricetext2.getText());
            }
            return priceDouble(pricetext.getText());
        }catch (ComparisonFailure e){
            return null;
        }
    }

    public double priceDouble(String price){
        if (price!=null)
        {
            return  Double.parseDouble(price
                    .replace(".","")
                    .replace(",",".")
                    .split(" ")[0]);
        }
      return  0;
    }

}
