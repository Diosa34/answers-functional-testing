import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrendingQuestionsTestCase {
    WebDriver driver;

    @BeforeEach
    public void setup() {
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

        // Задаём вопрос и нас перенаправляет на страничку ответа
        driver.get("https://www.answers.com/");
        WebElement searchBar = driver.findElement(By.xpath("//*[@id=\"search-input\"]"));
        searchBar.sendKeys("What color is the sun?");
        WebElement searchBtn = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/main/section[1]/div[2]/button"));
        searchBtn.click();
    }

    @AfterEach
    public void teardown() {
        driver.quit();
    }

    @Test
    public void trendingQuestion() {
        WebElement linkTo = driver.findElement(By.xpath("//*[@id=\"site-container\"]/div[1]/div[3]/div/div[1]/a[1]"));
        String linkToText = linkTo.getText();
        linkTo.click();

        WebElement title = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[2]/div[1]/h1"));
        assertEquals(linkToText, title.getText());
    }
}
