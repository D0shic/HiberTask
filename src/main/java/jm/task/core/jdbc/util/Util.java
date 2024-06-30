package jm.task.core.jdbc.util;


import java.util.Properties;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

public class Util {
    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = getConfiguration();

                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.getMessage();
            }
        }
        return sessionFactory;
    }

    private static Configuration getConfiguration() {
        Configuration configuration = new Configuration();

        Properties settings = new Properties();
        settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        settings.put(Environment.URL, "jdbc:mysql://localhost:3306/test_schema?useSSL=false");
        settings.put(Environment.USER, "root");
        settings.put(Environment.PASS, "dorik5656");
        settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");

        settings.put(Environment.SHOW_SQL, "true");

        settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

        settings.put(Environment.HBM2DDL_AUTO, "create-drop");

        configuration.setProperties(settings);
        return configuration;
    }
}

//import java.sql.Connection;
//import com.mysql.cj.jdbc.Driver;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//
//public class Util {
//    // реализуйте настройку соеденения с БД
//    private static Connection connection;
//
//    private static final String URL = "jdbc:mysql://localhost:3306/test_schema";
//    private static final String USER = "root";
//    private static final String PASSWORD = "dorik5656";
//
//    private Util() {
//        // приватный конструктор чтобы нельзя было создать ненужных экземпляров
//    }
//
//    public static Connection getConnection() {
//        if (connection == null) {
//            try {
//                Driver driver = new Driver();
//                DriverManager.registerDriver(driver);
//
//                connection = DriverManager.getConnection(URL, USER, PASSWORD);
//            } catch (SQLException e) {
//                System.out.println(e.getMessage());
//            }
//        }
//        return connection;
//    }
//}
