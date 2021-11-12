Feature: Tests performing the actions on a WO

  @1151
  Scenario: Verify the Submit functionality
    Given I log into the Purchase Orders app as an "Initiator"
    And I click the "Add Request" button
    And I enter the following information into the form
      | WO Title   | Test     |
      | Location   | Vizag TH |
      | Department | IT       |
    And I add the following into the "Item / Service Details" table
      | Item / Service Name | Description              | Quantity |
      | Test                | Test Item <current date> | 1        |
    And I submit the new request
    And I click the "Confirm" button
    And I click the "Yes" button
    Then The new request will be displayed in the grid
    And I enter the following information into the form
      | WO Title | Test <current date> |
    When I click the "Submit" button
    And I click the "confirm" button
    Then I verify that "Request Updated Successfully" validation  message appears
    And I click the "Close" button
    And I select "Pending Request Approval" from the "Status" header in the grid
    And I navigate to the "Requests" tab
    And I click on the top work order link
    And The record is added
      | WO Title |

  @1152
  Scenario:Verify the Cancel Changes functionality
    Given I log into the Purchase Orders app as an "Initiator"
    And I click the "Add Request" button
    And I enter the following information into the form
      | WO Title   | Test     |
      | Location   | Vizag TH |
      | Department | IT       |
    And I add the following into the "Item / Service Details" table
      | Item / Service Name | Description              | Quantity |
      | Test                | Test Item <current date> | 1        |
    And I submit the new request
    And I click on "Confirm" and "Yes" button
    Then The new request will be displayed in the grid
    And I enter the following information into the form
      | WO Title | Test1 |
    And I update my expectations
      | WO Title | Test |
    And I click on "Cancel Changes" button
    And The record is added
      | WO Title |

  @1153
  Scenario:Verify the Close functionality
    Given I log into the Purchase Orders app as an "Initiator"
    And I click the "Add Request" button
    And I enter the following information into the form
      | WO Title   | Test     |
      | Location   | Vizag TH |
      | Department | IT       |
    And I add the following into the "Item / Service Details" table
      | Item / Service Name | Description              | Quantity |
      | Test                | Test Item <current date> | 1        |
    And I submit the new request
    And I click on "Confirm" and "Yes" button
    Then The new request will be displayed in the grid
    And I enter the following information into the form
      | WO Title | Test1 |
    When I click the "Submit" button
    And I click the "confirm" button
    Then I verify that "Request Updated Successfully" validation  message appears
    When I enter the following information into the form
      | WO Title | Test |
    And I update my expectations
      | WO Title | Test1 |
    And I click the "Close" button
    Then The record is closed
    When I open the created WO
    Then The record is added
      | WO Title |

  @1484
  Scenario:  Verifying the functionality of Submit & Next Button
# Setup: make a new Work Order
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
    And I click the "Close" button
# Setup: make a second new Work Order
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
    And I click the "Close" button
    # get two consecutive numbers
    When I get the "WO #" for "row 1" of the grid
    And I store that information for later
    When I get the "WO #" for "row 2" of the grid
    And I enter that information into the grid header
    And I navigate to the "Requests" tab
    When I click on the top work order link
    When I enter the following information into the form
      | WO Title | This is the new note |
    And I click on "Submit & Next" button
    Then The next WO is displayed
    When I use the Back button
    Then The record is added
      | WO Title |

  @1773
  Scenario: Verifying Confirm Button
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
    Then I verify the selected status is "Work Order Raised" for the dropdown "Status"
    Then I verify that "Confirm" button is displayed
    And I click the "Confirm" button
    And I click the "Yes" button
    And I refresh the page
    Then I verify the selected status is "Pending Request Approval" for the dropdown "Status"
    And I Click on user icon
    And I click Logout button

  @5151
  Scenario: Verifying the presence of Reject Button

##---------Create a Work order# request from initiator role and approve the same from approver role-------------###
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
    Then I Verify that request got approved successfully BY APPROVER
    And I Click on user icon
    And I click Logout button

    ###------------Purchase Officer - Login Functionality-------------###
    Given I log into the Purchase Orders app as an "Purchaseofficer"
    And I open the created WO
    Then I verify that Reject button "is" displayed

    ###------------Add a quote from Purchase officer role which is >5K or < 5K-------------###
    And Click on Add Quote by Purchase Officer Button
    And I set the quote date in the datepicker
    Then Input values in the Quotes pop-up
      | QuoteTitle | QuoteVendor | QuotedPrice |
      | keyboard   | Apple       | 9000        |
    And I click the "Total Price" box
    And I click the "Submit" and "confirm" button
    Then I verify that "Quote Successfully Added" validation  message appears
    Then I verify that Reject button "is" displayed
    And I Click on user icon
    And I click Logout button

    ###------------Logged into Procurement manager role/approver's role and approve the raised quote-------------###
    Given I log into the Purchase Orders app as an "Procurementmanager"
    And I open the created WO
    And I click on the "Quotes" link
    And I click the eye icon
    And I click on "Approve" button
    Then I verify that "Quote approved by Procurement Manager, need Manager approval" validation  message appears
    And I Click on user icon
    And I click Logout button

    ###------------logged into the Purchase officer role-------------###
    Given I log into the Purchase Orders app as an "Purchaseofficer"
    And I open the created WO
    ###------------Reject button should not present when quote got approved partially-------------###
    Then I verify that Reject button "is not" displayed
    And I Click on user icon
    And I click Logout button

   ###------------logged into the Approver role-------------###
    Given I log into the Purchase Orders app as an "Approver"
    And I open the created WO
    And I click on the "Quotes" link
    And I click the eye icon
    And I click on "Approve" button
    Then I verify that "Quote Approved" validation  message appears
    And I Click on user icon
    And I click Logout button

    ###------------logged into the Purchase officer role-------------###
    Given I log into the Purchase Orders app as an "Purchaseofficer"
    And I open the created WO
    ###------------Reject button should not present when quote got approved fully-------------###
    Then I verify that Reject button "is not" displayed
    And I Click on user icon
    And I click Logout button

