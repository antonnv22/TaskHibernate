package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String DB_HOSTNAME ="localhost";
    private static final String DB_DBNAME ="userDB";
    private static final String DB_USERNAME ="root";
    private static final String DB_PASSWORD ="Moscowneversleep2020";
    // Connect to MySQL
    public static Connection getMySQLConnection() {
        return getMySQLConnection(DB_HOSTNAME, DB_DBNAME, DB_USERNAME, DB_PASSWORD);
    }

    public static Connection getMySQLConnection(String hostName, String dbName,
                                                String userName, String password) {
        Connection connection = null;
        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName+
                "?verifyServerCertificate=false"+
                "&useSSL=false"+
                "&requireSSL=false"+
                "&useLegacyDatetimeCode=false"+
                "&amp"+
                "&serverTimezone=UTC";
        try {
            connection = DriverManager.getConnection(connectionURL, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
