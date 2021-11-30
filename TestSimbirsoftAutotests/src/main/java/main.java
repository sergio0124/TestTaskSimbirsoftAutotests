import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class main {

    private final static String authorization_login = "wengarelo@mail.ru";
    private final static String authorization_password = "Phy84apXx";
    private final static String letter_theme = "Simbirsoft Тестовое задание Резников";

    public static void main(String[] args) {
        System.setProperty("webdriver.edge.driver", "D:\\Selenium\\msedgedriver.exe");

        WebDriver driver = new EdgeDriver();

        try{

            EntryMailPage entryMailPage = new EntryMailPage();
            entryMailPage.EntryGmail(driver, authorization_login, authorization_password);

            CountEmailNumber countEmailNumber = new CountEmailNumber();
            int count = countEmailNumber.GetLettersFromMyselfCount(driver,letter_theme);

            SendEmailLetter sendEmailLetter = new SendEmailLetter();
            sendEmailLetter.SendEmail(driver,authorization_login,letter_theme,"It is "+count+" with stated theme");
        }
        catch (Exception ex){

        }
        finally {
            driver.quit();
        }

    }

}
