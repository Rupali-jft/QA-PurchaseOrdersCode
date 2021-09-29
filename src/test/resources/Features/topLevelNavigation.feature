Feature: Top Level Navigation test-cases

  @1502
  Scenario:Validating Global Search Functionality

    Given I log into the Purchase Orders app as an "Initiator"
  #####------------ Search with existing request# in "Search All" text box on top right side of the page---------#####
    When I get the "WO #" for "row 1" of the grid and enter in the search field
    Then I see 1 result saying expected record
 #####--------Search with existing WO title in "Search All" text box on top right side of the page-------#####
    When I get the "WO Title" for "row 1" of the grid and enter in the search field
    Then I see 1 result saying expected record
  ###--------Search with single digit in search box------#####
    When I enter "6" in the search field
    Then I see 5 results saying "6"
    And The results are in descending order

  @1588
  Scenario:Validating Breadcrumbs Functionality
    Given I log into the Purchase Orders app as an "Purchaseofficer"
    And I click on the "Home" link
    Then The "Home" link "is" displayed
    And I click the "Add Request" button
    Then The "Current Request" link "is" displayed
    And I click the "Close" button
    And I select "Pending Purchase Order" from the "Status" header in the grid
    And I navigate to the "Requests" tab
    And I click on the top work order link
    And I click the "Add Purchase Order" button
    Then The "Purchase Order" link "is" displayed

  @1914
  Scenario: Global Search Initiator role
    Given I log into the Purchase Orders app as an "Initiator"
    When I enter "11357" in the search field
    Then I see 1 result saying "Record Doesn't Exist."

