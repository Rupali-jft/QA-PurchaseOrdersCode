package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


import java.util.List;


public class Loginpage {
    WebDriver driver;
    private static String passingstringvalue = "";

    public Loginpage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.ID, using = "email")
    public WebElement txtEmail;
    @FindBy(how = How.ID, using = "password")
    public WebElement txtPassword;
    @FindBy(how = How.ID, using = "submit")
    public WebElement btnSignIn;

    //To enter login credentials into user name and password text box
    public void Login(String Email, String Password) throws Throwable {
        Thread.sleep(15000);
        txtEmail.sendKeys(Email);
        Thread.sleep(15000);
        txtPassword.sendKeys(Password);
        Thread.sleep(15000);
    }

    //CLick on Sign-in Button after entering login details
    public void SignInButton() {

        btnSignIn.click();
    }

    @FindBy(how = How.XPATH, using = "//*[contains(text(),'Purchase Order')]")
    public WebElement link;

    public void POLink() {
        link.click();
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"dtPolicies\"]")
    public WebElement pagination;
    @FindBy(how = How.XPATH, using = "//*[@id=\"dtPolicies_length\"]/label/select")
    public WebElement pValue;

    public void pagin(String Pagevalue) {
        pValue.sendKeys(Pagevalue);
    }

    public void pagin1() {
        List<WebElement> TotalRowsList = pagination.findElements(By.tagName("tr"));
        System.out.println("Total no of rows in table:" + (TotalRowsList.size() - 1));
    }

    //Get Values for Initiator role KPI count
    @FindBy(how = How.XPATH, using = "//*[@id=\"kpi1Count\"]")
    public WebElement reqPenApprovalKpiValue;
    @FindBy(how = How.XPATH, using = "//*[@id=\"kpi2Count\"]")
    public WebElement quoPenApprovalKpiValue;
    @FindBy(how = How.XPATH, using = "//*[@id=\"kpi3Count\"]")
    public WebElement returnedRequestsKpiValue;
    @FindBy(how = How.XPATH, using = "//*[@id=\"kpi4Count\"]")
    public WebElement rejectedRequestsKpiValue;

    public void InitiatorKPIValue() {
        String RequestPendingApprovalCount = reqPenApprovalKpiValue.getText();
        System.out.println("Request Pending Approval KPI Count is :" + RequestPendingApprovalCount);
        String QuotePendingApprovalCount = quoPenApprovalKpiValue.getText();
        System.out.println("Quote Pending Approval KPI Count is :" + QuotePendingApprovalCount);
        String ReturnedRequestsCount = returnedRequestsKpiValue.getText();
        System.out.println("My New Business KPI Count is :" + ReturnedRequestsCount);
        String RejectedRequestsCount = rejectedRequestsKpiValue.getText();
        System.out.println("My Lost Business KPI Count is :" + RejectedRequestsCount);
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"dtRequests\"]/tbody/tr/td")
    public WebElement noDataAvailableMsg;

    public void VerifyNoDataAvailableMsg() {
        System.out.println("Text/Displayed Message is :" + noDataAvailableMsg.getText());
    }

    //Get Values for Procurement Manager role KPI Count
    @FindBy(how = How.XPATH, using = "//*[@id=\"kpi1Count\"]")
    public WebElement reqPenApprovalProcurementKpiValue;
    @FindBy(how = How.XPATH, using = "//*[@id=\"kpi2Count\"]")
    public WebElement returnedRequestsProcurementKpiValue;

    public void ProcurementKPIValue() {
        String RequestPendingApprovalProcurementCount = reqPenApprovalProcurementKpiValue.getText();
        System.out.println("Request Pending Approval KPI Count is :" + RequestPendingApprovalProcurementCount);
        String ReturnedRequestsProcurementCount = returnedRequestsProcurementKpiValue.getText();
        System.out.println("Quote Pending Approval KPI Count is :" + ReturnedRequestsProcurementCount);
    }

    //Click on Add Request Button
    @FindBy(how = How.ID, using = "addPORequest")
    public WebElement clickAddRequestBtn;

    public void clickAddRequest() {
        clickAddRequestBtn.click();
    }

    // Click on Edit item icon and update the quantity and save the details
    @FindBy(how = How.XPATH, using = " //*[@id=\"serviceDetails\"]/tbody/tr/td[4]/a[2]/i")
    public WebElement clickEditItemIcon;
    @FindBy(how = How.XPATH, using = "//*[@id=\"serviceDetails\"]/tbody/tr/td[4]/a[1]/i")
    public WebElement clickSaveItemIcon;

    public void clickOnEditIcon() {
        clickEditItemIcon.click();
        addQuantity.clear();
        addQuantity.sendKeys("3");
        clickSaveItemIcon.click();
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"typeaheadValue\"]")
    public WebElement txtGlobalSearch;
    @FindBy(how = How.XPATH, using = " //*[@id=\"the-basics-new\"]/span/div/div/p/a")
    public WebElement txtGlobalSearch1;

    //Used to check any work order requests globally
    public void GlobalSearch(String GlobalSearch) {
        try {
            txtGlobalSearch.sendKeys(GlobalSearch);
            Thread.sleep(5000);
            txtGlobalSearch1.click();
        } catch (Exception e) {
        }
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"request_title\"]")
    public WebElement addWOTitle;
    @FindBy(how = How.XPATH, using = "//*[@id=\"request_location\"]")
    public WebElement addLocation;
    @FindBy(how = How.XPATH, using = "//*[@id=\"request_Department\"]")
    public WebElement addDepartment;
    @FindBy(how = How.XPATH, using = "//*[@id=\"request_status\"]")
    public WebElement addStatus;
    @FindBy(how = How.XPATH, using = "//*[@id=\"request_date\"]")
    public WebElement addDate;
    @FindBy(how = How.XPATH, using = "//*[@id=\"WorkOrderDetails\"]/div[2]/div/div/div[2]/button")
    public WebElement addItem;
    @FindBy(how = How.XPATH, using = "//*[@id=\"item_service0\"]")
    public WebElement addItemName;
    @FindBy(how = How.XPATH, using = "//*[@id=\"request_item_service_description0\"]")
    public WebElement addDescription;
    @FindBy(how = How.XPATH, using = "//*[@id=\"qty\"]")
    public WebElement addQuantity;
    @FindBy(how = How.XPATH, using = "//*[@id=\"item_service1\"]")
    public WebElement addItemName1;
    @FindBy(how = How.XPATH, using = "//*[@id=\"request_item_service_description1\"]")
    public WebElement addDescription1;
    @FindBy(how = How.XPATH, using = "//*[@name='qty1']")
    public WebElement addQuantity1;
    @FindBy(how = How.XPATH, using = "//*[@id=\"serviceDetails\"]/tbody/tr/td[4]/a[1]/i\n")
    public WebElement clickItem;
    @FindBy(how = How.XPATH, using = "//*[@id=\"serviceDetails\"]/tbody/tr[2]/td[4]/a[1]/i\n")
    public WebElement clickItem1;

    //Filling all the required details in Work Order detailed page
    public void addRequestButton(String wotitle, String location, String department, String itemName, String description, String quantity) {
        try {
            addWOTitle.sendKeys(wotitle);
            addLocation.sendKeys(location);
            addDepartment.sendKeys(department);
            String statusValue = addStatus.getAttribute("value");
            System.out.println("Prints the Default Status is :" + statusValue);
            String dateValue = addDate.getAttribute("value");
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
        System.out.println("Work Order title value is:" + addWOTitle.getText());
        System.out.println("Work Order Location dropdown value is:" + addLocation.getText());
        System.out.println("Work Order Department dropdown value is:" + addDepartment.getText());
    }

    @FindBy(how = How.ID, using = "po_VendorRefNo")
    public WebElement vendorRefNo;
    @FindBy(how = How.ID, using = "po_vendorAddress")
    public WebElement vendorAddressTextBox;
    @FindBy(how = How.ID, using = "item_partno0")
    public WebElement partNoTextBox;

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
    public WebElement moveBusinessType;
    @FindBy(how = How.XPATH, using = "//*[@id=\"note_title\"]")
    public WebElement commentTitle;
    @FindBy(how = How.XPATH, using = "//*[@id=\"note_desc\"]")
    public WebElement commentDescription;
    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[1]/div/div[1]/div/div[1]/button[5]")
    public WebElement addNotes;
    @FindBy(how = How.XPATH, using = "//*[@id=\"notes_submit\"]")
    public WebElement commentSubmit;

    //To add any comments, clicked on add comments button
    public void clickOnAddComment() {
        addNotes.click();
    }

    // To enter comments title and description
    public void commentDetails(String title, String description) {
        commentTitle.sendKeys(title);
        commentDescription.sendKeys(description);
        commentSubmit.click();
    }

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[1]/div/div[1]/div/div[1]/div/button")
    public WebElement addQuote;
    @FindBy(how = How.XPATH, using = "//*[@id=\"request_status\"]")
    public WebElement status;
    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[1]/div/div[1]/div/div[1]/div/ul/li[3]/a")
    public WebElement clickOnByPurchaseOfficer;

    // To add quotes by purchase officer
    public void clickAddQuote() {
        try {
            addQuote.click();
            clickOnByPurchaseOfficer.click();
            Thread.sleep(5000);
        } catch (Exception e) {
        }
    }

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[1]/div/div[1]/div/div[1]/div/ul/li[1]/a")
    public WebElement clickOnByInitiator;

    // To add quotes by initiator
    public void clickAddQuoteByInitiator() {
        try {
            addQuote.click();
            clickOnByInitiator.click();
            Thread.sleep(5000);

        } catch (Exception e) {
        }
    }

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[1]/div/div[1]/div/div[1]/button[6]")
    public WebElement addAttach;

    //To click on add attachment button
    public void clickAddAttachment() {
        try {
            Thread.sleep(5000);
            addAttach.click();
            Thread.sleep(5000);
            //highLightElement(driver,addNotes);
        } catch (Exception e) {
        }
    }

    @FindBy(how = How.ID, using = "attach_desc")
    public WebElement attachDesc;
    @FindBy(how = How.ID, using = "attachment_submit")
    public WebElement attachSubmit;

    // To submit an attachments
    public void attachSubmit() {
        try {
            Thread.sleep(6000);
            attachDesc.sendKeys("File");
            Thread.sleep(6000);
            attachSubmit.click();
            Thread.sleep(6000);
        } catch (Exception e) {
        }
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"quote_date\"]")
    public WebElement quoDate;
    @FindBy(how = How.XPATH, using = "//*[@id=\"quote_title\"]")
    public WebElement quoTitle;
    @FindBy(how = How.XPATH, using = "//*[@id=\"quote_vendor\"]")
    public WebElement quoVendor;
    @FindBy(how = How.XPATH, using = "//*[@id=\"quotedprice0\"]")
    public WebElement quoPrice;
    @FindBy(how = How.XPATH, using = "//*[@id=\"quote_mark_selec\"]")
    public WebElement markSelected;
    @FindBy(how = How.XPATH, using = "//*[@id=\"quotes_submit\"]")
    public WebElement quoSubmit;

    // Entering required values in the Quotes Pop-up
    public void quotesDetails1(String quotedate, String quoteTitle, String quoteVendor, String quotedPrice) {
        quoDate.sendKeys(quotedate);
        quoTitle.sendKeys(quoteTitle);
        quoVendor.sendKeys(quoteVendor);
        quoPrice.sendKeys(quotedPrice);
        quoSubmit.click();
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"notes_sects\"]")
    public WebElement notesTab;
    @FindBy(how = How.XPATH, using = "//*[@id=\"dtnotes_wrapper\"]/tbody/tr/td[5]/center/span")
    public WebElement notesIcon;

    public void clickNotesTab() {
        try {
            notesTab.click();
            notesIcon.click();
        } catch (Exception e) {
        }
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"history_sects\"]")
    public WebElement historyTab;

    //To click on history tab
    public void clickHistoryTab() {
        historyTab.click();
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"request_add\"]")
    public WebElement addSubmitButton;

    //To submit the request, clicked on submit button
    public void addSubmit() {
        addSubmitButton.click();
    }

    //To click on cancel changes button
    public void clickCancelChangesButton() {
        CancelChangesButton.click();
    }


    @FindBy(how = How.XPATH, using = "//*[@id=\"serviceDetails\"]/tbody/tr[2]/td[4]/a[3]/i")
    public WebElement deleteButton;

    // Clicks on Delete Button
    public void addDelete() {
        deleteButton.click();
    }

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[1]/div/div[1]/div/div[1]/button[8]")
    public WebElement addPO;

    //To Click on Add Purchase order button
    public void clickAddPO() {
        addPO.click();
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"po_date\"]")
    public WebElement addPoDate;
    @FindBy(how = How.XPATH, using = "//*[@id=\"po_vendor\"]")
    public WebElement addPoVendor;
    @FindBy(how = How.XPATH, using = "//*[@id=\"po_subject\"]")
    public WebElement addPoSubject;
    @FindBy(how = How.XPATH, using = "//*[@id=\"po_reference\"]")
    public WebElement addPoReference;
    @FindBy(how = How.XPATH, using = "//*[@id=\"po_vendorAddress\"]")
    public WebElement addPoVendorAddress;
    @FindBy(how = How.XPATH, using = "//*[@id=\"Add_PO\"]/div[2]/div[2]/div/div/div[2]/div/button")
    public WebElement addPoItem;
    @FindBy(how = How.XPATH, using = "//*[@id=\"item_service0\"]")
    public WebElement addPoItemName;
    @FindBy(how = How.XPATH, using = "//*[@id=\"part_no0\"]")
    public WebElement addPoPartNo;
    @FindBy(how = How.XPATH, using = "//*[@id=\"request_item_service_description0\"]")
    public WebElement addPoDescription;
    @FindBy(how = How.XPATH, using = "//*[@id=\"qty0\"]")
    public WebElement addPoQty;
    @FindBy(how = How.XPATH, using = "//*[@id=\"unitprice0\"]")
    public WebElement addPoUnit;
    @FindBy(how = How.XPATH, using = "//*[@id=\"po_details\"]/tbody/tr/td[7]/a[1]")
    public WebElement addPoItemClick;
    @FindBy(how = How.XPATH, using = "//*[@id=\"po_submit\"]")
    public WebElement addPoSubmit;

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
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"PDFURL\"]/button")
    public WebElement printPDFPo;

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
    public WebElement poClose;

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
    public WebElement ApprovalStatus1;

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
    public WebElement ApprovalStatus2;

    //To verify quote1 approval status
    public void verifyQuote2Status() {
        try {

            Thread.sleep(7000);
            String approvalStatus2 = ApprovalStatus2.getText();
            System.out.println("Approval status is showing as :" + approvalStatus2);
        } catch (Exception e) {
        }
    }

    @FindBy(how = How.ID, using = "request_confirm")
    public WebElement WorkOrderConfirmButton;

    //To click on request confirm button
    public void clickConfirmButton() {
        WorkOrderConfirmButton.click();
    }

    @FindBy(how = How.XPATH, using = "/html/body/header/nav/div/div/div[3]/div[1]/div/button")
    public WebElement UserIcon;
    @FindBy(how = How.XPATH, using = "/html/body/header/nav/div/div/div[3]/div[1]/div/ul/li[14]/a")
    public WebElement InitiatorLogOutButton;

    //To click on initiator Logout button
    public void clickOnInitiatorLogOutButton() {
        try {
            Thread.sleep(20000);
            UserIcon.click();
            System.out.println("Clicks on Initiator logout button");
            Thread.sleep(28000);
            InitiatorLogOutButton.click();
        } catch (Exception e) {
        }
    }

    @FindBy(how = How.XPATH, using = "/html/body/header/nav/div/div/div[3]/div[1]/div/ul/li[17]/a")
    public WebElement ApproverLogOutButton;

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
    public WebElement PurchaseOfficerLogOutButton;

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
    public WebElement ProcMgrLogOutButton;

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
    public WebElement QuotesSubmitButton;

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
    public WebElement QuotesDeleteButton;
    @FindBy(how = How.XPATH, using = "/html/body/div[7]/div[2]//div[4]/button[1]")
    public WebElement QuotesDeleteConfimation;

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

    @FindBy(how = How.ID, using = "quotes_sects")
    public WebElement QuotesTab;

    //To click on quotes tab
    public void clickOnQuotesTab() {
        try {
            Thread.sleep(9000);
            System.out.println("Clicks on quotes Tab");
            QuotesTab.click();
        } catch (Exception e) {
        }
    }

    @FindBy(how = How.ID, using = "quotes_approve")
    public WebElement QuotesApproveButton;

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
    public WebElement QuotesSendBackButton;

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
    public WebElement QuotesRejectButton;

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
    public WebElement RaisePOButton;

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
    public WebElement ClosePOButton;

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
    public WebElement CloseWOButton;

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
    public WebElement AddPurchaseOrderButton;

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
    public WebElement QuotesEyeIcon;

    //To click on quotes eye icon to view quote details
    public void clickOnQuotesEyeIcon() {
        try {
            Thread.sleep(5000);
            System.out.println("Clicks on quotes eye icon button");
            QuotesEyeIcon.click();
        } catch (Exception e) {
        }
    }

    @FindBy(how = How.ID, using = "request_approve")
    public WebElement WOApproveButton;

    // To click on Work order approve button
    public void clickApproveButton() {
        WONumber.click();
        WOApproveButton.click();
    }

    @FindBy(how = How.ID, using = "request_sendback")
    public WebElement WOSendBackButton;

    //  To click on Work order sendback button
    public void clickSendBackButton() {
        WONumber.click();
        WOSendBackButton.click();
    }

    @FindBy(how = How.ID, using = "request_reject")
    public WebElement WORejectButton;

    // To click on Work order reject button
    public void clickRejectButton() {
        WONumber.click();
        WORejectButton.click();
    }

    @FindBy(how = How.ID, using = "WoNumber1")
    public WebElement WorkOrderNumber;

    public void getWorkOrderNum() {
        String workOderNum = WorkOrderNumber.getText();
        Loginpage.passingstringvalue = workOderNum;
        System.out.println("Created Work Order Number is :" + WorkOrderNumber.getText());
        System.out.println(Loginpage.passingstringvalue);
    }

    @FindBy(how = How.ID, using = "location_err")
    public WebElement LocationErrMsg;

    //To get an error message, when location dropdown is empty and trying to submit
    public void getErrMsgForLocationDropDown() {
        String LocationDropDownErrMsg = LocationErrMsg.getText();
        System.out.println("Error Message is:" + LocationDropDownErrMsg);
    }

    @FindBy(how = How.ID, using = "note_desc_err")
    public WebElement CommentsDescErrMsg;
    @FindBy(how = How.ID, using = "notes_close")
    public WebElement CommentsCancelButton;

    // To get an error message when comments description text box is empty and trying to submit
    public void getErrorMsgForCommentsDescription() {
        System.out.println("Error Message is:" + CommentsDescErrMsg.getText());
        CommentsCancelButton.click();
    }

    @FindBy(how = How.ID, using = "quote_error_msg")
    public WebElement QuotedPriceErrMsg;
    @FindBy(how = How.ID, using = "quotes_close")
    public WebElement QuotesCloseButton;

    //To get an error message, when a quoted price is empty and trying to submit
    public void getErrorMsgForQuotedPriceEmpty() {
        System.out.println("Error Message is:" + QuotedPriceErrMsg.getText());
        QuotesCloseButton.click();
    }

    @FindBy(how = How.ID, using = "po_reference")
    public WebElement ReferenceInPOPage;

    // To get the reference value from Purchase order details page
    public void getReferenceFromPO() {
        String reference = ReferenceInPOPage.getText();
        System.out.println("Reference value is  :" + reference);
    }

    @FindBy(how = How.ID, using = "request_add")
    public WebElement SubmitButton;
    @FindBy(how = How.ID, using = "request_cancel")
    public WebElement CancelChangesButton;
    @FindBy(how = How.ID, using = "request_close")
    public WebElement CloseButton;

    //To verify the submit button presence
    public void verifySubmitButtonPresence() {
        if (SubmitButton.isDisplayed()) {
            System.out.println("Submit button is present\n");
        } else {
            System.out.println("Submit button is not present in the page\n");
        }
    }

    //To verify the cancel changes button presence
    public void verifyCancelChangesButtonPresence() {
        if (CancelChangesButton.isDisplayed()) {
            System.out.println("Cancel Changes button is present\n");
        } else {
            System.out.println("Cancel Changes button is not present in the page\n");
        }
    }

    //To verify close button presence
    public void verifyCloseButtonPresence() {
        if (CloseButton.isDisplayed()) {
            System.out.println("Close button is present\n");
        } else {
            System.out.println("Close button is not present in the page\n");
        }
    }

    @FindBy(how = How.ID, using = "WoNumber1")
    public WebElement CreatedPONumber;

    //To get the created purchase order number.
    public void getCreatedPONumber() {
        String PONumber = CreatedPONumber.getText();
        System.out.println("Created PO # is  :" + PONumber);
    }

    @FindBy(how = How.XPATH, using = "/html/body/div[7]/div[2]//div[4]/button[1]")
    public WebElement YesButton;

    //To accept an alert
    public void acceptAnAlert() {
        try {
            System.out.println("Accepting an alert\n");
            Thread.sleep(5000);
            YesButton.click();
            Thread.sleep(7000);
        } catch (Exception e) {
        }
    }

    @FindBy(how = How.XPATH, using = "/html/body/div[7]/div[2]//div[4]/button[1]")
    public WebElement givePermissionToInitiator;

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
    public WebElement YesButtonInClosePOAlert;

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
    public WebElement YesButtonInCloseWOAlert;

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

    @FindBy(how = How.ID, using = "approverSects")
    public WebElement pendingApprovalTab;
    @FindBy(how = How.ID, using = "PurchaseRequestIDpendingGrid")
    public WebElement woGrid;
    @FindBy(how = How.XPATH, using = "//*[@id=\"dtApproval\"]/tbody/tr/td[2]/a")
    public WebElement WONumber;

    //To navigate to pending approver tab
    public void naviagteToPendingApproverTab() throws Throwable {
        Thread.sleep(8000);
        pendingApprovalTab.click();
        Thread.sleep(10000);
        woGrid.click();
        System.out.println("Check1" + Loginpage.passingstringvalue);
        String WOnum = Loginpage.passingstringvalue;
        Thread.sleep(8000);
        woGrid.sendKeys(WOnum);
        System.out.println("Check2" + Loginpage.passingstringvalue);
    }

    //To enter special/Invalid characters in Work order grid
    public void enterSpecialCharsinWOGrid() throws Throwable {
        Thread.sleep(5000);
        WOReqGrid.click();
        WOReqGrid.sendKeys("$%&");
    }

    @FindBy(how = How.ID, using = "PurchaseRequestIDreqGrid")
    public WebElement WOReqGrid;
    @FindBy(how = How.XPATH, using = "//*[@id=\"dtRequests\"]/tbody/tr/td[1]/a")
    public WebElement WONumPO;

    //To navigate to Purchase order request tab
    public void naviagteToPORequestTab() throws Throwable {
        Thread.sleep(10000);
        WOReqGrid.click();
        System.out.println("Check1" + Loginpage.passingstringvalue);
        String WOnumber = Loginpage.passingstringvalue;
        Thread.sleep(5000);
        WOReqGrid.sendKeys(WOnumber);
        System.out.println("Check2" + Loginpage.passingstringvalue);
        WONumPO.click();
    }
}
