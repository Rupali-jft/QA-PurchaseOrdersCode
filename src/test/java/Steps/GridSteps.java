package Steps;


import Base.BaseUtil;
import Pages.CommonGrid;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class GridSteps extends BaseUtil {

    @And("I get the {string} for {string}")
    public void iGetTheFor(String colName, String rowName) {
        BaseUtil.pageLoaded();
        System.out.println(colName + " for " + rowName + " is: " + commonGrid.gridEntry(rowName, colName));
    }

    @And("I get the current grid page number")
    public void iGetTheCurrentGridPageNumber() throws Exception {
        BaseUtil.pageLoaded();
        int result = commonGrid.gridPageNumber(currentTab, "current");
        if (result == 0) {
            System.out.println("Page number not displayed as there are no entries in the grid");
        } else if (result == -1) {
            throw new Exception("Unable to get page number");
        } else {
            System.out.println("Currently on page " + result + " of grid");
        }
    }

    @And("I move to the next page in the grid")
    public void iMoveToTheNextPageInTheGrid() {
        BaseUtil.pageLoaded();
        System.out.println("Clicking next page button in " + currentTab + " grid");
        int currentPage = commonGrid.gridPageNumber(currentTab, "current");
        if (currentPage == -1) {
            if (commonGrid.gridNextPage(currentTab)) {
                System.out.println("Moved to next page in grid");
            } else {
                System.out.println("No next page to move to in grid");
            }
        } else if (currentPage == 0) {
            System.out.println("Cannot go to next page as the grid has no entries");
        } else {
            int newPage;
            if (commonGrid.gridNextPage(currentTab)) {
                BaseUtil.pageLoaded();
                newPage = commonGrid.gridPageNumber(currentTab, "current");
                Assert.assertEquals(newPage, currentPage + 1,
                        "Did not move to previous page after clicking previous page button! Previous page: " + currentPage + " current page: " + newPage);
                System.out.println("Successfully moved to next page " + newPage + " from original page " + currentPage);
            } else {
                System.out.println("Could not go to next page of grid as page " + currentPage + " is the last page");
            }
        }
    }

    @And("I move to the previous page in the grid")
    public void iMoveToThePreviousPageInTheGrid() {
        BaseUtil.pageLoaded();
        System.out.println("Clicking previous page button in " + currentTab + " grid");
        int currentPage = commonGrid.gridPageNumber(currentTab, "current");
        if (currentPage == -1) {
            if (commonGrid.gridPrevPage(currentTab)) {
                System.out.println("Moved to previous page in grid");
            } else {
                System.out.println("No previous page to move to in grid");
            }
        } else if (currentPage == 0) {
            System.out.println("Cannot go to previous page as the grid has no entries");
        } else {
            int newPage;
            if (commonGrid.gridPrevPage(currentTab)) {
                BaseUtil.pageLoaded();
                newPage = commonGrid.gridPageNumber(currentTab, "current");
                Assert.assertEquals(newPage, currentPage - 1,
                        "Did not move to previous page after clicking previous page button! Previous page: " + currentPage + " current page: " + newPage);
                System.out.println("Successfully moved to previous page " + newPage + " from original page " + currentPage);
            } else {
                System.out.println("Could not go to previous page of grid as page " + currentPage + " is the first page");
            }
        }
    }

    @And("I change pages by entering a number")
    public void iEnterANumber() throws Exception {
        BaseUtil.pageLoaded();
        int currentPage = commonGrid.gridPageNumber(currentTab, "current");
        if (currentPage == 0) {
            System.out.println("Cannot change pages as there are no entries in the grid");
        } else {
            int randPage = commonGrid.gridRandomPage(currentTab);
            if (randPage == 1) {
                System.out.println("Only one page, grid displays all available records");

            } else if (randPage == -1) {
                throw new Exception("Unable to find page number");
            } else {
                System.out.println("Moved from page " + currentPage + " to " + randPage);
            }
        }
    }

    @When("I navigate to the {string} tab")
    public void iNavigateToTheTab(String tabName) {
        currentTab = tabName;
        Assert.assertTrue(commonGrid.gridTab(currentTab), "Unable to navigate to " + tabName + " tab!");
        System.out.println("On " + tabName + " tab");
    }

    @When("I select the {string} option from the the Viewing drop down")
    public void iSelectTheOptionFromTheTheViewingDropDown(String rowOption) {
        BaseUtil.pageLoaded();
        int currentPage = commonGrid.gridPageNumber(currentTab, "current");
        if (currentPage == 0) {
            System.out.println("Cannot change grid size as there are no entries in the grid");
        } else {
            rowCount = Integer.parseInt(rowOption);
            commonGrid.gridViewSelection(currentTab, rowOption);
        }

    }

    @Then("The number of rows displayed will be less than or equal to the number selected")
    public void theNumberOfRowsDisplayedWillBeLessThanOrEqualToTheNumberSelected() {
        BaseUtil.pageLoaded();
        int currentPage = commonGrid.gridPageNumber(currentTab, "current");
        if (currentPage == 0) {
            System.out.println("No rows to count as there are no entries in the grid");
        } else {
            int recordCount = commonGrid.gridRecordNumber(currentTab);
            int visibleRows = commonGrid.gridRowCount(currentTab);
            Assert.assertTrue(visibleRows == rowCount || visibleRows == recordCount,
                    "Rows displayed (" + visibleRows + ") does note equal selection from Viewing dropdown (" + rowCount + "), or record count (" + recordCount + ")");
            System.out.println("Number of records currently displayed is " + visibleRows + " of a possible " + recordCount);
        }
    }

    @And("I enter {string} into the {string} grid header")
    public void iEnterIntoTheGridHeader(String textEntry, String header) {
        System.out.println("Searching for " + textEntry + " in " + header + " column");
        headerChoice = header;
        headerInfo = textEntry;
        commonGrid.gridHeaderField(header, textEntry);
        BaseUtil.pageLoaded();
    }

    @And("I open the {string} grid item")
    public void iOpenTheGridItem(String gridItem) {
        System.out.println(commonGrid.gridOpenItem(currentTab, gridItem));
    }

    @And("I select {string} from the {string} header in the grid")
    public void iSelectFromTheHeaderInTheGrid(String selection, String header) {
        System.out.println("Selecting " + selection + " from the " + header + " header");
        headerChoice = header;
        headerInfo = selection;
        Assert.assertTrue(commonGrid.gridHeaderSelectorSelect(header, selection),
                "Could not find " + selection + " option in " + header + " grid header");
        BaseUtil.pageLoaded();
    }

    @When("I get the number of records in the {string} tab")
    public void iGetTheNumberOfRecordsInTheTab(String tabName) {
        BaseUtil.pageLoaded();
        commonGrid.gridTab(tabName);
        gridRecords = commonGrid.gridRecordNumber(tabName);
        System.out.println("Number of records in " + tabName + " grid: " + gridRecords);
    }

    @And("I check if {string} is selected in the {string} header")
    public void iCheckIfIsSelectedInTheHeader(String selection, String headerName) {
        ArrayList<String> entries = commonGrid.gridHeaderSelectorRead(headerName);
        Assert.assertNotNull(entries, "No active selections in " + headerName + " list!");
        Assert.assertTrue(entries.contains(selection), selection + " not selected in " + headerName + " list!");
        System.out.println(selection + " is currently selected in " + headerName + " list");

    }

    @And("I check if the following items are selected in the {string} header")
    public void iCheckIfTheFollowingItemsAreSelectedInTheHeader(String headerName, List<List<String>> table) {
        List<String> items = table.get(0);
        ArrayList<String> entries = commonGrid.gridHeaderSelectorRead(headerName);
        Assert.assertNotNull(entries, "No active selections in " + headerName + " list!");
        for (String entry : items) {
            Assert.assertTrue(entries.contains(entry), entry + " not selected in " + headerName + " list!");
            System.out.println(entry + " is currently selected in " + headerName + " list");
        }
    }

    String rowInfo;

    @And("I enter that wo number into the grid")
    public void iEnterThatWoNumberIntoTheGrid() {
        commonGrid.gridHeaderField("WO #", rowInfo);
    }

    String headerChoice;
    String headerInfo;

    @And("I get the {string} for {string} of the grid")
    public void iGetTheForRowOfTheGrid(String columnName, String rowNumber) {
        // columnName is the header of the grid (eg. "Branch Office")
        // rowNumber is the index (starting at 1) of the row you want to view (eg. "row 3")
        headerChoice = columnName;
        headerInfo = commonGrid.gridEntry(rowNumber, columnName).getText();
        System.out.println("The info in " + rowNumber + " for " + columnName + " was " + headerInfo);
    }

    @And("I enter that information into the grid header")
    public void iEnterThatInformationIntoTheGridHeader() {
        System.out.println("Entering " + headerInfo + " into " + headerChoice + " header");
        commonGrid.gridHeaderField(headerChoice, headerInfo);
        try {
            wait.until(ExpectedConditions.stalenessOf(commonGrid.gridEntry("row 1", headerChoice)));
        } catch (TimeoutException ignored) {
        }
        BaseUtil.pageLoaded();
    }

    @Then("The information in the first row of the grid will match what was entered")
    public void theInformationInTheFirstRowOfTheGridWillMatchWhatWasEntered() {
        if (commonGrid.gridRecordNumber(currentTab) == 0) {
            System.out.println("No entries in grid match " + headerInfo + " in " + headerChoice + " column");
        } else {
            String result = commonGrid.gridEntry("row 1", headerChoice).getText();
            System.out.println("result is: " + result);
            Assert.assertTrue(commonGrid.gridEntry("row 1", headerChoice).getText().toLowerCase().contains(headerInfo.toLowerCase()),
                    "Grid data does not match. Expected '" + headerInfo + "', found '" + commonGrid.gridEntry("row 1", headerChoice).getText() + "'");
            System.out.println("Entry for " + headerChoice + " header in first row of grid matches " + headerInfo);
        }
    }

    @And("I select that information from the grid header")
    public void iSelectThatInformationFromTheGridHeader() {
        commonGrid.gridHeaderSelectorSelect(headerChoice, headerInfo);
        try {
            wait.until(ExpectedConditions.stalenessOf(commonGrid.gridEntry("row 1", headerChoice)));
        } catch (TimeoutException ignored) {
        }
        BaseUtil.pageLoaded();
    }

    @And("Verify the following headers are present")
    public void verifyTheFollowingHeadersArePresent(List<List<String>> table) {
        List<String> headers = table.get(0);
        BaseUtil.pageLoaded();
        for (String header : headers) {
            Assert.assertTrue(commonGrid.gridHeaderFind(currentTab, header),
                    header + " header not visible in " + currentTab + " grid!");
            System.out.println(header + " header verified on grid");
        }
    }

    @And("I set the {string} field in the {string} header to {string}-{string}-{string}")
    public void iSetTheFieldInTheHeaderTo(String fromTo, String headerName, String day, String month, String year) {
        commonGrid.gridHeaderDateSelect(headerName, fromTo, day, month, year);
    }

    @And("I set the date in the {string} header to the following")
    public void iSetTheDateInTheHeaderToTheFollowing(String header, List<Map<String, String>> table) {
        Map<String, String> selections = table.get(0);
        if (selections.containsKey("Time")) {
            System.out.println("Entering " + selections.get("Month") + "/" + selections.get("Day") + "/" + selections.get("Year") + " " + selections.get("Time") + selections.get("AM / PM") + " into " + selections.get("From / To") + " field of " + header + " header.");
            commonGrid.gridHeaderDateSelect(header, selections);
        } else {
            System.out.println("Entering " + selections.get("Month") + "/" + selections.get("Day") + "/" + selections.get("Year") + " into " + selections.get("From / To") + " field of " + header + " header.");
            commonGrid.gridHeaderDateSelect(header, selections.get("From / To"), selections.get("Day"), selections.get("Month"), selections.get("Year"));
        }

    }

    @And("I set that date in the header")
    public void iSetThatDateInTheHeader() {
        System.out.println("Entering " + headerInfo + " into " + headerChoice + " header");
        String[] date;
        Map<String, String> dateMap = new HashMap<>();
        if (headerInfo.contains("/")) {
            date = headerInfo.split("/");
        } else {
            date = headerInfo.split("-");
        }
        dateMap.put("Month", new DateFormatSymbols().getMonths()[Integer.parseInt(date[0].replaceAll("^0", "")) - 1]);
        dateMap.put("Day", date[1].replaceAll("^0", ""));
        dateMap.put("Year", date[2].replaceAll(" .+", ""));

        if (driver.getCurrentUrl().contains("policychecking")) {
            String[] time = date[2].split(" ");
            dateMap.put("From / To", "From");
            dateMap.put("Time", time[1]);
            dateMap.put("AM / PM", time[2]);
            commonGrid.gridHeaderDateSelect(headerChoice, dateMap);
            dateMap.put("From / To", "To");
            commonGrid.gridHeaderDateSelect(headerChoice, dateMap);

        } else {

            commonGrid.gridHeaderDateSelect(headerChoice, "From", dateMap.get("Day"), dateMap.get("Month"), dateMap.get("Year"));
            commonGrid.gridHeaderDateSelect(headerChoice, "To", dateMap.get("Day"), dateMap.get("Month"), dateMap.get("Year"));
        }


        BaseUtil.pageLoaded();
    }

    @And("set the tab to {string}")
    public void setTheTabTo(String tabName) {
        currentTab = tabName;
    }

    @And("I sort the {string} grid column by {string}")
    public void iSortTheGridColumnBy(String headerName, String sort) {
        commonGrid.gridHeaderSort(headerName, sort);
    }

    @And("The top row background colour is {string}")
    public void iVerifiedTheWorkOrderBackGroundColor(String colourName) {
        // colourName should be one of the words in the Switch statement.
        // Or you can send in a custom colour using the format "RRR,GGG,BBB,A"

        System.out.println("Checking the background colour of the first row.");
        String expectatedColour;
        String actualColour = commonGrid.verifyBackGroundColor(currentTab);
        switch (colourName) {
            case "White":
                expectatedColour = "rgba(249, 249, 249, 1)";
                break;
            case "Rush - Yellow":
                expectatedColour = "rgba(255, 255, 102, 1)";
                break;
            case "AGS Insurance - Green":
                expectatedColour = "rgba(0, 100, 0, 1)";
                break;
            case "donut Account -Light Green":
                expectatedColour = "rgba(153, 204, 51, 1)";
                break;
            case "DueDateToday -Purple":
                expectatedColour = "rgba(206, 84, 200, 1)";
                break;
            case "Cancel -Blue":
                expectatedColour = "rgba(103, 200, 255, 1)";
                break;
            case "SLA -Red":
                expectatedColour = "rgba(238, 67, 67, 1)";
                break;
            default:
                System.out.println("Colour: " + colourName + " was not in the switch statement. Using the manually entered value.");
                expectatedColour = "rgba(" + colourName + ")";
        }
        Assert.assertEquals(actualColour, expectatedColour, "The colour of the first row (" + actualColour + ") did not match the expected colour (" + expectatedColour + ")");
    }

    @Then("There is no data in the grid")
    public void gridIsEmpty() {
        Assert.assertEquals(commonGrid.topRowText(), "No data available in table");
        System.out.println("No data available in table");
    }

    @Then("The number of records in the {string} tab is {string}")
    public void iCheckTheNumberOfRecordsInTheTab(String tabName, String expectation) {
        // expectations should be "increased", "decreased", or "the same"
        System.out.println("Checking the number of records in the " + tabName + " grid.");
        commonGrid.gridTab(tabName);
        gridRecords = commonGrid.gridRecordNumber(tabName);
        try {
            int oldNumber = Integer.parseInt(valueStore.get("numberOfRecords"));
            System.out.println("oldNumber -" + oldNumber);
            if (expectation.equalsIgnoreCase("decreased")) {
                Assert.assertTrue(gridRecords < oldNumber);
            } else if (expectation.equalsIgnoreCase("increased")) {
                Assert.assertTrue(gridRecords > oldNumber);
            } else {
                Assert.assertEquals(gridRecords, oldNumber);
            }
        } catch (NumberFormatException e) {
            System.out.println("NumberFormatException is handled ");
        }
    }

    @And("I click the cross button")
    public void iClickTheCrossButton() {
        System.out.println("Clicking the cross button");
        driver.findElement(By.xpath("//thead/tr[1]/th[3]/span[1]/div[1]/ul[1]/li[1]/div[1]/span[2]/button[1]/i[1]")).click();
    }

    @Then("Verify that text is removed from the field")
    public void verifyThatTextIsRemovedFromTheField() {
        commonGrid.verifySearchFieldEmpty();
    }

    @And("I enter {string} into the search field")
    public void iEnterIntoTheSearchField(String arg0) {
        System.out.println("Entering Initiator into the search field: " + arg0);
        commonGrid.enteringSearchField(arg0);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @And("Verify that items in the dropdown list are displayed")
    public void verifyThatItemsInTheDropdownListAreDisplayed() {
        List<WebElement> drpdwn = driver.findElements(By.xpath("//thead/tr[1]/th[3]/span[1]/div[1]/ul[1]"));
        for (WebElement suggestion : drpdwn) {
            System.out.println("Drop-down list is displayed: " + suggestion.getText());
        }
    }

    @And("I enter {string} into the search field for {string} column field")
    public void iEnterIntoTheSearchFieldForColumnField(String arg0, String arg1) {
        System.out.println("Entering " + arg1 + " into the search field: " + arg0);
        commonGrid.searchingStatus(arg0);
    }

    @And("I click the cross icon")
    public void iClickTheCrossIcon() {
        System.out.println("Clicking the cross Icon");
        driver.findElement(By.xpath("//thead/tr[1]/th[4]/span[1]/div[1]/ul[1]/li[1]/div[1]/span[2]/button[1]/i[1]")).click();
    }

    @And("Verify that items in the Status dropdown list are displayed")
    public void verifyThatItemsInTheStatusDropdownListAreDisplayed() {
        List<WebElement> drpdwn = driver.findElements(By.xpath("//thead/tr[1]/th[4]/span[1]/div[1]/ul[1]"));
        for (WebElement suggestion : drpdwn) {
            System.out.println("Drop-down list is displayed: " + suggestion.getText());
        }
    }

    @And("I clear the {string} field")
    public void iClearTheField(String arg0) {
        driver.findElement(By.xpath("//input[@placeholder='" + arg0 + "']")).clear();
    }

    @And("I click the select. . . button for {string} field")
    public void iClickTheSelectButtonForField(String arg0) {
        driver.findElement(By.xpath("//select[@name='" + arg0 + "']//following::button")).click();
    }

    @And("I enter {string} in the {string} field for Location column field at index {int}")
    public void iEnterInTheFieldForLocationColumnFieldAtIndex(String arg0, String arg1, int arg2) {
        driver.findElements(By.xpath("//input[@placeholder='" + arg1 + "']")).get(arg2).sendKeys(arg0);
        System.out.println("Entering the location " + arg0 + " in the search box");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    @And("I click the cross icon button")
    public void iClickTheCrossIconButton() {
        driver.findElement(By.xpath("//thead/tr[1]/th[6]/span[1]/div[1]/ul[1]/li[1]/div[1]/span[2]/button[1]")).click();
    }

    @Then("Verify that text is removed from the {string} field for Location column field at index {int}")
    public void verifyThatTextIsRemovedFromTheFieldForLocationColumnFieldAtIndex(String arg0, int arg1) {
        WebElement ele = driver.findElements(By.xpath("//input[@placeholder='" + arg0 + "']")).get(arg1);
        String search = ele.getAttribute("value");
        Boolean condition = search.isEmpty();
        Assert.assertTrue(condition, "Search Field is not empty");
        System.out.println("Search field is empty");
    }

    @And("Verify that items in the Location dropdown list are displayed")
    public void verifyThatItemsInTheLocationDropdownListAreDisplayed() {
        List<WebElement> drpdwn = driver.findElements(By.xpath("//thead/tr[1]/th[6]/span[1]/div[1]/ul[1]"));
        for (WebElement suggestion : drpdwn) {
            System.out.println("Drop-down list is displayed: " + suggestion.getText());
        }
    }

    @And("Verify that items in the Department dropdown list are displayed")
    public void verifyThatItemsInTheDepartmentDropdownListAreDisplayed() {
        List<WebElement> drpdwn = driver.findElements(By.xpath("//thead/tr[1]/th[7]/span[1]/div[1]/ul[1]"));
        for (WebElement suggestion : drpdwn) {
            System.out.println("Drop-down list is displayed: " + suggestion.getText());
        }
    }

    @And("I click the cross")
    public void iClickTheCross() {
        driver.findElement(By.xpath("//thead/tr[1]/th[7]/span[1]/div[1]/ul[1]/li[1]/div[1]/span[2]/button[1]")).click();
    }

    @And("I clear the {string} field for {string} column field at index {int}")
    public void iClearTheFieldForColumnFieldAtIndex(String arg0, String arg1, int arg2) {
        driver.findElements(By.xpath("//input[@placeholder='" + arg0 + "']")).get(arg2).clear();
        System.out.println("Search field is clear");
    }

    @And("I enter {string} in the Page box and press the keyboard's enter button")
    public void iEnterInThePageBoxAndPressTheKeyboardSEnterButton(String arg0) {
        WebElement pagebox = driver.findElement(By.xpath("//body/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]"));
        pagebox.sendKeys(Keys.chord(Keys.CONTROL, "a"), arg0, Keys.ENTER);
    }

    @And("I click the Reset button in the grid header")
    public void iClickTheResetButton() {
        System.out.println("Clicking the Reset button to clear all filters");
        String currentRow1 = commonGrid.gridEntry("row 1", "WO #").getText();
        commonGrid.clickResetButton();
        try {
            commonGrid.waitForFilter(currentRow1, "WO #");
        } catch (TimeoutException ignored) {
        }
    }

    @Then("The {string} grid header is {string}")
    public void theGridHeaderIs(String header, String expectation) {
        System.out.println("Checking the " + header + "header contents.");
        if (expectation.contains("<headerInfo>")) { //todo: maybe use a better flag.
            expectation = expectation.replaceAll("<headerInfo>", headerInfo);
        }
        BaseUtil.pageLoaded();
        String actual = commonGrid.gridHeaderField(header, null);
        Assert.assertEquals(actual, expectation, "Header text was not correct. Expected \"" + expectation + "\", but was \"" + actual + "\".");
    }
}
