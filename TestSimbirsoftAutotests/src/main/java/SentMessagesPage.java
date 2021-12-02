import java.util.Calendar;
import java.util.GregorianCalendar;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class SentMessagesPage extends PageFactory {

  WebDriverWait wait;

  @FindBy(css = ".llc")
  List<WebElement> messages;

  public boolean isMessageSent(WebDriver driver, String theme){
    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".llc")));
    PageFactory.initElements(driver, this);
    Calendar currentDate = new GregorianCalendar();
    currentDate.add(Calendar.MINUTE, -10);
    for (WebElement element :
        messages) {
      WebElement hourMinuteElement = element.findElement(By.cssSelector(".llc__item_date"));
      String[] hourMinutes = hourMinuteElement.getText().split(":");
      Calendar messageDate = new GregorianCalendar();
      try {
        messageDate.set(Calendar.HOUR, Integer.parseInt(hourMinutes[0]));
        messageDate.set(Calendar.MINUTE, Integer.parseInt(hourMinutes[1]));
      } catch (Exception exception) {
        return false;
      }
      WebElement messageString = element.findElement(By.cssSelector(".ll-sj__normal"));
      String messageTheme = messageString.getText();
      long minute = 1000 * 60;
      if (messageDate.after(currentDate) && messageTheme
          .contains(theme)) {
        return true;
      } else if (!messageDate.after(currentDate)) {
        return false;
      }
    }
    return false;
  }
}