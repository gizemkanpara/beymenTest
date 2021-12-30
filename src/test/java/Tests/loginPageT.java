package Tests;

import Base.BaseClass;
import org.junit.Assert;
import org.junit.ComparisonFailure;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.Log;

import java.util.concurrent.TimeUnit;

public class loginPageT  extends BaseClass {
    WebDriver driver;
    public loginPageT(){
        driver=super.driver;
    }

    public void LoginWebPage(){
        try {

            WebElement loginButton1 = driver.findElement(new By.ByCssSelector("div.gekhq4-6:first-of-type"));
            loginButton1.click();
           driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            WebElement loginButton2 = driver.findElement(new By.ByCssSelector("a.qjixn8-0:first-of-type"));
            loginButton2.click();
            Log.info("Login Sayfası açıldı.");
        }catch (ComparisonFailure e){
            Log.error("Login Sayfası açılamadı. Hata="+e.toString());
        }
    }

    @Test
    public void Login(){
        try {

            WebElement UserNameField = driver.findElement(By.id("L-UserNameField"));
            UserNameField.click();
            UserNameField.sendKeys("KULLANICI ADINIZ");
            WebElement PasswordField = driver.findElement(By.id("L-PasswordField"));
            PasswordField.click();
            PasswordField.sendKeys("ŞİFRENİZ");
            WebElement loginButton3 = driver.findElement(By.id("gg-login-enter"));
            loginButton3.click();
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            String URL = driver.getCurrentUrl();
            Assert.assertEquals(URL, "https://www.gittigidiyor.com/" );

            Log.info("Login olundu");
        }catch (AssertionError e){
            Log.error("Login olunamadı. Hata="+e.toString());
        }
    }


}
