package Steps;

import Base.BaseUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
}
