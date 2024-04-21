package com.crm.qa.testcases;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ContactPageTest extends TestBase {
    LoginPage loginPage;
    HomePage homepage;
    TestUtil  tutil;
    ContactsPage contactsPage;
    String sheetName="contacts";

    public ContactPageTest() {
        super();
    }

    @BeforeMethod
    public void setUp() {
        intialization();
        loginPage = new LoginPage();
        contactsPage = new ContactsPage();
        homepage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
        tutil = new TestUtil();
        tutil.switchToFrame();
        // contactsPage = homepage.clickOnContacts();

    }


    @Test(priority = 1, enabled = false)
    public void validateContactNametest() {
        contactsPage.selectContactName("Iron");
        contactsPage.selectContactName("Bond");
    }

    @DataProvider
    public Object[][] getCRMContactData() {
        TestUtil  tutil=new TestUtil();
        Object[][] data = tutil.getTestData(sheetName);
        return data;
    }

    @Test(priority = 2, dataProvider = "getCRMContactData")
    public void validateNewContactTest(String title, String fname, String lname, String companyName) {
        homepage.hoverOnLink();
        contactsPage.createNewContact(title, fname, lname, companyName);
    }

    @AfterMethod
    public void tearDown() {
         driver.quit();
    }
}
