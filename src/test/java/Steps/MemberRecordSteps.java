package Steps;

import DTO.MemberData;
import DTO.Members;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MemberRecordSteps {

    public static Members localData;
    public static Members liveData;
    public static MemberData localAnimeData;
    public static MemberData liveAnimeDate;
    public static String animeName;
    public static WebDriverWait wait;
    public static WebDriver driver;


    public MemberRecordSteps(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\thepu\\Documents\\GitHub\\selenium-webdriver\\Drivers\\chromedriver.exe" );
        driver = new ChromeDriver();
        liveData= new Members();
    }

    @Given("Selenium setup is done and driver is started with given anime name")
    public void seleniumSetupIsDone(DataTable dataTable) {
        wait = new WebDriverWait(driver,20);
        Map<String,String> map=dataTable.asMap(String.class,String.class);
        animeName=map.get("name");
        System.out.println("Anime Name is " +  animeName);
        System.out.println("Driver Started .....");
    }

    @And("Local Data is stored in variable")
    public void localDataIsStoredInVariable() throws Exception{
        try {
            FileInputStream file = new FileInputStream("LocalData");
            ObjectInputStream in = new ObjectInputStream(file);
            localData = (Members) in.readObject();
            in.close();
            file.close();
            localAnimeData=localData.getMap().get(animeName);
            System.out.println(localAnimeData);
        }
        catch (IOException e){
            System.out.println("Error Message is " + e.getMessage());
            FileOutputStream file = new FileOutputStream("LocalData");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(new Members());
            out.close();
            file.close();
        }
    }

    @When("Top Anime Page is loaded and windows is maximized")
    public void topAnimePageIsLoadedAndWindowsIsMaximized() {
        driver.get("https://myanimelist.net/topanime.php");
        driver.manage().window().maximize();
        System.out.println("Title is " + driver.getTitle() + " for " + driver.getCurrentUrl());
    }

    @And("Anime name is searched and clicked")
    public void animeNameIsSearchedAndClicked( ) {
        WebElement animeElement = driver.findElement(By.linkText(animeName));
        animeElement.click();

    }

    @Then("Search for the members tag and store the count and display the difference")
    public void searchForTheMembersTagAndStoreTheCountAndDisplayTheDifference() {
        WebElement membersCount = driver.findElement(By.xpath("//span[@class='numbers members']/strong"));
        Integer countInteger=removeCommasFromString(membersCount.getText());
        liveData.getMap().put(animeName, new MemberData(countInteger));
        liveAnimeDate=liveData.getMap().get(animeName);
        Integer localCount=localAnimeData==null?0:localAnimeData.getCount();
        System.out.println(liveAnimeDate);
        System.out.println("Difference in Members is " + (countInteger-localCount));
    }

    @And("Store the new count in the file")
    public void storeTheNewCountInTheFile() {
        try {
            FileOutputStream file = new FileOutputStream("LocalData");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(liveData);
            out.close();
            file.close();
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    @Then("Wait for the page to show the accept cookies option and accept the cookies")
    public void waitForThePageToShowTheAcceptCookiesOption() {
        wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//button[text()='OK']"))));
        WebElement acceptCoookie=driver.findElement((By.xpath("//button[text()='OK']")));
        acceptCoookie.click();
    }

    @And("Refresh the page and check if cookie acceptance is shown again or not")
    public void refreshThePageAndCheckIfCookieAcceptanceIsShownAgainOrNot() {
        driver.navigate().refresh();
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='button-wrapper']")));
        }
        catch(TimeoutException tiex){
            System.out.println(tiex.getMessage());
        }
        catch(Exception e){
            System.out.println("Error Message is + " + e.getMessage());
        }
    }

    @After
    public void after(){
        driver.quit();
        System.out.println(".....Driver Closed");

    }

    //UTIL FUNCTIONS
    private Integer removeCommasFromString(String countString) {
        String arr[]=countString.split(",");
        String formattedCountString="";
        for(String x : arr)
            formattedCountString+=x;
        return Integer.parseInt(formattedCountString);
    }


}
