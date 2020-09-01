package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String DB_HOSTNAME ="localhost";
    private static final String DB_DBNAME ="userDB";
    private static final String DB_USERNAME ="root";
    private static final String DB_PASSWORD ="Moscowneversleep2020";

    private static final SessionFactory sessionFactory = BuildSessionFactory();
    private static final Connection SQLConnection = getDBSQLConnection();

    public static Connection getSQLConnection() {
        return SQLConnection;
    }

    // Connect to MySQL
    private static Connection getDBSQLConnection() {
        return getMySQLConnection(DB_HOSTNAME, DB_DBNAME, DB_USERNAME, DB_PASSWORD);
    }

    private static Connection getMySQLConnection(String hostName, String dbName,
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

    private static SessionFactory BuildSessionFactory() {
        SessionFactory sessionFactory = null;
            try {
                Configuration configuration = new Configuration()
                .setProperty("connection.driver_class","com.mysql.jdbc.Driver")
                .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/userDB?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false")
                .setProperty("hibernate.connection.username", "root")
                .setProperty("hibernate.connection.password", "Moscowneversleep2020")
                .addAnnotatedClass(User.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Исключение!" + e);
            }
        return sessionFactory;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        if (getSessionFactory() != null) {
            getSessionFactory().close();
        }
        if (getDBSQLConnection() != null) {
            try {
                getDBSQLConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
