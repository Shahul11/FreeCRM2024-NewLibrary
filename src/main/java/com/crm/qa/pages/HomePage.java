package com.crm.qa.pages;

import com.crm.qa.base.TestBase;
import com.crm.qa.util.TestUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import javax.swing.*;

public class HomePage extends TestBase {

    @FindBy(xpath = "//td[contains(text(),'Shahul')]")
    WebElement lblUsername;

    @FindBy(xpath = "//a[contains(text(),'Contacts')]")
    WebElement linkContact;

    @FindBy(xpath = "//a[contains(text(),'Deals')]")
    WebElement linkDeals;

    @FindBy(xpath = "/a[contains(text(),'Tasks')]")
    WebElement linkTasks;

    @FindBy(xpath = "//a[contains(text(),'New Contact')]")
    WebElement linkNewContacts;


    public HomePage() {
        PageFactory.initElements(driver, this);
    }

    public String verifyHomePageTitle() {
        return driver.getTitle();
    }

    public boolean vreifyUserName() {
        return lblUsername.isDisplayed();
    }

    public ContactsPage clickOnContacts() {

        linkContact.click();
        return new ContactsPage();
    }

    public DealsPage clickOnDeals() {
        linkDeals.click();
        return new DealsPage();
    }

    public TaskPage clickOnTask() {
        linkTasks.click();
        return new TaskPage();
    }

    public void hoverOnLink() {
        Actions act = new Actions(driver);
        act.moveToElement(linkContact).build().perform();
        linkNewContacts.click();

    }


}
