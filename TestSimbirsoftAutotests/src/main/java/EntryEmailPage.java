import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class EntryEmailPage extends PageFactory {
  WebDriverWait wait;

  @FindBy(xpath = "//input[@name='login']")
  WebElement textFieldLogin;

  @FindBy(xpath = "//input[@name='password']")
  WebElement textFieldPassword;

  public void entryEmail(WebDriver driver, String login, String password, String address) {
    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    PageFactory.initElements(driver, this);
    driver.get(address);

    wait.until(ExpectedConditions.visibilityOf(textFieldLogin));
    textFieldLogin.sendKeys(login);
    textFieldLogin.sendKeys(Keys.RETURN);

    wait.until(ExpectedConditions.visibilityOf(textFieldPassword));
    textFieldPassword.sendKeys(password);
    textFieldPassword.sendKeys(Keys.RETURN);
  }
}
