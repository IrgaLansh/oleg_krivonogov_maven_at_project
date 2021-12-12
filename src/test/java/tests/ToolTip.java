package tests;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ToolTip {

    WebDriver driver = new ChromeDriver();

    @Test
    public void toolTipTest() {

        driver.manage().window().maximize();
        driver.navigate().to("https://www.booking.com/");

        WebElement element = driver.findElement(By.xpath("//button[@data-modal-aria-label  = 'Выберите валюту']"));

        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();

        Assert.assertEquals("Wrong Currency Tooltip text ", "Выберите валюту", driver
                .findElement(By.xpath("//div[@class= 'bui-tooltip__content']")).getText());


        WebElement element2 = driver.findElement(By.xpath("//button[@data-modal-aria-label  = 'Выберите язык']"));

        actions.moveToElement(element2);
        actions.perform();

        Assert.assertEquals("Wrong Language Tooltip text ", "Выберите язык", driver
                .findElement(By.xpath("//div[@class= 'bui-tooltip__content']")).getText());

        //driver.close();
        //driver.quit();
    }
}
