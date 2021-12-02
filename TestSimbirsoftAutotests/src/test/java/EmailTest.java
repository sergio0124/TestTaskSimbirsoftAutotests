import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.Properties;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.net.MalformedURLException;

public class EmailTest {

  private String address;
  private String login;
  private String password;
  private String theme;

  EntryEmailPage entryEmailPage;
  MainEmailPage mainEmailPage;
  SentMessagesPage sentMessagesPage;
  WebDriver driver;
  int countOfThemes;

  @BeforeClass
  void setup() throws MalformedURLException {
    entryEmailPage = new EntryEmailPage();
    mainEmailPage = new MainEmailPage();
    sentMessagesPage = new SentMessagesPage();

    FileInputStream fis;
    Properties property = new Properties();
    try {
      fis = new FileInputStream(this.getClass().getResource("/config.properties").getFile());
      property.load(fis);
      address = property.getProperty("address");
      login = property.getProperty("login");
      password = property.getProperty("password");
      theme = property.getProperty("theme");
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }

    String URL = "http://192.168.56.1:4444";
    DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
    desiredCapabilities.setBrowserName("MicrosoftEdge");
    desiredCapabilities.setPlatform(Platform.WIN10);
    driver = new RemoteWebDriver(new URL(URL), desiredCapabilities);
  }

  @Test(priority = 1)
  void entryCountSendEmail() {
    entryEmailPage.entryEmail(driver, login, password, address);
  }

  @Test(priority = 2)
  void countEmail() {
    countOfThemes = mainEmailPage.getLettersFromMyselfCount(driver, theme);
  }

  @Test(priority = 3)
  void sendEmail() {
    mainEmailPage
        .sendEmail(driver, login, theme,
            "It is " + countOfThemes + " with stated theme");
  }

  @Test(priority = 4)
  void checkMessage() throws ParseException {
    mainEmailPage.goToSentPage(driver);
    assert (sentMessagesPage.isMessageSent(driver, theme));
  }

  @AfterClass
  void tearDown() {
    driver.quit();
  }
}
