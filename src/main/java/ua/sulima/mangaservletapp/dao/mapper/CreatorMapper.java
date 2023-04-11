package ua.sulima.mangaservletapp.dao.mapper;

import ua.sulima.mangaservletapp.entity.Creator;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CreatorMapper implements FromResultSetMapper<Creator> {
    @Override
    public Creator retrieveFromResultSet(ResultSet resultSet) throws SQLException {
        return Creator.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .description(resultSet.getString("description"))
                .build();
    }
}
