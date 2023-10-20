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

public class Filter {
    WebDriver driver;
    String baseUrl = "https://www.saucedemo.com/";

    @Given("the user successfully logs in")
    public void user_success_login() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.get(baseUrl);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        WebElement username = driver.findElement(By.id("user-name"));
        username.sendKeys("standard_user");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("secret_sauce");
        WebElement button = driver.findElement(By.id("login-button"));
        button.click();
    }

    @When("the user clicks on the filter option")
    public void theUserClicksOnTheFilterOption() {
        driver.findElement(By.className("product_sort_container")).click();
    }

    @And("selects Price High to Low")
    public void selects() {
        driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div/span/select/option[4]")).click();
    }

    @Then("the product page should display products with the highest price")
    public void theProductPageShouldDisplayProductsWithTheHighestPrice() {
        String price = driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[1]/div[2]/div[2]/div")).getText();
        Assert.assertEquals(price,"$49.99");
        driver.close();
    }
}
