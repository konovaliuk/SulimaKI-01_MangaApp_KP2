package ua.sulima.mangaservletapp.factories.connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
class ConnectionFactoryHikariImpl extends ConnectionFactory {

    private static volatile DataSource dataSource;
    private static HikariConfig config = new HikariConfig();;

    static {
        config.setJdbcUrl( "jdbc:postgresql://localhost:5432/manga_db" );
        config.setUsername( "postgres" );
        config.setPassword( "admin" );
        config.setDriverClassName("org.postgresql.Driver");
        config.addDataSourceProperty( "cachePrepStmts" , "true" );
        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
        dataSource = new HikariDataSource(config);
    }


    @Override
    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            log.info("failed to get db connection");
            throw new RuntimeException(e);
        }
    }
}
