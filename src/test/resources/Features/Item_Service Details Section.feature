Feature: Item/Service Details Section

  Background:Add Item
    Given I log into the Purchase Orders app as an "Initiator"
    And I click the "Add Request" button
    And I add the following into the "Item / Service Details" table
      | Item / Service Name | Description              | Quantity |
      | Test                | Test Item <current date> | 1        |

  @1351
  Scenario: Adding the Item/Service
    Then Verify that item is added into the "Item / Service Details" table
      | Item / Service Name | Description | Quantity |

  @1907
  Scenario: Edit the Item/Service
    And I click the edit icon
    Then verify that fields are enabled to edit
    And I enter "New Test" into "Item / Service Name" field
    And I enter "New Test Item" into the "Description" field
    And I update "12" into "Quantity" field
    And I add the record
    Then Verify the edited fields are updated correctly
      | New Test       |
      | New Test  Item |
      | 12             |

  @1908
  Scenario: Delete the Item/Service
    And I click the delete icon
    Then Verify that item is deleted successfully and shouldn't display in the grid.

