Feature: Testing Edit Request features

  ##--------------------------------------- TestRail case: C1153 ----------------------------------------##
  Scenario: Close request detail page and ensure changes were not saved

    ##------------Initiator - Login Functionality-------------###
    Given I navigate to login page of PO app
    And I enter login credentials for "Initiator" role
    And I click on Sign In button
    ###-----------Initiator Adding Requests and Items---------###
    When Click on Add Request
    Then Enter Add Request Details
      | Wotitle                 | Location | Department | ItemName | Description | Quantity |
      | Testing request editing | Vizag TH | IT         | T1       | Laptop      | 1        |
    And Click on Submit button
    ###------------Add comment/Attachments---------------------###
    Then Click on Add Comment Button
    And Input values in the Comment pop-up
      | Title   | Description                        |
      | TESTING | Testing of request editing feature |
    ###-----------Verify the status before creating Work Order---------###
    And Click on Submit button
    And verify the status
    Then Get the created Work Order Number
    And I close Request Details screen
    And I try to navigate to the "Requests" tab
    And Find the created request
    And Open the request details for that request
    Then I edit the title from the request details page
    And I close Request Details screen
    And I try to navigate to the "Requests" tab
    And Find the created request
    And Open the request details for that request
    Then I will see that the change was not saved
    And I close Request Details screen
    Then I click on the Logout button

 ##--------------------------------------- TestRail case: C1457 ----------------------------------------##
  Scenario: Cancel adding a comment
     ##------------Initiator - Login Functionality-------------###
    Given I navigate to login page of PO app
    And I enter login credentials for "Initiator" role
    And I click on Sign In button
    And I try to navigate to the "Requests" tab
    And Find the created request
    And Open the request details for that request
    And Click on Add Comment Button
    And Add comment title and description
    And Click the comment cancel button
    Then I will see that comment was not saved
    Then I click on the Logout button

    ##--------------------------------------- TestRail case: C1598 ----------------------------------------##
  Scenario: Close purchase order screen without saving
     ##------------Initiator - Login Functionality-------------###
    Given I navigate to login page of PO app
    And I enter login credentials for "Initiator" role
    And I click on Sign In button
    And I try to navigate to the "Requests" tab
    And Find the created request
    And Open the request details for that request
     ###-----------Confirming the created Work Order---------###
    Then I click on Confirm button
    Then I accept an alert
    Then I click on the Logout button

    ###------------Approver - Login Functionality-------------###
    Given I navigate to login page of PO app
    And I enter login credentials for "Approver" role
    And I click on Sign In button
    ###---------------Approves the work order by Approver---------------###
    Then I Navigate to Pending Approval tab and search the raised request
    And I click on Approve button
    ###-----------Verify the status after approving WO---------###
    And verify the status
    Then I click on the Logout button

   ###------------Purchase Officer - Login Functionality-------------###
    Given I navigate to login page of PO app
    And I enter login credentials for "Purchase Officer" role
    And I click on Sign In button
    ###----------Navigates to Request Tab------------------###
    Then I Navigate to Requests tab and search the raised request
    And verify the status
    ###-----------------Add Quote Details for the created Work Order---------------###
    And Click on Add Quote by Purchase Officer Button
    Then Input values in the Quotes pop-up
      | QuoteDate  | QuoteTitle | QuoteVendor | QuotedPrice |
      | 10/22/2019 | Laptop     | DELL        | 500       |
    ###----------------Submit the quote 1--------------------####
    Then click on quotes submit button
    And verify the status
    And Verify the approval status on Quotes Tab for 1st Quote
    Then I click on the Logout button

    ###------------Approver - Approve approves the Quotes which is raised by purchase officer-------------###
    Given I navigate to login page of PO app
    And I enter login credentials for "Approver" role
    And I click on Sign In button
    ###---------------Get the Work order number and search in grid--------------###
    Then I Navigate to Requests tab and search the raised request
    And verify the status
    And I click on Quotes tab
    And I click on view quote eye icon button
    ###---------------Approves the same quote by Approver---------------###
    And I click on Quotes Approve button
    And verify the status
    Then I click on the Logout button

    ##------------ Purchase officer tests close functionality ------------##
    Given I navigate to login page of PO app
    And I enter login credentials for "Purchase Officer" role
    And I click on Sign In button
    And I try to navigate to the "Requests" tab
    And Find the created request
    And Open the request details for that request
    And Click the Add Purchase Order button
    And I edit the Subject field
    And I close the purchase order screen without saving
    And I try to navigate to the "Purchase Orders" tab
    Then I will see that the purchase order was not raised
    Then I click on the Logout button

     ##--------------------------------------- TestRail case: C1906 ----------------------------------------##
  Scenario: Add Purchase Order button is not shown
     ##------------Initiator - Check for Add Purchase Order button-------------###
    Given I navigate to login page of PO app
    And I enter login credentials for "Initiator" role
    And I click on Sign In button
    And I try to navigate to the "Requests" tab
    And Find the created request
    And Open the request details for that request
    Then I will see that the Add Purchase Order button is not displayed
    Then I click on the Logout button

         ##------------Approver - Check for Add Purchase Order button-------------###
    Given I navigate to login page of PO app
    And I enter login credentials for "Approver" role
    And I click on Sign In button
    And I try to navigate to the "Requests" tab
    And Find the created request
    And Open the request details for that request
    Then I will see that the Add Purchase Order button is not displayed
    Then I click on the Logout button

         ##------------Procurement Manager - Check for Add Purchase Order button-------------###
    Given I navigate to login page of PO app
    And I enter login credentials for "Procurement Manager" role
    And I click on Sign In button
    And I try to navigate to the "Requests" tab
    And Find the created request
    And Open the request details for that request
    Then I will see that the Add Purchase Order button is not displayed
    Then I click on the Logout button

    ##--------------------------------------- TestRail case: C1905 ----------------------------------------##
  Scenario: Cancel editing a quote
     ##------------Initiator - Login Functionality-------------###
    Given I navigate to login page of PO app
    And I enter login credentials for "Purchase Officer" role
    And I click on Sign In button
    And I try to navigate to the "Requests" tab
    And Find the created request
    And Open the request details for that request
    And Go to the "Quotes" tab in Request Details
    And I click on view quote eye icon button
    And Edit the Quote Title field
    And Click the cancel button on the Edit Quote modal
    Then I will see that the title has not changed
    Then I click on the Logout button