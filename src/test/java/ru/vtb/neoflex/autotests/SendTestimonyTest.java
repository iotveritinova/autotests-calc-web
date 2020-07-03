package ru.vtb.neoflex.autotests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.neoflex.model.MainPage;
import ru.neoflex.model.SendPage;

public class SendTestimonyTest<coldWaterValue> {
    private ChromeDriver driver;


    @BeforeEach
    public void setupPage() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://localhost:5500/index.html");
        Thread.sleep(1000);
    }

    private By coldWaterValueField = By.xpath("//*[@id=\"table\"]/tbody/tr[3]/td[2]");

    @Test
    public void FillAndSubmitSendPage() throws InterruptedException {
        //1) Перейти с главной страницы сайта на страницу передачи показаний
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSend();
        Assertions.assertEquals(driver.findElement(By.xpath("/html/body/h1")).getText(), "Передача показаний");
        //2) Заполнить на странице передачи показаний все поля необходимыми данными
        fillNewTestimony("01.07.2020", "123.3", "234.8", "3453", "55");
        // 3) Нажать на кнопку для передачи данных формы
        clickSendTesimony();
        // there is a problim with value check
        String coldwater = driver.findElement(coldWaterValueField).getText();
        System.out.println(coldwater + " " + coldwater.isEmpty());
        Assertions.assertEquals(true, coldwater.isEmpty());
    }

    private By date = By.xpath("//*[@id=\"date\"]");
    private By coldData = By.xpath("//*[@id=\"coldData\"]");
    private By hotData = By.xpath("//*[@id=\"hotData\"]");
    private By gasData = By.xpath("//*[@id=\"gasData\"]");
    private By elecData = By.xpath("//*[@id=\"elecData\"]");
    private By sendTestimony = By.xpath("//*[@id=\"button\"]");


    public void fillNewTestimony(String testDate, String coldValue, String hotValue, String gasValue, String elecValue) {
        fillField(driver, date, testDate);
        fillField(driver, coldData, coldValue);
        fillField(driver, hotData, hotValue);
        fillField(driver, gasData, gasValue);
        fillField(driver, elecData, elecValue);
    }

    public SendPage clickSendTesimony() {
        driver.findElement(sendTestimony).click();
        return new SendPage(driver);
    }

    public static void fillField(ChromeDriver driver, By date, String s) {
        driver.findElement(date).sendKeys(s);
    }

    @AfterEach
    public void closePage() {
        driver.quit();
    }
}
