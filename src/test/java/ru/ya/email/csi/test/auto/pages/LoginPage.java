package ru.ya.email.csi.test.auto.pages;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

public class LoginPage {

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    public WebDriver driver;

    @FindBy( linkText = "Войти в почту")
    private WebElement aEntryPost;

    @FindBy(xpath = ".//input[@name='login']")
    private WebElement loginField;

    @FindBy(xpath = ".//input[@name='passwd']")
    private WebElement passwordField;

    @FindBy(xpath = ".//span[text()='Войти']/..")
    private WebElement loginButton;

    public void clickEnterPost(){
        aEntryPost.click();
    }

    public void inputLogin(String login) {
        loginField.sendKeys(login);
    }

    public void inputPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public void checkEnterPost() throws Exception {
        try{
            aEntryPost.getText();
        }
        catch (Exception e) {
            throw new Exception("Error. " + e.getMessage() );
        }
    }
}