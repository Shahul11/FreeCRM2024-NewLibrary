package com.crm.qa.testcases;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTest extends TestBase {
    LoginPage loginPage;
    HomePage homepage;
    TestUtil tutil;
    ContactsPage contactsPage;

    public HomePageTest() {
        super();
    }

    @BeforeMethod
    public void setUp() {
        intialization();
        loginPage = new LoginPage();
        contactsPage = new ContactsPage();
        tutil = new TestUtil();
        homepage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
    }

    //@Test(priority = 1)
    public void homePatetitleTest() {
        String homepagetile = homepage.verifyHomePageTitle();
        Assert.assertEquals(homepagetile, "CRMPRO");
    }

    @Test(priority = 1)
    public void verifyUserNameTest() {
        tutil.switchToFrame();
        boolean flag = homepage.vreifyUserName();
        Assert.assertTrue(flag);
    }

    @Test(priority = 2)
    public void verifyContactsLinkTest() {
        tutil.switchToFrame();
        contactsPage = homepage.clickOnContacts();

    }


    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
