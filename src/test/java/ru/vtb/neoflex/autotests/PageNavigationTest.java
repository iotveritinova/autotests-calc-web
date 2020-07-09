package ru.vtb.neoflex.autotests;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.neoflex.model.MainPage;

public class PageNavigationTest {
    private WebDriver driver;

    @BeforeEach
    public void setupPage() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://localhost:5500/index.html");
        Thread.sleep(1000);
    }

    @Step
    @DisplayName("Navigation test")
    @Description("Navigation test description")
    @Test
    public void FromMainToSendAndBack() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSend();
        Assertions.assertEquals(driver.findElement(By.xpath("/html/body/h1")).getText(), "Передача показаний");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"back_button\"]")).click();
        Assertions.assertEquals(driver.findElement(By.xpath("/html/body/h1")).getText(), "Neo ЖКХ");
    }

    @Step
    @DisplayName("Navigation test2")
    @Description("Navigation test description2")
    @Test
    public void FromMainToHistoryAndBack() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickHistory();
        Assertions.assertEquals(driver.findElement(By.xpath("/html/body/h1")).getText(), "История показаний");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"back_button\"]")).click();
        Assertions.assertEquals(driver.findElement(By.xpath("/html/body/h1")).getText(), "Neo ЖКХ");
    }

    @Step
    @DisplayName("Navigation test3")
    @Description("Navigation test description3")
    @Test
    public void FromMainToPriceAndBack() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPrice();
        Assertions.assertEquals(driver.findElement(By.xpath("/html/body/h1")).getText(), "Справочник стоимости услуг");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"back_button\"]")).click();
        Assertions.assertEquals(driver.findElement(By.xpath("/html/body/h1")).getText(), "Neo ЖКХ");
    }

    @AfterEach
    public void closePage() {
        driver.quit();
    }
    /*Для того, чтобы после выполнения теста сформировать отчёт и открыть его в браузере, необходимо выполнить следующие действия:

1) Открыть Terminal в IntelliJ IDEA

2) Ввести команду: allure generate --clean

3) Ввести команду: allure serve*/
}
