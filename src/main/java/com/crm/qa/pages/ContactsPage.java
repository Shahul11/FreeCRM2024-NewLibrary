package com.crm.qa.pages;

import com.crm.qa.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ContactsPage extends TestBase {


    @FindBy(xpath = "//td[contains(text(),'Contacts')]")
    WebElement lblContacts;


    @FindBy(id = "first_name")
    WebElement txtboxFirstName;

    @FindBy(id = "surname")
    WebElement txtboxSurName;

    @FindBy(xpath = "//input[@name='client_lookup']")
    WebElement txtboxCompany;


    @FindBy(xpath = "//input[contains(@value,'Load From Company')]/../input[@value='Save']")
    WebElement btnSave;


    public ContactsPage() {
        PageFactory.initElements(driver, this);
    }


    public boolean verifyContactLable() {
        return lblContacts.isDisplayed();
    }

    public void selectContactName(String name) {
        driver.findElement(By.xpath("//form//tr/td/a[contains(text(),'" + name + "')]/../../td[1]")).click();
    }

    public void createNewContact(String title, String fname, String lname, String compName) {
        Select sl = new Select(driver.findElement(By.name("title")));
        sl.selectByValue(title);
        txtboxFirstName.sendKeys(fname);
        txtboxSurName.sendKeys(lname);
        txtboxCompany.sendKeys(compName);
        btnSave.click();


    }


}
