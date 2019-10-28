Feature: Purchase order end to end happy path flow with quoted price is greater than 5K


  Scenario: Purchase order application complete functionality

    ##------------Initiator - Login Functionality-------------###
    Given I navigate to login page of PO app
    And I enter login credentials for "Initiator" role
    And I click on Sign In button
    #################--------KPI Count for Initiator role---#######

    Then Get the Initiator KPI Count
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
    ###-----------------Add Quote Details for the created Work Order---------------###
    And Click on Add Quote by Purchase Officer Button
    Then Input values in the Quotes pop-up
      | QuoteDate  | QuoteTitle | QuoteVendor | QuotedPrice |
      | 10/22/2019 | Laptop     | DELL        | 50000       |
    ###----------------Submit the quote 1--------------------####
    Then click on quotes submit button
    And Click on Add Quote by Purchase Officer Button
    Then Input values in the Quotes pop-up
      | QuoteDate  | QuoteTitle | QuoteVendor | QuotedPrice |
      | 11/22/2019 | Laptop     | Apple       | 90000       |
     ###----------------Submit the quote 2--------------------####
    Then click on quotes submit button
    ###----------------Verify the quote status--------------------####
    And verify the status
    And Verify the approval status on Quotes Tab for 1st Quote
    And Verify the approval status on Quotes Tab for 2nd Quote
    Then I click on the Logout button

    ###------------Procurement Manager - Login Functionality-------------###
    Given I navigate to login page of PO app
    And I enter login credentials for "Procurement Manager" role
    And I click on Sign In button

    #################--------KPI Count for Procurement Manager role---#######

    Then Get the Procurement KPI Count
     ###----------------------Get the Work Order ID-----------------------###
    Then I Navigate to Requests tab and search the raised request
    And verify the status
    And I click on Quotes tab
    And I click on view quote eye icon button
    ###------------Approve any one of the quotes by Proc Mgr-------------###
    And I click on Quotes Approve button
    ###----------------Verify the quote status--------------------####
    And verify the status
    And Verify the approval status on Quotes Tab for 1st Quote
    And Verify the approval status on Quotes Tab for 2nd Quote
    Then I click on the Logout button

    ###------------Approver - Login Functionality-------------###
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

    ###------------Purchase Officer - To raise purchase order Request-------------###
    Given I navigate to login page of PO app
    And I enter login credentials for "Purchase Officer" role
    And I click on Sign In button
    ###----------Navigates to Request Tab and search the raised Work order------------------###
    Then I Navigate to Requests tab and search the raised request
    And verify the status
   ###---------------------------To Add PO, enter all the required details-------------------------------------###
    And I click on Add Purchase Order button
    Then Enter Purchase Order confirmation Details
      | VendorReferenceNumber | VendorAddress              | PartNo |
      | PATRA-1631            | Visakapatnam,AndhraPradesh | 1A     |
    ###---------------------------Raise Purchase Order Request---------------------###
    And I click on RaisePO button
    And I verify the PO status
    And I get the created Purchase Order Number
    ###---------------------------Closing the raised Purchase Order---------------------###
    And I click on ClosePO button
    Then I accept close purchase order alert
    And I verify the PO status
    Then I click on the Logout button

     ##------------Initiator - To Close Work Order------------###
    Given I navigate to login page of PO app
    And I enter login credentials for "Initiator" role
    And I click on Sign In button
    ###-------------------Search the Raised Work order request in grid--------------------###
    Then I Navigate to Requests tab and search the raised request
    And verify the status
    ###---------------------------Closing the raised Work Order--------------------------###
    And I click on CloseWO button
    Then I accept close work order alert
    And verify the status
    Then I click on the Logout button

































