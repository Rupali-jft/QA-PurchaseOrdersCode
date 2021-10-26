package Steps;

import Base.BaseUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class FormSteps extends BaseUtil {

    @When("I click the {string} button")
    public void iClickTheButton(String button) {
        BaseUtil.pageLoaded();
        Assert.assertTrue(commonForm.commonButton(button), button + " button could not be found on page");
        BaseUtil.pageLoaded();
        System.out.println("Clicking " + button + " button.");
    }

    @And("I select {string} from the {string} drop down")
    public void iSelectFromTheDropDown(String selection, String dropDown) {
        BaseUtil.pageLoaded();
        System.out.println("Selecting " + selection + " from " + dropDown + " drop down.");
        Assert.assertTrue(commonForm.commonDropDownSelect(dropDown, selection),
                "Could not select " + selection + " from the " + dropDown + " dropdown");

        valueStore.put(dropDown, selection);
    }

    @And("I edit the following drop downs")
    public void iEditTheFollowingDropDowns(List<Map<String, String>> table) {
        Map<String, String> dropDowns = table.get(0);
        dropDowns.forEach((key, value) -> {
            System.out.println("Selecting " + value + " from " + key + " drop down");
            Assert.assertTrue(commonForm.commonDropDownSelect(key, value),
                    "Could not select " + value + " from " + key + " drop down");
            editedValues.put(key, value);
            valueStore.put(key, value);
        });

    }

    @And("I get the value for the {string} drop down")
    public void iGetTheValueForTheDropDown(String dropDown) {
        String value = commonForm.commonDropDownRead(dropDown);
        Assert.assertNotNull(value, "Could not read from " + dropDown + " drop down");

        System.out.println("Entry in " + dropDown + " drop down: " + value);
    }

    @And("I enter {string} in the {string} field")
    public void iEnterInTheField(String text, String fieldName) {
        String trackEntry = text;
        String dateString = dateFormat.format(new Date());
        String timeString = timeFormat.format(new Date());
        String random = new SimpleDateFormat("DDDyyHHmmss").format(new Date());

        if (trackEntry.contains("<current date>")) {
            trackEntry = trackEntry.replaceAll("<current date>", dateString + " " + timeString);
        }
        if (trackEntry.contains("<random>")) {
            trackEntry = trackEntry.replaceAll("<random>", random);
        }
        if (trackEntry.contains("<gmail>")) {
            String email = login.getLogins().getProperty("gmail.email");
            if (trackEntry.contains("+")) {
                String modifier = trackEntry.substring(trackEntry.indexOf('+'));
                String[] eSplit = email.split("@");
                email = eSplit[0] + modifier + "@" + eSplit[1];
            }
            trackEntry = email;
        }

        System.out.println("Entering " + trackEntry + " into " + fieldName + " field.");

        Assert.assertTrue(commonForm.commonFieldEnter(fieldName, trackEntry),
                "Field labeled " + fieldName + " is not present on page!");

        valueStore.put(fieldName, trackEntry);
    }

    @And("I edit the following fields")
    public void iEditTheFollowingFields(List<Map<String, String>> table) {
        String dateString = dateFormat.format(new Date());
        String timeString = timeFormat.format(new Date());
        String random = new SimpleDateFormat("DDDyyHHmmss").format(new Date());
        Map<String, String> fields = table.get(0);
        fields.forEach((key, value) -> {
            if (value.contains("<current date>")) {
                value = value.replaceAll("<current date>", dateString + " " + timeString);
            }
            if (value.contains("<random>")) {
                value = value.replaceAll("<random>", random);
            }
            if (value.contains("<gmail>")) {
                String email = login.getLogins().getProperty("gmail.email");
                if (value.contains("+")) {
                    String modifier = value.substring(value.indexOf('+'));
                    String[] eSplit = email.split("@");
                    email = eSplit[0] + modifier + "@" + eSplit[1];
                }
                value = email;
            }
            System.out.println("Entering " + value + " into " + key + " field");
            Assert.assertTrue(commonForm.commonFieldEnter(key, value), "Could not find " + key + " field!");
            editedValues.put(key, value);
            valueStore.put(key, value);
        });
    }

    @And("I get the text from the {string} field")
    public void iGetTheTextFromTheField(String field) {
        String fieldText = commonForm.commonFieldRead(field);
        System.out.println("Text currently visible in " + field + " field: " + fieldText);
        valueStore.put(field, fieldText);
    }

    @And("I enter {string} in the {string} text box")
    public void iEnterInTheTextBox(String text, String textArea) {
        String trackEntry;
        String dateString = dateFormat.format(new Date());
        String timeString = timeFormat.format(new Date());

        if (text.contains("<current date>")) {
            trackEntry = text.replaceAll("<current date>", dateString + " " + timeString);
        } else {
            trackEntry = text;
        }

        System.out.println("Entering " + trackEntry + " into " + textArea + " text box.");

        Assert.assertTrue(commonForm.commonTextAreaEnter(textArea, trackEntry),
                "Could not find " + textArea + " text area on page");

        valueStore.put(textArea, trackEntry);
    }

    @And("I edit the following text areas")
    public void iEditTheFollowingTextAreas(List<Map<String, String>> table) {
        String dateString = dateFormat.format(new Date());
        String timeString = timeFormat.format(new Date());
        Map<String, String> fields = table.get(0);
        fields.forEach((key, value) -> {
            if (value.contains("<current date>")) {
                value = value.replaceAll("<current date>", dateString + " " + timeString);
            }
            System.out.println("Entering " + value + " into " + key + " field");
            Assert.assertTrue(commonForm.commonTextAreaEnter(key, value), "Could not find " + key + " text area!");
            editedValues.put(key, value);
            valueStore.put(key, value);
        });
    }

    @And("I enter the following information into the form")
    public void iEnterTheFollowingInformationIntoTheForm(Map<String, String> table) {
        String dateString = dateFormat.format(new Date());
        String timeString = timeFormat.format(new Date());
        String random = new SimpleDateFormat("DDDyyHHmmss").format(new Date());
        table.forEach((key, value) -> {
            boolean foundItem = false;
            if (value.contains("<current date>")) {
                value = value.replaceAll("<current date>", dateString + " " + timeString);
            }
            if (value.contains("<random>")) {
                value = value.replaceAll("<random>", random);
            }
            if (value.contains("<gmail>")) {
                String email = login.getLogins().getProperty("gmail.email");
                if (value.contains("+")) {
                    String modifier = value.substring(value.indexOf('+'));
                    String[] eSplit = email.split("@");
                    email = eSplit[0] + modifier + "@" + eSplit[1];
                }
                value = email;
            }
            if (commonForm.commonFieldEnter(key, value)) {
                foundItem = true;
                System.out.println("Entering \"" + value + "\" into \"" + key + "\" field");
            } else if (commonForm.commonDropDownSelect(key, value)) {
                foundItem = true;
                System.out.println("Selecting \"" + value + "\" from \"" + key + "\" drop down");
            } else if (commonForm.commonTextAreaEnter(key, value)) {
                foundItem = true;
                System.out.println("Entering \"" + value + "\" into \"" + key + "\" text area");
            }
            Assert.assertTrue(foundItem, "Could not find " + key + " field, drop down, or text area!");
            valueStore.put(key, value);
        });
    }

    @And("I set the {string} date picker to today's date")
    public void iSetTheDatePickerToTodaySDate(String datePicker) {
        SimpleDateFormat day = new SimpleDateFormat("d");
        SimpleDateFormat month = new SimpleDateFormat("MMMM");
        SimpleDateFormat year = new SimpleDateFormat("yyyy");
        Map<String, String> dateTime = new HashMap<>();
        dateTime.put("Day", day.format(new Date()));
        dateTime.put("Month", month.format(new Date()));
        dateTime.put("Year", year.format(new Date()));
        dateTime.put("Time", "12:00");
        dateTime.put("AM / PM", "AM");
        if (!commonForm.commonDatePick(datePicker, dateTime.get("Day"), dateTime.get("Month"), dateTime.get("Year"))) {
            commonForm.commonDatePick(datePicker, dateTime);
        }
    }

    @And("I set the date in the {string} date picker to")
    public void iSetTheDateInTheDatePickerTo(String datePicker, List<Map<String, String>> table) {
        Map<String, String> selections = table.get(0);
        if (selections.containsKey("Time")) {
            Assert.assertTrue(commonForm.commonDatePick(datePicker, selections), "Could not enter date into " + datePicker + " date picker");
            System.out.println("Entering " + selections.get("Month") + " " + selections.get("Day") + ", " + selections.get("Year") + " " + selections.get("Time") + selections.get("AM / PM") + " into " + datePicker + " date picker.");
        } else {
            Assert.assertTrue(commonForm.commonDatePick(datePicker, selections.get("Day"), selections.get("Month"), selections.get("Year")), "Could not enter date into " + datePicker + " date picker");

            System.out.println("Entering " + selections.get("Month") + " " + selections.get("Day") + ", " + selections.get("Year") + " into " + datePicker + " date picker.");
        }
    }

    @And("I click {string} checkbox")
    public void iClickCheckbox(String checkBox) {
        System.out.println("Clicking " + checkBox + " check box");
        Assert.assertTrue(commonForm.commonCheckBoxClick(checkBox), checkBox + " checkbox could not be found!");
    }

    @And("I select the {string} checkbox")
    public void iSelectCheckbox(String checkBox) {
        BaseUtil.pageLoaded();
        System.out.println("Selecting the " + checkBox + " checkbox.");
        if (!commonForm.commonCheckBoxSelected(checkBox)) {
            commonForm.commonCheckBoxClick(checkBox);
            BaseUtil.pageLoaded();
        }
        Assert.assertTrue(commonForm.commonCheckBoxSelected(checkBox), checkBox + " checkbox was not selected OR could not be found!");
        //valueStore.put(checkBox, "true");
    }

    // could possibly combine this with the previous stepdef as "I {string} the {string} checkbox"
    @And("I deselect the {string} checkbox")
    public void iDeselectCheckbox(String checkBox) {
        BaseUtil.pageLoaded();
        System.out.println("Selecting the " + checkBox + " checkbox.");
        if (commonForm.commonCheckBoxSelected(checkBox)) {
            commonForm.commonCheckBoxClick(checkBox);
            BaseUtil.pageLoaded();
        }
        Assert.assertNotNull(commonForm.commonCheckBox(checkBox), checkBox + " checkbox could not be found!");
        Assert.assertFalse(commonForm.commonCheckBoxSelected(checkBox), checkBox + " checkbox was selected, but was expected to be deselected.");
        //valueStore.put(checkBox, "false");
    }

    @Then("The {string} checkbox is {string}")
    public void checkboxSelected(String checkBox, String expectation) {
        // expectation should be "selected" or "deselected" (or "checked" or "unchecked")
        boolean boolExpect = false;
        if (expectation.equalsIgnoreCase("selected") || expectation.equalsIgnoreCase("checked")) {
            boolExpect = true;
        }

        BaseUtil.pageLoaded();
        System.out.println("Checking that the " + checkBox + " checkbox is " + expectation + ".");
        if (commonForm.commonCheckBox(checkBox) != null) {
            Assert.assertEquals(commonForm.commonCheckBoxSelected(checkBox), boolExpect);
        } else {
            Assert.fail(checkBox + " checkbox could not be found!");
        }
    }

    @And("I set the {string} date picker to {int} {string} in the {string}")
    public void iSetTheDatePickerToInThe(String datePicker, int count, String units, String direction, List<Map<String, String>> table) {
        Map<String, String> tCheck = table.get(0);
        boolean hasTime = tCheck.get("Has Time").equalsIgnoreCase("yes");
        commonForm.commonDateShift(datePicker, count, units, direction, hasTime);
    }

    @When("I delete the attachment")
    public void iDeleteTheAttachment() throws InterruptedException {
        System.out.println("Checking that the delete action is available.");
        int numberOfAttachments = driver.findElements(By.id("attachment_delete")).size();
        while (numberOfAttachments > 0) {
            System.out.println("Deleting file.");
            wait.until(ExpectedConditions.elementToBeClickable(By.id("attachment_delete")));
            driver.findElement(By.id("attachment_delete")).click();
            BaseUtil.pageLoaded();
            commonForm.commonButton("confirm");
            BaseUtil.pageLoaded();
            // After confirming a delete, the user is redirected to the Details tab. Wait for a Details tab element to be visible before continuing.
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pc_Comments")));//Nothing special about this element. Just unique to Detail tab.
            driver.findElement(By.linkText("Attachments")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("th-LinkWOAttachments")));
            numberOfAttachments = driver.findElements(By.id("attachment_delete")).size();
        }
        Assert.assertTrue(driver.findElements(By.id("attachment_delete")).isEmpty());
    }

    @And("I upload an attachment")
    public void iUploadAnAttachment() {
        commonForm.commonUploadAttachment(attachLocation);
    }

    @And("I upload an {string} attachment and verify is uploaded")
    public void iUploadAnAttachment(String fileType) {
        switch(fileType.toLowerCase())
        {
            case "pdf":
                attachLocation=pdfAttachLocation;
                attachName=pdfAttachName;
                break;
            case "doc":
                attachLocation=docAttachLocation;
                attachName=docAttachName;
                break;
        }
        commonForm.commonUploadAttachment(attachLocation);
        commonForm.fileWillBeDisplayedInTheAttachmentsGrid(attachName);
    }

    @And("I upload an attachment more than 30MB")
    public void iUploadAnAttachmentMoreThan30MB() {
        attachLocation=moreThan30MbAttachLocation;
        commonForm.commonUploadAttachment(attachLocation);
    }

    @Then("The following elements exist")
    public void fieldsExist(List<String> table) {
        pageLoaded();
        //Need atomic boolean to work with Java lambda expressions.
        AtomicBoolean elementExists = new AtomicBoolean(false);
        table.forEach(value -> {
            System.out.println("Checking the existance of: " + value);
            if (commonForm.commonField(value) != null) {
                elementExists.set(true);
            } else if (commonForm.commonDropDown(value) != null) {
                elementExists.set(true);
            } else if (commonForm.commonTextArea(value) != null) {
                elementExists.set(true);
            } else if (commonForm.commonBtnGet(value) != null) {
                elementExists.set(true);
            } else if (commonForm.commonCheckBoxGet(value) != null) {
                elementExists.set(true);
            } else {
                boolean linkExists;
                try {
                    linkExists = driver.findElement(By.linkText(value)) != null;
                } catch (NoSuchElementException e) {
                    linkExists = false;
                }
                elementExists.set(linkExists);
            }

            Assert.assertTrue(elementExists.get(), "Could not find " + value + " field!");
        });
    }

    @Then("The file will not be displayed in the Attachments grid")
    public void theFileWillNotBeDisplayedInTheAttachmentsGrid() {
        try {
            driver.findElement(By.linkText("Attachments")).click();
        } catch (ElementClickInterceptedException e) {
            commonForm.clickErrorHandle(e.toString(), driver.findElement(By.linkText("Attachments")));
        }

        Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[4]/div[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[1]"))).isDisplayed(), "Attachments grid is not empty");
    }

    @Then("The file will be displayed in the Attachments grid")
    public void theFileWillBeDisplayedInTheAttachmentsGrid() {
        commonForm.fileWillBeDisplayedInTheAttachmentsGrid(attachName);
    }

    @Then("I verify that Add Attachment button {string} displayed")
    public void iVerifyThatAddAttachmentButtonDisplayed(String visibility) {
        switch (visibility) {
            case "is" -> Assert.assertTrue(commonForm.commonButtonGet("Add Attachment").isDisplayed(), "Add Attachment button is not present");
            case "is not" -> Assert.assertNull(commonForm.commonButtonGet("Add Attachment"), "Add Attachment button is present");
            default -> System.out.println("Specified condition is not working");
        }
    }

    @Then("I verified that hover message displayed")
    public void IVerifyThatHoverMessageDisplayed() {
        Assert.assertEquals(purchaseOrder.verifyHoverMessageOnDeleteButton(), "You can't delete the attahment, quote was already approved.");
    }
}
