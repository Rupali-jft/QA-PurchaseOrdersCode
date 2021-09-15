package Steps;

import Base.BaseUtil;
import Pages.Login;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

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
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("po_succ_msg")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("po_succ_msg")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("po_close")));
        pageLoaded();
    }

    @And("I close the purchase order")
    public void iCloseThePurchaseOrder() {
        commonForm.commonButton("Close PO");
        pageLoaded();
        commonForm.commonButton("Yes");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("po_close_msg")));
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
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("")));
        commonForm.commonButton(cnfrmBtn);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Quote Successfully Added')]")));
        pageLoaded();
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
        System.out.println(btn + " button is visible");
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
        login.waitForMiliseconds(10000);
    }

    @And("I click on {string} button")
    public void iClickOnButton(String submit_Next_Btn) {
        commonForm.commonButton(submit_Next_Btn);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='confirm']")));
        commonForm.commonButton("confirm");
        login.waitForMiliseconds(15000);
    }

    @And("I confirm the raised work order")
    public void iConfirmTheRaisedWorkOrder() {
        commonForm.commonButton("Confirm");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Yes']")));
        commonForm.commonButton("Yes");
    }
}

