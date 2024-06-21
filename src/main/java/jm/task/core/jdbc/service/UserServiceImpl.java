package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserDaoJDBCImpl userDAOcopy = new UserDaoJDBCImpl();

    public void createUsersTable() {
        userDAOcopy.createUsersTable();
    }

    public void dropUsersTable() {
        userDAOcopy.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        userDAOcopy.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        userDAOcopy.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return userDAOcopy.getAllUsers();
    }

    public void cleanUsersTable() {
        userDAOcopy.cleanUsersTable();
    }
}
