Feature:Purchase Order Grid Common test cases

  Background: Sign In
    Given I log into the Purchase Orders app as an "Initiator"
    When I navigate to the "Requests" tab


  @1295
  Scenario: Grid - verify common input filters with unique result
    When I get the "WO #" for "row 4" of the grid
    And I enter that information into the grid header
    Then The information in the first row of the grid will match what was entered
    And I click the Reset button in the grid header
    When I get the "WO Title" for "row 4" of the grid
    And I enter that information into the grid header
    Then The information in the first row of the grid will match what was entered
    And I click the Reset button in the grid header
    And I get the "Date" for "row 1" of the grid
    When I set that date in the header
    Then I get the number of records in the "Requests" tab

  @1296
  Scenario: Grid - verify common input filters with Multiple result
    When I enter "18" into the "WO Title" grid header
    Then I get the number of records in the "Requests" tab
    And I click the Reset button in the grid header
    And I get the "Date" for "row 4" of the grid
    When I set that date in the header
    Then I get the number of records in the "Requests" tab

  @1297
  Scenario: Grid - verify common input filters with 0 result
    When I enter "abc" into the "WO #" grid header
    Then There is no data in the grid
    And I click the Reset button in the grid header
    When I enter "00000000dss" into the "WO Title" grid header
    Then There is no data in the grid
    And I click the Reset button in the grid header
    And I enter "abc" into the "Date" grid header
    Then There is no data in the grid
    When I enter "&#$" into the "WO #" grid header
    Then There is no data in the grid
    And I click the Reset button in the grid header
    When I enter "#123@P" into the "WO Title" grid header
    Then There is no data in the grid

  @1302
  Scenario: Grid -Verify Select All for grid drop downs

    And I select "multiselect-all" from the "Status" header in the grid
    Then I check if the following items are selected in the "Status" header
      |Pending Quotes|Pending Purchase Order|Pendinlg Request Approval|Purchase Order Closed|Purchase Order Raised|Quote Pending Approver|Quote Pending Approver|Quote Pending Approver|Work Order Closed|Work Order Raised|
    And The number of records in the "Requests" tab is "the same"
    And I click the Reset button in the grid header
    And I select "multiselect-all" from the "Location" header in the grid
    Then I check if the following items are selected in the "Location" header
      |Bangalore|Bhilai|Coimbatore|Hyderabad|Raipur|Vizag TH|Vizag VP|
    And The number of records in the "Requests" tab is "the same"
    And I click the Reset button in the grid header
    And I select "multiselect-all" from the "Department" header in the grid
    Then I check if the following items are selected in the "Department" header
      |IT|HR|Legal|Admin|Facility|Finance|
    And The number of records in the "Requests" tab is "the same"

  @1303
  Scenario: Grid - Verify Select one for grid drop downs
    When I select "Pending Quotes" from the "Status" header in the grid
    And I check if "Pending Quotes" is selected in the "Status" header
    And I click the Reset button in the grid header
    When I select "Bangalore" from the "Location" header in the grid
    And I check if "Bangalore" is selected in the "Location" header
    And I click the Reset button in the grid header
    When I select "Finance" from the "Department" header in the grid
    And I check if "Finance" is selected in the "Department" header


  @1304
  Scenario: Grid -  Verify Select Multiple Values for grid drop downs

    And I select "multiselect-all" from the "Status" header in the grid
    When I get the number of records in the "Requests" tab
    And I click the Reset button in the grid header
    And I select "Purchase Order Raised" from the "Status" header in the grid
    Then I check if the following items are selected in the "Status" header
      | Purchase Order Raised |
    And The number of records in the "Requests" tab is "decreased"
    When I get the number of records in the "Requests" tab
    And I select "Purchase Order Closed" from the "Status" header in the grid
    And I select "Work Order Closed" from the "Status" header in the grid
    Then I check if the following items are selected in the "Status" header
      | Purchase Order Raised | Purchase Order Closed| Work Order Closed|
    And The number of records in the "Requests" tab is "increased"
    And I get the number of records in the "Requests" tab

    And I click the Reset button in the grid header
    When I get the number of records in the "Requests" tab
    And I select "Coimbatore" from the "Location" header in the grid
    Then I check if the following items are selected in the "Location" header
      | Coimbatore|
    And The number of records in the "Requests" tab is "decreased"
    When I get the number of records in the "Requests" tab
    And I select "Bhilai" from the "Location" header in the grid
    And I select "Hyderabad" from the "Location" header in the grid
    Then I check if the following items are selected in the "Location" header
      | Coimbatore | Bhilai| Hyderabad|
    And The number of records in the "Requests" tab is "increased"
    And I get the number of records in the "Requests" tab

    And I click the Reset button in the grid header
    When I get the number of records in the "Requests" tab
    And I select "Facility" from the "Department" header in the grid
    Then I check if the following items are selected in the "Department" header
      | Facility|
    And The number of records in the "Requests" tab is "decreased"
    When I get the number of records in the "Requests" tab
    And I select "Legal" from the "Department" header in the grid
    And I select "Admin" from the "Department" header in the grid
    Then I check if the following items are selected in the "Department" header
      |Facility| Legal| Admin|
    And The number of records in the "Requests" tab is "increased"
    And I get the number of records in the "Requests" tab








