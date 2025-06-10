package scraper.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Base {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static Wrappers wp;

    public void startBrowser() {

        // Initializing the instance variable of ChromeDriver class
        driver = new ChromeDriver();

        // Maximizing the browser window
        driver.manage().window().maximize();

        // Initializing the instance variable of WebDriverWait class
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Initializing the instance variable of Wrappers class
        wp = new Wrappers(driver, wait);
    }
}