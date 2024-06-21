package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь

        UserServiceImpl userTest = new UserServiceImpl();
        userTest.createUsersTable();

        userTest.saveUser("Oleg", "Kladovoy", (byte)45);
        userTest.saveUser("Peter", "Armanskiy", (byte)31);
        userTest.saveUser("Gayorgy", "Kustovoy", (byte)28);
        userTest.saveUser("Olesya", "Moshka", (byte)23);

        List<User> usersList = userTest.getAllUsers();
        for (User user : usersList) {
            System.out.println(user.toString());
        }

        userTest.cleanUsersTable();
        userTest.dropUsersTable();
    }
}
