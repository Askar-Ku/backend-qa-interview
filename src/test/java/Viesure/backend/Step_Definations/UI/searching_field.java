package Viesure.backend.Step_Definations.UI;

import Viesure.backend.utilities.BrowserUtilities;
import Viesure.backend.utilities.ConfigurationReader;
import Viesure.backend.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;


public class searching_field {


    @When("the main pages title should be {string}")
    public void the_main_pages_title_should_be(String expectedtitle) {

        Driver.get().manage().window().maximize();
        String url = ConfigurationReader.get("url");
        Driver.get().get(url);
        Driver.get().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        String actualtitle = Driver.get().findElement(By.xpath("//h1//span[contains(text(),'OpenWeather')]")).getText();

        Assert.assertEquals(expectedtitle, actualtitle);

    }


    @Given("the user select Sydney,AU from the list")
    public void the_user_select_sydney_au_from_the_list() throws InterruptedException {

        Driver.get().findElement(By.xpath("//input[@placeholder='Search city']")).sendKeys("Sydney,AU");
        Thread.sleep(4000);


        BrowserUtilities.waitForPageToLoad(20);
        Driver.get().findElement(By.xpath("//button[@type='submit']")).click();
        Driver.get().findElement(By.xpath("//ul[@class='search-dropdown-menu']/li")).click();
       /*normally my my apporoch is here like under the blow i tried many ways to find first elment of DD but it returns whole budy
         of the DD it returns only one element as the whole body of list.
          fainnly i tried this way to locate first elment of the DD*/
        /*String optionselected="Sydney, AU";
        List<WebElement> list = Driver.get().findElements(By.xpath("//div/ul[@class='search-dropdown-menu']"));
        for (int  i= 0; i < list.size(); i++) {
            System.out.println(list.get(i).getText());
        }
        /*for (WebElement webElement : list) {
            String currentoption= webElement.getText();
          if (currentoption.contains(optionselected)){

              webElement.click();
              break;
          }else{
              System.out.println("the web elment dosent exist in the list");
          }
        }*/

    }

    @When("the selected city title should be like {string}")
    public void the_selected_city_title_should_be_like(String expectedre) {
        BrowserUtilities.waitForPageToLoad(20);
        String actualre = Driver.get().findElement(By.xpath("//h2[contains(text(),'Sydney, AU')]")).getText();
        Assert.assertEquals(expectedre, actualre);
    }



    @When("the date and time need to be verified.")
    public void the_date_and_time_need_to_be_verified() throws InterruptedException {
        TimeZone.setDefault(TimeZone.getTimeZone("Australia/Sydney"));
        ZoneId zone = ZoneId.of("Australia/Sydney");
        LocalDateTime myDateObj = LocalDateTime.now(zone);
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("MMM dd, hh:mma");
        String formattedDate = myDateObj.format(myFormatObj);

        String Autidatext = formattedDate.replace("AM", "am").replace("PM", "pm");
        System.out.println(Autidatext);
        String[] dd = Autidatext.split(", ");
        String Expdate = dd[0];

        System.out.println(Expdate);

        String actual = Driver.get().findElement(By.xpath("//div/span[@class='orange-text']")).getText();
        System.out.println(actual);
        Assert.assertEquals(Autidatext, actual);

        String[] Act = actual.split(", ");
        String Acdate = Act[0];
        System.out.println(Acdate);

        Assert.assertEquals("this is date verifying", Expdate, Acdate);
        String[] tt = Autidatext.split(", ");
        String Exptime = tt[1];
        String Actime = Act[1];
        System.out.println(Actime);

        Assert.assertEquals("this is time varifying", Exptime, Actime);

        //as above you can see my onother approch i wanted creat dynamic expath locator.acctually i wanted verify both
        //date and time in one test case but i created two test case dependes on AC;
        //String xpath="\"//span[contains(text(),'\"+Autidatext+\"')]\"";

    }

}









































