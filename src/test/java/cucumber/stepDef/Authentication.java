package cucumber.stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.concurrent.TimeUnit;

public class Authentication {
    WebDriver driver;
    String baseUrl = "https://www.saucedemo.com/";


    @Given("I open the Swaglab site login page")
    public void halaman_login_swaglabs() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.get(baseUrl);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

        //Assertion
        String title_loginPage = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]")).getText();
        Assert.assertEquals(title_loginPage,"Swag Labs");
    }

    @When("input a valid username into the Username field")
    public void input_username_field() {
        WebElement username = driver.findElement(By.id("user-name"));
        username.sendKeys("standard_user");
    }

    @And("input the correct password into the Password field")
    public  void  input_password_field() {
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("secret_sauce");
    }

    @And("click the Login button")
    public void button_login(){
        WebElement button = driver.findElement(By.id("login-button"));
        button.click();
    }

    @Then("should show page products")
    public void show_page_products() {
        String title = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText();
        Assert.assertEquals(title,"Products");
        driver.close();
    }

    @And("I click bars on the top left")
    public void iClickBarsOnTheTopLeft() {
        driver.findElement(By.id("react-burger-menu-btn")).click();
    }

    @And("I click button logout")
    public void iClickButtonLogout() {
        driver.findElement(By.id("logout_sidebar_link")).click();
    }

    @Then("should show login page")
    public void shouldShowLoginPage() {
        String title_page = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]")).getText();
        Assert.assertEquals(title_page,"Swag Labs");
        driver.close();
    }

    @And("input an invalid password into the Password field")
    public void invalid_password() {
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("123456");
    }

    @Then("should see an error message")
    public void error_message(){
        String ErrorLogin = driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText();
        Assert.assertEquals(ErrorLogin, "Epic sadface: Username and password do not match any user in this service");
        driver.close();
    }
}