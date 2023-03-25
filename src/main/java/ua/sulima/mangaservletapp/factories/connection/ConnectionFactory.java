package ua.sulima.mangaservletapp.factories.connection;

import java.sql.Connection;

public abstract class ConnectionFactory {
    private static volatile ConnectionFactory connectionFactory;

    public static ConnectionFactory getHikariInstance() {
        if (connectionFactory == null) {
            synchronized (ConnectionFactory.class) {
                if (connectionFactory == null) {
                    ConnectionFactory dao = new ConnectionFactoryHikariImpl();
                    connectionFactory = dao;
                }
            }
        }
        return connectionFactory;
    }

    public abstract Connection getConnection();
}
