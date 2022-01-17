import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.Assertion;

public class TeamStat {
    @FindBy(xpath="//*[@id='autocomplete2']") WebElement searchTeam;
    @FindBy(xpath="//*[contains(text(),'Both teams scored')]//../td[2]") WebElement bttsHome;
    @FindBy(xpath="//*[contains(text(),'Both teams scored')]//../td[3]") WebElement bttsAway;
    @FindBy(xpath="//*[contains(text(),'Matches over 1.5 goals')]//../td[2]")  WebElement over15GoalsHome;
    @FindBy(xpath="//*[contains(text(),'Matches over 1.5 goals')]//../td[3]")  WebElement over15GoalsAway;
    @FindBy(xpath="//*[contains(text(),'Matches over 3.5 goals')]//../td[2]")  WebElement over35GoalsHome;
    @FindBy(xpath="//*[contains(text(),'Matches over 3.5 goals')]//../td[3]")  WebElement over35GoalsAway;
    @FindBy(xpath="/html/body/div[5]/div[2]/div[1]/div[2]/div[2]/button[1]")  WebElement consent;
    @FindBy(xpath="//*[@id='content']//table[1]//td[2]/h2") WebElement teamName;
    WebDriver driver;
    String cUrl;

    //Constructor, as every page needs a Webdriver to find elements
    public TeamStat(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public Double getNumbers (String text) {
        return Double.valueOf(text.substring(0,text.length()-1));
    }

    public void clickOnConsent() {
        consent.click();
    }

    public void navigateTo(String url2) {
        driver.get(url2);
        driver.manage().window().maximize();
    }

    public Double getBTTSHome() {
        return getNumbers(bttsHome.getText());
    }

    public Double getBTTSAway() {
        return getNumbers(bttsAway.getText());
    }

    public Double getOver15Away() {
        return getNumbers(over15GoalsAway.getText());
    }
    public Double getoVER15Home() {
        return getNumbers(over15GoalsHome.getText());
    }
    public Double getoVER35Home() {
        return getNumbers(over35GoalsHome.getText());
    }
    public Double getoVER35Away() {
        return getNumbers(over35GoalsAway.getText());
    }

    public void selectTeam(String team) throws InterruptedException {
        //verify if link changes
        searchTeam.clear();
        Thread.sleep(2000);
        searchTeam.sendKeys(team);
        searchTeam.sendKeys(Keys.DOWN);
        searchTeam.sendKeys(Keys.ENTER);
        Thread.sleep(5000);
        Assert.assertNotEquals(cUrl,driver.getCurrentUrl());
        Assert.assertTrue(teamName.getText().contains(team));
        cUrl= driver.getCurrentUrl();

    }

    public Double[] getStatHome() {
        Double[] stat=new Double[3];
        stat[0]=this.getBTTSHome();
        stat[1]=this.getoVER15Home();
        stat[2]=this.getoVER35Home();
        return stat;
    }

    public Double[] getStatAway() {
        Double[] stat=new Double[3];
        stat[0]=this.getBTTSAway();
        stat[1]=this.getOver15Away();
        stat[2]=this.getoVER35Away();
        return stat;
    }


}

