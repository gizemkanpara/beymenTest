import Base.BaseClass;
import Tests.*;
import org.junit.Test;

import org.apache.log4j.Logger;
import utilities.Log;


public class manage {



    public static void main(String[] args) {


        System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");

        BaseClass base = new BaseClass();
        base.StartUp();

        double pageprice;

        homePageT test1= new homePageT();
        searchPageT test3= new searchPageT();
        productPageT test4= new productPageT();
        basketPageT test5= new basketPageT();

        Log.startLog();
        test1.openWebSite();
        test3.search("pantolon");
        test3.searchSecondPage();
        test4.selectProduct();
        pageprice=test4.getproductPagePrice();
        test5.addBasket();
        test5.basketAndPagePrice(test5.getproductBasketPrice(),pageprice);
        test5.productCount();
        test5.basketEmpty();
        test1.tearDown();
        Log.endLog();


    }


}
