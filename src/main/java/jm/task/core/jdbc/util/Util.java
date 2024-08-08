package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:/kata";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "8305";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            if (!connection.isClosed()) {
                System.out.println("Ну типа подключился, и чо?");
            }
        } catch (SQLException e) {
            System.out.println("Нет подключения");
            e.printStackTrace();
        }
        return connection;
    }
}
