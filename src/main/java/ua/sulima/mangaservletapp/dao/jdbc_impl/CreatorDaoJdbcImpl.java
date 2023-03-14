package ua.sulima.mangaservletapp.dao.jdbc_impl;

import ua.sulima.mangaservletapp.dao.CreatorDao;
import ua.sulima.mangaservletapp.dao.mapper.CreatorMapper;
import ua.sulima.mangaservletapp.dao.mapper.MangaMapper;
import ua.sulima.mangaservletapp.entity.Creator;
import ua.sulima.mangaservletapp.entity.Manga;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class CreatorDaoJdbcImpl extends ConnectionDaoJdbcImpl
        implements CreatorDao {
    public CreatorDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Optional<Creator> findById(Integer id) {
        Creator creator = null;
        String statement = "SELECT * FROM mangaapp.creator WHERE id = ?;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(statement)) {
            //preparedStatement.setString(1, tableName);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                creator = new CreatorMapper().retrieveFromResultSet(resultSet);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.ofNullable(creator);
    }

    @Override
    public Integer save(Creator creatorToSave) {

        Integer generatedId = 0;
        String saveCreatorStatement =
                "INSERT INTO mangaapp.creator(name, description) VALUES (?, ?);";

        try (PreparedStatement saveCreatorPrepStatement = connection.prepareStatement(saveCreatorStatement
                ,PreparedStatement.RETURN_GENERATED_KEYS)) {

            saveCreatorPrepStatement.setString(1, creatorToSave.getName());
            saveCreatorPrepStatement.setString(2, creatorToSave.getDescription());

            saveCreatorPrepStatement.executeUpdate();

            ResultSet resultSet = saveCreatorPrepStatement.getGeneratedKeys();
            resultSet.next();
            generatedId = resultSet.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return generatedId;
    }
}
