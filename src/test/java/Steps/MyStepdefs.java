package Steps;

import Pages.BaseUtil;
import Pages.Loginpage;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MyStepdefs extends BaseUtil {

    public MyStepdefs(BaseUtil base) {
        this.base = base;
    }

    private BaseUtil base;

    @Given("^I navigate to login page of PO app$")
    public void iNavigateToLoginPageOfPOApp() {
        System.out.println("Navigates to DEV Login page\n");
        base.driver.get("https://dev.patracorp.net");
        base.driver.manage().window().maximize();
    }

    @And("^I enter login credentials$")
    public void iEnterLoginCredentials(DataTable table) throws Throwable {
        System.out.println("Entering email address and password\n");
        List<SignIn> users = new ArrayList<SignIn>();
        users = table.asList(SignIn.class);
        Loginpage page = new Loginpage(base.driver);
        for (SignIn user : users) {
            page.Login(user.email, user.password);
        }
    }

    public class SignIn {
        public String email;
        public String password;

        public SignIn(String Email, String Password) {
            email = Email;
            password = Password;
        }
    }

    @And("^I click on Sign In button$")
    public void iClickOnSignInButton() throws Throwable {
        System.out.println("Clicking on Sign In button\n");
        Loginpage page = new Loginpage(base.driver);
        page.SignInButton();
        Thread.sleep(5000);
        page.POLink();
        Thread.sleep(10000);
        String parentWindow = base.driver.getWindowHandle();
        Set<String> handles = base.driver.getWindowHandles();
        for (String windowHandle : handles) {
            if (!windowHandle.equals(parentWindow)) {
                base.driver.switchTo().window(windowHandle);
            }
        }
        Thread.sleep(6000);
    }

    @And("^Get the Initiator KPI Count$")
    public void getTheInitiatorKpiCOunt() throws Throwable {
        Thread.sleep(5000);
        Loginpage page = new Loginpage(base.driver);
        page.InitiatorKPIValue();
    }

    @And("^Get the Procurement KPI Count$")
    public void getTheProcurementKpiCOunt() throws Throwable {
        Thread.sleep(5000);
        Loginpage page = new Loginpage(base.driver);
        page.ProcurementKPIValue();
    }

    @And("^Pagination value")
    public void paginationValue(DataTable table) throws Throwable {
        List<pagin> pages = new ArrayList<pagin>();
        pages = table.asList(pagin.class);
        Loginpage page = new Loginpage(base.driver);
        for (pagin page1 : pages) {
            page.pagin(page1.pagevalue);
        }
        Thread.sleep(5000);
        page.pagin1();
    }

    public class pagin {
        public String pagevalue;
        public pagin(String Pagevalue) {
            pagevalue = Pagevalue;
        }
    }

    @When("^Click on Add Request$")
    public void clickOnAddRequest() throws Throwable {
        System.out.println("clicking on Add Request\n");
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(25000);
        page.clickAddRequest();
        Thread.sleep(5000);
    }

    @Then("^I click on edit item icon and edited and submitted$")
    public void clickOnEditItemIcon() throws Throwable {
        System.out.println("clicking on Edit icon to edit the item quantity\n");
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(3000);
        page.clickOnEditIcon();
    }

    @Then("^Enter Add Request Details$")
    public void enterAddRequestDetails(DataTable table) throws Throwable {
        System.out.println("Entering Work order Request Details\n");
        List<addRequest> users = new ArrayList<addRequest>();
        users = table.asList(addRequest.class);
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(5000);
        for (addRequest user : users) {
            page.addRequestButton(user.wotitle, user.location, user.department, user.itemName, user.description, user.quantity);
           }
        Thread.sleep(5000);
    }
    public class addRequest {
        public String wotitle, location, department, itemName, description, quantity, itemName1, description1, quantity1;
        public addRequest(String Wotitle, String Location, String Department, String ItemName, String Description, String Quantity, String ItemName1, String Description1, String Quantity1) {
            this.wotitle = Wotitle;
            this.location = Location;
            this.department = Department;
            this.itemName = ItemName;
            this.description = Description;
            this.quantity = Quantity;
            this.itemName1 = ItemName1;
            this.description1 = Description1;
            this.quantity1 = Quantity1;
        }
    }

    @Then("^Enter Purchase Order confirmation Details$")
    public void enterPurchaseOrderDetails(DataTable table) throws Throwable {
        System.out.println("Entering Purchase Order Details\n");
        List<addPurchaseOrderDetails> poDetails = new ArrayList<addPurchaseOrderDetails>();
        poDetails = table.asList(addPurchaseOrderDetails.class);
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(5000);
        for (addPurchaseOrderDetails POdet : poDetails) {
            page.addPurchaseOrderMandatoryDetails(POdet.vendorReferenceNumber, POdet.vendorAddress, POdet.partNo);
        }
        Thread.sleep(5000);
    }
    public class addPurchaseOrderDetails {
        public String vendorReferenceNumber,vendorAddress,partNo;
        public addPurchaseOrderDetails(String VendorReferenceNumber, String VendorAddress, String PartNo) {
            this.vendorReferenceNumber = VendorReferenceNumber;
            this.vendorAddress = VendorAddress;
            this.partNo = PartNo;
        }
    }

    @Then("^Click on Add Comment Button$")
    public void clickOnAddCommentButton() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("clicking on Add Comment Button in detail page\n");
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(3000);
        page.clickOnAddComment();
        Thread.sleep(3000);
    }

    @Then("^Input values in the Comment pop-up$")
    public void inputValuesInTheCommentPopUp(DataTable table) throws Throwable {
        System.out.println("Entering details in the comments Pop-up\n");
        List<noteDetails> users1 = new ArrayList<noteDetails>();
        users1 = table.asList(noteDetails.class);
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(5000);
        for (noteDetails user1 : users1) {
            page.commentDetails(user1.title, user1.description);
        }
        Thread.sleep(2000);
    }

    public class noteDetails {
        public String title, description;
        public noteDetails(String Title, String Description) {
            this.title = Title;
            this.description = Description;
        }
    }

    @Then("^Click on Add Quote by Purchase Officer Button$")
    public void clickOnAddQuoteButton() throws Throwable {
        System.out.println("clicking on Add Quote Button - To raise quotes by purchase officer\n");
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(3000);
        page.clickAddQuote();
        Thread.sleep(3000);
    }

    @Then("^Click on Add Quote by Initiator Button$")
    public void clickOnAddQuoteByInitiator() throws Throwable {
        System.out.println("clicks Add Quote Button to add quotes by initiator\n");
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(3000);
        page.clickAddQuoteByInitiator();
        Thread.sleep(3000);
    }

    @Then("^Click on Add Attachment$")
    public void clickOnAddAttachment() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("clicks on Add Attachment button\n");
        Loginpage page = new Loginpage(base.driver);
        page.clickAddAttachment();
        Thread.sleep(10000);
        WebElement uploadElement = base.driver.findElement(By.id("attach_file"));
        uploadElement.sendKeys("C:\\Users\\Lakshmi.Lavanya\\Desktop\\PO flow.png");
        Thread.sleep(15000);
        page.attachSubmit();
    }

    @Then("^Input values in the Quotes pop-up$")
    public void inputValuesInTheQuotesPopUp(DataTable table) throws Throwable {
        System.out.println("Entering required details in the Quotes Pop-up\n");
        List<quotesDetails> users1 = new ArrayList<quotesDetails>();
        users1 = table.asList(quotesDetails.class);
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(5000);
        for (quotesDetails user1 : users1) {
            page.quotesDetails1(user1.quoteDate, user1.quoteTitle, user1.quoteVendor, user1.quotedPrice);
        }
        Thread.sleep(1000);
    }

    public class quotesDetails {
        public String quoteDate, quoteTitle, quoteVendor, quotedPrice;
        public quotesDetails(String QuoteDate, String QuoteTitle, String QuoteVendor, String QuotedPrice)
        {
            this.quoteDate = QuoteDate;
            this.quoteTitle = QuoteTitle;
            this.quoteVendor = QuoteVendor;
            this.quotedPrice = QuotedPrice;
        }
    }

    @Then("^Click on Submit button$")
    public void clickOnSubmitButton() throws Throwable {
        System.out.println("Click On request Submit button \n");
        Thread.sleep(5000);
        Loginpage page = new Loginpage(base.driver);
        page.addSubmit();
        Thread.sleep(15000);
    }

    @Then("^I entered invalid characters into WO# column$")
    public void enterInvalidChars() throws Throwable {
        System.out.println("Entering invalid characters in WO# grid\n");
        Thread.sleep(5000);
        Loginpage page = new Loginpage(base.driver);
        page.enterSpecialCharsinWOGrid();
        Thread.sleep(1000);
    }

    @Then("^I observed the no data available message$")
    public void captureTextMsg() throws Throwable {
        Thread.sleep(5000);
        Loginpage page = new Loginpage(base.driver);
        page.VerifyNoDataAvailableMsg();
        Thread.sleep(1000);
    }

    @Then("^I click on CancelChanges button$")
    public void clickOnCancelChangesButton() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Click On Cancel Changes button in detail page\n");
        Thread.sleep(5000);
        Loginpage page = new Loginpage(base.driver);
        page.clickCancelChangesButton();
        Thread.sleep(1000);
    }

    @Then("^Delete the Item$")
    public void deleteTheItem() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Click On Delete Icon - To delete the added items\n");
        Loginpage page = new Loginpage(base.driver);
        page.addDelete();
        Thread.sleep(5000);
    }

    @Then("^Click on Add Purchase Order$")
    public void clickOnAddPurchaseOrder() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("clicking on  Add purchase order Button\n");
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(3000);
        page.clickAddPO();
        Thread.sleep(3000);
    }

    @Then("^Input values in Add Purchase Order Page$")
    public void inputValuesInAddPurchaseOrderpage(DataTable table) throws Throwable {
       System.out.println("Entering required details in the Purchase Order Page\n");
        List<addPoDetails> users1 = new ArrayList<addPoDetails>();
        users1 = table.asList(addPoDetails.class);
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(5000);
        for (addPoDetails user1 : users1) {
            page.addPoDetails1(user1.poDate, user1.poVendor, user1.poSubject, user1.poReference,user1.poVendorAddress,user1.poItemName,
                    user1.poPartNo,user1.poDescription, user1.poQty,user1.poUnit);
        }
        Thread.sleep(5000);
    }
    public class addPoDetails {
        public String poDate, poVendor, poSubject, poReference,poVendorAddress,poItemName,poPartNo,poDescription,poQty,poUnit;
        public addPoDetails(String PoDate, String PoVendor, String PoSubject, String PoReference,String PoVendorAddress,String PoItemName,String PoPartNo,String PoDescription,String PoQty,String PoUnit)
        {
            this.poDate = PoDate;
            this.poVendor = PoVendor;
            this.poSubject = PoSubject;
            this.poReference = PoReference;
            this.poVendorAddress = PoVendorAddress;
            this.poItemName = PoItemName;
            this.poPartNo = PoPartNo;
            this.poDescription = PoDescription;
            this.poQty = PoQty;
            this.poUnit=PoUnit;
        }
    }

    @Then("^Click on Print PDF on PO Page$")
    public void clickOnPrintPDFOnPoPage() throws Throwable {
        System.out.println("clicks on Print PDF button on PO Page\n");
        Loginpage page = new Loginpage(base.driver);
        page.clickPrintPDFPo();
        Thread.sleep(5000);
        String currentWindow = base.driver.getWindowHandle();
        base.driver.switchTo().window(currentWindow);
    }

    @And("^Click on Close button on PO Page$")
    public void clickOnCloseButtonOnPoPage() throws Throwable {
        System.out.println("clicks on close button on PO Page\n");
        Loginpage page = new Loginpage(base.driver);
        page.clickClosePo();
        Thread.sleep(5000);
    }

    @And("^verify the status$")
    public void verifyStatus() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(10000);
        page.verifyStatus();
    }

    @And("^I verify the PO status$")
    public void verifyPurchaseOrderStatus() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(8000);
        page.verifyPOStatus();
    }

    @And("^I verify the Reference Number$")
    public void getReferenceNumber() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(13000);
        page.getReferenceFromPO();
    }

    @And("^I get the created Purchase Order Number$")
    public void getPONumber() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(10000);
        page.getCreatedPONumber();
    }

    @And("^Verify the approval status on Quotes Tab for 1st Quote$")
    public void verifyApprovalStatusforQuote1() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(10000);
        page.verifyQuote1Status();
    }

    @And("^Verify the approval status on Quotes Tab for 2nd Quote$")
    public void verifyApprovalStatusforQuote2() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(10000);
        page.verifyQuote2Status();
    }

    @Then("^I click on Confirm button$")
    public void clickOnConfirmButtonOnPOPage() throws Throwable {
        System.out.println("Confirm the created Work order by clicking on confirm button \n");
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(5000);
        page.clickConfirmButton();
    }

    @Then("^I click on Initiator Logout button$")
    public void clickInitiatorLogOutButton() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Clicks on Initiator Log Out Button \n");
        Loginpage page = new Loginpage(base.driver);
        page.clickOnInitiatorLogOutButton();
        Thread.sleep(20000);
    }

    @Then("^I click on Approver Logout button$")
    public void clickApproverLogOutButton() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Clicks on Approver Log Out Button \n");
        Loginpage page = new Loginpage(base.driver);
        page.clickOnApproverLogOutButton();
        Thread.sleep(5000);
    }

    @Then("^I click on Purchase officer role Logout button$")
    public void clickPurchaseOfficerLogOutButton() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Clicks on Purchase Officer Log Out Button \n");
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(5000);
        page.clickOnPurchaseOfficerLogOutButton();
        Thread.sleep(10000);
    }

    @Then("^I click on Procurement Manager role Logout button$")
    public void clickProcurementManagerLogOutButton() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Clicks on Procurement manager Log Out Button\n");
        Loginpage page = new Loginpage(base.driver);
        page.clickOnProcurementManagerLogOutButton();
        Thread.sleep(10000);
    }


    @Then("^click on quotes submit button$")
    public void clickQuotesSubmitButton() throws Throwable {
        System.out.println("Clicks on quotes submit button \n");
        Loginpage page = new Loginpage(base.driver);
        page.clickOnQuotesSubmitButton();
        Thread.sleep(10000);
    }

    @Then("^click on quotes delete button$")
    public void clickQuotesDeleteButton() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.clickOnQuotesDeleteButton();
        Thread.sleep(10000);
    }

    @And("^I click on Quotes tab$")
    public void clickQuotesTab() throws Throwable {
        System.out.println("Clicks on quotes tab \n");
        Loginpage page = new Loginpage(base.driver);
        page.clickOnQuotesTab();
        Thread.sleep(10000);
    }
    @And("^I click on Quotes Approve button$")
    public void clickQuotesApproveButton() throws Throwable {
        System.out.println("Clicks on quotes approve button \n");
        Loginpage page = new Loginpage(base.driver);
        page.clickOnQuotesApproveButton();
        Thread.sleep(10000);
    }

    @And("^I click on Quotes SendBack button$")
    public void clickQuotesSendBackButton() throws Throwable {
        System.out.println("Clicks on quotes sendback button \n");
        Loginpage page = new Loginpage(base.driver);
        page.clickOnQuotesSendBackButton();
        Thread.sleep(6000);
    }

    @And("^I click on Quotes Reject button$")
    public void clickQuotesRejectButton() throws Throwable {
        System.out.println("Clicks on quotes reject button\n");
        Loginpage page = new Loginpage(base.driver);
        page.clickOnQuotesRejectButton();
        Thread.sleep(7000);
    }

    @And("^I click on RaisePO button$")
    public void clickRaisePOButton() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.clickOnRaisePOButton();
        Thread.sleep(7000);
    }

    @And("^I click on ClosePO button$")
    public void clickClosePOButton() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.clickOnClosePOButton();
        Thread.sleep(7000);
    }

    @And("^I click on CloseWO button$")
    public void clickCloseWOButton() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.clickOnCloseWOButton();
        Thread.sleep(7000);
    }

    @And("^I click on Add Purchase Order button$")
    public void clickAddPurchaseOrderButton() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.clickOnAddPurchaseOrderButton();
        Thread.sleep(7000);
    }

    @And("^I click on view quote eye icon button$")
    public void clickQuotesEyeIcon() throws Throwable {
       System.out.println("Click on Eye button to view the quote details \n");
        Loginpage page = new Loginpage(base.driver);
        page.clickOnQuotesEyeIcon();
        Thread.sleep(10000);
    }

    @And("^I click on Approve button$")
    public void clickOnWOApproveButton() throws Throwable {
        System.out.println("Click on approve button to approve the WO request \n");
        Loginpage page = new Loginpage(base.driver);
        page.clickApproveButton();
        Thread.sleep(5000);
    }

    @And("^I click on SendBack button$")
    public void clickOnWOSendBackButton() throws Throwable {
        System.out.println("Click on Send back back button \n");
        Loginpage page = new Loginpage(base.driver);
        page.clickSendBackButton();
        Thread.sleep(5000);
    }

    @And("^I click on Reject button$")
    public void clickOnWORejectButton() throws Throwable {
        System.out.println("Click on work order Reject button \n");
        Loginpage page = new Loginpage(base.driver);
        page.clickRejectButton();
        Thread.sleep(4000);
    }

    @Then("^Get the created Work Order Number$")
    public void getWONumber(){
        System.out.println("Getting the created WO number from detail page \n");
        Loginpage page = new Loginpage(base.driver);
        page.getWorkOrderNum();
    }

    @Then("^I verified the Location error message$")
    public void getErrorMsgForLocation(){
        System.out.println("Verifying the error message if location dropdown has not been selected\n");
        Loginpage page = new Loginpage(base.driver);
        page.getErrMsgForLocationDropDown();
    }

    @Then("^I verified the error message when comments description is empty$")
    public void getErrorMsgForCommentsDesc(){
        System.out.println("Verifying the error message if comments description text box is empty\n");
        Loginpage page = new Loginpage(base.driver);
        page.getErrorMsgForCommentsDescription();
    }

    @Then("^I verified the error message when quotedPrice Value is empty$")
    public void getErrorMsgForQuotedPriceEmpty(){
        System.out.println("Verifying the error message if quoted price text box is empty\n");
        Loginpage page = new Loginpage(base.driver);
        page.getErrorMsgForQuotedPriceEmpty();
    }

    @Then("^I verify Submit button presence$")
    public void verifySubmitButton(){
        Loginpage page = new Loginpage(base.driver);
        page.verifySubmitButtonPresence();
    }

    @Then("^I verify Cancel Changes button presence$")
    public void verifyCancelChangesButton(){
        Loginpage page = new Loginpage(base.driver);
        page.verifyCancelChangesButtonPresence();
    }

    @Then("^I verify Close button presence$")
    public void verifyCloseButton(){
        Loginpage page = new Loginpage(base.driver);
        page.verifyCloseButtonPresence();
    }

    @Then("^I accept an alert$")
    public void acceptAlert(){
        Loginpage page = new Loginpage(base.driver);
        page.acceptAnAlert();
    }

    @Then("^I accept giving Add Quote Access to Initiator$")
    public void giveAddQuotesAccessAlert(){
        Loginpage page = new Loginpage(base.driver);
        page.acceptAddQuotesAccessAlert();
    }

    @Then("^I accept close purchase order alert$")
    public void acceptClosePOAlert(){
        Loginpage page = new Loginpage(base.driver);
        page.acceptClosePurchaseOrderAlert();
    }

    @Then("^I accept close work order alert$")
    public void acceptCloseWOAlert(){
        Loginpage page = new Loginpage(base.driver);
        page.acceptCloseWorkOrderAlert();
    }

    @Then("^I Navigate to Pending Approval tab and search the raised request$")
    public void clickOnPendingApprovalTab() throws Throwable{
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(10000);
        page.naviagteToPendingApproverTab();
    }

    @Then("^I Navigate to Requests tab and search the raised request$")
    public void clickOnPORequestsTab() throws Throwable{
        Loginpage page = new Loginpage(base.driver);
        page.naviagteToPORequestTab();
    }

    @Then("^I verified the Reset Values$")
    public void verifyWOFieldsResetValues(){
        Loginpage page = new Loginpage(base.driver);
        page.verifyWOFields();
    }

}