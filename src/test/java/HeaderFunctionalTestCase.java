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

public class HeaderFunctionalTestCase {
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

        Thread.sleep(5000);
    }

    @AfterEach
    public void teardown() {
        driver.quit();
    }

    @Test
    public void search() {
        WebElement icon = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div/div[4]/span"));
        icon.click();

        WebElement searchBar = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[4]/aside/div/div[2]/div[1]/form/input"));
        searchBar.sendKeys("Where does the rainbow end?");

        WebElement search = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[4]/aside/div/div[2]/div[1]/form/img"));
        search.click();

        WebElement tag = driver.findElement(By.xpath("//*[@id=\"best-answer\"]/div[1]/div[1]"));
        assertEquals("Best answer", tag.getText());
    }

    @Test
    public void webSearch() {
        WebElement icon = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div/div[4]/span"));
        icon.click();

        WebElement webTab = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[4]/aside/div/div[2]/div[2]/div[2]/button[2]"));
        webTab.click();

        WebElement searchBar = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[4]/aside/div/div[2]/div[1]/form/input"));
        searchBar.sendKeys("Where does the rainbow end");

        WebElement search = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[4]/aside/div/div[2]/div[1]/form/img"));
        search.click();

        String expectedUrl = "https://search.answers.com/serp?q=Where+does+the+rainbow+end&segment=answers0000";
        String actualUrl = driver.getCurrentUrl();

        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void notifications() {
        WebElement ring = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div/div[4]/a"));
        ring.click();

        WebElement message = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/a/p/strong[2]"));

        assertEquals("\"What property do the bilimbi plant contr...?\"", message.getText());
    }

    @Test
    public void createQuestion() throws InterruptedException {
        WebElement create = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div/div[4]/div[1]/div/button/button"));
        create.click();

        WebElement question = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div/div[4]/div[1]/div/div/div/span[1]/button"));
        question.click();

        WebElement textArea = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[4]/div/div[2]/div/div[2]/div[1]/textarea"));
        textArea.sendKeys("Which are u");

        Thread.sleep(2000);

        WebElement advanced = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[4]/div/div[2]/div/div[2]/div[2]/button"));
        advanced.click();

        WebElement contextArea = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[4]/div/div[2]/div/div[2]/div[2]/div/div[2]/textarea"));
        contextArea.sendKeys("In case.");

        WebElement tag = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[4]/div/div[2]/div/div[2]/div[2]/div/div[4]/div[1]/input"));
        tag.sendKeys("space");

        WebElement submit = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[4]/div/div[2]/div/div[1]/button[2]"));
        submit.click();

        Thread.sleep(2000);

        WebElement questionPage = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[2]/div[1]/h1/a"));

        assertEquals("Which are u", questionPage.getText());
    }

    @Test
    public void createGuide() throws InterruptedException {
        WebElement create = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div/div[4]/div[1]/div/button/button"));
        create.click();

        WebElement guide = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div/div[4]/div[1]/div/div/div/span[2]/button"));
        guide.click();

        WebElement title = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[3]/textarea"));
        title.sendKeys("Discover the beginning and the end of the rainbow");

        WebElement description = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[5]/textarea"));
        description.sendKeys("All for the giraffe and M&M's.");

        WebElement tag = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[7]/div[1]/input"));
        tag.sendKeys("rainbow");

        WebElement term = driver.findElement(By.xpath("//*[@id=\"editGuides\"]/div/div[1]/div[1]"));
        term.sendKeys("Where is the beginning of the rainbow?");

        WebElement definition = driver.findElement(By.xpath("//*[@id=\"editGuides\"]/div/div[2]/div[1]"));
        definition.sendKeys("In the east, under a palm tree.");

        WebElement createBtn = driver.findElement(By.xpath("//*[@id=\"createButton\"]"));
        createBtn.click();

        Thread.sleep(2000);

        assertAll(
                () -> {
                    WebElement resultTitle = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[2]/h1"));
                    assertEquals("Discover the beginning and the end of the rainbow", resultTitle.getText());
                }
                ,() -> {
                    WebElement edit = driver.findElement(By.xpath("//*[@id=\"options-menu\"]/div[1]"));
                    edit.click();

                    WebElement editField = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[3]/div[2]/div/textarea"));
                    editField.sendKeys(" for yourself");

                    WebElement save = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[5]/div[4]/button[2]"));
                    save.click();

                    Thread.sleep(2000);

                    WebElement resultTitle = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[2]/h1"));
                    assertEquals("Discover the beginning and the end of the rainbow for yourself", resultTitle.getText());
                }

        );
    }

    @Test
    public void money() {
        WebElement money = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div/div[4]/div[2]/span"));
        money.click();

        WebElement earn = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[4]/div/div/button"));
        earn.click();

        assertAll(
                () -> {
                    WebElement title = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[1]/div/div/h1"));
                    assertEquals("Answerable", title.getText());
                }
                ,() -> {
                    WebElement answBtn = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[2]/div/div[4]/div[3]/div/button"));
                    answBtn.click();

                    WebElement writeArea = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[4]/div/div[2]/div/div[2]/div/div[1]/div[2]/div"));
                    writeArea.sendKeys("Yes");

                    WebElement submit = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[4]/div/div[2]/div/div[1]/button[2]"));
                    submit.click();

                    Thread.sleep(2000);

                    WebElement answer = driver.findElement(By.xpath("//*[@id=\"top-answer\"]/div[2]/div[1]/div/p"));
                    assertEquals("Yes", answer.getText());
                }
        );
    }

    @Test
    public void checkPerson() {
        WebElement money = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div/div[4]/div[2]/span"));
        money.click();

        WebElement person = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[5]/div[1]/a/div[2]/p[1]"));
        String personName = person.getText();

        WebElement personLink = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[5]/div[1]/a"));
        personLink.click();

        WebElement personProfile = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[2]/div/div[1]/div[2]/h1"));

        assertAll(
                () -> assertEquals(personName, personProfile.getText())
                ,() -> {
                    WebElement back = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[2]/div/div[2]/div[1]"));
                    back.click();

                    WebElement backPerson = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[5]/div[1]/a/div[2]/p[1]"));
                    assertEquals(personName, backPerson.getText());
                }
        );
    }

    @Test
    public void settings() {
        WebElement avatar = driver.findElement(By.xpath("//*[@id=\"profile-menu\"]"));
        avatar.click();

        WebElement settingsLink = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div/div[4]/div[3]/span/div/div/div/button[2]/a"));
        settingsLink.click();

        WebElement notify = driver.findElement(By.xpath("//*[@id=\"checkbox\"]/input"));
        notify.click();

        WebElement toProfile = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[2]/div/div/div[3]/button"));
        toProfile.click();

        WebElement myName = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[2]/div/div[1]/div[2]/h1"));
        assertEquals("335086", myName.getText());
    }

    @Test
    public void edit() throws InterruptedException {
        WebElement avatar = driver.findElement(By.xpath("//*[@id=\"profile-menu\"]/span/img"));
        avatar.click();

        WebElement profile = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div/div[4]/div[3]/span/div/div/div/button[1]/a"));
        profile.click();

        WebElement edit = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[2]/div/div[1]/div[3]/button[2]"));
        edit.click();

        Thread.sleep(2000);

        WebElement nameArea = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[4]/div/div[2]/div/div[2]/div[2]/textarea"));
        nameArea.sendKeys("7");

        WebElement save = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[4]/div/div[2]/div/div[1]/button[2]"));
        save.click();

        Thread.sleep(2000);

        WebElement myName = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[2]/div/div[1]/div[2]/h1"));
        assertEquals("3350867", myName.getText());
    }


    @Test
    public void goHome() {
        assertAll(
                () -> {
                    WebElement avatar = driver.findElement(By.xpath("//*[@id=\"profile-menu\"]"));
                    avatar.click();

                    WebElement profile = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div/div[4]/div[3]/span/div/div/div/button[1]/a"));
                    profile.click();

                    WebElement home = driver.findElement(By.xpath("//*[contains(@class, 'profileEmoji')]"));
                    home.click();

                    assertEquals("https://www.answers.com/leaderboard/weekly-stats", driver.getCurrentUrl());
                }
                ,() -> {
                    WebElement home = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div/div[2]/a"));
                    home.click();

                    WebElement message = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/main/section[1]/h1"));
                    assertEquals("What is your question?", message.getText());
                }
        );
    }
}
