import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CountEmailNumber {

    public int GetLettersFromMyselfCount(WebDriver driver, String theme) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(
                ExpectedConditions.elementToBeClickable(By.className("metathread")
                )).click();

        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(".llc.js-tooltip-direction_letter-bottom.js-letter-list-item.llc_pony-mode.llc_has-indent")));

        List<WebElement> theme_elements = driver.findElements(
                By.xpath("//span[@class='ll-sj__normal']"));

        //Не понимаю, как собрать по содержимому, пробовал
        //By.xpath("//span[@class='ll-sj__normal' contains(.," + theme + ")]")
        //Но это не работает

        int count = 0;
        for (WebElement elem:
                theme_elements) {
            if(elem.getText().contains(theme) || theme.contains(elem.getText())){
                count++;
            }
        }

        return count;

    }

}
