package tests;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class W3school {

    WebDriver driver = new ChromeDriver();

    @Test
    public void searchResultTest() {

        driver.manage().window().maximize();
        driver.navigate().to("https://www.w3schools.com/java/");
        WebElement element = driver.findElement(By.xpath("//span[contains(text(),'Tutorial')]"));

        Actions make = new Actions(driver);

        make
                .doubleClick(element)
                .keyDown(Keys.LEFT_CONTROL)
                .sendKeys("c")
                .keyUp(Keys.LEFT_CONTROL)
                .build()
                .perform();

        driver.navigate().to("https://google.com");
        driver.findElement(By.xpath("//input[@name = 'q']")).click();

        make
                .keyDown(Keys.LEFT_CONTROL)
                .sendKeys("v")
                .keyUp(Keys.LEFT_CONTROL)
                .sendKeys(Keys.ENTER)
                .build()
                .perform();

        int blocks_counter = driver.findElements(By.xpath("//div[@class = 'g']")).size();
        int matchedWords_counter = driver.findElements(By.xpath("//div[@class = 'g' and contains(.,'tutorial')]")).size();
        Assert.assertEquals("Blocks vs Words", blocks_counter, matchedWords_counter);

        //driver.close();
        //driver.quit();
    }
}
