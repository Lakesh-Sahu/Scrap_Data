package scraper.testcases;

import scraper.utils.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestCases extends Base {

    /*
     * testCase01: Go to this website and click on
     * "Hockey Teams: Forms, Searching and Pagination"
     * 1. Iterate through the table and collect the Team Name, Year and Win % for
     * the teams with Win % less than 40% (0.40)
     * 2. Iterate through 4 pages of this data and store it in an ArrayList
     * 3. Convert the ArrayList object to a JSON file named hockey-team-data.json.
     * Each Hashmap object should contain:
     * a. Epoch Time of Scrape
     * b. Team Name
     * c. Year
     * d. Win %
     */
    @Test(description = """
            Go to "https://www.scrapethissite.com/pages" website and click on "Hockey Teams: Forms, Searching and Pagination"
            * 1. Iterate through the table and collect the Team Name, Year and Win % for
            * the teams with Win % less than 40% (0.40)
            * 2. Iterate through 4 pages of this data and store it in an ArrayList
            * 3. Convert the ArrayList object to a JSON file named hockey-team-data.json.
            * Each Hashmap object should contain:
            * a. Epoch Time of Scrape
            * b. Team Name
            * c. Year
            * d. Win %""")
    public void testCase01() {

        // Creating ArrayList object to store result
        ArrayList<HashMap<String, String>> tableList = new ArrayList<>();

        // Opening required URL
        wp.openUrl("https://www.scrapethissite.com/pages");

        // Locating the required title to be clicked
        WebElement hockeyTeam = wp.findEleByVisi("//a[text()='Hockey Teams: Forms, Searching and Pagination']");

        // Asserting that the located element has required text
        Assert.assertEquals(hockeyTeam.getText(), "Hockey Teams: Forms, Searching and Pagination",
                "Hockey Teams: Forms, Searching and Pagination Not Found");

        // Clicking on the Required link
        wp.click(hockeyTeam);

        // Iterating through 4 pages
        for (int i = 1; i <= 4; i++) {

            // Waiting for a WebElement which contains text "Team Name"
            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//th[contains(text(), 'Team Name')]")));

            // Locating the next button
            WebElement nextBtn = wp.findEleByVisi("//a[@aria-label='Next']");

            // Clicking on the next button
            wp.click(nextBtn);

            // Waiting for the url to contain the current page number so that we can confirm the page is loaded
            wait.until(ExpectedConditions.urlContains("" + i));

            // Finding win % as parent WebElement
            ArrayList<WebElement> parentWin = new ArrayList<>(
                    driver.findElements(By.xpath("//td[@class = 'pct text-success' or @class='pct text-danger']")));

            // Iterating through all parent win % WebElements
            for (WebElement element : parentWin) {

                // Getting the epoch time
                String epochTime = wp.getEpochTime();

                // Getting the win % as String
                String winPer = wp.getText(element);

                // Converting the String win % into float
                float winPerInFloat = wp.stringTOFloat(winPer);

                // If win % is less than 0.40
                if (winPerInFloat < 0.40f) {
                    // Creating new HashMap for each new row
                    HashMap<String, String> singleRowData = new HashMap<>();

                    // Getting team name by the parent win % WebElement
                    String teamName = wp
                            .getText(element.findElement(By.xpath("./preceding-sibling::td[@class='name']")));
                    String year = wp
                            .getText(element.findElement(By.xpath("./preceding-sibling::td[@class='year']")));

                    singleRowData.put("Epoch Time of Scrap", epochTime);
                    singleRowData.put("Team Name", teamName);
                    singleRowData.put("Year", year);
                    singleRowData.put("Win %", winPer);

                    // adding all the hashmap for each time for each page for all pages
                    tableList.add(singleRowData);
                }
            }
        }
        // Converting the ArrayList<HashMap<String, String>> into JSON file
        wp.convertToJson(tableList, "hockey-team-data.json");
    }

    /*
     * testCase02: Go to this website and click on "Oscar Winning Films"
     * 1. Click on each year present on the screen and find the top 5 movies on the
     * list - store in an ArrayList.
     * 2. Keep a Boolean variable "isWinner" which will be true only for the best
     * picture winner of that year.
     * 3. Keep a variable to maintain the year from which the data is scraped
     * 4. Convert the ArrayList object to a JSON file named oscar-winner-data.json.
     * Each HashMap object should contain:
     * a. Epoch Time of Scrape
     * b. Year
     * c. Title
     * d. Nomination
     * e. Awards
     * f. isWinner
     * 5. Store the file in the output folder in the root directory. Assert using
     * TestNG that the file is present and not empty
     */
    @Test(description = """
            Go to "https://www.scrapethissite.com/pages" website and click on "Oscar Winning Films"
            * 1. Click on each year present on the screen and find the top 5 movies on the
            * list - store in an ArrayList.
            * 2. Keep a Boolean variable "isWinner" which will be true only for the best
            * picture winner of that year.
            * 3. Keep a variable to maintain the year from which the data is scraped
            * 4. Convert the ArrayList object to a JSON file named oscar-winner-data.json.
            * Each HashMap object should contain:
            * a. Epoch Time of Scrape
            * b. Year
            * c. Title
            * d. Nomination
            * e. Awards
            * f. isWinner
            * 5. Store the file in the output folder in the root directory. Assert using
            * TestNG that the file is present and not empty""")
    public void testCase02() {
        ArrayList<HashMap<String, String>> topMoviesName = new ArrayList<>();

        wp.openUrl("https://www.scrapethissite.com/pages");

        WebElement OscarTitle = wp.findEleByVisi("//a[contains(text(),'Oscar Winning Films')]");
        wp.click(OscarTitle);

        WebElement chooseTitle = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//h3[contains(text(),'Choose a Year to View Films')]")));

        List<WebElement> allYears = chooseTitle.findElements(By.xpath("./following-sibling::a"));

        // traversing through each year
        for (WebElement yearElement : allYears) {
            String year = yearElement.getText();
            wp.click(yearElement);
            wait.until(ExpectedConditions
                    .visibilityOfElementLocated(By.xpath("//tbody[@id='table-body']/tr[position()<6]")));

            ArrayList<WebElement> parentRows = new ArrayList<>(
                    driver.findElements(By.xpath("//tbody[@id='table-body']/tr[position()<6]")));

            // traversing through 5 rows of each year
            for (WebElement singleRow : parentRows) {
                HashMap<String, String> eachRowMap = new HashMap<>();

                String epoch = wp.getEpochTime();
                String title = singleRow.findElement(By.xpath("./td[1]")).getText();
                String nominations = singleRow.findElement(By.xpath("./td[2]")).getText();
                String awards = singleRow.findElement(By.xpath("./td[3]")).getText();
                boolean isWinner = false;
                try {
                    singleRow.findElement(By.xpath("./td[4]/i"));
                    isWinner = true;
                } catch (Exception ignore) {
                }

                // putting all the key-values pair to the HashMap
                eachRowMap.put("Epoch Time of Scrape", epoch);
                eachRowMap.put("Year", year);
                eachRowMap.put("Title", title);
                eachRowMap.put("Nomination", nominations);
                eachRowMap.put("Awards", awards);
                eachRowMap.put("isWinner", (isWinner + ""));

                // Adding HashMap to the ArrayList of Hashmap
                topMoviesName.add(eachRowMap);
            }
        }
        wp.convertToJson(topMoviesName, "oscar-winner-data.json");
    }
}