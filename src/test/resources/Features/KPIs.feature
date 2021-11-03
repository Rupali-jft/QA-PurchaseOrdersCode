Feature:Validation of KPIs incrementing when requests are actioned

####----------------------Initiator Role KPIs---------------------####
  @1340
  Scenario: Verify the functionality of Requests Pending Approval KPI By Initiator
    Given I log into the Purchase Orders app as an "Initiator"
    When I get the count for "all" KPI
    And I click the "Add Request" button
    And I enter the following information into the form
      | WO Title   | Test <current date> |
      | Location   | Bangalore           |
      | Department | IT                  |
    And I add the following into the "Item / Service Details" table
      | Item / Service Name | Description              | Quantity |
      | Test                | Test Item <current date> | 1        |
    And I submit the new request
    And I confirm the raised work order
    And I refresh the page
    And I verify the selected status is "Requests Pending Approval" for the dropdown "Status"
    And I click the "Close" button
    And I click on the "Requests Pending Approval" KPI
    Then Verify that "Requests Pending Approval" KPI has "Increased"

  @1341
  Scenario: Verify the functionality of Quotes Pending Approval KPI
##-------------Initiator - Login Functionality --------------##
    Given I log into the Purchase Orders app as an "Initiator"
    When I get the count for "all" KPI
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
    And I confirm the raised work order
    ###-----------Verify the status once Work Order created---------###
    And I refresh the page
    Then I verify the selected status is "Pending Request Approval" for the dropdown "Status"
    And I Click on user icon
    And I click Logout button
##---------Created a Work order# request from initiator role and approve the same from approver role-------------###
    Given I log into the Purchase Orders app as an "Approver"
    And I navigate to the "Pending Approval" tab
    And I open the created WO
    And I approve the request
    Then I Verify that request got approved successfully BY APPROVER
    And I refresh the page
    And I verify the selected status is "Pending Quotes" for the dropdown "Status"
    And I Click on user icon
    And I click Logout button
###------------Purchase Officer - Login Functionality-------------###
    Given I log into the Purchase Orders app as an "Purchaseofficer"
    And I open the created WO
    And Click on Add Quote by Purchase Officer Button
    And I set the quote date in the datepicker
    Then Input values in the Quotes pop-up
      | QuoteTitle | QuoteVendor | QuotedPrice |
      | keyboard   | Apple       | 900         |
    And I click the "Total Price" box
    And I click on "Submit" button
    #And I click the "Submit" button
    #And I click the "confirm" button
    Then I verify the selected status is "Quotes Pending approval" for the dropdown "Status"
    And I Click on user icon
    And I click Logout button
##-------------Initiator - Login Functionality --------------##
    Given I log into the Purchase Orders app as an "Initiator"
    Then Verify that "Quotes Pending approval" KPI has "Increased"

  @1342
  Scenario: Verify the functionality of Returned Requests KPI
##-------------Initiator - Login Functionality --------------##
    Given I log into the Purchase Orders app as an "Initiator"
    When I get the count for "all" KPI
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
    And I confirm the raised work order
    ###-----------Verify the status once Work Order created---------###
    And I refresh the page
    Then I verify the selected status is "Pending Request Approval" for the dropdown "Status"
    And I Click on user icon
    And I click Logout button

###------------Approver - Login Functionality-------------###
    Given I log into the Purchase Orders app as an "Approver"
    ###---------------Approver uses grid to Send_Back the work order---------------###
    And I navigate to the "Pending Approval" tab
    And I "Send back" the request from the grid
    And I click the "confirm" button

    ###-----------Verify the status after sending back WO---------###
    And I verify the status is "Work Order Raised" from Requests tab
    And I Click on user icon
    And I click Logout button
 ##-------------Initiator - Login Functionality --------------##
    Given I log into the Purchase Orders app as an "Initiator"
    Then Verify that "Returned Requests" KPI has "Increased"

  @1843
  Scenario: Verify the functionality of Rejected Requests KPI
 ##-------------Initiator - Login Functionality --------------##
    Given I log into the Purchase Orders app as an "Initiator"
    When I get the count for "all" KPI
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
    And I confirm the raised work order
    ###-----------Verify the status once Work Order created---------###
    And I refresh the page
    Then I verify the selected status is "Pending Request Approval" for the dropdown "Status"
    And I Click on user icon
    And I click Logout button

