package jm.task.core.jdbc.util;

import java.sql.Connection;
import com.mysql.cj.jdbc.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {
    // реализуйте настройку соеденения с БД
    private static Connection connection;

    private static final String URL = "jdbc:mysql://localhost:3306/test_schema";
    private static final String USER = "root";
    private static final String PASSWORD = "dorik5656";

    private Util() {
        // приватный конструктор чтобы нельзя было создать ненужных экземпляров
    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Driver driver = new Driver();
                DriverManager.registerDriver(driver);

                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return connection;
    }
}
