Feature: Data Quality validation for ETL pipeline

  Scenario: Validate source and target record counts
    Given the source and target tables exist
    When I validate record counts between source and target
    Then the record counts should match

  Scenario: Validate no null IDs exist in target
    Given the source and target tables exist
    When I validate target records for null IDs
    Then the target table should not contain null IDs

  Scenario: Validate no duplicate IDs exist in target
    Given the source and target tables exist
    When I validate target records for duplicate IDs
    Then the target table should not contain duplicate IDs

  Scenario: Validate source and target balances match
    Given the source and target tables exist
    When I compare balances between source and target
    Then the balances should match

  Scenario: Validate email format in target table
    Given the source and target tables exist
    When I validate target records for email quality
    Then the email values should be valid

  Scenario: Validate source and target schemas match
    Given the source and target tables exist
    When I compare source and target schemas
    Then the schemas should match