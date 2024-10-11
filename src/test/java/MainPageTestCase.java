import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainPageTestCase {
    WebDriver driver;

    @BeforeEach
    public void setup() throws InterruptedException {
        driver = new ChromeDriver();
        driver.get("https://www.answers.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        // Выполняем вход
        WebElement logIn = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div/div[4]/div[3]/span/button"));
        logIn.click();
        WebElement logInWIthEmail = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[4]/div/div[2]/div/div[2]/div[2]/div/button[4]"));
        logInWIthEmail.click();
        WebElement login = driver.findElement(By.xpath("//*[@id=\"email-input\"]"));
        login.sendKeys("335086@niuitmo.ru");
        WebElement password = driver.findElement(By.xpath("//*[@id=\"outlined-adornment-password\"]"));
        password.sendKeys("password");
        WebElement submit = driver.findElement(By.xpath("//*[@id=\"loginUser\"]/button"));
        submit.click();
        Thread.sleep(2000);
    }

    @AfterEach
    public void teardown() {
        driver.quit();
    }

    @Test
    public void browseSubjects() {
        WebElement home = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/main/div[2]/a[8]"));
        String name = home.getText();
        home.click();

        WebElement message = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/h1"));
        assertEquals(name, message.getText());
    }

    @Test
    public void browseCategory() {
        WebElement category = driver.findElement(By.xpath("//*[@id=\"category-chips-0\"]"));
        String name = category.getText();
        category.click();

        WebElement message = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/h1"));
        assertEquals(name, message.getText());
    }

    @Test
    public void aboutUs() throws InterruptedException {
        WebElement body = driver.findElement(By.cssSelector("body"));
        body.click();
        body.sendKeys(Keys.PAGE_DOWN);

        WebElement contact = driver.findElement(By.xpath("//*[@id=\"answ-footer\"]/div/div[1]/div[4]/a[2]"));
        contact.click();

        Thread.sleep(2000);

        WebElement message = driver.findElement(By.xpath("//*[@id=\"page\"]/h1"));
        assertEquals("Contact Us", message.getText());
    }

    @Test
    public void youtube() throws InterruptedException {
        WebElement body = driver.findElement(By.cssSelector("body"));
        body.click();
        body.sendKeys(Keys.PAGE_DOWN);

        WebElement youtube = driver.findElement(By.xpath("//*[@id=\"answ-footer\"]/div/div[2]/div/a[4]"));
        youtube.click();

        Thread.sleep(2000);

        String expectedUrl = "https://www.youtube.com/c/AnswersVideo";
        String actualUrl = driver.getCurrentUrl();

        assertEquals(expectedUrl, actualUrl);
    }
}
