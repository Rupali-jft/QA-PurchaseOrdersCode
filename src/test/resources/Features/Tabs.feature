Feature:Different tabs functionality

###-------Attachments Tab in Request Details Page-------###
  @1359
  Scenario: Add Attachment in Request Details Page
    ####--------------------happy path-----------------#####
    Given I log into the Purchase Orders app as an "Initiator"
    And I click the "Add Request" button
    And I enter the following information into the form
      | WO Title   | Test <current date> |
      | Location   | Vizag TH            |
      | Department | IT                  |
    And I add the following into the "Item / Service Details" table
      | Item / Service Name | Description              | Quantity |
      | Test                | Test Item <current date> | 1        |
    And I submit the new request
#####--------Add Attachment in Request Details Page---------#####
    And I upload an attachment
    Then The file will be displayed in the Attachments grid

  @5179
  Scenario: Verify Delete Attachments functionality
    Given I log into the Purchase Orders app as an "Initiator"
     ####--------------------happy path-----------------#####
    And I click the "Add Request" button
    And I enter the following information into the form
      | WO Title   | Test <current date> |
      | Location   | Vizag TH            |
      | Department | IT                  |
    And I add the following into the "Item / Service Details" table
      | Item / Service Name | Description              | Quantity |
      | Test                | Test Item <current date> | 1        |
    And I submit the new request
#####--------Add Attachment in Request Details Page---------#####
    And I upload an attachment
    Then The file will be displayed in the Attachments grid
    Then I verify that delete button is enabled
    And I delete the attachments
    Then I verify that "Deleted Successfully" validation  message appears
    And I click the "ok" button
    Then The file will not be displayed in the Attachments grid

  @1355
  Scenario: Attachments Tab in Request Details Page
    Given I log into the Purchase Orders app as an "Initiator"
    And I navigate to the "Requests" tab
    When I click on the top work order link
    And I go to "Attachments" tab.
    And set the tab to "Attachments"
    Then Verify the following headers are present
      | Date Created           |
      | Created By             |
      | Attachment File Name   |
      | Attachment Description |
      | Actions                |

###-----------History Tab in Request Details Page--------###
  @1356
  Scenario:History Tab in Request Details Page
    Given I log into the Purchase Orders app as an "Initiator"
    And I navigate to the "Requests" tab
    When I click on the top work order link
    When I navigate to the "History" tab
    And set the tab to "History"
    Then Verify the following headers are present
      | Status                   |
      | Action                   |
      | Add Date                 |
      | Added User               |
      | Status Change Time Lapse |

###-----------Comments Tab in Request Details Page--------###
  @1354
  Scenario: Comments Tab in Request Details Page
    Given I log into the Purchase Orders app as an "Initiator"
    And I navigate to the "Requests" tab
    When I click on the top work order link
    And I open the "Comments" tab
    And set the tab to "notes_wrapper"
    Then Verify the following headers are present
      | Date Created  |
      | Created By    |
      | Title         |
      | Comments      |
      | View Comments |

####---------PO tab in Request Details Page----------####
  @1352
  Scenario: Purchase Orders Tab in Request Details Page
    Given I log into the Purchase Orders app as an "Purchaseofficer"
    And I navigate to the "Requests" tab
    When I select "Purchase Order Raised" from the "Status" header in the grid
    And I click on the top work order link
    And set the tab to "Purchaseorders"
    Then Verify the following headers are present
      | PO #     |
      | Status   |
      | PO Title |
      | PO Date  |
      | Vendor   |
      | Price    |
      | View PDF |
    And I click the PO# column field
    And I click the view PDF link
    And I click the "Print PDF" button

