package com.github.diosa;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class App {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("https://answers.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
}