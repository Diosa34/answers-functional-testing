import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class SubjectsTestCase {
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
    public void subjectsLink() {
        WebElement subjects = driver.findElement(By.xpath("//*[@id=\"site-container\"]/div[1]/div[1]/div/div/a"));
        subjects.click();

        assertAll(
                () -> {
                    WebElement title1 = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/h1"));
                    String subjectsTitle = title1.getText();
                    assertEquals("\uD83D\uDCAF Subjects", subjectsTitle);
                }
                ,() -> {
                    WebElement firstSubjectLink = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[1]/a"));
                    WebElement firstSubject = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[1]/a/div[1]"));
                    String firstSubjectText = firstSubject.getText();
                    firstSubjectLink.click();
                    WebElement firstSubjectPage = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/h1"));
                    assertEquals(firstSubjectText, firstSubjectPage.getText());
                }
                ,() -> {
                    WebElement secondSubjectLink = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[1]/a"));
                    WebElement secondSubject = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[1]/a/div[1]"));
                    String secondSubjectText = secondSubject.getText();
                    secondSubjectLink.click();
                    WebElement secondSubjectPage = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/h1"));
                    assertEquals(secondSubjectText, secondSubjectPage.getText());
                }
                ,() -> {
                    WebElement qa = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[1]/a[1]"));
                    assertEquals("Q&A", qa.getText());
                }
                ,() -> {
                    WebElement guides = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[1]/a[2]"));
                    assertEquals("Guides", guides.getText());
                }
                ,() -> {
                    WebElement backSubjectsLink = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[1]/a[1]"));
                    backSubjectsLink.click();
                    WebElement cancelST = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/h1"));
                    assertEquals("\uD83D\uDCAF Subjects", cancelST.getText());
                }
        );
    }
}
