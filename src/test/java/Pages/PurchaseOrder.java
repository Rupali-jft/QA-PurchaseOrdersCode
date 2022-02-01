package Pages;

import Base.BaseUtil;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PurchaseOrder {
    private final WebDriver driver;
    private final JavascriptExecutor js;
    private static String passingstringvalue = "";
    private final WebDriverWait wait;


    public PurchaseOrder(WebDriver driver, JavascriptExecutor js) {
        this.driver = driver;
        this.js = js;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(this.driver, 15);


    }


    @FindBy(xpath = "//*[@id=\"index-root\"]/span[normalize-space() = 'Purchase Order']")
    private WebElement pageName;
    @FindBy(id = "addPORequest")
    private WebElement addRequestBtn;

    // Purchase Officer role only elements
    @FindBy(how = How.XPATH, using = "//button[contains(.,'Add Quote')]")
    private WebElement addQuoteBtn;

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[1]/div/div[1]/div/div[1]/div/ul/li[3]/a")
    private WebElement clickOnByPurchaseOfficer;

    @FindBy(how = How.XPATH, using = "//input[@id='quote_date']")
    private WebElement quoDate;
    @FindBy(how = How.XPATH, using = "/html[1]/body[1]/div[8]/div[1]/table[1]/tbody[1]/tr[4]/td[5]")
    private WebElement quotedateselection;
    @FindBy(how = How.XPATH, using = "//form[@id='Add_Quotes']/div[2]/div/div[2]/input")
    private WebElement quoTitle;
    @FindBy(how = How.XPATH, using = "//input[@id='quote_vendor']")
    private WebElement quoVendor;
    @FindBy(how = How.XPATH, using = "//input[@id='quotedprice0']")
    private WebElement quoPrice;
    @FindBy(how = How.XPATH, using = "//input[@id='total_price0']")
    private WebElement totPrice;
    @FindBy(how = How.XPATH, using = "//input[@id='request_title']")
    private WebElement woTitle;
    @FindBy(how = How.XPATH, using = "//select[@id='request_location']")
    private WebElement location;
    @FindBy(how = How.XPATH, using = "//select[@id='request_Department']")
    private WebElement department;
    @FindBy(how = How.XPATH, using = "//tbody/tr[1]/td[5]/center[1]/button[1]")
    private WebElement deleteBtn;
    @FindBy(how = How.XPATH, using = "//tbody/tr[1]/td[7]/center[1]/button[1]")
    private WebElement deleteButton;
    @FindBy(how = How.XPATH, using = "//tbody/tr[2]/td[7]/center[1]/button[1]")
    public WebElement deleteButton1;
    @FindBy(how = How.XPATH, using = "//button[@id='quotes_approve']")
    private WebElement approveBtn;
    @FindBy(how = How.XPATH, using = "//button[@id='quotes_send_back']")
    private WebElement sendbackBtn;
    @FindBy(how = How.XPATH, using = "//button[@id='quotes_reject']")
    private WebElement rejectQuoteBtn;
    @FindBy(how = How.XPATH, using = "//button[@id='quotes_submit']")
    private WebElement submitBtn;
    @FindBy(how = How.XPATH, using = "//*[@id=\"dtRequests\"]/tbody/tr/td[4]")
    private WebElement gridRequestsStatus;
    @FindBy(how = How.XPATH, using = "//table[@id='dtApproval']/tbody/tr/td/input")
    private WebElement gridPendingApprovalCheckBox;
    @FindBy(how = How.ID, using = "btnApprove")
    private WebElement gridApproveBtn;
    @FindBy(how = How.XPATH, using = "//*[@id=\"btnApprove\"][2]")
    private WebElement gridSendBackBtn;
    @FindBy(how = How.XPATH, using = "//*[@id=\"btnApprove\"][3]")
    private WebElement gridRejectBtn;
    @FindBy(how = How.XPATH, using = "//a[normalize-space()='By Initiator']")
    private WebElement clickOnByInitiator;

    public boolean onCorrectPage() {
        try {
            return pageName.isDisplayed();
        } catch (NoSuchElementException ignored) {

        }
        return false;
    }

    public void addTableEntry(String tableName, Map<String, String> entries) {
        WebElement table = driver.findElement(By.xpath("//div[normalize-space()=\"" + tableName + "\"]/ancestor::div[@class=\"table-title\"]/following-sibling::table"));
        int rows = table.findElements(By.xpath("tbody/tr")).size();
        System.out.println("Size of rows: " + rows);
        driver.findElement(By.xpath("//*[@id=\"WorkOrderDetails\"]/div[2]/div/div/div[2]/button")).click();
        BaseUtil.pageLoaded();
        entries.forEach((key, value) -> {
            String header = generateXPATH(table.findElement(By.xpath("thead/tr/th[normalize-space()=\"" + key + "\"]")), "");
            Pattern hdrPat = Pattern.compile("(?<=th\\[)(\\d+)(?=\\])");
            Matcher hdrM = hdrPat.matcher(header);
            while (hdrM.find()) {
                header = hdrM.group();
            }
            System.out.println("Entering \"" + value + "\" into " + key + " column of " + tableName + " table");
            table.findElement(By.xpath("tbody/tr[" + (rows + 1) + "]/td[" + header + "]/input")).sendKeys(value);

        });
        table.findElement(By.xpath("tbody/tr[1]/td[4]/a[@class=\"add\"]")).click();


    }

    public String generateXPATH(WebElement childElement, String current) {
        String childTag = childElement.getTagName();
        if (childTag.equals("html")) {
            return "/html[1]" + current;
        }
        WebElement parentElement = childElement.findElement(By.xpath(".."));
        List<WebElement> childrenElements = parentElement.findElements(By.xpath("*"));
        int count = 0;
        for (int i = 0; i < childrenElements.size(); i++) {
            WebElement childrenElement = childrenElements.get(i);
            String childrenElementTag = childrenElement.getTagName();
            if (childTag.equals(childrenElementTag)) {
                count++;
            }
            if (childElement.equals(childrenElement)) {
                return generateXPATH(parentElement, "/" + childTag + "[" + count + "]" + current);
            }
        }
        return null;
    }

    // To add quotes by purchase officer
    public void clickAddQuote() {
        try {
            addQuoteBtn.click();
            clickOnByPurchaseOfficer.click();
        } catch (Exception e) {
        }
    }

    // To add quotes by Initiator
    public boolean clickAddQuoteByInitiator() {
        if (addQuoteBtn.isDisplayed() && addQuoteBtn.isEnabled()) {
            try {
                js.executeScript("arguments[0].click()", addQuoteBtn);
            } catch (ElementClickInterceptedException e) {
                BaseUtil.commonForm.clickErrorHandle(e.toString(), addQuoteBtn);
            }
            if (clickOnByInitiator.isDisplayed() && clickOnByInitiator.isEnabled()) {
                try {
                    js.executeScript("arguments[0].click()", clickOnByInitiator);
                } catch (ElementClickInterceptedException e) {
                    BaseUtil.commonForm.clickErrorHandle(e.toString(), clickOnByInitiator);
                }
            }
            return true;
        }
        return false;
    }

    public void verifyemptyfields() {

        String title = woTitle.getAttribute("value");
        String loc = location.getAttribute("value");
        String dep = department.getAttribute("value");

        boolean condition = title.isEmpty() && loc.isEmpty() && dep.isEmpty();
        Assert.assertTrue(condition, "Input fields are not empty");
        System.out.println("Input fields are empty");
    }

    public void verifyDeleteButton() {
        Boolean condition = deleteBtn.isEnabled();
        Assert.assertTrue(condition, "delete box disabled");
        System.out.println("Good delete box enabled");
    }


    public void enterQuoteDate() {
        wait.until(ExpectedConditions.visibilityOf(quoDate)).click();
        quotedateselection.click();
    }

    public void enterQuoteDetails(DataTable dataTable) {
        for (Map<Object, Object> data : dataTable.asMaps(String.class, String.class)) {

            quoTitle.sendKeys((String) data.get("QuoteTitle"));
            quoVendor.sendKeys((String) data.get("QuoteVendor"));
            quoPrice.sendKeys((String) data.get("QuotedPrice"));
        }
    }

    public void clickTotalPriceBox() {
        totPrice.click();
    }

    public boolean verifyWarningmessage(String warning_Message) {
        try {
            return new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'" + warning_Message + "')]"))).isDisplayed();
        } catch (TimeoutException ignored) {
        }
        return false;
    }

    public boolean verifyErrormessage(String error) {
        try {
            return new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'" + error + "')]"))).isDisplayed();
        } catch (TimeoutException ignored) {
        }
        return false;
    }

    public boolean verifyValidationmessage(String validation_message) {
        try {
            return new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'" + validation_message + "')]"))).isDisplayed();
        } catch (TimeoutException ignored) {
        }
        return false;
    }

    public boolean RequestsTabStatusCheck(String status) {
        try {
            String gridStatus = gridRequestsStatus.getText().toLowerCase();
            return status.toLowerCase().equals(gridStatus);
        } catch (Exception e) {

        }
        return false;
    }

    // Approve through Pending Approval grid
    public boolean ChangeStatusFromGrid(String status) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(gridPendingApprovalCheckBox));
        try {
            gridPendingApprovalCheckBox.click();
        } catch (org.openqa.selenium.StaleElementReferenceException e) {
            gridPendingApprovalCheckBox.click();
        }

        System.out.println("Changing status of WO# " + passingstringvalue + " to " + status.toUpperCase());

        switch (status.toLowerCase()) {
            case "approve":
                if (gridApproveBtn.isDisplayed() && gridApproveBtn.isEnabled()) {
                    try {
                        js.executeScript("arguments[0].click()", gridApproveBtn);
                    } catch (ElementClickInterceptedException e) {
                        BaseUtil.commonForm.clickErrorHandle(e.toString(), gridApproveBtn);
                    }
                    return true;
                }
                break;
            case "send back":
                if (gridSendBackBtn.isDisplayed() && gridSendBackBtn.isEnabled()) {
                    try {
                        js.executeScript("arguments[0].click()", gridSendBackBtn);
                    } catch (ElementClickInterceptedException e) {
                        BaseUtil.commonForm.clickErrorHandle(e.toString(), gridSendBackBtn);
                    }
                    return true;
                }
                break;
            case "reject":
                if (gridRejectBtn.isDisplayed() && gridRejectBtn.isEnabled()) {
                    try {
                        js.executeScript("arguments[0].click()", gridRejectBtn);
                    } catch (ElementClickInterceptedException e) {
                        BaseUtil.commonForm.clickErrorHandle(e.toString(), gridRejectBtn);
                    }
                    return true;
                }
                break;
        }
        return false;
    }


    @FindBy(how = How.XPATH, using = "//*[@id=\"dtquotes\"]/tbody/tr[1]/td[5]/center")
    private WebElement ApprovalStatus1;

    //To verify quote1 approval status
    public boolean verifyQuote1Status() {
        try {
            wait.until(ExpectedConditions.visibilityOf(ApprovalStatus1));
            String approvalStatus1 = ApprovalStatus1.getText();
            System.out.println("Current quote Approval status is showing as:" + approvalStatus1);
        } catch (Exception e) {
        }
        return false;
    }

    @FindBy(how = How.XPATH, using = "//tbody/tr[2]/td[5]/center")
    private WebElement ApprovalStatus2;

    //To verify quote1 approval status
    public boolean verifyQuote2Status() {
        try {
            wait.until(ExpectedConditions.visibilityOf(ApprovalStatus1));
            String approvalStatus2 = ApprovalStatus2.getText();
            System.out.println("Approval status is showing as :" + approvalStatus2);
        } catch (Exception e) {
        }
        return false;
    }

    public boolean verifyApproveButtonDisabled() {
        try {
            return approveBtn.isEnabled();
        } catch (Exception e) {
        }
        return false;
    }

    public boolean verifySendBackButtonDisabled() {
        try {
            return sendbackBtn.isEnabled();
        } catch (Exception e) {
        }
        return false;
    }

    public boolean verifyRejectButtonDisabled() {
        try {
            return rejectQuoteBtn.isEnabled();
        } catch (Exception e) {
        }
        return false;
    }

    public boolean verifySubmitButtonDisabled() {
        try {
            return submitBtn.isEnabled();
        } catch (Exception e) {
        }
        return false;
    }

    String editedText;
    String originalText;

    public void EditQuoteTitle() {
        wait.until(ExpectedConditions.visibilityOf(quoTitle));
        java.util.Date date = new java.util.Date();
        editedText = "Quote title was edited on " + date;
        originalText = quoTitle.getAttribute("value");
        System.out.println("Current subject for work order " + passingstringvalue + " is " + originalText);
        quoTitle.click();
        quoTitle.sendKeys(Keys.chord(Keys.LEFT_CONTROL, "a"));
        System.out.println("Changing quote title to " + editedText);
        quoTitle.sendKeys(editedText);
    }

    @FindBy(how = How.ID, using = "quotes_close")
    private WebElement QuotesCloseButton;

    public void CancelQuoteEdit() {
        QuotesCloseButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.invisibilityOf(QuotesCloseButton));
        System.out.println("Closing work order without saving changes");
    }

    @FindBy(how = How.ID, using = "quotes_sects")
    private WebElement quotesWOTab;

    public boolean VerifyQuoteTitleNotSaved() {
        try {
            quotesWOTab.click();
            return driver.findElements(By.xpath("//*[contains(text(), '" + editedText + "')]")).isEmpty();
        } catch (Exception e) {
        }
        return false;
    }

    @FindBy(how = How.ID, using = "po_VendorRefNo")
    private WebElement vendorRefNo;
    @FindBy(how = How.ID, using = "po_vendorAddress")
    private WebElement vendorAddressTextBox;
    @FindBy(how = How.ID, using = "item_partno0")
    private WebElement partNoTextBox;

    //Entering Purchase Order required details
    public void enterPurchaseOrderDetails(DataTable dataTable) {
        for (Map<Object, Object> data : dataTable.asMaps(String.class, String.class)) {

            vendorRefNo.sendKeys((String) data.get("VendorReferenceNumber"));
            vendorAddressTextBox.sendKeys((String) data.get("VendorAddress"));
            partNoTextBox.sendKeys((String) data.get("PartNo"));
        }
    }

    @FindBy(how = How.ID, using = "WoNumber1")
    private WebElement workOrderNumber;

    //To get the created purchase order number.
    public void getCreatedPONumber() {
        String PONumber = workOrderNumber.getText();
        System.out.println("Created PO # is  :" + PONumber);
    }

    public boolean linkDisplayed(String linkText) {
        try {
            if (driver.findElement(By.linkText(linkText)).isDisplayed()) {
                System.out.println("Link: " + linkText + " was displayed.");
                return true;
            } else {
                System.out.println("Link: " + linkText + " was not displayed.");
                return false;
            }
        } catch (NoSuchElementException | ElementNotInteractableException e) {
            System.out.println("Link: " + linkText + " does not exist.");
            return false;
        }
    }

    @FindBy(how = How.ID, using = "typeaheadValue")
    private WebElement topSearchBar;
    @FindBy(how = How.XPATH, using = "//*[@id=\"the-basics-new\"]/span/div/div/p")
    private List<WebElement> allSearchDropdown;

    public void typeInSearchField(String entry) {
        // Manually clear the search field, because the .clear() function does not work (because it's a "value" and not "text")
        // Could send "Ctrl+A" instead, but might not work on MacOS.

        elementFound(topSearchBar, "Top Search Bar");
        clearField(topSearchBar);

        long startTime = System.currentTimeMillis();
        while (topSearchBar.getAttribute("value").equals("") && ((System.currentTimeMillis() - startTime) < 5000)) { // even with the updated pageLoaded, sometimes the search field was not being filled. Just run this line until it is.
            topSearchBar.sendKeys(entry);
        }

        wait.until(ExpectedConditions.visibilityOfAllElements(allSearchDropdown));
    }

    public boolean elementFound(WebElement targetElement, String elementName) {
        try {
            if (targetElement.isDisplayed()) {
                return true;
            } else {
                System.out.println("Element " + elementName + " was not displayed.");
                return false;
            }
        } catch (NoSuchElementException | ElementNotInteractableException e) {
            System.out.println("Element " + elementName + " does not exist, or cannot be interacted with.");
            return false;
        }
    }

    public boolean elementFound(List<WebElement> targetElements, String elementName) {
        try {
            for (WebElement targetElement : targetElements) {
                if (!targetElement.isDisplayed()) {
                    System.out.println(elementName + " was not displayed.");
                    return false;
                }
            }
        } catch (NoSuchElementException | ElementNotInteractableException e) {
            System.out.println(elementName + " does not exist, or cannot be interacted with.");
            return false;
        }
        return true;
    }

    public boolean clearField(WebElement field) {
        long startTime = System.currentTimeMillis();
        while (!field.getAttribute("value").equals("") && ((System.currentTimeMillis() - startTime) < 5000)) { // lifted from NodeApp
            field.sendKeys(Keys.BACK_SPACE);
        }
        return field.getAttribute("value").equals("");
    }

    public void searchResultsContainSearch(String expected) {
        elementFound(allSearchDropdown, "Search Dropdown");
        List<WebElement> searchResults = allSearchDropdown;
        searchResults.addAll(emptySearchDropdown);
        for (WebElement dropDownResult : searchResults) {
            Assert.assertTrue(dropDownResult.getText().contains(expected), "Search string was not in this result.");
        }
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"the-basics-new\"]/span/div/div/div")
    private List<WebElement> emptySearchDropdown;

    public int numberOfSearchResults() {
        elementFound(allSearchDropdown, "Search Dropdown");
        return allSearchDropdown.size();
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"the-basics-new\"]/span/div/div/p")
    private WebElement topSearchDropdown;

    public void searchResultsDescending() {
        elementFound(allSearchDropdown, "Search Dropdown");
        int[] workOrderNumbers = new int[allSearchDropdown.size()];
        // initialize the first value so we can do the rest in just one FOR loop without IF statements
        workOrderNumbers[0] = Integer.parseInt(topSearchDropdown.getText().split(" /| ")[0]);

        // compare each number to its neighbours
        for (int i = 1; i < allSearchDropdown.size(); i++) {
            workOrderNumbers[i] = Integer.parseInt(allSearchDropdown.get(i).getText().split(" /| ")[0]); // To remove the " | <company name>" part of the string
            Assert.assertTrue(workOrderNumbers[i] < workOrderNumbers[i - 1]);
        }
    }

    @FindBy(how = How.ID, using = "WorkOrderDetails")
    private WebElement addRecordModal;

    public boolean waitForAddRecordClosed() {
        waitForElementGone(addRecordModal);
        if (elementFound(addRecordModal, "Current Request Detail Form")) {
            return addRecordModal.isDisplayed();
        }
        return false;
    }

    public boolean waitForElementGone(WebElement gallowsElement) {
        long startTime = System.currentTimeMillis();
        while ((System.currentTimeMillis() - startTime) < 5000) { // lifted from NodeApp
            try {
                if (gallowsElement.isDisplayed()) { // element is displayed: wait longer
                    new WebDriverWait(driver, 20);
                } else { // element is hidden: we're done
                    return true;
                }
            } catch (NoSuchElementException | ElementNotInteractableException e) { // element doesn't exist: we're done
                return true;
            }
        }
        return false;
    }

    public boolean gridHeaderFind(String tab, String header) {
        String tabName = tab.replaceAll("\\s", "");
        try {
            return driver.findElement(By.xpath("//table[@id='dt" + tabName + "']")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickTopPurchaseOrder() {
        try {
            driver.findElement(By.xpath("//*[@id='dtPurchaseOrder']/tbody/tr[1]/td[1]/a")).click();
        } catch (NoSuchElementException e) {
            driver.findElement(By.xpath("//*[@id='dtPurchaseOrder']/tbody/tr/td[1]/a")).click();
        }
    }

    //To get the message on hover the delete button
    public String verifyHoverMessageOnDeleteButton() {
        WebElement ele = driver.findElement(By.xpath("//table[@id='dtattachments']/tbody/tr/td[5]/center/button"));
//      Creating object of an Actions class
        Actions action = new Actions(driver);
//      Performing the mouse hover action on the target element.
        action.moveToElement(ele).perform();
        return ele.getAttribute("title");
    }

    public boolean verifyDeleteButtonUnderQuote() {
        try {
            return deleteButton1.isEnabled();
        } catch (Exception e) {
        }
        return false;
    }

    public boolean verifyDeleteButtonUnderQuoteHeader() {
        try {
            return deleteButton.isEnabled();
        } catch (Exception e) {
        }
        return false;
    }
}

