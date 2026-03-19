package utils;

import config.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class QueryHelper {


    public int getSingleIntResult(String sql) throws Exception {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        }
    }

    public List<String> getColumnNames(String tableName) throws Exception {
        List<String> columns = new ArrayList<>();

        String sql = "SELECT * FROM " + tableName + " LIMIT 1";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                columns.add(metaData.getColumnName(i).toLowerCase());
            }
        }

        return columns;
    }
    public boolean hasDuplicates(String tableName, String columnName) throws Exception {

        String sql = "SELECT " + columnName + ", COUNT(*) " +
                "FROM " + tableName +
                " GROUP BY " + columnName +
                " HAVING COUNT(*) > 1";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            return rs.next(); // true = duplicates exist
        }
    }

public boolean hasCustomersBelowBalance(String tableName, int threshold) throws Exception {

    String sql = "SELECT id, name, balance FROM " + tableName + " WHERE balance < ?";
    boolean hasInvalid = false;
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, threshold);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            hasInvalid = true;
            System.out.println(
                    "Invalid Customer -> ID: " + rs.getInt("id") +
                            ", Name: " + rs.getString("name") +
                            ", Balance: " + rs.getInt("balance")
            );
        }
    }
    return hasInvalid;
}

}