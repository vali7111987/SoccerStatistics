import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.*;
import java.util.concurrent.TimeUnit;

public class BetfairStatsMap {
    public WebDriver driver;
    public TeamStat teamStat;
    public Betfair betfair;
    public void writeToFile (String text) {
        FileWriter fw = null;
        BufferedWriter bw = null;
        PrintWriter out = null;
        try {
            fw = new FileWriter("pairs.txt", true);
            bw = new BufferedWriter(fw);
            out = new PrintWriter(bw);
            out.println(text);
            out.close();
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
    }

    @Test
    public void writePairs() throws InterruptedException, IOException {

        String pair=null;
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\vali7\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        betfair=new Betfair(driver);
        teamStat=new TeamStat(driver);
        betfair.navigateTo("https://www.betfair.ro/sport/");
        Thread.sleep(10000);
        betfair.acceptaCookie.click();
        Thread.sleep(5000);
        betfair.fotbalAstazi.click();
        Thread.sleep(5000);
        betfair.fotbalAstazi.click();
        for (String team : betfair.getTeamsHome()) {
            teamStat.navigateTo("https://www.soccerstats.com/");
            Thread.sleep(15000);
            teamStat.clickOnConsent();
            Thread.sleep(3000);
            pair = teamStat.findTeam(team);
            writeToFile("\n");
            writeToFile(team + "," + pair);
        }
    }
}
