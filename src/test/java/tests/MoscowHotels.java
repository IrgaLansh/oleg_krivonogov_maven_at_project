package tests;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class MoscowHotels {

    WebDriver driver = new ChromeDriver();

    @Test
    public void moscowHotelsExists() {

        driver.manage().window().maximize();
        driver.navigate().to("https://www.booking.com/");
        driver.findElement(By.xpath("//*[@id='ss']")).sendKeys("Москва");
        driver.findElement(By.xpath("//*[contains(@class, 'sb-date-field__icon sb-date-field__icon-btn bk-svg-wrapper calendar-restructure-sb')]")).click();
        driver.findElement(By.xpath("//*[contains(@data-date, '2021-12-07')]")).click();
        driver.findElement(By.xpath("//*[contains(@data-date, '2021-12-14')]")).click();
        driver.findElement(By.xpath("//button[@data-sb-id='main']")).click();

        WebElement elementExist = driver.findElement(By.xpath("//div[@data-testid='property-card'][1]"));
        Assert.assertTrue(elementExist.isDisplayed());

        //driver.close();
        //driver.quit();
    }
}
