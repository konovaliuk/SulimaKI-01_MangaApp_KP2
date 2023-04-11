package ua.sulima.mangaservletapp.factories.dao;

import lombok.RequiredArgsConstructor;
import ua.sulima.mangaservletapp.dao.CreatorDao;
import ua.sulima.mangaservletapp.dao.MangaDao;
import ua.sulima.mangaservletapp.dao.UserDao;
import ua.sulima.mangaservletapp.factories.connection.ConnectionFactory;
import ua.sulima.mangaservletapp.dao.jdbc_impl.CreatorDaoJdbcImpl;
import ua.sulima.mangaservletapp.dao.jdbc_impl.MangaDaoJdbcImpl;
import ua.sulima.mangaservletapp.dao.jdbc_impl.UserDaoJdbcImpl;

import java.sql.Connection;

@RequiredArgsConstructor
class JdbcDaoFactory extends DaoFactory {
    private final ConnectionFactory connectionFactory;


    @Override
    public UserDao getUserDao(Connection connection) {
        return new UserDaoJdbcImpl(connection);
    }

    @Override
    public UserDao getUserDao(){
        return getUserDao(connectionFactory.getConnection());
    }

    @Override
    public MangaDao getMangaDao(Connection connection) {
        return new MangaDaoJdbcImpl(connection);
    }
    @Override
    public MangaDao getMangaDao() {
        return getMangaDao(connectionFactory.getConnection());
    }

    @Override
    public CreatorDao getCreatorDao(Connection connection) {
        return new CreatorDaoJdbcImpl(connection);
    }
    @Override
    public CreatorDao getCreatorDao() {
        return getCreatorDao(connectionFactory.getConnection());
    }
}
