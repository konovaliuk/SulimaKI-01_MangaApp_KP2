package ua.sulima.mangaservletapp.service;

import ua.sulima.mangaservletapp.entity.Manga;

import java.util.List;
import java.util.Optional;

public interface MangaService {
    Optional<Manga> getMangaById(Integer id);

    List<Manga> getMangaList(Integer amountOnPage, Integer firstManga);

}
