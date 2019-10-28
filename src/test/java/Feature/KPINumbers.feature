Feature: Validation of KPI incrementing when requests are actioned

  ##--------------------------------------- TestRail case: C1341 ----------------------------------------##
  Scenario: Verify the functionality of Quotes Pending Approval KPI for Initiator
     ##-------------Initiator - Login Functionality --------------##
    Given I navigate to login page of PO app
    And I enter login credentials for "Initiator" role
    And I click on Sign In button
    Then I am taken to the Purchase Order page
     #################--------Store the initial KPI number---#######
    Then Get the "Quotes Pending Approval" KPI Count
        ###-----------Initiator Adding Requests and Items---------###
    When Click on Add Request
    Then Enter Add Request Details
      | Wotitle                                      | Location | Department | ItemName | Description | Quantity |
      | Test for Quotes Pending Approval KPI changes | Vizag TH | IT         | T1       | Laptop      | 1        |
    And Click on Submit button

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
      | 11/22/2019 | Laptop     | DELL        | 5000        |
    ###----------------Submit the quote 1--------------------####
    Then click on quotes submit button
    ###----------------Verify the quote status--------------------####
    And verify the status
    And Verify the approval status on Quotes Tab for 1st Quote
    Then I click on the Logout button

     ##-------------Initiator - Login Functionality --------------##
    Given I navigate to login page of PO app
    And I enter login credentials for "Initiator" role
    And I click on Sign In button
    Then I am taken to the Purchase Order page
     #################--------Compare the KPI to initial number---#######
    Then Get the "Quotes Pending Approval" KPI Count
    Then I click on the Logout button

   #########################################################################################################
  ################## Test cases below this line require logins that are not currently setup ##################
  ################## Once the logins have been created and added, please uncomment and test ##################
   #########################################################################################################


