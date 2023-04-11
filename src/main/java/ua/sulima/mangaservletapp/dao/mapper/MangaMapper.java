package ua.sulima.mangaservletapp.dao.mapper;

import ua.sulima.mangaservletapp.entity.Creator;
import ua.sulima.mangaservletapp.entity.Manga;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MangaMapper implements FromResultSetMapper<Manga> {

    @Override
    public Manga retrieveFromResultSet(ResultSet resultSet) throws SQLException {
        return Manga.builder()
                .id(resultSet.getInt("id"))
                .mangaName(resultSet.getString("manga_name"))
                .authorId(resultSet.getInt("author_id"))
                .artistId(resultSet.getInt("artist_id"))
                .releaseYear(resultSet.getShort("release_year"))
                .translatorId(resultSet.getLong("translator_id"))
                .alternativeMangaName(resultSet.getString("alternative_manga_name"))
                .addDatetime(resultSet.getTimestamp("add_datetime"))
                .updateDatetime(resultSet.getTimestamp("update_datetime"))
                .approvalDatetime(resultSet.getTimestamp("approval_datetime"))
                .previewImagePath(resultSet.getString("preview_image_path"))
                .isApproved(resultSet.getBoolean("is_approved"))
                .build();
    }
}
