import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BestAnswerTestCase {
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

        // Задаём вопрос и нас перенаправляет на страничку со всеми ответами
        driver.get("https://www.answers.com/");
        WebElement searchBar = driver.findElement(By.xpath("//*[@id=\"search-input\"]"));
        searchBar.sendKeys("What color is the sun?");
        WebElement searchBtn = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/main/section[1]/div[2]/button"));
        searchBtn.click();

        // Кликаем по лучшему ответу
        WebElement answer = driver.findElement(By.xpath("//*[@id=\"best-answer\"]/div[3]/a"));
        answer.getText();

        WebElement viewPageBtn = driver.findElement(By.xpath("//*[@id=\"best-answer\"]/div[4]/div[2]/button[2]"));
        viewPageBtn.click();
    }

    @AfterEach
    public void teardown() {
        driver.quit();
    }

    @Test
    public void comment() throws InterruptedException {
        WebElement hideComment = driver.findElement(By.xpath("//*[@id=\"top-answer\"]/div[2]/button"));
        int commentCount = Integer.parseInt(hideComment.getText().substring(15, 16));

        WebElement commentBar = driver.findElement(By.xpath("//*[@id=\"comment-input-box\"]"));
        commentBar.sendKeys("Thanks!");

        WebElement commentBtn = driver.findElement(By.xpath("//*[@id=\"top-answer\"]/div[2]/div[4]/div[2]/img"));
        commentBtn.click();

        Thread.sleep(5000);

        WebElement updateHideComment = driver.findElement(By.xpath("//*[@id=\"top-answer\"]/div[2]/button"));
        int updateCommentCount = Integer.parseInt(updateHideComment.getText().substring(15, 16));

        assertEquals(commentCount + 1, updateCommentCount);
    }

    @Test
    public void myAnswer(){
        WebElement answerField = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[4]/div[1]"));
        answerField.click();

        WebElement closeBtn = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[4]/div/div[2]/div/div[1]/button[1]"));
        closeBtn.click();
    }
}
