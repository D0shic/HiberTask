package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private static final SessionFactory sessionFactory = Util.getSessionFactory();
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS users (id INT AUTO_INCREMENT PRIMARY KEY," +
                                  " name VARCHAR(50), lastName VARCHAR(50), age INT)";
        String succeedMessage   = "Таблица успешно создана";
        executeQuery(createTableQuery, succeedMessage);
    }

    @Override
    public void dropUsersTable() {
        String dropTableQuery = "DROP TABLE IF EXISTS users";
        String succeedMessage = "Таблица успешно удалена";
        executeQuery(dropTableQuery, succeedMessage);
    }

    private static void executeQuery(String sqlQuery, String message) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            String createTableQuery = sqlQuery;
            Query query = session.createSQLQuery(createTableQuery);
            query.executeUpdate();
            transaction.commit();
            System.out.println(message);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;

        String removeUserQuery = "DELETE FROM User WHERE id = :id";
        try (Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();
            Query query = session.createQuery(removeUserQuery);
            query.setParameter("id", id);
            query.executeUpdate();
            transaction.commit();
            System.out.println("Запись с id = " + id + " успешно удалена");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from User", User.class).list();
        }
    }

    @Override
    public void cleanUsersTable() {
        String cleanTableQuery = "TRUNCATE TABLE users";
        String succeedMessage = "Таблица успешно очищена";
        executeQuery(cleanTableQuery, succeedMessage);
    }
}
