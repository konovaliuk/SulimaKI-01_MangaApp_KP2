package ua.sulima.mangaservletapp.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface FromResultSetMapper<T> {

    T retrieveFromResultSet(ResultSet resultSet) throws SQLException;
}
