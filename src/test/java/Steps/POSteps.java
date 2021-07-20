package Steps;

import Base.BaseUtil;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        driver.findElement(By.xpath("//tbody/tr[1]/td[6]/center[1]/span[1]")).click();
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
        System.out.println("GST under address section :\n"+arg1+"-" + GST);
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


}