#   ##--------------------------------------- TestRail case: C1349 ----------------------------------------##
#  Scenario: Verify the functionality of Quotes Pending Approval KPI for Purchase Officer
#     ##-------------2. Add request with the item details --------------##
#    Given I navigate to login page of PO app
#    And I enter login credentials for "Purchase Officer" role
#    And I click on Sign In button
#    Then I am taken to the Purchase Order page
#     #################--------Store the initial KPI number---#######
#    Then Get the "Quotes Pending Approval" KPI Count
#        ###-----------Initiator Adding Requests and Items---------###
#    When Click on Add Request
#    Then Enter Add Request Details
#      | Wotitle                                      | Location | Department | ItemName | Description | Quantity |
#      | Test for Quotes Pending Approval KPI changes | Vizag TH | IT         | T1       | Laptop      | 1        |
#    And Click on Submit button
#    ###-----------Verify the status before creating Work Order---------###
#    And Click on Submit button
#    And verify the status
#    Then Get the created Work Order Number
#    ###-----------Confirming the created Work Order---------###
#    Then I click on Confirm button
#    Then I accept an alert
#    ###----------Verify the status once work order created----------###
#    And verify the status
#    Then I click on the Logout button
#
#    #-- 3. Verify that their approver approves the request an the status is updated to "Requests Pending Quotes" --#
#    Given I navigate to login page of PO app
#    And I enter login credentials for "Approver" role
#    And I click on Sign In button
#    ###---------------Approves the work order by Approver---------------###
#    Then I Navigate to Pending Approval tab and search the raised request
#    And I click on Approve button
#    ###-----------Verify the status after approving WO---------###
#    And verify the status
#    Then I click on the Logout button
#
#    ###------------4. Verify requests goes to PO and add quote with a price higher than $5000-------------###
#    Given I navigate to login page of PO app
#    And I enter login credentials for "Purchase Officer" role
#    And I click on Sign In button
#    ###----------Navigates to Request Tab------------------###
#    Then I Navigate to Requests tab and search the raised request
#    And verify the status
#    ###-----------------Add Quote Details for the created Work Order---------------###
#    And Click on Add Quote by Purchase Officer Button
#    Then Input values in the Quotes pop-up
#      | QuoteDate  | QuoteTitle | QuoteVendor | QuotedPrice |
#      | 11/22/2019 | Laptop     | DELL        | 5000        |
#    ###----------------Submit the quote 1--------------------####
#    Then click on quotes submit button
#    ###----------------Verify the quote status--------------------####
#    And verify the status
#    And Verify the approval status on Quotes Tab for 1st Quote
#    Then I click on the Logout button
#
#    ###------------Purchase Officer - Login Functionality-------------###
#    Given I navigate to login page of PO app
#    And I enter login credentials for "Purchase Officer" role
#    And I click on Sign In button
#    ###----------Navigates to Request Tab------------------###
#    Then I Navigate to Requests tab and search the raised request
#    And verify the status
#    ###-----------------Add Quote Details for the created Work Order---------------###
#    And Click on Add Quote by Purchase Officer Button
#    Then Input values in the Quotes pop-up
#      | QuoteDate  | QuoteTitle | QuoteVendor | QuotedPrice |
#      | 11/22/2019 | Laptop     | DELL        | 5000        |
#    ###----------------Submit the quote 1--------------------####
#    Then click on quotes submit button
#    ###----------------Verify the quote status--------------------####
#    And verify the status
#    And Verify the approval status on Quotes Tab for 1st Quote
#    Then I click on the Logout button
#
#     ##-------------Initiator - Login Functionality --------------##
#    Given I navigate to login page of PO app
#    And I enter login credentials for "Initiator" role
#    And I click on Sign In button
#    Then I am taken to the Purchase Order page
#     #################--------Compare the KPI to initial number---#######
#    Then Get the "Quotes Pending Approval" KPI Count
#    Then I click on the Logout button
#
#    ##--------------------------------------- TestRail case: C1350 ----------------------------------------##
#  Scenario: Verify the functionality of Returned Quotes from Procurement manager role with Quoted price>5k
#     ##-------------Initiator - Login Functionality --------------##
#    Given I navigate to login page of PO app
#    And I enter login credentials for "Purchase Officer" role
#    And I click on Sign In button
#    Then I am taken to the Purchase Order page
#     #################--------Store the initial KPI number---#######
#    Then Get the "Quotes Pending Approval" KPI Count
#        ###-----------Initiator Adding Requests and Items---------###
#    When Click on Add Request
#    Then Enter Add Request Details
#      | Wotitle                                      | Location | Department | ItemName | Description | Quantity |
#      | Test for Quotes Pending Approval KPI changes | Vizag TH | IT         | T1       | Laptop      | 1        |
#    And Click on Submit button
#
#    ###-----------Verify the status before creating Work Order---------###
#    And Click on Submit button
#    And verify the status
#    Then Get the created Work Order Number
#    ###-----------Confirming the created Work Order---------###
#    Then I click on Confirm button
#    Then I accept an alert
#    ###----------Verify the status once work order created----------###
#    And verify the status
#    Then I click on the Logout button
#
#    ###------------Approver - Login Functionality-------------###
#    Given I navigate to login page of PO app
#    And I enter login credentials for "Approver" role
#    And I click on Sign In button
#    ###---------------Approves the work order by Approver---------------###
#    Then I Navigate to Pending Approval tab and search the raised request
#    And I click on Approve button
#    ###-----------Verify the status after approving WO---------###
#    And verify the status
#    Then I click on the Logout button
#
#    ###------------Purchase Officer - Login Functionality-------------###
#    Given I navigate to login page of PO app
#    And I enter login credentials for "Purchase Officer" role
#    And I click on Sign In button
#    ###----------Navigates to Request Tab------------------###
#    Then I Navigate to Requests tab and search the raised request
#    And verify the status
#    ###-----------------Add Quote Details for the created Work Order---------------###
#    And Click on Add Quote by Purchase Officer Button
#    Then Input values in the Quotes pop-up
#      | QuoteDate  | QuoteTitle | QuoteVendor | QuotedPrice |
#      | 11/22/2019 | Laptop     | DELL        | 5000        |
#    ###----------------Submit the quote 1--------------------####
#    Then click on quotes submit button
#    ###----------------Verify the quote status--------------------####
#    And verify the status
#    And Verify the approval status on Quotes Tab for 1st Quote
#    Then I click on the Logout button
#
#     ##-------------Initiator - Login Functionality --------------##
#    Given I navigate to login page of PO app
#    And I enter login credentials for "Initiator" role
#    And I click on Sign In button
#    Then I am taken to the Purchase Order page
#     #################--------Compare the KPI to initial number---#######
#    Then Get the "Quotes Pending Approval" KPI Count
#    Then I click on the Logout button
#
#    ##--------------------------------------- TestRail case: C1766 ----------------------------------------##
#  Scenario: Verify the functionality of Returned Quotes from Approver role to Procurement manager role with Quoted price>5k
#     ##-------------2. Add request with item details --------------##
#    Given I navigate to login page of PO app
#    And I enter login credentials for "Purchase Officer" role
#    And I click on Sign In button
#    Then I am taken to the Purchase Order page
#     #################--------Store the initial KPI number---#######
#    Then Get the "Returned Quotes" KPI Count
#        ###-----------Initiator Adding Requests and Items---------###
#    When Click on Add Request
#    Then Enter Add Request Details
#      | Wotitle                              | Location | Department | ItemName | Description | Quantity |
#      | Test for Returned Quotes KPI changes | Vizag TH | IT         | T1       | Laptop      | 1        |
#    And Click on Submit button
#
#    ###-----------Verify the status before creating Work Order---------###
#    And Click on Submit button
#    And verify the status
#    Then Get the created Work Order Number
#    ###-----------Confirming the created Work Order---------###
#    Then I click on Confirm button
#    Then I accept an alert
#    ###----------Verify the status once work order created----------###
#    And verify the status
#    Then I click on the Logout button
#
#    ###------------Step 3. Request approved by Approver role-------------###
#    Given I navigate to login page of PO app
#    And I enter login credentials for "Approver" role
#    And I click on Sign In button
#    ###---------------Approves the work order by Approver---------------###
#    Then I Navigate to Pending Approval tab and search the raised request
#    And I click on Approve button
#    ###-----------Verify the status after approving WO---------###
#    And verify the status
#    Then I click on the Logout button
#
#    ###------------Step 4. Purchase Officer adds quote with quoted price higher than $5000-------------###
#    Given I navigate to login page of PO app
#    And I enter login credentials for "Purchase Officer" role
#    And I click on Sign In button
#    ###----------Navigates to Request Tab------------------###
#    Then I Navigate to Requests tab and search the raised request
#    And verify the status
#    ###-----------------Add Quote Details for the created Work Order---------------###
#    And Click on Add Quote by Purchase Officer Button
#    Then Input values in the Quotes pop-up
#      | QuoteDate  | QuoteTitle | QuoteVendor | QuotedPrice |
#      | 11/22/2019 | Laptop     | DELL        | 10000       |
#    ###----------------Submit the quote 1--------------------####
#    Then click on quotes submit button
#    ###----------------Verify the quote status--------------------####
#    And verify the status
#    And Verify the approval status on Quotes Tab for 1st Quote
#    Then I click on the Logout button
#
#    ###------------Step 7. Login with Procurement Manager to approve quote-------------###
#    Given I navigate to login page of PO app
#    And I enter login credentials for "Procurement Manager" role
#    And I click on Sign In button
#       ###----------------------Get the Work Order ID-----------------------###
#    Then I Navigate to Requests tab and search the raised request
#    And verify the status
#    And I click on Quotes tab
#    And I click on view quote eye icon button
#    ###------------Approve any one of the quotes by Proc Mgr-------------###
#    And I click on Quotes Approve button
#    ###----------------Verify the quote status--------------------####
#    And verify the status
#    And Verify the approval status on Quotes Tab for 1st Quote
#    Then I click on the Logout button
#
#   ###------------Step 10. Approver sends back the quote-------------###
#    Given I navigate to login page of PO app
#    And I enter login credentials for "Approver" role
#    And I click on Sign In button
#    ###---------------Get the Work order number and search in grid--------------###
#    Then I Navigate to Requests tab and search the raised request
#    And verify the status
#    And I click on Quotes tab
#    And I click on view quote eye icon button
#    ###---------------Sendback the same quote by Approver---------------###
#    And I click on Quotes SendBack button
#    And verify the status
#    Then I click on the Logout button
#
#    ###------------Step 12. Procurement Manager sends back the quote-------------###
#    Given I navigate to login page of PO app
#    And I enter login credentials for "Procurement Manager" role
#    And I click on Sign In button
#    ###---------------Get the Work order number and search in grid--------------###
#    Then I Navigate to Requests tab and search the raised request
#    And verify the status
#    And I click on Quotes tab
#    And I click on view quote eye icon button
#    ###---------------Sendback the same quote by Approver---------------###
#    And I click on Quotes SendBack button
#    And verify the status
#    Then I click on the Logout button
#
#     ##-------------Step 14. Purchase Officer verifies "Returned Quotes" KPI count has increased --------------##
#    Given I navigate to login page of PO app
#    And I enter login credentials for "Initiator" role
#    And I click on Sign In button
#    Then I am taken to the Purchase Order page
#     #################--------Compare the KPI to initial number---#######
#    Then Get the "Quotes Pending Approval" KPI Count
#    Then I click on the Logout button
#
#        ##--------------------------------------- TestRail case: C1767 ----------------------------------------##
#  Scenario: Verify the functionality of Returned Quotes from Approver role to Procurement manager role with Quoted price<5k
#     ##-------------2. Add request with item details --------------##
#    Given I navigate to login page of PO app
#    And I enter login credentials for "Purchase Officer" role
#    And I click on Sign In button
#    Then I am taken to the Purchase Order page
#     #################--------Store the initial KPI number---#######
#    Then Get the "Returned Quotes" KPI Count
#        ###-----------Initiator Adding Requests and Items---------###
#    When Click on Add Request
#    Then Enter Add Request Details
#      | Wotitle                              | Location | Department | ItemName | Description | Quantity |
#      | Test for Returned Quotes KPI changes | Vizag TH | IT         | T1       | Laptop      | 1        |
#    And Click on Submit button
#
#    ###-----------Verify the status before creating Work Order---------###
#    And Click on Submit button
#    And verify the status
#    Then Get the created Work Order Number
#    ###-----------Confirming the created Work Order---------###
#    Then I click on Confirm button
#    Then I accept an alert
#    ###----------Verify the status once work order created----------###
#    And verify the status
#    Then I click on the Logout button
#
#    ###------------Step 3. Request approved by Approver role-------------###
#    Given I navigate to login page of PO app
#    And I enter login credentials for "Approver" role
#    And I click on Sign In button
#    ###---------------Approves the work order by Approver---------------###
#    Then I Navigate to Pending Approval tab and search the raised request
#    And I click on Approve button
#    ###-----------Verify the status after approving WO---------###
#    And verify the status
#    Then I click on the Logout button
#
#    ###------------Step 4. Purchase Officer adds quote with quoted price higher than $5000-------------###
#    Given I navigate to login page of PO app
#    And I enter login credentials for "Purchase Officer" role
#    And I click on Sign In button
#    ###----------Navigates to Request Tab------------------###
#    Then I Navigate to Requests tab and search the raised request
#    And verify the status
#    ###-----------------Add Quote Details for the created Work Order---------------###
#    And Click on Add Quote by Purchase Officer Button
#    Then Input values in the Quotes pop-up
#      | QuoteDate  | QuoteTitle | QuoteVendor | QuotedPrice |
#      | 11/22/2019 | Laptop     | DELL        | 2000        |
#    ###----------------Submit the quote 1--------------------####
#    Then click on quotes submit button
#    ###----------------Verify the quote status--------------------####
#    And verify the status
#    And Verify the approval status on Quotes Tab for 1st Quote
#    Then I click on the Logout button
#
#   ###------------Step 8. Approver sends back the quote-------------###
#    Given I navigate to login page of PO app
#    And I enter login credentials for "Approver" role
#    And I click on Sign In button
#    ###---------------Get the Work order number and search in grid--------------###
#    Then I Navigate to Requests tab and search the raised request
#    And verify the status
#    And I click on Quotes tab
#    And I click on view quote eye icon button
#    ###---------------Sendback the same quote by Approver---------------###
#    And I click on Quotes SendBack button
#    And verify the status
#    Then I click on the Logout button
#
#    ###------------Step 12. Procurement Manager sends back the quote-------------###
#    Given I navigate to login page of PO app
#    And I enter login credentials for "Procurement Manager" role
#    And I click on Sign In button
#    ###---------------Get the Work order number and search in grid--------------###
#    Then I Navigate to Requests tab and search the raised request
#    And verify the status
#    And I click on Quotes tab
#    And I click on view quote eye icon button
#    ###---------------Sendback the same quote by Approver---------------###
#    And I click on Quotes SendBack button
#    And verify the status
#    Then I click on the Logout button
#
#     ##-------------Step 14. Purchase Officer verifies "Returned Quotes" KPI count has increased --------------##
#    Given I navigate to login page of PO app
#    And I enter login credentials for "Initiator" role
#    And I click on Sign In button
#    Then I am taken to the Purchase Order page
#     #################--------Compare the KPI to initial number---#######
#    Then Get the "Quotes Pending Approval" KPI Count
#    Then I click on the Logout button