###------------Approver - Login Functionality-------------###
    Given I log into the Purchase Orders app as an "Approver"
    ###---------------Approver uses grid to approve the work order---------------###
    And I navigate to the "Pending Approval" tab
    And I open the created WO
    And I click on "Reject" button
    #And I click the "confirm" button
    Then I verify that "Requests Rejected Successfully" validation  message appears

 ###-----------Verify the status after Rejecting---------###
    Then I verify the selected status is "Rejected" for the dropdown "Status"
    And I Click on user icon
    And I click Logout button

##-------------Initiator - Login Functionality --------------##
    Given I log into the Purchase Orders app as an "Initiator"
    Then Verify that "Rejected Requests" KPI has "Increased"

####----------------Approver Role KPI's----------------####
  @1345
  Scenario: Verify the functionality of Requests Pending Approval KPI by Approver Role
######--------------Approver-Login Functionality---------------########
    Given I log into the Purchase Orders app as an "Approver"
    And I get the count for "all" KPI
######--------------Initiator-Login Functionality---------------########
    Given I log into the Purchase Orders app as an "Initiator"
    And I click the "Add Request" button
    And I enter the following information into the form
      | WO Title   | Test <current date> |
      | Location   | Bangalore           |
      | Department | IT                  |
    And I add the following into the "Item / Service Details" table
      | Item / Service Name | Description              | Quantity |
      | Test                | Test Item <current date> | 1        |
    And I submit the new request
    And I confirm the raised work order
    ###-----------Verify the status once Work Order created---------###
    And I refresh the page
    Then I verify the selected status is "Pending Request Approval" for the dropdown "Status"
    And I Click on user icon
    And I click Logout button
  ##---------Created a Work order# request from initiator role and approve the same from approver role-------------###
    Given I log into the Purchase Orders app as an "Approver"
    Then Verify that "Requests Pending Approval" KPI has "Increased"

  @1346
  Scenario:Verify the functionality of Requests pending Quotes KPI
 ##-------------Approver - Login Functionality --------------##
    Given I log into the Purchase Orders app as an "Approver"
    When I get the count for "all" KPI
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
    And I confirm the raised work order
    ###-----------Verify the status once Work Order created---------###
    And I refresh the page
    Then I verify the selected status is "Pending Request Approval" for the dropdown "Status"
    And I Click on user icon
    And I click Logout button
##---------Created a Work order# request from initiator role and approve the same from approver role-------------###
    Given I log into the Purchase Orders app as an "Approver"
    And I navigate to the "Pending Approval" tab
    And I open the created WO
    And I approve the request
    Then I Verify that request got approved successfully BY APPROVER
    And I refresh the page
    And I verify the selected status is "Pending Quotes" for the dropdown "Status"
    And I click the "Close" button
########-----------------Verify the Requests Pending Quotes KPI is incremented-----------##########
    Then Verify that "Requests Pending Quotes" KPI has "Increased"

  @1347
  Scenario: Verify the functionality of Quotes Pending Approval KPI By Approver role
###-------------Approver - Login Functionality --------------###
    Given I log into the Purchase Orders app as an "Approver"
    When I get the count for "all" KPI
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
    And I confirm the raised work order
###-----------Verify the status once Work Order created---------###
    And I refresh the page
    Then I verify the selected status is "Pending Request Approval" for the dropdown "Status"
    And I Click on user icon
    And I click Logout button
###---------Created a Work order# request from initiator role and approve the same from approver role-------------###
    Given I log into the Purchase Orders app as an "Approver"
    And I navigate to the "Pending Approval" tab
    And I open the created WO
    And I approve the request
    Then I Verify that request got approved successfully BY APPROVER
    And I refresh the page
    And I verify the selected status is "Pending Quotes" for the dropdown "Status"
    And I Click on user icon
    And I click Logout button
###------------Purchase Officer - Login Functionality-------------###
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
    Then I verify the selected status is "Quotes Pending approval" for the dropdown "Status"
    And I Click on user icon
    And I click Logout button
##-------------Approver - Login Functionality --------------##
    Given I log into the Purchase Orders app as an "Approver"
    Then Verify that "Quotes Pending Approval" KPI has "Increased"

######---------------------Purchase Officer KPI's------------------######
  @1348
  Scenario:Verify the functionality of Requests Pending Quotes KPI by Purchase Officer
 ##-------------Purchase Officer - Login Functionality --------------##
    Given I log into the Purchase Orders app as an "Purchaseofficer"
    When I get the count for "all" KPI
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
    And I confirm the raised work order
    ###-----------Verify the status once Work Order created---------###
    And I refresh the page
    Then I verify the selected status is "Pending Request Approval" for the dropdown "Status"
    And I Click on user icon
    And I click Logout button
