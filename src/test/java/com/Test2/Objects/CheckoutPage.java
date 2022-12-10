package com.Test2.Objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Test2.TestBase.Base;

public class CheckoutPage extends Base {

    public WebDriver driver;

    public CheckoutPage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "company")
    WebElement CompanyDetail;

    public void enterCompanyDetail(String enterCompanyDetail) {
        logger.info("User enter Company Details for placing order");
        CompanyDetail.sendKeys(enterCompanyDetail);

    }

    @FindBy(name = "street[0]")
    WebElement streetAddress;

    public void enterstreetAddress(String enterstreetDetail) {
        logger.info("User enter street address for placing order");
        streetAddress.sendKeys(enterstreetDetail);

    }

    @FindBy(name = "city")
    WebElement city;

    public void enterCity(String enterCityDetail) {
        logger.info("User enter city for placing order");
        city.sendKeys(enterCityDetail);

    }

    @FindBy(xpath = "//div[@name='shippingAddress.region_id']//div//select")
    WebElement state;

    public void SelectState(String Text) {
        logger.info("User selects state'\'province for placing order");
        Select select = new Select(state);
        select.selectByVisibleText(Text);
        logger.info("The Selected dropdown is :"+Text);

    }

    @FindBy(name = "postcode")
    WebElement postal;

    public void enterPostCode(String enterpostcodeDetail) {
        logger.info("User enter postcode for placing order");
        postal.sendKeys(enterpostcodeDetail);

    }

    @FindBy(name = "telephone")
    WebElement telephone;

    public void enterPhoneNumber(String enterPH) {
        logger.info("User enter phone Number for placing order");
        telephone.sendKeys(enterPH);

    }

    @FindBy(xpath = "//tr//td//input[@value='flatrate_flatrate']")
    WebElement flatRate;

    public void checboxFlat() {
        logger.info("User checked flat rate checkbox for placing order");
        flatRate.click();

    }

    @FindBy(xpath = "//span[normalize-space()='Next']")
    WebElement Next;

    public void clickNextButton() {
        logger.info("User clicked on NEXT Button for placing order");
        Next.click();

    }

    @FindBy(name = "billing-address-same-as-shipping")
    WebElement billing_address;

    public void clickBillingAddress() {
        logger.info("User clicked on checkbox of billing-address-same-as-shipping for placing order");
        billing_address.click();

    }

    @FindBy(xpath = "//div[@class='primary']//button[@title='Place Order']")
    WebElement placeOrder;

    public void clickplaceOrder() {
        logger.info("User clicked on place order Button for placing order");
        Actions act = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(placeOrder));
        act.moveToElement(placeOrder).build().perform();
        act.click(placeOrder).build().perform();

    }

    @FindBy(xpath = "//main//div[@class='page-title-wrapper']//span[normalize-space()='Thank you for your purchase!']")
    WebElement OrderSuccess;

    public String orderStatus() {
        logger.info("User checking status of Order");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(OrderSuccess));
        String status = OrderSuccess.getAttribute("textContent").toString();
        logger.info("The Purchase Status is " + status);
        return status;

    }
}
