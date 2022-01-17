import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class TakeBetfair {

    public WebDriver driver;
    public Betfair betfair;
    public static Double[] calculateStat(Double[] home, Double[] away) {
        Double[] calcStat=new Double[3];
        for (int i=0;i<3;i++) {
            calcStat[i]=(home[i]+away[i])/2;
        }
        return calcStat;
    }

    @BeforeSuite
    public void setUp() throws InterruptedException, IOException {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\vali7\\chromedriver.exe");
        driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        betfair=new Betfair(driver);
        betfair.navigateTo("https://www.betfair.ro/sport/");
        Thread.sleep(10000);
        betfair.acceptaCookie.click();
        Thread.sleep(5000);
        betfair.fotbalAstazi.click();
        Thread.sleep(5000);
        betfair.orderByCompetition.click();
        Thread.sleep(5000);


    }

    @Test
    public void getTeamsPairs() throws  IOException {
        betfair.getTeamPairsInFile();
    }

    @AfterSuite
    public void burnDown() throws IOException {
        driver.quit();
    }
}
