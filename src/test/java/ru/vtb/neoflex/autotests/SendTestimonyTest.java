package ru.vtb.neoflex.autotests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.neoflex.model.MainPage;
import ru.neoflex.model.SendPage;

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
        // there is a problim with value check
        String coldwater = driver.findElement(coldWaterValueField).getText();
        Assertions.assertEquals(true, coldwater.isEmpty());
        Assertions.assertEquals(driver.findElement(By.xpath("/html/body/h1")).getText(), "Передача показаний");
    }


    @AfterEach
    public void closePage() {
        driver.quit();
    }
}