##---------Created a Work order# request from initiator role and approve the same from approver role-------------###
    Given I log into the Purchase Orders app as an "Approver"
    And I navigate to the "Pending Approval" tab
    And I open the created WO
    And I approve the request
    Then I Verify that request got approved successfully BY APPROVER
    And I refresh the page
    And I verify the selected status is "Pending Quotes" for the dropdown "Status"
    And I click the "Close" button
    And I Click on user icon
    And I click Logout button
########-----------------Verify the Requests Pending Quotes KPI is incremented-----------##########
    Given I log into the Purchase Orders app as an "Purchaseofficer"
    Then Verify that "Requests Pending Quotes" KPI has "Increased"

  @1349
  Scenario: Verify the functionality of Quotes Pending Approval KPI by Purchase-Officer Role
###-------------PurchaseOfficer - Login Functionality --------------###
    Given I log into the Purchase Orders app as an "Purchaseofficer"
    When I get the count for "all" KPI
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
    And I confirm the raised work order
###-----------Verify the status once Work Order created---------###
    And I refresh the page
    Then I verify the selected status is "Pending Request Approval" for the dropdown "Status"
    And I Click on user icon
    And I click Logout button
###---------Created a Work order# request from initiator role and approve the same from approver role-------------###
    Given I log into the Purchase Orders app as an "Approver"
    And I navigate to the "Pending Approval" tab
    And I open the created WO
    And I approve the request
    Then I Verify that request got approved successfully BY APPROVER
    And I refresh the page
    And I verify the selected status is "Pending Quotes" for the dropdown "Status"
    And I Click on user icon
    And I click Logout button
###------------Purchase Officer - Login Functionality-------------###
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
    Then I verify the selected status is "Quotes Pending approval" for the dropdown "Status"
    And I click the "Close" button
##-------------Verify the Quotes Pending Approval KPI is incremented --------------##
    Then Verify that "Quotes Pending Approval" KPI has "Increased"

  @1350
  Scenario: Verify the functionality of Returned Quotes from Procurement manager role.(Quoted price under 5k)
  ###-------------PurchaseOfficer - Login Functionality --------------###
    Given I log into the Purchase Orders app as an "Purchaseofficer"
    When I get the count for "all" KPI
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
    And I confirm the raised work order
###-----------Verify the status once Work Order created---------###
    And I refresh the page
    Then I verify the selected status is "Pending Request Approval" for the dropdown "Status"
    And I Click on user icon
    And I click Logout button
###---------Login Functionality - Approver Role-------------###
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
    Then I verify the selected status is "Pending Quotes" for the dropdown "Status"
###------------Add a quote from Purchase officer role which is over 5K-------------###
    And Click on Add Quote by Purchase Officer Button
    And I set the quote date in the datepicker
    Then Input values in the Quotes pop-up
      | QuoteTitle | QuoteVendor | QuotedPrice |
      | keyboard   | Apple       | 7000        |
    And I click the "Total Price" box
    And I click the "Submit" button
    And I click the "confirm" button
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
    And I click the "Send back" button
    And I click the "confirm" button
    Then I verify the selected status is "Pending Quotes" for the dropdown "Status"
    And I Click on user icon
    And I click Logout button
####------------Purchase-Officer role - Login Functionality-------------####
    Given I log into the Purchase Orders app as an "Purchaseofficer"
    And I click on the "Returned Quotes" KPI
    And I search the stored work order number
    Then The work order will be displayed in the grid
    Then Verify that "Returned Quotes" KPI has "Increased"

  @1766
  Scenario: Verify the functionality of Returned Quotes from Approver role to Procurement manager role.(Quoted price greater than 5k)
  ###-------------PurchaseOfficer - Login Functionality --------------###
    Given I log into the Purchase Orders app as an "Purchaseofficer"
    When I get the count for "all" KPI
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
    And I confirm the raised work order
###-----------Verify the status once Work Order created---------###
    And I refresh the page
    Then I verify the selected status is "Pending Request Approval" for the dropdown "Status"
    And I Click on user icon
    And I click Logout button
###---------Login Functionality - Approver Role-------------###
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
    Then I verify the selected status is "Pending Quotes" for the dropdown "Status"
###------------Add a quote from Purchase officer role which is over 5K-------------###
    And Click on Add Quote by Purchase Officer Button
    And I set the quote date in the datepicker
    Then Input values in the Quotes pop-up
      | QuoteTitle | QuoteVendor | QuotedPrice |
      | keyboard   | Apple       | 6000        |
    And I click the "Total Price" box
    #And I click the "Submit" button
    #And I click the "confirm" button
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
    And I click on "Send back" button
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
    And I click the "Send back" button
    And I click the "confirm" button
    Then I verify that "Quote Sent Back by Procurement Manager to Purchase Officer" validation  message appears
    Then I verify the selected status is "Pending Quotes" for the dropdown "Status"
    Then I check if "Approval Status" is "Quote Sent Back by Procurement Manager"
    And I Click on user icon
    And I click Logout button
