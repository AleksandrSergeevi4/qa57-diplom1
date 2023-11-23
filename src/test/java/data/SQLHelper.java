package data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {
    private static QueryRunner runner = new QueryRunner();

    private SQLHelper() {
    }

    private static Connection getConnection() throws SQLException {
        var URL = System.getProperty("datasource.url");
        var username = System.getProperty("datasource.username");
        var password = System.getProperty("datasource.password");
        return DriverManager.getConnection(URL, username, password);
    }

    @SneakyThrows
    public static void cleanDatabase() {
        var connection = getConnection();
        runner.execute(connection, "DELETE FROM credit_request_entity");
        runner.execute(connection, "DELETE FROM order_entity");
        runner.execute(connection, "DELETE FROM payment_entity");
    }

    @SneakyThrows
    public static String getPaymentStatusOperation() {
        var SQLStatus = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1";
        var connection = getConnection();
        var status = runner.query(connection, SQLStatus, new ScalarHandler<String>());
        return status;
    }

    @SneakyThrows
    public static String getCreditStatusOperation() {
        var SQLStatus = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1";
        var connection = getConnection();
        var status = runner.query(connection, SQLStatus, new ScalarHandler<String>());
        return status;
    }
}
