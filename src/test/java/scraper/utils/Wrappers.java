package scraper.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Wrappers {
    private static final Logger log = LogManager.getLogger(Wrappers.class);

    WebDriver driver;
    WebDriverWait wait;

    // Constructor of Wrappers class to initialize a driver and wait
    public Wrappers(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    // Used to open Url
    public void openUrl(String url) {
        driver.get(url);
    }

    // TO click a WebElement
    public void click(WebElement element) {
        element.click();
    }

    // To send text to a WebElement
    public void sendKeys(WebElement element, String s) {
        element.clear();
        element.sendKeys(s);
    }

    // Locating WebElement by visibility of using xpath
    public WebElement findEleByVisi(String xpath) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

    // Locating WebElement by presence of using xpath
    public WebElement findEleByPres(String xpath) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
    }

    // Converting String into float
    public float stringTOFloat(String s) {
        return Float.parseFloat(s);
    }

    public String getText(WebElement element) {
        return element.getText();
    }

    // Converting the ArrayList<HashMap<String, String>> into JSON file
    public void convertToJson(ArrayList<HashMap<String, String>> arrList, String fileName) {
        ObjectMapper mapper = new ObjectMapper();

        // Converting map to a JSON payload as String
        try {
            String teamYearWin = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(arrList);
            System.out.println(teamYearWin);
        } catch (JsonProcessingException e) {
            log.trace(Arrays.toString(e.getStackTrace()));
        }

        String filePath = System.getProperty("user.dir")  + "/jsonFiles/";
        if(!new File(filePath).exists()) {
            new File(filePath).mkdir();
        }

        // Writing JSON on a file
        try {
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(filePath + fileName), arrList);
        } catch (IOException e) {
            log.trace(Arrays.toString(e.getStackTrace()));
        }
    }

    // Returning epoch time as String
    public String getEpochTime() {
        return "" + System.currentTimeMillis();
    }

    public void quit() {
        // Quiting the browser window or closing all the windows
        if (driver != null) {
            driver.quit();
        }
    }
}