package ru.vtb.neoflex.autotests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.neoflex.model.MainPage;
import ru.neoflex.model.SendPage;
import ru.neoflex.helpers.Table;

import javax.swing.*;

public class SendTestimonyTest<coldWaterValue> {
    private WebDriver driver;


    @BeforeEach
    public void setupPage() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://localhost:5500/index.html");
        Thread.sleep(1000);
    }

    private By coldWaterValueField = By.xpath("//*[@id=\"table\"]/tbody/tr[1]/td[2]");
    private By tablePath = By.xpath("//*[@id=\"table\"]");

    @Test
    public void FillAndSubmitSendPage() throws InterruptedException {
        //1) Перейти с главной страницы сайта на страницу передачи показаний
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSend();
        Assertions.assertEquals(driver.findElement(By.xpath("/html/body/h1")).getText(), "Передача показаний");
        SendPage sendPage = new SendPage(driver);
        //2) Заполнить на странице передачи показаний все поля необходимыми данными
        sendPage.fillNewTestimony("01.07.2020", "123", "234", "34", "55");
        // 3) Нажать на кнопку для передачи данных формы
        sendPage.clickSendTesimony();
        Assertions.assertEquals(driver.findElement(By.xpath("/html/body/h1")).getText(), "Передача показаний");
        //check is table values embpty or not
        Table testTable = getTable(tablePath);
        isTableValuesFilled(testTable);

    }

    public void isTableValuesFilled(Table testTable) {
        //0,1 - period,1-4,1 -consumed,1-4,2 - costs,5,1 - sum cost
        //check period
        Assertions.assertEquals(false, testTable.getValueFromCell(0, 1).isEmpty());
        //check sumCost
        Assertions.assertEquals(false, testTable.getValueFromCell(5, 1).isEmpty());
        for (int r = 1; r < 5; r++) {
            //check consumed value
            Assertions.assertEquals(false, testTable.getValueFromCell(r, 1).isEmpty());
            //then check cost of consumed
            Assertions.assertEquals(false, testTable.getValueFromCell(r, 2).isEmpty());
        }
    }

    public Table getTable(By tablePath) {
        WebElement tableElement = driver.findElement(tablePath);
        return new Table(tableElement, driver);
    }


    @AfterEach
    public void closePage() {
        driver.quit();
    }
}