####------------Purchase-Officer role - Login Functionality-------------####
    Given I log into the Purchase Orders app as an "Purchaseofficer"
    And I click on the "Returned Quotes" KPI
    And I search the stored work order number
    Then The work order will be displayed in the grid
    Then Verify that "Returned Quotes" KPI has "Increased"

  @1767
  Scenario:Verify the functionality of Returned Quotes from Approver role.(Quoted price below 5k)
 ###-------------PurchaseOfficer - Login Functionality --------------###
    Given I log into the Purchase Orders app as an "Purchaseofficer"
    When I get the count for "all" KPI
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
    And I confirm the raised work order
###-----------Verify the status once Work Order created---------###
    And I refresh the page
    Then I verify the selected status is "Pending Request Approval" for the dropdown "Status"
    And I Click on user icon
    And I click Logout button
###---------Login Functionality - Approver Role-------------###
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
    Then I verify the selected status is "Pending Quotes" for the dropdown "Status"
###------------Add a quote from Purchase officer role which is >5K-------------###
    And Click on Add Quote by Purchase Officer Button
    And I set the quote date in the datepicker
    Then Input values in the Quotes pop-up
      | QuoteTitle | QuoteVendor | QuotedPrice |
      | keyboard   | Apple       | 3000        |
    And I click the "Total Price" box
    And I click on "Submit" button
    Then I verify that "Quote Successfully Added" validation  message appears
    And I verify the selected status is "Pending Quotes" for the dropdown "Status"
    And I Click on user icon
    And I click Logout button
###------------Approver role - Login Functionality-------------###
    Given I log into the Purchase Orders app as an "Approver"
    And I open the created WO
    And I click on the "Quotes" link
    Then I check if "Approval Status" is "Pending for Approval"
    And I click the eye icon
    And I click the "Send back" button
    And I click the "confirm" button
    Then I verify that "Quote Sent Back to the Purchase officer" validation  message appears
    Then I verify the selected status is "Pending Quotes" for the dropdown "Status"
    And I Click on user icon
    And I click Logout button
####------------Purchase-Officer role - Login Functionality-------------####
    Given I log into the Purchase Orders app as an "Purchaseofficer"
    And I click on the "Returned Quotes" KPI
    And I search the stored work order number
    Then The work order will be displayed in the grid
    Then Verify that "Returned Quotes" KPI has "Increased"

  @1770
  Scenario: Verify the functionality of Requests Pending Approval KPI By procurement Manager
    Given I log into the Purchase Orders app as an "Procurementmanager"
    When I get the count for "all" KPI
    And I click the "Add Request" button
    And I enter the following information into the form
      | WO Title   | Test <current date> |
      | Location   | Bangalore           |
      | Department | IT                  |
    And I add the following into the "Item / Service Details" table
      | Item / Service Name | Description              | Quantity |
      | Test                | Test Item <current date> | 1        |
    And I submit the new request
    And I confirm the raised work order
    And I refresh the page
    And I verify the selected status is "Requests Pending Approval" for the dropdown "Status"
    And I click the "Close" button
    And I click on the "Requests Pending Approval" KPI
    Then Verify that "Requests Pending Approval" KPI has "Increased"

  @1771
  Scenario: Verify the functionality of Returned Requests KPI by Procurement Manager
##-------------Initiator - Login Functionality --------------##
    Given I log into the Purchase Orders app as an "Procurementmanager"
    When I get the count for "all" KPI
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
    And I confirm the raised work order
    ###-----------Verify the status once Work Order created---------###
    And I refresh the page
    Then I verify the selected status is "Pending Request Approval" for the dropdown "Status"
    And I Click on user icon
    And I click Logout button

###------------Approver - Login Functionality-------------###
    Given I log into the Purchase Orders app as an "Approver"
    ###---------------Approver uses grid to Send_Back the work order---------------###
    And I navigate to the "Pending Approval" tab
    And I "Send back" the request from the grid
    And I click the "confirm" button

    ###-----------Verify the status after sending back WO---------###
    And I Click on user icon
    And I click Logout button
 ##-------------Procurement manager - Login Functionality --------------##
    Given I log into the Purchase Orders app as an "Procurementmanager"
    Then Verify that "Returned Requests" KPI has "Increased"







