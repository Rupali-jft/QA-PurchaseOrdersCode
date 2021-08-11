package Pages;

import Base.BaseUtil;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.*;
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
    @FindBy(how = How.XPATH, using = "//body/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/button[2]")
    private WebElement rejectBtn;
    @FindBy(how = How.XPATH, using = "//tbody/tr[1]/td[5]/center[1]/button[1]")
    private WebElement deleteBtn;
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
    public void clickAddQuoteByInitiator() {
        try {
            addQuoteBtn.click();
            clickOnByInitiator.click();
        } catch (Exception e) {
        }
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
        quoDate.click();
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

    //To verify the Reject button presence
    public boolean verifyRejectButtonPresence() {
        try{
            return  rejectBtn.isDisplayed();}
        catch(NoSuchElementException ignored){
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
    public void RequestsTabStatusCheck(String status) throws Exception {
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

        System.out.println("Changing status of WO# " + passingstringvalue + " to " + status.toUpperCase());

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
    public void verifyApproveButtonDisabled(){
        Boolean condition=approveBtn.isEnabled();
        Assert.assertFalse(condition, "Approve button is not disabled");
        System.out.println("Good Approve button disabled");
    }
    public void verifySendBackButtonDisabled(){
        Boolean condition=sendbackBtn.isEnabled();
        Assert.assertFalse(condition, "SendBack Button is not disabled");
        System.out.println("Good SendBack Button disabled");
    }
    public void verifyRejectButtonDisabled(){
        Boolean condition=rejectQuoteBtn.isEnabled();
        Assert.assertFalse(condition, "Reject Button is not disabled");
        System.out.println("Good Reject Button disabled");
    }
    public void verifySubmitButtonDisabled(){
        Boolean condition=submitBtn.isEnabled();
        Assert.assertFalse(condition, "Submit button is not disabled");
        System.out.println("Good Submit button disabled");
    }
}

