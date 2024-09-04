package de.qcademy.selenium.course.introduction;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class BasicSelectors {

    private static WebDriver driver;

    @BeforeAll
    static void globalSetup() {
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
    @DisplayName("Should register a new user")
    void RegistrationTest1() throws InterruptedException {

        // Arrange
        String email = "bugyboss5+14@gmail.com";
        String vorname = "John";
        String nachname = "Max";
        String password = "lksalhd5dk";
        String expected = "John Max";

        // Act
        driver.get("http://www.automationpractice.pl/index.php?controller=authentication&back=my-account");
        Thread.sleep(3000);
        driver.findElement(By.className("login")).click();
        driver.findElement(By.name("email_create")).sendKeys(email);
        driver.findElement(By.name("SubmitCreate")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("id_gender1")).click();
        driver.findElement(By.id("customer_firstname")).sendKeys(vorname);
        driver.findElement(By.id("customer_lastname")).sendKeys(nachname);
        driver.findElement(By.id("passwd")).sendKeys(password);
        Select daysSelct = new Select(driver.findElement(By.id("days")));
        daysSelct.selectByIndex(10);
        Select monthsSelct = new Select(driver.findElement(By.id("months")));
        monthsSelct.selectByIndex(3);
        Select yearsSelct = new Select(driver.findElement(By.id("years")));
        yearsSelct.selectByIndex(5);
        driver.findElement(By.id("submitAccount")).click();
        Thread.sleep(10000);
        String userName = driver.findElement(By.className("account")).getText().trim();

        // Assert
        Assertions.assertEquals(expected, userName);
    }
}


