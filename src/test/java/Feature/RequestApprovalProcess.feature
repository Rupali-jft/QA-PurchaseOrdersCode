@HappyPath
Feature: Request approval process for all roles
  Runs test cases for test suite S97 in TestRail

      ##------------- TestRail case: C1775 --------------##
  Scenario: Use grid to approve request submitted by Initiator
    ##-------------Initiator - Login Functionality --------------##
    Given I navigate to login page of PO app
    And I enter login credentials for "Initiator" role
    And I click on Sign In button
    Then I am taken to the Purchase Order page
        ###-----------Initiator Adding Requests and Items---------###
    When Click on Add Request
    Then Enter Add Request Details
      | Wotitle                        | Location | Department | ItemName | Description | Quantity |
      | Test for approval through grid | Vizag TH | IT         | T1       | Laptop      | 1        |
    And Click on Submit button
    ###------------Add comment/Attachments---------------------###
    Then Click on Add Comment Button
    And Input values in the Comment pop-up
      | Title   | Description                              |
      | Testing | Testing work order approval through grid |
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
    ###---------------Approver uses grid to approve the work order---------------###
    Then I Navigate to Pending Approval tab and search the raised request
    And I "approve" the request from the grid
    ###-----------Verify the status from Requests tab---------###
    And I verify the status is "Pending Quotes" from Requests tab
    Then I click on the Logout button

    ##-------------Initiator - Login Functionality --------------##
    Given I navigate to login page of PO app
    And I enter login credentials for "Initiator" role
    And I click on Sign In button
    Then I am taken to the Purchase Order page
    ###-----------Verify the status from Requests tab---------###
    And I verify the status is "Pending Quotes" from Requests tab
    Then I click on the Logout button


      ##------------- TestRail case: C1778 --------------##
    Scenario: Use grid to send back request made by Initiator

      ##-------------Initiator - Login Functionality --------------##
      Given I navigate to login page of PO app
      And I enter login credentials for "Initiator" role
      And I click on Sign In button
      Then I am taken to the Purchase Order page
        ###-----------Initiator Adding Requests and Items---------###
      When Click on Add Request
      Then Enter Add Request Details
        | Wotitle                        | Location | Department | ItemName | Description | Quantity |
        | Test for SEND BACK through grid | Vizag TH | IT         | T1       | Laptop      | 1        |
      And Click on Submit button
    ###------------Add comment/Attachments---------------------###
      Then Click on Add Comment Button
      And Input values in the Comment pop-up
        | Title   | Description                              |
        | Testing | Testing work order SEND BACK through grid |
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
    ###---------------Approver uses grid to send back the work order---------------###
      Then I Navigate to Pending Approval tab and search the raised request
      And I "Send Back" the request from the grid
    ###-----------Verify the status after sending back WO---------###
      And I verify the status is "Work Order Raised" from Requests tab
      Then I click on the Logout button

      ##-------------Initiator - Login Functionality --------------##
      Given I navigate to login page of PO app
      And I enter login credentials for "Initiator" role
      And I click on Sign In button
      Then I am taken to the Purchase Order page
    ###-----------Verify the status from Requests tab---------###
      And I verify the status is "Work Order Raised" from Requests tab
      Then I click on the Logout button


       ##------------- TestRail case: C1779 --------------##
  Scenario: Use grid to reject request made by Initiator

      ##-------------Initiator - Login Functionality --------------##
    Given I navigate to login page of PO app
    And I enter login credentials for "Initiator" role
    And I click on Sign In button
    Then I am taken to the Purchase Order page
        ###-----------Initiator Adding Requests and Items---------###
    When Click on Add Request
    Then Enter Add Request Details
      | Wotitle                        | Location | Department | ItemName | Description | Quantity |
      | Test for rejections through grid | Vizag TH | IT         | T1       | Laptop      | 1        |
    And Click on Submit button
    ###------------Add comment/Attachments---------------------###
    Then Click on Add Comment Button
    And Input values in the Comment pop-up
      | Title   | Description                              |
      | Testing | Testing work order rejections through grid |
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
    ###---------------Approver uses grid to send back the work order---------------###
    Then I Navigate to Pending Approval tab and search the raised request
    And I "Reject" the request from the grid
    ###-----------Verify the status after sending back WO---------###
    And I verify the status is "Rejected" from Requests tab
    Then I click on the Logout button

      ##-------------Initiator - Login Functionality --------------##
    Given I navigate to login page of PO app
    And I enter login credentials for "Initiator" role
    And I click on Sign In button
    Then I am taken to the Purchase Order page
    ###-----------Verify the status from Requests tab---------###
    And I verify the status is "Rejected" from Requests tab
    Then I click on the Logout button
