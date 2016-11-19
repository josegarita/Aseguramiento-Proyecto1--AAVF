package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TestSistemaNavigationLogout {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
	System.setProperty("webdriver.gecko.driver", "C:\\Users\\Dell\\Desktop\\geckodriver.exe");
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:3000/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testSistemaResults() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.linkText("Log in")).click();
    driver.findElement(By.id("user_email")).clear();
    driver.findElement(By.id("user_email")).sendKeys("admin@bocajunior.com");
    driver.findElement(By.id("user_password")).clear();
    driver.findElement(By.id("user_password")).sendKeys("bocajunior");
    driver.findElement(By.name("commit")).click();
    assertEquals("Cargar", driver.findElement(By.linkText("Cargar")).getText());
    driver.findElement(By.linkText("Cargar")).click();
    Thread.sleep(5000);
    assertEquals("Detector de escenas", driver.findElement(By.id("logo")).getText());
    Thread.sleep(5000);
    driver.findElement(By.linkText("Home")).click();
    Thread.sleep(5000);
    driver.findElement(By.linkText("Log Out")).click();
    Thread.sleep(5000);
    assertEquals("Sign in", driver.findElement(By.linkText("Sign in")).getText());
    Thread.sleep(5000);
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
