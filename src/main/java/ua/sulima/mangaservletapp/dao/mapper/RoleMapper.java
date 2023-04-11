package ua.sulima.mangaservletapp.dao.mapper;

import ua.sulima.mangaservletapp.entity.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleMapper implements FromResultSetMapper{
    @Override
    public Role retrieveFromResultSet(ResultSet resultSet) throws SQLException {
        return Role.builder()
                .id(resultSet.getShort("id"))
                .name(resultSet.getString("name"))
                .build();
    }
}
