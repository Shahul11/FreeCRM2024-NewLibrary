package com.crm.qa.pages;

import com.crm.qa.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends TestBase {

    @FindBy(name = "username")
    WebElement txtBoxUserName;

    @FindBy(name = "password")
    WebElement txtBoxUserPassword;

    @FindBy(xpath = "//input[@type='submit']")
    WebElement btnLogin;

    @FindBy(xpath = "//a/img[contains(@alt,'Free CRM Software for customer')]")
    WebElement crmLogo;


    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    public String validateLoginPageTitle() {
        return driver.getTitle();
    }

    public boolean validateCRMImage() {
        return crmLogo.isDisplayed();
    }

    public HomePage login(String userName, String pwd) {
        txtBoxUserName.sendKeys(userName);
        txtBoxUserPassword.sendKeys(pwd);
        btnLogin.click();
        return new HomePage();
    }

}
