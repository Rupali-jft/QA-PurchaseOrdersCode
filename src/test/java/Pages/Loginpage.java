package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.List;


public class Loginpage extends BaseUtil{
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
    public void Login(String user) throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOf(txtEmail));
        String email = "";
        String pass = "";
        System.out.println("Entering login for "+ user.toUpperCase() + " role\n");
        switch(user.toLowerCase()) {
            case "initiator":
                email = initiatorEmail;
                pass = initiatorPass;
                break;
            case "approver":
                email = approverEmail;
                pass = approverPass;
                break;
            case "purchase officer":
                email = purchaseOfcEmail;
                pass = purchaseOfcPass;
                break;
            case "procurement manager":
                email = procurementMgrEmail;
                pass = procurementMgrPass;
                break;
            default:
                throw new Exception("No login information available for " + user.toUpperCase() + "please use 'Initiator', 'Approver', 'Purchase Officer', or 'Procurement Manager'.");

        }
        //Thread.sleep(15000);
        txtEmail.sendKeys(email);
        //Thread.sleep(15000);
        txtPassword.sendKeys(pass);
        //Thread.sleep(15000);
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


}
