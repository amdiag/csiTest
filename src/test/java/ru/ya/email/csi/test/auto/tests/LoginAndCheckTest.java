package ru.ya.email.csi.test.auto.tests;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.ya.email.csi.test.auto.pages.*;

import java.util.concurrent.TimeUnit;

public class LoginAndCheckTest {
    public static WebDriver driver;
    public static LoginPage loginPage;
    public static MailPage mailPage;

    private static String login = "csi.test.auto";
    private static String pass = "12345678(";
    private static String sender = "hello@yandex.ru";
    private static String summary = "Соберите всю почту в этот ящик";
    private static String body = "Если у Вас уже есть почтовые ящики, куда приходят письма, Вам будет удобнее хранить и читать все письма в одном ящике.";

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "./src/resources/chromedriver.exe");
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        mailPage = new MailPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://ya.ru");
    }

    @Test
    public void loginAndCheckData() throws Exception {
        try {
            loginPage.clickEnterPost();
            loginPage.clickLoginButton();
            loginPage.inputLogin(login);
            loginPage.inputPassword(pass);
            loginPage.clickLoginButton();

            String userNameText = mailPage.checkMailUser(login);
            Assert.assertEquals(login, userNameText);
            mailPage.clickSenderName();

            boolean value = mailPage.checkData(sender, summary, body);
            if (!value) {
                //LOG()
                throw new Exception("Error. Не совпало значение отправитель, тема, тело письма\n");
            }

            mailPage.clickUnloginButton();
            loginPage.checkEnterPost();
        }
        catch (Exception e){
            throw new Exception("Не найдено\n" + e.getMessage() );
        }
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
