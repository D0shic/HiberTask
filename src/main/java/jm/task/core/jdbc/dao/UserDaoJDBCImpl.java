package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS users (id INT AUTO_INCREMENT PRIMARY KEY," +
                                  " name VARCHAR(50), lastName VARCHAR(50), age INT)";
        String succeedMessage   = "Таблица успешно создана";
        executeStatement(createTableQuery, succeedMessage);
    }

    private static void executeStatement(String query, String message) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println(message);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void dropUsersTable() {
        String dropTableQuery = "DROP TABLE IF EXISTS users";
        String succeedMessage = "Таблица успешно удалена";
        executeStatement(dropTableQuery, succeedMessage);
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            PreparedStatement insert = connection.prepareStatement ("INSERT INTO users" +
                                                                        "(name, lastName, age) " +
                                                                        "VALUES (?, ?, ?)");
            insert.setString (1, name);
            insert.setString (2, lastName);
            insert.setByte (3, age);
            insert.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeUserById(long id) {
        String removeUserQuery = "DELETE FROM users WHERE id = " + id;
        String succeedMessage = "Запись с id = " + id + " успешно удалена";
        executeStatement(removeUserQuery, succeedMessage);
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM users");
            while (result.next()) {
                User newUser = new User(result.getString("name")
                        , result.getString("lastName")
                        , result.getByte("age"));
                userList.add(newUser);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userList;
    }

    public void cleanUsersTable() {
        String cleanTableQuery = "TRUNCATE TABLE users";
        String succeedMessage = "Таблица успешно очищена";
        executeStatement(cleanTableQuery, succeedMessage);
    }
}
