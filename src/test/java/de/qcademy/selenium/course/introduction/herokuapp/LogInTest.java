package de.qcademy.selenium.course.introduction.herokuapp;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LogInTest {

    private static WebDriver driver;

    @BeforeAll
    static void globalSetUp() {
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
    @DisplayName("should check if a valid user can log in")
    public void testValidUserLogIn() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/login");
        Thread.sleep(3000);
        WebElement userNameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement logInButton = driver.findElement(By.className("radius"));

        userNameField.sendKeys("tomsmith");
        passwordField.sendKeys("SuperSecretPassword!");
        logInButton.click();
        Thread.sleep(3000);

        WebElement successMessage = driver.findElement(By.id("flash"));
        assert(successMessage.getText().contains("You logged into a secure area!"));
    }

    @Test
    @DisplayName("should check that the error message for an invalid username is shown")
    public void testInvalidUsernameErrorMessage() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/login");
        Thread.sleep(3000);

        WebElement userNameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement logInButton = driver.findElement(By.className("radius"));

        userNameField.sendKeys("invlaidUserName");
        passwordField.sendKeys("SuperSecretPassword!");
        logInButton.click();
        Thread.sleep(3000);

        WebElement successMessage = driver.findElement(By.id("flash"));
        assert(successMessage.getText().contains("Your username is invalid!"));
    }
    @Test
    @DisplayName("Test case should check that  the error message for an invalid password is shown")
    public void testInvalidPasswordErrorMessage() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/login");
        Thread.sleep(3000);

        WebElement userNameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement logInButton = driver.findElement(By.className("radius"));

        userNameField.sendKeys("tomsmith");
        passwordField.sendKeys("InvalidPassword");
        logInButton.click();
        Thread.sleep(3000);

        WebElement successMessage = driver.findElement(By.id("flash"));
        assert(successMessage.getText().contains("Your password is invalid!"));
    }
}
