import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class JunitProject {
    WebDriver driver;

    @BeforeAll
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    @DisplayName("Check if the Form Automated Fill up")
    @Test
    public void automateWebForm() throws InterruptedException {
        driver.get("https://www.digitalunite.com/practice-webform-learners");
        List<WebElement> formFieldList = driver.findElements(By.className("form-control"));

        //AdBannerClose
        driver.findElement(By.className("banner-close-button")).click();
        //Name
        formFieldList.get(0).sendKeys("Anwar Hossen");
        //Phone Number
        formFieldList.get(1).sendKeys("01758741239");
        scroll(0, 500);
        //Age
        driver.findElement(By.xpath("//label[normalize-space()='20-30']")).click();
        scroll(0, 500);

        //(DOB)Date of Birth
        formFieldList.get(2).sendKeys(Keys.CONTROL + "a");
        formFieldList.get(2).sendKeys(Keys.BACK_SPACE);
        formFieldList.get(2).sendKeys("10/16/1997", Keys.ENTER);

        //Email
        formFieldList.get(3).sendKeys("anwarhossen123@gmail.com");
        scroll(0, 500);

        //About Yourself
        formFieldList.get(4).sendKeys("My name is Anwar Hossen, I am 25 years old and I recently graduated from University X with a B.Sc. in Computer Science and Engineering. While there, I learned a lot of theory in subjects. I have worked hard in my education and now I am ready to apply my knowledge into practice. Now, I am looking to leverage everything I have learned in university and get some hands-on work experience.");
        scroll(0, 500);
        scroll(0, 300);

        //Upload File
        driver.findElement(By.id("edit-uploadocument-upload")).sendKeys(System.getProperty("user.dir") + "/src/test/resources/Bug Report.pdf");

        //Click on Check Box
        scroll(0, 1000);
        Thread.sleep(2000);
        driver.findElement(By.id("edit-age")).click();

        //Submit
        Thread.sleep(1000);
        driver.findElement(By.id("edit-submit")).click();

        //Assert Success Message
        String actualMessage = driver.findElement(By.tagName("h1")).getText();
        String expectedMessage = "Thank you for your submission!";

        Assertions.assertEquals(actualMessage, expectedMessage);
    }

    public void scroll(int x, int y) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(" + x + "," + y + ")");
    }

    @AfterAll
    public void browserQuit() {
        driver.quit();

    }
}
