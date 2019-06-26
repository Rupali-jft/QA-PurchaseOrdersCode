package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;


public class Loginpage  {

    WebDriver driver;
    public Loginpage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Login Page
    @FindBy(how = How.ID, using = "email")
    public WebElement txtEmail;
    @FindBy(how = How.ID, using = "password")
    public WebElement txtPassword;
    @FindBy(how = How.ID, using = "submit")
    public WebElement btnSignIn;

    public void Login(String Email, String Password) {
        txtEmail.sendKeys(Email);
        txtPassword.sendKeys(Password);
        //highLightElement(driver,txtEmail);
        //highLightElement(driver,txtPassword);
    }

    //CLick on Sign-in Button
    public void SignInButton() {
        btnSignIn.click();
    }

    @FindBy(how = How.XPATH, using = "//*[contains(text(),'Purchase Order')]")
    public WebElement link;


    public void POLink() {
        link.click();
    }

    /*   ------Size of table and pagination*/
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

    /*   ------Get Values for Initiator role KPI*/

    @FindBy(how = How.XPATH, using = "//*[@id=\"kpi1Count\"]")
    public WebElement reqPenApprovalKpiValue;
    @FindBy(how = How.XPATH, using = "//*[@id=\"kpi2Count\"]")
    public WebElement quoPenApprovalKpiValue;
    @FindBy(how = How.XPATH, using = "//*[@id=\"kpi3Count\"]")
    public WebElement returnedRequestsKpiValue;
    @FindBy(how = How.XPATH, using = "//*[@id=\"kpi4Count\"]")
    public WebElement rejectedRequestsKpiValue;


    public void InitiatorKPIValue()
    {
        String RequestPendingApprovalCount = reqPenApprovalKpiValue.getText();
        System.out.println("Request Pending Approval KPI Count is :" + RequestPendingApprovalCount);
        String QuotePendingApprovalCount = quoPenApprovalKpiValue.getText();
        System.out.println("Quote Pending Approval KPI Count is :" + QuotePendingApprovalCount);
        String ReturnedRequestsCount = returnedRequestsKpiValue.getText();
        System.out.println("My New Business KPI Count is :" + ReturnedRequestsCount);
        String RejectedRequestsCount = rejectedRequestsKpiValue.getText();
        System.out.println("My Lost Business KPI Count is :" + RejectedRequestsCount);
    }


    /*   ------Get Values for Procurement role KPI*/

    @FindBy(how = How.XPATH, using = "//*[@id=\"kpi1Count\"]")
    public WebElement reqPenApprovalProcurementKpiValue;
    @FindBy(how = How.XPATH, using = "//*[@id=\"kpi2Count\"]")
    public WebElement returnedRequestsProcurementKpiValue;

    public void ProcurementKPIValue()
    {
        String RequestPendingApprovalProcurementCount = reqPenApprovalProcurementKpiValue.getText();
        System.out.println("Request Pending Approval KPI Count is :" + RequestPendingApprovalProcurementCount);
        String ReturnedRequestsProcurementCount = returnedRequestsProcurementKpiValue.getText();
        System.out.println("Quote Pending Approval KPI Count is :" + ReturnedRequestsProcurementCount);
    }

    //Click on Add Policy Button---------------------
    @FindBy(how = How.ID, using = "addPORequest")   //Add policy
    public WebElement clickAddRequestBtn;

    public void clickAddRequest()
    {
        clickAddRequestBtn.click();
        //highLightElement(driver,clickAddRequestBtn);
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"typeaheadValue\"]")
    public WebElement txtGlobalSearch;

    @FindBy(how = How.XPATH, using = " //*[@id=\"the-basics-new\"]/span/div/div/p/a")
    public WebElement txtGlobalSearch1;

    public void GlobalSearch(String GlobalSearch) {
        try {
            txtGlobalSearch.sendKeys(GlobalSearch);
            Thread.sleep(5000);
            txtGlobalSearch1.click();
        } catch (Exception e) {
        }

    }

    //Initializing elements in Add Policy--------------------------------------------

