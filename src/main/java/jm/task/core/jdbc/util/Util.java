package jm.task.core.jdbc.util;
import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

public class Util {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/mydb?autoReconnect=true&useSSL=false";
    private static final String USERNAME = "rroot";
    private static final String PASSWORD = "rroot";

    private static SessionFactory sessionFactory;

    public static org.hibernate.SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration()
                    .addAnnotatedClass(User.class)
                    .setProperty(Environment.DRIVER, DRIVER)
                    .setProperty(Environment.URL, URL)
                    .setProperty(Environment.USER, USERNAME)
                    .setProperty(Environment.PASS, PASSWORD)
                    .setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect")
                    .setProperty(Environment.SHOW_SQL, "true")
                    .setProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread")
                    .setProperty(Environment.HBM2DDL_AUTO, "create-drop");
            StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        return sessionFactory;
    }
}
