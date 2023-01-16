import org.apache.spark.ui.jobs.ApiHelper;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class TestData {
    @DataProvider(name = "create")
    public Object[][] dataProvFunc() throws IOException {
        return new Object[][] {
                {"Estoril Praia","Casa Pia"},
                {"Empoli","Sampdoria"},
                {"Cadiz","Elche"},
                {"Gil Vicente","Guimaraes"},
                {"Wigan","Luton"},
                {"Accrington","Boreham Wood"},
                {"Wolves","Liverpool"},
                {"Swansea","Bristol City"},
                {"Forest Green","Birmingham"},
                {"West Brom","Chesterfield"},
                {"Napoli","US Cremonese"},
                {"Leeds","Cardiff"},
                {"Crystal Palace","Man Utd"},
                {"Atalanta","Spezia"},
                {"Lazio","Bologna"},
                {"Man City","Tottenham"},
                {"Juventus","AC Monza"},
                {"Arouca","Portimonense"},
                {"RB Leipzig","Bayern Munich"},
                {"Mallorca","Celta Vigo"}
        };
    }
}
