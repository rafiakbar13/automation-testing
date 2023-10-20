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

public class Cart {
    WebDriver driver;
    String baseUrl = "https://www.saucedemo.com/";

    @Given("call read\\({string})")
    public void call_read(String string) {
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
    @When("I click the Add to Cart button for a product")
    public void i_click_the_add_to_cart_button_for_a_product() {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
    }

    @Then("the product should be added to the shopping cart")
    public void the_product_should_be_added_to_the_shopping_cart() {
        driver.findElement(By.className("shopping_cart_link")).click();
        String product_name = driver.findElement(By.xpath("//*[@id=\"item_4_title_link\"]/div")).getText();
        Assert.assertEquals(product_name,"Sauce Labs Backpack");
    }

    @And("the shopping cart icon should display the correct item count")
    public void the_shopping_cart_icon_should_display_the_correct_item_count() {
        Boolean count_item = driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a/span")).isDisplayed();
        Assert.assertEquals(count_item,true);
        driver.close();
    }

    @And("I am on the shopping cart page")
    public void iAmOnTheShoppingCartPage() {
            driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span"));
    }

    @When("I click the Remove button for a product in the shopping cart")
    public void iClickTheButtonForAProductInTheShoppingCart() {
        driver.findElement(By.id("remove-sauce-labs-backpack")).click();
    }

    @Then("the product should be removed from the shopping cart")
    public void theProductShouldBeRemovedFromTheShoppingCart() {
            driver.findElement(By.id("continue-shopping")).isDisplayed();
            driver.close();
    }


}
