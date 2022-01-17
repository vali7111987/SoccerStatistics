import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDriverDemo {
    public static void main(String[] args) throws InterruptedException{

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\vali7\\chromedriver.exe");
        System.out.println("Execution after setting ChromeDriver path in System Variables");
        WebDriver driver=new ChromeDriver();
        driver.get("https://demoqa.com");
        Thread.sleep(3000);
    }

}