package com.crm.qa.util;

import com.crm.qa.base.TestBase;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TestUtil extends TestBase {
    public static long PAGE_LOAD_TIMEOUT = 20;
    public static long IMPLICIT_WAIT = 10;
    public static FileInputStream fis;
    static Workbook book;
    static Sheet sheet;


    public void switchToFrame() {
        driver.switchTo().frame("mainpanel");
    }

    public Object[][] getTestData(String sheetName) {
        try {
            fis = new FileInputStream("K:\\Automation\\FreeCRMTestAutomation\\src\\main\\java\\com\\crm\\qa\\testdata\\FreeCRMdata.xlsx");
            book = WorkbookFactory.create(fis);
            sheet = book.getSheet(sheetName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
                data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
            }

        }

        return data;
    }


    public void takeScreenShotAtEndOfTest() {

        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String currentDir = System.getProperty("user.dir");
        try {
            FileUtils.copyFile(screenshotFile, new File(currentDir + "/screenshot/" + System.currentTimeMillis() + ".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
