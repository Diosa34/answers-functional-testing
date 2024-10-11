import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnswersPageTestCase {
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
    public void interfaceStructure() {
        assertAll(
                () -> {
                    WebElement tab1 = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/span[1]"));
                    assertEquals("All", tab1.getText());
                },
                () -> {
                    WebElement tag1 = driver.findElement(By.xpath("//*[@id=\"best-answer\"]/div[1]/div[1]"));
                    assertEquals("Best answer", tag1.getText());
                },
                () -> {
                    WebElement tag2 = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[5]/div[1]"));
                    assertEquals("Related answers", tag2.getText());
                },
                () -> {
                    WebElement tag3 = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[7]/div[1]"));
                    assertEquals("Featured study guide", tag3.getText());
                },
                () -> {
                    WebElement tag4 = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[11]/div[1]"));
                    assertEquals("More study guides", tag4.getText());
                },
                () -> {
                    WebElement tag5 = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[12]/div[1]"));
                    assertEquals("Search results", tag5.getText());
                },
                () -> {
                    WebElement tag6 = driver.findElement(By.xpath("//*[@id=\"widget_34\"]/div/div[1]"));
                    assertEquals("Still have questions?", tag6.getText());
                },
                () -> {
                    WebElement tab2 = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/span[2]"));
                    tab2.click();
                    WebElement tag = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[1]"));
                    assertEquals("Search results", tag.getText());
                },
                () -> {
                    WebElement tab3 = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/span[3]"));
                    tab3.click();
                    WebElement tag = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[3]/div[1]"));
                    assertEquals("Study Guides", tag.getText());
                },
                () -> {
                    WebElement subjects = driver.findElement(By.xpath("//*[@id=\"site-container\"]/div[1]/div[1]/div/div/a"));
                    assertEquals("Subjects", subjects.getText());
                },
                () -> {
                    WebElement trendingQuestions = driver.findElement(By.xpath("//*[@id=\"site-container\"]/div[1]/div[3]/div/div[1]/div"));
                    assertEquals("Trending Questions", trendingQuestions.getText());
                },
                () -> {
                    WebElement stillHaveQuestions = driver.findElement(By.xpath("//*[@id=\"widget_36\"]/div/div[1]"));
                    assertEquals("Still have questions?", stillHaveQuestions.getText());
                }

        );
    }

    @Test
    public void reactionBtn() {
        assertAll(
                () -> {
                    WebElement helpfulBtn = driver.findElement(By.xpath("//*[@id=\"best-answer\"]/div[4]/div[1]/div/button[1]"));
                    helpfulBtn.click();
                    assertEquals("py-2 px-3 rounded-md flex items-center justify-center   border-primaryLight border border-solid caption1 bg-secondaryBG border-secondaryText cursor-default border border-solid rounded-md focus:outline-none ", helpfulBtn.getAttribute("class"));
                },
                () -> {
                    WebElement notHelpfulBtn = driver.findElement(By.xpath("//*[@id=\"best-answer\"]/div[4]/div[1]/div/button[2]"));
                    notHelpfulBtn.click();
                    notHelpfulBtn.click();
                    assertEquals("py-2 px-3 rounded-md flex items-center justify-center   border-primaryLight border border-solid caption1 bg-BGColor1 border-tertiaryDark cursor-default border border-solid rounded-md focus:outline-none ", notHelpfulBtn.getAttribute("class"));
                }
        );
    }

    @Test
    public void studyNow() {
        WebElement studyNow = driver.findElement(By.xpath("//*[@id=\"best-answer\"]/div[4]/div[2]/button[1]"));
        studyNow.click();
        assertAll(
            () -> assertEquals(driver.findElement(By.xpath("//*[@id=\"best-answer\"]/div[3]/a")).getText(), driver.findElement(By.xpath("//*[@id=\"flash-card-modal\"]/div/div/div[2]/div[2]/div[3]/div/div[1]/div/div/div[1]/div/div[2]")).getText() + "?")
            ,() -> {
                WebElement card = driver.findElement(By.xpath("//*[@id=\"flash-card-modal\"]/div/div/div[2]/div[2]/div[3]/div/div[1]/div/div/div[1]/div"));
                card.click();
                assertEquals(driver.findElement(By.xpath("//*[@id=\"best-answer\"]/div[2]/p")).getText(), driver.findElement(By.xpath("//*[@id=\"flash-card-modal\"]/div/div/div[2]/div[2]/div[3]/div/div[1]/div/div/div[2]/div/div[2]/p")).getText());
            }
        );
    }


    @Test
    public void copyBtn() throws IOException, UnsupportedFlavorException {
        WebElement copy = driver.findElement(By.xpath("//*[@id=\"options-menu\"]/div[1]/a[2]/div"));
        copy.click();
        String clipboardText = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);

        WebElement answer = driver.findElement(By.xpath("//*[@id=\"best-answer\"]/div[2]/p"));

        assertEquals(answer.getText(), clipboardText.replace("\n", " "));
    }

    @Test
    public void pointsBtn(){
        WebElement pointsBtn = driver.findElement(By.xpath("//*[@id=\"options-menu\"]/div[2]/button"));
        pointsBtn.click();

        WebElement menu = driver.findElement(By.xpath("//*[@id=\"options-menu\"]/div[2]/div/div"));

        assertEquals("menu", menu.getAttribute("role"));
    }

    @Test
    public void answerLink(){
        WebElement answer = driver.findElement(By.xpath("//*[@id=\"best-answer\"]/div[3]/a"));
        String answerText = answer.getText();
        answer.click();
        assertEquals(answerText, driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[2]/div[1]/h1/a")).getText());
    }

    @Test
    public void viewPage() {
        WebElement answer = driver.findElement(By.xpath("//*[@id=\"best-answer\"]/div[3]/a"));
        String answerText = answer.getText();

        WebElement viewPageBtn = driver.findElement(By.xpath("//*[@id=\"best-answer\"]/div[4]/div[2]/button[2]"));
        viewPageBtn.click();
        assertEquals(answerText, driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[2]/div[1]/h1/a")).getText());
    }
}
