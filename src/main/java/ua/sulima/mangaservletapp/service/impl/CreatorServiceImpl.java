package ua.sulima.mangaservletapp.service.impl;

import lombok.RequiredArgsConstructor;
import ua.sulima.mangaservletapp.dao.CreatorDao;
import ua.sulima.mangaservletapp.dao.MangaDao;
import ua.sulima.mangaservletapp.entity.Creator;
import ua.sulima.mangaservletapp.entity.Manga;
import ua.sulima.mangaservletapp.factories.dao.DaoFactory;
import ua.sulima.mangaservletapp.service.CreatorService;

import java.util.Optional;
@RequiredArgsConstructor
public class CreatorServiceImpl implements CreatorService {
    private final DaoFactory daoFactory;

    @Override
    public Optional<Creator> getCreatorById(Integer id) {
        Optional<Creator> maybeCreator = Optional.empty();
        try(CreatorDao creatorDao = daoFactory.getCreatorDao()){
            maybeCreator = creatorDao.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maybeCreator;
    }
}
