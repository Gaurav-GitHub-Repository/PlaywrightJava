package Utility;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.MouseButton;
import com.microsoft.playwright.options.SelectOption;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;

public class ReusableComponents {

    Page page;

    //Assign page
    public ReusableComponents(Page page) {
        this.page = page;
    }

    //Click on element
    public void clickElement(Locator locator) {
        waitForElement(locator, "VISIBLE", "5000");
        locator.click();
    }

    //Double click on element
    public void doubleClickElement(Locator locator) {
        waitForElement(locator, "VISIBLE", "5000");
        locator.dblclick();
    }

    //Right click on element
    public void rightClickElement(Locator locator) {
        waitForElement(locator, "VISIBLE", "5000");
        locator.click(new Locator.ClickOptions().setButton(MouseButton.RIGHT));
    }

    //Mouse hover the element
    public void hoverOverElement(Locator locator) {
        waitForElement(locator, "VISIBLE", "5000");
        locator.hover();
    }

    //Enter text in element
    public void fillTextElement(Locator locator, String text) {
        waitForElement(locator, "ATTACHED", "5000");
        locator.fill(text);
    }

    //Clear text in element
    public void clearTextElement(Locator locator) {
        waitForElement(locator, "VISIBLE", "5000");
        locator.clear();
    }

    //Select dropdown by value
    public void selectDropdownByValue(Locator locator, String value) {
        waitForElement(locator, "VISIBLE", "5000");
        locator.selectOption(value);
    }

    //Select dropdown by index
    public void selectDropdownByIndex(Locator locator, int index) {
        waitForElement(locator, "VISIBLE", "5000");
        locator.selectOption(new SelectOption().setIndex(index));
    }

    //Select dropdown
    public void selectDropdown(Locator dropdown, String textToSelect) {
        List<Locator> options = dropdown.locator("option").all();
        for (Locator option : options) {
            if (option.innerText().equalsIgnoreCase(textToSelect)) {
                dropdown.selectOption(new SelectOption().setLabel(textToSelect));
                break;
            }
        }
    }

    //Select Radio Button
    public void selectRadioButton(Locator locator) {
        waitForElement(locator, "VISIBLE", "5000");
        locator.click();
        PlaywrightAssertions.assertThat(locator).isChecked();
    }

    //Deselect Radio Button
    public void deselectRadioButton(Locator locator) {
        waitForElement(locator, "VISIBLE", "5000");
        PlaywrightAssertions.assertThat(locator).isChecked();
        locator.click();
    }

    //get text of the element
    public String getTextElement(Locator locator) {
        waitForElement(locator, "ATTACHED", "5000");
        String text = locator.innerText();
        return text;
    }

    //Scroll till the element
    public void scrollToElement(Locator locator) {
        waitForElement(locator, "ATTACHED", "5000");
        locator.scrollIntoViewIfNeeded();
    }

    //Click on the element using javascript
    public void jsClick(String locatorValue) {
        ElementHandle locator = page.querySelector(locatorValue);
        locator.evaluate("locator => locator.click()", locator);
    }

    //Scroll till the element using javascript
    public void jsScroll(String locatorValue) {
        ElementHandle locator = page.querySelector(locatorValue);
        locator.evaluate("locator => locator.scrollIntoView();", locator);
    }

    //Take screenshot
    public void captureScreenshot() {
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(System.getProperty("user.dir") + "/Screenshots/Screenshots.png")));
    }

    //Take screenshot with locator wise
    public void captureScreenshotWithLocator(Locator locator) {
        locator.screenshot(new Locator.ScreenshotOptions().setPath(Paths.get(System.getProperty("user.dir") + "/Screenshots/Screenshots.png")));
    }

    //Switch to new window and bring to front
    public void switchMainWindow(String value) {
        Page newTab = page.waitForPopup(() -> {
            page.locator(value).click();
        });
        page.bringToFront();
    }

    //Switch to new window
    public void handleWindow(String value) {
        Page newTab = page.waitForPopup(() -> {
            page.locator(value).click();
        });
    }

    //Set load state
    public void setLoadState(String value, int Timeout) {
        switch (value) {
            case "LOAD":
                page.waitForLoadState(LoadState.LOAD, new Page.WaitForLoadStateOptions().setTimeout(Double.parseDouble(String.valueOf(Timeout))));
                break;
            case "DOMCONTENTLOADED":
                page.waitForLoadState(LoadState.DOMCONTENTLOADED, new Page.WaitForLoadStateOptions().setTimeout(Double.parseDouble(String.valueOf(Timeout))));
                break;
            case "NETWORKIDLE":
                page.waitForLoadState(LoadState.NETWORKIDLE, new Page.WaitForLoadStateOptions().setTimeout(Double.parseDouble(String.valueOf(Timeout))));
                break;
            default:
                System.out.println("Invalid load state specified");
                throw new IllegalArgumentException("Invalid load state specified: " + value);
        }
    }

    //Wait For Element Options
    public void waitForElement(Locator locator, String elementToBe, String Timeout) {
        switch (elementToBe) {
            case "VISIBLE":
                locator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(Double.parseDouble(Timeout)));
                break;
            case "ATTACHED":
                locator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.ATTACHED).setTimeout(Double.parseDouble(Timeout)));
                break;
            case "DETACHED":
                locator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.DETACHED).setTimeout(Double.parseDouble(Timeout)));
                break;
            case "HIDDEN":
                locator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN).setTimeout(Double.parseDouble(Timeout)));
                break;
        }
    }

    public String[] readExcel(String excelSheet, int rowNum, int colNum) throws IOException {
        {
            String[] result = null;
            DataFormatter formatter = new DataFormatter();
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/TestData/executeTestData.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            int totalSheet = workbook.getNumberOfSheets();
            for (int i = 0; i <= totalSheet; i++) {
                if (workbook.getSheetName(i).equalsIgnoreCase(excelSheet)) {
                    XSSFSheet sheet = workbook.getSheetAt(i);
                    int totalRows = sheet.getLastRowNum();
                    for (int j = 0; j < totalRows; j++) {
                        Iterator<Row> rows = sheet.iterator();
                        while (rows.hasNext()) {
                            Row row = rows.next();
                            int RowNum = row.getRowNum();
                            if (RowNum == rowNum) {
                                Iterator<Cell> cell = row.cellIterator();
                                while (cell.hasNext()) {
                                    int CellNum = cell.next().getColumnIndex();
                                    if (CellNum == colNum) {
                                        String[] value = new String[]{formatter.formatCellValue(row.getCell(colNum))};
                                        result = value;
                                    }
                                }
                            }
                        }
                        break;
                    }
                    break;
                }
            }
            return result;
        }
    }

}
