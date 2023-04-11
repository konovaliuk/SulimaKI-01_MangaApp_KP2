package ua.sulima.mangaservletapp.dao.jdbc_impl;

import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class ConnectionDaoJdbcImpl implements AutoCloseable {

    protected final Connection connection;

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
