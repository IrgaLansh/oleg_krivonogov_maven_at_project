package tests;

import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class DemoQA {

    WebDriver driver = new ChromeDriver();

    @Test
    public void selectValueTest() {

        driver.manage().window().maximize();
        driver.navigate().to("https://demoqa.com/select-menu");
        driver.findElement(By.xpath("//div[@id = 'withOptGroup']")).click();
        driver.findElement(By.xpath("//div[contains(text(),'Group 2, option 2')]")).click();

        driver.findElement(By.xpath("//div[@id = 'selectOne']")).click();
        driver.findElement(By.xpath("//div[contains(text(),'Ms.')]")).click();


        WebElement element = driver.findElement(By.xpath("//select[@id = 'oldSelectMenu']"));
        Select select = new Select(element);
        select.selectByVisibleText("Black");
//        select.selectByValue("2");


//        driver.findElement(By.xpath("//div[contains(text(),'Select...')]")).click();
//        driver.findElement(By.xpath("//div[contains(text(),'Black')]")).click();
        //driver.findElement(By.xpath("//*[@id=\"selectMenuContainer\"]/div[7]/div/div/div/div[1]/div[1]")).click();



        element = driver.findElement(By.xpath("//select[@id = 'cars']"));
        select = new Select(element);
        select.selectByValue("Opel");

        driver.close();
        driver.quit();
    }
}