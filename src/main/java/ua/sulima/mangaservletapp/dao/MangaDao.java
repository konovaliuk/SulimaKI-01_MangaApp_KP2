package ua.sulima.mangaservletapp.dao;

import ua.sulima.mangaservletapp.entity.Manga;

import java.util.List;
import java.util.Optional;

public interface MangaDao extends BasicDao<Manga, Integer>{

    List<Manga> findAll(Integer limit, Integer offset);

}
