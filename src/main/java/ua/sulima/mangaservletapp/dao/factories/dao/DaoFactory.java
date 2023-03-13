package ua.sulima.mangaservletapp.dao.factories.dao;

import ua.sulima.mangaservletapp.dao.MangaDao;
import ua.sulima.mangaservletapp.dao.UserDao;
import ua.sulima.mangaservletapp.dao.factories.connection.ConnectionFactory;

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

    public abstract MangaDao getMangaDao();
}
