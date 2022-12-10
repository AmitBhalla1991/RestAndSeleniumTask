package com.Test2.Objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Test2.TestBase.Base;

public class RegisterPage extends Base {

    public WebDriver driver;

    public RegisterPage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='panel header']//ul//li//a[normalize-space()='Create an Account']")
    WebElement setRegister;

    public void clickRegister() {

        logger.info("User click on Create account link");
        setRegister.click();
    }

    @FindBy(id = "firstname")
    WebElement setFirstName;

    public void setFirstName(String setfirstName) {
        logger.info("User Entering First Name");
        setFirstName.sendKeys(setfirstName);

    }

    @FindBy(id = "lastname")
    WebElement setLastName;

    public void setLastName(String setlastName) {
        logger.info("User Entering Last Name");
        setLastName.sendKeys(setlastName);

    }

    @FindBy(id = "email_address")
    WebElement setEmailAddress;

    public void setEmailAddress(String email_address) {
        logger.info("User Entering Email Address");
        setEmailAddress.sendKeys(email_address);

    }

    @FindBy(id = "password")
    WebElement setPassword;

    public void setPassword(String password) {
        logger.info("User Entering password");
        setPassword.sendKeys(password);

    }

    @FindBy(id = "password-confirmation")
    WebElement setconfirmPassword;

    public void setconfirmPassword(String confirmPassword) {
        logger.info("User Entering confirm password");
        setconfirmPassword.sendKeys(confirmPassword);

    }

    @FindBy(xpath = "//button//span[normalize-space()='Create an Account']")
    WebElement clickCreateAccount;

    public void createAccount() {
        logger.info("User tap on create Button");
        clickCreateAccount.click();
    }

    @FindBy(xpath = "//div[@role='alert' and @class='messages']//div//div[contains(text(),'There is already an account with this email address.')]")
    WebElement duplicateRegistration;

    public String duplicateAccount() {
        logger.warn("User data is already in use");
        String error = duplicateRegistration.getAttribute("textContent").toString();
        return error;

    }

    @FindBy(xpath = "(//ul[@class='header links']//span//button[@class='action switch']//span[normalize-space()='Change'])[1]")
    WebElement signOutlink;

    public void signOutlink() {
        logger.warn("User tap on signout link");
        Actions act = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(signOutlink));
        act.click(signOutlink).build().perform();

    }

    @FindBy(xpath = "(//li//div[@class='customer-menu']//li[normalize-space()='Sign Out'])[1]")
    WebElement signOutbutton;

    public void signOutbutton() {
        logger.warn("User tap on signout button");
        Actions act = new Actions(driver);
        act.click(signOutbutton).build().perform();

    }

}
