package tests;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class BookingJSColors {

    WebDriver driver = new ChromeDriver();

    @Test
    public void bookingJS() {

//        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true)",element1);
//        ((JavascriptExecutor)driver).executeScript("arguments[0].style.backgroundColor = 'green'",element);
//        ((JavascriptExecutor)driver).executeScript("arguments[0].style.color = 'red'",element);
//        ((JavascriptExecutor)driver).executeScript("arguments[0].click",element);

        driver.manage().window().maximize();
        driver.navigate().to("https://www.booking.com/");
        driver.findElement(By.xpath("//*[@id='ss']")).sendKeys("Москва");
        driver.findElement(By.xpath("//button[@data-sb-id='main']")).click();

        WebElement tenElement = driver.findElement(By.xpath("//div[@data-testid='property-card'][10]"));
        WebElement titleElement = driver.findElement(By.xpath("//div[@data-testid='property-card'][10]//div[@data-testid='title']"));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", tenElement);
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.backgroundColor = 'green'", tenElement);
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.color = 'red'", titleElement);

        WebElement actualElement = driver.findElement(By.xpath("//div[@data-testid='property-card'][10]//div[@data-testid='title']"));
        String actualColor = actualElement.getCssValue("color");

        Assert.assertEquals("Wrong result", "rgba(255, 0, 0, 1)", actualColor);

        //driver.close();
        //driver.quit();
    }
}