###------------Verify Upload/Download attachment using S3 -------------###
  @14668
  Scenario: Verify Upload/Download attachment in Request Details Page
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
    ##Add Attachment in Request Details Page #
    And I upload an attachment
    Then The file will be displayed in the Attachments grid
    And I Click on user icon
    And I click Logout button
    ##verify pdf file attachment upload and also verify role "Purchaseofficer" can upload/download attachment
    Given I log into the Purchase Orders app as an "Purchaseofficer"
    And I click the "Add Request" button
    And I enter the following information into the form
      | WO Title   | Test <current date> |
      | Location   | Bangalore           |
      | Department | IT                  |
    And I add the following into the "Item / Service Details" table
      | Item / Service Name | Description              | Quantity |
      | Test                | Test Item <current date> | 1        |
    And I submit the new request
    ## upload different types of file
    And I upload an "pdf" attachment and verify is uploaded
    And I Click on user icon
    And I click Logout button
    ##verify doc file attachment upload and also verify role "Procurementmanager" can upload/download  attachment
    Given I log into the Purchase Orders app as an "Procurementmanager"
    And I click the "Add Request" button
    And I enter the following information into the form
      | WO Title   | Test <current date> |
      | Location   | Bangalore           |
      | Department | IT                  |
    And I add the following into the "Item / Service Details" table
      | Item / Service Name | Description              | Quantity |
      | Test                | Test Item <current date> | 1        |
    And I submit the new request
    And I upload an "doc" attachment and verify is uploaded
    And I Click on user icon
    And I click Logout button
    #verify  "Add Attachment" button is not visible for role "poguestdevuser"
    Given I log into the Purchase Orders app as an "poguestdevuser"
    And I navigate to the "Requests" tab
    When I click on the top work order link
    Then I verify that Add Attachment button "is not" displayed

###------------Verify delete file attachment for different roles-------------###
  @14669
  Scenario: Verify delete file attachment for different roles
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
    Then The new request will be displayed in the grid
    And I upload an attachment
    Then The file will be displayed in the Attachments grid
    And I Click on user icon
    And I click Logout button
    #Verify User is not able to delete files attached by other roles
    Given I log into the Purchase Orders app as an "Purchaseofficer"
    And I navigate to the "Requests" tab
    When I click on the top work order link
    Then The file will be displayed in the Attachments grid
    And I delete the attachments
    Then I verify that "you are not authorized to perform this action" validation  message appears
    And I click the "ok" button
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
      | keyboard   | Apple       | 900         |
    And I click the "Total Price" box
    And I click the "Submit" button
    And I click the "confirm" button
    And I Click on user icon
    And I click Logout button
    Given I log into the Purchase Orders app as an "Approver"
    And I open the created WO
    And I click on the "Quotes" link
    And I click the eye icon
    And I click the "Approve" button
    And I click the "confirm" button
    And I Click on user icon
    And I click Logout button
    #Verify "Delete" button is disabled when quote was already approved.
    Given I log into the Purchase Orders app as an "Initiator"
    And I navigate to the "Requests" tab
    When I click on the top work order link
    Then The file will be displayed in the Attachments grid
    Then I verified that hover message displayed


###------------Upload more than 30 MB file-------------###
  @14670
  Scenario: Upload more than 30 MB file
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
    And I upload an attachment more than 30MB
    And I see error warning "The File Size Limit is Exceeded. You can not upload more than 30MB File" appeared
    And I click the "Cancel" button
    And I Click on user icon
    And I click Logout button
    #Verify more than 30Mb file upload is not allowed for role "Approver"
    Given I log into the Purchase Orders app as an "Approver"
    And I navigate to the "Requests" tab
    When I click on the top work order link
    And I upload an attachment more than 30MB
    And I see error warning "The File Size Limit is Exceeded. You can not upload more than 30MB File" appeared
    And I click the "Cancel" button
    And I Click on user icon
    And I click Logout button
    #Verify more than 30Mb file upload is not allowed for role "Purchaseofficer"
    Given I log into the Purchase Orders app as an "Purchaseofficer"
    And I navigate to the "Requests" tab
    When I click on the top work order link
    And I upload an attachment more than 30MB
    And I see error warning "The File Size Limit is Exceeded. You can not upload more than 30MB File" appeared
    And I click the "Cancel" button
    And I Click on user icon
    And I click Logout button
    #Verify more than 30Mb file upload is not allowed for role "Procurementmanager"
    Given I log into the Purchase Orders app as an "Procurementmanager"
    And I navigate to the "Requests" tab
    When I click on the top work order link
    And I upload an attachment more than 30MB
    And I see error warning "The File Size Limit is Exceeded. You can not upload more than 30MB File" appeared
    And I click the "Cancel" button
