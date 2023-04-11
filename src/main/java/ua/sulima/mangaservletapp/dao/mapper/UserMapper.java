package ua.sulima.mangaservletapp.dao.mapper;

import ua.sulima.mangaservletapp.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserMapper implements FromResultSetMapper<User> {

    @Override
    public User retrieveFromResultSet(ResultSet resultSet) throws SQLException {
        return User.builder()
                .id(resultSet.getLong("id"))
                .email(resultSet.getString("email"))
                .nickname(resultSet.getString("nickname"))
                .updated(resultSet.getTimestamp("updated"))
                .password(resultSet.getString("password"))
                .build();
    }
}
