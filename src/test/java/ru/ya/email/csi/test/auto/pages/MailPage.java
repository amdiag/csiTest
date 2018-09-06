package ru.ya.email.csi.test.auto.pages;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

public class MailPage {

    public MailPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    public WebDriver driver;

    @FindBy( xpath = ".//div[@class='mail-User-Name']")
    private WebElement mailUserName;

    @FindBy( xpath = ".//span[text()='Яндекс']")
    private WebElement xpathNameSender;

    private String xpathSender = ".//span[text()='%s']";
    private String xpathSummary = ".//div[text()='%s']";
    private String xpathBody = ".//div[@class='mail-Message-Body-Content']/div/font";
    private String xpathExitButton = ".//a[text()='Выйти из сервисов Яндекса']";

    public String checkMailUser(String userName){
        String userNameText = mailUserName.getText();
        return userNameText;
    }

    public void clickSenderName(){
        xpathNameSender.click();
    }

    public boolean checkData(String sender, String summary, String body) {
        boolean checkSender = false, checkSummary = false, checkBody = false;
        WebElement element = driver.findElement(By.xpath( String.format(xpathSender,sender) ) );

        if (element.getText().equals(sender)){
            checkSender = true;
        }

        element = driver.findElement(By.xpath( String.format(xpathSummary,summary) ) );
        if (element.getText().equals(summary)) {
            checkSummary = true;
        }

        element = driver.findElement(By.xpath( xpathBody ) );
        if( element.getText().contains(body)) {
            checkBody = true;
        }


        return checkBody && checkSender && checkSummary;
    }

    public void clickUnloginButton(){
        mailUserName.click();
        WebElement element = driver.findElement(By.xpath(xpathExitButton));
        element.click();
    }
}