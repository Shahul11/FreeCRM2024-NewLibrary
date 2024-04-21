package com.crm.qa.util;

import com.crm.qa.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class MyListener extends TestBase implements WebDriverListener {

TestUtil tu= new TestUtil();

    @Override
    public void onError(Object target, Method method, Object[] args, InvocationTargetException e) {
        System.out.println("Error while calling method: " + method.getName() + " - " + e.getMessage());
        try{
            tu.takeScreenShotAtEndOfTest();
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void beforeGet(WebDriver driver, String url) {
        System.out.println("Before Navigation " + url);
    }

    @Override
    public void afterGet(WebDriver driver, String url) {
        System.out.println("After Navigation " + url);
    }

    @Override
    public void beforeClick(WebElement element) {
        System.out.println("Before clicking the Element is " +element);
    }

    @Override
    public void afterClick(WebElement element) {
        System.out.println("After clicking the Element is " +element);
    }

    @Override
    public void afterSendKeys(WebElement element, CharSequence... keysToSend) {
        for (int i = 0; i < keysToSend.length; i++) {
            System.out.println("Typing the Element : " + element + "entered the value is " +keysToSend[i]);
        }

    }

    @Override
    public void afterFindElement(WebElement element, By locator, WebElement result) {
        System.out.println("After Finding the element " + element + " By the locator of " + locator + "Result is " + result);
    }



}
