package Steps;

import Pages.BaseUtil;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;


public class Hooks extends BaseUtil {

    private BaseUtil base;

    public Hooks(BaseUtil base) {
        this.base = base;
    }

    //Initialization of Chrome driver
    @Before
    public void InitializeTest() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Lakshmi.Lavanya\\chromedriver.exe");
        System.out.println("Opening Chrome browser");
        base.driver = new ChromeDriver();
    }
    @After

   public void TearDownTest() {
/*    if (scenario.isFailed()) {
        System.out.println(scenario.getName());
    }
        else
        {*/

//// Prints the screenshot in your local drive mentioned
            File scrFile = ((TakesScreenshot) base.driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(scrFile, new File("C:/selenium/"+System.currentTimeMillis()+".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

}

    /*
        @After
        public void after(Scenario scenario) {
            final byte[] screenshot = base.driver.getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        }*/
/*    @After
    public String captureScreen() throws IOException {
        TakesScreenshot screen = (TakesScreenshot) base.driver;
        File src = screen.getScreenshotAs(OutputType.FILE);
        String dest = "D:\\Automation_Reports\\screenshots" + ".png";
        File target = new File(dest);
        FileUtils.copyFile(src, target);
        return dest;
    }
}*/
