Feature: Purchase order negative tests and Low priority test cases

  Scenario: Purchase order Low priority and negative test cases

    ##------------Initiator - Login Functionality-------------###
    Given I navigate to login page of PO app
    And I enter login credentials
      | email      | password |
      | XXXX  | XXXX |
    And I click on Sign In button
      #C1298 - Non allowed characters entered into WO# Grid
    Then I entered invalid characters into WO# column
    Then I observed the no data available message
    ###-----------Initiator Adding Requests and Items by keeping location dropdown empty---------###
    When Click on Add Request
    ## C1497 - Add Request by keeping any of the field(Location) empty
    Then Enter Add Request Details
      | Wotitle| Location  | Department  | ItemName | Description  | Quantity |
      | Test Request for Laptop   |   | IT          |   T1     | Laptop         | 1       |
    Then I verify Submit button presence
    Then I verify Cancel Changes button presence
    Then I verify Close button presence
    And Click on Submit button
    #Verifying the error message for location
    Then I verified the Location error message
    Then I click on Initiator Logout button

    #C1152 - Cancel changes
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
      | Test Request for Laptop   | Bangalore  | IT          |   T1     | Laptop         | 1       |
    Then I click on CancelChanges button
    Then Enter Add Request Details
      | Wotitle| Location  | Department  | ItemName | Description  | Quantity |
      | Test Request for Mouse   | Hyderabad  | IT          |   T1     | Laptop         | 1       |
    And Click on Submit button

    #C1458 - Add comment mandatory fields blank
      ###------------Add comment/Attachments---------------------###
    Then Click on Add Comment Button
    And Input values in the Comment pop-up
      | Title | Description |
      | New  | |
    Then I verified the error message when comments description is empty
    Then I click on Initiator Logout button

    #C1899 - Add Quote is not successful when mandatory fields are blank
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
    ###---------------Approves the work order by Approver---------------###
    Then I Navigate to Pending Approval tab and search the raised request
    And I click on Approve button
    ###-----------Verify the status after approving WO---------###
    And verify the status
    Then I click on Approver Logout button
      ###------------Purchase Officer - Login Functionality-------------###
    Given I navigate to login page of PO app
    And I enter login credentials
      | email      | password |
      |XXXX | XXXX |
    And I click on Sign In button
    ###----------Navigates to Request Tab------------------###
    Then I Navigate to Requests tab and search the raised request
    And verify the status
    ###-----------------Add Quote Details for the created Work Order---------------###
    And Click on Add Quote by Purchase Officer Button
    Then Input values in the Quotes pop-up
      | QuoteDate  | QuoteTitle | QuoteVendor | QuotedPrice|
      | 10/22/2019 | Laptop  | DELL      |        |
    ###----------------Submit the quote --------------------####
    ##Then click on quotes submit button
    Then I verified the error message when quotedPrice Value is empty
    Then I click on Purchase officer role Logout button


  ##C1907 - Edit the Item/Service
    # #------------Initiator - Login Functionality-------------###
    Given I navigate to login page of PO app
    And I enter login credentials
      | email      | password |
      | XXXX | XXXX |
    And I click on Sign In button
    ###-----------Initiator Adding Requests and Items by keeping location dropdown empty---------###
    When Click on Add Request
    Then Enter Add Request Details
      | Wotitle| Location  | Department  | ItemName | Description  | Quantity |
      | Test Request for Laptop   | Bangalore   | IT          |   T1     | Laptop         | 1       |
    Then I verify Submit button presence
    Then I click on edit item icon and edited and submitted
    And Click on Submit button
    Then I click on Initiator Logout button

  #C1904Edit - Delete Quote
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
      |XXXX | XXXX |
    And I click on Sign In button
    ###---------------Approves the work order by Approver---------------###
    Then I Navigate to Pending Approval tab and search the raised request
    And I click on Approve button
    ###-----------Verify the status after approving WO---------###
    And verify the status
    Then I click on Approver Logout button

    ###------------Purchase Officer - Login Functionality-------------###
    Given I navigate to login page of PO app
    And I enter login credentials
      | email      | password |
      | XXXX | XXXX |
    And I click on Sign In button
    ###----------Navigates to Request Tab------------------###
    Then I Navigate to Requests tab and search the raised request
    And verify the status
    ###-----------------Add Quote Details for the created Work Order---------------###
    And Click on Add Quote by Purchase Officer Button
    Then Input values in the Quotes pop-up
      | QuoteDate  | QuoteTitle | QuoteVendor | QuotedPrice|
      | 10/22/2019 | Laptop  | DELL      | 10000       |
    ###----------------Submit the quote --------------------####
    Then click on quotes submit button
    And verify the status
    Then click on quotes delete button
    And verify the status







