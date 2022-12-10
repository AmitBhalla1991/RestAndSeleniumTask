package com.Test2.Objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Test2.TestBase.Base;

public class LoginPage extends Base {

    public WebDriver driver;

    public LoginPage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='panel header']//ul//li[@class='authorization-link']//a[normalize-space()='Sign In']")
    WebElement signIn;

    public void signIn() {
        logger.info("User tap on signIn button");
        Actions act = new Actions(driver);
        act.click(signIn).build().perform();

    }

    @FindBy(id = "email")
    WebElement loginUserName;

    public void loginUserName(String userName) {
        logger.info("User Entering userName for signin");
        loginUserName.sendKeys(userName);

    }

    @FindBy(id = "pass")
    WebElement loginPassword;

    public void loginPassword(String password) {
        logger.info("User Entering password for signin");
        loginPassword.sendKeys(password);

    }

    @FindBy(xpath = "(//div[@class='actions-toolbar']//button[@name='send'])[1]")
    WebElement submitButton;

    public void clickSubmit() {
        logger.info("User tap on submit button for signin");
        submitButton.click();

    }

    @FindBy(xpath = "//li[@role='presentation']//a[@id='ui-id-3']")
    WebElement whatsNew;

    public void clickWhatsNew() {
        logger.info("User tap on what's New Button");
        whatsNew.click();

    }

    @FindBy(xpath = "//span//span[normalize-space()='Shop New Yoga']")
    WebElement shopNewYoga;

    public void clickNewYoga() {
        logger.info("User tap on shop New yoga Button");
        shopNewYoga.click();

    }

    @FindBy(xpath = "//li[contains(@class,'item product')]//img[@alt='Echo Fit Compression Short']")
    WebElement echoShort;

    public void clickechoShort() {
        logger.info("User tap on echo short Button");
        Actions act = new Actions(driver);
        act.moveToElement(echoShort).build().perform();
        echoShort.click();

    }

    @FindBy(xpath = "//div[@role='listbox']//div[@option-id='171']")
    WebElement size;

    public void clickSize() {
        logger.info("User choose size of selected item");
        Actions act = new Actions(driver);
        act.moveToElement(size).build().perform();
        size.click();

    }

    @FindBy(xpath = "//div[@role='listbox']//div[@option-label='Black']")
    WebElement color;

    public void clickColor() {
        logger.info("User choose color of selected item");
        Actions act = new Actions(driver);
        act.moveToElement(color).build().perform();
        color.click();

    }

    @FindBy(id = "product-addtocart-button")
    WebElement AddToCart;

    public void clickAddToCart() {
        logger.info("User tap on add to cart button");
        AddToCart.click();

    }

    @FindBy(xpath = "//div//a[text()='shopping cart']")
    WebElement shoppingCart;

    public void clickshoppingCartlink() {
        logger.info("User tap on shopping cart link");
        shoppingCart.click();

    }


    @FindBy(xpath = "//button[@data-role='proceed-to-checkout']")
    WebElement proceedCheckout;

    public void clickproceedCheckout() {
        logger.info("User tap on proceed checkout button");
        WebDriverWait wait= new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfAllElements(proceedCheckout));
        proceedCheckout.click();

    }
}
