package system;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Function {
    public static ResultSet query(Connection conn) throws SQLException {
        return conn.createStatement().executeQuery("SELECT TOP 1 name FROM EMPLOYEE WHERE payment_id = (SELECT TOP 1 ID FROM Payment ORDER BY Amount desc)");
    }
}
