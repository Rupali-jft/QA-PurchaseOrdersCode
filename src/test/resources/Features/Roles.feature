Feature:Grid Filters with Different Roles

  @4877
  Scenario: Verify the access of Guest Role
    Given I log into the Purchase Orders app as an "POGuestDevUser"
    And The "Add Request" button "is not" displayed
    And I get the number of records in the "Requests" tab

  @1541
  Scenario: Verify the initiator role request grid filter
    Given I log into the Purchase Orders app as an "Initiator"
    And I navigate to the "Requests" tab
    And I get the "Initiator" column value

  @1879
  Scenario:Verify the Procurement approval grid filter.
    Given I log into the Purchase Orders app as an "ProcurementManager"
    And I click on the "Procurement Approval" link
    And set the tab to "ProcMgrApproval"
    And Verify below given headers are present
      | WO #       |
      | WO Title   |
      | Initiator  |
      | Status     |
      | Date       |
      | Location   |
      | Department |

  @1882
  Scenario: Verify the Purchase Orders grid filter
    Given I log into the Purchase Orders app as an "ProcurementManager"
    And I click on the "Purchase Orders" link
    And set the tab to "PurchaseOrder"
    And Verify below given headers are present
      | PO #       |
      | PO Title   |
      | Initiator  |
      | Status     |
      | Date       |
      | Location   |
      | Department |
      | Vendor     |
    And I click on the top purchase order link

  @1877
  Scenario: Verify the Pending approval grid filter
    Given I log into the Purchase Orders app as an "Approver"
    And I navigate to the "Pending Approval" tab
    And set the tab to "Approval"
    And Verify below given headers are present
      | WO #       |
      | WO Title   |
      | Initiator  |
      | Status     |
      | Date       |
      | Location   |
      | Department |
      | Approved   |

  @1881 @1876
  Scenario: Verify the Purchase Orders grid filter by Approver
    Given I log into the Purchase Orders app as an "Approver"
    And I click on the "Purchase Orders" link
    And set the tab to "PurchaseOrder"
    And Verify below given headers are present
      | PO #       |
      | PO Title   |
      | Initiator  |
      | Status     |
      | Date       |
      | Location   |
      | Department |
      | Vendor     |
    And I click on the top purchase order link