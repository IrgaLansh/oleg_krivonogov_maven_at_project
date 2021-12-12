package tests;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class ParisHotelMaxPrice {

    WebDriver driver;

    @Before
    public void before(){
        driver = new ChromeDriver();
        setTimeouts(30);
    }

    private void setTimeouts(int time) {
        driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(time, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(time, TimeUnit.SECONDS);
    }

    @Test
    public void highestPriceTest() {

        driver.manage().window().maximize();
        driver.navigate().to("https://www.booking.com/");
        driver.findElement(By.xpath("//*[@id='ss']")).sendKeys("Париж");
        driver.findElement(By.xpath("//*[contains(@class, 'sb-date-field__icon sb-date-field__icon-btn bk-svg-wrapper calendar-restructure-sb')]")).click();
        driver.findElement(By.xpath("//*[contains(@data-date, '2021-12-17')]")).click();
        driver.findElement(By.xpath("//*[contains(@data-date, '2021-12-24')]")).click();

        driver.findElement(By.xpath("//*[contains(@class, 'xp__guests__count')]")).click();
        driver.findElement(By.xpath("//span[@id='group_adults_desc']/preceding-sibling::button[1]")).click();
        driver.findElement(By.xpath("//span[@id='group_adults_desc']/preceding-sibling::button[1]")).click();
        driver.findElement(By.xpath("//button[contains(@aria-label, 'Номера: увеличить количество')]")).click();
        driver.findElement(By.xpath("//button[contains(@data-sb-id, 'main')]")).click();
        driver.findElement(By.xpath("//a[contains(text(), 'сначала самая низкая')]")).click();

        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(60))
                .pollingEvery(Duration.ofMillis(5))
                .ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".bui-spinner.bui-spinner--size-large")));

        WebElement elementPrice = driver.findElement(By.xpath("//div[@id='searchboxInc']//div[@data-filters-group='pri']//div[contains(text(), '+')]"));
        int expectedPrice = Integer.parseInt(elementPrice.getText().replaceAll("\\D+", ""));
        elementPrice.click();

        new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(60))
                .pollingEvery(Duration.ofMillis(5))
                .ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".bui-spinner.bui-spinner--size-large")));

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        WebElement webElement = driver.findElement(
                By.xpath("//div[@id='search_results_table']//div[@data-testid='property-card'][1]//div[@data-testid='price-and-discounted-price']//span[1]"));

        String actualPrice = Arrays.stream(webElement.getText().split("BYN")).skip(1).findFirst().get().trim().replace(" ", "");

        int actualMaxPrice = Integer.parseInt(actualPrice.replaceAll("\\D+", ""));

        System.out.println("Expected price: " + expectedPrice);
        System.out.println("Actual price: " + actualMaxPrice / 7);

        Assert.assertTrue("Actual price is lower than expected!", actualMaxPrice / 7 >= expectedPrice);

//            driver.close();
//            driver.quit();

    }
}