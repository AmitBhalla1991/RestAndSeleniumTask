package com.Test2.Tests;

import com.Test2.Objects.CheckoutPage;
import com.Test2.Objects.LoginPage;
import com.Test2.Objects.RegisterPage;
import com.Test2.TestBase.Base;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class GetTest extends Base {

    RegisterPage page;

    LoginPage data;

    CheckoutPage detail;

    static JsonPath jp;

    @Test(enabled = true, priority = 0, description = "TEST CASE 1 | Validate that User able to generate random user.")

    public static void TEST_01_Generate_Random_User() {

        logger.info("Test case 1 starts");

        String responseBody = response.getBody().asPrettyString();

        try {
            Assert.assertTrue(responseBody != null);
        } catch (Exception e) {

            e.printStackTrace();
            logger.warn("The response Body is:" + responseBody);

        }

        logger.info("The Response Body is:" + responseBody);

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);

        String Connection = response.header("Connection");
        Assert.assertEquals(Connection, "keep-alive");

        jp = new JsonPath(responseBody).setRootPath("results");

        String firstName=jp.getString("name.first").replaceAll("\\[|\\]", "");
        logger.info("The First Name is : " + firstName);

        String LastName = jp.getString("name.last").replaceAll("\\[|\\]", "");
        logger.info("The Last Name is : " + LastName);

        String email = jp.getString("email").replaceAll("\\[|\\]", "");
        logger.info("The Email is : " + email);

        logger.info("Test case 1 Ends");

    }

    @Test(enabled = true, priority = 1, description = "TEST CASE 2 | Validate that response Time of Random API should be less than 3000 ms")

    public static void TEST_O2_Response_Time() {

        logger.info("Test case 2 starts");

        long responseTime = response.getTime();

        if (responseTime > 3000) {
            logger.warn("Response Time is greater than 3000");
        }
        logger.info("The Response Time is:" + responseTime);

        Assert.assertTrue(responseTime < 3000);

        logger.info("Test case 2 ends");

    }

    @Test(enabled = true, priority = 2, description = "TEST CASE 3 | validate that user able to register at Test store")

    public void TEST_O3_Register_User() {

        logger.info("Test case 3 Starts");

        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

        String firstName = response.jsonPath().getString("results[0].name.first").toString();
        logger.info("The First Name is : " + firstName);
        String LastName = response.jsonPath().getString("results[0].name.last").toString();
        logger.info("The Last Name is : " + LastName);
        String email = response.jsonPath().getString("results[0].email").toString();
        logger.info("The Email is : " + email);

        page = new RegisterPage(driver);
        page.clickRegister();
        page.setFirstName(firstName);
        page.setLastName(LastName);
        page.setEmailAddress(email);
        page.setPassword(prop.getProperty("Password"));
        page.setconfirmPassword(prop.getProperty("Password"));
        page.createAccount();
        try {
            Thread.sleep(2000);
            page.signOutlink();
            page.signOutbutton();
        } catch (Exception e) {

            e.printStackTrace();
        }

        logger.info("Test case 3 Ends");

    }

    @Test(enabled = true, priority = 3, description = "TEST CASE 4 | validate that user NOT able register at Test store with existing user data.")

    public void TEST_O4_Register__Duplicate_User() {

        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

        logger.info("Test case 4 Starts");

        page = new RegisterPage(driver);

        page.clickRegister();
        page.setFirstName(prop.getProperty("Duplicate_firstName"));
        page.setLastName(prop.getProperty("Duplicate_lastName"));
        page.setEmailAddress(prop.getProperty("Duplicate_email"));
        page.setPassword(prop.getProperty("Password"));
        page.setconfirmPassword(prop.getProperty("Password"));
        page.createAccount();
        String error = page.duplicateAccount().toString();
        logger.warn("THE ERROR IS: " + error);
        Assert.assertEquals(error, prop.getProperty("DuplicateUser"));

        logger.info("Test case 4 Ends");

    }

    @Test(enabled = true, priority = 4, description = "TEST CASE 5 | validate that user able to signin at Test store with existing user data and start shopping.")

    public void TEST_O5_SignIn_And_Start_Shopping() {

        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

        logger.info("Test case 5 Starts");

        data = new LoginPage(driver);

        String email = response.jsonPath().getString("results[0].email").toString();
        logger.info("The Email is : " + email);

        data.signIn();
        data.loginUserName(email);
        data.loginPassword(prop.getProperty("Password"));
        data.clickSubmit();
        data.clickWhatsNew();
        data.clickNewYoga();
        data.clickechoShort();
        data.clickSize();
        data.clickColor();
        data.clickAddToCart();
        data.clickshoppingCartlink();
        data.clickproceedCheckout();
        logger.info("Test case 5 Ends");

    }

    @Test(enabled = true, priority = 5, description = "TEST CASE 6 | validate that user able to complete checkout and place order Succcessfully")

    public void TEST_O6_Complete_Checkout() {

        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

        logger.info("Test case 6 Starts");

        detail = new CheckoutPage(driver);
        detail.enterCompanyDetail(prop.getProperty("company"));
        detail.enterstreetAddress(prop.getProperty("streetAddress"));
        detail.enterCity(prop.getProperty("city"));
        detail.SelectState(prop.getProperty("state"));
        detail.enterPostCode(prop.getProperty("postCode"));
        detail.enterPhoneNumber(prop.getProperty("PH"));
        detail.checboxFlat();
        detail.clickNextButton();
        detail.clickBillingAddress();
        detail.clickplaceOrder();
        Assert.assertEquals(detail.orderStatus(), prop.getProperty("purchaseStatus"));
        logger.info("Test case 6 Ends");

    }

}
