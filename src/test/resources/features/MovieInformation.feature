Feature: Extracting the movie details.


  Scenario: Verify the country and the release date of the movie 'Pushpa the rise' from IMDB
    Given the user navigates to the url 'https://www.imdb.com'
    And   the user is presented with 'IMDb: Ratings, Reviews, and Where to Watch the Best Movies & TV Shows' page

    And   the user searches for the movie 'Pushpa The Rise' using the search field
    And   the user is presented with 'Find - IMDb' page
    And   the user is shown the results for the relevant movie title 'Pushpa The Rise'

    When  the user clicks on the relevant movie link 'Pushpa: The Rise - Part 1'
    Then  the user is presented with 'Pushpa: The Rise - Part 1 (2021) - IMDb' page

    And   the user validates the below details
      | Release Date                    | Country of origin |
      | January 7, 2022 (United States) | India             |


