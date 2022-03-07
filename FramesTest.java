package windowsandframes;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

public class FramesTest {

    WebDriver driver;
    WebElement element;

    @Before
    public void setupTest () {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver2.exe");
        driver = new ChromeDriver();
    }

    @After
    public void finishTest() {
        driver.quit();
    }

    @Test
    public void framesTesting () {
        //Navigate to URL:
        driver.navigate().to("http://the-internet.herokuapp.com/nested_frames");

        //frame-top is the top level, default view of the frames
        WebElement defaultFrame = driver.findElement(By.name("frame-top"));
        //we can switch frames by index, 0 is the first
        driver.switchTo().frame(1);
        //assert that we have switched to the bottom frame:
        assertEquals("BOTTOM", driver.findElement(By.tagName("body")).getText());

        //switchTo().parentFrame() moves focus to the parent frame:
        driver.switchTo().parentFrame();
        driver.switchTo().frame("frame-top");
        driver.switchTo().frame("frame-left");
        //assert that we have switched to the left frame:
        assertEquals("LEFT", driver.findElement(By.tagName("body")).getText());

        //switchTo().defaultContent() will move to the top most/default frame:
        driver.switchTo().defaultContent();
        //Get the element's height with Name frame-top. If you weren't focused on this
        //element, findElement() would throw a NoSuchException:
        assertTrue(driver.findElement(By.name("frame-top")).getSize().height > 0);


    }


}
