package Pages;

import org.openqa.selenium.WebDriver;

// Declaring the driver

public class BaseUtil
{
    public static WebDriver driver;

    //TODO Set logins for required users

    // Initiator role
    public static String initiatorEmail = "******@***.***";
    public static String initiatorPass = "********";
    // Approver role
    public static String approverEmail = "******@***.***";
    public static String approverPass = "********";
    // Purchase officer role
    public static String purchaseOfcEmail = "******@***.***";
    public static String purchaseOfcPass = "******@***.***";
    // Procurement manager role
    public static String procurementMgrEmail = "******@***.***";
    public static String procurementMgrPass = "********";

    // filePath is currently set to C:\Users\<USER_NAME>. Feel free to change it
    // filePath is used to determine where screenshots and reports are saved on your system
    public static String filePath = System.getProperty("user.home") + "\\Desktop\\QA-PO-Files";
    // Set this to the location of chromedriver.exe on your system
    public static String driverLocation = "C:\\chromedriver\\chromedriver.exe";
}

