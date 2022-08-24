package pages;

import com.google.common.base.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.WebBrowser;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Page {

    private static final int TIMEOUT = 30;

    protected WebDriver driver = WebBrowser.getDriver();

    public Page() {
        PageFactory.initElements(driver, this);
    }


    public void getUrl(final String url) {
        driver.navigate().to(url);
    }

    protected String getTitle() {
        return driver.getTitle();
    }

    private static void waitForElementVisible(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    private static void waitForElementClickable(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    private void waitForObject(WebElement element){

        Wait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(60))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(java.util.NoSuchElementException.class);

        WebElement innerElement = fluentWait
                .until((Function<WebDriver, WebElement>) driver -> element);
        System.out.println("*****FLUENT WAIT Result: " + innerElement.getText());

    }






    @FindBy(how = How.ID, using = "suggestion-search")
    private WebElement searchField;

    @FindBy(how = How.CSS, using = "#main div h1 span")
    private WebElement movieTitleSearchResult;

    @FindBy(how = How.CSS, using = "#main > div > div.findSection > table > tbody > tr:nth-child(1) > td.result_text > a")
    private WebElement movieTitle;

    //place holder
    @FindBy(how = How.CSS, using = "")
    private WebElement releaseDate;

    //place holder
    @FindBy(how = How.CSS, using = "")
    private WebElement countryOfOrigin;

    protected void typeText(String text) {
        //waitForElementVisible(driver, searchField);
        waitForObject(searchField);
        searchField.sendKeys(text);
        searchField.sendKeys(Keys.ENTER);
    }

    protected String getSearchResult() {
        //waitForElementVisible(driver, searchField);
        waitForObject(movieTitleSearchResult);
        return movieTitleSearchResult.getText();
    }

    protected void clickOnMovieTitle(String text) {
        waitForObject(driver.findElement(By.linkText(text)));
        waitForElementClickable(driver, movieTitle);
        driver.findElement(By.linkText(text)).click();
    }

    protected String getReleaseDate(String text) {
        return getText(text);
    }

    protected String getCountryOfOrigin(String text) {
        return getText(text);
    }

    private String getText(String text){
        waitForObject(driver.findElement(By.linkText(text)));
        return driver.findElement(By.linkText(text)).getText();
    }

}
