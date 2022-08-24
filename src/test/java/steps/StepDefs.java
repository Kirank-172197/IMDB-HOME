package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java8.En;
import org.junit.Assert;
import pages.Page;

import java.util.Map;

public class StepDefs extends Page implements En {

    public StepDefs() {

        Given("the user navigates to the url '(.*)'$", this::getUrl);

        And("the user is presented with '(.*)' page$", (String pageName) -> Assert.assertEquals(getTitle(), pageName));

        And("the user searches for the movie '(.*)' using the search field$", this::typeText);

        And("the user is shown the results for the relevant movie title '(.*)'$", (String movieTitleSearchResult) -> {
            Assert.assertTrue(String.valueOf(getSearchResult().toLowerCase().contains(movieTitleSearchResult)), true);
        });

        When("the user clicks on the relevant movie link '(.*)'$", this::clickOnMovieTitle);

        Then("the user validates the below details", (DataTable dataTable) -> {

          for(Map<String, String> movieDetails : dataTable.asMaps(String.class, String.class)){

              Assert.assertEquals(getReleaseDate(movieDetails.get("Release Date")), movieDetails.get("Release Date"));
              Assert.assertEquals(getCountryOfOrigin(movieDetails.get("Country of origin")), movieDetails.get("Country of origin"));
          }
        });


    }


}
