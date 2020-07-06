package ru.neoflex.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SendPage {
    private WebDriver driver;

    public SendPage(WebDriver driver) {
        this.driver = driver;
    }

    private By sendTestimony = By.xpath("//*[@id=\"button\"]");

    public SendPage clickSendTesimony() {
        driver.findElement(sendTestimony).click();
        return new SendPage(driver);
    }

    private By date = By.xpath("//*[@id=\"date\"]");
    private By coldData = By.xpath("//*[@id=\"coldData\"]");
    private By hotData = By.xpath("//*[@id=\"hotData\"]");
    private By gasData = By.xpath("//*[@id=\"gasData\"]");
    private By elecData = By.xpath("//*[@id=\"elecData\"]");


    public SendPage fillNewTestimony(String testDate, String coldValue, String hotValue, String gasValue, String elecValue) {
        fillField(date, testDate);
        fillField(coldData, coldValue);
        fillField(hotData, hotValue);
        fillField(gasData, gasValue);
        fillField(elecData, elecValue);
        return new SendPage(driver);
    }


    public void fillField(By field, String value) {
        driver.findElement(field).sendKeys(value);
    }


}
