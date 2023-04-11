package ua.sulima.mangaservletapp.dao.jdbc_impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import ua.sulima.mangaservletapp.dao.MangaDao;
import ua.sulima.mangaservletapp.dao.mapper.MangaMapper;
import ua.sulima.mangaservletapp.dao.mapper.UserMapper;
import ua.sulima.mangaservletapp.entity.Manga;
import ua.sulima.mangaservletapp.entity.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.rmi.server.LogStream.log;

@Slf4j
public class MangaDaoJdbcImpl extends ConnectionDaoJdbcImpl implements MangaDao {

    public MangaDaoJdbcImpl(Connection connection){
        super(connection);
    }

    public Optional<Manga> findById(Integer id) {

        Manga manga = null;
        String statement = "SELECT * FROM mangaapp.manga WHERE id = ?;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(statement)) {
            //preparedStatement.setString(1, tableName);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                manga = new MangaMapper().retrieveFromResultSet(resultSet);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.ofNullable(manga);
    }


    @Override
    public Integer save(Manga mangaToSave) {

        Integer generatedId = 0;
        String saveUserStatement =
                "insert into mangaapp.manga(manga_name, author_id, artist_id, release_year, " +
                        "translator_id, preview_image_path" +
                        ",add_datetime, update_datetime)\n" +
                        "VALUES (?, ?, ?, ?, ?, ?, DEFAULT, DEFAULT);";

        try (PreparedStatement saveUser = connection.prepareStatement(saveUserStatement
                ,PreparedStatement.RETURN_GENERATED_KEYS)) {

            saveUser.setString(1, mangaToSave.getMangaName());
            saveUser.setInt(2, mangaToSave.getAuthorId());
            saveUser.setInt(3, mangaToSave.getArtistId());
            saveUser.setShort(4, mangaToSave.getReleaseYear());
            saveUser.setLong(5, mangaToSave.getTranslatorId());
            saveUser.setString(6, mangaToSave.getPreviewImagePath());

            saveUser.executeUpdate();

            ResultSet resultSet = saveUser.getGeneratedKeys();
            resultSet.next();
            generatedId = resultSet.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return generatedId;
    }

    @Override
    public List<Manga> findAll(Integer limit, Integer offset) {
        Manga manga = null;
        String statement = "SELECT * FROM mangaapp.manga LIMIT ? OFFSET ?;";
        List<Manga> mangas = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(statement)) {
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                mangas.add(new MangaMapper().retrieveFromResultSet(resultSet));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return mangas;
    }
}
