package ua.sulima.mangaservletapp.dao.factories.dao;

import lombok.RequiredArgsConstructor;
import ua.sulima.mangaservletapp.dao.CreatorDao;
import ua.sulima.mangaservletapp.dao.MangaDao;
import ua.sulima.mangaservletapp.dao.UserDao;
import ua.sulima.mangaservletapp.dao.factories.connection.ConnectionFactory;
import ua.sulima.mangaservletapp.dao.jdbc_impl.CreatorDaoJdbcImpl;
import ua.sulima.mangaservletapp.dao.jdbc_impl.MangaDaoJdbcImpl;
import ua.sulima.mangaservletapp.dao.jdbc_impl.UserDaoJdbcImpl;

@RequiredArgsConstructor
class JdbcDaoFactory extends DaoFactory {
    private final ConnectionFactory connectionFactory;


    @Override
    public UserDao getUserDao() {
        return new UserDaoJdbcImpl(connectionFactory.getConnection());
    }

    @Override
    public MangaDao getMangaDao() {
        return new MangaDaoJdbcImpl(connectionFactory.getConnection());
    }

    @Override
    public CreatorDao getCreatorDao() {
        return new CreatorDaoJdbcImpl(connectionFactory.getConnection());
    }
}
