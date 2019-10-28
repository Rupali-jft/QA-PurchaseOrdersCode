package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class PurchaseOrderPage {
    WebDriver driver;
    private static String passingstringvalue = "";

    public PurchaseOrderPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"dtPolicies\"]")
    private WebElement pagination;
    @FindBy(how = How.XPATH, using = "//*[@id=\"dtPolicies_length\"]/label/select")
    private WebElement pValue;

    public void pagin(String Pagevalue) {
        pValue.sendKeys(Pagevalue);
    }

    public void pagin1() {
        List<WebElement> TotalRowsList = pagination.findElements(By.tagName("tr"));
        System.out.println("Total no of rows in table:" + (TotalRowsList.size() - 1));
    }

    //Get Values for Initiator role KPI count
    @FindBy(how = How.XPATH, using = "//*[@id=\"kpi1Count\"]")
    private WebElement reqPenApprovalKpiValue;
    @FindBy(how = How.XPATH, using = "//*[@id=\"kpi2Count\"]")
    private WebElement quoPenApprovalKpiValue;
    @FindBy(how = How.XPATH, using = "//*[@id=\"kpi3Count\"]")
    private WebElement returnedRequestsKpiValue;
    @FindBy(how = How.XPATH, using = "//*[@id=\"kpi4Count\"]")
    private WebElement rejectedRequestsKpiValue;

    public void InitiatorKPIValue() {
        String RequestPendingApprovalCount = reqPenApprovalKpiValue.getText();
        System.out.println("Request Pending Approval KPI Count is :" + RequestPendingApprovalCount);
        String QuotePendingApprovalCount = quoPenApprovalKpiValue.getText();
        System.out.println("Quote Pending Approval KPI Count is :" + QuotePendingApprovalCount);
        String ReturnedRequestsCount = returnedRequestsKpiValue.getText();
        System.out.println("Returned Requests KPI Count is :" + ReturnedRequestsCount);
        String RejectedRequestsCount = rejectedRequestsKpiValue.getText();
        System.out.println("Rejected Requests KPI Count is :" + RejectedRequestsCount);
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"dtRequests\"]/tbody/tr/td")
    private WebElement noDataAvailableMsg;

    public void VerifyNoDataAvailableMsg() {
        System.out.println("Text/Displayed Message is :" + noDataAvailableMsg.getText());
    }

    //Get Values for Procurement Manager role KPI Count
    @FindBy(how = How.XPATH, using = "//*[@id=\"kpi1Count\"]")
    private WebElement reqPenApprovalProcurementKpiValue;
    @FindBy(how = How.XPATH, using = "//*[@id=\"kpi2Count\"]")
    private WebElement returnedRequestsProcurementKpiValue;

    public void ProcurementKPIValue() {
        String RequestPendingApprovalProcurementCount = reqPenApprovalProcurementKpiValue.getText();
        System.out.println("Request Pending Approval KPI Count is :" + RequestPendingApprovalProcurementCount);
        String ReturnedRequestsProcurementCount = returnedRequestsProcurementKpiValue.getText();
        System.out.println("Quote Pending Approval KPI Count is :" + ReturnedRequestsProcurementCount);
    }

    //Click on Add Request Button
    @FindBy(how = How.ID, using = "addPORequest")
    private WebElement clickAddRequestBtn;

    public void clickAddRequest() {
        clickAddRequestBtn.click();
    }

    // Click on Edit item icon and update the quantity and save the details
    @FindBy(how = How.XPATH, using = " //*[@id=\"serviceDetails\"]/tbody/tr/td[4]/a[2]/i")
    private WebElement clickEditItemIcon;
    @FindBy(how = How.XPATH, using = "//*[@id=\"serviceDetails\"]/tbody/tr/td[4]/a[1]/i")
    private WebElement clickSaveItemIcon;

    public void clickOnEditIcon() {
        clickEditItemIcon.click();
        addQuantity.clear();
        addQuantity.sendKeys("3");
        clickSaveItemIcon.click();
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"typeaheadValue\"]")
    private WebElement txtGlobalSearch;
    @FindBy(how = How.XPATH, using = " //*[@id=\"the-basics-new\"]/span/div/div/p/a")
    private WebElement txtGlobalSearch1;

    //Used to check any work order requests globally
    public void GlobalSearch(String GlobalSearch) {
        try {
            txtGlobalSearch.sendKeys(GlobalSearch);
            Thread.sleep(5000);
            txtGlobalSearch1.click();
        } catch (Exception e) {
        }
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"WorkOrderDetails\"]/div[2]/div/div/div[2]/button")
    private WebElement addItem;
    @FindBy(how = How.XPATH, using = "//*[@id=\"item_service0\"]")
    private WebElement addItemName;
    @FindBy(how = How.XPATH, using = "//*[@id=\"request_item_service_description0\"]")
    private WebElement addDescription;
    @FindBy(how = How.XPATH, using = "//*[@id=\"qty\"]")
    private WebElement addQuantity;
    @FindBy(how = How.XPATH, using = "//*[@id=\"item_service1\"]")
    private WebElement addItemName1;
    @FindBy(how = How.XPATH, using = "//*[@id=\"request_item_service_description1\"]")
    private WebElement addDescription1;
    @FindBy(how = How.XPATH, using = "//*[@name='qty1']")
    private WebElement addQuantity1;
    @FindBy(how = How.XPATH, using = "//*[@id=\"serviceDetails\"]/tbody/tr/td[4]/a[1]/i\n")
    private WebElement clickItem;
    @FindBy(how = How.XPATH, using = "//*[@id=\"serviceDetails\"]/tbody/tr[2]/td[4]/a[1]/i\n")
    private WebElement clickItem1;

    //Filling all the required details in Work Order detailed page
    public void addRequestButton(String wotitle, String location, String department, String itemName, String description, String quantity) {
        try {
            workOrderTitleField.sendKeys(wotitle);
            locationDropDown.sendKeys(location);
            departmentDropDown.sendKeys(department);
            String statusValue = statusDropDown.getAttribute("value");
            System.out.println("Prints the Default Status is :" + statusValue);
            String dateValue = dateField.getAttribute("value");
            System.out.println("Prints the date i.e current date is :" + dateValue);
            addItem.click();
            addItemName.sendKeys(itemName);
            addDescription.sendKeys(description);
            addQuantity.sendKeys(quantity);
            clickItem.click();
        } catch (Exception e) {
        }
    }

    // Verify and get the Work order field values.
    public void verifyWOFields() {
        System.out.println("Work Order title value is:" + workOrderTitleField.getText());
        System.out.println("Work Order Location dropdown value is:" + locationDropDown.getText());
        System.out.println("Work Order Department dropdown value is:" + departmentDropDown.getText());
    }

    @FindBy(how = How.ID, using = "po_VendorRefNo")
    private WebElement vendorRefNo;
    @FindBy(how = How.ID, using = "po_vendorAddress")
    private WebElement vendorAddressTextBox;
    @FindBy(how = How.ID, using = "item_partno0")
    private WebElement partNoTextBox;

    //Entering Purchase Order required details
    public void addPurchaseOrderMandatoryDetails(String vendorReferenceNumber, String vendorAddress, String partNo) {
        try {
            vendorRefNo.sendKeys(vendorReferenceNumber);
            vendorAddressTextBox.sendKeys(vendorAddress);
            partNoTextBox.sendKeys(partNo);
        } catch (Exception e) {
        }
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"business-type-form\"]")
    private WebElement moveBusinessType;
    @FindBy(how = How.XPATH, using = "//*[@id=\"note_title\"]")
    private WebElement commentTitle;
    @FindBy(how = How.XPATH, using = "//*[@id=\"note_desc\"]")
    private WebElement commentDescription;

    @FindBy(how = How.XPATH, using = "//*[@id=\"notes_submit\"]")
    private WebElement commentSubmit;

    //To add any comments, clicked on add comments button
    public void clickOnAddComment() {
        addCommentBtn.click();
    }

    // To enter comments title and description
    public void commentDetails(String title, String description) {
        commentTitle.sendKeys(title);
        commentDescription.sendKeys(description);
        commentSubmit.click();
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("addNote")));
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"request_status\"]")
    private WebElement status;
    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[1]/div/div[1]/div/div[1]/div/ul/li[3]/a")
    private WebElement clickOnByPurchaseOfficer;

    // To add quotes by purchase officer
    public void clickAddQuote() {
        try {
            addQuoteBtn.click();
            clickOnByPurchaseOfficer.click();
            Thread.sleep(5000);
        } catch (Exception e) {
        }
    }

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[1]/div/div[1]/div/div[1]/div/ul/li[1]/a")
    private WebElement clickOnByInitiator;

    // To add quotes by initiator
    public void clickAddQuoteByInitiator() {
        try {
            addQuoteBtn.click();
            clickOnByInitiator.click();
            Thread.sleep(5000);

        } catch (Exception e) {
        }
    }


    //To click on add attachment button
    public void clickAddAttachment() {
        try {
            Thread.sleep(5000);
            addAttachmentBtn.click();
            Thread.sleep(5000);
            //highLightElement(driver,addNotes);

        } catch (Exception e) {
        }
    }

    @FindBy(how = How.ID, using = "attach_desc")
    private WebElement attachDesc;
    @FindBy(how = How.ID, using = "attachment_submit")
    private WebElement attachSubmit;

    // To submit an attachments
    public void attachSubmit() {
        try {
            Thread.sleep(6000);
            attachDesc.sendKeys("File");
            Thread.sleep(6000);
            attachSubmit.click();
            Thread.sleep(6000);
            WebDriverWait wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("addAttachment")));
        } catch (Exception e) {
        }
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"quote_date\"]")
    private WebElement quoDate;
    @FindBy(how = How.XPATH, using = "//*[@id=\"quote_title\"]")
    private WebElement quoTitle;
    @FindBy(how = How.XPATH, using = "//*[@id=\"quote_vendor\"]")
    private WebElement quoVendor;
    @FindBy(how = How.XPATH, using = "//*[@id=\"quotedprice0\"]")
    private WebElement quoPrice;
    @FindBy(how = How.XPATH, using = "//*[@id=\"quote_mark_selec\"]")
    private WebElement markSelected;
    @FindBy(how = How.XPATH, using = "//*[@id=\"quotes_submit\"]")
    private WebElement quoSubmit;

    // Entering required values in the Quotes Pop-up
    public void quotesDetails1(String quotedate, String quoteTitle, String quoteVendor, String quotedPrice) {
        quoDate.sendKeys(quotedate);
        quoTitle.sendKeys(quoteTitle);
        quoVendor.sendKeys(quoteVendor);
        quoPrice.sendKeys(quotedPrice);
        quoSubmit.click();
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("addQuote")));
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"dtnotes_wrapper\"]/tbody/tr/td[5]/center/span")
    private WebElement notesIcon;

    public void clickNotesTab() {
        try {
            commentsWOTab.click();
            notesIcon.click();
        } catch (Exception e) {
        }
    }


    //To click on history tab
    public void clickHistoryTab() {
        historyWOTab.click();
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"request_add\"]")
    private WebElement addSubmitButton;

    //To submit the request, clicked on submit button
    public void addSubmit() {
        addSubmitButton.click();
    }

    //To click on cancel changes button
    public void clickCancelChangesButton() {
        cancelChangesBtn.click();
    }


    @FindBy(how = How.XPATH, using = "//*[@id=\"serviceDetails\"]/tbody/tr[2]/td[4]/a[3]/i")
    private WebElement deleteButton;

    // Clicks on Delete Button
    public void addDelete() {
        deleteButton.click();
    }

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[1]/div/div[1]/div/div[1]/button[8]")
    private WebElement addPO;

    //To Click on Add Purchase order button
    public void clickAddPO() {
        addPO.click();
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"po_date\"]")
    private WebElement addPoDate;
    @FindBy(how = How.XPATH, using = "//*[@id=\"po_vendor\"]")
    private WebElement addPoVendor;
    @FindBy(how = How.XPATH, using = "//*[@id=\"po_subject\"]")
    private WebElement addPoSubject;
    @FindBy(how = How.XPATH, using = "//*[@id=\"po_reference\"]")
    private WebElement addPoReference;
    @FindBy(how = How.XPATH, using = "//*[@id=\"po_vendorAddress\"]")
    private WebElement addPoVendorAddress;
    @FindBy(how = How.XPATH, using = "//*[@id=\"Add_PO\"]/div[2]/div[2]/div/div/div[2]/div/button")
    private WebElement addPoItem;
    @FindBy(how = How.XPATH, using = "//*[@id=\"item_service0\"]")
    private WebElement addPoItemName;
    @FindBy(how = How.XPATH, using = "//*[@id=\"part_no0\"]")
    private WebElement addPoPartNo;
    @FindBy(how = How.XPATH, using = "//*[@id=\"request_item_service_description0\"]")
    private WebElement addPoDescription;
    @FindBy(how = How.XPATH, using = "//*[@id=\"qty0\"]")
    private WebElement addPoQty;
    @FindBy(how = How.XPATH, using = "//*[@id=\"unitprice0\"]")
    private WebElement addPoUnit;
    @FindBy(how = How.XPATH, using = "//*[@id=\"po_details\"]/tbody/tr/td[7]/a[1]")
    private WebElement addPoItemClick;
    @FindBy(how = How.XPATH, using = "//*[@id=\"po_submit\"]")
    private WebElement addPoSubmit;

    //To add purchase order required details
    public void addPoDetails1(String poDate, String poVendor, String poSubject, String poReference, String poVendorAddress, String poItemName, String poPartNo, String poDescrription, String poQty, String poUnit) {
        addPoDate.sendKeys(poDate);
        addPoVendor.sendKeys(poVendor);
        addPoSubject.sendKeys(poSubject);
        addPoReference.sendKeys(poReference);
        addPoVendorAddress.sendKeys(poVendorAddress);
        addPoItem.click();
        addPoItemName.sendKeys(poItemName);
        addPoPartNo.sendKeys(poPartNo);
        addPoDescription.sendKeys(poDescrription);
        addPoQty.sendKeys(poQty);
        addPoUnit.sendKeys(poUnit);
        addPoItemClick.click();
        addPoSubmit.click();
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("addPO")));
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"PDFURL\"]/button")
    private WebElement printPDFPo;

    //To click on Print PDF button to generate Purchase order details
    public void clickPrintPDFPo() {
        try {
            Thread.sleep(5000);
            printPDFPo.click();
            Thread.sleep(4000);
        } catch (Exception e) {
        }
    }


    @FindBy(how = How.XPATH, using = "//*[@id=\"po_close\"]")
    private WebElement poClose;

    //Click on Close PO button
    public void clickClosePo() {
        try {
            Thread.sleep(5000);
            poClose.click();
            Thread.sleep(5000);
        } catch (Exception e) {
        }
    }

    // To verify the WO status
    public void verifyStatus() {
        try {
            Thread.sleep(8000);
            Select select = new Select(driver.findElement(By.id("request_status")));
            WebElement option = select.getFirstSelectedOption();
            Thread.sleep(5000);
            String displayedStatus = option.getText();
            System.out.println("Current Work Order status is :" + displayedStatus);
        } catch (Exception e) {
        }
    }

    //To Verify the purchase Order status.
    public void verifyPOStatus() {
        try {
            Thread.sleep(8000);
            Select select = new Select(driver.findElement(By.id("postatus")));
            WebElement option = select.getFirstSelectedOption();
            Thread.sleep(5000);
            String displayedStatus = option.getText();
            System.out.println("Current Purchase Order status is :" + displayedStatus);
        } catch (Exception e) {
        }
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"dtquotes\"]/tbody/tr[1]/td[5]/center")
    private WebElement ApprovalStatus1;

    //To verify quote1 approval status
    public void verifyQuote1Status() {
        try {

            Thread.sleep(5000);
            String approvalStatus1 = ApprovalStatus1.getText();
            System.out.println("Current quote Approval status is showing as:" + approvalStatus1);
        } catch (Exception e) {

        }
    }

    @FindBy(how = How.XPATH, using = "//tbody/tr[2]/td[5]/center")
    private WebElement ApprovalStatus2;

    //To verify quote1 approval status
    public void verifyQuote2Status() {
        try {

            Thread.sleep(7000);
            String approvalStatus2 = ApprovalStatus2.getText();
            System.out.println("Approval status is showing as :" + approvalStatus2);
        } catch (Exception e) {
        }
    }


    //To click on request confirm button
    public void clickConfirmButton() {
        confirmWOBtn.click();

    }

    @FindBy(how = How.CSS, using = ".glyphicon-user")
    private WebElement UserIcon;
    @FindBy(how = How.CSS, using = ".h4ClrwhtStyl")
    private WebElement logOutButton;


    //To click on initiator Logout button
    public void clickOnLogOutButton() {
        try {
            Thread.sleep(20000);
            UserIcon.click();
            System.out.println("Clicks on Initiator logout button");
            Thread.sleep(28000);
            logOutButton.click();
        } catch (Exception e) {
        }
    }

    @FindBy(how = How.XPATH, using = "/html/body/header/nav/div/div/div[3]/div[1]/div/ul/li[17]/a")
    private WebElement ApproverLogOutButton;

    //To click on Approver Logout button
    public void clickOnApproverLogOutButton() {
        try {
            Thread.sleep(9000);
            UserIcon.click();
            System.out.println("Clicks on Approver logout button");
            Thread.sleep(3000);
            ApproverLogOutButton.click();
        } catch (Exception e) {
        }
    }

    @FindBy(how = How.XPATH, using = "/html/body/header/nav/div/div/div[3]/div[1]/div/ul/li[16]/a")
    private WebElement PurchaseOfficerLogOutButton;

    //To click on Purchase Officer role logout button
    public void clickOnPurchaseOfficerLogOutButton() {
        try {
            Thread.sleep(15000);
            UserIcon.click();
            System.out.println("Clicks on Purchase Officer logout button");
            Thread.sleep(20000);
            PurchaseOfficerLogOutButton.click();
        } catch (Exception e) {
        }
    }

    @FindBy(how = How.XPATH, using = "/html/body/header/nav/div/div/div[3]/div[1]/div/ul/li[7]/a")
    private WebElement ProcMgrLogOutButton;

    //To click on Procurement manager role logout button
    public void clickOnProcurementManagerLogOutButton() {
        try {
            Thread.sleep(13000);
            UserIcon.click();
            System.out.println("Clicks on procurement manager logout button");
            Thread.sleep(150000);
            ProcMgrLogOutButton.click();
        } catch (Exception e) {

        }
    }

    @FindBy(how = How.ID, using = "quotes_submit")
    private WebElement QuotesSubmitButton;

    //To click on Quote submit button
    public void clickOnQuotesSubmitButton() {
        try {
            Thread.sleep(9000);
            System.out.println("Clicks on quotes submit button");
            QuotesSubmitButton.click();
        } catch (Exception e) {
        }
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"dtquotes\"]/tbody/tr/td[7]/center/button")
    private WebElement QuotesDeleteButton;
    @FindBy(how = How.XPATH, using = "/html/body/div[7]/div[2]//div[4]/button[1]")
    private WebElement QuotesDeleteConfimation;

    //To click on Quotes delete button
    public void clickOnQuotesDeleteButton() {
        try {
            Thread.sleep(7000);
            System.out.println("Clicks on quotes delete button");
            QuotesDeleteButton.click();
            Thread.sleep(5000);
            QuotesDeleteConfimation.click();
        } catch (Exception e) {
        }
    }

    //To click on quotes tab
    public void clickOnQuotesTab() {
        try {
            Thread.sleep(9000);
            System.out.println("Clicks on quotes Tab");
            quotesWOTab.click();
        } catch (Exception e) {
        }
    }

    @FindBy(how = How.ID, using = "quotes_approve")
    private WebElement QuotesApproveButton;

    //To click  on approve button to approve the quotes
    public void clickOnQuotesApproveButton() {
        try {
            Thread.sleep(9000);
            System.out.println("Clicks on approve button to approve the quotes");
            QuotesApproveButton.click();
        } catch (Exception e) {

        }
    }

    @FindBy(how = How.ID, using = "quotes_send_back")
    private WebElement QuotesSendBackButton;

    //To click  on Sendback button to sendback the quotes
    public void clickOnQuotesSendBackButton() {
        try {
            Thread.sleep(9000);
            System.out.println("Clicks on sendback button to Sendback the quotes");
            QuotesSendBackButton.click();
        } catch (Exception e) {
        }
    }

    @FindBy(how = How.ID, using = "quotes_reject")
    private WebElement QuotesRejectButton;

    // To Click on reject button to reject the quotes
    public void clickOnQuotesRejectButton() {
        try {
            Thread.sleep(9000);
            System.out.println("Clicks on reject button to reject the quotes");
            QuotesRejectButton.click();
        } catch (Exception e) {
        }
    }

    @FindBy(how = How.ID, using = "po_submit")
    private WebElement RaisePOButton;

    //To click on raise purchase order button
    public void clickOnRaisePOButton() {
        try {
            Thread.sleep(5000);
            System.out.println("Clicks on Raise PO button");
            RaisePOButton.click();
        } catch (Exception e) {
        }
    }

    @FindBy(how = How.ID, using = "po_close")
    private WebElement ClosePOButton;

    // To click on close purchase order button
    public void clickOnClosePOButton() {
        try {
            Thread.sleep(10000);
            System.out.println("Clicks on close PO button");
            ClosePOButton.click();
        } catch (Exception e) {
        }
    }

    @FindBy(how = How.ID, using = "wo_close")
    private WebElement CloseWOButton;

    //To click on close work order button
    public void clickOnCloseWOButton() {
        try {
            Thread.sleep(10000);
            System.out.println("Clicks on close WO button");
            CloseWOButton.click();
        } catch (Exception e) {
        }
    }

    @FindBy(how = How.ID, using = "add_po")
    private WebElement AddPurchaseOrderButton;

    //To click on add purchase order button
    public void clickOnAddPurchaseOrderButton() {
        try {
            Thread.sleep(9000);
            System.out.println("Clicks on add purchase order button to raise PO");
            AddPurchaseOrderButton.click();
        } catch (Exception e) {
        }
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"dtquotes\"]/tbody/tr[1]/td[6]/center")
    private WebElement QuotesEyeIcon;

    //To click on quotes eye icon to view quote details
    public void clickOnQuotesEyeIcon() {
        try {
            Thread.sleep(5000);
            System.out.println("Clicks on quotes eye icon button");
            QuotesEyeIcon.click();
            WebDriverWait wait = new WebDriverWait(driver, 20);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("quote_title")));

        } catch (Exception e) {
        }
    }



    // To click on Work order approve button
    public void clickApproveButton() {
        WONumber.click();
        approveWOBtn.click();
    }


    //  To click on Work order sendback button
    public void clickSendBackButton() {
        WONumber.click();
        sendBackWOBtn.click();
    }

    // To click on Work order reject button
    public void clickRejectButton() {
        WONumber.click();
        rejectWOBtn.click();
    }


    @FindBy(how = How.ID, using = "location_err")
    private WebElement LocationErrMsg;

    //To get an error message, when location dropdown is empty and trying to submit
    public void getErrMsgForLocationDropDown() {
        String LocationDropDownErrMsg = LocationErrMsg.getText();
        System.out.println("Error Message is:" + LocationDropDownErrMsg);
    }

    @FindBy(how = How.ID, using = "note_desc_err")
    private WebElement commentsDescErrMsg;
    @FindBy(how = How.ID, using = "notes_close")
    private WebElement commentsCancelButton;

    // To get an error message when comments description text box is empty and trying to submit
    public void getErrorMsgForCommentsDescription() {
        System.out.println("Error Message is:" + commentsDescErrMsg.getText());
        commentsCancelButton.click();
    }

    @FindBy(how = How.ID, using = "quote_error_msg")
    private WebElement QuotedPriceErrMsg;
    @FindBy(how = How.ID, using = "quotes_close")
    private WebElement QuotesCloseButton;

    //To get an error message, when a quoted price is empty and trying to submit
    public void getErrorMsgForQuotedPriceEmpty() {
        System.out.println("Error Message is:" + QuotedPriceErrMsg.getText());
        QuotesCloseButton.click();
    }

    @FindBy(how = How.ID, using = "po_reference")
    private WebElement ReferenceInPOPage;

    // To get the reference value from Purchase order details page
    public void getReferenceFromPO() {
        String reference = ReferenceInPOPage.getText();
        System.out.println("Reference value is  :" + reference);
    }

    // Request Details elements
    // Universal elements
    @FindBy(how = How.ID, using = "request_add")
    private WebElement submitBtn;
    @FindBy(how = How.ID, using = "request_add_next")
    private WebElement submitNextBtn;
    @FindBy(how = How.ID, using = "request_cancel")
    private WebElement cancelChangesBtn;
    @FindBy(how = How.ID, using = "request_close")
    private WebElement closeBtn;
    @FindBy(how = How.XPATH, using = "//button[contains(.,'Add Comment')]")
    private WebElement addCommentBtn;
    @FindBy(how = How.XPATH, using = "//button[contains(.,'Add Attachment')]")
    private WebElement addAttachmentBtn;
    @FindBy(how = How.ID, using = "request_confirm")
    private WebElement confirmWOBtn;
    @FindBy(how = How.ID, using = "WoNumber1")
    private WebElement workOrderNumber;
    @FindBy(how = How.ID, using = "request_title")
    private WebElement workOrderTitleField;
    @FindBy(how = How.ID, using = "request_status")
    private WebElement statusDropDown;
    @FindBy(how = How.ID, using = "initiator")
    private WebElement initiatorField;
    @FindBy(how = How.ID, using = "request_location")
    private WebElement locationDropDown;
    @FindBy(how = How.ID, using = "request_Department")
    private WebElement departmentDropDown;
    @FindBy(how = How.ID, using = "request_date")
    private WebElement dateField;
    // Tabs
    @FindBy(how = How.ID, using = "purchaseorders_sec")
    private WebElement purchaseOrdersWOTab;
    @FindBy(how = How.ID, using = "quotes_sects")
    private WebElement quotesWOTab;
    @FindBy(how = How.ID, using = "notes_sects")
    private WebElement commentsWOTab;
    @FindBy(how = How.ID, using = "attachments_sec")
    private WebElement attachmentWOTab;
    @FindBy(how = How.ID, using = "history_sects")
    private WebElement historyWOTab;
    // Approver role only elements
    @FindBy(how = How.ID, using = "request_approve")
    private WebElement approveWOBtn;
    @FindBy(how = How.ID, using = "request_sendback")
    private WebElement sendBackWOBtn;
    @FindBy(how = How.ID, using = "request_reject")
    private WebElement rejectWOBtn;
    // Purchase Officer role only elements
    @FindBy(how = How.XPATH, using = "//button[contains(.,'Add Quote')]")
    private WebElement addQuoteBtn;

    //To verify the submit button presence
    public void verifySubmitButtonPresence() {
        if (submitBtn.isDisplayed()) {
            System.out.println("Submit button is present\n");
        } else {
            System.out.println("Submit button is not present in the page\n");
        }
    }

    public void CloseRequestDetail(){
        closeBtn.click();
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOf(requestsTab));
        System.out.println("Request details screen closed");
    }

    //To verify the cancel changes button presence
    public void verifyCancelChangesButtonPresence() {
        if (cancelChangesBtn.isDisplayed()) {
            System.out.println("Cancel Changes button is present\n");
        } else {
            System.out.println("Cancel Changes button is not present in the page\n");
        }
    }

    //To verify close button presence
    public void verifyCloseButtonPresence() {
        if (closeBtn.isDisplayed()) {
            System.out.println("Close button is present\n");
        } else {
            System.out.println("Close button is not present in the page\n");
        }
    }

    //To get the created purchase order number.
    public void getCreatedPONumber() {
        String PONumber = workOrderNumber.getText();
        System.out.println("Created PO # is  :" + PONumber);
    }

    public void getWorkOrderNum() {
        String workOderNum = workOrderNumber.getText();
        PurchaseOrderPage.passingstringvalue = workOderNum;
        System.out.println("Created Work Order Number is :" + workOrderNumber.getText());
        System.out.println(PurchaseOrderPage.passingstringvalue);
    }

    @FindBy(how = How.XPATH, using = "/html/body/div[7]")
    private WebElement confirmBox;
    @FindBy(how = How.XPATH, using = "/html/body/div[7]/div[2]/div/div/div/div/div/div/div/div[4]/button[1]")
    private WebElement YesButton;


    //To accept an alert
    public void acceptAnAlert() throws InterruptedException {

        System.out.println("Accepting an alert\n");
        Thread.sleep(5000);
        YesButton.click();
        Thread.sleep(7000);
    }

    @FindBy(how = How.XPATH, using = "/html/body/div[7]/div[2]//div[4]/button[1]")
    private WebElement givePermissionToInitiator;

    //To give permissions to initiator from purchase officer role
    public void acceptAddQuotesAccessAlert() {
        try {
            System.out.println("Accepting an alert\n");
            Thread.sleep(1000);
            givePermissionToInitiator.click();
            Thread.sleep(7000);
        } catch (Exception e) {
        }
    }

    @FindBy(how = How.XPATH, using = "/html/body/div[3]/div[2]//div[4]/button[1]")
    private WebElement YesButtonInClosePOAlert;

    //To confirm while closing the raised purchase order
    public void acceptClosePurchaseOrderAlert() {
        try {
            System.out.println("Accepting an alert\n");
            Thread.sleep(1000);
            YesButtonInClosePOAlert.click();
            Thread.sleep(7000);
        } catch (Exception e) {
        }
    }

    @FindBy(how = How.XPATH, using = "/html/body/div[7]/div[2]//div[4]/button[1]")
    private WebElement YesButtonInCloseWOAlert;

    //To accept the popup while closing the Work order
    public void acceptCloseWorkOrderAlert() {
        try {
            System.out.println("Accepting an alert\n");
            Thread.sleep(1000);
            YesButtonInCloseWOAlert.click();
            Thread.sleep(7000);
        } catch (Exception e) {
        }
    }




    @FindBy(how = How.ID, using = "PurchaseRequestIDpendingGrid")
    private WebElement gridPendingApprovalWOField;
    @FindBy(how = How.XPATH, using = "//table[@id='dtApproval']/tbody/tr/td/input")
    private WebElement gridPendingApprovalCheckBox;
    @FindBy(how = How.XPATH, using = "//*[@id=\"dtApproval\"]/tbody/tr/td[2]/a")
    private WebElement WONumber;

    //To navigate to pending approval tab
    public void NavigateToPendingApprovalTab() throws Throwable {
        Thread.sleep(8000);
        pendingApprovalTab.click();
        Thread.sleep(10000);
        gridPendingApprovalWOField.click();
        System.out.println("Check1 " + PurchaseOrderPage.passingstringvalue);
        String WOnum = PurchaseOrderPage.passingstringvalue;
        Thread.sleep(8000);
        gridPendingApprovalWOField.sendKeys(WOnum);
        System.out.println("Check2 " + PurchaseOrderPage.passingstringvalue);
    }

    public void FindWorkOrder(String tabName) throws Exception {
        WebElement workOrderField = SetTab(tabName, "workOrderField");
        WebElement status = SetTab(tabName, "status");
        workOrderField.click();
        System.out.println("Checking Work Order " + passingstringvalue);
        Thread.sleep(5000);
        workOrderField.sendKeys(passingstringvalue);
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'" + passingstringvalue + "')]")));
        System.out.println("Status of first position item is " + status.getText());
    }

    //To enter special/Invalid characters in Work order grid
    public void enterSpecialCharsinWOGrid() throws Throwable {
        Thread.sleep(5000);
        WOReqGrid.click();
        WOReqGrid.sendKeys("$%&");
    }

    @FindBy(how = How.ID, using = "PurchaseRequestIDreqGrid")
    private WebElement WOReqGrid;
    @FindBy(how = How.XPATH, using = "//*[@id=\"dtRequests\"]/tbody/tr/td[1]/a")
    private WebElement WONumPO;

    //To navigate to Purchase order request tab
    public void naviagteToPORequestTab() throws Throwable {
        Thread.sleep(10000);
        WOReqGrid.click();
        System.out.println("Check1" + PurchaseOrderPage.passingstringvalue);
        String WOnumber = PurchaseOrderPage.passingstringvalue;
        Thread.sleep(5000);
        WOReqGrid.sendKeys(WOnumber);
        System.out.println("Check2" + PurchaseOrderPage.passingstringvalue);
        WONumPO.click();
    }

    // KPI counts for all logins
    @FindBy(how = How.ID, using = "kpi1Count")
    private WebElement kpiValue1;
    @FindBy(how = How.XPATH, using = "//*[@id=\"kpi1\"]/div[2]/div[2]")
    private WebElement kpiTitle1;
    @FindBy(how = How.ID, using = "kpi2Count")
    private WebElement kpiValue2;
    @FindBy(how = How.XPATH, using = "//*[@id=\"kpi2\"]/div[2]/div[2]")
    private WebElement kpiTitle2;
    @FindBy(how = How.ID, using = "kpi3Count")
    private WebElement kpiValue3;
    @FindBy(how = How.XPATH, using = "//*[@id=\"kpi3\"]/div[2]/div[2]")
    private WebElement kpiTitle3;
    @FindBy(how = How.ID, using = "kpi4Count")
    private WebElement kpiValue4;
    @FindBy(how = How.XPATH, using = "//*[@id=\"kpi4\"]/div[2]/div[2]")
    private WebElement kpiTitle4;

    public int KPICount(String login, String kpi) throws Exception {


        if (login.equalsIgnoreCase("initiator")) {
            switch (kpi.toLowerCase()) {
                case "requests pending approval":
                    return Integer.parseInt(kpiValue1.getText());
                case "quotes pending approval":
                    return Integer.parseInt(kpiValue2.getText());
                case "returned requests":
                    return Integer.parseInt(kpiValue3.getText());
                case "rejected requests":
                    return Integer.parseInt(kpiValue4.getText());
                default:
                    break;
            }
        }
        if (login.equalsIgnoreCase("approver")) {
            switch (kpi.toLowerCase()) {
                case "requests pending approval":
                    return Integer.parseInt(kpiValue1.getText());
                case "requests pending quotes":
                    return Integer.parseInt(kpiValue2.getText());
                case "quotes pending approval":
                    return Integer.parseInt(kpiValue3.getText());
                default:
                    break;
            }
        }
        if (login.equalsIgnoreCase("purchase officer")) {
            switch (kpi.toLowerCase()) {
                case "requests pending quotes":
                    return Integer.parseInt(kpiValue1.getText());
                case "quotes pending approval":
                    return Integer.parseInt(kpiValue2.getText());
                case "returned quotes":
                    return Integer.parseInt(kpiValue3.getText());
                default:
                    break;
            }
        }
        if (login.equalsIgnoreCase("procurement manager")) {
            switch (kpi.toLowerCase()) {
                case "requests pending quotes":
                    return Integer.parseInt(kpiValue1.getText());
                case "returned requests":
                    return Integer.parseInt(kpiValue2.getText());
                default:
                    break;

            }
        }
        throw new Exception(kpi + " not not found for " + login + " Please ensure the login is correct");
    }

    // Elements for Requests grid
    @FindBy(how = How.ID, using = "tabbtnRequests")
    private WebElement requestsTab;
    // Pagination and view controls
    @FindBy(how = How.XPATH, using = "//*[@id=\"dtRequests_paginate\"]/div/a[1]")
    private WebElement gridRequestsPagePrev;
    @FindBy(how = How.XPATH, using = "//*[@id=\"dtRequests_paginate\"]/div/a[2]")
    private WebElement gridRequestsPageNext;
    @FindBy(how = How.XPATH, using = "//*[@id=\"dtRequests_paginate\"]/div/input")
    private WebElement gridRequestsPageNum;
    @FindBy(how = How.XPATH, using = "//*[@id=\"dtRequests_paginate\"]/div/span")
    private WebElement gridRequestsTotalPages;
    @FindBy(how = How.XPATH, using = "//*[@id=\"dtRequests_length\"]/label/select")
    private WebElement gridRequestsViewingDropdown;
    @FindBy(how = How.XPATH, using = "//*[@id=\"dtRequests_wrapper\"]/div[1]/div[2]/div/div[3]")
    private WebElement gridRequestsResetBtn;
    // Table header elements
    @FindBy(how = How.ID, using = "PurchaseRequestIDreqGrid")
    private WebElement gridRequestsWOField;
    @FindBy(how = How.ID, using = "WOTitlereqGrid")
    private WebElement gridRequestsWOTitleField;
    @FindBy(how = How.XPATH, using = "//*[@id=\"th-RequestInitiatorOfficerreqGrid\"]/span/div/button")
    private WebElement gridRequestsInitiatorList;
    @FindBy(how = How.XPATH, using = "//*[@id=\"th-PurchaseOrderStatusreqGrid\"]/span/div/button")
    private WebElement gridRequestsStatusList;
    @FindBy(how = How.XPATH, using = "//*[@id=\"from-RequestDatereqGrid\"]")
    private WebElement gridRequestsDateFrom;
    @FindBy(how = How.XPATH, using = "//*[@id=\"to-RequestDatereqGrid\"]")
    private WebElement gridRequestsDateTo;
    @FindBy(how = How.XPATH, using = "//*[@id=\"th-OfficeNamereqGrid\"]/span/div/button")
    private WebElement gridRequestsLocationList;
    @FindBy(how = How.XPATH, using = "//*[@id=\"th-DepartmentreqGrid\"]/span/div/button")
    private WebElement gridRequestsDepartmentList;
    // First row of table body
    @FindBy(how = How.XPATH, using = "//*[@id=\"dtRequests\"]/tbody/tr/td[1]/a")
    private WebElement gridRequestsWOLink;
    @FindBy(how = How.XPATH, using = "//*[@id=\"dtRequests\"]/tbody/tr/td[2]")
    private WebElement gridRequestsWOTitle;
    @FindBy(how = How.XPATH, using = "//*[@id=\"dtRequests\"]/tbody/tr/td[3]")
    private WebElement gridRequestsInitiator;
    @FindBy(how = How.XPATH, using = "//*[@id=\"dtRequests\"]/tbody/tr/td[4]")
    private WebElement gridRequestsStatus;
    @FindBy(how = How.XPATH, using = "//table[@id=\'dtRequests\']/tbody/tr/td[5]")
    private WebElement gridRequestsDate;
    @FindBy(how = How.XPATH, using = "//table[@id=\'dtRequests\']/tbody/tr/td[6]")
    private WebElement gridRequestsLocation;
    @FindBy(how = How.XPATH, using = "//table[@id=\'dtRequests\']/tbody/tr/td[7]")
    private WebElement gridRequestsDepartment;


    // Elements for Purchase Order grid
    @FindBy(how = How.ID, using = "tabbtnPurchaseOrders")
    private WebElement purchaseOrdersTab;
    // Pagination and view controls
    @FindBy(how = How.XPATH, using = "//*[@id=\"dtPurchaseOrder_paginate\"]/div/a[1]")
    private WebElement gridPOPagePrev;
    @FindBy(how = How.XPATH, using = "//*[@id=\"dtPurchaseOrder_paginate\"]/div/a[2]")
    private WebElement gridPOPageNext;
    @FindBy(how = How.XPATH, using = "//*[@id=\"dtPurchaseOrder_paginate\"]/div/input")
    private WebElement gridPOPageNum;
    @FindBy(how = How.XPATH, using = "//*[@id=\"dtPurchaseOrder_paginate\"]/div/span")
    private WebElement gridPOTotalPages;
    @FindBy(how = How.XPATH, using = "//*[@id=\"dtPurchaseOrder_length\"]/label/select")
    private WebElement gridPOViewingDropdown;
    // Table header elements
    @FindBy(how = How.ID, using = "PurchaseOrderIDpoGrid")
    private WebElement gridPOWOField;
    @FindBy(how = How.ID, using = "TitlepoGrid")
    private WebElement gridPOTitleField;
    @FindBy(how = How.XPATH, using = "//*[@id=\"th-RequestInitiatorpoGrid\"]/span/div/button")
    private WebElement gridPOInitiatorList;
    @FindBy(how = How.XPATH, using = "//*[@id=\"th-StatuspoGrid\"]/span/div/button")
    private WebElement gridPOStatusList;
    @FindBy(how = How.ID, using = "from-PurchaseOrderDatepoGrid")
    private WebElement gridPODateFrom;
    @FindBy(how = How.ID, using = "to-PurchaseOrderDatepoGrid")
    private WebElement gridPODateTo;
    @FindBy(how = How.XPATH, using = "//*[@id=\"th-LocationOfficepoGrid\"]/span/div/button")
    private WebElement gridPOLocationList;
    @FindBy(how = How.XPATH, using = "//*[@id=\"th-DepartmentpoGrid\"]/span/div/button")
    private WebElement gridPODepartmentList;
    @FindBy(how = How.ID, using = "VendorpoGrid")
    private WebElement gridPOVendorField;
    // First row of table body
    @FindBy(how = How.XPATH, using = "//*[@id=\"dtPurchaseOrder\"]/tbody/tr/td[1]/a")
    private WebElement gridPOWOLink;
    @FindBy(how = How.XPATH, using = "//*[@id=\"dtPurchaseOrder\"]/tbody/tr/td[2]")
    private WebElement gridPOWOTitle;
    @FindBy(how = How.XPATH, using = "//*[@id=\"dtPurchaseOrder\"]/tbody/tr/td[3]")
    private WebElement gridPOInitiator;
    @FindBy(how = How.XPATH, using = "//*[@id=\"dtPurchaseOrder\"]/tbody/tr/td[4]")
    private WebElement gridPOStatus;
    @FindBy(how = How.XPATH, using = "//table[@id=\'dtPurchaseOrder\']/tbody/tr/td[5]")
    private WebElement gridPODate;
    @FindBy(how = How.XPATH, using = "//table[@id=\'dtPurchaseOrder\']/tbody/tr/td[6]")
    private WebElement gridPOLocation;
    @FindBy(how = How.XPATH, using = "//table[@id=\'dtPurchaseOrder\']/tbody/tr/td[7]")
    private WebElement gridPODepartment;
    @FindBy(how = How.XPATH, using = "//table[@id=\'dtPurchaseOrder\']/tbody/tr/td[8]")
    private WebElement gridPOVendor;


    // Elements for Pending Approval grid
    @FindBy(how = How.ID, using = "tabbtnApproval")
    private WebElement pendingApprovalTab;
    // Action buttons
    @FindBy(how = How.ID, using = "btnApprove")
    private WebElement gridApproveBtn;
    @FindBy(how = How.XPATH, using = "//*[@id=\"btnApprove\"][2]")
    private WebElement gridSendBackBtn;
    @FindBy(how = How.XPATH, using = "//*[@id=\"btnApprove\"][3]")
    private WebElement gridRejectBtn;
    // Pagination and view controls
    @FindBy(how = How.XPATH, using = "//*[@id=\"dtApproval_paginate\"]/div/a[1]")
    private WebElement gridPendApprovalPagePrev;
    @FindBy(how = How.XPATH, using = "//*[@id=\"dtApproval_paginate\"]/div/a[2]")
    private WebElement gridPendApprovalPageNext;
    @FindBy(how = How.XPATH, using = "//*[@id=\"dtApproval_paginate\"]/div/input")
    private WebElement gridPendApprovalPageNum;
    @FindBy(how = How.XPATH, using = "//*[@id=\"dtApproval_paginate\"]/div/span")
    private WebElement gridPendApprovalTotalPages;
    @FindBy(how = How.XPATH, using = "//*[@id=\"dtApproval_length\"]/label/select")
    private WebElement gridPendApprovalViewingDropdown;
    // Table header elements
    @FindBy(how = How.ID, using = "PurchaseRequestIDpendingGrid")
    private WebElement gridPendApprovalWOField;
    @FindBy(how = How.ID, using = "WOTitlependingGrid")
    private WebElement gridPendApprovalWOTitleField;
    @FindBy(how = How.XPATH, using = "//*[@id=\"th-RequestInitiatorOfficerpendingGrid\"]/span/div/button")
    private WebElement gridPendApprovalInitiatorList;
    @FindBy(how = How.XPATH, using = "//*[@id=\"th-PurchaseOrderStatuspendingGrid\"]/span/div/button")
    private WebElement gridPendApprovalStatusList;
    @FindBy(how = How.XPATH, using = "//*[@id=\"from-RequestDatependingGrid\"]")
    private WebElement gridPendApprovalDateFrom;
    @FindBy(how = How.XPATH, using = "//*[@id=\"to-RequestDatependingGrid\"]")
    private WebElement gridPendApprovalDateTo;
    @FindBy(how = How.XPATH, using = "//*[@id=\"th-OfficeNamependingGrid\"]/span/div/button")
    private WebElement gridPendApprovalLocationList;
    @FindBy(how = How.XPATH, using = "//*[@id=\"th-DepartmentpendingGrid\"]/span/div/button")
    private WebElement gridPendApprovalDepartmentList;
    @FindBy(how = How.XPATH, using = "//*[@id=\"th-IsApprovedpendingGrid\"]/span/div/button/span")
    private WebElement gridPendApprovalApprovedList;
    // First row of table body
    @FindBy(how = How.XPATH, using = "//table[@id='dtApproval']/tbody/tr/td/input")
    private WebElement gridPendApprovalCheckBox;
    @FindBy(how = How.XPATH, using = "//*[@id=\"dtApproval\"]/tbody/tr/td[2]/a")
    private WebElement gridPendApprovalWOLink;
    @FindBy(how = How.XPATH, using = "//*[@id=\"dtApproval\"]/tbody/tr/td[3]")
    private WebElement gridPendApprovalWOTitle;
    @FindBy(how = How.XPATH, using = "//*[@id=\"dtApproval\"]/tbody/tr/td[4]")
    private WebElement gridPendApprovalInitiator;
    @FindBy(how = How.XPATH, using = "//*[@id=\"dtApproval\"]/tbody/tr/td[5]")
    private WebElement gridPendApprovalStatus;
    @FindBy(how = How.XPATH, using = "//*[@id=\"dtApproval\"]/tbody/tr/td[6]")
    private WebElement gridPendApprovalDate;
    @FindBy(how = How.XPATH, using = "//*[@id=\"dtApproval\"]/tbody/tr/td[7]")
    private WebElement gridPendApprovalLocation;
    @FindBy(how = How.XPATH, using = "//*[@id=\"dtApproval\"]/tbody/tr/td[8]")
    private WebElement gridPendApprovalDepartment;
    @FindBy(how = How.XPATH, using = "//*[@id=\"dtApproval\"]/tbody/tr/td[9]")
    private WebElement gridPendApprovalApproved;


    // Elements for Procurement Approval grid
    @FindBy(how = How.ID, using = "tabbtnProcMgr")
    private WebElement procApprovalTab;
    // Pagination and view controls
    @FindBy(how = How.XPATH, using = "//*[@id=\"dtProcMgrApproval_paginate\"]/div/a[1]")
    private WebElement gridProcApprovalPagePrev;
    @FindBy(how = How.XPATH, using = "//*[@id=\"dtProcMgrApproval_paginate\"]/div/a[2]")
    private WebElement gridProcApprovalPageNext;
    @FindBy(how = How.XPATH, using = "//*[@id=\"dtProcMgrApproval_paginate\"]/div/input")
    private WebElement gridProcApprovalPageNum;
    @FindBy(how = How.XPATH, using = "//*[@id=\"dtProcMgrApproval_paginate\"]/div/span")
    private WebElement gridProcApprovalTotalPages;
    @FindBy(how = How.XPATH, using = "//*[@id=\"dtProcMgrApproval_length\"]/label/select")
    private WebElement gridProcApprovalViewingDropdown;
    // Table header elements
    @FindBy(how = How.ID, using = "PurchaseRequestIDprocMgr")
    private WebElement gridProcApprovalWOField;
    @FindBy(how = How.ID, using = "WOTitleprocMgr")
    private WebElement gridProcApprovalWOTitleField;
    @FindBy(how = How.XPATH, using = "//*[@id=\"th-RequestInitiatorOfficerprocMgr\"]/span/div/button")
    private WebElement gridProcApprovalInitiatorList;
    @FindBy(how = How.XPATH, using = "//*[@id=\"th-PurchaseOrderStatusprocMgr\"]/span/div/button")
    private WebElement gridProcApprovalStatusList;
    @FindBy(how = How.XPATH, using = "//*[@id=\"from-RequestDateprocMgr\"]")
    private WebElement gridProcApprovalDateFrom;
    @FindBy(how = How.XPATH, using = "//*[@id=\"to-RequestDateprocMgr\"]")
    private WebElement gridProcApprovalDateTo;
    @FindBy(how = How.XPATH, using = "//*[@id=\"th-OfficeNameprocMgr\"]/span/div/button")
    private WebElement gridProcApprovalLocationList;
    @FindBy(how = How.XPATH, using = "//*[@id=\"th-DepartmentprocMgr\"]/span/div/button")
    private WebElement gridProcApprovalDepartmentList;
    // First row of table body
    @FindBy(how = How.XPATH, using = "//*[@id=\"dtProcMgrApproval\"]/tbody/tr/td[1]/a")
    private WebElement gridProcApprovalWOLink;
    @FindBy(how = How.XPATH, using = "//*[@id=\"dtProcMgrApproval\"]/tbody/tr/td[2]")
    private WebElement gridProcApprovalWOTitle;
    @FindBy(how = How.XPATH, using = "//*[@id=\"dtProcMgrApproval\"]/tbody/tr/td[3]")
    private WebElement gridProcApprovalInitiator;
    @FindBy(how = How.XPATH, using = "//*[@id=\"dtProcMgrApproval\"]/tbody/tr/td[4]")
    private WebElement gridProcApprovalStatus;
    @FindBy(how = How.XPATH, using = "//table[@id=\'dtProcMgrApproval\']/tbody/tr/td[5]")
    private WebElement gridProcApprovalDate;
    @FindBy(how = How.XPATH, using = "//table[@id=\'dtProcMgrApproval\']/tbody/tr/td[6]")
    private WebElement gridProcApprovalLocation;
    @FindBy(how = How.XPATH, using = "//table[@id=\'dtProcMgrApproval\']/tbody/tr/td[7]")
    private WebElement gridProcApprovalDepartment;

    private int pageNumber;
    private int pageTotal;

    private WebElement SetTab(String tabName, String element) throws Exception {
        Exception missingElement = new ElementNotVisibleException(element.toUpperCase() + " was not found in " + tabName.toUpperCase() + " tab. \n" +
                "Please check if element is incorrect or has not been added to SetTab method");
        if(tabName.equalsIgnoreCase("requests")){
            switch(element.toLowerCase()){
                case "tab":
                    return requestsTab;
                case "pageprev":
                    return gridRequestsPagePrev;
                case "pagenext":
                    return gridRequestsPageNext;
                case "pagenum":
                    return gridRequestsPageNum;
                case "viewingdropdown":
                    return gridRequestsViewingDropdown;
                case "totalpages":
                    return gridRequestsTotalPages;
                case "workorderfield":
                    return gridRequestsWOField;
                case "workordertitlefield":
                    return gridRequestsWOTitleField;
                case "initiatorlist":
                    return gridRequestsInitiatorList;
                case "statusdropdown":
                    return gridRequestsStatusList;
                case "datefrom":
                    return gridRequestsDateFrom;
                case "dateto":
                    return gridRequestsDateTo;
                case "locationlist":
                    return gridRequestsLocationList;
                case "departmentlist":
                    return gridRequestsDepartmentList;
                case "workorderlink":
                    return gridRequestsWOLink;
                case "workordertitle":
                    return gridRequestsWOTitle;
                case "initiator":
                    return gridRequestsInitiator;
                case "status":
                    return gridRequestsStatus;
                case "date":
                    return gridRequestsDate;
                case "location":
                    return gridRequestsLocation;
                case "department":
                    return gridRequestsDepartment;
                default:
                    throw missingElement;

            }
        }
        if(tabName.equalsIgnoreCase("purchase orders")){
            switch(element.toLowerCase()){
                case "tab":
                    return purchaseOrdersTab;
                case "pageprev":
                    return gridPOPagePrev;
                case "pagenext":
                    return gridPOPageNext;
                case "pagenum":
                    return gridPOPageNum;
                case "viewingdropdown":
                    return gridPOViewingDropdown;
                case "totalpages":
                    return gridPOTotalPages;
                case "workorderfield":
                    return gridPOWOField;
                case "workordertitlefield":
                    return gridPOTitleField;
                case "initiatorlist":
                    return gridPOInitiatorList;
                case "statusdropdown":
                    return gridPOStatusList;
                case "datefrom":
                    return gridPODateFrom;
                case "dateto":
                    return gridPODateTo;
                case "locationlist":
                    return gridPOLocationList;
                case "departmentlist":
                    return gridPODepartmentList;
                case "vendorlist":
                    return gridPOVendorField;
                case "workorderlink":
                    return gridPOWOLink;
                case "workordertitle":
                    return gridPOWOTitle;
                case "initiator":
                    return gridPOInitiator;
                case "status":
                    return gridPOStatus;
                case "date":
                    return gridPODate;
                case "location":
                    return gridPOLocation;
                case "department":
                    return gridPODepartment;
                case "vendor":
                    return gridPOVendor;
                default:
                    throw missingElement;

            }
        }
        if(tabName.equalsIgnoreCase("pending approval")){
            switch(element.toLowerCase()){
                case "tab":
                    return pendingApprovalTab;
                case "pageprev":
                    return gridPendApprovalPagePrev;
                case "pagenext":
                    return gridPendApprovalPageNext;
                case "pagenum":
                    return gridPendApprovalPageNum;
                case "viewingdropdown":
                    return gridPendApprovalViewingDropdown;
                case "totalpages":
                    return gridPendApprovalTotalPages;
                case "workorderfield":
                    return gridPendApprovalWOField;
                case "workordertitlefield":
                    return gridPendApprovalWOTitleField;
                case "initiatorlist":
                    return gridPendApprovalInitiatorList;
                case "statusdropdown":
                    return gridPendApprovalStatusList;
                case "datefrom":
                    return gridPendApprovalDateFrom;
                case "dateto":
                    return gridPendApprovalDateTo;
                case "locationlist":
                    return gridPendApprovalLocationList;
                case "departmentlist":
                    return gridPendApprovalDepartmentList;
                case "approvedlist":
                    return gridPendApprovalApprovedList;
                case "workorderlink":
                    return gridPendApprovalWOLink;
                case "workordertitle":
                    return gridPendApprovalWOTitle;
                case "initiator":
                    return gridPendApprovalInitiator;
                case "status":
                    return gridPendApprovalStatus;
                case "date":
                    return gridPendApprovalDate;
                case "location":
                    return gridPendApprovalLocation;
                case "department":
                    return gridPendApprovalDepartment;
                case "approved":
                    return gridPendApprovalApproved;
                default:
                    throw missingElement;

            }
        }
        if(tabName.equalsIgnoreCase("procurement approval")){
            switch(element.toLowerCase()){
                case "tab":
                    return procApprovalTab;
                case "pageprev":
                    return gridProcApprovalPagePrev;
                case "pagenext":
                    return gridProcApprovalPageNext;
                case "pagenum":
                    return gridProcApprovalPageNum;
                case "viewingdropdown":
                    return gridProcApprovalViewingDropdown;
                case "totalpages":
                    return gridProcApprovalTotalPages;
                case "workorderfield":
                    return gridProcApprovalWOField;
                case "workordertitlefield":
                    return gridProcApprovalWOTitleField;
                case "initiatorlist":
                    return gridProcApprovalInitiatorList;
                case "statusdropdown":
                    return gridProcApprovalStatusList;
                case "datefrom":
                    return gridProcApprovalDateFrom;
                case "dateto":
                    return gridProcApprovalDateTo;
                case "locationlist":
                    return gridProcApprovalLocationList;
                case "departmentlist":
                    return gridProcApprovalDepartmentList;
                case "workorderlink":
                    return gridProcApprovalWOLink;
                case "workordertitle":
                    return gridProcApprovalWOTitle;
                case "initiator":
                    return gridProcApprovalInitiator;
                case "status":
                    return gridProcApprovalStatus;
                case "date":
                    return gridProcApprovalDate;
                case "location":
                    return gridProcApprovalLocation;
                case "department":
                    return gridProcApprovalDepartment;
                default:
                    throw missingElement;

            }
        }
        return null;
    }

    public void NavigateToTab(String tabName) throws Exception {
        System.out.println("Navigating to " + tabName + " tab");
        WebElement tab = SetTab(tabName, "tab");
        Thread.sleep(5000);
        try {
            if (!tab.getAttribute("class").contains("active")) {
                tab.click();
            } else {
                System.out.println(tabName + " is already the current tab.");
            }
        } catch (NoSuchElementException e) {
            throw new Exception("Could not find " + tabName.toUpperCase() + " tab. Please ensure you are using the correct login");
        }
        Thread.sleep(5000);
    }

    public void NavigateToRequestsTab() throws InterruptedException {
        Thread.sleep(5000);

        if (!requestsTab.getAttribute("class").contains("active")) {
            requestsTab.click();
        }
        Thread.sleep(5000);
        gridRequestsWOField.click();
        System.out.println("Check1" + PurchaseOrderPage.passingstringvalue);
        String WOnum = PurchaseOrderPage.passingstringvalue;
        Thread.sleep(5000);
        gridRequestsWOField.sendKeys(WOnum);
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'" + WOnum + "')]")));
        System.out.println("Check2" + PurchaseOrderPage.passingstringvalue);

    }

    public void RequestsTabStatusCheck(String status) throws Exception {
        Thread.sleep(5000);
        String gridStatus = gridRequestsStatus.getText().toLowerCase();

        if (status.toLowerCase().equals(gridStatus)) {
            System.out.println("Expected status: " + status + " matches found status: " + gridStatus);
        } else {
            throw new Exception("Expected status: " + status + " was not found. Current status is " + gridStatus);
        }
    }

    // Approve through Pending Approval grid
    public void ChangeStatusFromGrid(String status) throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(gridPendingApprovalCheckBox));

        Thread.sleep(5000);

        try {
            gridPendingApprovalCheckBox.click();
        } catch (org.openqa.selenium.StaleElementReferenceException e) {
            gridPendingApprovalCheckBox.click();
        }

        System.out.println("Changing status of WO# " + PurchaseOrderPage.passingstringvalue + " to " + status.toUpperCase());

        switch (status.toLowerCase()) {
            case "approve":
                gridApproveBtn.click();
                break;
            case "send back":
                gridSendBackBtn.click();
                break;
            case "reject":
                gridRejectBtn.click();
                break;
            default:
                throw new Exception(status + " button not found. Please use 'Approve', 'Send Back', or 'Reject'");
        }
    }

    public void OpenWorkOrderFromGrid(String tabName) throws Exception {
        WebElement woLink = SetTab(tabName, "workorderlink");
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'" + passingstringvalue + "')]")));
        try {
            woLink.click();
        } catch (org.openqa.selenium.StaleElementReferenceException e) {
            woLink.click();
        }

        wait.until(ExpectedConditions.visibilityOf(workOrderNumber));
        System.out.println("Opened request details for item " + passingstringvalue);

    }
    String originalText;
    String editedText;

    public void EditTitleFromDetailsPage() throws InterruptedException {
        java.util.Date date=new java.util.Date();
        editedText = "Title was edited on " + date;
        originalText = workOrderTitleField.getAttribute("value");
        System.out.println("Current title for work order " + passingstringvalue + " is " + originalText);
        workOrderTitleField.click();
        workOrderTitleField.sendKeys(Keys.chord(Keys.LEFT_CONTROL, "a"));
        System.out.println("Changing work order title to " + editedText);
        workOrderTitleField.sendKeys(editedText);
        Thread.sleep(5000);
        System.out.println("Closing work order without saving changes");
    }

    public void VerifyWorkOrderTitle(){
        String currentTitle = workOrderTitleField.getAttribute("value");
            Assert.assertEquals(currentTitle, originalText);



    }

    public void WorkOrderNumber(String tabName, String workOrder) throws Exception {
        WebElement workOrderField = SetTab(tabName, "workOrderField");
        WebElement status = SetTab(tabName, "status");
        workOrderField.click();
        System.out.println("Checking Work Order " + workOrder);
        Thread.sleep(5000);
        workOrderField.sendKeys(workOrder);
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'" + workOrder + "')]")));
        System.out.println("Status of first position item is " + status.getText());

    }

    public void AddCommentText() throws InterruptedException {
        java.util.Date date=new java.util.Date();
        editedText = "Comment created on " + date;
        commentTitle.sendKeys(editedText);
        commentDescription.sendKeys(editedText);
        System.out.println("Adding title & description");
        Thread.sleep(5000);

    }

    public void CancelRequestComment(){
        commentsCancelButton.click();
        System.out.println("Canceling comment without saving");
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.invisibilityOf(commentsCancelButton));

    }

    public void VerifyCommentNotSaved(){
        commentsWOTab.click();
        System.out.println("Checking if comment was saved");
        Assert.assertTrue(driver.findElements(By.xpath("//*[contains(text(), '"+editedText+"')]")).isEmpty());

    }

    public void AddPurchaseOrderBtn(){
        AddPurchaseOrderButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(addPoSubmit));
        System.out.println("Opening purchase order screen");
    }

    public void EditSubjectFromPOPage() throws InterruptedException {
        java.util.Date date=new java.util.Date();
        editedText = "Subject was edited on " + date;
        originalText = addPoSubject.getAttribute("value");
        System.out.println("Current subject for work order " + passingstringvalue + " is " + originalText);
        addPoSubject.click();
        addPoSubject.sendKeys(Keys.chord(Keys.LEFT_CONTROL, "a"));
        System.out.println("Changing work order subject to " + editedText);
        addPoSubject.sendKeys(editedText);
        Thread.sleep(5000);
        System.out.println("Closing work order without saving changes");
    }

    public void VerifyPONotSaved(){
        System.out.println("Checking if Purchase Order was saved");
        Assert.assertTrue(driver.findElements(By.xpath("//*[contains(text(), '"+editedText+"')]")).isEmpty());
    }

    public void CheckAddPOBtn(){

        Assert.assertTrue(driver.findElements(By.id("add_po")).isEmpty());
    }

    public void EditQuoteTitle() throws InterruptedException {
        java.util.Date date=new java.util.Date();
        editedText = "Quote title was edited on " + date;
        originalText = quoTitle.getAttribute("value");
        System.out.println("Current subject for work order " + passingstringvalue + " is " + originalText);
        quoTitle.click();
        quoTitle.sendKeys(Keys.chord(Keys.LEFT_CONTROL, "a"));
        System.out.println("Changing quote title to " + editedText);
        quoTitle.sendKeys(editedText);
        Thread.sleep(5000);
        System.out.println("Closing work order without saving changes");
    }

    public void CancelQuoteEdit(){
        QuotesCloseButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.invisibilityOf(QuotesCloseButton));

    }

    public void VerifyQuoteTitleNotSaved(){
        quotesWOTab.click();
        Assert.assertTrue(driver.findElements(By.xpath("//*[contains(text(), '"+editedText+"')]")).isEmpty());

    }

    public void RequestDetailTabs(String tabName) throws Exception {
        switch(tabName.toLowerCase()){
            case "po":
                purchaseOrdersWOTab.click();
                break;
            case "quotes":
                quotesWOTab.click();
                break;
            case "comments":
                commentsWOTab.click();
                break;
            case "attachments":
                attachmentWOTab.click();
                break;
            case "history":
                historyWOTab.click();
                break;
            default:
                throw new Exception(tabName.toUpperCase() + " tab could not be found");


        }
    }

}
