import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SendEmailLetter {

    public void SendEmail(WebDriver driver, String to, String theme, String text) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.findElement(By.cssSelector(
                ".compose-button.compose-button_white.compose-button_short.compose-button_compact.js-shortcut"
        )).click();

        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("div[role='textbox']"))).sendKeys(text);


        List<WebElement> elements = driver.findElements(By.cssSelector((".container--H9L5q.size_s_compressed--2c-eV")));
        elements.get(0).sendKeys(to);
        elements.get(1).sendKeys(theme);


        driver.findElement(By.xpath("//span[@title='Отправить']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ico.ico_16-close.ico_size_s"))).click();
    }

}
