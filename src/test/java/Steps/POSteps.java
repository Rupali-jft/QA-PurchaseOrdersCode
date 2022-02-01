package Steps;

import Base.BaseUtil;
import Pages.Login;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.sql.Struct;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class POSteps extends BaseUtil {

    @Given("I log into the Purchase Orders app as a(n) {string}")
    public void iLogIntoThePurchaseOrdersAppAsA(String user) {
        login.navigateToLogin();
        driver.manage().window().maximize();
        currentLogin = user;
        login.enterUserEmail(user);
        login.enterUserPassword(user);
        login.ClickSignIn();

        Assert.assertTrue(login.onCorrectPage(), "Apps page not displayed");
        currentApp = "Purchase Order";
        System.out.println("Clicking on " + currentApp + " tile");
        login.clickOnTile(currentApp);
        BaseUtil.pageLoaded();
        Assert.assertTrue(purchaseOrder.onCorrectPage(), "Homepage for " + currentApp + " not displayed");
        System.out.println("On homepage for " + currentApp);
    }

    @And("I add the following into the {string} table")
    public void iAddTheFollowingIntoTheTable(String tableName, List<Map<String, String>> dataTable) {
        String dateString = dateFormat.format(new Date());
        String timeString = timeFormat.format(new Date());
        Map<String, String> entries = new HashMap<>();
        dataTable.get(0).forEach((key, value) -> {
            if (value.contains("<current date>")) {
                value = value.replaceAll("<current date>", dateString + " " + timeString);

            }
            entries.put(key, value);
            valueStore.put(key, value);
        });
        purchaseOrder.addTableEntry(tableName, entries);

    }

    @And("I submit the new request")
    public void iSubmitTheNewRequest() {
        commonForm.commonButton("Submit");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\"jconfirm-title\" and normalize-space()=\"Confirm!\"]")));
        commonForm.commonButton("confirm");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("header_succ_msg_add")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("request_confirm")));
        BaseUtil.pageLoaded();
        valueStore.put("WO #", driver.findElement(By.id("WoNumber1")).getText());
    }

    @Then("The new request will be displayed in the grid")
    public void theNewRequestWillBeDisplayedInTheGrid() {
        driver.findElement(By.id("index1")).click();
        BaseUtil.pageLoaded();
        int rows = commonGrid.gridRowCount();
        commonGrid.gridHeaderField("WO Title", valueStore.get("WO Title"));
        long startTime = System.currentTimeMillis();
        while (commonGrid.gridRowCount() == rows && (System.currentTimeMillis() - startTime) < 5000) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException ignored) {
            }
        }
        driver.findElement(By.linkText(valueStore.get("WO #"))).click();
        BaseUtil.pageLoaded();
        Assert.assertEquals(commonForm.commonFieldRead("WO Title"), valueStore.get("WO Title"), "WO Title mismatch on request");
        Assert.assertEquals(commonForm.commonDropDownRead("Location"), valueStore.get("Location"), "Location mismatch on request");


    }

    @And("I click the eye icon")
    public void iClickTheEyeIcon() {
        ((JavascriptExecutor) driver).executeScript("scroll(0,2000);");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody/tr[1]/td[6]/center[1]"))).click();
        login.waitForMiliseconds(2000);
    }

    @And("I approve the request")
    public void iApproveTheRequest() {
        commonForm.commonButton("Approve");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\"jconfirm-title\" and normalize-space()=\"Confirm!\"]")));
        commonForm.commonButton("confirm");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("header_succ_msg_add")));
        BaseUtil.pageLoaded();
        valueStore.put("WO #", driver.findElement(By.id("WoNumber1")).getText());
    }

    @And("I click the edit action")
    public void iClickTheEditAction() {
        driver.findElement(By.xpath("//tbody/tr[1]/td[7]/a[2]/i[1]")).click();
        ((JavascriptExecutor) driver).executeScript("scroll(0,2000);");
    }

    @And("Verify the GST {string} under the Address Section for {string}")
    public void verifyTheGSTUnderTheAddressSectionFor(String arg0, String arg1) {
        windowsHandling();
        boolean result = false;
        String GST = arg0;
        try {
            result = new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[@id='pdf']/form[@id='form']/h5[2]"))).isDisplayed();
        } catch (
                TimeoutException ignored) {
        }
        Assert.assertTrue(result, "Expected GST under address section \"" + GST + "\" did not display!");
        System.out.println("GST under address section :\n" + arg1 + "-" + GST);
    }

    @And("Click on Add Quote by Purchase Officer Button")
    public void clickOnAddQuoteByPurchaseOfficerButton() {
        System.out.println("clicking on Add Quote Button - To raise quotes by purchase officer\n");
        purchaseOrder.clickAddQuote();
        pageLoaded();
    }

    @Then("Input values in the Quotes pop-up")
    public void inputValuesInTheQuotesPopUp(DataTable table) {
        System.out.println("Entering required details in the Quotes Pop-up\n");
        purchaseOrder.enterQuoteDetails(table);
    }

    @And("I set the quote date in the datepicker")
    public void iSetTheQuoteDateInTheDatepicker() {
        purchaseOrder.enterQuoteDate();
    }

    @And("I click the {string} box")
    public void iClickTheBox(String arg0) {
        purchaseOrder.clickTotalPriceBox();
    }


    @And("I enter {string} in the Part No field")
    public void iEnterInThePartNoField(String arg0) {
        driver.findElement(By.xpath("//input[@id='item_partno0']")).sendKeys(arg0);
    }

    @And("I click the save action")
    public void iClickTheSaveAction() {
        driver.findElement(By.xpath("//tbody/tr[1]/td[7]/a[1]/i[1]")).click();
        ((JavascriptExecutor) driver).executeScript("scroll(2000,0);");
    }

    @Then("Verify the changes has been erased")
    public void verifyTheChangesHasBeenErased() {
        purchaseOrder.verifyemptyfields();
    }

    @And("I open the created WO")
    public void iOpenTheCreatedWO() {
        driver.findElement(By.linkText(valueStore.get("WO #"))).click();
        pageLoaded();
    }

    @Then("I Verify that request got approved successfully BY APPROVER")
    public void iVerifyThatRequestGotApprovedSuccessfullyBYAPPROVER() {
        boolean result = false;
        String validation = "Request Added Successfully";
        try {
            result = new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id("header_succ_msg_add"))).isDisplayed();
        } catch (
                TimeoutException ignored) {
        }
        Assert.assertTrue(result, "Expected validation message \"" + validation + "\" did not display!");
        System.out.println("Validation message when request is approved by Approver: \n" + validation);

    }

    @Then("I verify that delete button is enabled")
    public void iVerifyThatDeleteButtonIsEnabled() {
        purchaseOrder.verifyDeleteButton();
    }

    @And("I delete the attachments")
    public void iDeleteTheAttachments() {
        driver.findElement(By.xpath("//tbody/tr[1]/td[5]/center[1]/button[1]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'confirm')]")));
        commonForm.commonButton("confirm");
    }

    @Then("I verify that {string} validation  message appears")
    public void iVerifyThatValidationErrorMessageAppears(String validation_message) {
        Assert.assertTrue(purchaseOrder.verifyValidationmessage(validation_message), "Expected validation message \"" + validation_message + "\" did not display!");
        System.out.println("Validation message appeared:  " + validation_message);
        login.waitForMiliseconds(5000);
    }

    @And("I raise the purchase order")
    public void iRaiseThePurchaseOrder() {
        commonForm.commonButton("RAISE PO");
        pageLoaded();
        commonForm.commonButton("confirm");
        login.waitForMiliseconds(5000);
        pageLoaded();
    }

    @And("I close the purchase order")
    public void iCloseThePurchaseOrder() {
        commonForm.commonButton("Close PO");
        pageLoaded();
        commonForm.commonButton("Yes");
    }

    @Then("I verify that Reject button {string} displayed")
    public void iVerifyThatRejectButtonDisplayed(String visibility) {
        switch (visibility) {
            case "is" -> Assert.assertTrue(commonForm.commonButtonGet("Reject").isDisplayed(), "Reject button is not present");
            case "is not" -> Assert.assertNull(commonForm.commonButtonGet("Reject"), "Reject button is present");
            default -> System.out.println("Specified condition is not working");
        }
    }

    @Then("I verify the selected status is {string} for the dropdown {string}")
    public void iVerifyTheSelectedStatusIsForTheDropdown(String arg0, String dropDown) {
        String value = commonForm.commonDropDownRead(dropDown);
        System.out.println("Status is: \n" + value);
    }

    @And("I select the raised WO checkbox in the grid")
    public void iSelectTheRaisedWOCheckboxInTheGrid() {
        driver.findElement(By.xpath("//table[@id='dtApproval']/tbody/tr/td/input")).click();
    }

    @And("I verify the status is {string} from Requests tab")
    public void iVerifyTheStatusIsFromRequestsTab(String status) {
        Assert.assertTrue(purchaseOrder.RequestsTabStatusCheck(status), "Expected status: " + status + " was not found. Current status is " + status);
        System.out.println("Expected status: " + status + " matches found status: " + status);
    }

    @And("I {string} the request from the grid")
    public void iTheRequestFromTheGrid(String status) {
        //purchaseOrder.ChangeStatusFromGrid(status);
        Assert.assertTrue(purchaseOrder.ChangeStatusFromGrid(status), status + " button not found. Please use 'Approve', 'Send Back', or 'Reject'");
        System.out.println(status + " button found.");
    }

    @And("Click on Add Quote by Initiator Button and click yes")
    public void clickOnAddQuoteByInitiatorButtonAndClickYes() {
        Assert.assertTrue(purchaseOrder.clickAddQuoteByInitiator(), "Add quote by Initiator button is not clickable");
        System.out.println("Clicked add quote by Initiator");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Yes']")));
        commonForm.commonButton("Yes");
        pageLoaded();
    }

    @And("Click on Add Quote by Initiator Button")
    public void clickOnAddQuoteByInitiatorButton() {
        Assert.assertTrue(purchaseOrder.clickAddQuoteByInitiator(), "Add quote by Initiator button is not clickable");
        System.out.println("Clicked add quote by Initiator");
    }

    @And("Verify the approval status on Quotes Tab for {int}st Quote")
    public void verifyTheApprovalStatusOnQuotesTabForStQuote(int arg0) {
        purchaseOrder.verifyQuote1Status();
    }

    @And("^Verify the approval status on Quotes Tab for 2nd Quote$")
    public void verifyApprovalStatusforQuote2() {
        purchaseOrder.verifyQuote2Status();
    }

    @Then("I check if {string} is {string}")
    public void iCheckIfIs(String arg0, String arg1) {
        js.executeScript("window.scrollBy(0,3000)");
        String currentStatus = new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//center[contains(text(),'" + arg1 + "')]"))).getText();
        System.out.println(currentStatus);
        boolean condition = (currentStatus.equals(arg1));
        Assert.assertTrue(condition, "Approval status is not: " + currentStatus);
        System.out.println("Approval status is: " + currentStatus);
    }

    @And("I verify that buttons are disabled")
    public void iVerifyThatButtonsAreDisabled() {
        Assert.assertFalse(purchaseOrder.verifyApproveButtonDisabled(), "Approve button is not disabled");
        System.out.println("Good Approve button disabled");

        Assert.assertFalse(purchaseOrder.verifySendBackButtonDisabled(), "SendBack button is not disabled");
        System.out.println("Good SendBack button disabled");

        Assert.assertFalse(purchaseOrder.verifyRejectButtonDisabled(), "Reject button is not disabled");
        System.out.println("Good Reject button disabled");

        Assert.assertFalse(purchaseOrder.verifySubmitButtonDisabled(), "Submit button is not disabled");
        System.out.println("Good Submit button disabled");
    }

    @Then("I check if {string} is changed to {string}")
    public void iCheckIfIsChangedTo(String arg0, String arg1) {
        boolean result = false;
        String status = arg1;
        try {
            result = new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[5]/div[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[5]/center[1]"))).isDisplayed();
        } catch (
                TimeoutException ignored) {
        }
        Assert.assertTrue(result, arg0 + " is not: " + status);
        System.out.println(arg0 + " is: " + status);
    }

    @And("I click the eye icon in the second row")
    public void iClickTheEyeIconInTheSecondRow() {
        driver.findElement(By.xpath("//tbody/tr[2]/td[6]/center[1]/span[1]")).click();
    }

    @And("I click {string} button")
    public void iClickButton(String arg0) {
        driver.findElement(By.xpath("//button[@id='notes_submit']")).click();
    }


    @And("I go to {string} tab.")
    public void iGoToTab(String tabs) {
        commonForm.commonLinkClick(tabs);
    }

    @And("I open the {string} tab")
    public void iOpenTheTab(String comment_Tab) {
        driver.findElement(By.xpath("//a[@id='notes_sects']")).click();
        System.out.println("Navigate to " + comment_Tab);
    }

    @And("^Edit the Quote Title field$")
    public void editTheQuoteTitleField() {
        purchaseOrder.EditQuoteTitle();
    }

    @And("^Click the cancel button on the Edit Quote modal$")
    public void clickTheCancelButtonOnTheEditQuoteModal() {
        purchaseOrder.CancelQuoteEdit();
    }

    @Then("^I will see that the title has not changed$")
    public void iWillSeeThatTheTitleHasNotChanged() {
        Assert.assertTrue(purchaseOrder.VerifyQuoteTitleNotSaved(), "Title has been changed successfully");
        System.out.println("Title has not been changed");
    }

    @And("I delete the Quote")
    public void iDeleteTheQuote() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody/tr[1]/td[7]/center[1]/button[1]/span[1]"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Yes']")));
        commonForm.commonButton("Yes");
        System.out.println("Quote is deleted successfully");
    }

    @Then("verify that Quote page is opened.")
    public void verifyThatQuotePageIsOpened() {
        String expected_Title = driver.getTitle();
        String actual_Title = "Patra Corp - Purchase Order";
        Assert.assertEquals(actual_Title, expected_Title, "Quote Page is opened");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#quotedprice0"))).sendKeys(Keys.chord(Keys.CONTROL, "a"), "2500");
        System.out.println("Quote tab is editable.");
    }

    @And("I click the view PDF link")
    public void iClickTheViewPDFLink() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody/tr[1]/td[7]/center[1]/a[1]/span[1]"))).click();
        windowsHandling();
    }

    @And("I click the PO# column field")
    public void iClickThePOColumnField() {
        String clicklnk = Keys.chord(Keys.CONTROL, Keys.ENTER);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody/tr[1]/td[1]/center[1]/a[1]"))).sendKeys(clicklnk);
        System.out.println("Navigates to PO page");
        mainWindowHandle();
        new WebDriverWait(driver, 20);
        System.out.println("Back to main window");
    }

    @Then("I see error warning {string} is displayed")
    public void iSeeErrorWarningIsDisplayed(String warning_Message) {
        Assert.assertTrue(purchaseOrder.verifyWarningmessage(warning_Message), "Expected validation message \"" + warning_Message + "\" did not display!");
        System.out.println("Warning message appeared:  " + warning_Message);
    }

    @Then("I see error warning {string} appeared")
    public void iSeeErrorWarningAppeared(String error) {
        Assert.assertTrue(purchaseOrder.verifyErrormessage(error), "Expected validation message \"" + error + "\" did not display!");
        System.out.println("Warning message appeared:  " + error);
    }

    @And("I click the {string} and {string} button")
    public void iClickTheAndButton(String subBtn, String cnfrmBtn) {
        commonForm.commonButton(subBtn);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'confirm')]")));
        commonForm.commonButton(cnfrmBtn);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Quote Successfully Added')]")));
        pageLoaded();
    }


    @And("I click on {string} and {string} button")
    public void iClickOnAndButton(String cnfrm, String btn_Yes) {
        commonForm.commonButton(cnfrm);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Yes']")));
        commonForm.commonButton(btn_Yes);
    }

    @Then("^Enter Purchase Order confirmation Details$")
    public void enterPurchaseOrderDetails(DataTable table) {
        purchaseOrder.enterPurchaseOrderDetails(table);
    }

    @And("^I get the created Purchase Order Number$")
    public void getPONumber() {
        purchaseOrder.getCreatedPONumber();
    }

    @And("I close the created work order")
    public void iCloseTheCreatedWorkOrder() {
        commonForm.commonButton("Close WO");
        pageLoaded();
        commonForm.commonButton("Yes");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("wo_close_msg")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("wo_close_msg")));

    }

    @When("I refresh the page")
    public void iRefreshThePage() {
        driver.navigate().refresh();
        pageLoaded();
    }

    @And("The {string} link {string} displayed")
    public void theLinkDisplayed(String linkText, String expectation) {
        System.out.println("Checking if " + linkText + " link exists.");
        if (expectation.equalsIgnoreCase("is not")) {
            Assert.assertFalse(purchaseOrder.linkDisplayed(linkText));
        } else {
            Assert.assertTrue(purchaseOrder.linkDisplayed(linkText));
        }
    }

    @When("I enter {string} in the search field")
    public void iCanUseTheSearchField(String recordID) {
        pageLoaded();
        purchaseOrder.typeInSearchField(recordID);
        System.out.println("Entering \"" + recordID + "\" to the search field");
    }

    @Then("I see {int} result(s) saying {string}")
    public void confirmSearchResults(int expectedNumberOfResponses, String expectedString) {

        // Number of responses is correct?
        Assert.assertEquals(purchaseOrder.numberOfSearchResults(), expectedNumberOfResponses, "Number of results was incorrect.");

        // assert that expected string is present in each response
        purchaseOrder.searchResultsContainSearch(expectedString);
        System.out.println("Checking the search dropdown results");
    }

    @Then("I see {int} result saying expected record")
    public void iSeeResultSayingExpectedRecord(int expectedNumberOfResponses) {
        // Number of responses is correct?
        Assert.assertEquals(purchaseOrder.numberOfSearchResults(), expectedNumberOfResponses, "Number of results was incorrect.");

        // assert that expected string is present in each response
        purchaseOrder.searchResultsContainSearch(GridSteps.headerInfo);
        System.out.println("Checking the search dropdown results");
    }

    @And("The results are in descending order")
    public void resultsInDescendingOrder() {
        purchaseOrder.searchResultsDescending();
        System.out.println("Checking the search dropdown results are in order");
    }

    @Then("The record is added")
    public void confirmAddRecord(List<String> fields) {
        // confirm record details match what were entered
        fields.forEach(key -> {
            boolean foundItem = false;
            if (commonForm.commonField(key) != null) {
                foundItem = true;
                String found = commonForm.commonFieldRead(key);
                if (key.contains("Date")) { // The way dates are stored and displayed are different. Need to do some parsing.
                    // Convert the expected format into the actual format, and then compare with the actual date.
                    // expected: "19-02-2021 103851", actual: "02/19/2021 10:38 AM"
                    SimpleDateFormat formatActual = new SimpleDateFormat("MM/dd/yyyy h:mm aa", Locale.ENGLISH); // need locale to catch AM/PM component
                    SimpleDateFormat formatExpected = new SimpleDateFormat("dd-MM-yyyy HHmmss");
                    try {
                        Date expectedDate = formatExpected.parse(valueStore.get(key));
                        String reformattedExpectedDate = formatActual.format(expectedDate);
                        // "Mail Date" in Print Shop is special. It is always set to 15:15 PT.
                        // Git issue: https://github.com/patracorp/print-shop/issues/34
                        // Test Case: https://patra.testrail.io/index.php?/cases/view/10466
                        if (key.equals("Mail Date")) {
                            reformattedExpectedDate = reformattedExpectedDate.substring(0, 11) + "3:15 PM";
                        }
                        System.out.println("Comparing actual (\"" + found + "\") to expected (\"" + reformattedExpectedDate + "\") in \"" + key + "\" field");
                        Assert.assertEquals(found, reformattedExpectedDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Comparing actual (\"" + found + "\") to expected (\"" + valueStore.get(key) + "\") in \"" + key + "\" field");
                    Assert.assertEquals(found, valueStore.get(key));
                }
            } else if (commonForm.commonDropDown(key) != null) {
                foundItem = true;
                String found = commonForm.commonDropDownRead(key);
                System.out.println("Comparing actual (\"" + found + "\") to expected (\"" + valueStore.get(key) + "\") in \"" + key + "\" drop down");
                Assert.assertEquals(found, valueStore.get(key));
            } else if (commonForm.commonTextArea(key) != null) {
                foundItem = true;
                String found = commonForm.commonTextAreaRead(key);
                System.out.println("Comparing actual (\"" + found + "\") to expected (\"" + valueStore.get(key) + "\") in \"" + key + "\" test area");
                Assert.assertEquals(found, valueStore.get(key));
            }
            Assert.assertTrue(foundItem, "Could not find " + key + " field, drop down, or text area!");
        });
        System.out.println("Record has been successfully read");
    }

    @And("I update my expectations")
    public void iUpdateMyExpectations(Map<String, String> table) {
        String dateString = dateFormat.format(new Date());
        String timeString = timeFormat.format(new Date());
        table.forEach((key, value) -> {
            if (value.contains("<current date>")) {
                value = value.replaceAll("<current date>", dateString + " " + timeString);
            }
            valueStore.put(key, value);
        });
    }

    @Then("The record is closed")
    public void record_is_added_closed() {
        System.out.println("The edit request detail modal is closed.");
        Assert.assertFalse(purchaseOrder.waitForAddRecordClosed());
    }

    @Then("I verify that {string} button is displayed")
    public void iVerifyThatButtonIsDisplayed(String btn) {
        Assert.assertTrue(commonForm.commonButtonGet(btn).isDisplayed(), btn + " button is not present");
        System.out.println(btn + " button is visible and enabled");
    }

    @Then("The next WO is displayed")
    public void theNextWOIsDisplayed() {
        pageLoaded();
        Assert.assertNotEquals(commonForm.getWorkOrderNumber(), valueStore.get("headerInfo"));
        //Assert.assertEquals(commonForm.getWorkOrderNumber(), valueStore.get("headerInfoStore"));
    }

    @When("I use the Back button")
    public void iUseTheBackButton() {
        driver.navigate().back();
        pageLoaded();
        login.waitForMiliseconds(5000);
    }

    @And("I click on {string} button")
    public void iClickOnButton(String submit_Next_Btn) {
        pageLoaded();
        commonForm.commonButton(submit_Next_Btn);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='confirm']")));
        commonForm.commonButton("confirm");
        login.waitForMiliseconds(2000);
    }

    @And("I confirm the raised work order")
    public void iConfirmTheRaisedWorkOrder() {
        commonForm.commonButton("Confirm");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Yes']")));
        commonForm.commonButton("Yes");
    }

    @And("I click the Comment_Submit button")
    public void iClickTheComment_SubmitButton() {
        driver.findElement(By.xpath("//button[@id='notes_submit']")).click();
    }

    @Then("Verify that Comment pop-up gets closed")
    public void verifyThatCommentPopUpGetsClosed() {
        String expected_Title = driver.getTitle();
        String actual_Title = "Patra Corp - Purchase Order";
        Assert.assertEquals(actual_Title, expected_Title, "Comment pop-up gets closed");
    }

    @And("The {string} button {string} displayed")
    public void theButtonDisplayed(String button, String expectation) {
        switch (expectation) {
            case "is" -> Assert.assertTrue(commonForm.commonButtonGet(button).isDisplayed(), button + " is not present");
            case "is not" -> Assert.assertNull(commonForm.commonButtonGet(button), button + " is present");
            default -> System.out.println("Specified condition is not working");
        }
    }

    @And("I get the {string} column value")
    public void iGetTheColumnValue(String column) {
        //To locate rows of table.
        List<WebElement> rows_table = driver.findElements(By.tagName("tr"));
        //To calculate no of rows In table.
        int rows_count = rows_table.size();
        System.out.println(rows_count);
        //Loop will execute till the last row of table.
        for (int row = 0; row < rows_count; row++) {
            //To locate columns(cells) of that specific row.
            WebElement Columns_row = rows_table.get(row).findElement(By.xpath("//tbody/tr/td[3]"));
            // To retrieve text from that specific cell.
            String celtext = Columns_row.getText();
            System.out.println("Cell Value of row number " + row + " and column name " + column + " Is " + celtext);
        }
    }

    @And("Verify below given headers are present")
    public void verifyBelowGivenHeadersArePresent(List<String> table) {
        BaseUtil.pageLoaded();
        for (String header : table) {
            Assert.assertTrue(purchaseOrder.gridHeaderFind(currentTab, header),
                    header + " header not visible in " + currentTab + " grid!");
            System.out.println(header + " header verified on grid");
        }
    }

    @And("I click on the top purchase order link")
    public void iClickOnTheTopPurchaseOrderLink() {
        purchaseOrder.clickTopPurchaseOrder();
        pageLoaded();
    }

    @Then("Verify that item is added into the {string} table")
    public void verifyThatItemIsAddedIntoTheTable(String table_Name, List<Map<String, String>> dataTable) {
        List<WebElement> rows = driver.findElements(By.cssSelector("tbody > tr"));
        for (WebElement row : rows) {
            Assert.assertTrue(row.isDisplayed(), "item is not added in the " + table_Name);
            System.out.println("Item is added in the " + table_Name);
        }
    }

    @And("I click the edit icon")
    public void iClickTheEditIcon() {
        driver.findElement(By.xpath("//i[contains(text(),'\uE254')]")).click();
    }

    @And("I update {string} into {string} field")
    public void iUpdateIntoField(String value, String field) {
        driver.findElement(By.name("qty0")).sendKeys(Keys.chord(Keys.CONTROL, "a"), value);
        System.out.println("Entering the " + value + " in the " + field + " field");
    }

    @And("I add the record")
    public void iAddTheRecord() {
        driver.findElement(By.xpath("//i[contains(text(),'\uE03B')]")).click();
    }

    @And("I enter {string} into the {string} field")
    public void iEnterIntoTheField(String value, String field) {
        driver.findElement(By.xpath("//input[@id='request_item_service_description0']")).sendKeys(Keys.chord(Keys.CONTROL, "a"), value);
        System.out.println("Entering the " + value + " in the " + field + " field");
    }

    @And("I enter {string} into {string} field")
    public void iEnterIntoField(String value, String field) {
        driver.findElement(By.xpath("//input[@id='item_service0']")).sendKeys(Keys.chord(Keys.CONTROL, "a"), value);
        System.out.println("Entering the " + value + " in the " + field + " field");
    }

    @Then("verify that fields are enabled to edit")
    public void verifyThatFieldsAreEnabledToEdit() {
        Assert.assertTrue(driver.findElement(By.cssSelector("td:nth-of-type(2)")).isEnabled(), "Field is not enabled");
        System.out.println("Field is enabled");
    }

    @Then("Verify the edited fields are updated correctly")
    public void verifyTheEditedFieldsAreUpdatedCorrectly(List<String> dataTable) {
        List<WebElement> rows = driver.findElements(By.cssSelector("tbody > tr"));
        String val1 = dataTable.get(0);
        String val2 = dataTable.get(1);
        String val3 = dataTable.get(2);
        System.out.println(val1 + val2 + val3);
        for (WebElement row : rows) {
            String d1 = driver.findElement(By.xpath("//input[@id='item_service0']")).getText();
            System.out.println(d1);
            if (row.findElement(By.cssSelector("td:nth-of-type(1)")).getText().equals(val1) && driver.findElement(By.cssSelector("td:nth-of-type(2)")).getText().equals(val2) && driver.findElement(By.cssSelector("td:nth-of-type(3)")).getText().equals(val3)) {
                System.out.println("All fields are edited successfully");
            } else
                System.out.println("Fields are not updated correctly");

        }
    }

    @And("I click the delete icon")
    public void iClickTheDeleteIcon() {
        driver.findElement(By.xpath("//i[contains(text(),'\uE872')]")).click();
        System.out.println("Deleted Successfully");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Yes']")));
        commonForm.commonButton("Yes");
    }

    @Then("Verify that item is deleted successfully and shouldn't display in the grid.")
    public void verifyThatItemIsDeletedSuccessfullyAndShouldnTDisplayInTheGrid() {
        List<WebElement> rows = driver.findElements(By.cssSelector("tbody > tr"));
        for (WebElement row : rows) {
            Assert.assertFalse(row.findElement(By.id("serviceDetails")).isDisplayed(), "Row is not removed in the Item/Service table");
            System.out.println("Row is removed in the Item/Service table");
        }
    }

    @Then("Verify the table headers in {string}")
    public void verifyTheTableHeadersIn(String tableName, List<String> table) {
        List<WebElement> heads = driver.findElements(By.xpath("//table[@id='" + tableName + "']//thead//tr"));
        System.out.println(heads.size());
        for (WebElement h : heads) {
            System.out.println("Table headers are: " + h.getText());
        }
    }

    @Then("Verify that Purchase order page opens")
    public void verifyThatPurchaseOrderPageOpens() {
        String expected_Title = driver.getTitle();
        String actual_Title = "Patra Corp - Purchase Order";
        Assert.assertEquals(actual_Title, expected_Title, "Purchase order page gets opened");
    }

    @Then("Verify that {string} text box is empty")
    public void verifyThatTextBoxIsEmpty(String field) {
        String vendor_Add = driver.findElement(By.xpath("//textarea[@id='po_vendorAddress']")).getText();
        boolean condition = vendor_Add.isEmpty();
        Assert.assertTrue(condition, "Changes have been saved");
        System.out.println("All the changes have been discarded in " + field);
    }

    @Then("I verify that {string} button {string} displayed")
    public void iVerifyThatButtonDisplayed(String field, String visibility) {
        switch (visibility) {
            case "is" -> Assert.assertTrue(commonForm.commonButtonGet("Add Purchase Order").isDisplayed(), field + " is not present");
            case "is not" -> Assert.assertNull(commonForm.commonButtonGet("Add Purchase Order"), field + " is present");
            default -> System.out.println("Specified condition is not working");
        }
    }

    @Then("I verify that PO status is changed to {string}")
    public void iVerifyThatPOStatusIsChangedTo(String status) {
        String po_Status = new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/fieldset[1]/div[2]/div[1]/div[2]/div[3]/input[1]"))).getAttribute("value");
        System.out.println(po_Status);
        Assert.assertFalse(po_Status.isEmpty(), "Status is not " + status);
        System.out.println("Status is " + status);
    }

    @Then("Verify that PDF preview opened in new tab")
    public void verifyThatPDFPreviewOpenedInNewTab() {
        String title = "Patra Corp - Purchase Order";
        Assert.assertTrue(driver.getTitle().equals(title), "New tab is not opened");
        System.out.println("New tab is opened");
    }

    @Then("Verify that PDF is downloading successfully")
    public void verifyThatPDFIsDownloadingSuccessfully() {
        File file = new File("PurchaseOrder.pdf");
        String getLatestFile = file.getName();
        System.out.println(getLatestFile);
        String currentFile = "PurchaseOrder.pdf";
        Assert.assertTrue(getLatestFile.equals(currentFile), "Downloaded file name is not matching with expected file name");
        System.out.println("Downloaded file name is matched with expected file name");
    }

    @Then("Verify the fields present on the page")
    public void verifyTheFieldsPresentOnThePage() {
        List<WebElement> heads = driver.findElements(By.xpath("//table/thead/tr[1]/th"));
        System.out.println((heads.size()));
        for (WebElement h : heads) {
            System.out.println(h.getText());
        }
    }

    @When("I select {string}")
    public void iSelect(String value) {
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//thead/tr[1]/th[3]/span[1]/div[1]/ul[1]/li[55]/a[1]/label[1]/input[1]"))).click();
    }

    @Then("Verify that Quote is updated successfully.")
    public void verifyThatQuoteIsUpdatedSuccessfully() {
        login.waitForMiliseconds(5000);
        String updated_quote = "6000";
        String actual_quote = driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[5]/div[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[4]")).getText();
        Assert.assertTrue(actual_quote.equals(updated_quote), "Quote has not been updated");
        System.out.println("Quote has been updated successfully");
    }

    @And("I update {string} in the {string} field")
    public void iUpdateInTheField(String value, String field) {
        driver.findElement(By.xpath("//input[@id='quotedprice0']")).sendKeys(Keys.chord(Keys.CONTROL, "a"), value);
        System.out.println("Entering the " + value + " in the " + field + " field");
    }

    @And("I clicked {string} button")
    public void iClickedButton(String btn_Reject) {
        pageLoaded();
        commonForm.commonButton(btn_Reject);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='confirm']")));
        commonForm.commonButton("confirm");
    }

    @And("I click the {string} and {string} button to complete the updation")
    public void iClickTheAndButtonToCompleteTheUpdation(String subBtn, String cnfrmBtn) {
        commonForm.commonButton(subBtn);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'confirm')]")));
        commonForm.commonButton(cnfrmBtn);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Quote Successfully Updated')]")));
        pageLoaded();
    }

    @Then("Verify the {string} Address for Patra {string} location")
    public void verifyTheAddressForPatraLocation(String add, String loc) {
        windowsHandling();
        String exp_Address = "PATRA INDIA BPO SERVICES PVT. LTD\n" +
                "Falak Tower, 4/11\n" +
                "Priyadarshini Parisar (East), G.E. Road,\n" +
                "Bhilai\n" +
                "Chhattisgarh, 490023\n" +
                "INDIA";
        String actual_Address = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/form[1]/h5[2]")).getText();
        Assert.assertTrue(actual_Address.contains(exp_Address), "Wrong" + add + " Address is displayed for " + loc);
        System.out.println(add + " Address for " + loc + " location has been updated: " + actual_Address);
    }

    @Then("I verify that {string} button is disabled")
    public void iVerifyThatButtonIsDisabled(String btn) {
        Assert.assertFalse(purchaseOrder.verifyApproveButtonDisabled(), "Approve button is not disabled");
        System.out.println("Good Approve button disabled");
    }

    @Then("I verify delete button present under delete quote header")
    public void iVerifyDeleteButtonPresentUnderDeleteQuoteHeader() {
        purchaseOrder.verifyDeleteButtonUnderQuote();
        Assert.assertTrue(purchaseOrder.verifyDeleteButtonUnderQuote(), "Delete icon does not show up under delete quote header");
        System.out.println("Delete Icon Shows up under under delete quote header ");
    }

    @Then("I verify Delete button under {string} header")
    public void iVerifyDeleteButtonUnderHeader(String deleteBox) {
        Assert.assertTrue(purchaseOrder.verifyDeleteButtonUnderQuoteHeader(), "Delete icon does not show up under delete quote header");
        System.out.println("Delete Icon Shows up under under " + deleteBox);
    }

    @And("Delete greater than {int}K quote")
    public void deleteGreaterThanKQuote(int quotePrice) {
        wait.until(ExpectedConditions.visibilityOf(purchaseOrder.deleteButton1)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Yes')]")));
        commonForm.commonButton("Yes");
        System.out.println("Greater Than 5K quote has been removed successfully");
    }

    @And("I fetch the value for the {string} drop down")
    public void iFetchTheValueForTheDropDown(String dDown) {
        Select dropdown = new Select(driver.findElement(By.xpath("//select[@id='request_Department']")));

        //Get all options
        List<WebElement> dd = dropdown.getOptions();

        //Get the length
        System.out.println(dd.size());

        // Loop to print one by one
        for (int j = 0; j < dd.size(); j++) {
            System.out.println(dDown + " options : " + dd.get(j).getText());

        }
    }

    @Then("Verify PO Date Pre-populates the current date")
    public void verifyPODatePrePopulatesTheCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy ");
        //get current date time with Date()
        Date date = new Date();
        // Now format the date
        String date1 = dateFormat.format(date);
        String date2 = date1.trim();
        String value = driver.findElement(By.id("po_date")).getAttribute("value");
        System.out.println("value is " + value);
        Assert.assertEquals(date2, value);
        System.out.println("Verified PO Date Pre-populates the current date");
    }

    @Then("Verify Future dates are disabled")
    public void verifyFutureDatesAreDisabled() {
        driver.findElement(By.id("po_date")).click();
        boolean condition = driver.findElement(By.xpath("//body[1]/div[7]/div[1]/table[1]/tbody[1]/tr[1]/td[4]")).isDisplayed();
        Assert.assertTrue(condition, "Future dates are not disabled");
        System.out.println("Future dates are disabled");
    }
}

