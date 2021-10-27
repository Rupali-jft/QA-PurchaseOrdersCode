Feature: Add Purchase Order Test-Cases

  @1596
  Scenario: Raise PO
    Given I log into the Purchase Orders app as an "Purchaseofficer"
    And I select "Pending Purchase Order" from the "Status" header in the grid
    And I navigate to the "Requests" tab
    And I click on the top work order link
    And I click the "Add Purchase Order" button
    Then Verify that Purchase order page opens
    Then The following elements exist
      | PO Date *               |
      | Vendor                  |
      | Subject                 |
      | Vendor Reference Number |
      | Reference               |
      | Vendor Address          |
    And I click the "Add Term" button
    Then Verify the table headers in "po_termsTable"
      | No                   |
      | Terms And Conditions |
      | Actions              |
    And I click the "Add Item" button
    Then Verify the table headers in "po_details"
      | Item / Service |
      | Part No *      |
      | Description    |
      | Qty            |
      | Unit Price     |
      | Total          |
      | Action         |
    And I enter "GIP" in the "Vendor Address" text box
    And I click the edit action
    And I enter "10" in the Part No field
    And I click the save action
    And I raise the purchase order

  @1597
  Scenario: Cancel Changes
    Given I log into the Purchase Orders app as an "Purchaseofficer"
    And I select "Pending Purchase Order" from the "Status" header in the grid
    And I navigate to the "Requests" tab
    And I click on the top work order link
    And I click the "Add Purchase Order" button
    Then The following elements exist
      | RAISE PO       |
      | Cancel Changes |
      | Close          |
    And I enter "GIP" in the "Vendor Address" text box
    And I click the "Cancel Changes" button
    And I click the "confirm" button
    Then Verify that "Vendor Address" text box is empty

  @1598
  Scenario: Close Button
    Given I log into the Purchase Orders app as an "Purchaseofficer"
    And I select "Pending Purchase Order" from the "Status" header in the grid
    And I navigate to the "Requests" tab
    And I click on the top work order link
    And I click the "Add Purchase Order" button
    Then The following elements exist
      | RAISE PO       |
      | Cancel Changes |
      | Close          |
    And I enter "GIP" in the "Vendor Address" text box
    And I click the "Close" button