###-----------Quotes Tab in Request Details Page--------###
  @1353
  Scenario:Quotes Tab in Request Details Page-Purchase Officer Role
  ####--------------------happy path-----------------#####
    Given I log into the Purchase Orders app as an "Initiator"
    And I click the "Add Request" button
    And I enter the following information into the form
      | WO Title   | Test <current date> |
      | Location   | Vizag TH            |
      | Department | IT                  |
    And I add the following into the "Item / Service Details" table
      | Item / Service Name | Description              | Quantity |
      | Test                | Test Item <current date> | 1        |
    And I submit the new request
    And I click the "Confirm" button
    And I click the "Yes" button
    And I Click on user icon
    And I click Logout button
    Given I log into the Purchase Orders app as an "Approver"
    And I navigate to the "Pending Approval" tab
    And I open the created WO
    And I approve the request
    And I Click on user icon
    And I click Logout button
    Given I log into the Purchase Orders app as an "Purchaseofficer"
    And I open the created WO
    And Click on Add Quote by Purchase Officer Button
    And I set the quote date in the datepicker
    Then Input values in the Quotes pop-up
      | QuoteTitle | QuoteVendor | QuotedPrice |
      | keyboard   | Apple       | 9000        |
    And I click the "Total Price" box
    And I click the "Submit" button
    And I click the "confirm" button
    When I go to "Quotes" tab.
    And set the tab to "Quotes"
    Then Verify the following headers are present
      | Quote Date     |
      | Quote Title    |
      | Vendor         |
      | Quoted Price   |
      | Quote Selected |
      | View quote     |
    And I click the eye icon
    Then verify that Quote page is opened.
    And I click the "Total Price" box
    And I click the "Submit" button
    And I click the "confirm" button
    Then I verify that "Quote Successfully Updated" validation  message appears

  @1357
  Scenario:Add Quote in Request details Page-Purchase Officer Role
  ####--------------------happy path-----------------#####
    Given I log into the Purchase Orders app as an "Purchaseofficer"
    And I select "Quote Pending Proc. Mgr." from the "Status" header in the grid
    And I navigate to the "Requests" tab
    And I click on the top work order link
    When I go to "Quotes" tab.
    And set the tab to "Quotes"
    Then Verify the following headers are present
      | Quote Date     |
      | Quote Title    |
      | Vendor         |
      | Quoted Price   |
      | Quote Selected |
      | View quote     |
    And I click the eye icon
    Then verify that Quote page is opened.
    And I click the "Total Price" box
    And I click the "Submit" button
    And I click the "confirm" button
    Then I verify that "Quote Successfully Updated" validation  message appears

  @1899
  Scenario: Add Quote is not successful when mandatory fields are blank
    Given I log into the Purchase Orders app as an "Initiator"
    And I click the "Add Request" button
    And I enter the following information into the form
      | WO Title   | Test <current date> |
      | Location   | Vizag TH            |
      | Department | IT                  |
    And I add the following into the "Item / Service Details" table
      | Item / Service Name | Description              | Quantity |
      | Test                | Test Item <current date> | 1        |
    And I submit the new request
    And I click the "Confirm" button
    And I click the "Yes" button
    And I Click on user icon
    And I click Logout button
    Given I log into the Purchase Orders app as an "Approver"
    And I navigate to the "Pending Approval" tab
    And I open the created WO
    And I approve the request
    And I Click on user icon
    And I click Logout button
    Given I log into the Purchase Orders app as an "Purchaseofficer"
    And I open the created WO
    And Click on Add Quote by Purchase Officer Button
    And I click the "Submit" button
    Then I see error warning "Please Select Quote Date" is displayed
    And I set the quote date in the datepicker
    And I click the "Submit" button
    Then I see error warning "Please Enter the  Quote title" appeared
    And I enter the following information into the form
      | Quote Title | New Title |
    And I click the "Submit" button
    Then I see error warning "Please Enter vendor" appeared
    And I enter the following information into the form
      | Vendor | Mouse |
    And I click the "Submit" button
    Then I see error warning "Quoted Price Can not be Empty" appeared

  @1909
  Scenario: Quotes Tab in Request Details Page-Initiator Role
   ####--------------------happy path-----------------#####
    Given I log into the Purchase Orders app as an "Initiator"
    And I click the "Add Request" button
    And I enter the following information into the form
      | WO Title   | Test <current date> |
      | Location   | Vizag TH            |
      | Department | IT                  |
    And I add the following into the "Item / Service Details" table
      | Item / Service Name | Description              | Quantity |
      | Test                | Test Item <current date> | 1        |
    And I submit the new request
    And I click the "Confirm" button
    And I click the "Yes" button
    And I Click on user icon
    And I click Logout button
    Given I log into the Purchase Orders app as an "Approver"
    And I navigate to the "Pending Approval" tab
    And I open the created WO
    And I approve the request
    And I Click on user icon
    And I click Logout button
    Given I log into the Purchase Orders app as an "Purchaseofficer"
    And I open the created WO
    And Click on Add Quote by Purchase Officer Button
    And I set the quote date in the datepicker
    Then Input values in the Quotes pop-up
      | QuoteTitle | QuoteVendor | QuotedPrice |
      | keyboard   | Apple       | 9000        |
    And I click the "Total Price" box
    And I click the "Submit" and "confirm" button
    And I Click on user icon
    And I click Logout button
    Given I log into the Purchase Orders app as an "Initiator"
    And I navigate to the "Requests" tab
    When I select "Quote Pending Proc. Mgr." from the "Status" header in the grid
    And I open the created WO
    When I go to "Quotes" tab.
    And set the tab to "Quotes"
    Then Verify the following headers are present
      | Quote Date     |
      | Quote Title    |
      | Vendor         |
      | Quoted Price   |
      | Quote Selected |
      | View quote     |
    And I click the eye icon
    Then verify that Quote page is opened.
    And I click the "Total Price" box
    And I click the "Submit" button
    And I click the "confirm" button
    Then I verify that "Quote Successfully Updated" validation  message appears

  @1910
  Scenario: Quotes Tab in Request Details Page-Approver Role
   ####--------------------happy path-----------------#####
    Given I log into the Purchase Orders app as an "Initiator"
    And I click the "Add Request" button
    And I enter the following information into the form
      | WO Title   | Test <current date> |
      | Location   | Vizag TH            |
      | Department | IT                  |
    And I add the following into the "Item / Service Details" table
      | Item / Service Name | Description              | Quantity |
      | Test                | Test Item <current date> | 1        |
    And I submit the new request
    And I click the "Confirm" button
    And I click the "Yes" button
    And I Click on user icon
    And I click Logout button
    Given I log into the Purchase Orders app as an "Approver"
    And I navigate to the "Pending Approval" tab
    And I open the created WO
    And I approve the request
    And I Click on user icon
    And I click Logout button
    Given I log into the Purchase Orders app as an "Purchaseofficer"
    And I open the created WO
    And Click on Add Quote by Purchase Officer Button
    And I set the quote date in the datepicker
    Then Input values in the Quotes pop-up
      | QuoteTitle | QuoteVendor | QuotedPrice |
      | keyboard   | Apple       | 9000        |
    And I click the "Total Price" box
    And I click the "Submit" and "confirm" button
    And I Click on user icon
    And I click Logout button
    Given I log into the Purchase Orders app as an "Approver"
    And I navigate to the "Requests" tab
    When I select "Quote Pending Proc. Mgr." from the "Status" header in the grid
    And I click on the top work order link
    When I go to "Quotes" tab.
    And set the tab to "Quotes"
    Then Verify the following headers are present
      | Quote Date     |
      | Quote Title    |
      | Vendor         |
      | Quoted Price   |
      | Quote Selected |
      | View quote     |
    And I click the eye icon
    Then verify that Quote page is opened.
    And I verify that buttons are disabled

  @1902
  Scenario: Delete Quote
   ####--------------------happy path-----------------#####
    Given I log into the Purchase Orders app as an "Initiator"
    And I click the "Add Request" button
    And I enter the following information into the form
      | WO Title   | Test <current date> |
      | Location   | Vizag TH            |
      | Department | IT                  |
    And I add the following into the "Item / Service Details" table
      | Item / Service Name | Description              | Quantity |
      | Test                | Test Item <current date> | 1        |
    And I submit the new request
    And I click on "Confirm" and "Yes" button
    And I Click on user icon
    And I click Logout button
    Given I log into the Purchase Orders app as an "Approver"
    And I navigate to the "Pending Approval" tab
    And I open the created WO
    And I approve the request
    And I Click on user icon
    And I click Logout button
    Given I log into the Purchase Orders app as an "Purchaseofficer"
    And I open the created WO
    And Click on Add Quote by Purchase Officer Button
    And I set the quote date in the datepicker
    Then Input values in the Quotes pop-up
      | QuoteTitle | QuoteVendor | QuotedPrice |
      | keyboard   | Apple       | 9000        |
    And I click the "Total Price" box
    And I click the "Submit" and "confirm" button
    When I go to "Quotes" tab.
    And set the tab to "Quotes"
    And I delete the Quote

  @1903
  Scenario: Edit and Submit Quote in Quotes Tab
    Given I log into the Purchase Orders app as an "Initiator"
    And I click the "Add Request" button
    And I enter the following information into the form
      | WO Title   | Test <current date> |
      | Location   | Vizag TH            |
      | Department | IT                  |
    And I add the following into the "Item / Service Details" table
      | Item / Service Name | Description              | Quantity |
      | Test                | Test Item <current date> | 1        |
    And I submit the new request
    And I click the "Confirm" button
    And I click the "Yes" button
    And I Click on user icon
    And I click Logout button
    Given I log into the Purchase Orders app as an "Approver"
    And I navigate to the "Pending Approval" tab
    And I open the created WO
    And I approve the request
    And I Click on user icon
    And I click Logout button
    Given I log into the Purchase Orders app as an "Purchaseofficer"
    And I open the created WO
    And Click on Add Quote by Purchase Officer Button
    And I set the quote date in the datepicker
    Then Input values in the Quotes pop-up
      | QuoteTitle | QuoteVendor | QuotedPrice |
      | keyboard   | Apple       | 9000        |
    And I click the "Total Price" box
    And I click the "Submit" and "confirm" button
    And I click on the "Quotes" link
    Then I check if "Approval Status" is "Pending for Approval"
    And I click the eye icon
    And I update "6000" in the "Quoted Price" field
    And I click the "Total Price" box
    And I click the "Submit" and "confirm" button to complete the updation
    Then Verify that Quote is updated successfully.

  @1904
  Scenario: Deleting a Quote
   ####--------------------happy path-----------------#####
    Given I log into the Purchase Orders app as an "Initiator"
    And I click the "Add Request" button
    And I enter the following information into the form
      | WO Title   | Test <current date> |
      | Location   | Vizag TH            |
      | Department | IT                  |
    And I add the following into the "Item / Service Details" table
      | Item / Service Name | Description              | Quantity |
      | Test                | Test Item <current date> | 1        |
    And I submit the new request
    And I click the "Confirm" button
    And I click the "Yes" button
    And I Click on user icon
    And I click Logout button
    Given I log into the Purchase Orders app as an "Approver"
    And I navigate to the "Pending Approval" tab
    And I open the created WO
    And I approve the request
    And I Click on user icon
    And I click Logout button
    Given I log into the Purchase Orders app as an "Purchaseofficer"
    And I open the created WO
    And Click on Add Quote by Purchase Officer Button
    And I set the quote date in the datepicker
    Then Input values in the Quotes pop-up
      | QuoteTitle | QuoteVendor | QuotedPrice |
      | keyboard   | Apple       | 9000        |
    And I click the "Total Price" box
    And I click the "Submit" and "confirm" button
    And I Click on user icon
    And I click Logout button
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






