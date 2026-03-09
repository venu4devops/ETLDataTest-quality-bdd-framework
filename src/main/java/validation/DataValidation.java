package validation;
import utils.QueryHelper;
import java.util.List;

public class DataValidation {

    private final QueryHelper queryHelper = new QueryHelper();

    public boolean validateRecordCount() throws Exception {
        int sourceCount = queryHelper.getSingleIntResult("SELECT COUNT(*) FROM source_customers");
        int targetCount = queryHelper.getSingleIntResult("SELECT COUNT(*) FROM target_customers");
        return sourceCount == targetCount;
    }

    public boolean validateNoNullIdsInTarget() throws Exception {
        int nullCount = queryHelper.getSingleIntResult(
                "SELECT COUNT(*) FROM target_customers WHERE id IS NULL"
        );
        return nullCount == 0;
    }

    public boolean validateNoDuplicateIdsInTarget() throws Exception {
        int duplicateCount = queryHelper.getSingleIntResult(
                "SELECT COUNT(*) FROM (" +
                        "SELECT id FROM target_customers GROUP BY id HAVING COUNT(*) > 1" +
                        ") dup"
        );
        return duplicateCount == 0;
    }

    public boolean validateBalancesMatch() throws Exception {
        int mismatchCount = queryHelper.getSingleIntResult(
                "SELECT COUNT(*) " +
                        "FROM source_customers s " +
                        "JOIN target_customers t ON s.id = t.id " +
                        "WHERE s.balance <> t.balance"
        );
        return mismatchCount == 0;
    }

    public boolean validateEmailFormatExists() throws Exception {
        int invalidEmailCount = queryHelper.getSingleIntResult(
                "SELECT COUNT(*) FROM target_customers " +
                        "WHERE email IS NULL OR email NOT LIKE '%@%.%'"
        );
        return invalidEmailCount == 0;
    }

    public boolean validateSchemaMatches() throws Exception {
        List<String> sourceColumns = queryHelper.getColumnNames("source_customers");
        List<String> targetColumns = queryHelper.getColumnNames("target_customers");

        return sourceColumns.equals(targetColumns);
    }
}