    @FindBy(how = How.XPATH, using = "//*[@id=\"request_title\"]")
    public WebElement addWOTitle;
    @FindBy(how = How.XPATH, using = "//*[@id=\"request_location\"]")
    public WebElement addLocation;
    @FindBy(how = How.XPATH, using = "//*[@id=\"request_Department\"]")
    public WebElement addDepartment;/*
    @FindBy(how = How.XPATH, using = "//*[@id=\"business-type-form\"]")
    public WebElement addBusinessType;
    @FindBy(how = How.XPATH, using = "//*[@id=\"isNew\"]")
    public WebElement addNewExisting;
    @FindBy(how = How.XPATH, using = "//*[@id=\"policy-add-date\"]") //*[@id="policy-add-date"]
    public WebElement addDate;
    @FindBy(how = How.XPATH, using = "//*[@id=\"policy-insured\"]")
    public WebElement addEmployerGroup;
    @FindBy(how = How.XPATH, using = "//*[@id=\"policy-coverage-type\"]")
    public WebElement addCoverageType;
    @FindBy(how = How.XPATH, using = "//*[@id=\"policy-prior-carrier\"]")
    public WebElement addPriorCarrier;
    @FindBy(how = How.XPATH, using = "//*[@id=\"PriorPlanName\"]")
    public WebElement addPriorPlanName;
    @FindBy(how = How.XPATH, using = "//*[@id=\"policy-expiring-premium\"]")
    public WebElement addExpiringPremium;
    @FindBy(how = How.XPATH, using = "//*[@id=\"policy-commission\"]")
    public WebElement addExpiringCommission;
    @FindBy(how = How.XPATH, using = "//*[@id=\"policy-renewal-carrier\"]")
    public WebElement addRenewalCarrier;
    @FindBy(how = How.XPATH, using = "//*[@id=\"RenewalNewPlanName\"]")
    public WebElement addRenewalNewPlanName;
    @FindBy(how = How.XPATH, using = "//*[@id=\"policy-renewal-premium\"]")
    public WebElement addRenewalPremium;
    @FindBy(how = How.XPATH, using = "//*[@id=\"policy-renewal-commission\"]")
    public WebElement addRenewalCommission;
    @FindBy(how = How.XPATH, using = "//*[@id=\"policy_lead_source\"]")
    public WebElement addLeadSource;
    @FindBy(how = How.XPATH, using = "//*[@id=\"policy-lead-source\"]")
    public WebElement addLeadSource1;*/

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

