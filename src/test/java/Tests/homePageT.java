package Tests;

import Base.BaseClass;

import org.junit.Assert;
import org.junit.ComparisonFailure;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import utilities.Log;

import java.net.URL;

import static org.junit.Assert.fail;

public class homePageT extends BaseClass {

    WebDriver driver;

    public homePageT(){
    driver=super.driver;
    }

    @Test
    public  void openWebSite(){

        try {
            driver.manage().window().maximize();
            driver.get("https://www.beymen.com/");

            String URL = driver.getCurrentUrl();
            Assert.assertEquals(URL, "https://www.beymen.com/" );

            Log.info("Anasayfa açıldı."+URL);

        }catch (AssertionError e){
            Log.error("Anasayfa açılamadı "+ driver.getCurrentUrl());
        }

    }
}
