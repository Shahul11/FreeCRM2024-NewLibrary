package com.crm.qa.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.crm.qa.util.TestUtil;
import com.crm.qa.util.MyListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {

    public static WebDriver driver;
    public static Properties prop;
    public  static  WebDriverWait wait;
    public  static  WebDriver decorateDriver;

    public static ExtentSparkReporter sparkReporter;
    public static ExtentReports extent;
    public static ExtentTest test;

    public TestBase() {
        prop = new Properties();
        try {
            FileInputStream fis = new FileInputStream("K:\\Automation\\FreeCRMTestAutomation\\src\\main\\java\\com\\crm\\qa\\config\\config.properties");
            prop.load(fis);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void intialization() {
        String browsername = prop.getProperty("browser");
        if (browsername.equals("chrome")) {
            driver = new ChromeDriver();
            // wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10-second explicit wait
            WebDriverListener wevnt= new MyListener();
            EventFiringDecorator<WebDriver> fire = new EventFiringDecorator<WebDriver>(wevnt);
            driver=fire.decorate(driver);

        } else if (browsername.equals("firefox")) {
            driver = new FirefoxDriver();
        } else {
            driver = new EdgeDriver();
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
        driver.get(prop.getProperty("url"));

    }


    public void initializeReport(){
        sparkReporter =  new ExtentSparkReporter(System.getProperty("user.dir")+"/Reports/extentSparkReport.html");
        sparkReporter.config().setDocumentTitle("AutomationReport");
        sparkReporter.config().setReportName("Automation Test Execution Report");
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        extent =new ExtentReports();
        extent.attachReporter(sparkReporter);
    }


}
