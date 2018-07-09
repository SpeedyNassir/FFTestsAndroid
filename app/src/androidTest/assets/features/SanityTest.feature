Feature: Sanity Test

  @Sanity_01
  Scenario: Verify Tapping on specific news displays full screen.
    Given I launch the News App
    And I should see screen with News heading
    When I Tap on 1 st news
    Then I should see full screen page

  @Sanity_02
  Scenario: Verify Heading for each article in Index Screen.
    Given I launch the News App
    And I should see screen with News heading
    Then I should see Heading for each article in Index Screen

  @Sanity_03
  Scenario: Verify Abstract for each article in Index Screen.
    Given I launch the News App
    And I should see screen with News heading
    Then I should see Abstract for each article in Index Screen

  @Sanity_04
  Scenario: Verify ByLine for each article in Index Screen.
    Given I launch the News App
    And I should see screen with News heading
    Then I should see ByLine for each article in Index Screen

  @Sanity_05
  Scenario: Verify Image for each article in Index Screen.
    Given I launch the News App
    And I should see screen with News heading
    Then I should see Image for each article in Index Screen

  @Sanity_06
  Scenario: Verify No More than 10 Articles are present in Index Screen.
    Given I launch the News App
    And I should see screen with News heading
    Then I Verify No More Than 10 Articles are present in Index Screen

  @Sanity_07
  Scenario: Verify Navigation to Full Screen and back to Index Screen.
    Given I launch the News App
    And I should see screen with News heading
    When I Tap on 1 st news
    Then I should see full screen page
    When I tap on News button
    And I should see screen with News heading

  @Sanity_08
  Scenario: Verify Exactly 10 Articles are present in Index Screen.
    Given I launch the News App
    And I should see screen with News heading
    Then I Verify 10 Articles are present in Index Screen