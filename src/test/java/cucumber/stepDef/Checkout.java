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

public class Checkout {
    WebDriver driver;
    String baseUrl = "https://www.saucedemo.com/";


    @Given("the user on the product page")
    public void theUserOnTheProductPage() {
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
       String product_page =  driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText();
       Assert.assertEquals(product_page,"Products");
    }

    @When("the user clicks the Add to Cart button")
    public void theUserClicksTheAddToCartButtonOnOneOrMoreProducts() {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
    }

    @And("the user clicks the cart icon on the top right side")
    public void theUserClicksTheCartIconOnTheTopRightSide() {
        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();

    }

    @And("the user clicks the Checkout button")
    public void theUserClicksTheCheckoutButton() {
        driver.findElement(By.id("checkout")).click();
    }

    @And("the user is on the buyer's biodata form")
    public void theUserIsOnTheBuyerSBiodataForm() {
        String form_biodata = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText();
        Assert.assertEquals(form_biodata,"Checkout: Your Information");
    }

    @When("the user fills out the biodata form")
    public void theUserFillsOutTheBiodataForm() {
        driver.findElement(By.id("first-name")).sendKeys("rafi");
        driver.findElement(By.id("last-name")).sendKeys("akbar");
        driver.findElement(By.id("postal-code")).sendKeys("14564");
    }

    @And("the user clicks the Continue button")
    public void theUserClicksTheContinueButton() {
        driver.findElement(By.id("continue")).click();
    }

    @And("the user clicks the Finish button")
    public void theUserClicksTheFinishButton() {
        driver.findElement(By.id("finish")).click();
    }

    @Then("checkout completed")
    public void checkoutCompleted() {
       String checkout_completed =  driver.findElement(By.xpath("//*[@id=\"checkout_complete_container\"]/h2")).getText();
        Assert.assertEquals(checkout_completed, "Thank you for your order!");
        driver.close();
    }

    @Then("the user should be warned that buyer's biodata is required")
    public void the_user_should_be_warned_that_buyer_s_biodata_is_required() {
        String error_message = driver.findElement(By.xpath("//*[@id=\"checkout_info_container\"]/div/form/div[1]/div[4]")).getText();
        Assert.assertEquals(error_message, "Error: First Name is required");
        driver.close();
    }
}
