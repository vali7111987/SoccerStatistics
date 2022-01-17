import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import java.io.*;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class TakeStat {
    //public static String teamHome2="FC Koln";
    //public static String teamAway2="Bayern Munich";

    public WebDriver driver;
    public FileWriter myWriter;
    public TeamStat teamStat;

    public static Double[] calculateStat(Double[] home, Double[] away) {
        Double[] calcStat=new Double[3];
        for (int i=0;i<3;i++) {
            calcStat[i]=(home[i]+away[i])/2;
        }
        return calcStat;
    }

    public void writeToFile (String text) {
        FileWriter fw = null;
        BufferedWriter bw = null;
        PrintWriter out = null;
        try {
            fw = new FileWriter("stats.txt", true);
            bw = new BufferedWriter(fw);
            out = new PrintWriter(bw);
            out.println(text);
            out.close();
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
    }


    @BeforeSuite
    public void setUp() throws InterruptedException, IOException {
        //File f =new File("stats.txt");
        writeToFile("Team Home, Team Away, BTTS, Over 15, Over 35");
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\vali7\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        teamStat=new TeamStat(driver);
        teamStat.navigateTo("https://www.soccerstats.com/");
        Thread.sleep(15000);
        teamStat.clickOnConsent();
        Thread.sleep(3000);

    }

    @Test(dataProvider = "create", dataProviderClass = TestData.class)
    public void search(String keyWord1, String keyWord2) throws InterruptedException, IOException {
        teamStat.navigateTo("https://www.soccerstats.com/");
        Thread.sleep(10000);
        teamStat.selectTeam(keyWord1);
       Double[] stat1=teamStat.getStatHome();
       Thread.sleep(2000);
       teamStat.selectTeam(keyWord2);
       Double[] stat2=teamStat.getStatAway();
       Double[] statC=calculateStat(stat1,stat2);
       String stats=statC[0].toString() + "," + statC[1].toString() + "," + statC[2].toString();
       writeToFile(keyWord1 + "," + keyWord2 + "," + stats);


        //System.out.println("BTTS: "+statC[0]);
        //System.out.println("Over 15: "+statC[1]);
        //System.out.println("Over 35: "+statC[2]);
    }
    @AfterSuite
    public void burnDown() throws IOException {
        driver.quit();

    }
}
