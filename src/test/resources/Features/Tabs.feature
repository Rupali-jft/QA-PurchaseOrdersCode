Feature:Different tabs functionality

###-------Attachments Tab in Request Details Page-------###
  @1359
  Scenario: Add Attachment in Request Details Page
    Given I log into the Purchase Orders app as an "Initiator"
    And I navigate to the "Requests" tab
    When I click on the top work order link
    And I upload an attachment
    Then The file will be displayed in the Attachments grid

  @5179
  Scenario: Verify Delete Attachments functionality
    Given I log into the Purchase Orders app as an "Initiator"
    And I navigate to the "Requests" tab
    When I click on the top work order link
    And I upload an attachment
    Then The file will be displayed in the Attachments grid
    When I delete the attachment
    Then The file will not be displayed in the Attachments grid

  @1355
  Scenario: Attachments Tab in Request Details Page
    Given I log into the Purchase Orders app as an "Initiator"
    And I navigate to the "Requests" tab
    When I click on the top work order link
    And I go to "Attachments" tab.
    And set the tab to "Attachments"
    Then Verify the following headers are present
      |Date Created|
      |Created By |
      |Attachment File Name|
      |Attachment Description|
      |Actions|

###-----------History Tab in Request Details Page--------###
  @1356
  Scenario:History Tab in Request Details Page
    Given I log into the Purchase Orders app as an "Initiator"
    And I navigate to the "Requests" tab
    When I click on the top work order link
    When I navigate to the "History" tab
    And set the tab to "History"
    Then Verify the following headers are present
      |Status|
      |Action|
      |Add Date|
      |Added User|
      |Status Change Time Lapse|

###-----------Comments Tab in Request Details Page--------###
@1354
Scenario: Comments Tab in Request Details Page
Given I log into the Purchase Orders app as an "Initiator"
And I navigate to the "Requests" tab
When I click on the top work order link
And I open the "Comments" tab
And set the tab to "notes_wrapper"
Then Verify the following headers are present
|Date Created|
|Created By|
|Title |
|Comments|
 |View Comments|

####---------PO tab in Request Details Page----------####
@1352
Scenario: Purchase Orders Tab in Request Details Page
  Given I log into the Purchase Orders app as an "Purchaseofficer"
  And I navigate to the "Requests" tab
  When I select "Purchase Order Raised" from the "Status" header in the grid
  And I click on the top work order link
  And set the tab to "Purchaseorders"
  Then Verify the following headers are present
    |PO #|
    |Status|
    |PO Title|
    |PO Date|
    |Vendor|
  | Price  |
  | View PDF|
  And I click the view PDF link
  And I click the "Print PDF" button

###-----------Quotes Tab in Request Details Page--------###
@1353
Scenario:Quotes Tab in Request Details Page-Purchase Officer Role
Given I log into the Purchase Orders app as an "Purchaseofficer"
And I navigate to the "Requests" tab
When I select "Quote Pending Approver" from the "Status" header in the grid
And I click on the top work order link
When I go to "Quotes" tab.
And set the tab to "Quotes"
Then Verify the following headers are present
|Quote Date|
|Quote Title|
|Vendor     |
|Quoted Price|
|Quote Selected|
|View quote|
And I click the eye icon
Then verify that Quote page is opened.
And I click the "Submit" button
And I click the "confirm" button
Then I verify that "Quote Successfully Updated" validation  message appears

@1909
Scenario: Quotes Tab in Request Details Page-Initiator Role
  Given I log into the Purchase Orders app as an "Initiator"
    And I navigate to the "Requests" tab
    When I select "Quote Pending Approver" from the "Status" header in the grid
    And I click on the top work order link
    When I go to "Quotes" tab.
    And set the tab to "Quotes"
    Then Verify the following headers are present
      |Quote Date|
      |Quote Title|
      |Vendor     |
      |Quoted Price|
      |Quote Selected|
      |View quote|
    And I click the eye icon
    Then verify that Quote page is opened.
    And I click the "Submit" button
    And I click the "confirm" button
    Then I verify that "Quote Successfully Updated" validation  message appears

@1910
Scenario: Quotes Tab in Request Details Page-Approver Role
  Given I log into the Purchase Orders app as an "Approver"
  And I navigate to the "Requests" tab
  When I select "Quote Pending Approver" from the "Status" header in the grid
  And I click on the top work order link
  When I go to "Quotes" tab.
  And set the tab to "Quotes"
  Then Verify the following headers are present
    |Quote Date|
    |Quote Title|
    |Vendor     |
    |Quoted Price|
    |Quote Selected|
    |View quote|
  And I click the eye icon
  Then verify that Quote page is opened.
 And I verify that buttons are disabled

@1904
Scenario: Deleting a Quote
Given I log into the Purchase Orders app as an "Purchaseofficer"
And I navigate to the "Requests" tab
When I select "Quote Pending Proc. Mgr." from the "Status" header in the grid
And I click on the top work order link
When I go to "Quotes" tab.
And set the tab to "Quotes"
And I delete the Quote

@1905
Scenario: Cancel editing a quote
    Given I log into the Purchase Orders app as an "Purchaseofficer"
    And I navigate to the "Requests" tab
    When I select "Quote Pending Approver" from the "Status" header in the grid
    And I click on the top work order link
    When I go to "Quotes" tab.
    And set the tab to "Quotes"
    And I click the eye icon
    And Edit the Quote Title field
    And Click the cancel button on the Edit Quote modal
    Then I will see that the title has not changed
    Then I Click on user icon
    And I click Logout button






