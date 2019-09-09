Feature: WorkOrder Request should get Rejected by approver

  Scenario: WorkOrder Request submitted by initiator and rejected by approver.

    ##------------Initiator - Login Functionality-------------###
    Given I navigate to login page of PO app
    And I enter login credentials
      | email      | password |
      | XXXX  | XXXX |
    And I click on Sign In button
    ###-----------Initiator Adding Requests and Items---------###
    When Click on Add Request
    Then Enter Add Request Details
      | Wotitle| Location  | Department  | ItemName | Description  | Quantity |
      | Test Request for Laptop   | Vizag TH  | IT          |   T1     | Laptop         | 1       |
    And Click on Submit button
    ###------------Add comment/Attachments---------------------###
    Then Click on Add Comment Button
    And Input values in the Comment pop-up
      | Title | Description |
      | New  | Placing a new work order request|
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
    Then I click on Initiator Logout button

    ###------------Approver - Login Functionality-------------###
    Given I navigate to login page of PO app
    And I enter login credentials
      | email      | password |
      | XXXX| XXXX |
    And I click on Sign In button
    ###---------------rejects the work order by Approver---------------###
    Then I Navigate to Pending Approval tab and search the raised request
    And I click on Reject button
    ###-----------Verify the status after rejecting WO---------###
    And verify the status
    Then I click on Approver Logout button
