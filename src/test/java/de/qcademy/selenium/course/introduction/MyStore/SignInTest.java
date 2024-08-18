package de.qcademy.selenium.course.introduction.MyStore;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SignInTest {

    public static WebDriver driver;

    @BeforeAll
    static void GlobalSetup() {
        WebDriverManager.firefoxdriver().setup();
    }
    @BeforeEach
    void setup() {
        driver = new FirefoxDriver();
    }
    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    @Test
    @DisplayName("Sign in to My Store!")
    void singInTest() throws InterruptedException {

        //Arrange
        String username = "tomsmith";
        String password = "SuperSecretPassword!";
        String expected = "You logged into a secure area!\n×";

        //Act
        driver.get("https://the-internet.herokuapp.com/login");
        Thread.sleep(3000);
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.className("radius")).click();
        Thread.sleep(3000);

        String actual = driver.findElement(By.id("flash")).getText().trim();

        //Assert
        Assertions.assertEquals(expected, actual);
    }
}
