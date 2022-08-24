package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebBrowser {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        browserShutDown();
     return driver;

    }

    private static void browserShutDown(){
        Runtime.getRuntime().addShutdownHook(new Thread("Driver shutdown thread") {
            public void run () {
                driver.close();
            }
        });
    }


}