    public void addRequestButton(String wotitle,String location, String department, String itemName, String description, String quantity,String itemName1,String description1,String quantity1) {
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
            addItem.click();
            addItemName1.sendKeys(itemName1);
            addDescription1.sendKeys(description1);
            addQuantity1.sendKeys(quantity1);
            clickItem1.click();

            /*addBusinessType.sendKeys(businessType);
            addNewExisting.sendKeys(newExisting);
            addDate.sendKeys(date);
            addEmployerGroup.sendKeys(employerGroup);
            addCoverageType.sendKeys(coverageType);
            addPriorCarrier.sendKeys(priorCarrier);
            addPriorPlanName.sendKeys(priorPlanName);
            addExpiringPremium.sendKeys(expiringPremium);
            addExpiringCommission.sendKeys(expiringCommission);
            addRenewalCarrier.sendKeys(renewalNewCarrier);
            addRenewalNewPlanName.sendKeys(renewalNewPlanName);
            addRenewalPremium.sendKeys(renewalNewPremium);
            addRenewalCommission.sendKeys(renewalNewCommission);
            addLeadSource.sendKeys(leadSource);*/
        } catch (Exception e) {
        }
    }



    //Entering details into WO Entry section

    @FindBy(how = How.XPATH, using = "//*[@id=\"business-type-form\"]")
    public WebElement moveBusinessType;

    @FindBy(how = How.XPATH, using = "//*[@id=\"note_title\"]")
    public WebElement noteTitle;
    @FindBy(how = How.XPATH, using = "//*[@id=\"note_desc\"]")
    public WebElement noteDescription;
    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[1]/div/div[1]/div/div/button[6]")
    public WebElement addNotes;
    @FindBy(how = How.XPATH, using = "//*[@id=\"notes_submit\"]")
    public WebElement noteSubmit;

    public void clickAddNote()
    {
        addNotes.click();
        //highLightElement(driver,addNotes);
    }
    public void noteDetails(String title, String description)
    {
        // Notes section tab
        noteTitle.sendKeys(title);
        noteDescription.sendKeys(description);
        noteSubmit.click();
    }

    //Adding Quote

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[1]/div/div[1]/div/div[1]/button[5]")
    public WebElement addQuote;
    @FindBy(how = How.XPATH, using = "//*[@id=\"request_status\"]")
    public WebElement status;

    public void clickAddQuote()
    {
        try
        {
            status.sendKeys("Pending Quotes");
            addSubmitButton.click();
            Thread.sleep(5000);
            addQuote.click();
        }
        catch (Exception e){}
        //highLightElement(driver,addNotes);
    }

    //Adding Attachment
    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[1]/div/div[1]/div/div/button[7]")
    public WebElement addAttach;

    public void clickAddAttachment()
    {
        try
        {
        Thread.sleep(5000);
        addAttach.click();
        Thread.sleep(5000);
        //highLightElement(driver,addNotes);
        }
        catch (Exception e){}
    }
    @FindBy(how = How.XPATH, using = "//*[@id=\"attach_desc\"]")
    public WebElement attachDesc;

    @FindBy(how = How.XPATH, using = "//*[@id=\"attachment_submit\"]")
    public WebElement attachSubmit;

    public void attachSubmit()
    {
        try
        {
            Thread.sleep(5000);
            attachDesc.sendKeys("File");
            Thread.sleep(5000);
            attachSubmit.click();
            //highLightElement(driver,addNotes);
        }
        catch (Exception e){}
    }

    // Entering values in the Quotes Pop-up

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


    public void quotesDetails1(String quotedate, String quoteTitle,String quoteVendor, String quotedPrice)
    {
        quoDate.sendKeys(quotedate);
        quoTitle.sendKeys(quoteTitle);
        quoVendor.sendKeys(quoteVendor);
        quoPrice.sendKeys(quotedPrice);
        markSelected.click();
        quoSubmit.click();
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"notes_sects\"]")
    public WebElement notesTab;

    @FindBy(how = How.XPATH, using = "//*[@id=\"dtnotes_wrapper\"]/tbody/tr/td[5]/center/span")
    public WebElement notesIcon;

    public void clickNotesTab()
    {
        try
        {
            notesTab.click();
            notesIcon.click();
        }
        catch (Exception e){}
    }
    @FindBy(how = How.XPATH, using = "//*[@id=\"history_sects\"]")
    public WebElement historyTab;

    public void clickHistoryTab()
    {
        historyTab.click();
    }

    // Clicking on Add Record Submit Button

    @FindBy(how = How.XPATH, using = "//*[@id=\"request_add\"]")
    public WebElement addSubmitButton;

    public void addSubmit()
    {
        //okButton.click();
        addSubmitButton.click();
        //highLightElement(driver,addSubmitButton);
    }

    // Clicking on Delete Button

    @FindBy(how = How.XPATH, using = "//*[@id=\"serviceDetails\"]/tbody/tr[2]/td[4]/a[3]/i")
    public WebElement deleteButton;

    public void addDelete()
    {
        deleteButton.click();
        //highLightElement(driver,addSubmitButton);
    }

    // Adding Purchase Order

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[1]/div/div[1]/div/div[1]/button[8]")
    public WebElement addPO;
    //@FindBy(how = How.XPATH, using = "//*[@id=\"request_status\"]")
    // public WebElement status;

    public void clickAddPO()
    {
        addPO.click();
        //highLightElement(driver,addNotes);
    }
    // Entering values in the Quotes Pop-up

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

    public void addPoDetails1(String poDate, String poVendor,String poSubject, String poReference,String poVendorAddress,String poItemName,String poPartNo, String poDescrription, String poQty, String poUnit)
    {
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
    // Clicking on Print PDF PO
    @FindBy(how = How.XPATH, using = "//*[@id=\"PDFURL\"]/button")
    public WebElement printPDFPo;

    public void clickPrintPDFPo()
    {
        try
        {
            Thread.sleep(5000);
            printPDFPo.click();
            Thread.sleep(5000);
            //highLightElement(driver,addNotes);
        }
        catch (Exception e){}
    }
    //Click Close PO

    @FindBy(how = How.XPATH, using = "//*[@id=\"po_close\"]")
    public WebElement poClose;

    public void clickClosePo()
    {
        try
        {
            Thread.sleep(5000);
            poClose.click();
            Thread.sleep(5000);
            //highLightElement(driver,addNotes);
        }
        catch (Exception e){}
    }






}

