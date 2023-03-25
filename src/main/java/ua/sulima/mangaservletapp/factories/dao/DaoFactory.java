package ua.sulima.mangaservletapp.factories.dao;

import ua.sulima.mangaservletapp.dao.CreatorDao;
import ua.sulima.mangaservletapp.dao.MangaDao;
import ua.sulima.mangaservletapp.dao.UserDao;
import ua.sulima.mangaservletapp.factories.connection.ConnectionFactory;

import java.sql.Connection;

public abstract class DaoFactory {

    private static volatile DaoFactory daoFactory;

    public static DaoFactory getJdbcInstance() {
        if (daoFactory == null) {
            synchronized (DaoFactory.class) {
                if (daoFactory == null) {
                    DaoFactory dao = new JdbcDaoFactory(ConnectionFactory.getHikariInstance());
                    daoFactory = dao;
                }
            }
        }
        return daoFactory;
    }

    public abstract UserDao getUserDao();

    public abstract UserDao getUserDao(Connection connection);

    public abstract MangaDao getMangaDao();

    public abstract MangaDao getMangaDao(Connection connection);

    public abstract CreatorDao getCreatorDao();

    public abstract CreatorDao getCreatorDao(Connection connection);
}
