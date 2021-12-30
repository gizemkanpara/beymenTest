package Tests;

import Base.BaseClass;
import org.junit.Assert;
import org.junit.ComparisonFailure;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Log;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class basketPageT extends BaseClass {

    WebDriver driver;
    public basketPageT(){
        driver=super.driver;
    }

    public  void addBasket(){

        try {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", driver.findElement(By.id("add-to-basket")));

            executor.executeScript("arguments[0].click();", driver.findElement(new By.ByCssSelector(".basket-title")));

            Log.info("Ürün sepete eklendi .");
            Log.info("Ürünün sepetteki fiyatı = "+getproductBasketPrice());
        }catch (ComparisonFailure e){
            Log.error("Ürün sepete eklenemedi. ");
        }

    }
    public  Double getproductBasketPrice(){
        try {
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            WebElement pricetext = driver.findElement(new By.ByCssSelector(".new-price"));
            return priceDouble(pricetext.getText());
        }catch (Exception e){
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

    @Test
    public void basketAndPagePrice(Double bPrice,Double pPrice){

        try{
            Assert.assertEquals(bPrice, pPrice );
            Log.info("Ürünün sayfadaki fiyatıyla sepetteki fiyatı aynı. ");
        }catch (AssertionError e){
            Log.error("Ürünün sayfadaki fiyatıyla sepetteki fiyatı farklı. ");
        }
    }

    @Test
    public void productCount(){


        try {

            Select proCount= new Select(driver.findElement(By.xpath("//select[contains(@class,amount)][1]")));
            proCount.selectByIndex(1);

            Assert.assertEquals(proCount.getFirstSelectedOption().getText(), "2" );
            Log.info("Ürünün adedi arttırıldı.");

        }catch (AssertionError e){
            Log.error("Ürünün adedi arttırılamadı. ");

        }

    }

    @Test
    public void basketEmpty(){


        try {
            int i=0;
            WebElement e;
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            List<WebElement> empty = driver.findElements(By.xpath("//div[contains(@class,'gg-d-24')]/div[contains(@class,'row')]/a[contains(@class,'btn-delete')]"));
            while (i<empty.size()) {
                try {
                    driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
                    e=driver.findElement(By.xpath("//div[contains(@class,'gg-d-24')]/div[contains(@class,'row')]/a[contains(@class,'btn-delete')][1]"));
                    e.click();
                    i++;
                }catch (StaleElementReferenceException ex){

                }
            }

            WebDriverWait wait = new WebDriverWait(driver,10);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'gg-w-22')]/h2")));

            WebElement emptyControl=driver.findElement(By.xpath("//div[contains(@class,'gg-w-22')]/h2"));
            Assert.assertEquals(emptyControl.getText(), "Sepetinizde ürün bulunmamaktadır." );
            Log.info("Sepet Boşaltıldı");

        }catch (AssertionError e){
            Log.error("Sepet Boşaltılamadı. ");

        }

    }
}
