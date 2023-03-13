package ua.sulima.mangaservletapp.dao.jdbc_impl;

import ua.sulima.mangaservletapp.dao.CreatorDao;
import ua.sulima.mangaservletapp.entity.Creator;

import java.sql.Connection;
import java.util.Optional;

public class CreatorDaoJdbcImpl extends ConnectionDaoJdbcImpl
        implements CreatorDao {
    public CreatorDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Optional<Creator> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public Integer save(Creator entity) {

        return 0;
    }
}
