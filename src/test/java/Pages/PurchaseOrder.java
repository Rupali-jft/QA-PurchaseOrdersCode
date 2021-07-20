package Pages;

import Base.BaseUtil;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PurchaseOrder {
    private final WebDriver driver;
    private final JavascriptExecutor js;

    private final WebDriverWait wait;


    public PurchaseOrder(WebDriver driver, JavascriptExecutor js) {
        this.driver = driver;
        this.js = js;
        PageFactory.initElements(driver, this);
        this.wait=new WebDriverWait(this.driver,15);


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
    @FindBy(how = How.XPATH, using = "/html[1]/body[1]/div[8]/div[1]/table[1]/tbody[1]/tr[4]/td[2]")
    private WebElement quotedateselection;
    @FindBy(how = How.XPATH, using = "//form[@id='Add_Quotes']/div[2]/div/div[2]/input")
    private WebElement quoTitle;
    @FindBy(how = How.XPATH, using = "//input[@id='quote_vendor']")
    private WebElement quoVendor;
    @FindBy(how = How.XPATH, using = "//input[@id='quotedprice0']")
    private WebElement quoPrice;
    @FindBy(how = How.XPATH, using = "//input[@id='total_price0']")
    private WebElement totPrice;

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
public void enterQuoteDate(){
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
    public void clickTotalPriceBox(){
            totPrice.click();
    }

}
