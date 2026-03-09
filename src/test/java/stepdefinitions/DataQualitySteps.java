package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import validation.DataValidation;

public class DataQualitySteps {

    private final DataValidation validator = new DataValidation();
    private boolean validationResult;

    @Given("the source and target tables exist")
    public void theSourceAndTargetTablesExist() {
        System.out.println("Source and target tables are available for validation");
    }

    @When("I validate record counts between source and target")
    public void iValidateRecordCountsBetweenSourceAndTarget() throws Exception {
        validationResult = validator.validateRecordCount();
    }

    @Then("the record counts should match")
    public void theRecordCountsShouldMatch() {
        Assert.assertTrue("Record counts do not match", validationResult);
    }

    @When("I validate target records for null IDs")
    public void iValidateTargetRecordsForNullIds() throws Exception {
        validationResult = validator.validateNoNullIdsInTarget();
    }

    @Then("the target table should not contain null IDs")
    public void theTargetTableShouldNotContainNullIds() {
        Assert.assertTrue("Target table contains null IDs", validationResult);
    }

    @When("I validate target records for duplicate IDs")
    public void iValidateTargetRecordsForDuplicateIds() throws Exception {
        validationResult = validator.validateNoDuplicateIdsInTarget();
    }

    @Then("the target table should not contain duplicate IDs")
    public void theTargetTableShouldNotContainDuplicateIds() {
        Assert.assertTrue("Target table contains duplicate IDs", validationResult);
    }

    @When("I compare balances between source and target")
    public void iCompareBalancesBetweenSourceAndTarget() throws Exception {
        validationResult = validator.validateBalancesMatch();
    }

    @Then("the balances should match")
    public void theBalancesShouldMatch() {
        Assert.assertTrue("Balance values do not match", validationResult);
    }

    @When("I validate target records for email quality")
    public void iValidateTargetRecordsForEmailQuality() throws Exception {
        validationResult = validator.validateEmailFormatExists();
    }

    @Then("the email values should be valid")
    public void theEmailValuesShouldBeValid() {
        Assert.assertTrue("Invalid email values found in target table", validationResult);
    }

    @When("I compare source and target schemas")
    public void iCompareSourceAndTargetSchemas() throws Exception {
        validationResult = validator.validateSchemaMatches();
    }

    @Then("the schemas should match")
    public void theSchemasShouldMatch() {
        Assert.assertTrue("Source and target schemas do not match", validationResult);
    }
}