package com.Test2.TestBase;

import com.Test2.Utilities.Wait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

@Listeners(com.Test2.Utilities.observation.class)

public class Base {

    public static RequestSpecification httpRequest;
    public static Response response;
    private static com.Test2.Utilities.RandomStringGeneration RandomStringGeneration;
    public String generatedString = RandomStringGeneration.randomstring();
    public String str = generatedString;
    public String generatedNumber = RandomStringGeneration.number();
    public String num = generatedNumber;

    public static ExtentReports extent;
    public static ExtentTest test;
    public static ExtentSparkReporter spark;

    public static Properties prop;

    public static WebDriver driver;
    public ChromeOptions chromeOptions;

    public DesiredCapabilities cap;

    protected static Logger logger = LoggerFactory.getLogger(Base.class.getName());

    Faker fake = new Faker();


    @BeforeTest

    public void initialization() {
        cap = new DesiredCapabilities();
        cap.setJavascriptEnabled(true);
        chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--incognito");
        chromeOptions.addArguments("--remote-allow-origins=*");
        //chromeOptions.setHeadless(true);
        chromeOptions.merge(cap);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        Wait.waitInSeconds(60);
        driver.get(prop.getProperty("TestURL"));
        String title = driver.getTitle();
        logger.info("====================FASTEN YOUR BELT AND JOIN THE RIDE=========================" + "\n" + "======================================================================================================");
        if (title.equalsIgnoreCase(prop.getProperty("title"))) {
            logger.info("The Title is Correct");
            Assert.assertEquals(title, prop.getProperty("title"));

        } else {

            logger.warn("The Title is Changed");
        }

    }

    @BeforeTest

    public static void ConfigurationFile() {

        prop = new Properties();

        try {
            FileInputStream fis = new FileInputStream(
                    System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "Helping files" + File.separator + "Env.properties");

            try {
                prop.load(fis);

            } catch (IOException fi) {

                fi.printStackTrace();
            }

        } catch (FileNotFoundException fn) {

            fn.printStackTrace();
        }

    }

    @BeforeTest

    public static void setup() {
        RestAssured.baseURI = prop.getProperty("randomAPI");
        httpRequest = RestAssured.given();
        response = given().when().get(Resources.Resourcedata);

    }


    @AfterTest(enabled = true, alwaysRun = true) // Test cleanup
    public void TeardownTest() {

        logger.info("Closing the Browser");
        driver.close();
        logger.info("Quiting the Browser");
        driver.quit();

    }
}
