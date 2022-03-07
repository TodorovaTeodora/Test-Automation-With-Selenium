package windowsandframes;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;
import java.util.Set;

public class WindowsTest {

    WebDriver driver;
    WebElement element;


    @Before
    public void setupTest () {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver2.exe");
        driver = new ChromeDriver();
    }

    @After
    public void finishTests() {
        driver.quit();
    }

    @Test
    public void windowsFrames() {
        driver.navigate().to("http://the-internet.herokuapp.com/windows");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.open('http://the-internet.herokuapp.com/windows/new')");

        String originalWindow = driver.getWindowHandle();
        Set handles = driver.getWindowHandles();
        handles.remove(originalWindow);

        String nextWindow = String.valueOf(handles.iterator().next());

        driver.switchTo().window(nextWindow);
        assertEquals("New Window",driver.getTitle());

        driver.close();
        driver.switchTo().window(originalWindow);
        assertEquals("The Internet", driver.getTitle());

    }
}
