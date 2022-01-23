import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Betfair {
    @FindBy(xpath ="//li[@class='ui-clickselect']//*[contains(text(),'Fotbal')]") WebElement fotbal;
    @FindBy(xpath="//*[contains(text(),'Elita')]") WebElement elita;
    @FindBy(xpath="//*[contains(text(),'Fotbal de ast')]") WebElement fotbalAstazi;
    @FindBy(xpath="//*[@id='onetrust-accept-btn-handler']") WebElement acceptaCookie;
    @FindBy(xpath="//div[@class='chooser-container']//span[2]//a[@data-action='loadCompetition']") WebElement orderByCompetition;
    String pathTeamsHome="//div[@class='teams-container']/span[1]";
    String pathTeamsAway="//div[@class='teams-container']/span[2]";
    WebDriver driver;
    HashMap<String, String> teamsPaired;

    //Constructor, as every page needs a Webdriver to find elements
    public Betfair(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void navigateTo(String url2) {
        driver.get(url2);
        driver.manage().window().maximize();
    }

    public String eliminBadChars(String input) {
        String s = input;        return s;
    }
    public void getTeamPairsInFile() throws IOException {
        String home,away,teams;
        PrintWriter out = new PrintWriter("teampairs.txt");

        List<WebElement> teamPairsHome = driver.findElements(By.xpath(pathTeamsHome));
        List<WebElement> teamPairsAway = driver.findElements(By.xpath(pathTeamsAway));
        for (int i=0;i<teamPairsHome.size();i++) {
            home="{\""+eliminBadChars(teamPairsHome.get(i).getText())+"\"";
            away="\""+eliminBadChars(teamPairsAway.get(i).getText())+"\"}";
            teams=home+","+away+","+"\n";
            out.write(teams);
        }
        out.close();
    }
    public List<String> getTeamsHome() throws IOException {
        List <String> teamHome = Collections.singletonList("");
        List<WebElement> teams=driver.findElements(By.xpath(pathTeamsHome));
        for (int i=0;i<teams.size();i++) {
            teamHome.add(eliminBadChars(String.valueOf(teams.get(i))));
        }
        return teamHome;
    }

    public List<String> getTeamsAway() throws IOException {
        List <String> teamAway = Collections.singletonList("");
        List<WebElement> teams=driver.findElements(By.xpath(pathTeamsAway));
        for (int i=0;i<teams.size();i++) {
            teamAway.add(eliminBadChars(String.valueOf(teams.get(i))));
        }
        return teamAway;
    }
}




