Feature: PO Login

  @Scenario1

  Scenario: Request Initiator Role Login, Add and Details Request, KPI's and Logout

    Given I navigate to login page of PO app
    And I enter login credentials
      | email      | password |
      | XXXXX| XXXXXX |
    And I click on Sign In button

    #################--------KPI Count for Initiator role---#######

    Then Get the Initiator KPI Count

    ##################--------Add Request-------####################
    When Click on Add Request
    Then Enter Add Request Details
      | Wotitle| Location  | Department  | ItemName | Description  | Quantity | ItemName1 | Description1 | Quantity1 |
      | test   | Vizag TH  | IT          |   Test   | Test         | 12       | test1      | Test1       | Test1     |
    And Click on Submit button

    ################-------Delete Item--------####################

    Then Delete the Item

    ##################--------Details Policy-------####################

    ###########---------Questions,Notes,History Tabs----###########

    Then Click on Add Note Button
    And Input values in the Note pop-up
      | Title | Description |
      | Note  | Note        |

    And Click on Add Quote Button
    Then Input values in the Quotes pop-up
    | QuoteDate  | QuoteTitle | QuoteVendor | QuotedPrice|
    | 06/09/2019 | Test Quote | Renuka      | 500        |

    And Click on Add Attachment
    And Click on Notes Tab
    And Click on History tab





