package lk.sudha.pos.hibernate.db;

import lk.sudha.pos.hibernate.entity.Customer;
import lk.sudha.pos.hibernate.entity.Item;
import lk.sudha.pos.hibernate.entity.Order;
import lk.sudha.pos.hibernate.entity.OrderDetail;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class HibernateUtil {

    private static String username;
    private static String password;
    private static String database;
    private static String host;
    private static String port;

    private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        File proFile = new File("resources/application.properties");
        Properties properties = new Properties();

        try (FileInputStream fis = new FileInputStream(proFile)) {
            properties.load(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        username = properties.getProperty("hibernate.connection.username");
        password = properties.getProperty("hibernate.connection.password");
        host = properties.getProperty("sudha.pos.url");
        port = properties.getProperty("sudha.pos.port");
        database = properties.getProperty("sudha.pos.db");

        StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                .loadProperties(proFile)
                .applySetting("hibernate.connection.username", username)
                .applySetting("hibernate.connection.password", password)
                .build();

        Metadata metadata = new MetadataSources(standardRegistry)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Item.class)
                .addAnnotatedClass(Order.class)
                .addAnnotatedClass(OrderDetail.class)
                .getMetadataBuilder()
                .applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE)
                .build();

        return metadata.getSessionFactoryBuilder()
                .build();

    }

    public static String getUsername() {

        return username;
    }

    public static String getPassword() {
        return password;
    }

    public static String getDatabase() {
        return database;
    }

    public static String getHost() {
        return host;
    }

    public static String getPort() {
        return port;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
