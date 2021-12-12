package tests;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

public class MoscowHotelsPrices {

    WebDriver driver = new ChromeDriver();

    @Test
    public void hotelsPriceRating(){

        driver.manage().window().maximize();
        driver.navigate().to("https://www.booking.com/");
        driver.findElement(By.xpath("//*[@id='ss']")).sendKeys("Москва");
        driver.findElement(By.xpath("//span[@class='sb-date-field__icon sb-date-field__icon-btn bk-svg-wrapper calendar-restructure-sb']")).click();
        driver.findElement(By.xpath("//*[contains(@data-date, '2021-12-17')]")).click();
        driver.findElement(By.xpath("//*[contains(@data-date, '2021-12-24')]")).click();
        driver.findElement(By.xpath("//button[@data-sb-id='main']")).click();
        driver.findElement(By.xpath("//a[@data-type='dropdown']")).click();
        driver.findElement(By.xpath("//ul[@role='menu' ]//a[@data-type='review_score_and_price']")).click();

        WebElement element = new WebDriverWait(driver, 1000).until(
                ExpectedConditions.elementToBeClickable (By.xpath("//div[@data-testid='property-card'][1]//div[@aria-label='Оценка 10']")));

        int maxRating = Integer.parseInt(element.getText().replaceAll("\\D+", ""));

        Assert.assertTrue("Wrong hotel rating ", maxRating >= 9);

//        driver.close();
//        driver.quit();
    }
}
