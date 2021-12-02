import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class MainEmailPage extends PageFactory {

  WebDriverWait wait;

  @FindBy(css = ".llc.llc_has-indent")
  WebElement messageInPackage;

  @FindBy(className = "metathread")
  WebElement messageToMyselfGroup;

  @FindBy(xpath = "//span[@class='ll-sj__normal']")
  List<WebElement> themeElements;

  @FindBy(css = ".compose-button")
  WebElement writeMessageButton;

  @FindBy(css = "div[role='textbox']")
  WebElement textMessageBox;

  @FindBy(css = ".size_s_compressed--2c-eV")
  List<WebElement> headerMessageElements;

  @FindBy(xpath = "//span[@title='Отправить']")
  WebElement sendMessageButton;

  @FindBy(css = ".ico_16-close")
  WebElement closeIcon;

  @FindBy(css = "[title='Отправленные']")
  WebElement buttonSentMessages;

  public int getLettersFromMyselfCount(WebDriver driver, String theme) {
    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    PageFactory.initElements(driver, this);
    wait.until(ExpectedConditions.elementToBeClickable(messageToMyselfGroup)).click();
    wait.until(ExpectedConditions.elementToBeClickable(messageInPackage));
    int count = 0;
    for (WebElement elem :
        themeElements) {
      if (elem.getText().contains(theme) || theme.contains(elem.getText())) {
        count++;
      }
    }
    return count;
  }

  public void sendEmail(WebDriver driver, String to, String theme, String text) {
    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    PageFactory.initElements(driver, this);

    writeMessageButton.click();
    wait.until(ExpectedConditions.elementToBeClickable(textMessageBox)).sendKeys(text);
    headerMessageElements.get(0).sendKeys(to);
    headerMessageElements.get(1).sendKeys(theme);
    sendMessageButton.click();
    wait.until(ExpectedConditions.elementToBeClickable(closeIcon)).click();
  }

  public void goToSentPage(WebDriver driver) {
    wait = new WebDriverWait(driver,Duration.ofSeconds(10));
    PageFactory.initElements(driver,this);
    buttonSentMessages.click();
  }
}
