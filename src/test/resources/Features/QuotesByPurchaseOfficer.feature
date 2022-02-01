Feature: Quotes Added by PURCHASE OFFICER TEST CASES

  Background:  ##-------------Common steps in each scenarios --------------##
##-------------Initiator - Login Functionality --------------##
    Given I log into the Purchase Orders app as an "Initiator"
        ###-----------Initiator Adding Requests and Items---------###
    When I click the "Add Request" button
    And I enter the following information into the form
      | WO Title   | Test <current date> |
      | Location   | Vizag TH            |
      | Department | IT                  |
    And I add the following into the "Item / Service Details" table
      | Item / Service Name | Description              | Quantity |
      | Test                | Test Item <current date> | 1        |
    And I submit the new request
    And I click on "Confirm" and "Yes" button
    ###-----------Verify the status once Work Order created---------###
    Then I verify the selected status is "Pending Request Approval" for the dropdown "Status"
    And I Click on user icon
    And I click Logout button
    ###------------Approver - Login Functionality-------------###
    Given I log into the Purchase Orders app as an "Approver"
    ###---------------Approver uses grid to approve the work order---------------###
    And I navigate to the "Pending Approval" tab
    And I "Approve" the request from the grid
    And I click the "confirm" button

###-----------Verify the status from request grid---------###
    And I verify the status is "Pending Quotes" from Requests tab
    And I Click on user icon
    And I click Logout button

  @15119
  Scenario: Verify "Reject" button when Quotes added with Total Price greater than 5k and APPROVE by Proc Mgr and SEND BACK by Approver.
###------------Purchase Officer - Login Functionality-------------###
    Given I log into the Purchase Orders app as an "Purchaseofficer"
    And I open the created WO
    Then I verify the selected status is "Pending Quotes" for the dropdown "Status"
    ###------------Add a quote from Purchase officer role which is >5K-------------###
    And Click on Add Quote by Purchase Officer Button
    And I set the quote date in the datepicker
    Then Input values in the Quotes pop-up
      | QuoteTitle | QuoteVendor | QuotedPrice |
      | keyboard   | Apple       | 6000        |
    And I click the "Total Price" box
    And I click the "Submit" and "confirm" button
    Then I verify that "Quote Successfully Added" validation  message appears
    And I verify the selected status is "Pending Quotes" for the dropdown "Status"
    And I Click on user icon
    And I click Logout button

###------------Procurement Manager role - Login Functionality-------------###
    Given I log into the Purchase Orders app as an "Procurementmanager"
    And I open the created WO
    Then I verify the selected status is "Quote Pending Proc. Mgr." for the dropdown "Status"
    And I click on the "Quotes" link
    Then I check if "Approval Status" is "Pending for Approval"
    And I click the eye icon
    And I click the "Approve" button
    And I click the "confirm" button
    Then I verify the selected status is "Quote Pending Approver" for the dropdown "Status"
    Then I check if "Approval Status" is "Partially Approved"

###------------Approver role - Login Functionality-------------###
    Given I log into the Purchase Orders app as an "Approver"
    And I open the created WO
    And I click on the "Quotes" link
    Then I check if "Approval Status" is "Partially Approved"
    And I click the eye icon
    And I click the "Send back" button
    And I click the "confirm" button
    Then I verify that "Quote Sent Back By Manager to Procurement Manager" validation  message appears
    And I verify that buttons are disabled
    Then I verify the selected status is "Quote Pending Proc. Mgr." for the dropdown "Status"
    Then I check if "Approval Status" is "Not Approved"

###------------Procurement Manager role - Login Functionality-------------###
    Given I log into the Purchase Orders app as an "Procurementmanager"
    And I open the created WO
    And I click on the "Quotes" link
    Then I check if "Approval Status" is "Quote Sent Back by Approver"
    And I click the eye icon
    Then I verify that "Reject" button is displayed
    And I click the "Reject" button
    And I click the "confirm" button
    Then I verify that "Quote Rejected" validation  message appears

  @14807
  Scenario: Approve button disabled for Procurement Manager if quote is <5K
      ###------------Purchase Officer - Login Functionality-------------###
    Given I log into the Purchase Orders app as an "Purchaseofficer"
    ###----------Navigates to Request Tab------------------###
    And I open the created WO
    Then I verify the selected status is "Pending Quotes" for the dropdown "Status"
    ###-----------------Add Quote Details for the created Work Order---------------###
    And Click on Add Quote by Purchase Officer Button
    And I set the quote date in the datepicker
    Then Input values in the Quotes pop-up
      | QuoteTitle | QuoteVendor | QuotedPrice |
      | keyboard   | Apple       | 600         |
    And I click the "Total Price" box
    ###----------------Submit the quote--------------------####
    And I click the "Submit" and "confirm" button
    Then I verify that "Quote Successfully Added" validation  message appears
    ###----------------Verify the quote status--------------------####
    Then I verify the selected status is "Quote Pending Approver" for the dropdown "Status"
    And I Click on user icon
    And I click Logout button

      ###------------Procurement Manager role - Login Functionality-------------###
    Given I log into the Purchase Orders app as an "Procurementmanager"
    And I open the created WO
    And I click on the "Quotes" link
    And I click the eye icon
    Then I verify that "Approve" button is disabled
    And I click the "Close" button
    And I Click on user icon
    And I click Logout button

      ###------------Approver role - Login Functionality-------------###
    Given I log into the Purchase Orders app as an "Approver"
    And I open the created WO
    And I click on the "Quotes" link
    And I click the eye icon
    Then I verify that "Approve" button is displayed

  @15127
  Scenario: When multiple value quotes are available, status updates correctly if user deletes a quote
      ###------------Purchase Officer - Login Functionality-------------###
    Given I log into the Purchase Orders app as an "Purchaseofficer"
    ###----------Navigates to Request Tab------------------###
    And I open the created WO
    Then I verify the selected status is "Pending Quotes" for the dropdown "Status"
    ###-----------------Add Quote Details <5k for the created Work Order---------------###
    And Click on Add Quote by Purchase Officer Button
    And I set the quote date in the datepicker
    Then Input values in the Quotes pop-up
      | QuoteTitle | QuoteVendor | QuotedPrice |
      | keyboard   | Apple       | 600         |
    And I click the "Total Price" box
    ###----------------Submit the quote--------------------####
    And I click the "Submit" and "confirm" button
    Then I verify that "Quote Successfully Added" validation  message appears
    ###----------------Verify the quote status--------------------####
    Then I verify the selected status is "Quote Pending Approver" for the dropdown "Status"
    Then I verify Delete button under "Delete Quote" header
###-----------------Add second Quote Details >5k for the created Work Order---------------###
    And Click on Add Quote by Purchase Officer Button
    And I set the quote date in the datepicker
    Then Input values in the Quotes pop-up
      | QuoteTitle | QuoteVendor | QuotedPrice |
      | keyboard   | Apple       | 6000        |
    And I click the "Total Price" box
    And I click the "Submit" and "confirm" button
    Then I verify that "Quote Successfully Added" validation  message appears
    Then I verify the selected status is "Quote Pending Proc. Mgr." for the dropdown "Status"
    Then I verify delete button present under delete quote header
    And Delete greater than 5K quote
    Then I verify the selected status is "Quote Pending Approver" for the dropdown "Status"