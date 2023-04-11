package ua.sulima.mangaservletapp.service.impl;

import lombok.RequiredArgsConstructor;
import ua.sulima.mangaservletapp.dao.MangaDao;
import ua.sulima.mangaservletapp.entity.Manga;
import ua.sulima.mangaservletapp.factories.dao.DaoFactory;
import ua.sulima.mangaservletapp.service.MangaService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class MangaServiceImpl implements MangaService {
    private final DaoFactory daoFactory;

    @Override
    public Optional<Manga> getMangaById(Integer id) {

        Optional<Manga> maybeManga = Optional.empty();
        try(MangaDao mangaDao = daoFactory.getMangaDao()){
            maybeManga = mangaDao.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maybeManga;
    }

    @Override
    public List<Manga> getMangaList(Integer amountOnPage, Integer firstManga) {
        List<Manga> mangas= new ArrayList<>();
        try(MangaDao mangaDao = daoFactory.getMangaDao()){
            mangas = mangaDao.findAll(amountOnPage, firstManga);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mangas;
    }

}
