import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EntryMailPage {

    private final String authorization_address = "https://mail.ru/";

    public void EntryGmail(WebDriver driver, String login, String password) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get(authorization_address);

        WebElement textfield_login = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='login']")
                ));
        textfield_login.sendKeys(login);
        textfield_login.sendKeys(Keys.RETURN);

        WebElement textfield_password = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='password']")
                ));

        textfield_password.sendKeys(password);
        textfield_password.sendKeys(Keys.RETURN);
    }

}
