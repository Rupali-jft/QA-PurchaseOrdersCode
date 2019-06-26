package Steps;

import Pages.BaseUtil;
import Pages.Loginpage;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.lexer.De;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;
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
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Navigates to DEV Login page\n");
        base.driver.get("https://dev.patracorp.net");
        base.driver.manage().window().maximize();
    }

    @And("^I enter login credentials$")
    public void iEnterLoginCredentials(DataTable table) {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Entering email address and password\n");
        //create arraylst
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
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Clicks Sign In button\n");
        Loginpage page = new Loginpage(base.driver);
        page.SignInButton();
        Thread.sleep(5000);
        page.POLink();
        Thread.sleep(5000);
        String parentWindow = base.driver.getWindowHandle();
        Set<String> handles = base.driver.getWindowHandles();
        for (String windowHandle : handles) {
            if (!windowHandle.equals(parentWindow)) {
                base.driver.switchTo().window(windowHandle);
            }
        }
        Thread.sleep(5000);
    }

    @And("^Get the Initiator KPI Count$")
    public void getTheInitiatorKpiCOunt() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Thread.sleep(5000);
        Loginpage page = new Loginpage(base.driver);
        page.InitiatorKPIValue();
    }


    @And("^Get the Procurement KPI Count$")
    public void getTheProcurementKpiCOunt() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Thread.sleep(5000);
        Loginpage page = new Loginpage(base.driver);
        page.KPIValue();
    }

    @And("^Pagination value")
    public void paginationValue(DataTable table) throws Throwable {
        // Write code here that turns the phrase above into concrete actions

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

    @And("^Get the KPI Count$")
    public void getTheKpiCOunt() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Thread.sleep(5000);
        Loginpage page = new Loginpage(base.driver);
        page.KPIValue();
    }

    @When("^Click on Add Request$")
    public void clickOnAddRequest() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("clicks Add Request\n");
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(5000);
        page.clickAddRequest();
        Thread.sleep(5000);
    }

    @Then("^Enter Add Request Details$")
    public void enterAddRequestDetails(DataTable table) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Entering Add Request Details\n");
        List<addRequest> users = new ArrayList<addRequest>();
        users = table.asList(addRequest.class);
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(5000);
        for (addRequest user : users) {
            page.addRequestButton(user.wotitle, user.location, user.department, user.itemName, user.description, user.quantity, user.itemName1, user.description1, user.quantity1);

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

    @Then("^Click on Add Note Button$")
    public void clickOnAddNoteButton() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("clicks Add Note Button\n");
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(5000);
        page.clickAddNote();
        Thread.sleep(5000);
    }

    @Then("^Input values in the Note pop-up$")
    public void inputValuesInTheNotePopUp(DataTable table) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Entering details in the Note Pop-up\n");
        List<noteDetails> users1 = new ArrayList<noteDetails>();
        users1 = table.asList(noteDetails.class);
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(5000);
        for (noteDetails user1 : users1) {
            page.noteDetails(user1.title, user1.description);
        }
        Thread.sleep(5000);
    }

    public class noteDetails {

        public String title, description;

        public noteDetails(String Title, String Description) {
            this.title = Title;
            this.description = Description;
        }
    }

    @Then("^Click on Add Quote Button$")
    public void clickOnAddQuoteButton() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("clicks Add Quote Button\n");
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(5000);
        page.clickAddQuote();
        Thread.sleep(5000);
    }


    @Then("^Click on Add Attachment$")
    public void clickOnAddAttachment() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("clicks Add Attachment\n");
        Loginpage page = new Loginpage(base.driver);
        page.clickAddAttachment();
        Thread.sleep(10000);
        WebElement uploadElement = base.driver.findElement(By.id("attach_file"));
        uploadElement.sendKeys("C:\\Renuka\\screenshot.png");
        Thread.sleep(10000);
        page.attachSubmit();
    }
    @Then("^Input values in the Quotes pop-up$")
    public void inputValuesInTheQuotesPopUp(DataTable table) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Entering details in the Quotes Pop-up\n");
        List<quotesDetails> users1 = new ArrayList<quotesDetails>();
        users1 = table.asList(quotesDetails.class);
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(5000);
        for (quotesDetails user1 : users1) {
            page.quotesDetails1(user1.quoteDate, user1.quoteTitle, user1.quoteVendor, user1.quotedPrice);
        }
        Thread.sleep(5000);
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

    @Then("^Click on Notes Tab$")
    public void clickOnNotesTab() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("clicks on Notes Tab\n");
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(5000);
        page.clickNotesTab();
        Thread.sleep(5000);
    }

    @And("^Click on History tab$")
    public void clickOnHistoryTab() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("clicks on History Tab\n");
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(5000);
        page.clickHistoryTab();
        Thread.sleep(5000);
    }

    @Then("^Click on Submit button$")
    public void clickOnSubmitButton() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Click On Submit\n");
        Thread.sleep(7000);
        Loginpage page = new Loginpage(base.driver);
        page.addSubmit();
        Thread.sleep(7000);
    }

    @Then("^Delete the Item$")
    public void deleteTheItem() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Click On Delete Icon\n");
        Loginpage page = new Loginpage(base.driver);
        page.addDelete();
        Thread.sleep(7000);
    }
    @Then("^Click on Add Purchase Order$")
    public void clickOnAddPurchaseOrder() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("clicks Add Quote Button\n");
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(5000);
        page.clickAddPO();
        Thread.sleep(5000);
    }

    @Then("^Input values in Add Purchase Order Page$")
    public void inputValuesInAddPurchaseOrderpage(DataTable table) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Entering details in the Purchase Order Page\n");
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
        // Write code here that turns the phrase above into concrete actions
        System.out.println("clicks on Print PDF on PO Page\n");
        Loginpage page = new Loginpage(base.driver);
        page.clickPrintPDFPo();
        Thread.sleep(5000);
        String currentWindow = base.driver.getWindowHandle();
        base.driver.switchTo().window(currentWindow);
    }
    @And("^Click on Close button on PO Page$")
    public void clickOnCloseButtonOnPoPage() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("clicks on Print PDF on PO Page\n");
        Loginpage page = new Loginpage(base.driver);
        page.clickClosePo();
        Thread.sleep(5000);
    }

}