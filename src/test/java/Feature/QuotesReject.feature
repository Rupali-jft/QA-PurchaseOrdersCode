Feature: Quotes are added by Initiator and rejected by approver

  Scenario: Purchase order quotes reject functionality

   ##------------Initiator - Login Functionality-------------###
    Given I navigate to login page of PO app
    And I enter login credentials for "Initiator" role
    And I click on Sign In button
    ###-----------Initiator Adding Requests and Items---------###
    When Click on Add Request
    Then Enter Add Request Details
      | Wotitle                 | Location | Department | ItemName | Description | Quantity |
      | Test Request for Laptop | Vizag TH | IT         | T1       | Laptop      | 1        |
    And Click on Submit button
    ###------------Add comment/Attachments---------------------###
    Then Click on Add Comment Button
    And Input values in the Comment pop-up
      | Title | Description                      |
      | New   | Placing a new work order request |
    And Click on Add Attachment
    ###-----------Verify the status before creating Work Order---------###
    And Click on Submit button
    And verify the status
    Then Get the created Work Order Number
    ###-----------Confirming the created Work Order---------###
    Then I click on Confirm button
    Then I accept an alert
    ###----------Verify the status once work order created----------###
    And verify the status
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
    ###-----------------Giving permission to initiator to add quotes---------------###
    And Click on Add Quote by Initiator Button
    Then I accept giving Add Quote Access to Initiator
    And verify the status
    Then I click on the Logout button

     ###------------Initiator - To add Quotes when Quoted price is less than 5K-------------###
    Given I navigate to login page of PO app
    And I enter login credentials for "Initiator" role
    And I click on Sign In button
    Then I Navigate to Requests tab and search the raised request
    And Click on Add Quote by Initiator Button
    Then I accept giving Add Quote Access to Initiator
    Then Input values in the Quotes pop-up
      | QuoteDate  | QuoteTitle | QuoteVendor | QuotedPrice |
      | 10/22/2019 | Mouse      | DELL        | 500         |
    ###----------------Submit the quote 1--------------------####
    Then click on quotes submit button
    And Click on Add Quote by Initiator Button
    Then Input values in the Quotes pop-up
      | QuoteDate  | QuoteTitle | QuoteVendor | QuotedPrice |
      | 11/22/2019 | keyboard   | Apple       | 900         |
     ###----------------Submit the quote 2----------------------####
    Then click on quotes submit button
    ###----------------Verify the quote status--------------------####
    And verify the status
    And Verify the approval status on Quotes Tab for 1st Quote
    And Verify the approval status on Quotes Tab for 2nd Quote
    Then I click on the Logout button

   ###------------Approver - Approve rejects the Quotes which is raised by initiator-------------###
    Given I navigate to login page of PO app
    And I enter login credentials for "Approver" role
    And I click on Sign In button
    ###---------------Get the Work order number and search in grid--------------###
    Then I Navigate to Requests tab and search the raised request
    And verify the status
    And I click on Quotes tab
    And I click on view quote eye icon button
    ###---------------reject the same quote by Approver---------------###
    And I click on Quotes Reject button
    And verify the status
    Then I click on the Logout button

